/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.io.File;


/**
 *
 * @author Andreas Stefik
 */
public class Breakpoint implements Comparable{
    
    private String file;
    private int line;
    private int caretPos;

    public Breakpoint() {

        setFile("");
        setLine(0);
    }
    
    public Breakpoint(String path, int caretPos, int line) {
        setFile(path);
        setLine(line);       
    }

    public Breakpoint(String filename, int line) {

        File f = new File(filename);
        String path = f.getAbsolutePath();
        setFile(path);
        setLine(line);
    }
   
    public int compareTo(Object o) {
        Breakpoint b = (Breakpoint) o;
        if(file.compareTo(b.getFile()) == 0) {
            if(line > b.getLine()) {
                return 1;
            }
            else if(line < b.getLine()) {
                return -1;
            }
            else {
                return 0;
            }
        } 
        else if(file.compareTo(b.getFile()) > 0){
            return 1;
        }
        else {
            return -1;
        }
    }

    public int getCaretPos(){
        return caretPos;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setCaretPos(int pos) {
        this.caretPos = pos;
    }

}
