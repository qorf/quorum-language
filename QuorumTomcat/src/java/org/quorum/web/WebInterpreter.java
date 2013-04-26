/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quorum.Main;
import org.quorum.vm.implementation.QuorumClassLoader;
import org.quorum.vm.implementation.QuorumSecurityException;
import org.quorum.vm.implementation.QuorumSecurityMode;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CodeGenerator;
import org.quorum.vm.interfaces.CompilerErrorManager;

/**
 *
 * @author astefik
 */
public class WebInterpreter extends HttpServlet {

    /**
     * This is the virtual machine that compiles Quorum programs.
     */
    private static QuorumVirtualMachine vm;
    /**
     * This is the name given to the distribution that Quorum will output.
     */
    private static String name = "Default";
    /**
     * A state variable for controlling which command line argument is currently
     * being processed.
     */
    private static int argumentIndex = 0;
    /**
     * A command line flag telling the computer to interpret the source.
     */
    private static final String INTERPRET = "-interpret";
    /**
     * A flag telling the computer to compile down to java bytecode.
     */
    private static final String COMPILE = "-compile";
    /**
     * A flag telling the computer to output documentation.
     */
    private static final String DOCUMENT = "-document";
    /**
     * A flag telling the computer to output documentation and determine if
     * action examples compile.
     */
    private static final String DOCUMENT_AND_VERIFY = "-verify";
    /**
     * A flag telling the computer the name of the file to distribute. This flag
     * must be followed a string name, with no file extension.
     *
     * Example: -name Hello
     *
     * this would output Hello.jar.
     */
    private static final String NAME = "-name";
    /**
     * This flag allows the user to override the folder containing quorum
     * plugins.
     */
    private static final String PLUGINS = "-plugins";
    /**
     * This is the default relative path for the plugins directory relative to
     * Quorum.jar.
     */
    public static final String DEFAULT_PLUGIN_PATH = "libraries/plugins";
    /**
     * This is an absolute path indicating the location of the plugin directory.
     */
    private static File pluginFolder = null;
    /**
     * This flags tracks where the default plugin folder has been overriden.
     */
    private static boolean pluginOverride = false;
    /**
     * Adding in this flag causes all others to be ignored and help information
     * is output to the command line.
     */
    private static final String HELP = "-help";
    /**
     * If this flag is set, code will be interpreted rather than compiled to JVM
     * bytecode. This is off by default.
     */
    private static boolean isInterpret = false;
    /**
     * Whether or not to generate documentation instead of
     * compiling/interpreting. This is off by default.
     */
    private static boolean isDocumentation = false;
    /**
     * Whether or not to verify code examples in actions when generating
     * documentation. This is off by default.
     */
    private static boolean isVerifyDocumentation = false;
    /**
     * A folder for the distributions to be placed.
     */
    private static final String DISTRIBUTE_DIRECTORY = "Run";
    /**
     * A folder for build information, like classes and plugins.
     */
    private static final String BUILD_DIRECTORY = "Build";
    /**
     * A folder for documentation to be generated into.
     */
    private static final String DOCUMENTATION_DIRECTORY = "Documentation";
    /**
     * Adding this flag will start a web server and display a quorum web project
     * in the default browser.
     */
    private static final String WEB = "-web";
    /**
     * Adding this flag will start a web server and allow source code to be sent
     * in by POST requests.
     */
    private static final String SERVER = "-server";
    /**
     * This flag is for passing a new plugin to the compiler.
     */
    private static final String PLUGIN = "-plugin";
    /**
     * This flag indicates that a jar file should be added as a dependency.
     */
    private static final String JAR_DEPENDENCY = "-java";
    /**
     * If this flag is true, this compile had additional plugins.
     */
    private static boolean hasPlugins = false;
    /**
     * If this flag is true, this compile has additional jar files that need to
     * be woven into the final JAR file.
     */
    private static boolean hasJars = false;
    /**
     * This array list contains a list of all submitted plugins, in additional
     * the ones included by default by the compiler.
     */
    private static HashMap<String, String> plugins = new HashMap<String, String>();
    /**
     * This list includes all jar files requested by the user to be included in
     * the final dependency list.
     */
    private static HashMap<String, String> jars = new HashMap<String, String>();
    /**
     * This flag tells the Quorum compiler to update itself and then shuts down.
     */
    private static final String UPDATE = "-update";
    /**
     * If this flag is true, Quorum will start a process to update itself and
     * shut down.
     */
    private static boolean isUpdate = false;
    /**
     * Whether or not to start the web server instead of compiling/interpreting.
     * This is off by default.
     */
    private static boolean isWeb = false;
    /**
     * Whether or not to start quorum as a server for hosting code. This
     * parameter is off by default.
     */
    private static boolean isServer = false;
    /**
     * This flag sets the security mode for the -server flag.
     */
    private static QuorumSecurityMode security = QuorumSecurityMode.WEB;
    private static final String INSECURE = "insecure";
    /**
     * This file represents the folder where Quorum is located.
     */
    private static File root = null;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();



        try {

            root = new File("/Users/astefik/quorum/trunk/quorum/dist");
            if (root == null) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
                        "Could not load Virtual Machine, "
                        + "as Quorum's location is invalid.");
            }

            File index = new File(root.getAbsolutePath()
                    + "/libraries/indexes/quorum.index");

            pluginFolder = new File(root.getAbsolutePath() + "/"
                    + DEFAULT_PLUGIN_PATH);

            File library = new File(root.getAbsolutePath()
                    + "/libraries/quorum");

            // The "build" directory should be in the current directory.
            File build = new File("./" + BUILD_DIRECTORY);

            File distribution = new File("./" + DISTRIBUTE_DIRECTORY);

            File dependencies = new File(root.getAbsolutePath()
                    + "/lib");

            File phonemic = new File(dependencies.getAbsolutePath()
                    + "/phonemic.jar");

            File phonemicJNI = new File(dependencies.getAbsolutePath()
                    + "/jni");

            File documentation = new File(DOCUMENTATION_DIRECTORY + "/");

            if (!build.isDirectory()) {
                build.mkdir();
            }

            if (!documentation.isDirectory()) {
                documentation.mkdirs();
            }

            QuorumStandardLibrary.overrideStandardLibraryPath(library, index);
            vm = new QuorumVirtualMachine();
            
            
            
            
            StringBuffer buffer = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                int length = request.getContentLength();
                char[] codeByteArray = new char[length];
                reader.read(codeByteArray);
                line = new String(codeByteArray);
                String runcode = runcode(line);
                out.println("<html>"+runcode+"</html>");
            } catch (Exception e) { /*report an error*/ }
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String runcode(String code) {
        boolean built = false;
        boolean failure = false;
        String response = "";
        QuorumSecurityMode securityMode = QuorumSecurityMode.WEB;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        code = code.replaceAll(Matcher.quoteReplacement("\\\""), Matcher.quoteReplacement("\""));
        if (code != null) {
            try {
                CodeGenerator g = vm.getCodeGenerator();
                g.setCompileToDisk(false);
                vm.setGenerateCode(true);
                vm.clean(true);
                vm.build(code);
                CompilerErrorManager errors = vm.getCompilerErrors();
                if (!errors.isCompilationErrorFree()) {
                    built = false;
                    response = errors.getShortErrorList();
                } else {
                    built = true;
                    vm.setMain(vm.getMain());
                    // Load the main quorum class and invoke the static main(String[] args) method with no arguments.
                    String mainClassName = g.getMainClassName();
                    QuorumClassLoader classLoader = new QuorumClassLoader();
                    classLoader.setSecurityMode(securityMode);
                    classLoader.setCodeGenerator(g);
                  //  classLoader.setPluginFolder(getPluginFolder());
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
                if (ex.getCause() != null && ex.getCause() instanceof QuorumWebExecutor.ExitException) {
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

        response = status + "|" + response;
        return response;
    }
}
