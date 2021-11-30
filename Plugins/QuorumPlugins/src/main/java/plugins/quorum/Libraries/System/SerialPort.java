package plugins.quorum.Libraries.System;

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

    public com.fazecast.jSerialComm.SerialPort getPort() {
        return port;
    }

    public void setPort(com.fazecast.jSerialComm.SerialPort port) {
        this.port = port;
    }
}
