/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.Severity;
import org.netbeans.modules.csl.spi.DefaultError;
import org.openide.filesystems.FileObject;

/**
 *
 * @author stefika
 */
public class QuorumError extends DefaultError {

    public QuorumError(String displayName, String description, String key, FileObject fo, int start, int end, Severity severity) {
        super(displayName, description, key, fo, start, end, severity);
    }
    
}
