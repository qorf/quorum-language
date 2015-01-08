/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import org.netbeans.spi.project.ui.PrivilegedTemplates;

/**
 *
 * @author stefika
 */
public class QuorumPrivilegedTemplates implements PrivilegedTemplates{

    @Override
    public String[] getPrivilegedTemplates() {
        String[] vals = new String[2];
        vals[0] = "Templates/Quorum/EmptyQuorumFile.quorum";
        vals[1] = "Templates/Quorum/Main.quorum";
        return vals;
    }

}
