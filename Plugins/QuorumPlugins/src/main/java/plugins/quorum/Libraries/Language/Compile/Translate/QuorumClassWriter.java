/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import org.objectweb.asm.ClassWriter;

/**
 *
 * @author stefika
 */
public class QuorumClassWriter extends ClassWriter{

    public QuorumClassWriter(int i) {
        super(i);
    }

    @Override
    protected String getCommonSuperClass(String left, String right) {
        try {
        String test = super.getCommonSuperClass(left, right);
        return test;
        } catch(Exception exc) {
            //This may not be ideal and has something to do with the exception system
            //TODO: Investigate the impact of this more carefully.
            return "java/lang/Object";
        }
    }
    
}
