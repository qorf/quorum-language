
package org.quorum.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class RunAndroid {
    
    String PATH_TO_LIBS = File.separator + "app" + File.separator + "libs";
    String ASSEMBLED_APK_FOR_RELEASE = File.separator + "app" + File.separator + "build" + File.separator + "outputs" + File.separator + "apk" + File.separator + "release" + File.separator + "app-release-unsigned.apk";
    
    String keyStorePath = "~/keystore.ks";
    String keyStorePassword = "";
    String keyAlias = "key0";
    String keyPassword = "";
    String androiSDKPath;
    
    String toolPath = File.separator +  "build-tools" + File.separator + "27.0.3" + File.separator;
    String zipalignPath = toolPath + "zipalign";
    String zipalignOptions = "-v -p 4";
    String apksignerPath = toolPath + "apksigner";
    
    String pathToBuildAndroidFolder = "./TestApplication";
    
    String[] librarySources; 
    String[] libraryDestinations;
    
    public static final String FOLDER_NAME = "Android";

    public RunAndroid(String pathToRunFolder) {
        this.androiSDKPath = getDefaultAndroidSDKPath();
        this.pathToBuildAndroidFolder = pathToRunFolder + File.separator + FOLDER_NAME;
        this.librarySources = new String[] {
            pathToRunFolder + File.separator + "Default.jar",
            pathToRunFolder + File.separator + "QuorumStandardLibrary.jar", 
            pathToRunFolder + File.separator + "QuorumStandardPlugins.jar"
        }; 
        this.libraryDestinations = new String[] {
            this.pathToBuildAndroidFolder + PATH_TO_LIBS + File.separator + "Default.jar", 
            this.pathToBuildAndroidFolder + PATH_TO_LIBS + File.separator + "QuorumStandardLibrary.jar", 
            this.pathToBuildAndroidFolder + PATH_TO_LIBS + File.separator + "QuorumStandardPlugins.jar"
        }; 
        if (isWindows()) {
            zipalignPath = zipalignPath + ".exe";
        }
    }
    
    public final String getDefaultAndroidSDKPath() {
        String defaultPath = System.getProperty("user.home");
        if (isWindows()) {
            defaultPath += File.separator + "AppData\\Local\\Android\\sdk"; 
        } else {
           defaultPath += File.separator + "Library/Android/sdk"; 
        }
        return defaultPath;
    }
    
    /*
    Builds project as android application using debug key
    Before running this, libraries have to be built
    */
    public void debugBuildAndInstall() throws IOException, InterruptedException {

        // copy libraries to project
        copyLibraries(librarySources, libraryDestinations);
        System.out.println("Done copying libraries");
        // assembleDebug android app
        assembleDebugCommand();
        System.out.println("Done assembling");
        // installDebug android app
        installDebugCommand();
        System.out.println("Done installing");
    }
    
    /*
    Builds project as android application using release key
    Before running this, libraries have to be built
    */
    public void releaseBuildAndSign() throws IOException, InterruptedException {
       // Building project
       assembleReleaseCommand();
       
       // Doing zipalign is necessary before signing
       zipalignCommand();
       
       // Sign application
       apkSignerCommand();
       
       apkSignerCommand();
    }
    
    public void zipalignCommand () throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + buildZipalignCommand() + "& exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else if(isMac()) {
            Process proc = Runtime.getRuntime().exec(buildZipalignCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(buildZipalignCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    public void apkSignerCommand () throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd start /c \"\" " + buildAPKSignerCommand() + "& exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else if(isMac()) {
            Process proc =  Runtime.getRuntime().exec(buildAPKSignerCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(buildAPKSignerCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    public void assembleReleaseCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + pathToBuildAndroidFolder + "\\gradlew.bat -p " + pathToBuildAndroidFolder + " assembleRelease & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else if(isMac()) {
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleRelease");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleRelease");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
        
    public void assembleDebugCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + pathToBuildAndroidFolder + "\\gradlew.bat -p " + pathToBuildAndroidFolder + " assembleDebug & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleDebug");
            Thread thread = new Thread(new ProcessWatcher(proc));
            
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleDebug");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    public Process GetAPKDebugBuildProcess() throws IOException, InterruptedException  {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + pathToBuildAndroidFolder + "\\gradlew.bat -p " + pathToBuildAndroidFolder + " assembleDebug & exit");
            return proc;
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleDebug");
            return proc;
        } else {
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleDebug");
            return proc;
        }
    }
    
    public void installDebugCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + pathToBuildAndroidFolder + "\\gradlew.bat -p " + pathToBuildAndroidFolder + " installDebug & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else if(isMac()) {   
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " assembleDebug");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(pathToBuildAndroidFolder + "/gradlew -p " + pathToBuildAndroidFolder + " installDebug ");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    private String buildAPKSignerCommand() {
        return androiSDKPath + apksignerPath + " sign --ks " + keyStorePath + " --ks-pass pass:" + keyStorePassword + " --ks-key-alias " + keyAlias + " --key-pass pass:" + keyPassword + " --out ." + File.separator + "Run" + File.separator + "ReleaseReady.apk" + " ." + File.separator + "Run" + File.separator + "ReleaseAssembled.apk" ;
    }
    
    private String buildZipalignCommand() {  
        return androiSDKPath + zipalignPath + " " + zipalignOptions + " " + pathToBuildAndroidFolder + ASSEMBLED_APK_FOR_RELEASE + " ." + File.separator + "Run" + File.separator + "ReleaseAssembled.apk";
                
    }
    
    public void copyLibraries(String[] sourcePaths, String[] destinationPaths) throws IOException {
        File libFolder = new File(this.pathToBuildAndroidFolder + PATH_TO_LIBS);
        if(!libFolder.exists()) {
            libFolder.mkdir();
        }
        
        for (int i = 0; i < sourcePaths.length; i++ ){
            copyLibrary(sourcePaths[i], destinationPaths[i]);
        }
    }
    
    public void copyLibrary(String sourcePath, String destinationPath) throws IOException {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);
        
        copy(source, destination);
    }
    
    
    private void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private void copyFile(File source, File target) throws IOException {
        Files.copy(source.toPath(), target.toPath(), COPY_ATTRIBUTES, REPLACE_EXISTING);
    }
     public String getKeyStorePath() {
        return keyStorePath;
    }
    
    public String getKeyStorePassword() {
        return keyStorePassword;
    }
    
    public String getKeyAlias() {
        return keyAlias;
    }
    
    public String getKeyPassword() {
        return keyPassword;
    }
    
    public String getAndroidPath() {
        return androiSDKPath;
    }
    
    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }
    
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }
    
    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }
    
    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }
    
    public void setAndroidSDKPath(String androidSDKPath) {
        this.androiSDKPath = androidSDKPath;
    }
    
    public void setProjectPath(String projectPath) {
        this.pathToBuildAndroidFolder = projectPath;
        
    }
    
    public String getProjectPath() {
        return this.pathToBuildAndroidFolder;
    }
    
    public void setLibrarySources(String[] librarySources) {
        this.librarySources = librarySources;
    }
    
    public void setLibraryDestinations(String[] libraryDestinations) {
        this.libraryDestinations = libraryDestinations;
    }
    
    public String[] getLibrarySources() {
        return this.librarySources;
    }
    
    public String[] getLibraryDestinations() {
        return this.libraryDestinations;
    }
    
    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isMac() {
        return getOsName().startsWith("Mac");
    }

    private static String OS = null;

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }
    
    static class ProcessWatcher implements Runnable {

        private final Process proc;

        public ProcessWatcher(Process proc) {
            this.proc = proc;
        }

        public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader inputE = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String line = null;
            try {
                while ((line = inputE.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while ((line = input.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
