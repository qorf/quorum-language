/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.File;

/**
 *
 * @author stefika
 */
public class ImageSheetManager {
    private HashMap<String, List<String>> imagesHash = new HashMap<>();
    private static final String SHEET_DELIMITER = ";";
    private static final String IMAGE_DELIMITER = ",";
    private static final String SHEET_SEPARATOR = ":";
    private String buildPath = "resources";
    private boolean rebuildOnCompile = false;
    private boolean enableImageSheetSupport = false;
    public static final String IMAGE_SHEETS = "Image_Sheets";
    public static final String IMAGE_SHEETS_ENABLED = "Image_Sheets_Enabled";
    public static final String REBUILD_IMAGE_SHEETS_ON_COMPILE = "Rebuild_Image_Sheets_On_Compile";
    public static final String IMAGE_SHEET_BUILD_PATH = "ImageSheetBuildPath";
    
    public boolean Add(String imageSheet) {
        if(getImagesHash().containsKey(imageSheet)) {
            return false;
        } else {
            getImagesHash().put(imageSheet, new LinkedList<String>());
            return true;
        }
    }
    
    public List<String> remove(String imageSheet) {
        return getImagesHash().remove(imageSheet);
    }
    
    public List<String> get(String imageSheet) {
        return getImagesHash().get(imageSheet);
    }
    
    public boolean has(String imageSheet) {
        return getImagesHash().containsKey(imageSheet);
    }
    
    public int size() {
        return getImagesHash().size();
    }
    
    public Iterator<String> getImageSheetIterator() {
        return getImagesHash().keySet().iterator();
    }
    
    /*
    
        This method returns an image sheet manager with all appropriate properties
        set. It is just a helper method.
    */
    public static ImageSheetManager getImageSheetManager(Properties properties) {
        ImageSheetManager manager = new ImageSheetManager();
        String enabled = properties.getProperty(IMAGE_SHEETS_ENABLED);
        if(enabled != null) {
            manager.setEnableImageSheetSupport(true);
        }
        String rebuild = properties.getProperty(REBUILD_IMAGE_SHEETS_ON_COMPILE);
        if(rebuild != null) {
            manager.setRebuildOnCompile(true);
        }
        String path = properties.getProperty(IMAGE_SHEET_BUILD_PATH);
        if(path != null) {
            manager.setBuildPath(path);
        }
        String sheets = properties.getProperty(IMAGE_SHEETS);
        manager.load(sheets); //this method automatically checks for null
        return manager;
    }
    
    public void load(String string) {
        if(string != null) {
            String[] sheets = string.split(SHEET_DELIMITER);
            for(int i = 0; i < sheets.length; i++) {
                String sheet = sheets[i];
                String[] split = sheet.split(SHEET_SEPARATOR);
                String sheetName = split[0];
                List<String> images = new LinkedList<String>();
                
                if(split.length > 1) {
                    split = split[1].split(IMAGE_DELIMITER);
                    for(int j = 0; j < split.length; j++) {
                        images.add(split[j]);
                    }
                }
                imagesHash.put(sheetName, images);
            }
        }
    }
    
    /**
     * This method builds a single image sheet, if it exists.
     * 
     * @param sheet
     * @param path 
     */
    public void buildImageSheet(String sheet, File path) {
        Settings settings = new TexturePacker.Settings();
        TexturePacker packer = new TexturePacker(settings);
        String projectPath =  path.getAbsolutePath();
        List<String> images = imagesHash.get(sheet);
        if(images != null && images.size() > 0 && sheet != null && sheet.length() != 0) {
            for(int i = 0; i < images.size(); i++) {
                String image = (String) images.get(i);
                File file = new File(projectPath + File.separator + image);
                packer.addImage(file);
            }
            File output = new File(projectPath + File.separator + buildPath);
            try {
                deleteOldTextureSheet(sheet, path);
                packer.pack(output, sheet);
            } catch(Exception e) {
                Logger.logMsg(Logger.WARNING, e.toString());
            }
        }
    }
    
    private void deleteOldTextureSheet(String sheet, File path) {
        String projectPath =  path.getAbsolutePath();
        File atlas = new File(projectPath + File.separator + buildPath + File.separator + sheet + ".atlas");
        if(atlas.exists() && atlas.isFile()) {
            atlas.delete();
        }
        //now delete any atlas files that exist with this naming convention.
        boolean isFinished = false;
        int i = 0;
        while(!isFinished) {
            File page = null;
            if(i == 0) {
                page = new File(projectPath + File.separator + buildPath + File.separator + sheet + ".png");
            } else {
                page = new File(projectPath + File.separator + buildPath + File.separator + sheet + i + ".png");
            }
            if(page.exists() && page.isFile()) {
                page.delete();
            } else { //we're done here.
                isFinished = true;
            }
            i++;
        }
    }
    /**
     * This method builds all image sheets loaded into the object.
     * 
     * @param path 
     */
    public void buildAllImageSheets(File path) {
        Iterator<String> iterator = imagesHash.keySet().iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            buildImageSheet(next, path);
        }
    }
    
    public String save() {
        String result = "";
        Iterator<String> it = getImageSheetIterator();
        while(it.hasNext()) {
            String next = it.next();
            List<String> images = get(next);
            result = result + next + SHEET_SEPARATOR;
            Iterator<String> im = images.iterator();
            while(im.hasNext()) {
                String n = im.next();
                result = result + n;
                if(im.hasNext()) {
                    result = result + IMAGE_DELIMITER;
                }
            }
            result = result + SHEET_DELIMITER;
        }
        return result;
    }

    /**
     * @return the imagesHash
     */
    public HashMap<String, List<String>> getImagesHash() {
        return imagesHash;
    }

    /**
     * @param imagesHash the imagesHash to set
     */
    public void setImagesHash(HashMap<String, List<String>> imagesHash) {
        this.imagesHash = imagesHash;
    }

    /**
     * @return the buildPath
     */
    public String getBuildPath() {
        return buildPath;
    }

    /**
     * @param buildPath the buildPath to set
     */
    public void setBuildPath(String buildPath) {
        this.buildPath = buildPath;
    }

    /**
     * @return the rebuildOnCompile
     */
    public boolean isRebuildOnCompile() {
        return rebuildOnCompile;
    }

    /**
     * @param rebuildOnCompile the rebuildOnCompile to set
     */
    public void setRebuildOnCompile(boolean rebuildOnCompile) {
        this.rebuildOnCompile = rebuildOnCompile;
    }

    /**
     * @return the enableImageSheetSupport
     */
    public boolean isEnableImageSheetSupport() {
        return enableImageSheetSupport;
    }

    /**
     * @param enableImageSheetSupport the enableImageSheetSupport to set
     */
    public void setEnableImageSheetSupport(boolean enableImageSheetSupport) {
        this.enableImageSheetSupport = enableImageSheetSupport;
    }
}
