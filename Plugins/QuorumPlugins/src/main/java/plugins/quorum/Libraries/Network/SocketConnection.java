package plugins.quorum.Libraries.Network;

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
            writer.println(outputText);
        }
    }

    public String GetNextLine() {
        if(socket != null) {
            InputStream input = null;
            try {
                input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String line;

                if ((line = reader.readLine()) != null) {
                    return line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String GetFullResponse() {
        if(socket != null) {
            InputStream input = null;
            try {
                input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String result = "";
                String line;

                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                return result;
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
