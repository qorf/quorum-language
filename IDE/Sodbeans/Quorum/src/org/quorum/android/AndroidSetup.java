package org.quorum.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.quorum.android.RunAndroid.isWindows;

public class AndroidSetup {

    public static final String PATH_TO_MAIN = File.separator + "app" + File.separator + "src" + File.separator + "main" + File.separator;
    public static final String PATH_TO_MAIN_XML = PATH_TO_MAIN + "AndroidManifest.xml";

    public static final String PATH_TO_LAYOUT = PATH_TO_MAIN + "res" + File.separator + "layout" + File.separator;
    public static final String PATH_TO_LAYOUT_XML = PATH_TO_LAYOUT + "activity_main.xml";

    public static final String PATH_TO_PACKAGE = PATH_TO_MAIN + "java" + File.separator;
    public static final String PACKAGES_FOLDER = "packagesfolder";
    
    public static final String FOLDER_NAME = "Android";
    
    String androidSDKPath;
    
    public AndroidSetup() {
        this.androidSDKPath = getDefaultAndroidSDKPath();
    }

    public void copyAndRename(String templateLocation, String pathToRunFolder, String applicationName, String jdkPath) throws FileNotFoundException, IOException, InterruptedException {
        String pathToBuildAndroidFolder = pathToRunFolder + File.separator + FOLDER_NAME;
        // Copy project into new folder with appropriate name
        copyFolder(pathToBuildAndroidFolder, templateLocation);
        
        // Set SDK location
        setSDKLocation(pathToBuildAndroidFolder);
        setNDKLocation(pathToBuildAndroidFolder);
        
        if (jdkPath != null && !jdkPath.equals("")){
            setJDKLocation(pathToBuildAndroidFolder, jdkPath);
        }
        
        // Change Application Name
        changeApplicationName(pathToBuildAndroidFolder, applicationName);
    
        // Clean the generated files that might conflict with the renamings
        cleanGeneratedFiles(pathToBuildAndroidFolder);
    }
    
    
    private void setSDKLocation(String pathToBuildAndroidFolder) throws IOException {
        String pathToLocalProperties = pathToBuildAndroidFolder + File.separator + "local.properties";
        String sdkLocation = this.androidSDKPath;
        if(isWindows()) {
            sdkLocation = "";
            for (int i = 0; i < this.androidSDKPath.length(); i++) {
                sdkLocation += this.androidSDKPath.charAt(i);
                if (this.androidSDKPath.charAt(i) =='\\'){
                    sdkLocation += "\\\\\\";
                }
            }
        }
        replaceLineText(pathToLocalProperties, "sdk.dir=", "sdk.dir="+sdkLocation);
    }
    
    private void setNDKLocation(String pathToBuildAndroidFolder) throws IOException {
        String pathToLocalProperties = pathToBuildAndroidFolder + File.separator + "local.properties";
        String ndkLocation = this.androidSDKPath + File.separator + "ndk-bundle";
        String sdkLocation = ndkLocation;
        if(isWindows()) {
            sdkLocation = "";
            for (int i = 0; i < ndkLocation.length(); i++) {
                sdkLocation += ndkLocation.charAt(i);
                if (ndkLocation.charAt(i) =='\\'){
                    sdkLocation += "\\\\\\";
                }
            }
        }
        appendLine(pathToLocalProperties, "ndk.dir="+sdkLocation);
    }
    
    public void copyFolder(String projectPath, String templatePosition) throws IOException {
        
        File srcDir = new File(templatePosition);
        
        File destDir = new File(projectPath);

        if (destDir.exists()) {
            deleteFolder(projectPath);
        }
        
        copy(srcDir, destDir);
    }
    
     public void changeApplicationName(String projectPath, String applicationName) throws FileNotFoundException, IOException {
        String pathToMain = projectPath + PATH_TO_MAIN_XML;
        replaceLineText(pathToMain, "android:label=\"Android\"", "android:label=\""+applicationName+"\"");
    }

    public void cleanGeneratedFiles(String projectPath) throws IOException, InterruptedException {
        if (isWindows()) {
            Process proc = Runtime.getRuntime().exec("cmd /C \"\" " + projectPath + "\\gradlew.bat -p " + projectPath + " clean & exit");
            
            proc.waitFor();
            proc.destroy();
        } else if(isMac()) {
            //mac JDK's typically remove executable properties after a copy. Restore them.
            File file = new File(projectPath + "/gradlew");
            if(file.exists()) {
                file.setExecutable(true);
            }
            Process proc = Runtime.getRuntime().exec(projectPath + "/gradlew -p " + projectPath + " clean");
            proc.waitFor();
            proc.destroy();
        } else {
            Process proc = Runtime.getRuntime().exec(projectPath + "/gradlew -p " + projectPath + " clean");
            proc.waitFor();
            proc.destroy();
        }
    }
    
    public final String getDefaultAndroidSDKPath() {
        String defaultPath = System.getProperty("user.home");
        if (isWindows()) {
            defaultPath += File.separator + "AppData" + File.separator + "Local" +File.separator + "Android"+ File.separator +"sdk"; 
            System.out.println("defaultPath: " + defaultPath);
        } else {
           defaultPath += File.separator + "Library/Android/sdk"; 
        }
        return defaultPath;
    }

    public void changePackageName(String projectPath, String packageName) throws IOException {
        String packageFolderNames = convertPackageName(packageName);
        File packagesRoot = new File(projectPath + PATH_TO_PACKAGE + PACKAGES_FOLDER);

        File newPackages = new File(projectPath + PATH_TO_PACKAGE + packageFolderNames);
        newPackages.mkdirs();

        copy(packagesRoot, newPackages);
        deleteFolder(packagesRoot.getCanonicalPath());
    }

    public void changePackageNameInFiles(String packageName, String projectPath) throws FileNotFoundException, IOException {

        String pathToGradle = projectPath + File.separator + "app" + File.separator + "build.gradle";
        replaceLineText(pathToGradle, PACKAGES_FOLDER, packageName);

        String pathToJavaFile = projectPath + PATH_TO_PACKAGE + PACKAGES_FOLDER + File.separator + "MainActivity.java";
        replaceLineText(pathToJavaFile, PACKAGES_FOLDER, packageName);

        String pathToMain = projectPath + PATH_TO_MAIN_XML;
        replaceLineText(pathToMain, PACKAGES_FOLDER, packageName);

        String pathToLayout = projectPath + PATH_TO_LAYOUT_XML;
        replaceLineText(pathToLayout, PACKAGES_FOLDER, packageName);
    }
   
    public void changeSdkVersions(String sdkVersion, String minSdkVersion, String projectPath) throws IOException {
        String pathToGradle = projectPath + File.separator + "app" + File.separator + "build.gradle";

        File file = new File(pathToGradle);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            checkAndChangeLine(fileContent, i, "compileSdkVersion", sdkVersion);
            checkAndChangeLine(fileContent, i, "targetSdkVersion", sdkVersion);
            checkAndChangeLine(fileContent, i, "minSdkVersion", minSdkVersion);
        }

        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
    }
    
    // Helper methods ##########################################################
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

    private void checkAndChangeLine(List<String> fileContent, int index, String indicator, String replacement){
        if (fileContent.get(index).contains(indicator)) {
                int position = fileContent.get(index).indexOf(indicator) + indicator.length() + 1;
                String newLine = fileContent.get(index).substring(0, position) + replacement;
                fileContent.set(index, newLine);
            }
    }
    
    private void replaceLineText(String filePath, String toReplace, String newString) throws IOException {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(toReplace)) {
                
                fileContent.set(i, newString);
            }
        }

        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
    }
    
    private void appendLine(String filePath, String toAdd) throws IOException {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        fileContent.add(toAdd);

        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
    }

    private String convertPackageName(String packageName) {
        String result = packageName.replaceAll("\\.", File.separator);
        return result;
    }

    private List<File> getDirs(File parent, int level) {
        List<File> dirs = new ArrayList<File>();
        for (File f : parent.listFiles()) {
            if (f.isDirectory()) {
                if (level == 0) {
                    dirs.add(f);
                } else {
                    dirs.addAll(getDirs(f, level - 1));
                }
            }
        }
        return dirs;
    }

    private void copy(File sourceLocation, File targetLocation) throws IOException {
        
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (target.exists()) {
            deleteFolder(target);
        }
        
        if (!target.exists()) {
            target.mkdir();
        }

        
        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private void copyFile(File source, File target) throws IOException {
        if (target.exists()) {
            delete(target);
        }
        Files.copy(source.toPath(), target.toPath(), COPY_ATTRIBUTES);
    }

    private void deleteFolder(String projectPath) {
        File f = new File(projectPath);

        deleteFolder(f);
    }
    
    private void deleteFolder(File f) {
        try {
            delete(f);
        } catch (IOException ex) {
            Logger.getLogger(AndroidSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }

        } else {
            //if file, then delete it
            file.delete();
        }
    }

    private void setJDKLocation(String pathToBuildAndroidFolder, String jdkPath) throws IOException {
        String pathToLocalProperties = pathToBuildAndroidFolder + File.separator + "gradle.properties";
        String sdkLocation = jdkPath;
        if(isWindows()) {
            sdkLocation = "";
            for (int i = 0; i < jdkPath.length(); i++) {
                sdkLocation += jdkPath.charAt(i);
                if (jdkPath.charAt(i) =='\\'){
                    sdkLocation += "\\\\";
                }
            }
        }
        appendLine(pathToLocalProperties, "org.gradle.java.home="+sdkLocation);
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
     
    public void setAndroidSDKPath(String androidPath) {
        this.androidSDKPath = androidPath;
    }

}
