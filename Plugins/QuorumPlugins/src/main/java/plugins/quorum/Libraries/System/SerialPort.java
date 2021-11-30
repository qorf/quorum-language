package plugins.quorum.Libraries.System;

import java.io.InputStream;
import java.util.Arrays;

public class SerialPort {
    public java.lang.Object me_ = null;
    private com.fazecast.jSerialComm.SerialPort port = null;

    public String GetName() {
        return getPort().getSystemPortName();
    }

    public String GetDescription() {
        return getPort().getPortDescription();
    }

    public String GetDescriptiveName() {
        return getPort().getDescriptivePortName();
    }

    public boolean Close() {
        return getPort().closePort();
    }

    public boolean Open() {
        return getPort().openPort();
    }

    public boolean Open(int timeout) {
        return getPort().openPort(timeout);
    }

    public void Write(String msg){
        try{
            byte[] outBytes = msg.getBytes("UTF8");
            port.writeBytes(outBytes, outBytes.length);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String Read(){
        byte[] inBytes = new byte[1024];
        InputStream is = port.getInputStream();
        String readMsg = "";

        try{
            is.read(inBytes);
            for (int b = 0; b < inBytes.length; b++) {
                if(inBytes[b] == 0 || b == inBytes.length - 1){
                    readMsg = new String(Arrays.copyOfRange(inBytes, 0, b), "UTF8");
                    break;
                }
            }
            is.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return readMsg;
    }

    public com.fazecast.jSerialComm.SerialPort getPort() {
        return port;
    }

    public void setPort(com.fazecast.jSerialComm.SerialPort port) {
        this.port = port;
    }
}
