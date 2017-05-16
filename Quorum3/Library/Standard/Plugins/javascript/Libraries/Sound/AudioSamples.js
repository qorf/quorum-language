function plugins_quorum_Libraries_Sound_AudioSamples_(quorumSamples)
{
    var me_ = quorumSamples;
    
    this.buffer = null;
    this.samplesPerSecond = 44100;
    this.channels = 1;
    
    // Variables to handle asynchronous audio file loading.
    var loading = false;
    var onloadQueue = [];
    /*
     * The copyQueue contains objects with the following properties:
     * samples  : The AudioSamples object to copy information to.
     * channel  : What channel to copy, or -1 to copy all channels.
     * start    : The starting index to copy, or -1 to copy all elements.
     * end      : The last index to copy, or -1 to copy all elements.
     */
    var copyQueue = [];
    
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
        if (this.buffer == null)
            return 0;
        
        return this.buffer.duration;
    };
    
    this.SetTotalSize$quorum_integer = function(samples)
    {
        this.buffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(this.channels, Math.round(samples / this.channels), this.samplesPerSecond);
    };
    
    this.GetTotalSize = function()
    {
        if (this.buffer == null)
            return 0;
        
        return this.buffer.length * this.channels;
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
        
        if (loading)
            copyQueue.push({samples: copy, channel: -1, start: -1, end: -1});
        else
            this.CopyToSamples(copy, -1, -1);
        
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
        
        if (loading)
            copyQueue.push({samples: copy, channel: -1, start: start, end: end});
        else
            this.CopyToSamples(copy, start, end);
        
        return copy;
    };
    
    this.CopyToSamples = function(samples, start, end)
    {
        if (start === -1)
            start = 0;
        if (end === -1)
            end = this.buffer.length;
        
        samples.plugin_.samplesPerSecond = this.samplesPerSecond;
        samples.plugin_.channels = this.channels;
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(this.buffer.numberOfChannels, (end + 1 - start), this.buffer.sampleRate);
        for (var i = 0; i < this.buffer.numberOfChannels; i++)
        {
            var copyArray = this.buffer.getChannelData(i).subarray(start, end + 1);
            copyBuffer.copyToChannel(copyArray, i, 0);
        }
        samples.plugin_.buffer = copyBuffer;
        return samples;
    };
    
    this.CopyChannel$quorum_integer = function(channel)
    {
        var copy = new quorum_Libraries_Sound_AudioSamples_();
        
        if (loading)
            copyQueue.push({samples: copy, channel: channel, start: -1, end: -1});
        else
            this.CopyToSamples(copy, channel, -1, -1);
        
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
        
        if (loading)
            copyQueue.push({samples: copy, channel: channel, start: start, end: end});
        else
            this.CopyToSamples(copy, channel, start, end);
        
        return copy;
    };
    
    this.CopyChannelToSamples = function(samples, channel, start, end)
    {
        if (start === -1)
            start = 0;
        if (end === -1)
            end = this.buffer.length;
        
        samples.plugin_.samplesPerSecond = this.samplesPerSecond;
        samples.plugin_.channels = this.channels;
        var copyBuffer = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBuffer(1, (end + 1 - start), this.buffer.sampleRate);
        
        var copyArray = this.buffer.getChannelData(channel).subarray(start, end + 1);
        copyBuffer.copyToChannel(copyArray, 0, 0);
        
        samples.plugin_.buffer = copyBuffer;
        return samples;
    };
    
    this.Load$quorum_Libraries_System_File = function(file)
    {
        var request = new XMLHttpRequest();
        request.open('GET', file.GetPath(), true);
        request.responseType = 'arraybuffer';
        
        var samples = this;
        var loadAudio = this.FinishQueuedRequests.bind(this);
        
        request.onload = function()
        {
            var audioData = request.response;
            
            plugins_quorum_Libraries_Sound_Audio_.audioContext.decodeAudioData(audioData, function(buffer)
            {
                samples.buffer = buffer;
                loading = false;
                loadAudio();
            },
            function(e)
            {
                console.log("Error decoding audio data: " + e.err);
            });
        };
        loading = true;
        request.send();
    };
    
    this.IsLoading = function()
    {
        return loading;
    };
    
    this.RequestAudioLoad = function(audio)
    {
        if (this.IsLoading())
            onloadQueue.push(audio);
        else
            audio.Load$quorum_Libraries_Sound_AudioSamples(me_);
    };
    
    this.FinishQueuedRequests = function()
    {
        for (var i = 0; i < onloadQueue.length; i++)
        {
            onloadQueue[i].Load$quorum_Libraries_Sound_AudioSamples(me_);
        }
        
        onloadQueue = [];
        
        for (var i = 0; i < copyQueue.length; i++)
        {
            var request = copyQueue[i];
            if (request.channel === -1)
                this.CopyToSamples(request.samples, request.start, request.end);
            else
                this.CopyChannelToSamples(request.samples, request.channel, request.start, request.end);
        }
        
        copyQueue = [];
    };
}