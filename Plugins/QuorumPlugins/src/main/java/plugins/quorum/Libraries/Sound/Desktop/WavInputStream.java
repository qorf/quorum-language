/*
 * This class is adapted from the LibGDX java libraries under the Apache
 * License. You may find LibGDX here: http://libgdx.badlogicgames.com
 *
 * A copy of the original license is below.
 */

/*******************************************************************************
 * Copyright 2011 Mario Zechner <badlogicgames@gmail.com>,
 *                Nathan Sweet <nathan.sweet@gmail.com> 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package plugins.quorum.Libraries.Sound.Desktop;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import plugins.quorum.Libraries.Sound.Desktop.AudioData;
import plugins.quorum.Libraries.Sound.Data;

/**
 *
 * @author alleew
 */
public class WavInputStream extends FilterInputStream {
    public int channels, sampleRate, dataRemaining;
    public String file;
    
    public WavInputStream (File file) 
    {
        super(Data.FileToStream(file));
        this.file = file.getAbsolutePath();
        
	Initialize();
    }
    
    public WavInputStream(InputStream stream, String filePath)
    {
        super(stream);
        this.file = filePath;
        
        Initialize();
    }

    private void Initialize()
    {
        try 
        {
            if (read() != 'R' || read() != 'I' || read() != 'F' || read() != 'F')
                throw new IOException("RIFF header not found: " + file);

            skipFully(4);

            if (read() != 'W' || read() != 'A' || read() != 'V' || read() != 'E')
                throw new IOException("Invalid wave file header: " + file);

            int fmtChunkLength = seekToChunk('f', 'm', 't', ' ');

            int type = read() & 0xff | (read() & 0xff) << 8;
            if (type != 1)
               throw new IOException("WAV files must be PCM: " + type);

            channels = read() & 0xff | (read() & 0xff) << 8;
            if (channels != 1 && channels != 2)
                throw new IOException("WAV files must have 1 or 2 channels: " + channels);

            sampleRate = read() & 0xff | (read() & 0xff) << 8 | (read() & 0xff) << 16 | (read() & 0xff) << 24;

            skipFully(6);

            int bitsPerSample = read() & 0xff | (read() & 0xff) << 8;
            if (bitsPerSample != 16)
                throw new IOException("WAV files must have 16 bits per sample: " + bitsPerSample);

            skipFully(fmtChunkLength - 16);

            dataRemaining = seekToChunk('d', 'a', 't', 'a');
        } 
        catch (Throwable ex) 
        {
            try
            { 
                this.close();
            }
            catch (Throwable e)
            {
                // Ignore any exceptions while closing.
            }
            throw new RuntimeException("Error reading WAV file: " + file + ": " + ex.getMessage(), ex);
	    }
    }

    private int seekToChunk (char c1, char c2, char c3, char c4) throws IOException 
    {
	while (true) 
        {
            boolean found = read() == c1;
            found &= read() == c2;
            found &= read() == c3;
            found &= read() == c4;
            int chunkLength = read() & 0xff | (read() & 0xff) << 8 | (read() & 0xff) << 16 | (read() & 0xff) << 24;
            
            if (chunkLength == -1)
                throw new IOException("Chunk not found: " + c1 + c2 + c3 + c4);
            if (found)
                return chunkLength;
            
            skipFully(chunkLength);
	}
    }

    private void skipFully (int count) throws IOException 
    {
	while (count > 0) 
        {
            long skipped = in.skip(count);
            if (skipped <= 0)
                throw new EOFException("Unable to skip.");
            count -= skipped;
	}
    }

    @Override
    public int read (byte[] buffer) throws IOException 
    {
	if (dataRemaining == 0) 
            return -1;
	int length = Math.min(super.read(buffer), dataRemaining);
	if (length == -1) 
            return -1;
	dataRemaining -= length;
	return length;
    }
	
}
