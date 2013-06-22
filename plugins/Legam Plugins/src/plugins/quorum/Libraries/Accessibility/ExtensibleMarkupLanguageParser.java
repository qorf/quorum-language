package plugins.quorum.Libraries.Accessibility;

import java.util.logging.Logger;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
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
    public List<List<String>> children;
    public List<String> child;
    public int numComp;
    public int childNum;
    public String temp;
    
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
        children = new ArrayList<List<String>>();
        child = new ArrayList<String>();
    }
    
    public static void main(String[] args) {
        
    }
    public synchronized String Parse(String toParse) {
        childNum = 0;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

                }

                public void endElement(String uri, String localName, String name) throws SAXException {
                    if (name.equalsIgnoreCase("Category")) {
                        category = temp;
                    }
                    if ( (name.equalsIgnoreCase("FocusType")) || (name.equalsIgnoreCase("KeyboardType")) || 
                            (name.equalsIgnoreCase("MouseType")) || (name.equalsIgnoreCase("WindowType")) || 
                            (name.equalsIgnoreCase("NotificationType")) || (name.equalsIgnoreCase("MenuType")) || 
                            (name.equalsIgnoreCase("PropertyChangeType")) ) {
                        act = temp;
                    }
                    if (name.equalsIgnoreCase("Component")) {
                        component = temp;
                    }
                    if (name.equalsIgnoreCase("Reading")) {
                        componentName = temp;
                        key = temp;
                    }
                    if (name.equalsIgnoreCase("Position")) {
                        position = temp;
                    }
                    if (name.equalsIgnoreCase("MouseButton")) {
                        button = temp;
                    }
                    if (name.equalsIgnoreCase("Shortcut")) {
                        shortcut = temp;
                    }
                    if (name.equalsIgnoreCase("ChildCount")) {
                        childNum = Integer.parseInt(temp);
                        System.out.println("temp = " + temp + " " + category + " " + act);
                    }
                    if (name.equalsIgnoreCase("Child"))
                    {
                        children.add(child);
                        child.clear();
                    }
                    if (name.equalsIgnoreCase("ChildName")) {
                        child.add(temp);
                    }
                    if (name.equalsIgnoreCase("ChildComponent")) {
                        child.add(temp);
                    }
                    if (name.equalsIgnoreCase("ChildShortcut")) {
                        child.add(temp);
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    temp = new String(ch, start, length);
                }
            };
            
            parser.parse(new InputSource(new ByteArrayInputStream(toParse.getBytes("utf-8"))), handler);
            
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ExtensibleMarkupLanguageParser.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
        //System.out.println("java: N:" + componentName + " S:" + shortcut + " C:" + childNum);
        return category;
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
        //System.out.println("get child name");
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            String[] a = new String[0];
            return children.get(childNum - 1).get(0);
        }
        else
            return null;
    }
    
    public String GetChildComponent(int childNum) {
        //System.out.println("get child component");
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            String[] a = new String[0];
            return children.get(childNum - 1).get(1);
        }
        else
            return null;
    }
    
    public String GetChildShortcut(int childNum) {
        //System.out.println("get child shortcut");
        if ( (childNum >= 0) && (childNum < children.size()) )
        {
            String[] a = new String[0];
            return children.get(childNum - 1).get(2);
        }
        else
            return null;
    }
}
