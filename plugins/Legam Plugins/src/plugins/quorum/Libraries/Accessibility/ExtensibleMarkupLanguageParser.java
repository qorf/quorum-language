package plugins.quorum.Libraries.Accessibility;

import java.util.logging.Logger;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Nicole Blumhorst
 */
public class ExtensibleMarkupLanguageParser {
    public Object $me;
    
    public String category;
    public String act;
    public String component;
    public String componentName;
    public String position;
    public String button;
    public String key;
    public String shortcut;
    //public int childCount;
    public List<AccessibleChild> children;
    public AccessibleChild child;
    public int numComp;
    public int childNum;
    //public String temp;
    
    public ExtensibleMarkupLanguageParser() {
        category = new String();
        act = new String();
        component = new String();
        componentName = new String();
        position = new String();
        button = new String();
        key = new String();
        shortcut = new String();
        numComp = 0;
        childNum = 0;
        children = new ArrayList<AccessibleChild>();
        child = new AccessibleChild();
    }
    
    public static void main(String[] args) {
        
    }
    public synchronized String Parse(String toParse) {
        final String xmlMethod = toParse;
        childNum = 0;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                String xml = xmlMethod;
                Stack<String> stack = new Stack<String>();
                private StringBuilder chars = new StringBuilder();
                boolean illegal = false;
                @Override
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    int a = 0;
                    chars.setLength(0);
                }

                @Override
                public void endElement(String uri, String localName, String name) throws SAXException {
                    String value = stack.pop().trim();
                    if (name.equalsIgnoreCase("Category")) {
                        category = ReplaceSpecialCharacters(value);
                    } else if ( (name.equalsIgnoreCase("FocusType"))
                            || (name.equalsIgnoreCase("KeyboardType")) 
                            ||  (name.equalsIgnoreCase("MouseType")) 
                            || (name.equalsIgnoreCase("WindowType")) 
                            || (name.equalsIgnoreCase("NotificationType")) 
                            || (name.equalsIgnoreCase("MenuType")) 
                            ||  (name.equalsIgnoreCase("PropertyChangeType")
                            ) 
                            ) {
                        act = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("Component")) {
                        component = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("Reading")) {
                        componentName = ReplaceSpecialCharacters(value);
                        key = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("Position")) {
                        position = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("MouseButton")) {
                        button = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("Shortcut")) {
                        shortcut = ReplaceSpecialCharacters(value);
                    } else if (name.equalsIgnoreCase("ChildCount")) {
                        childNum = Integer.parseInt(value);
                    } else if (name.equalsIgnoreCase("Child")) {
                        children.add(child);
                        child = new AccessibleChild();
                    } else if (name.equalsIgnoreCase("ChildName")) {
                        child.setName(ReplaceSpecialCharacters(value));
                    } else if (name.equalsIgnoreCase("ChildComponent")) {
                        child.setComponent(ReplaceSpecialCharacters(value));
                    } else if (name.equalsIgnoreCase("ChildShortcut")) {
                        child.setShortcut(ReplaceSpecialCharacters(value));
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    String temp = new String(ch, start, length);
                    temp = temp.trim();
                    chars.append(temp);
                    stack.push(chars.toString());
                }
            };
            parser.parse(new InputSource(new ByteArrayInputStream(toParse.getBytes("utf-8"))), handler);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ExtensibleMarkupLanguageParser.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
        return category;
    }  
    
    private String ReplaceSpecialCharacters(String xml) {
        String newXML = xml;
        newXML = newXML.replace("&lt;", "<");
        newXML = newXML.replace("&gt;", ">");
        newXML = newXML.replace("&quot;", "\"");
        newXML = newXML.replace("&apos;", "\'");
        newXML = newXML.replace("&amp;", "&");
        return newXML;
    }
    
    public String GetCategory() {
        return category;
    }
    
    public String GetAction() {
        return act;
    }

    public String GetComponent() {
        return component;
    }

    public String GetComponentName() {
        return componentName;
    }

    public String GetPosition() {
        return position;
    }

    public String GetButton() {
        return button;
    }
    
    public String GetKey() {
        return key;
    }
    
    public String GetShortcut() {
        return shortcut;
    }

    public int GetChildCount() {
        return childNum;
    }
    
    public String GetChildName(int childNum) {
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            return children.get(childNum).getName();
        }
        else
            return "invalid child";
    }
    
    public String GetChildComponent(int childNum) {
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            return children.get(childNum).getComponent();
        }
        else
            return "invalid child";
    }
    
    public String GetChildShortcut(int childNum) {
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            return children.get(childNum).getShortcut();
        }
        else
            return "invalid child";
    }
}
