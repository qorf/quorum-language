/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class acts as a wrapper for starting Quorum projects on the CBC. It
 * simply invokes the Main() method of the "Main" class in the CBC project.
 * In the future, this might be auto-generated, but for now, the file
 * quorum/Main.class must exist when this wrapper is used.
 * @author jeff
 */
public class Wrapper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        quorum.main.main(args);
    }
}
