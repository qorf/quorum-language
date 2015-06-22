/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author alleew
 */
public class WavStreamingData extends StreamingData {
    private WavInputStream input;

    public WavStreamingData (File file) 
    {
	super(file);
	input = new WavInputStream(file);
	if (manager.noDevice)
            return;
	SetUp(input.channels, input.sampleRate);
    }

    public int Read (byte[] buffer) 
    {
	if (input == null) 
        {
            input = new WavInputStream(file);
            SetUp(input.channels, input.sampleRate);
	}
			
        try 
        {
            return input.read(buffer);
	}
        catch (IOException ex) 
        {
            throw new RuntimeException("Error reading WAV file: " + file, ex);
	}
    }

    public void Reset () 
    {
        try
        {
            input.close();
        }
        catch (Exception e)
        {
            // Ignore any exceptions caught while closing the input stream.
        }
        
	input = null;
    }
}
