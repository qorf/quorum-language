/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.quorum.projects.QuorumProject;
import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Language.Compile.Compiler;

/**
 *
 * @author stefika
 */
public class Build extends QuorumAction implements ActionListener{

    public Build(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Lookup lookup = project.getLookup();
        Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);
        FileObject projectDirectory = project.getProjectDirectory();
        File directory = FileUtil.toFile(projectDirectory);
        
        File file = new File(directory.getAbsolutePath() + "/SourceCode");
        quorum.Libraries.System.File quorumFile = getQuorumFile(file);
        Array$Interface listing = quorumFile.GetDirectoryListing();
        compiler.Empty();
        quorum.Libraries.System.File f = (quorum.Libraries.System.File)listing.Get(0);
        compiler.SetMain(f);
        compiler.Compile(listing);
        int a = 5;
    }

    @Override
    protected String getDisplayName() {
        return "Build";
    }
}
