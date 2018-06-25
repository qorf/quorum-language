/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Desktop;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public class WavData extends AudioData
{
    // Load the data from a raw buffer, instead of a file.
    public WavData(AudioSamples_ buffer)
    {
        SetUp(buffer);
    }
    
    public WavData(File file)
    {
        WavInputStream input = new WavInputStream(file);
        // GetBytes also sets input.channels and input.sampleRate via side effect.
        byte[] output = GetBytes(input);
        if (output == null)
            return;
        SetUp(output, input.channels, input.sampleRate);
    }
    
    public WavData(InputStream stream, String filePath)
    {
        WavInputStream input = new WavInputStream(stream, filePath);
        // GetBytes also sets input.channels and input.sampleRate via side effect.
        byte[] output = GetBytes(input);
        if (output == null)
            return;
        SetUp(output, input.channels, input.sampleRate);
    }
    
    public static byte[] GetBytes(WavInputStream input)
    {
        if (MANAGER.noDevice)
            return null;
        
        ByteArrayOutputStream output = new ByteArrayOutputStream(input.dataRemaining);
        
	try 
        {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) 
            {
		output.write(buffer, 0, bytesRead);
            }
	}
        catch (IOException ex) 
        {
            throw new RuntimeException("Error reading WAV file: " + input.file, ex);
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
        
        return output.toByteArray();
    }
    
}
