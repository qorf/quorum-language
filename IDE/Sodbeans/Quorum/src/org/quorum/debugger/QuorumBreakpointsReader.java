/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import java.net.MalformedURLException;
import java.net.URL;
import org.netbeans.api.debugger.Properties;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;

/**
 * This Properties.Reader subclass allows one to write, and later retrieve
 * breakpoints from a debugger session.
 * 
 * A majority of this code was adopted verbatim from the PHP debugger module
 * in NetBeans. 
 * 
 * @author jeff
 */
public class QuorumBreakpointsReader implements Properties.Reader {
    private static final String LINE_NUMBER    = "lineNumber";
    private static final String URL             = "url";
    private static final String ENABED          = "enabled";
    private static final String GROUP_NAME = "groupName"; 
    
    @Override
    public String[] getSupportedClassNames() {
        return new String[] {
            QuorumBreakpoint.class.getName()
        };
    }

    @Override
    public Object read(String typeID, Properties properties) {
        if (typeID.equals(QuorumBreakpoint.class.getName())) {
            String url = properties.getString(URL, null);
            Line line = getLine(url, properties
                    .getInt(LINE_NUMBER, 1));

            if (line == null) {
                return null;
            }
            QuorumBreakpoint breakpoint = new QuorumBreakpoint(line, getFileObject(url));
            
            if (!properties.getBoolean(ENABED, true)) {
                breakpoint.disable();
            }
            
            breakpoint.setGroupName(properties.getString(GROUP_NAME, ""));
            return breakpoint;
        } else {
            return null;
        }        
    }

    @Override
    public void write(Object object, Properties properties) {
        if (object instanceof QuorumBreakpoint) {
            QuorumBreakpoint breakpoint = (QuorumBreakpoint) object;
            FileObject fileObject = breakpoint.getLine().getLookup().lookup(
                    FileObject.class);

            properties.setString(URL, fileObject.toURL().toString());
            properties.setInt(LINE_NUMBER, breakpoint.getLine()
                    .getLineNumber());
            properties.setBoolean(ENABED, breakpoint.isEnabled());
            properties.setString(GROUP_NAME, breakpoint.getGroupName());
        }
    }
    
    private Line getLine(String url, int lineNumber) {
        FileObject file = getFileObject(url);
        if ( file == null ){
            return null;
        }

        DataObject dataObject = null;
        try {
            dataObject = DataObject.find(file);
        } catch (DataObjectNotFoundException ex) {
            return null;
        }

        if (dataObject == null) {
            return null;
        }

        LineCookie lineCookie = dataObject.getLookup().lookup(LineCookie.class);
        if (lineCookie == null) {
            return null;
        }

        Line.Set ls = lineCookie.getLineSet();
        if (ls == null) {
            return null;
        }

        try {
            return ls.getCurrent(lineNumber);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private FileObject getFileObject( String url ) {
        FileObject file;
        try {
            file = URLMapper.findFileObject(new URL(url));
        } catch (MalformedURLException e) {
            return null;
        }

        return file;
    }
}
