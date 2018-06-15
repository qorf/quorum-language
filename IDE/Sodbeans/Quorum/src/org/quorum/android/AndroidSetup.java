package org.quorum.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AndroidSetup {

    public static String PATH_TO_MAIN = File.separator + "app" + File.separator + "src" + File.separator + "main" + File.separator;
    public static String PATH_TO_MAIN_XML = PATH_TO_MAIN + "AndroidManifest.xml";

    public static String PATH_TO_LAYOUT = PATH_TO_MAIN + "res" + File.separator + "layout" + File.separator;
    public static String PATH_TO_LAYOUT_XML = PATH_TO_LAYOUT + "activity_main.xml";

    public static String PATH_TO_PACKAGE = PATH_TO_MAIN + "java" + File.separator;
    private static final String PACKAGES_FOLDER = "packagesfolder";
    public static final String FOLDER_NAME = "Android";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String projectPath = "." + File.separator + "TestApplication";
            String applicationName = "TADA";
        //    new AndroidSetup().CopyAndRename(projectPath, applicationName);
            System.out.println("Finished setting up project!");
            new RunAndroid(projectPath).BuildAndSign();
            System.out.println("Finished ");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AndroidSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AndroidSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AndroidSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CopyAndRename(String templateLocation, String projectPath, String applicationName) throws FileNotFoundException, IOException, InterruptedException {
        // Information that has to be configured
        String templatePosition = templateLocation;
      //  String packageName = "com.test.cs.TestApplication";
     //   String sdkVersion = "26";
     //   String minSdkVersion = "16";

        // Copy project into new folder with appropriate name
        CopyFolder(projectPath, templatePosition);

        // Change the used package name inside the different files that refer to it
      //  ChangePackageNameInFiles(packageName, projectPath);
        
        // Change the version information inside the build configuration
    //    ChangeSdkVersions(sdkVersion, minSdkVersion, projectPath);
        
        // Change Application Name
        ChangeApplicationName(applicationName, projectPath);
    
        // Change the package name inside the folder structure
       // ChangePackageName(projectPath, packageName);
        
        // Clean the generated files that might conflict with the renamings
        CleanGeneratedFiles(projectPath);

  
        //DeleteFolder(projectPath);
    }
    
    public void ChangeSdkVersions(String sdkVersion, String minSdkVersion, String projectPath) throws IOException {
        String pathToGradle = projectPath + File.separator + "app" + File.separator + "build.gradle";
        
        
        File file = new File(pathToGradle);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            CheckAndChangeLine(fileContent, i, "compileSdkVersion", sdkVersion);
            CheckAndChangeLine(fileContent, i, "targetSdkVersion", sdkVersion);
            CheckAndChangeLine(fileContent, i, "minSdkVersion", minSdkVersion);
            //String buildToolVersion = "\"" +sdkVersion + ".0.0\"";
            //CheckAndChangeLine(fileContent, i, "buildToolsVersion", buildToolVersion);
        }

        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
    }
    
    private void CheckAndChangeLine(List<String> fileContent, int index, String indicator, String replacement){
        if (fileContent.get(index).contains(indicator)) {
                int position = fileContent.get(index).indexOf(indicator) + indicator.length() + 1;
                String newLine = fileContent.get(index).substring(0, position) + replacement;
                fileContent.set(index, newLine);
            }
    }

    public void CleanGeneratedFiles(String projectPath) throws IOException, InterruptedException {
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

    public void CopyFolder(String projectPath, String templatePosition) throws IOException {
        
        File srcDir = new File(templatePosition);
        
        File destDir = new File(projectPath);

        if (destDir.exists()) {
            DeleteFolder(projectPath);
        }
        
        copy(srcDir, destDir);
    }

    public void ChangePackageName(String projectPath, String packageName) throws IOException {
        String packageFolderNames = ConvertPackageName(packageName);
        File packagesRoot = new File(projectPath + PATH_TO_PACKAGE + PACKAGES_FOLDER);

        File newPackages = new File(projectPath + PATH_TO_PACKAGE + packageFolderNames);
        newPackages.mkdirs();

        copy(packagesRoot, newPackages);
        DeleteFolder(packagesRoot.getCanonicalPath());
    }

    public void ChangeApplicationName(String applicationName, String projectPath) throws FileNotFoundException, IOException {

        String pathToMain = projectPath + PATH_TO_MAIN_XML;
        ReplaceLineText(pathToMain, "android:label=\"Moep\"", "android:label=\""+applicationName+"\"");

    }
    
    public void ChangePackageNameInFiles(String packageName, String projectPath) throws FileNotFoundException, IOException {

        String pathToGradle = projectPath + File.separator + "app" + File.separator + "build.gradle";
        ReplaceLineText(pathToGradle, PACKAGES_FOLDER, packageName);

        String pathToJavaFile = projectPath + PATH_TO_PACKAGE + PACKAGES_FOLDER + File.separator + "MainActivity.java";
        ReplaceLineText(pathToJavaFile, PACKAGES_FOLDER, packageName);

        String pathToMain = projectPath + PATH_TO_MAIN_XML;
        ReplaceLineText(pathToMain, PACKAGES_FOLDER, packageName);

        String pathToLayout = projectPath + PATH_TO_LAYOUT_XML;
        ReplaceLineText(pathToLayout, PACKAGES_FOLDER, packageName);
    }
   
    /// ########################################################################
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

    private void ReplaceLineText(String filePath, String toReplace, String newString) throws IOException {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(toReplace)) {
                fileContent.set(i, fileContent.get(i).replaceFirst(toReplace, newString));
            }
        }

        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
    }

    private String ConvertPackageName(String packageName) {
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
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private void copyFile(File source, File target) throws IOException {
        Files.copy(source.toPath(), target.toPath(), COPY_ATTRIBUTES);
    }

    private void DeleteFolder(String projectPath) {
        File f = new File(projectPath);

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

}
