/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Desktop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 *
 * @author alleew
 */
public class OggData extends AudioData {
    
    public OggData(File file)
    {
	OggInputStream input = new OggInputStream(FileToStream(file));
        // GetBytes also sets input.channels and input.sampleRate via side effect.
	byte[] output = GetBytes(input);
        if (output == null)
            return;
        SetUp(output, input.getChannels(), input.getSampleRate());
    }
    
    public OggData(InputStream stream, String filePath)
    {
	OggInputStream input = new OggInputStream(stream);
        // GetBytes also sets input.channels and input.sampleRate via side effect.
	byte[] output = GetBytes(input);
        if (output == null)
            return;
        SetUp(output, input.getChannels(), input.getSampleRate());
    }
    
    public static byte[] GetBytes(OggInputStream input)
    {
        if (MANAGER.noDevice)
            return null;
        ByteArrayOutputStream output = new ByteArrayOutputStream(4096);
	try 
        {
            byte[] buffer = new byte[2048];
            while (!input.atEnd()) 
            {
                int length = input.read(buffer);
		if (length == -1)
                    break;
		output.write(buffer, 0, length);
            }
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
        return output.toByteArray();
    }
}
