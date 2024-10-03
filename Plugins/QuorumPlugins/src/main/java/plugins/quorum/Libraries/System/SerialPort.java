package plugins.quorum.Libraries.System;

import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import quorum.Libraries.System.SerialPort_;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SerialPort {
    public java.lang.Object me_ = null;
    private com.fazecast.jSerialComm.SerialPort port = null;
    private int readTimeout = 250;
    private int writeTimeout = 0;
    private int baudRate = 115200;

    public int GetTimeoutWriteBlocking() {
        return com.fazecast.jSerialComm.SerialPort.TIMEOUT_WRITE_BLOCKING;
    }

    public int GetTimeoutReadSemiBlocking() {
        return com.fazecast.jSerialComm.SerialPort.TIMEOUT_READ_SEMI_BLOCKING;
    }

    public int GetTimeoutNonBlocking() {
        return com.fazecast.jSerialComm.SerialPort.TIMEOUT_NONBLOCKING;
    }

    public int GetDefaultTimeout() {
        return com.fazecast.jSerialComm.SerialPort.TIMEOUT_WRITE_BLOCKING | com.fazecast.jSerialComm.SerialPort.TIMEOUT_READ_SEMI_BLOCKING;
    }

    public void SetTimeouts(int timeout, int read, int write) {
        port.setComPortTimeouts(timeout, read, write);
    }

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
        return port.openPort();
    }

    public boolean IsOpen() {
        return getPort().isOpen();
    }

    public void Write(String msg) throws Exception {
        byte[] outBytes = msg.getBytes("UTF8");
        port.writeBytes(outBytes, outBytes.length);
    }

    public void Write(int value) {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(value);
        byte[] array = b.array();
        port.writeBytes(array, array.length);
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

    public void InitializeListener() {
        port.removeDataListener();
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_RECEIVED |
                        com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_PORT_DISCONNECTED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                SerialPort_ port = (SerialPort_) me_;
                int type = event.getEventType();
                if (type == com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
                    byte[] data = event.getReceivedData();
                    ByteBuffer buffer = ByteBuffer.wrap(data);
                    String result = StandardCharsets.UTF_8.decode(buffer).toString();
                    port.SendReceievedEventToListeners(result);
                } else if(type == com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_PORT_DISCONNECTED) {
                    port.Close();
                }
                }
        });
    }

    public void SetBaudRate(int rate) {
        port.setBaudRate(rate);
    }

    public int GetBaudRate() {
        return port.getBaudRate();
    }
}
