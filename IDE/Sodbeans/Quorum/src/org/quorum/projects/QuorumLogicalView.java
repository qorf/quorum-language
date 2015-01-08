/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.projects;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JSeparator;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.FolderLookup;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import static org.quorum.projects.QuorumProject.QUORUM_PROJECT_ICON;

/**
 *
 * @author stefika
 */
public class QuorumLogicalView implements LogicalViewProvider{
    private final QuorumProject project;

    public QuorumLogicalView(QuorumProject project) {
        this.project = project;
    }
    
    @Override
    public Node createLogicalView() {
        try {
            DataFolder sourcesDataObject = DataFolder.findFolder(project.getProjectDirectory()); //Bug Fix -- Andrew Hauck
            Node realSourcesFolderNode = sourcesDataObject.getNodeDelegate(); //Bug Fix -- Andrew Hauck
            //This FilterNode will be our project node
            return new SourcesNode (realSourcesFolderNode, project);

        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
            //Fallback-the directory couldn't be created -
            //read-only filesystem or something evil happened
            return new AbstractNode (Children.LEAF);
        }
    }
    
    private static class ProjectFilteredChildren extends FilterNode.Children {
        public ProjectFilteredChildren(Node owner) {
            super(owner);
        }

        @Override
        protected Node copyNode(Node original) {
            return new ProjectFilteredNode(original);
        }

        @Override
        protected Node[] createNodes(Node key) {
            List<Node> result = new ArrayList<Node>();
            for (Node node : super.createNodes(key)) {
                if (accept(node)) {
                    result.add(node);
                }
            }
            return result.toArray(new Node[result.size()]);
        }   
        
        private boolean accept(Node node) {
            if(node.getName().equals(MainFileProvider.BUILD_DIRECTORY)) {
                return false;
            }
            else if(node.getName().equals(MainFileProvider.DISTRIBUTION_DIRECTORY)) {
                return false;
            }
            else if(node.getName().equals(MainFileProvider.PROJET_PROPERTIES_DIRECTORY)) {
                return false;
            }
            else if(node.getName().equals(MainFileProvider.DOCUMENTS_DIRECTORY)) {
                return false;
            }
            else if(node.getName().startsWith(".")) { //remove hidden files.
                return false;
            }
            return true;
        }
    }
    
    private static class ProjectFilteredNode extends FilterNode {
        FileObject fileObject;
        public ProjectFilteredNode(Node owner) {
            super(owner);
            FileObject lookup = owner.getLookup().lookup(FileObject.class);
            fileObject = lookup;
        }

        @Override
        public String getDisplayName() {
            if(this.getName().equals(QuorumProject.SOURCE_CODE_DIRECTORY)) {
                return "Source Code";
            }
            return super.getDisplayName();
        }
        
        @Override
        public java.awt.Image getIcon (int type) {
            java.awt.Image img = super.getIcon (type);

            if(fileObject != null) {
                try {
                    img = fileObject.getFileSystem().getStatus().annotateIcon(img, type, Collections.singleton(fileObject));
                } catch (FileStateInvalidException e) {
                    // no fs, do nothing
                }
            }
            return img;
        }
    }

    /** This is the node you actually see in the project tab for the project */
    private static final class SourcesNode extends FilterNode {

        final QuorumProject project;
        FileObject fileObject = null;
        public SourcesNode (Node node, QuorumProject project) throws DataObjectNotFoundException {
            super (node, new ProjectFilteredChildren(node),
                    //The projects system wants the project in the Node's lookup.
                    //NewAction and friends want the original Node's lookup.
                    //Make a merge of both
                    new ProxyLookup (new Lookup[] {Lookups.singleton(project),
                    node.getLookup() }));
            FileObject lookup = node.getLookup().lookup(FileObject.class);
            fileObject = lookup;
            this.project = project;
        }
        
        /***
         * This function defines what shows up on the right click menu for
         * a project of type Quorum
         * @param popup
         * @return
         */
        @Override
        public Action[] getActions(boolean popup) {
            boolean canDocument = true;
            ArrayList<Action> nodeActions = new ArrayList<Action>();
            
            nodeActions.add(CommonProjectActions.newFileAction());

            //build actions
//            nodeActions.add(null);
//            nodeActions.add(new SodbeansBuildAction());
//            nodeActions.add(new SodbeansCleanBuildAction());
//            nodeActions.add(new SodbeansCleanAction());
//
//            //run and debug actions
//            nodeActions.add(null);
//            nodeActions.add(new SodbeansRunAction());
//            nodeActions.add(new SodbeansDebugAction());
//            if(canDocument) {
//                nodeActions.add(new SodbeansDocumentAction());
//            }
            
            //set main project and close
            nodeActions.add(null);
            nodeActions.add(new SetMainProjectAction());
            nodeActions.add(super.getActions(popup)[2]); //find
            nodeActions.add(CommonProjectActions.closeProjectAction());

            //rename, copy, delete
            nodeActions.add(null);
            nodeActions.add(super.getActions(popup)[9]); //rename
            nodeActions.add(CommonProjectActions.moveProjectAction());
            nodeActions.add(CommonProjectActions.copyProjectAction());
            nodeActions.add(CommonProjectActions.deleteProjectAction());            
            
            //honor 57874 contact
            //http://wiki.netbeans.org/ProjectVersioning
            //the code is slightly deprecated and could be updated, but it 
            //still works. 
            try {
                Repository repository  = Repository.getDefault();
                FileSystem sfs = repository.getDefaultFileSystem();
                FileObject fo = sfs.findResource("Projects/Actions");  // NOI18N
                if (fo != null) {
                    DataObject dobj = DataObject.find(fo);
                    FolderLookup actionRegistry = new FolderLookup((DataFolder)dobj);
                    Lookup.Template query = new Lookup.Template(Object.class);
                    Lookup lookup = actionRegistry.getLookup();
                    Iterator it = lookup.lookup(query).allInstances().iterator();
                    if (it.hasNext()) {
                        nodeActions.add(null);
                    }
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (next instanceof Action) {
                            nodeActions.add((Action) next);
                        } else if (next instanceof JSeparator) {
                            nodeActions.add(null);
                        }
                     }
                }
            } catch (DataObjectNotFoundException ex) {
                // data folder for exiting fileobject expected
                ErrorManager.getDefault().notify(ex);
            }
            
            //nodeActions.add(SystemAction.get(PropertiesAction.class));
            nodeActions.add(CommonProjectActions.customizeProjectAction());
            
            
            Action[] nodes = new Action[nodeActions.size()];
            for(int i = 0; i < nodeActions.size(); i++) {
                nodes[i] = nodeActions.get(i);
            }
            return nodes;
        }

        @Override
        public Image getIcon(int type) {
            Image icon = ImageUtilities.loadImage(QuorumProject.QUORUM_PROJECT_ICON);
            
            if(fileObject != null) {
                try {
                    icon = fileObject.getFileSystem().getStatus().annotateIcon(icon, type, Collections.singleton(fileObject));
                } catch (FileStateInvalidException e) {
                    // no fs, do nothing
                }
            }
            return icon;
        }

        @Override
        public Image getOpenedIcon (int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }

        private final class SetMainProjectAction extends AbstractAction {

            public SetMainProjectAction() {
                //Set a display name
                putValue(Action.NAME, NbBundle.getMessage(QuorumLogicalView.class, "LBL_MainProjectAction"));
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                OpenProjects.getDefault().setMainProject(project);
            }
        }
    }

    @Override
    public Node findPath (Node root, Object target) {
        //leave unimplemented for now
        return null;
    }
}
