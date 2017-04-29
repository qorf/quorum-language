function plugins_quorum_Libraries_Sound_AudioSamples_()
{
    this.buffer = null;
    this.samples = 0;
    this.samplesPerSecond = 44100;
    this.channels = 1;
    
    // Ensure Audio is initialized.
    if (!plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio)
    {
        var initAudio = new plugins_quorum_Libraries_Sound_Audio_();
        initAudio = null;
    }
    
    // If the Web Audio API isn't supported, all methods are replaced with empty
    // methods that do nothing (but won't crash the program).
    if (plugins_quorum_Libraries_Sound_Audio_.audioContext === undefined)
    {
        this.SetChannels$quorum_integer = function(){};
        this.GetChannels = function(){return 0;};
        this.SetSamplesPerSecond$quorum_integer = function(){};
        this.GetSamplesPerSecond = function(){return 0;};
        this.SetSizeInSeconds$quorum_number = function(){};
        this.GetSizeInSeconds = function(){return 0;};
        this.SetTotalSize$quorum_integer = function(){};
        this.GetTotalSize = function(){return 0;};
        this.Set$quorum_integer$quorum_number$quorum_integer = function(){};
        this.Get$quorum_integer$quorum_integer = function(){return 0;};
        this.Copy = function(){return null;};
        this.Copy$quorum_integer$quorum_integer = function(){return null;};
        this.CopyChannel$quorum_integer = function(){return null;};
        this.CopyChannel$quorum_integer$quorum_integer$quorum_integer = function(){return null;};
        this.Load$quorum_Libraries_System_File = function(file){};
        
        return;
    }
    
    this.SetChannels$quorum_integer = function(channels)
    {
        this.channels = channels;
    };
    
    this.GetChannels = function()
    {
        return this.channels;
    };
    
    this.SetSamplesPerSecond$quorum_integer = function(samples)
    {
        this.samplesPerSecond = samples;
    };
    
    this.GetSamplesPerSecond = function()
    {
        return this.samplesPerSecond;
    };

    this.SetSizeInSeconds$quorum_number = function(seconds)
    {
        this.SetTotalSize(Math.round(seconds * this.samplesPerSecond) * this.channels);
    };
    
    this.GetSizeInSeconds = function()
    {
        return (this.buffer.length / this.samplesPerSecond / channels);
    };
    
    this.SetTotalSize$quorum_integer = function(samples)
    {
        this.buffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(this.channels, Math.round(samples / this.channels), this.samplesPerSecond);
    };
    
    this.GetTotalSize = function()
    {
        return this.buffer.length;
    };
    
    this.Set$quorum_integer$quorum_number$quorum_integer = function(index, value, channel)
    {
        
    };
    
    /*
    public void Set(int index, double value, int channel)
    {
        buffer[index * channels + channel] = (short)(value * MAX_SHORT);
    }
    
    public double Get(int index, int channel)
    {
        return buffer[index * channels + channel] / (double)MAX_SHORT;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ Copy()
    {
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = buffer.clone();
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ Copy(int start, int end)
    {
        if (start > end)
        {
            int temp = start;
            start = end;
            end = temp;
        }
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = new short[(end - start + 1) * channels];
        System.arraycopy(buffer, start * channels, array, 0, (end - start + 1) * channels);
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ CopyChannel(int channel)
    {
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = 1;
        short[] array = new short[buffer.length / channels];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = buffer[i * channels + channel];
        }
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ CopyChannel(int channel, int start, int end)
    {
        if (start > end)
        {
            int temp = start;
            start = end;
            end = temp;
        }
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = 1;
        short[] array = new short[(end - start + 1)];
        int channelStart = (channels * start) + channel;
        for (int i = 0; i < array.length; i++)
        {
            array[i] = buffer[channelStart + i * channels];
        }
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public void Load(quorum.Libraries.System.File_ quorumFile)
    {
        File file = new File(quorumFile.GetAbsolutePath());
        String fileName = file.getName().toLowerCase();
        byte[] bytes;
        int channels, sampleRate;
        
        if (fileName.endsWith(".wav"))
        {
            WavInputStream input = new WavInputStream(file);
            bytes = WavData.GetBytes(input);
            channels = input.channels;
            sampleRate = input.sampleRate;
        }
        else if (fileName.endsWith(".ogg"))
        {
            OggInputStream input = new OggInputStream(file);
            bytes = OggData.GetBytes(input);
            channels = input.getChannels();
            sampleRate = input.getSampleRate();
        }
        else 
            throw new RuntimeException("Can't load file " + file.getAbsolutePath() + " because the file extension is unsupported!");
        
        ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
	buffer.order(ByteOrder.nativeOrder());
	buffer.put(bytes, 0, bytes.length);
	buffer.flip();
        
        ShortBuffer b = buffer.asShortBuffer();
        this.buffer = new short[b.limit()];
        b.get(this.buffer);
        
        this.channels = channels;
        samplesPerSecond = sampleRate;
    }
     */
}


