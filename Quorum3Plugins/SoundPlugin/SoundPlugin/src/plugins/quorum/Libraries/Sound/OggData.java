/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 *
 * @author alleew
 */
public class OggData extends AudioData {
    
    public OggData(File file)
    {
	if (manager.noDevice) return;
	OggInputStream input = null;
	try 
        {
            input = new OggInputStream(FileToStream(file));
            ByteArrayOutputStream output = new ByteArrayOutputStream(4096);
            byte[] buffer = new byte[2048];
            while (!input.atEnd()) 
            {
                int length = input.read(buffer);
		if (length == -1)
                    break;
		output.write(buffer, 0, length);
            }
        SetUp(output.toByteArray(), input.getChannels(), input.getSampleRate());
	} 
        finally 
        {
            try
            {
                input.close();
            }
            catch (Exception e)
            {
                // Ignore any errors that occur while closing.
            }
	}
    }
    
}
