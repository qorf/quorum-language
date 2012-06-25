/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import org.quorum.vm.interfaces.LibraryIndexEntry;
import org.quorum.vm.interfaces.StandardLibrary;

/**
 * This class represents the files and folders in the standard library in the
 * quorum programming language.
 * 
 * @author Andreas Stefik
 */
public class QuorumStandardLibrary implements StandardLibrary{

    private final String CODE_BASE_NAME = "org.sodbeans.quorum.libraries";
    private final String STANDARD_LIBRARY_ROOT_NAME = "Libraries";
    private final String LIBRARY_ROOT_PATH = "quorum";
    private final String STANDARD_INDEX_PATH = "indexes/quorum.index";
    private File rootFolder;
    private static File staticIndex;
    private static File staticRoot;
    private static boolean isPathOverrideActive;
    
    /**
     * This HashMap's first string is the name of a particular class. The
     * HashMap nested inside of its string entry is the full package name.
     */
    private HashMap<String, HashMap<String, LibraryIndexEntry>> nameIndex;

    private HashMap<String, HashMap<String, LibraryIndexEntry>> packageIndex;
    
    private HashMap<String, HashMap<String, String>> packageList;

    public QuorumStandardLibrary() {
        //rootFolder = getRootFolder();
        nameIndex = new HashMap<String, HashMap<String, LibraryIndexEntry>>();
        packageIndex = new HashMap<String, HashMap<String, LibraryIndexEntry>>();
        packageList = new HashMap<String, HashMap<String, String>>();
        if(isPathOverrideActive) {
            rootFolder = staticRoot;
        }
        readIndex();
    }

    public Iterator<LibraryIndexEntry> getNameIterator(String name) {
        HashMap<String, LibraryIndexEntry> map = nameIndex.get(name);
        if (name != null) {
            return map.values().iterator();
        }
        return null;
    }

    /**
     * This method allows the user to override the standard path used
     * by the system to grab files from the standard library.
     *
     * @param root
     * @param index
     */
    public static void overrideStandardLibraryPath(File root, File index) {
        staticRoot = root;
        staticIndex = index;
        isPathOverrideActive = true;
    }

    /**
     * This allows the user to set whether the system should use an overriden
     * path or not.
     * 
     * @param isOverriden
     */
    public static void setStandardLibraryPathOverride(boolean isOverriden) {
        isPathOverrideActive = isOverriden;
    }
    
    private File getSourceFile(LibraryIndexEntry entry) {
        File file = new File(rootFolder, entry.getPath());
        return file;
    }

    @Override
    public File getRootFolder() {
        if(!isPathOverrideActive) {
           // File file = InstalledFileLocator.getDefault().locate(
           //     LIBRARY_ROOT_PATH, CODE_BASE_NAME, false);
           // return file;
            return null;
        }
        else {
            return staticRoot;
        }
    }

    private File getIndexFile() {
        if(!isPathOverrideActive) {
          //  File file = InstalledFileLocator.getDefault().locate(
           //         STANDARD_INDEX_PATH, CODE_BASE_NAME, false);
         //   return file;
            return null;
        }
        else {
            return staticIndex;
        }
    }

    private void readIndex() {
        File iFile = getIndexFile();
        try {
            BufferedReader input = new BufferedReader(new FileReader(iFile));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    addIndexEntry(line);
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private LibraryIndexEntry addIndexEntry(String line) {
        if (!line.isEmpty()) {
            QuorumLibraryIndexEntry entry = new QuorumLibraryIndexEntry();
            entry.setAttributesFromLine(line);
            HashMap<String, LibraryIndexEntry> ni = nameIndex.get(entry.getName());
            
            addPackages(entry);
            addToHash(nameIndex, entry, entry.getName(), entry.getFullPackageName());
            addToHash(packageIndex, entry, entry.getFullPackageName(), entry.getName());
        }
        return null;
    }
    
    private void addPackages(QuorumLibraryIndexEntry entry) {
        String[] packages = entry.getPackageArray();
        
        if(packages == null) {
            return;
        }
        
        String[] keys = new String[packages.length];
        String builder = this.getStandardLibraryRootName();
        keys[0] = builder;
        for(int i = 1; i < packages.length; i++) {
            builder += "." + packages[i];
            keys[i] = builder;
        }
        
        for(int i = 0; i < keys.length; i++) {
            HashMap<String, String> slot;
            if(!packageList.containsKey(keys[i])) {
                slot = new HashMap<String, String>();
                packageList.put(keys[i], slot);
            }
            else {
                slot = packageList.get(keys[i]);
            }
            if(i + 1 < packages.length && !slot.containsKey(packages[i + 1])) {
                slot.put(packages[i + 1], packages[i + 1]);
            }
        }
        
        
        int a = 0;
    } 

    private void addToHash(HashMap<String, HashMap<String, LibraryIndexEntry>> map, 
            LibraryIndexEntry entry, String key, String key2) {
        if(map != null) {
            HashMap<String, LibraryIndexEntry> map2 = map.get(key);
            if(map2 == null) {
                map2 = new HashMap<String, LibraryIndexEntry>();
                
                map.put(key, map2);
            }

            map2.put(key2, entry);
        }
    }

    @Override
    public boolean doesPackageExist(String name) {
        return packageIndex.containsKey(name);
    }

    @Override
    public File findClass(String pack, String name) {
        LibraryIndexEntry entry = findClassEntry(pack, name);
        if(entry != null) {
            File file = new File(this.rootFolder, entry.getPath());
            if(file.isFile()) {
                return file;
            }
        }

        return null;
    }

    public File findClass(LibraryIndexEntry entry) {
        return findClass(entry.getFullPackageName(), entry.getName());
    }

    public LibraryIndexEntry findClassEntry(String pack, String name) {
        HashMap<String, LibraryIndexEntry> packages = packageIndex.get(pack);
        if(packages != null) {
            return packages.get(name);
        }
        return null;
    }

    public Iterator<LibraryIndexEntry> findAllClassesInPackage(String pack) {
        HashMap<String, LibraryIndexEntry> map = this.packageIndex.get(pack);
        if(map == null) {
            Vector<LibraryIndexEntry> v = new Vector<LibraryIndexEntry>();
            return v.iterator();
        }
        return map.values().iterator();
    }
    
    public Iterator<String> findAllSubpackages(String pack) {
        HashMap<String, String> get = this.packageList.get(pack);
        if(get != null) {
            return get.values().iterator();
        }
        else {
            ArrayList<String> str = new ArrayList<String>();
            return str.iterator();
        }
    }

    public Iterator<LibraryIndexEntry> findAllClasses() {
        Iterator<HashMap<String, LibraryIndexEntry>> packages = this.packageIndex.values().iterator();
        
        LinkedList<LibraryIndexEntry> entries = new LinkedList<LibraryIndexEntry>();
        while(packages.hasNext()) {
            HashMap<String, LibraryIndexEntry> next = packages.next();
            Iterator<LibraryIndexEntry> thisPackage = next.values().iterator();
            while(thisPackage.hasNext()) {
                entries.add(thisPackage.next());
            }
        }
        
        return entries.iterator();
    }

    public String getStandardLibraryRootName() {
        return STANDARD_LIBRARY_ROOT_NAME;
    }
}
