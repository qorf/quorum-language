package org.quorum.lego;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Original program referenced under BSD license:
 * http://www.jcraft.com/jsch/examples/ScpTo.java.html
 */
public class QuorumToLegoAdapter {

    //all fields initialized to their default values for the EV3
    private String user = "root";
    private String host = "10.0.1.1";
    private String password = "";
    private String destinationPath = "/home/lejos/programs/";
    private static final int TIMEOUT = 5000;

    public boolean IsLegoConnected() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(getUser(), getHost(), 22);
            UserInfo ui = new DefaultUserInfo();
            session.setUserInfo(ui);
            session.connect(TIMEOUT);
            session.disconnect();
            return true;
        } catch (JSchException ex) {
            return false;
        }
    }

    public void Run() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(getUser(), getHost(), 22);

            // username and password will be given via UserInfo interface.
            UserInfo ui = new DefaultUserInfo();
            session.setUserInfo(ui);
            session.connect(TIMEOUT);

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("jrun -jar " + getDestinationPath() + "Default.jar"); //run a jar over ssh on the ev3 

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect(TIMEOUT);

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {

        }
    }

    public boolean Send(File from) {
        File to = new File(getDestinationPath()); //directory
        return Send(from, to);
    }

    public boolean Send(File from, File to) {
        
        FileInputStream fis = null;
        try {
            String sourcePath = from.getName();
            String destinationPath = to.getPath().replace("\\", "/");
//            String destinationPath = "'/home/lejos/programs'"; //works for Windows
            

            JSch jsch = new JSch();
            Session session = jsch.getSession(getUser(), getHost(), 22);

            // username and password will be given via UserInfo interface.
            UserInfo ui = new DefaultUserInfo();
            session.setUserInfo(ui);
            session.connect(TIMEOUT);

            // exec 'scp -t destinationPath' remotely
            String command = "scp " + " -t " + destinationPath;
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // get I/O streams for remote scp
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();

            channel.connect(TIMEOUT);

            if (checkAck(in) != 0) {
                Logger.getLogger(QuorumToLegoAdapter.class.getName()).log(Level.INFO, "Error in input buffer after connecting channels");
                out.close();
                channel.disconnect();
                session.disconnect();
                return false;
            }

            // send "C0644 filesize filename", where filename should not include '/'
            long filesize = from.length();
            command = "C0644 " + filesize + " ";
            if (sourcePath.lastIndexOf('/') > 0) {
                command += sourcePath.substring(sourcePath.lastIndexOf('/') + 1);
            } else {
                command += sourcePath;
            }
            command += "\n";
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                Logger.getLogger(QuorumToLegoAdapter.class.getName()).log(Level.INFO, "Error in input buffer after writing command to output");
                out.close();
                channel.disconnect();
                session.disconnect();
                return false;
            }

            // send a content of sourcePath
            fis = new FileInputStream(from.getAbsolutePath());
            byte[] buf = new byte[1024];
            while (true) {
                int len = fis.read(buf, 0, buf.length);
                if (len <= 0) {
                    break;
                }
                out.write(buf, 0, len); //out.flush();
            }
            fis.close();
            fis = null;

            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            if (checkAck(in) != 0) {
                Logger.getLogger(QuorumToLegoAdapter.class.getName()).log(Level.INFO, "Error in input buffer after writing '\0' to output");
                out.close();
                channel.disconnect();
                session.disconnect();
                return false;
            }

            out.close();

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            Logger.getLogger(QuorumToLegoAdapter.class.getName()).log(Level.INFO, null, e);
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception ee) {
                Logger.getLogger(QuorumToLegoAdapter.class.getName()).log(Level.INFO, null, ee);
            }
            return false;
        }
        return true;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param aDefaultHost the host to set
     */
    public void setHost(String aDefaultHost) {
        host = aDefaultHost;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param aDefaultUser the user to set
     */
    public void setUser(String aDefaultUser) {
        user = aDefaultUser;
    }

    /**
     * @return the destinationPath
     */
    public String getDestinationPath() {
        return destinationPath;
    }

    static int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if (b == 0) {
            return b;
        }
        if (b == -1) {
            return b;
        }

        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            } while (c != '\n');
            if (b == 1) { // error
                System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //A UserInfo object must be passed to instantiate a connection, but we don't need all of its functionality.
    //This subclass suppresses all of the connection prompts and uses the default EV3 password.
    public class DefaultUserInfo implements UserInfo {

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptYesNo(String str) {
            return true; //was used if user clicked the "yes" button on the prompt with the text:
            //"The authenticity of host '"+host+"' can't be established.\n"+
            //key_type+" key fingerprint is "+key_fprint+".\n"+
            //"Are you sure you want to continue connecting?"
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return false;
        }

        @Override
        public boolean promptPassword(String message) {
            return true; //simulates the user pressing the "OK" button after typing in their password
        }

        @Override
        public void showMessage(String message) {
            //do nothing, we don't need any pop up
        }
    }
}
