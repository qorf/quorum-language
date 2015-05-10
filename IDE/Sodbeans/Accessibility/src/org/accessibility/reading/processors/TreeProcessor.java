package org.accessibility.reading.processors;

import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Field;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.accessibility.AccessibleNode;
import org.sodbeans.phonemic.AbstractSpeechProcessor;
import org.accessibility.sound.SoundUtility;

/**
 * Handles processing for JTree objects.
 *
 * @author jeff
 */
public class TreeProcessor extends AbstractSpeechProcessor {
    /**
     * The text to say to describe an 'expanded' node.
     */
    public static final String EXPANDED_TEXT = "expanded";
    
    /**
     * The text to say to describe a 'collapsed' node.
     */
    public static final String COLLAPSED_TEXT = "collapsed";
    
    /**
     * Instruction text for how to expand a node.
     */
    public static final String INSTRUCTION_EXPAND = "Press right arrow to expand.";
    
    /**
     * Instruction text for how to collapse a node.
     */
    public static final String INSTRUCTION_COLLAPSE = "Press left arrow to collapse.";
    
    /**
     * Text describing how many children exist below a node when only one exists.
     * 
     * For example, A has one child.
     */
    public static final String CHILD_TEXT = "one child";
    
    /**
     * Text describing how many children exist below a node. For example,
     * node A has 36 children.
     */
    public static final String CHILDREN_TEXT  = "children";
    
    
    private TreeNode treeNode = null;
    private boolean keyEvent = false;
    private int keyCode = 0;
    private JTree tree;
    private SoundUtility snd = SoundUtility.instance();
    

    /**
     * break down the path string to say something more useful in the
     * tree structures especially the open dialog. TODO: limit this
     * to the dialog boxes we want(eg. open dialog, saveas dialog, etc.).
     *
     * @param s
     * @return
     */
    private String getSimplePathString(String s) {

        if (s != null) {
            String[] splitString = s.split("/");
            if (splitString.length > 0) {
                s = splitString[splitString.length - 1];
                splitString = s.split("\\\\");
                s = splitString[splitString.length - 1];
                return s;
            }
            else {
                return "Root Folder";
            }
        }
        return "";
    }

    public String getTreeNodeString(TreeNode node) {
        String sayString = "";

        
        if (node != null && node instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)node;
            if (dmtn.getUserObject() != null)
                sayString = dmtn.getUserObject().toString();
        } else
            sayString = node.toString();

        /*Change made by Andrew Hauck 3/8/2010
        (Hack) this assists evaluating the tab names correctly within Sappy*/
        if (sayString != null && (sayString.equals("Projects") || sayString.equals(""))
                && TopComponent.getRegistry() != null && TopComponent.getRegistry().getActivated() != null
                &&  node.getClass().getName().compareTo("org.openide.explorer.view.VisualizerNode") == 0) {
            sayString = TopComponent.getRegistry().getActivated().getName();
            return getSimplePathString(sayString) + " gained focus.";
        }

        //(hack) Forcing the focus change event to tell the user where they are
        // located.
        if (sayString != null && node.getClass().getName().compareTo("org.openide.explorer.view.VisualizerNode") == 0 && node.getParent() == null){
                if(sayString.equals("Project") || sayString.equals("Templates")) {
                    sayString = sayString + " Categories gained focus";
                    return sayString;
                }
        }

        if (sayString != null && tree.getSelectionCount() != 0 && tree.getSelectionPath() != null) {
            Object o = tree.getSelectionPath().getLastPathComponent();
            if (o instanceof TreeNode) {
                sayString = getTreeNodeChildString((TreeNode)o);
            }
        }

        if (sayString != null) {
            return getSimplePathString(sayString);
        }
        
        return sayString == null ? "" : sayString;
    }

    private String getTreeNodeChildString(TreeNode node) {
        String sayString = "";

        //is this a sodbeans specific Node?
        if(node.getClass().getName().compareTo("org.openide.explorer.view.VisualizerNode") == 0) {
            try {
                //check if it's a Quorum Node
                Field field = node.getClass().getDeclaredField("node");
                field.setAccessible(true);
                Object access = field.get(node);

                if(access != null && access instanceof AccessibleNode) {
                    AccessibleNode an = (AccessibleNode) access;
                    if(an.getScreenReaderInformation() != null) {
                        sayString = an.getScreenReaderInformation().getDescription();
                    }
                }
                else {
                    if (node != null) {
                        sayString = node.toString();
                    }
                }
            } catch (Exception ex) {
                if (node != null) {
                    sayString = node.toString();
                }
            }
        }
        else {
            if (node != null) {
                sayString = node.toString();
            }
        }
        return sayString;
    }

    private String getReflectionString(Object object) {
        if (object == null || tree == null) {
            return "";
        }

        if (tree.getSelectionPath() == null) {
            return "";
        }

        if (object.getClass().getName().compareTo("org.netbeans.modules.search.ResultTreeModel") == 0) {
            Object o = tree.getSelectionPath().getLastPathComponent();

            if (o == object) { //this is the root
                Class cls = o.getClass();
                try {
                    Field f = cls.getDeclaredField("rootDisplayName");
                    f.setAccessible(true);
                    Object result = f.get(o);

                    if (result instanceof String && result != null) {
                        return (String) result;
                    }
                } catch (IllegalArgumentException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IllegalAccessException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (NoSuchFieldException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (SecurityException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            if (o.getClass().getName().compareTo("org.netbeans.modules.search.MatchingObject") == 0) {
                return getMatchingObjectString(o);
            } else if (o.getClass().getName().compareTo("org.netbeans.modules.search.TextDetail$DetailNode") == 0) {
                return getTextDetailInnerDetailNodeString(o);
            }
        }//could be that this is the root, so handle this case as well
        else if (object.getClass().getName().compareTo("org.netbeans.modules.search.TextDetail$DetailNode") == 0) {
            return getTextDetailInnerDetailNodeString(object);
        }
        if (object.getClass().getName().compareTo("org.netbeans.modules.search.MatchingObject") == 0) {
            return getMatchingObjectString(object);
        }
        
        return "";
    }

    private String getTextDetailInnerDetailNodeString(Object object) {
        if (object instanceof org.openide.nodes.Node) {
            Node node = (Node) object;
            String sayString = node.getDisplayName();
            if (sayString != null) {
                return sayString;
            }
        }
        return "";
    }

    private String getMatchingObjectString(Object object) {
        Class cls = object.getClass();
        try {
            Field f = cls.getDeclaredField("file");
            f.setAccessible(true);
            Object result = f.get(object);
            File file = (File) result;

            if (file != null) {
                return file.getName();
            }
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        } catch (NoSuchFieldException ex) {
            Exceptions.printStackTrace(ex);
        } catch (SecurityException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassCastException ex) {
            Exceptions.printStackTrace(ex);
        }
        return "";
    }

        public String process() {
        if (keyEvent) {
            // Get some information about the currently selected node.
            if(tree == null) {
                return "";
            }
            else if(tree.getSelectionPath() == null) {
                return "";
            }
            Object s = tree.getSelectionPath().getLastPathComponent();
            TreeNode selection = null;
            boolean hasChildren = false;
            boolean expanded = false;
            int numberOfChildren = 0;
            String name = "";
            
            if (s instanceof TreeNode) {
                selection = (TreeNode)s;
                hasChildren = (selection.getChildCount() > 0);
                numberOfChildren = selection.getChildCount();
                expanded = tree.isExpanded(tree.getSelectionPath());
                name = getTreeNodeString(selection);
                
                // Does this represent a main project?
                Class cls = s.getClass();
                try {
                    Field f = cls.getDeclaredField("htmlDisplayName");
                    f.setAccessible(true);
                    Object result = f.get(selection);
                    
                    if (result instanceof String) {
                        String htmlName = (String)result;
                        
                        if (htmlName.startsWith("<b>")) {
                            if (selection.getParent().getParent() == null)
                                name += " - main project.";
                            else
                                name += " - main file.";
                        }
                    }
                } catch (NoSuchFieldException ex) {
                    //Exceptions.printStackTrace(ex);
                } catch (SecurityException ex) {
                    //Exceptions.printStackTrace(ex);
                } catch (IllegalArgumentException ex) {
                    //Exceptions.printStackTrace(ex);
                } catch (IllegalAccessException ex) {
                    //Exceptions.printStackTrace(ex);
                }

            }
            else {
                // TODO: Find a case where this happens.
                return getReflectionString(s);
            }
            
            if (keyCode == KeyEvent.VK_LEFT) {
                if (hasChildren) {
                    if (!expanded) {
                        return name + " " + COLLAPSED_TEXT + ". " + INSTRUCTION_EXPAND;
                    }
                    else {
                        return name + " " + EXPANDED_TEXT + ". " + INSTRUCTION_COLLAPSE;
                    }
                }
                else {
                    return name;
                }
            }
            else if (keyCode == KeyEvent.VK_RIGHT) {
                if (hasChildren) {
                    if (!expanded) {
                        return name + " " + COLLAPSED_TEXT + ". " + INSTRUCTION_EXPAND;
                    }
                    else {
                        // We expanded the node. Tell them how many items were expanded.
                        String childText = "";
                        
                        if (numberOfChildren == 1)
                            childText = CHILD_TEXT;
                        else if (numberOfChildren > 1)
                            childText = numberOfChildren + " " + CHILDREN_TEXT;
                        
                        return name + " " + EXPANDED_TEXT + ". " + childText + " " + INSTRUCTION_COLLAPSE;
                    }
                }
                else {
                    return name;
                }
            }
            else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                if (hasChildren) {
                    if (!expanded) {
                        return name + " " + COLLAPSED_TEXT + ". " + INSTRUCTION_EXPAND;
                    }
                    else {
                        String childText = "";
                        if (numberOfChildren == 1)
                            childText = CHILD_TEXT;
                        else if (numberOfChildren > 1)
                            childText = numberOfChildren + " " + CHILDREN_TEXT;
                        return name + " " + EXPANDED_TEXT + ". " + childText + " " + INSTRUCTION_COLLAPSE;
                    }
                }
                else {
                    return name;
                    }
                }
            }

        // Handling of focus events is slightly different.
        if (tree != null) {
            Object o = tree.getModel().getRoot();
            if (o instanceof TreeNode) {
                TreeNode node = (TreeNode) o;
                return getTreeNodeString(node);
            } else {
                //return getSimplePathString(getReflectionString(myO));
                return getReflectionString(o);
            }
        }

        return "";
    }

    /**
     * @return the treeNode
     */
    public TreeNode getTreeNode() {
        return treeNode;
    }

    /**
     * @param treeNode the treeNode to set
     */
    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    /**
     * @return the keyEvent
     */
    public boolean isKeyEvent() {
        return keyEvent;
    }

    /**
     * @param keyEvent the keyEvent to set
     */
    public void setKeyEvent(boolean keyEvent) {
        this.keyEvent = keyEvent;
    }

    /**
     * @return the tree
     */
    public JTree getTree() {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(JTree tree) {
        this.tree = tree;
    }

    /**
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
