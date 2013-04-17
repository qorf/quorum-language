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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import org.quorum.vm.implementation.QuorumClassLoader;
import org.quorum.vm.implementation.QuorumSecurityException;
import org.quorum.vm.implementation.QuorumSecurityMode;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CodeGenerator;
import org.quorum.vm.interfaces.CompilerErrorManager;

/**
 *
 * @author astefik
 */
public class QuorumWebExecutor {

    private HttpServer server;
    private MyHandler handler = new MyHandler();
    private QuorumVirtualMachine virtualMachine;
    private File pluginFolder;
    private static PrintStream defaultStandardOut = System.out;
    private QuorumSecurityMode securityMode = QuorumSecurityMode.WEB;

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

    /**
     * @return the securityMode
     */
    public QuorumSecurityMode getSecurityMode() {
        return securityMode;
    }

    /**
     * @param securityMode the securityMode to set
     */
    public void setSecurityMode(QuorumSecurityMode securityMode) {
        this.securityMode = securityMode;
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
            server.createContext("/", handler);
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(QuorumServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendTestMessage(String code) {
        try {
            String urlParameters = code;

            URL loginURL = new URL("http://localhost:8000");
            HttpURLConnection connection = (HttpURLConnection) loginURL.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);

            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

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

    private class ServerResponder implements Runnable {

        HttpExchange httpExchange = null;

        @Override
        public void run() {
            try {
                respond(httpExchange);
            } catch (IOException ex) {
                Logger.getLogger(QuorumWebExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private synchronized void respond(HttpExchange t) throws IOException {
            OutputStream os = t.getResponseBody();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            String response = "";
            try {
                List<String> get = t.getRequestHeaders().get("Content-length");
                int length = 0;
                if (!get.isEmpty()) {
                    String value = get.get(0);
                    int parseInt = Integer.parseInt(value);
                    length = parseInt;
                } else {
                    return;
                }

                byte[] codeByteArray = new byte[length];
                String code = null;
                try {
                    t.getRequestBody().read(codeByteArray);
                    String toString = new String(codeByteArray);
                    code = toString;
                } catch (IOException e) {
                    System.out.println("Could not get data from user.");
                }

                boolean built = false;
                boolean failure = false;
                if (code != null) {
                    try {
                        CodeGenerator g = virtualMachine.getCodeGenerator();
                        g.setCompileToDisk(false);
                        virtualMachine.setGenerateCode(true);
                        virtualMachine.clean(true);
                        virtualMachine.build(code);
                        CompilerErrorManager errors = virtualMachine.getCompilerErrors();
                        if (!errors.isCompilationErrorFree()) {
                            built = false;
                            response = errors.getShortErrorList();
                        } else {
                            built = true;
                            virtualMachine.setMain(virtualMachine.getMain());
                            // Load the main quorum class and invoke the static main(String[] args) method with no arguments.
                            String mainClassName = g.getMainClassName();
                            QuorumClassLoader classLoader = new QuorumClassLoader();
                            classLoader.setSecurityMode(securityMode);
                            classLoader.setCodeGenerator(g);
                            classLoader.setPluginFolder(getPluginFolder());
                            Class<?> quorumClass = null;
                            System.setOut(new PrintStream(stream));

                            quorumClass = classLoader.loadClass(mainClassName.replaceAll("/", "."));
                            Method declaredMethod = quorumClass.getDeclaredMethod("main", new Class[]{String[].class});
                            String[] programArguments = {}; // TODO: In the future, this can be used to pass arguments to a program running in interpreted mode.
                            declaredMethod.invoke(null, (Object) programArguments);
                        }
                    } catch (IllegalAccessException ex) {
//                    ex.printStackTrace();
                    } catch (IllegalArgumentException ex) {
//                    ex.printStackTrace();
                    } catch (NoSuchMethodException ex) {
//                    ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        if (ex.getCause() != null && ex.getCause() instanceof ExitException) {
                            failure = false; // a normal exit code was received.
                        } else if (ex.getCause() instanceof java.lang.NoClassDefFoundError) {
                            if (ex.getCause().getCause() != null && ex.getCause().getCause() instanceof QuorumSecurityException) {
                                QuorumSecurityException qex = (QuorumSecurityException) ex.getCause().getCause();
                                response = "Error: Class " + qex.getClassName() + " cannot be used"
                                        + " from the web version of Quorum.";
                                failure = true;
                            }
                        }
                    }
                }

                String status = "success";
                if (built && !failure) {
                    response = stream.toString(); //.replaceAll("\"", Matcher.quoteReplacement("\\\""));
                } else {
                    status = "fail";
                }
               // response = "{\n\t "
               //         + "\"status\": \"" + status + "\",\n\t"
               //         + "\"data\": \"" + response + "\"\n}";

                response  = status + "|" + response;
                
                t.sendResponseHeaders(200, response.length());
                os.write(response.getBytes());
                stream.flush();
                stream.close();
                os.close();
                System.gc();
            } catch (java.lang.OutOfMemoryError ex) {
                System.gc();
                return;
            } catch (Exception ex) {
                System.gc();
                return;
            }
        }
    }

    private class MyHandler implements HttpHandler {

        private static final int timeout = 2000;

        @Override
        public synchronized void handle(final HttpExchange t) throws IOException {
            try {
                ServerResponder responder = new ServerResponder();
                responder.httpExchange = t;
                Thread thread = new Thread(responder, "Quorum Code Runner");
                //thread.setDaemon(true);
                execute(thread, timeout);
            } catch (TimeoutException ex) {
                System.gc();
                OutputStream os = t.getResponseBody();
                String response = "{\n\t "
                        + "\"status\": \"" + "fail" + "\",\n\t"
                        + "\"data\": \"" + "Request timed out" + "\"\n}";
                t.sendResponseHeaders(200, response.length());
                os.write(response.getBytes());
                os.close();
            }
            System.gc();
        }

        public synchronized void execute(Thread task, long timeout) throws TimeoutException {
            task.start();
            try {
                task.join(timeout);
            } catch (InterruptedException e) {
            }
            if (task.isAlive()) {
                task.interrupt();
                throw new TimeoutException();
            }
        }
    }
}
