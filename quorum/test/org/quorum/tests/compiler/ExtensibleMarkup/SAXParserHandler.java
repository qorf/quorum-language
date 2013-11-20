
package org.quorum.tests.compiler.ExtensibleMarkup;

import java.util.ArrayList;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
//    Stack<String> stack = new Stack<String>();
//    private StringBuilder chars = new StringBuilder();
    ArrayList<String> result = new ArrayList<String>();
    
    @Override
    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
//        chars.setLength(0);
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

        result.add("End Element: " + qName );
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
//        String temp = new String(ch, start, length);
//        temp = temp.trim();
//        if (!temp.isEmpty())
//        {
//            chars.append(temp);
//            stack.push(chars.toString());
//        }
                    
        String value = new String(ch, start, length);
        if (!value.trim().isEmpty())
            result.add("Value: " + value.trim());
    }
    
    public ArrayList<String> GetResult()
    {
        return result;
    }
}
