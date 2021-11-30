package plugins.quorum.Libraries.System;

import com.fazecast.jSerialComm.*;
import java.io.InputStream;
import java.util.Arrays;

public class Serial{
    public java.lang.Object me_ = null;

    private SerialPort sp;

    public void Send(String msg){
        try{
            byte[] outBytes = msg.getBytes("UTF8");
            sp.writeBytes(outBytes, outBytes.length);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public String Read(){
        byte[] inBytes = new byte[1024];
        InputStream is = sp.getInputStream();
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

    public boolean Connect(String port){
        sp = SerialPort.getCommPort(port);
        if(sp.isOpen()) return true;
        else{
            sp.openPort();
            //Read and write will block for 30 seconds
            sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 30000, 30000);
            return sp.isOpen();
        }
    }
    public void Close(){
        sp.closePort();
    }

    /* 
    Example main function to test functionality:
    //Tested with the general testing block in main.
    //This was tested using virtual serial ports with pseudo terminals on Linux:
        socat -d -d pty,raw,echo=0 pty,raw,echo=0
    Then, using the two ports returned by that command (e.g /dev/pts/1 and /dev/pts/2)
    as the ports for Connect with two separate instances of this program, communication
    went through. 
    */
    // public static void main(String[] args){
    //     String msg;
    //     Serial cereal = new Serial();
    //     System.out.print("Port: ");
    //     String port = System.console().readLine();
    //     while(!cereal.Connect(port));

        //-Comment out whichever block isn't being tested-//
        //For general testing:
        //cereal.Send("hello");
        //System.out.println(cereal.Read());

         //For LEGO SPIKE Hub testing with legoAccept.py:
    //     cereal.Read();
    //     cereal.Send("[\"Motor\", \"A\", \"RunForDegrees\", 360]");
    //     msg = cereal.Read();
    //     System.out.println(msg);
    //     while(!msg.contains("Success!")) msg = cereal.Read();
    //     System.out.println(msg);
    //     cereal.Close();
    // }  
   
}
