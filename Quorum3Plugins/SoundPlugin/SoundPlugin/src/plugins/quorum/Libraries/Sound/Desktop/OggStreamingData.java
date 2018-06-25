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
public class OggStreamingData extends StreamingData {
    private OggInputStream input;
    private OggInputStream previousInput;

    public OggStreamingData(File file) 
    {
	super(file);
	if (MANAGER.noDevice) 
            return;
	input = new OggInputStream(FileToStream(file));
	SetUp(input.getChannels(), input.getSampleRate());
    }
    
    public OggStreamingData(InputStream stream, String filePath)
    {
	super(filePath);
	if (MANAGER.noDevice) 
            return;
	input = new OggInputStream(stream);
	SetUp(input.getChannels(), input.getSampleRate());
    }

    @Override
    public int Read (byte[] buffer) 
    {
	if (input == null) 
        {
            input = new OggInputStream(FileToStream(file), previousInput);
            SetUp(input.getChannels(), input.getSampleRate());
            previousInput = null; // release this reference
	}
	return input.read(buffer);
    }

    @Override
    public void Reset () 
    {
        try
        {
            input.close();
        }
        catch (Exception e)
        {
            //Ignore any errors that occur while closing the input stream.
        }
	previousInput = null;
	input = null;
    }

    @Override
    protected void Loop () 
    {
	try
        {
            input.close();
        }
        catch (Exception e)
        {
            //Ignore any errors that occur while closing the input stream.
        }
	previousInput = input;
	input = null;
    }
}
