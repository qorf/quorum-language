
package org.quorum.android;

import static org.quorum.android.AndroidSetup.isWindows;
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
    String keyStorePassword = "test";
    String keyAlias = "key0";
    String keyPassword = "test2";
    String androidPath;
    
    String toolPath = File.separator +  "build-tools" + File.separator + "27.0.3" + File.separator;
    String zipalignPath = toolPath + "zipalign";
    String zipalignOptions = "-v -p 4";
    String apksignerPath = toolPath + "apksigner";
    
    String projectPath = "./TestApplication";
    
    String[] librarySources; 
    String[] libraryDestinations;

    
    public RunAndroid() { 
        this.androidPath = getDefaultAndroidPath();
        this.librarySources = new String[] {"temp" + File.separator + "Default.jar",
            "temp" + File.separator + "QuorumStandardLibrary.jar", 
            "temp" + File.separator + "QuorumStandardPlugins.jar"}; 
        this.libraryDestinations = new String[] {projectPath + PATH_TO_LIBS + File.separator + "Default.jar", 
            projectPath + PATH_TO_LIBS + File.separator + "QuorumStandardLibrary.jar", 
            projectPath + PATH_TO_LIBS + File.separator + "QuorumStandardPlugins.jar"}; 
        if (isWindows()) 
            zipalignPath = zipalignPath + ".exe";
    }
    
    public RunAndroid(String projectPath) {
        this.androidPath = getDefaultAndroidPath();
        this.projectPath = projectPath;
        this.librarySources = new String[] {"temp" + File.separator + "Default.jar",
            "temp" + File.separator + "QuorumStandardLibrary.jar", 
            "temp" + File.separator + "QuorumStandardPlugins.jar"}; 
        this.libraryDestinations = new String[] {projectPath + PATH_TO_LIBS + File.separator + "Default.jar", 
            projectPath + PATH_TO_LIBS + File.separator + "QuorumStandardLibrary.jar", 
            projectPath + PATH_TO_LIBS + File.separator + "QuorumStandardPlugins.jar"}; 
        if (isWindows()) 
            zipalignPath = zipalignPath + ".exe";
    }
    
    public final String getDefaultAndroidPath() {
        String defaultPath = System.getProperty("user.home");
        System.out.println(defaultPath);
        if (isWindows()) {
            defaultPath += File.separator + "AppData\\Local\\Android\\sdk"; 
        } else {
           defaultPath += File.separator + "Library/Android/sdk"; 
        }
        return defaultPath;
    }
    
    public void CopyAndRun() throws IOException, InterruptedException {
        //paths are coming from somewhere else probably
        
        
        // build libraries first
        
        // copy libraries to project
        CopyLibraries(librarySources, libraryDestinations);
        System.out.println("Done copying libraries.");
        
        // assembleDebug android app
        AssembleDebugCommand();
        
        // installDebug android app
        InstallDebugCommand();
    }
    
    public void BuildAndSign() throws IOException, InterruptedException {
       AssembleReleaseCommand();
       
       System.out.println("Done Assembling");
       ZipalignCommand();
       System.out.println("Done Aligning");
       ApkSignerCommand();
    }
    
    public void ZipalignCommand () throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + buildZipalignCommand() + "& exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(buildZipalignCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    public void ApkSignerCommand () throws IOException, InterruptedException {
        if (isWindows()) {
            System.out.println(buildAPKSignerCommand());
            Process proc = Runtime.getRuntime().exec("cmd start /c \"\" " + buildAPKSignerCommand() + "& exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(buildAPKSignerCommand());
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    private String buildAPKSignerCommand() {
        return androidPath + apksignerPath + " sign --ks " + keyStorePath + " --ks-pass pass:" + keyStorePassword + " --ks-key-alias " + keyAlias + " --key-pass pass:" + keyPassword + " --out ." + File.separator + "Run" + File.separator + "ReleaseReady.apk" + " ." + File.separator + "Run" + File.separator + "ReleaseAssembled.apk" ;
    }
    
    private String buildZipalignCommand() {  
        return androidPath + zipalignPath + " " + zipalignOptions + " " + projectPath + ASSEMBLED_APK_FOR_RELEASE + " ." + File.separator + "Run" + File.separator + "ReleaseAssembled.apk";
                
    }
    
    public void AssembleReleaseCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + projectPath + "\\gradlew.bat -p " + projectPath + " assembleRelease & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(projectPath + "/gradlew -p " + projectPath + " assembleRelease");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    
    public void AssembleDebugCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + projectPath + "\\gradlew.bat -p " + projectPath + " assembleDebug & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(projectPath + "/gradlew -p " + projectPath + " assembleDebug");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    public void InstallDebugCommand( ) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /c \"\" " + projectPath + "\\gradlew.bat -p " + projectPath + " installDebug & exit");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        } else {
            Process proc =  Runtime.getRuntime().exec(projectPath + "/gradlew -p " + projectPath + " installDebug ");
            new Thread(new ProcessWatcher(proc)).start();
            proc.waitFor();
        }
    }
    
    
    public void CopyLibraries(String[] sourcePaths, String[] destinationPaths) throws IOException {
        for (int i = 0; i < sourcePaths.length; i++ ){
            System.out.println("Source: " + sourcePaths[i]);
            System.out.println("Destinations: " + destinationPaths[i]);
            
            CopyLibrary(sourcePaths[i], destinationPaths[i]);
        }
    }
    
    public void CopyLibrary(String sourcePath, String destinationPath) throws IOException {
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
        return androidPath;
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
    
    public void setAndroidPath(String androidPath) {
        this.androidPath = androidPath;
    }
    
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        
    }
    
    public String getProjectPath() {
        return this.projectPath;
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
