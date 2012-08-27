package org.quorum.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Melissa Stefik
 */
public class CheckLandingPads {
    private HashMap<String,DetectInfo> landingPads = new HashMap<String,DetectInfo>();
    private ArrayList<DetectInfo> orderedLandingPads = new ArrayList<DetectInfo>();
    private ClassDescriptor clazz;
    private MethodDescriptor method;
    private boolean alwaysBlock = false;

    /**
     * Add a landing pad to the checks. A landing pad is the location information
     * for a jump caused by an exception on the system. A table of landing pads is
     * stored for an exception to determine where to jump (search for matching
     * detect to jump to).
     *
     * @param detectTypeName
     * Name of the type of error a detect statement catches or "detects".
     *
     * @param detectInfo
     * The detect information that needs to be stored in the exception table. This
     * stores local and global location information.
     */
    public void addLandingPad(String detectTypeName, DetectInfo detectInfo){
        if(!landingPads.containsKey(detectTypeName)){
            landingPads.put(detectTypeName, detectInfo);
            if(!detectTypeName.equals("Always") || (detectTypeName.equals("Always") && detectInfo.getDetectParameter() != null)){
                orderedLandingPads.add(detectInfo);
            }
            setAlwaysBlock(detectInfo.isAlawysBlock());
        }
    }

    /**
     * Get a landing pad or location information for a given detect statement.
     *
     * @param typeName
     * name of the type of error a detect statement detects.
     *
     * @return
     * Detect information, local and global position of the detect statement.
     */
    public DetectInfo getLandingPad(String typeName){
        if(typeName != null){
            return landingPads.get(typeName);
        }else{
            return null;
        }
    }
    
    /**
     * Get all of the detects
     * @return 
     */
    public ArrayList<DetectInfo>  getAllDetects(){
        return orderedLandingPads;
    }

    /**
     * @return the clazz
     */
    public ClassDescriptor getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(ClassDescriptor clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the method
     */
    public MethodDescriptor getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(MethodDescriptor method) {
        this.method = method;
    }

    /**
     * @return the alwaysBlock
     */
    public boolean hasAlwaysBlock() {
        return alwaysBlock;
    }

    /**
     * @param alwaysBlock the alwaysBlock to set
     */
    private void setAlwaysBlock(boolean alwaysBlock) {
        this.alwaysBlock = alwaysBlock;
    }

}
