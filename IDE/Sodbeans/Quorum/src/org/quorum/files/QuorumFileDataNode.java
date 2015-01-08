/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.files;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileStatusEvent;
import org.openide.filesystems.FileStatusListener;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.quorum.projects.MainFileProvider;

/**
 *
 * @author stefika
 */
public class QuorumFileDataNode extends DataNode {
    public QuorumFileDataNode(QuorumDataObject obj, Lookup lookup) {
        super(obj, Children.LEAF, lookup);
        attachStatusListener();
    }
    
    private FileObject getFile() {
        return getDataObject().getPrimaryFile();
    }
    // Retrieves an object from a class in the getFromProject parameters.

    private Object getFromProject(Class clazz) {
        Object result;
        Project p = FileOwnerQuery.getOwner(getFile());
        if (p != null) {
            result = p.getLookup().lookup(clazz);
        } else {
            result = null;
        }
        return result;
    }
    // Detects whether or not a file is set as the Main File.

    private boolean isMainFile() {
        MainFileProvider prov = (MainFileProvider) getFromProject(MainFileProvider.class);
        boolean result;
        if (prov == null) {
            result = false;
        } else {
            FileObject myFile = getFile();
            result = prov.isMainFile(myFile);
        }
        return result;
    }

    @Override
    public String getHtmlDisplayName() {
        String name = getDisplayName();
        FileObject fileObject = getFile();

        try {
            FileSystem.Status stat = fileObject.getFileSystem().getStatus();
            if (stat instanceof FileSystem.HtmlStatus) {
                FileSystem.HtmlStatus hstat = (FileSystem.HtmlStatus) stat;

                String result = hstat.annotateNameHtml(getDisplayName(), Collections.singleton(fileObject));

                //Make sure the super string was really modified
                if (!name.equals(result)) {
                    name = result;
                }

                // TODO attach status listener at the FileSystem
                // and on change refire PROP_DISPLAY_NAME

            }
        } catch (FileStateInvalidException e) {
            //do nothing and fall through
        }

        name = isMainFile() ? "<u>" + name + "</u>" : name;
        return name;
    }

    private void attachStatusListener() {
        try {
            final FileObject fileObject = getFile();
            FileSystem fs = fileObject.getFileSystem();
            FileStatusListener l = FileUtil.weakFileStatusListener(new FileStatusListener() {
                @Override
                public void annotationChanged(FileStatusEvent ev) {
                    if (ev.hasChanged(fileObject)) {
                        if (ev.isNameChange()) {
                            fireDisplayNameChange(null, null);
                        }
                        if (ev.isIconChange()) {
                            fireIconChange();
                        }
                    }
                }
            }, fs);
            fs.addFileStatusListener(l);
        } catch (FileStateInvalidException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public Image getIcon(int type) {
        Image image = super.getIcon(type);
        FileObject fileObject = getFile();
        if (fileObject != null) {
            try {
                image = fileObject.getFileSystem().getStatus().annotateIcon(image, type, Collections.singleton(fileObject));
            } catch (FileStateInvalidException e) {
                // no fs, do nothing
            }
        }
        return image;
    }

    @Override
    public Action[] getActions(boolean popup) {
        Action[] actions = super.getActions(popup);
        //org.sodbeans.compiler.api.Compiler compiler =
        //        Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);
        Action[] result;
//        if (compiler != null && actions.length > 0) { //should always be > 0
//            result = new Action[actions.length + 1];
//            result[0] = actions[0];
//            result[1] = new SetMainFileAction();
//            System.arraycopy(actions, 1, result, 2, actions.length - 1);
//        } else {
            //Isolated file in the favorites window
            result = actions;
        //}
        return result;
    }
    // Sets the main file.

    private final class SetMainFileAction extends AbstractAction {

        public SetMainFileAction() {
            //Set a display name
            putValue(Action.NAME, "Set Main File");
        }

        public void actionPerformed(ActionEvent ae) {
            MainFileProvider provider = (MainFileProvider) getFromProject(MainFileProvider.class);
            FileObject oldMain = provider.getMainFile();
            provider.setMainFile(getFile());
            fireDisplayNameChange(getDisplayName(), getHtmlDisplayName());
            if (oldMain != null) {
                try {
                    Node oldMainFilesNode = DataObject.find(oldMain).getNodeDelegate();
                    if (oldMainFilesNode instanceof QuorumFileDataNode) {
                        ((QuorumFileDataNode) oldMainFilesNode).fireDisplayNameChange(null, oldMainFilesNode.getDisplayName());
                    }
                } catch (DataObjectNotFoundException donfe) { //Should never happen
                    Exceptions.printStackTrace(donfe);
                }
            }
        }

        @Override
        public boolean isEnabled() {
            return !isMainFile() && getFromProject(MainFileProvider.class) != null;
        }
    }
}
