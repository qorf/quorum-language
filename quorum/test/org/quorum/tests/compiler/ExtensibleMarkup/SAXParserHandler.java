
package org.quorum.tests.compiler.ExtensibleMarkup;

import java.util.ArrayList;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
    private StringBuilder chars = new StringBuilder();
    ArrayList<String> result = new ArrayList<String>();
    
    @Override
    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
        CheckForValue(); // check to see if there is a value that needs to be printed before printing a new start element
        StringBuilder start = new StringBuilder();
        start.append("Start Element: " + qName);

        int count = attributes.getLength();
        for (int i= 0; i <= count - 1; i++)
        {
            start.append(" | name=" + attributes.getQName(i) + ", value=" + attributes.getValue(i));
        }
        result.add(start.toString());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        CheckForValue(); // check to see if there is a value that needs to be printed before printing the end element
        result.add("End Element: " + qName );
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {                    
        String temp = new String(ch, start, length);
        chars.append(temp); 
    }
    
    public ArrayList<String> GetResult() {
        return result;
    }
    
    private void CheckForValue() {
        if (chars.length() > 0)
        {
            String value = chars.toString();
            if (!value.trim().isEmpty()) {
                String[] values = value.trim().split("\n");
                for (int i = 0; i < values.length; i++)
                {
                    if (i == 0) {
                        result.add("Value: " + values[i].trim());
                    }
                    else
                        result.add(values[i].trim());
                }
            }
        }
        chars.setLength(0);
    }
}
