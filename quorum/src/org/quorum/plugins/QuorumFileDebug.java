/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.io.File;
import java.util.Stack;

/**
 * A plugin for the "File" class in Quorum, located under Libraries.System.File.
 * 
 * This is the "debug" version of the plugin. The only difference is that this
 * class has "undo" methods for CreateDirectory(), Move(), Delete() and
 * SetPathNative().
 * 
 * Many methods here are undocumented -- please see the File.quorum class for
 * specifics on each method.
 * 
 * @author Jeff Wilson
 */
public class QuorumFileDebug extends QuorumFile {
    /**
     * Represents a Move() or Delete() operation. These can be used later to undo moves and deletes.
     * Deletes can be undone as a "delete" in debug mode is just a rename operation.
     */
    private class MoveRecord {
        public String from = "";
        public String to = "";
    }
    
    /**
     * Our undo stacks for path changes, directory creations, moves and deletes, respectively.
     */
    private Stack<String> paths = new Stack<String>();
    private Stack<String> directories = new Stack<String>();
    private Stack<MoveRecord> moves = new Stack<MoveRecord>();
    private Stack<MoveRecord> deletes = new Stack<MoveRecord>();
    
    /**
     * The same as "SetPathNative" in the parent class, but tracks the old
     * pathing information.
     */
    @Override
    public void SetPathNative(String path) {
        this.path = path;
        this.file = new File(path);
        paths.add(path);
    }
    
    /**
     * Go back to a previous path.
     */
    public void unSetPathNative() {
        if (paths.empty())
            return;
        
        String oldPath = paths.pop();
        this.path = oldPath;
        this.file = new File(oldPath);
    }
    
    /**
     * The same as "Move" in the parent class, but tracks the old
     * move information so it may be undone.
     */
    @Override
    public boolean Move(String newPath) {
        String oldPath = this.path;
        File newFile = new File(newPath);
        
        if (file.renameTo(newFile)) {
            this.path = newPath;
            
            // Store this change.
            MoveRecord rec = new MoveRecord();
            rec.from = oldPath;
            rec.to = newPath;
            moves.add(rec);
            return true;
        }
        
        return false;
    }
    
    /**
     * Undo a move.
     */
    public void unMove() {
        if (moves.empty())
            return;
        
        // Undo the last move.
        MoveRecord record = moves.pop();
        File f = new File(record.to);
        f.renameTo(new File(record.from)); // give it the old name
        this.path = record.from;
    }
    
    /**
     * The same as "CreateDirectory" in the parent, but tracks the old
     * creations so they may be undone.
     * 
     * @return 
     */
    @Override
    public boolean CreateDirectory() {
        if (file.mkdir()) {
            // Keep track of the directory we created.
            directories.add(file.getPath());
            return true;
        }
        
        return false; 
    }
    
    /**
     * Undo directory creation. This works even if the user put files in the
     * directory after calling CreateDirectory().
     */
    public void unCreateDirectory() {
        // Delete the last created directory.
        
        if (directories.isEmpty())
            return;
        
        // Delete the directory.
        String lastDirectory = directories.pop();
        File dir = new File(lastDirectory);
        deleteDir(dir);
    }
    
    /**
     * Delete the file by renaming it. This way, we can undo it later.
     * @return 
     */
    @Override
    public boolean Delete() {
        String newPath = file.getPath() + ".quorumdeleted" + System.currentTimeMillis();
                
        // If this succeeded, store it.
        if (file.renameTo(new File(newPath))) {
            MoveRecord rec = new MoveRecord();
            rec.from = file.getPath();
            rec.to = newPath;
            deletes.add(rec);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Undo a Delete() operation by giving the file its old name back.
     */
    public void unDelete() {
        if (deletes.isEmpty())
            return;
        
        // Undo the last delete.
        MoveRecord record = deletes.pop();
        File f = new File(record.to);
        f.renameTo(new File(record.from)); // give it the old name
    }
    
    /**
     * Recursively delete everything in a directory. Is called of unCreateDirectory()
     * can confirm that we created the specified directory during runtime.
     * 
     * Copied verbatim from http://www.exampledepot.com/egs/java.io/DeleteDir.html .
     * @param path
     * @return 
     */
    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
}
