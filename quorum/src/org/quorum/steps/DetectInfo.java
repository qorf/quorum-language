/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

/**
 * Information needed to identify a catch block as a "landing pad".
 *
 * @author Melissa Stefik
 */
public class DetectInfo {
    private boolean alawysBlock = false;
    private int globalLocation = -1;
    private int localLocation = -1;
    private DetectParameter detectParam = new DetectParameter();

    /**
     * @return the globalLocation
     */
    public int getGlobalLocation() {
        return globalLocation;
    }

    /**
     * @param globalLocation the globalLocation to set
     */
    public void setGlobalLocation(int globalLocation) {
        this.globalLocation = globalLocation;
    }

    /**
     * @return the localLocation
     */
    public int getLocalLocation() {
        return localLocation;
    }

    /**
     * @param localLocation the localLocation to set
     */
    public void setLocalLocation(int localLocation) {
        this.localLocation = localLocation;
    }

    /**
     * @return the detect
     */
    public DetectParameter getDetectParameter() {
        return detectParam;
    }

    /**
     * @param detectType the detectType to set
     */
    public void setDetectParameter(DetectParameter detectParam) {
        this.detectParam = detectParam;
    }

    /**
     * @return the alawysBlock
     */
    public boolean isAlawysBlock() {
        return alawysBlock;
    }

    /**
     * @param alawysBlock the alawysBlock to set
     */
    public void setAlawysBlock(boolean alawysBlock) {
        this.alawysBlock = alawysBlock;
    }
}
