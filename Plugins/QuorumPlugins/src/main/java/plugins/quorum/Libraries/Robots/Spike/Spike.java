package plugins.quorum.Libraries.Robots.Spike;

import quorum.Libraries.Robots.Spike.Spike_;
import quorum.Libraries.System.File_;
import quorum.Libraries.System.SerialPort;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
public class Spike {
    public Object me_ = null;

    private static final int SLOT_MAX = 19;
    private static final String[] B64_PADDING_LOOKUP = new String[]{"", "=", "==", "==="};
    private static final byte[] CTRL_A = new byte[]{'\u0001'};
    private static final byte[] CTRL_B = new byte[]{'\u0002'};
    private static final byte[] CTRL_C = new byte[]{'\u0003'};
    private static final byte[] CTRL_D = new byte[]{'\u0004'};
    private static final byte[] OK = new byte[]{'O', 'K'};
    private static final byte[] REPL_READY = new byte[]{'\u0004', '\u0004', '>'};
    private static final int MAX_READ_BYTES = 1024 * 1024;
    private static final int MAX_EMPTY_READS = 200;
    private static final int MAX_SLACK_READS = 10;
    private boolean isInREPL = false;
    private StringBuilder programOutput = new StringBuilder();

    public void CopyBytesToFile(byte[] data, String outputFilename) {
        String b64Contents = Base64.getEncoder().encodeToString(data);
        WritePython(
            String.format(
                "import binascii\n" +
                "d = binascii.a2b_base64\n" +
                "f = open(r'%s', 'wb')\n" +
                "w = f.write", outputFilename
            )
        );

        // blockSize MUST be a multiple of 4
        final int blockSize = 1280;
        for (int i = 0; i < b64Contents.length(); i += blockSize) {
            String b64Substring = b64Contents.substring(i, Math.min(i + blockSize, b64Contents.length()));
            int remainder = b64Substring.length() % 4;
            if (remainder != 0) {
                b64Substring = b64Substring + B64_PADDING_LOOKUP[remainder];
            }
            WritePython(String.format("w(d('%s'))", b64Substring));
        }
        WritePython("f.close()");
    }

    public void CopyToFile(File_ file, String name) {
        String path = file.GetAbsolutePath();
        Path filenamePath = FileSystems.getDefault().getPath(path);
        try {
            byte[] data = Files.readAllBytes(filenamePath);
            CopyBytesToFile(data, name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CopyToSlot(File_ file, int slot) {
        String path = file.GetAbsolutePath();
        Path filenamePath = FileSystems.getDefault().getPath(path);
        try {
            byte[] data = Files.readAllBytes(filenamePath);
            slot = Math.max(0, Math.min(SLOT_MAX, slot));
            String slotPath = String.format("/flash/program/%s", (slot >= 10 ? "" : "0") + slot);

            WritePython(
            "import os\n" +
                "try:\n" +
                "   os.mkdir('/flash/program')\n" +
                "except:\n" +
                "   pass"
            );
            RemoveDirectory(slotPath);
            WritePython(
                String.format(
                    "try:\n" +
                    "   os.mkdir(r'%s')\n" +
                    "except:\n" +
                    "   pass",
                    slotPath
                )
            );
            CopyBytesToFile(data, slotPath + "/program.py");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CloseNative() {
        com.fazecast.jSerialComm.SerialPort port = GetPort();
        if (isInREPL && port != null) {
            port.writeBytes(CTRL_B, CTRL_B.length);
            port.writeBytes(CTRL_D, CTRL_D.length);
        }
    }

    public void RemoveDirectory(String directoryToRemove) {
        final String rmSetup =
                "import os\n" +
                "def rm(directory):\n" +
                "   try:\n" +
                "       for entry in os.ilistdir(directory):\n" +
                "           name, entry_type = entry[0], entry[1]\n" +
                "           is_dir = entry_type == 0x4000\n" +
                "           subdir = directory + ('' if directory.endswith('/') else '/') + name\n" +
                "           try:\n" +
                "               if is_dir:\n" +
                "                   rm(subdir)\n" +
                "                   os.rmdir(subdir)\n" +
                "               else:\n" +
                "                   os.remove(subdir)\n" +
                "           except:\n" +
                "               pass\n" +
                "           os.rmdir(directory)\n" +
                "   except:\n" +
                "       pass\n";

        WritePython(rmSetup);
        WritePython(String.format("rm(r'%s')", directoryToRemove));
    }

    public String GetOutput() {
        String output = programOutput.toString();
        programOutput = new StringBuilder();
        return output;
    }

    private com.fazecast.jSerialComm.SerialPort GetPort() {
        Spike_ spike = (Spike_) me_;
        //this is somewhat unsafe if there are SerialPortSubclasses, but that's not relevant for serial ports I suspect
        SerialPort quorumPort = (SerialPort) spike.GetOpenPort();
        if(quorumPort == null) {
            return null;
        }
        com.fazecast.jSerialComm.SerialPort port = quorumPort.plugin_.getPort();
        return port;
    }

    private void EnterREPL() {
        com.fazecast.jSerialComm.SerialPort port = GetPort();
        if (isInREPL) {
            return;
        }

        // clear the read buffer of anything previous
        byte[] temp = new byte[512];
        while (port.readBytes(temp, temp.length) > 0) {
            // wait until there is nothing more to read
        }

        isInREPL = true;
        port.writeBytes(CTRL_C, CTRL_C.length);
        port.writeBytes(CTRL_C, CTRL_C.length);  // cancel any running code
        port.writeBytes(CTRL_A, CTRL_A.length);  // enter raw mode: ctrl-d to submit code, ctrl-b to exit

        // wait until the > prompt is read signifying the REPL is ready
        ReadUntilMatchAndEmpty(new byte[]{'>'});
    }

    public void WritePython(String str) {
        com.fazecast.jSerialComm.SerialPort port = GetPort();

        EnterREPL();
        // note: StandardCharsets.UTF_8.encode(str).array() gets the bytebuffer's underlying array which
        //  can contain extra 0's at the end and therefore contaminate the data...
        byte[] bytesToSend = str.getBytes(UTF_8);
        port.writeBytes(bytesToSend, bytesToSend.length);
        port.writeBytes(CTRL_D, CTRL_D.length);  // have the raw REPL run the command

        // Once the OK bytes have been read, all printed output from the code follows, if any, then "\u0004\u0004>" to
        //  signify the REPL is ready again. Reading until "\u0004\u0004>" is slightly dangerous since it could technically be
        //  printed as output, but there isn't really a better way.
        // Note: The "OK" string is printed immediately and any program output during execution is printed as it is received.
        boolean success = ReadUntil(OK);
        if (!success) {
            throw new RuntimeException("Could not read OK string after writing Python");
        }
        programOutput.append(ReadUntilMatchAndEmpty(REPL_READY));
    }

    // Reads input until a sequence of bytes are found followed by an empty read, or until there is nothing more to read.
    // The data read is returned as a string, with the byte sequence removed from the right if it was found.
    private String ReadUntilMatchAndEmpty(byte[] stripFromRight) {
        com.fazecast.jSerialComm.SerialPort port = GetPort();

        ArrayList<Byte> bytes = new ArrayList<>();
        int matchLength = stripFromRight.length;
        int currentMatch = 0;
        boolean rightStripFound = false;
        int bytesRead = 0;
        int emptyReads = 0;
        byte[] lastChar = new byte[1];
        while (bytesRead < MAX_READ_BYTES && emptyReads < MAX_EMPTY_READS) {
            int readNow = port.readBytes(lastChar, 1);
            if (readNow == 0) {
                if (rightStripFound) {
                    // an empty read with the last bytes being those to be stripped - success
                    break;
                }
                emptyReads += 1;
                continue;
            } else {
                bytes.add(lastChar[0]);
            }

            bytesRead += readNow;
            if (lastChar[0] == stripFromRight[currentMatch]) {
                ++currentMatch;
                if (currentMatch == matchLength) {
                    rightStripFound = true;
                    currentMatch = 0;
                }
            } else {
                currentMatch = 0;
                rightStripFound = false;
            }
        }

        if (bytes.isEmpty()) {
            return "";
        } else {
            byte[] byteArray = new byte[bytes.size()];
            for (int i = 0; i < bytes.size(); i++) {
                byteArray[i] = bytes.get(i);
            }

            String str = new String(byteArray, StandardCharsets.UTF_8);
            String stripFromRightStr = new String(stripFromRight, StandardCharsets.UTF_8);
            if (str.endsWith(stripFromRightStr)) {
                str = str.substring(0, str.length() - stripFromRightStr.length());
            }

            return str;
        }
    }

    // Reads from the serial port until the given sequence is found.
    // If the given sequence is found, reading is stopped after the sequence and true is returned.
    // If the maximum number of bytes are read or too many empty reads have been done, false is returned.
    private boolean ReadUntil(byte[] sequence) {
        com.fazecast.jSerialComm.SerialPort port = GetPort();

        int lenientEmptyReads = 0;
        int matchLength = sequence.length;
        int currentMatch = 0;
        byte[] lastChar = new byte[1];
        boolean success = false;
        int bytesRead = 0;
        int emptyReads = 0;
        boolean hasRead = false;
        while (bytesRead < MAX_READ_BYTES && emptyReads < MAX_EMPTY_READS) {
            int readNow = port.readBytes(lastChar, 1);
            if (readNow == 0) {
                if (hasRead && ++lenientEmptyReads > MAX_SLACK_READS) {
                    // Max number of empty reads after a non-empty read reached.
                    // This likely means there is nothing more to read, but that isn't guaranteed.
                    break;
                }
                emptyReads += 1;
                continue;
            } else {
                hasRead = true;
            }

            bytesRead += readNow;
            if (lastChar[0] == sequence[currentMatch]) {
                ++currentMatch;
                if (currentMatch == matchLength) {
                    success = true;
                    break;
                }
            } else {
                currentMatch = 0;
            }
        }

        return success;
    }
}
