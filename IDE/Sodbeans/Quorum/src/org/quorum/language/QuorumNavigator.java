/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.quorum.language;

import java.util.Collection;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JLabel;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.quorum.files.QuorumDataObject;
//import org.quorum.projects.QuorumProject;
import org.quorum.windows.QuorumNavigatorPanel;
import org.quorum.windows.nodes.QuorumClassNode;
import quorum.Libraries.Language.Compile.CompilerResult$Interface;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable$Interface;
import quorum.Libraries.Language.Compile.Symbol.Action$Interface;

/**
 *
 * @author stefika
 */
@NavigatorPanel.Registration(displayName = "Quorum Navigator", mimeType = "text/x-quorum")
public class QuorumNavigator implements NavigatorPanel{

    /** holds UI of this panel */
    private QuorumNavigatorPanel panelUI = new QuorumNavigatorPanel();
    /** template for finding data in given context.
     * Object used as example, replace with your own data source, for example JavaDataObject etc */
    private static final Lookup.Template MY_DATA = new Lookup.Template(QuorumDataObject.class);
    /** current context to work on */
    private Lookup.Result curContext;
    /** listener to context changes */
    private LookupListener contextL;
    
    /** public no arg constructor needed for system to instantiate provider well */
    public QuorumNavigator() {
    }

//     @Override
//    public JComponent getToolbarComponent() {
//        if (panelUI == null) {
//            panelUI = new JLabel("Toolbar");
//            // You can override requestFocusInWindow() on the component if desired.
//        }
//        return panelUI;
//    }
    
    @Override
    public String getDisplayHint() {
        return "Implementation of a Navigator for the Quorum programming language";
    }

    @Override
    public String getDisplayName() {
        return "Members";
    }

    @Override
    public JComponent getComponent() {
        return panelUI;
    }

    @Override
    public void panelActivated(Lookup context) {
        // lookup context and listen to result to get notified about context changes
        curContext = context.lookup(MY_DATA);
        curContext.addLookupListener(getContextListener());
        // get actual data and recompute content
        Collection data = curContext.allInstances();
        setNewContent(data);
    }

    @Override
    public void panelDeactivated() {
        curContext.removeLookupListener(getContextListener());
        curContext = null;
    }
    
    @Override
    public Lookup getLookup () {
        // go with default activated Node strategy
        return null;
    }
    
    /************* non - public part ************/
    
    private void setNewContent (Collection newData) {
        // put your code here that grabs information you need from given
        // collection of data, recompute UI of your panel and show it.
        // Note - be sure to compute the content OUTSIDE event dispatch thread,
        // just final repainting of UI should be done in event dispatch thread.
        // Please use RequestProcessor and Swing.invokeLater to achieve this.
        Iterator it = newData.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof QuorumDataObject) {
                //panelUI.setCurrentFileInEditor((FileObject)o);
                QuorumDataObject data = (QuorumDataObject) o;
                FileObject fileObject = data.getPrimaryFile();
                String path = fileObject.getPath();
                
                //now ask the compiler for the most recent version.
                Project project = FileOwnerQuery.getOwner(fileObject);
                if(project instanceof org.quorum.projects.QuorumProject) {
                    org.quorum.projects.QuorumProject qp = (org.quorum.projects.QuorumProject) project;
                    CompilerResult$Interface result = qp.getSandboxCompilerResult();
                    if(result != null) {
                        SymbolTable$Interface table = result.Get$Libraries$Language$Compile$CompilerResult$symbolTable();
                        quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz = table.GetClassInFile(path);
                        if(clazz != null) {
                            setToClass(clazz);
                        }
                    }
                }
            }
        }
    }
    
    private void setToClass(quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz) {
        QuorumClassNode node = new QuorumClassNode(clazz);
        panelUI.setRoot(node);
    }
    
    /** Accessor for listener to context */
    private LookupListener getContextListener () {
        if (contextL == null) {
            contextL = new ContextListener();
        }
        return contextL;
    }
    
    /** Listens to changes of context and triggers proper action */
    private class ContextListener implements LookupListener {
        
        @Override
        public void resultChanged(LookupEvent ev) {
            Collection<Object> data = ((Lookup.Result)ev.getSource()).allInstances();
            setNewContent(data);
        }
        
    } // end of ContextListener
}
