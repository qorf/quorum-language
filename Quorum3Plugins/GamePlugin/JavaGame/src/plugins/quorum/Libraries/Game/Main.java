/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;

/**
 *
 * @author stefika
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameDisplay gd = new GameDisplay();
        System.setProperty("org.lwjgl.librarypath", "/Users/alleew/NetbeansProjects/quorum-libgdx/QuorumRainDrop/native/macosx");
        String library_path = System.getProperty("org.lwjgl.librarypath");
        System.out.println(library_path);
    }
    
}
