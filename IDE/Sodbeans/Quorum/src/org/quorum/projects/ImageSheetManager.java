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
