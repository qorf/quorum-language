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
    private static final byte[] OK = new byte[]{'>', 'O', 'K'};
    private static final int MAX_READ_BYTES = 1024 * 1024;
    private boolean isInREPL = false;
    private StringBuilder programOutput = new StringBuilder();

    public void CopyBytesToFile(byte[] data, String outputFilename) {
        EnterREPL();
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
        final int blockSize = 256;
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

            EnterREPL();
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
        EnterREPL();
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

        isInREPL = true;
        port.writeBytes(CTRL_C, CTRL_C.length);
        port.writeBytes(CTRL_C, CTRL_C.length);  // cancel any running code
        port.writeBytes(CTRL_A, CTRL_A.length);  // enter raw mode: ctrl-d to submit code, ctrl-b to exit
        port.flushIOBuffers();


        //Not sure about this. Is there a way to detect when it's finished. Maybe a future, instead of a hard
        //sleep call?
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void WritePython(String str) {
        com.fazecast.jSerialComm.SerialPort port = GetPort();

        EnterREPL();
        // note: StandardCharsets.UTF_8.encode(str).array() gets the bytebuffer's underlying array which
        //  can contain extra 0's at the end and therefore contaminate the data...
        byte[] bytesToSend = str.getBytes(UTF_8);
        port.writeBytes(bytesToSend, bytesToSend.length);
        port.writeBytes(CTRL_D, CTRL_D.length);  // have the raw REPL run the command

        // Read until ">OK" then consume all remaining bytes: at that point, it is assumed the next write can be done.
        //  A potential problem here might occur if the REPL takes too long to finish executing and therefore the read times out
        //  after reading 0 bytes.
        int matchLength = OK.length;
        int curMatch = 0;
        byte[] lastChar = new byte[1];
        boolean success = false;
        int bytesRead = 0;
        while (bytesRead < MAX_READ_BYTES) {
            int readNow = port.readBytes(lastChar, 1);
            if (readNow == 0) {
                break;
            }

            bytesRead += readNow;
            if (lastChar[0] == OK[curMatch]) {
                ++curMatch;
                if (curMatch == matchLength) {
                    success = true;
                    break;
                }
            } else {
                curMatch = 0;
            }
        }

        // Once ">OK" has been read, all remaining output from the code follows, so consume it to avoid filling the buffer
        if (success) {
            byte[] printedOutput = new byte[1024];
            int readAmount;
            while ((readAmount = port.readBytes(printedOutput, printedOutput.length)) != 0) {
                programOutput.append(StandardCharsets.UTF_8.decode(ByteBuffer.wrap(printedOutput, 0, readAmount)));
            }
        }

        port.flushIOBuffers();
    }
}
