/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author alleew
 */
public class WavData extends AudioData
{
    
    public WavData(File file)
    {
        if (manager.noDevice)
            return;
        
        WavInputStream input = null;
	try 
        {
            input = new WavInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream(input.dataRemaining);
            
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) 
            {
		output.write(buffer, 0, bytesRead);
            }
            
            SetUp(output.toByteArray(), input.channels, input.sampleRate);
	}
        catch (IOException ex) 
        {
            throw new RuntimeException("Error reading WAV file: " + file, ex);
	} 
        finally 
        {
            try
            {
                input.close();
            }
            catch (Throwable ex)
            {
                // Do nothing -- exception is ignored.
            }
	}
    }
    
}
