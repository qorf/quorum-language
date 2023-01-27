package plugins.quorum.Libraries.Network;

import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketConnection {
    public java.lang.Object me_ = null;
    Socket socket = null;

    public void Open(String hostname, int port) {
        try  {
            socket = new Socket(hostname, port);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public void Write(String outputText) {
        if(socket != null) {
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter writer = new PrintWriter(output, true);
            writer.print(outputText);
            writer.flush();
        }
    }

    public void WriteBytes(ByteArray_ byteArray) {
        quorum.Libraries.Containers.ByteArray arr = (quorum.Libraries.Containers.ByteArray)byteArray;
        if(socket != null) {
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
                DataOutputStream writer = new DataOutputStream(output);
                byte[] bytes = arr.plugin_.getBytes();

                writer.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int AvailableBytes() {
        if(socket != null) {
            InputStream input = null;
            try {
                input = socket.getInputStream();
                return input.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public String Read(int numberOfBytes){
        if(socket != null) {
            InputStream input = null;
            try {
                input = socket.getInputStream();
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i < numberOfBytes; i++) {
                    builder.append( (char)input.read());
                }
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public ByteArray_ ReadBytes(int numberOfBytes) {
        ByteArray array = new ByteArray();
        byte[] bytes = null;
        if(socket != null) {
            InputStream input = null;
            try {
                bytes = new byte[numberOfBytes];
                input = socket.getInputStream();
                int numRead = input.read(bytes, 0, numberOfBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        array.plugin_.setBytes(bytes);
        return array;
    }

    public String ReadLine() {
        if(socket != null) {
            InputStream input = null;
            try {
                input = socket.getInputStream();
                StringBuilder builder = new StringBuilder();
                int lastCharacter = input.read();
                while(lastCharacter != -1 && lastCharacter != 0) {
                    builder.append((char) lastCharacter);
                    if(lastCharacter == 10) {
                        break;
                    }
                    lastCharacter = input.read();
                }
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void Close() {
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = null;
        }
    }
}
