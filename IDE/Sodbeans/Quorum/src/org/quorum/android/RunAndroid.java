
package org.quorum.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;


public class RunAndroid {
    
    String PATH_TO_LIBS = File.separator + "app" + File.separator + "libs";
    String ASSEMBLED_APK_FOR_RELEASE = File.separator + "app" + File.separator + "build" + File.separator + "outputs" + File.separator + "apk" + File.separator + "release" + File.separator + "app-release-unsigned.apk";
    
    String keyStorePath = "";
    String keyStorePassword = "";
    String keyAlias = "key0";
    String keyPassword = "";
    String androidSDKPath;
    
    String toolPath = File.separator +  "build-tools" + File.separator + "27.0.3" + File.separator;
    String zipalignPath = toolPath + "zipalign";
    String zipalignOptions = "-v -p 4";
    String apksignerPath = toolPath + "apksigner";
    String pathToRunFolder = "";
    
    String pathToBuildAndroidFolder = "./TestApplication";
    
    String[] librarySources; 
    String[] libraryDestinations;
    
    public static final String FOLDER_NAME = "Android";

    public RunAndroid(String pathToRunFolder, String jarName) {
        this.androidSDKPath = getDefaultAndroidSDKPath();
        this.pathToRunFolder = pathToRunFolder;
        this.pathToBuildAndroidFolder = pathToRunFolder + File.separator + FOLDER_NAME;
        this.librarySources = new String[] {
            pathToRunFolder + File.separator + jarName,
            pathToRunFolder + File.separator + "QuorumStandardLibrary.jar", 
            pathToRunFolder + File.separator + "QuorumStandardPlugins.jar"
        }; 
        this.libraryDestinations = new String[] {
            this.pathToBuildAndroidFolder + PATH_TO_LIBS + File.separator + jarName, 
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
    
    
    public boolean hasKeystoreInfo() {
        if (keyStorePath == null || keyStorePath.equals("") || keyStorePassword == null || keyStorePassword.equals("") || keyPassword == null || keyPassword.equals("") || keyAlias == null) {
            return false;
        }
        return true;
    }
       

    public Process GetZipalignProcess() throws IOException, InterruptedException  {
        if (isWindows()) {
            String[] list = buildZipalignCommandWindows();

            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);

            
            return pb.start();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(androidSDKPath + zipalignPath);
            if(file.exists()) {
                file.setExecutable(true);
            }
            ProcessBuilder pb = new ProcessBuilder(buildZipalignCommand());
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else {
            ProcessBuilder pb = new ProcessBuilder(buildZipalignCommand());
            pb.redirectErrorStream(true);
            
            return pb.start();
        }
    }
      
    public Process GetAPKSignerProcess() throws IOException, InterruptedException  {
        if (isWindows()) {
            String[] s = buildAPKSignerCommandWindows();

            ProcessBuilder pb = new ProcessBuilder(buildAPKSignerCommandWindows());
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(androidSDKPath + apksignerPath);
            if(file.exists()) {
                file.setExecutable(true);
            }
            ProcessBuilder pb = new ProcessBuilder(buildAPKSignerCommand());
            pb.redirectErrorStream(true);
            
            return pb.start(); 
        } else {
            ProcessBuilder pb = new ProcessBuilder(buildAPKSignerCommand());
            pb.redirectErrorStream(true);
            
            return pb.start(); 
        }
    }
    
    
        public Process GetCreateKeystoreProcess(String keystorePath, String keystorePassword, String keystoreAlias, String keyAliasPassword) throws IOException, InterruptedException  {
        if (isWindows()) {
            
            String[] list = {"cmd", "/c", "\"\"", "keytool", "-genkey", "-v", "-keystore", keystorePath, "-storepass", keystorePassword, "-keyalg", "RSA", "-keysize", "2048", "-validity", "10000", "-alias", keystoreAlias, "-keypasswd", keyAliasPassword, "&", "exit"};
            for (String string : list) {
                System.out.print(string+" ");
            }
            System.out.println();
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            String[] list = {pathToBuildAndroidFolder + "/gradlew",  "-p", pathToBuildAndroidFolder, "assembleRelease"};
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else {
            String[] list = {pathToBuildAndroidFolder + "/gradlew",  "-p", pathToBuildAndroidFolder, "assembleRelease"};
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        }
    }
    
    public Process GetAssembleReleaseProcess() throws IOException, InterruptedException  {
        if (isWindows()) {
            String[] list = {"cmd", "/c", "\"\"", pathToBuildAndroidFolder + "\\gradlew.bat",  "-p", pathToBuildAndroidFolder, "assembleRelease", "&", "exit"};

            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            String[] list = {pathToBuildAndroidFolder + "/gradlew",  "-p", pathToBuildAndroidFolder, "assembleRelease"};
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else {
            String[] list = {pathToBuildAndroidFolder + "/gradlew",  "-p", pathToBuildAndroidFolder, "assembleRelease"};
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        }
    }

    public Process GetAPKDebugBuildProcess() throws IOException, InterruptedException  {
        if (isWindows()) {
            
            String[] list = {"cmd", "/c", "\"\"", pathToBuildAndroidFolder+"\\gradlew.bat", "-p", pathToBuildAndroidFolder, "assembleDebug", "&" ,"exit" };
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            
            String[] list = {pathToBuildAndroidFolder+"/gradlew", "-p", pathToBuildAndroidFolder, "assembleDebug"};
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else {
                        
            String[] list = {pathToBuildAndroidFolder+"/gradlew", "-p", pathToBuildAndroidFolder, "assembleDebug"};
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        }
    }
    
    public Process GetDebugInstallProcess( ) throws IOException, InterruptedException {
        if (isWindows()) {
            String[] list = {"cmd", "/c", "\"\"", pathToBuildAndroidFolder+"\\gradlew.bat", "-p", pathToBuildAndroidFolder, "installDebug", "&" ,"exit" };
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else if(isMac()) {   
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(pathToBuildAndroidFolder + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            String[] list = {pathToBuildAndroidFolder+"/gradlew", "-p", pathToBuildAndroidFolder, "installDebug"};
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        } else {
            String[] list = {pathToBuildAndroidFolder+"/gradlew", "-p", pathToBuildAndroidFolder, "installDebug"};
            
            ProcessBuilder pb = new ProcessBuilder(list);
            pb.redirectErrorStream(true);
            
            return pb.start();
        }
    }
    
    private String[] buildAPKSignerCommand() {
        return new String[]{androidSDKPath + apksignerPath,  "sign",  "--ks", keyStorePath, "--ks-pass", "pass:" + keyStorePassword, "--ks-key-alias" , keyAlias, "--key-pass",  "pass:" + keyPassword, "--out", pathToRunFolder + File.separator + "ReleaseReady.apk", pathToRunFolder + File.separator + "Assembled.apk"};
    }
    
    private String[] buildAPKSignerCommandWindows() {
        return new String[]{"cmd", "/c", "\"\"", androidSDKPath + apksignerPath,  "sign",  "--ks", keyStorePath, "--ks-pass", "pass:" + keyStorePassword, "--ks-key-alias" , keyAlias, "--key-pass",  "pass:" + keyPassword, "--out", pathToRunFolder + File.separator + "ReleaseReady.apk", pathToRunFolder + File.separator + "Assembled.apk", "&" ,"exit"};
    }
    
    private String[] buildZipalignCommand() {  
        return new String[]{androidSDKPath + zipalignPath, "-v", "-p", "4", pathToBuildAndroidFolder + ASSEMBLED_APK_FOR_RELEASE, pathToRunFolder + File.separator + "Assembled.apk"};
                
    }
    
    private String[] buildZipalignCommandWindows() {  
        return new String[]{"cmd", "/c", "\"\"", androidSDKPath + zipalignPath, "-v", "-p", "4", pathToBuildAndroidFolder + ASSEMBLED_APK_FOR_RELEASE, pathToRunFolder + File.separator + "Assembled.apk", "&" ,"exit"};
                
    }
    
    public void copyAssets(File mediaFolder, String value) throws IOException {        
        File assetsFolder = new File(this.pathToBuildAndroidFolder + File.separator + "app"+ File.separator + "src" + File.separator + "main" + File.separator+ "assets" + File.separator + value);
        if(!mediaFolder.exists()) {
            mediaFolder.mkdirs();
        }
        
        if (mediaFolder.exists()) {
            copyDirectory(mediaFolder, assetsFolder);
        } else {
            throw new IOException("Resources folder could not be found!");
        }
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
        return androidSDKPath;
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
        this.androidSDKPath = androidSDKPath;
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
