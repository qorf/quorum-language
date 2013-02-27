/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.vm.implementation.QuorumClassLoader;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CodeGenerator;

/**
 *
 * @author astefik
 */
public class QuorumWebExecutor {

    private HttpServer server;
    private QuorumVirtualMachine virtualMachine;
    private File pluginFolder;
    private static PrintStream defaultStandardOut = System.out;
    
    
    public QuorumWebExecutor() {
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkPermission(Permission perm) {
                // allow anything.
            }

            @Override
            public void checkPermission(Permission perm, Object context) {
                // allow anything.
            }

            @Override
            public void checkExit(int status) {
                super.checkExit(status);
                throw new ExitException(status);
            }
        });
    }

    protected static class ExitException extends SecurityException {

        public final int status;

        public ExitException(int status) {
            super("");
            this.status = status;
        }
    }

    /**
     * Starts an http server using the current jar location. If the jar location
     * is null, this method does nothing.
     *
     */
    public void start() {
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 1);
            server.createContext("/", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(QuorumServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendTestMessage(String code) {
        try {
            URL loginURL = new URL("http://localhost:8000");
            HttpURLConnection connection = (HttpURLConnection) loginURL.openConnection();
            String urlParameters = code;

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            //check the code and print out the result
            int result = connection.getResponseCode();
            String get = connection.getHeaderField("Content-length");
            int length = Integer.parseInt(get);
            byte[] codeByteArray = new byte[length];
            InputStream inputStream = connection.getInputStream();
            inputStream.read(codeByteArray);
            String toString = new String(codeByteArray);
            defaultStandardOut.print(toString);
            connection.disconnect();
            




            int a = 5;
        } catch (IOException ex) {
            Logger.getLogger(QuorumWebExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the virtualMachine
     */
    public QuorumVirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    /**
     * @param virtualMachine the virtualMachine to set
     */
    public void setVirtualMachine(QuorumVirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    /**
     * @return the pluginFolder
     */
    public File getPluginFolder() {
        return pluginFolder;
    }

    /**
     * @param pluginFolder the pluginFolder to set
     */
    public void setPluginFolder(File pluginFolder) {
        this.pluginFolder = pluginFolder;
    }

    private class MyHandler implements HttpHandler {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        @Override
        public void handle(HttpExchange t) throws IOException {
            OutputStream os = t.getResponseBody();
            stream = new ByteArrayOutputStream();

            String response = "";
            List<String> get = t.getRequestHeaders().get("Content-length");

            int length = 0;
            if (!get.isEmpty()) {
                String value = get.get(0);
                int parseInt = Integer.parseInt(value);
                length = parseInt;
            } else {
                return;
            }
            
            //TODO: if length is too large, send back an error message
            
            //TODO: If they try to access invalid (non-secure) code, send
            //back an error message

            byte[] codeByteArray = new byte[length];
            String code = null;
            try {
                t.getRequestBody().read(codeByteArray);
                String toString = new String(codeByteArray);
                code = toString;
            } catch (IOException e) {
                System.out.println("Could not get data from user.");
            }

            if (code != null) {
                CodeGenerator g = virtualMachine.getCodeGenerator();
                g.setCompileToDisk(false);
                virtualMachine.setGenerateCode(true);
                virtualMachine.build(code);
                virtualMachine.setMain(virtualMachine.getMain());
                // Load the main quorum class and invoke the static main(String[] args) method with no arguments.
                String mainClassName = g.getMainClassName();
                QuorumClassLoader classLoader = new QuorumClassLoader();
                classLoader.setCodeGenerator(g);
                classLoader.setPluginFolder(getPluginFolder());
                Class<?> quorumClass = null;
                System.setOut(new PrintStream(stream));
                try {
                    quorumClass = classLoader.loadClass(mainClassName.replaceAll("/", "."));
                    Method declaredMethod = quorumClass.getDeclaredMethod("main", new Class[]{String[].class});
                    String[] programArguments = {}; // TODO: In the future, this can be used to pass arguments to a program running in interpreted mode.
                    declaredMethod.invoke(null, (Object) programArguments);
                } catch (Exception e) {
                }
            }
            
            response = stream.toString();
            t.sendResponseHeaders(200, response.length());
            
            
            os.write(response.getBytes());
            stream.flush();
            stream.close();
            os.close();
        }
    }
}
