package plugins.quorum.Libraries.Accessibility;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
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
    public int numComp;
    
    public String temp;
    
    public ExtensibleMarkupLanguageParser() {
        category = new String();
        act = new String();
        component = new String();
        componentName = new String();
        position = new String();
        button = new String();
        key = new String();
        numComp = 0;
    }
    
    public static void main(String[] args) {
        
    }
    public synchronized String Parse(String toParse) {
//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//            for (int i = 0; i < stackTrace.length; i++) {
//                System.out.println(stackTrace[i]);
//            }
//        System.out.println("Made it in");
        //System.out.println(toParse);
        String[] values = new String[0];
        numComp = 0;
        try {
            //System.out.println("trying");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            //System.out.print("between");
            SAXParser parser = factory.newSAXParser();
            //System.out.println("Before handler");
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

                }

                public void endElement(String uri, String localName, String name) throws SAXException {
                    if (name.equalsIgnoreCase("Category")) {
                        category = temp;
                        numComp++;
                    }
                    if ( (name.equalsIgnoreCase("FocusType")) || (name.equalsIgnoreCase("KeyboardType")) || 
                            (name.equalsIgnoreCase("MouseType")) || (name.equalsIgnoreCase("WindowType")) || 
                            (name.equalsIgnoreCase("NotificationType")) || (name.equalsIgnoreCase("MenuType")) || 
                            (name.equalsIgnoreCase("PropertyChangeType")) ) {
                        act = temp;
                        numComp++;
                    }
                    if (name.equalsIgnoreCase("Component")) {
                        component = temp;
                        numComp++;
                    }
                    if (name.equalsIgnoreCase("Reading")) {
                        componentName = temp;
                        key = temp;
                        numComp++;
                    }
                    if (name.equalsIgnoreCase("Position")) {
                        position = temp;
                        numComp++;
                    }
                    if (name.equalsIgnoreCase("MouseButton")) {
                        button = temp;
                        numComp++;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    temp = new String(ch, start, length);
                }
            };
            
            //System.out.println("end of trying");
            parser.parse(new InputSource(new ByteArrayInputStream(toParse.getBytes("utf-8"))), handler);
            //System.out.println("after parsed");
            
        } catch (Exception e) {
            System.out.println(toParse);
            e.printStackTrace();
        }
        //System.out.println("end");
        return category;
    }  
    
    public String GetCategory() {
        return category;
    }
    
    public String GetAct() {
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
}
