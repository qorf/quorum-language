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
        this.SetTotalSize$quorum_integer(Math.round(seconds * this.samplesPerSecond) * this.channels);
    };
    
    this.GetSizeInSeconds = function()
    {
        return this.buffer.duration;
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
        var channelArray = this.buffer.getChannelData(channel);
        channelArray[index] = value;
    };
    
    this.Get$quorum_integer$quorum_integer = function(index, channel)
    {
        var channelArray = this.buffer.getChannelData(channel);
        return channelArray[i];
    };
    
    this.Copy = function()
    {
        var copy = new quorum_Libraries_Sound_AudioSamples_();
        copy.plugin_.samplesPerSecond = this.samplesPerSecond;
        copy.plugin_.channels = channels;
        
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(this.buffer.numberOfChannels, this.buffer.length, this.buffer.sampleRate);
        for (var i = 0; i < this.buffer.numberOfChannels; i++)
        {
            var copyArray = new Float32Array(this.buffer.length);
            this.buffer.copyFromChannel(copyArray, i, 0);
            copyBuffer.copyToChannel(copyArray, i, 0);
        }
        copy.plugin_.buffer = copyBuffer;
        return copy;
    };
    
    this.Copy$quorum_integer$quorum_integer = function(start, end)
    {
        if (start > end)
        {
            var temp = start;
            start = end;
            end = temp;
        }
        
        var copy = new quorum_Libraries_Sound_AudioSamples_();
        copy.plugin_.samplesPerSecond = this.samplesPerSecond;
        copy.plugin_.channels = this.channels;
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(this.buffer.numberOfChannels, this.buffer.length, this.buffer.sampleRate);
        for (var i = 0; i < this.buffer.numberOfChannels; i++)
        {
            var copyArray = this.buffer.getChannelData(i).subarray(start, end + 1);
            copyBuffer.copyToChannel(copyArray, i, 0);
        }
        copy.plugin_.buffer = copyBuffer;
        return copy;
    };
    
    this.CopyChannel$quorum_integer = function(channel)
    {
        var copy = new quorum_Libraries_Sound_AudioSamples_();
        copy.plugin_.samplesPerSecond = this.samplesPerSecond;
        copy.plugin_.channels = 1;
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(1, this.buffer.length, this.buffer.sampleRate);
        
        var copyArray = new Float32Array(this.buffer.length);
        this.buffer.copyFromChannel(copyArray, channel, 0);
        copyBuffer.copyToChannel(copyArray, 0, 0);
        
        copy.plugin_.buffer = copyBuffer;
        return copy;
    };
    
    this.CopyChannel$quorum_integer$quorum_integer$quorum_integer = function(channel, start, end)
    {
        if (start > end)
        {
            var temp = start;
            start = end;
            end = temp;
        }
        
        var copy = new quorum_Libraries_Sound_AudioSamples_();
        copy.plugin_.samplesPerSecond = this.samplesPerSecond;
        copy.plugin_.channels = this.channels;
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(1, this.buffer.length, this.buffer.sampleRate);
        
        var copyArray = this.buffer.getChannelData(channel).subarray(start, end + 1);
        copyBuffer.copyToChannel(copyArray, 0, 0);
        
        copy.plugin_.buffer = copyBuffer;
        return copy;
    };
    
    this.Load$quorum_Libraries_System_File = function(file)
    {
        var request = new XMLHttpRequest();
        request.open('GET', file.GetPath(), false);
        request.responseType = 'arraybuffer';
        
        var samples = this;
        
        request.onload = function()
        {
            var audioData = request.response;
            
            plugins_quorum_Libraries_Sound_Audio_.audioContext.decodeAudioData(audioData, function(buffer)
            {
                samples.buffer = buffer;
            },
            function(e)
            {
                console.log("Error decoding audio data: " + e.err);
            });
        };
        request.send();
    };
}


