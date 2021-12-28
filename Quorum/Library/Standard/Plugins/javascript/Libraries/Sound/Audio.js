function plugins_quorum_Libraries_Sound_Audio_()
{
    if (!plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio)
    {
        plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio = true;
        
        try
        {
            plugins_quorum_Libraries_Sound_Audio_.audioContext = new AudioContext;
        }
        catch(error)
        {
            try
            {
                plugins_quorum_Libraries_Sound_Audio_.audioContext = new webkitAudioContext;
            }
            catch(e)
            {
                plugins_quorum_Libraries_Sound_Audio_.audioContext = undefined;
            }
        }
        
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext === undefined)
            alert("The Web Audio API couldn't be initialized. Your browser may not support it.");
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener = plugins_quorum_Libraries_Sound_Audio_.audioContext.listener;
            if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();            
        }
        
        plugins_quorum_Libraries_Sound_Audio_.defaultReferenceDistance = 1.0;
        plugins_quorum_Libraries_Sound_Audio_.defaultRolloff = 1.0;
    }

    if (plugins_quorum_Libraries_Sound_Audio_.audioContext === undefined)
    {
        /*
         * If the Web Audio API isn't supported, instead of loading the actual
         * functions we instead replace them with a series of empty functions.
         * Attempting to use these functions in an unsupported environment will
         * then do nothing instead of crashing.
         */
        this.Load$quorum_Libraries_System_File = function(){};
        this.LoadToStream$quorum_Libraries_System_File = function(){};
        this.Play = function(){};
        this.Stop = function(){};
        this.Pause = function(){};
        this.Resume = function(){};
        this.IsPlaying = function(){};
        this.EnableLooping = function(){};
        this.DisableLooping = function(){};
        this.IsLoopingEnabled = function(){};
        this.Dispose = function(){};
        this.SetPitch$quorum_number = function(){};
        this.GetPitch = function(){};
        this.SetVolume$quorum_number = function(){};
        this.GetVolume = function(){};
        this.SetBalance$quorum_number = function(){};
        this.GetBalance = function(){};
        this.Stream = function(){};
        this.IsStreaming = function(){};
        this.SetFade$quorum_number = function(){};
        this.GetFade = function(){};
        this.SetX$quorum_number = function(){};
        this.SetY$quorum_number = function(){};
        this.SetZ$quorum_number = function(){};
        this.SetPosition$quorum_number$quorum_number$quorum_number = function(){};
        this.GetX = function(){};
        this.GetY = function(){};
        this.GetZ = function(){};
        this.EnableDoppler = function(){};
        this.DisableDoppler = function(){};
        this.IsDopplerEnabled = function(){};
        this.SetVelocityX$quorum_number = function(){};
        this.SetVelocityY$quorum_number = function(){};
        this.SetVelocityZ$quorum_number = function(){};
        this.SetVelocity$quorum_number$quorum_number$quorum_number = function(){};
        this.GetVelocityX = function(){};
        this.GetVelocityY = function(){};
        this.GetVelocityZ = function(){};
        this.SetListenerX$quorum_number = function(){};
        this.SetListenerY$quorum_number = function(){};
        this.SetListenerZ$quorum_number = function(){};
        this.SetListenerPosition$quorum_number$quorum_number$quorum_number = function(){};
        this.SetListenerVelocityX$quorum_number = function(){};
        this.SetListenerVelocityY$quorum_number = function(){};
        this.SetListenerVelocityZ$quorum_number = function(){};
        this.SetListenerVelocity$quorum_number$quorum_number$quorum_number = function(){};
        this.EnableListenerDoppler = function(){};
        this.DisableListenerDoppler = function(){};
        this.IsListenerDopplerEnabled = function(){};
        this.GetListenerX = function(){};
        this.GetListenerY = function(){};
        this.GetListenerZ = function(){};
        this.GetListenerVelocityX = function(){};
        this.GetListenerVelocityY = function(){};
        this.GetListenerVelocityZ = function(){};
        this.SetListenerDirection$quorum_number$quorum_number$quorum_number = function(){};
        this.GetListenerDirectionX = function(){};
        this.GetListenerDirectionY = function(){};
        this.GetListenerDirectionZ = function(){};
        this.SetListenerUp$quorum_number$quorum_number$quorum_number = function(){};
        this.GetListenerUpX = function(){};
        this.GetListenerUpY = function(){};
        this.GetListenerUpZ = function(){};
        this.SetRotation$quorum_number = function(){};
        this.GetRotation = function(){};
        this.Rotate$quorum_number = function(){};
        
        this.Load$quorum_Libraries_Sound_AudioSamples = function(){};
        this.LoadToStream$quorum_Libraries_Sound_AudioSamples = function(){};
        this.SetMaximumVolumeDistance$quorum_number = function(){};
        this.SetRolloffFactor$quorum_number = function(){};
        this.SetDefaultMaximumVolumeDistance$quorum_number = function(){};
        this.SetDefaultRolloffFactor$quorum_number = function(){};
        this.GetMaximumVolumeDistance = function(){};
        this.GetRolloffFactor = function(){};
        this.GetDefaultMaximumVolumeDistance = function(){};
        this.GetDefaultRolloffFactor = function(){};
        this.AddToQueue$quorum_Libraries_Sound_AudioSamples = function(){};
        this.RemoveFromQueue$quorum_Libraries_Sound_AudioSamples = function(){};
        
        return;
    }
    
    var soundBuffer;
    var source;
    var panner;
    var gainNode;
    var loading = false;
    var loaded = false;
    var looping = false;
    var onloadQueue = [];
   
    var pitch = 1.0;
    var gain = 1.0;
    var rotation = 0;
    var pan = 0;
    var fade = 0;
    
    var startTime = 0;
    var pauseTime = 0;
    
    /*
     * An array containing samples ready to be scheduled for play by the Web
     * Audio API. Samples are queued by the Stream() action.
     */
    var queuedSamples = [];
    
    /* An array containing objects with several fields: sample (The AudioSample) and source (The source node). 
     * samples  : The AudioSamples object corresponding to the buffer being played.
     * source   : The Web Audio API source node.
     * startTime: The time the sound is scheduled to play at.
     * endTime  : The time the sound should be finished at.
     */
    var playingSamples = [];
    
    // True if this is streaming audio, false if it is not.
    var streaming;
    
    panner = plugins_quorum_Libraries_Sound_Audio_.audioContext.createPanner();
    panner.panningModel = 'equalpower';
    panner.distanceModel = 'inverse';
    panner.refDistance = plugins_quorum_Libraries_Sound_Audio_.defaultReferenceDistance;
    panner.maxDistance = 10000;
    panner.rolloffFactor = plugins_quorum_Libraries_Sound_Audio_.defaultRolloff;
    panner.coneInnerAngle = 360;
    panner.coneOuterAngle = 0;
    panner.coneOuterGain = 0;
    
    /*
     * Used only on Safari. On other browsers, this information is accessible
     * directly from the panner node.
     */
    var pannerData;
    if (!panner.positionX)
    {
        pannerData = {};
        pannerData.x = 0;
        pannerData.y = 0;
        pannerData.z = 0;
        pannerData.forwardX = 0;
        pannerData.forwardY = 0;
        pannerData.forwardZ = -1;
        pannerData.upX = 0;
        pannerData.upY = 1;
        pannerData.upZ = 0;
        
        if (plugins_quorum_Libraries_Sound_Audio_.listenerData === undefined)
        {
            plugins_quorum_Libraries_Sound_Audio_.listenerData = {};
            plugins_quorum_Libraries_Sound_Audio_.listenerData.x = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.y = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.z = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardX = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardY = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardZ = -1;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upX = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upY = 0;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upZ = 0;
        }
    }
    
    /* 
    Initial Y position is slightly offset to reduce impact of a bug found in
    some browsers where using the equalpower panning mode at 0, 0, 0 causes
    audio to only play out of one side.
    */
    if (panner.positionZ)
        panner.positionZ.value = 0.01;
    else
        panner.setPosition(0, 0, 0.01);

    gainNode = plugins_quorum_Libraries_Sound_Audio_.audioContext.createGain();
    
    var thisAudio = this;
    
    this.RunQueuedActions = function()
    {
        if (loading === true)
            return;
        
        for (var i = 0; i < onloadQueue.length; i++)
        {
            var func = onloadQueue[i].bind(thisAudio);
            func();
        }
    };
    
    this.Load$quorum_Libraries_System_File = function(file)
    {   
        streaming = false;
        var runQueuedActions = this.RunQueuedActions;
        
        var request = new XMLHttpRequest();
        request.open('GET', file.plugin_.ConvertAbsoluteToRelativeURL(file), true);
        request.responseType = 'arraybuffer';
        
        request.onload = function()
        {
            var audioData = request.response;
            
            plugins_quorum_Libraries_Sound_Audio_.audioContext.decodeAudioData(audioData, function(buffer)
            {
                soundBuffer = buffer;
                panner.connect(gainNode);
                gainNode.connect(plugins_quorum_Libraries_Sound_Audio_.audioContext.destination);
                gainNode.gain.value = gain;
                
                if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                    plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
                
                loading = false;
                loaded = true;
                runQueuedActions();
            },
            function(e)
            {
                console.log("Error decoding audio data: " + e.err);
            });
        };
        
        loading = true;
        request.send();
    };
    
    this.LoadToStream$quorum_Libraries_System_File = function(file)
    {
        // This needs to be reviewed for the possibility of streaming from a file.
        this.Load$quorum_Libraries_System_File(file);
    };
    
    this.Play = function Play()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(this.Play);
        else
        {
            if (this.IsStreaming() === true)
            {
                if (this.IsPlaying())
                    return;
                
                var queuedTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;

                /*
                Take all samples that have been queued by the user and schedule them
                with the Web Audio API to be played. Also store relevant information
                (the samples that produced it, the source that's playing it, the
                time it is scheduled to start, and the expected end time).
                */
                while (queuedSamples.length > 0)
                {
                    var container = {};
                    container.samples = queuedSamples.shift();

                    var newSource = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
                    newSource.buffer = container.samples.plugin_.buffer;
                    newSource.connect(panner);
                    newSource.loop = false;
                    newSource.start(queuedTime);
                    newSource.playbackRate.value = pitch;

                    container.source = newSource;
                    container.startTime = queuedTime;
                    queuedTime = queuedTime + newSource.buffer.duration / pitch;
                    container.endTime = queuedTime;

                    playingSamples.push(container);
                }
            }
            else
            {
                if (this.IsPlaying())
                    source.stop();

                source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
                source.buffer = soundBuffer;
                source.connect(panner);
                source.loop = looping;
                source.start(0);
                source.playbackRate.value = pitch;

                startTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;
            }
        }
    };
    
    this.Stop = function Stop()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(this.Stop);
        else if (this.IsStreaming())
        {
            for (var i = 0; i < playingSamples.length; i++)
            {
                var playingSource = playingSamples[i].source;
                playingSource.stop();
            }
            
            playingSamples = [];
            queuedSamples = [];
        }
        else if(source !== undefined && source !== null)
        {
            source.stop();
            pauseTime = 0;
            startTime = 0;
        }
    };
    
    this.Pause = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(this.Stop);
        else if (this.IsStreaming())
        {
            var currentTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;
            
            // Make sure there are no samples at the beginning of the queue
            // which have already been played.
            while (playingSamples.length > 0)
            {
                if (playingSamples[0].endTime <= currentTime)
                    playingSamples.shift();
                else
                    break;
            }
            
            if (!(playingSamples.length > 0))
                return;
            
            // Store how long the currently playing sample has been playing for.
            pauseTime = (currentTime - playingSamples[0].startTime) * pitch;
            
            // All samples need to be stopped and requeued for play.
            while (playingSamples.length > 0)
            {
                var container = playingSamples.pop();
                container.source.stop();
                queuedSamples.unshift(container.samples);
            }
        }
        else if (source !== undefined && source !== null)
        {
            source.stop();
            pauseTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;
        }
    };
    
    this.Resume = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(this.Play);
        else if (this.IsStreaming())
        {
            if (!(pauseTime > 0 && queuedSamples.length > 0))
                return;
            
            var currentTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;
            var container = {};
            container.samples = queuedSamples.shift();

            var newSource = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
            newSource.buffer = container.samples.plugin_.buffer;
            newSource.connect(panner);
            newSource.loop = false;
            newSource.start(0, pauseTime);
            newSource.playbackRate.value = pitch;

            container.source = newSource;
            container.startTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime - pauseTime / pitch;
            container.endTime = container.startTime + newSource.buffer.duration / pitch;

            playingSamples.push(container);
            pauseTime = 0;
            this.Stream();
        }
        else
        {
            source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
            source.buffer = soundBuffer;
            source.connect(panner);
            source.loop = looping;
            source.start(0, (pauseTime - startTime));
            source.playbackRate.value = pitch;
            
            pauseTime = 0;
        }
    };
    
    this.IsPlaying = function()
    {
        if (this.IsStreaming() === true)
            return (playingSamples.length > 0 
                && playingSamples[playingSamples.length - 1].endTime > plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime);
        
        if (source === undefined || source === null)
            return false;
        
        var duration = source.buffer.duration;
        return (startTime !== 0 && pauseTime === 0 && (looping || startTime + duration > plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime));
    };
    
    this.EnableLooping = function()
    {
        looping = true;
        if (source !== undefined && source !== null)
            source.loop = looping;
    };
    
    this.DisableLooping = function()
    {
        looping = false;
        if (source !== undefined && source !== null)
            source.loop = looping;
    };
    
    this.IsLoopingEnabled = function()
    {
        if (this.IsStreaming())
            return false;
        return looping;
    };
    
    this.Dispose = function()
    {
        this.Stop();
        queuedSamples = [];
        playingSamples = [];
        source = null;
        soundBuffer = null;
    };
    
    this.SetPitch$quorum_number = function(p)
    {
        if (this.IsStreaming() && this.IsPlaying())
        {
            // Pause and resume audio to reset audio sources (this is necessary
            // because changing pitch changes playback timing, which is already set).
            this.Pause();
            pitch = p;
            this.Resume();
        }
        else
            pitch = p;

        if (source !== null && source !== undefined)
        {
            source.playbackRate.value = pitch;
        }
    };
    
    this.GetPitch = function()
    {
        return pitch;
    };
    
    this.SetVolume$quorum_number = function(volume)
    {
        if (volume < 0)
            volume = 0;
        gain = volume;
        
        if (gainNode !== undefined && gainNode !== null)
            gainNode.gain.value = gain;
    };
    
    this.GetVolume = function()
    {
        return gain;
    };
    
    this.SetBalance$quorum_number = function(position)
    {
        if (position < -1)
            position = -1;
        else if (position > 1)
            position = 1;
        
        pan = position;
        fade = 0;
        
        this.SetPosition$quorum_number$quorum_number$quorum_number(
            Math.cos((pan - 1) * Math.PI / 2),
            Math.sin((pan + 1) * Math.PI / 2),
            0);
    };
    
    this.GetBalance = function()
    {
        return pan;
    };
    
    this.Stream = function()
    {
        if (this.IsStreaming() && this.IsPlaying())
        {
            var queuedTime = plugins_quorum_Libraries_Sound_Audio_.audioContext.currentTime;
            /* 
            Check for finished samples and remove them. The array is effectively
            a queue, so elements later in the array will end later.
            */
            while (playingSamples.length > 0)
            {
                if (playingSamples[0].endTime < queuedTime)
                    playingSamples.shift();
                else
                {
                    queuedTime = playingSamples[playingSamples.length - 1].endTime;
                    break;
                }
            }
            
            /*
            Take all samples that have been queued by the user and schedule them
            with the Web Audio API to be played. Also store relevant information
            (the samples that produced it, the source that's playing it, the
            time it is scheduled to start, and the expected end time).
            */
            while (queuedSamples.length > 0)
            {
                var container = {};
                container.samples = queuedSamples.shift();
                
                var newSource = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
                newSource.buffer = container.samples.plugin_.buffer;
                newSource.connect(panner);
                newSource.loop = false;
                newSource.start(queuedTime);
                newSource.playbackRate.value = pitch;
                
                container.source = newSource;
                container.startTime = queuedTime;
                queuedTime = queuedTime + newSource.buffer.duration / pitch;
                container.endTime = queuedTime;

                playingSamples.push(container);
            }
        }
    };
    
    this.IsStreaming = function()
    {
        return streaming;
    };
    
    this.SetFade$quorum_number = function(newFade)
    {
        if (newFade < -1)
            newFade = -1;
        else if (newFade > 1)
            newFade = 1;
        
        fade = newFade;
        pan = 0;
        
        this.SetPosition$quorum_number$quorum_number$quorum_number(
            0, 
            Math.sin((fade + 1) * Math.PI / 2),
            Math.cos((fade - 1) * Math.PI / 2));
    };
    
    this.GetFade = function()
    {
        return fade;
    };
    
    this.SetX$quorum_number = function(newX)
    {
        if (panner.positionX)
            panner.positionX.value = newX;
        else
        {
            panner.setPosition(newX, pannerData.y, pannerData.z);
            pannerData.x = newX;
        }
    };
    
    this.SetY$quorum_number = function(newY)
    {
        if (panner.positionY)
            panner.positionY.value = newY;
        else
        {
            panner.setPosition(pannerData.x, newY, pannerData.z);
            pannerData.y = newY;
        }
    };
    
    this.SetZ$quorum_number = function(newZ)
    {
        if (panner.positionZ)
            panner.positionZ.value = -newZ;
        else
        {
            panner.setPosition(pannerData.x, pannerData.y, -newZ);
            pannerData.z = -newZ;
        }
    };
    
    this.SetPosition$quorum_number$quorum_number$quorum_number = function(newX, newY, newZ)
    {
        if (panner.positionX)
        {
            panner.positionX.value = newX;
            panner.positionY.value = newY;
            panner.positionZ.value = newZ;
        }
        else
        {
            panner.setPosition(newX, newY, -newZ);
            pannerData.x = newX;
            pannerData.y = newY;
            pannerData.z = -newZ;
        }
    };
    
    this.GetX = function()
    {
        if (panner.positionX)
            return panner.positionX.value;
        return pannerData.x;
    };
    
    this.GetY = function()
    {
        if (panner.positionY)
            return panner.positionY.value;
        return pannerData.y;
    };
    
    this.GetZ = function()
    {
        if (panner.positionZ)
            return -panner.positionZ.value;
        return -pannerData.z;
    };
    
    this.EnableDoppler = function()
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.DisableDoppler = function()
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.IsDopplerEnabled = function()
    {
        return false;
    };
    
    this.SetVelocityX$quorum_number = function(x)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetVelocityY$quorum_number = function(y)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetVelocityZ$quorum_number = function(z)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetVelocity$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.GetVelocityX = function()
    {
        return 0;
    };
    
    this.GetVelocityY = function()
    {
        return 0;
    };
    
    this.GetVelocityZ = function()
    {
        return 0;
    };
    
    this.SetListenerX$quorum_number = function(x)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionX)
            plugins_quorum_Libraries_Sound_Audio_.listener.positionX = x;
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setPosition(
                x, 
                plugins_quorum_Libraries_Sound_Audio_.listenerData.y, 
                plugins_quorum_Libraries_Sound_Audio_.listenerData.z);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.x = x;
        }
    };
    
    this.SetListenerY$quorum_number = function(y)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionY)
            plugins_quorum_Libraries_Sound_Audio_.listener.positionY = y;
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setPosition(
                plugins_quorum_Libraries_Sound_Audio_.listenerData.x, 
                y, 
                plugins_quorum_Libraries_Sound_Audio_.listenerData.z);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.y = y;
        }
    };
    
    this.SetListenerZ$quorum_number = function(z)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionZ)
            plugins_quorum_Libraries_Sound_Audio_.listener.positionZ = z;
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setPosition(
                plugins_quorum_Libraries_Sound_Audio_.listenerData.x, 
                plugins_quorum_Libraries_Sound_Audio_.listenerData.y, 
                -z);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.z = -z;
        }
    };
    
    this.SetListenerPosition$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionX)
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.positionX = x;
            plugins_quorum_Libraries_Sound_Audio_.listener.positionY = y;
            plugins_quorum_Libraries_Sound_Audio_.listener.positionZ = -z;
        }
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setPosition(x, y, -z);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.x = x;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.y = y;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.z = -z;
        }
    };
    
    this.SetListenerVelocityX$quorum_number = function(x)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetListenerVelocityY$quorum_number = function(y)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetListenerVelocityZ$quorum_number = function(z)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.SetListenerVelocity$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.EnableListenerDoppler = function()
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.DisableListenerDoppler = function()
    {
        // Do nothing. The Web Audio API no longer supports doppler.
    };
    
    this.IsListenerDopplerEnabled = function()
    {
        return false;
    };
    
    this.GetListenerX = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionX)
            return plugins_quorum_Libraries_Sound_Audio_.listener.positionX;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.x;
    };
    
    this.GetListenerY = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionY)
            return plugins_quorum_Libraries_Sound_Audio_.listener.positionY;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.y;
    };
    
    this.GetListenerZ = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.positionZ)
            return -plugins_quorum_Libraries_Sound_Audio_.listener.positionZ;
        return -plugins_quorum_Libraries_Sound_Audio_.listenerData.z;
    };
    
    this.GetListenerVelocityX = function()
    {
        return 0;
    };
    
    this.GetListenerVelocityY = function()
    {
        return 0;
    };
    
    this.GetListenerVelocityZ = function()
    {
        return 0;
    };
    
    this.SetListenerDirection$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.forwardX)
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.forwardX.value = x;
            plugins_quorum_Libraries_Sound_Audio_.listener.forwardY.value = y;
            plugins_quorum_Libraries_Sound_Audio_.listener.forwardZ.value = -z;
        }
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setOrientation(
                x,
                y,
                -z,
                plugins_quorum_Libraries_Sound_Audio_.listenerData.upX,
                plugins_quorum_Libraries_Sound_Audio_.listenerData.upY,
                plugins_quorum_Libraries_Sound_Audio_.listenerData.upZ);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardX = x;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardY = y;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardZ = -z;
        }
    };
    
    this.GetListenerDirectionX = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.forwardX)
            return plugins_quorum_Libraries_Sound_Audio_.listener.forwardX.value;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardX;
    };
    
    this.GetListenerDirectionY = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.forwardY)
            return plugins_quorum_Libraries_Sound_Audio_.listener.forwardY.value;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardY;
    };
    
    this.GetListenerDirectionZ = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.forwardZ)
            return -plugins_quorum_Libraries_Sound_Audio_.listener.forwardZ.value;
        return -plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardY;
    };
    
    this.SetListenerUp$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.upX)
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.upX.value = x;
            plugins_quorum_Libraries_Sound_Audio_.listener.upY.value = y;
            plugins_quorum_Libraries_Sound_Audio_.listener.upZ.value = z;
        }
        else
        {
            plugins_quorum_Libraries_Sound_Audio_.listener.setOrientation(
                plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardX,
                plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardY,
                plugins_quorum_Libraries_Sound_Audio_.listenerData.forwardZ,
                x,
                y,
                -z);
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upX = x;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upY = y;
            plugins_quorum_Libraries_Sound_Audio_.listenerData.upZ = -z;
        }
    };
    
    this.GetListenerUpX = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.upX)
            return plugins_quorum_Libraries_Sound_Audio_.listener.upX.value;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.upX;
    };
    
    this.GetListenerUpY = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.upY)
            return plugins_quorum_Libraries_Sound_Audio_.listener.upY.value;
        return plugins_quorum_Libraries_Sound_Audio_.listenerData.upY;
    };
    
    this.GetListenerUpZ = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.listener.upZ)
            return -plugins_quorum_Libraries_Sound_Audio_.listener.upZ.value;
        return -plugins_quorum_Libraries_Sound_Audio_.listenerData.upZ;
    };
    
    this.SetRotation$quorum_number = function(newRotation)
    {
        rotation = newRotation;
        var radians = rotation * Math.PI/180;
        
        var newX = Math.sin(radians);
        var newZ = -Math.cos(radians);
        this.SetPosition$quorum_number$quorum_number$quorum_number(newX, 0, newZ);
    };
    
    this.GetRotation = function()
    {
        return rotation;
    };
    
    this.Rotate$quorum_number = function(addRotation)
    {
        this.SetRotation$quorum_number(rotation + addRotation);
    };
    
    this.Load$quorum_Libraries_Sound_AudioSamples = function(samples)
    {
        streaming = false;
        if (samples.plugin_.IsLoading())
        {
            loading = true;
            samples.plugin_.RequestAudioLoad(this);
            return;
        }
        
        soundBuffer = samples.plugin_.buffer;
        panner.connect(gainNode);
        gainNode.connect(plugins_quorum_Libraries_Sound_Audio_.audioContext.destination);
        gainNode.gain.value = gain;
        
        if (loading === true)
        {
            loaded = true;
            loading = false;
            this.RunQueuedActions();
        }
    };
    
    this.LoadToStream$quorum_Libraries_Sound_AudioSamples = function(samples)
    {
        streaming = true;
        loaded = true;
        panner.connect(gainNode);
        gainNode.connect(plugins_quorum_Libraries_Sound_Audio_.audioContext.destination);
        gainNode.gain.value = gain;
        this.AddToQueue$quorum_Libraries_Sound_AudioSamples(samples);
    };
    
    this.SetMaximumVolumeDistance$quorum_number = function(distance)
    {
        panner.refDistance = distance;
    };
    
    this.SetRolloffRate$quorum_number = function(rolloff)
    {
        panner.rolloffFactor = rolloff;
    };
    
    this.SetDefaultMaximumVolumeDistance$quorum_number = function(distance)
    {
        plugins_quorum_Libraries_Sound_Audio_.defaultReferenceDistance = distance;
    };
    
    this.SetDefaultRolloffRate$quorum_number = function(rolloff)
    {
        plugins_quorum_Libraries_Sound_Audio_.defaultRolloff = rolloff;
    };
    
    this.GetMaximumVolumeDistance = function()
    {
        return panner.refDistance;
    };
    
    this.GetRolloffRate = function()
    {
        return panner.rolloffFactor;
    };
    
    this.GetDefaultMaximumVolumeDistance = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.defaultReferenceDistance;
    };
    
    this.GetDefaultRolloffRate = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.defaultRolloff;
    };

    this.AddToQueue$quorum_Libraries_Sound_AudioSamples = function(samples)
    {
        if (this.IsStreaming() === undefined)
        {
            loaded = true;
            this.LoadToStream$quorum_Libraries_Sound_AudioSamples(samples);
            return;
        }
        
        queuedSamples.push(samples);
    };
    
    this.RemoveFromQueue$quorum_Libraries_Sound_AudioSamples = function(samples)
    {
        for (var i = 0; i < queuedSamples.length; i++)
        {
            if (queuedSamples[i] === samples)
            {
                queuedSamples.splice(i, 1);
                return;
            }
        }
        
        for (var j = 0; i < playingSamples.length; i++)
        {
            if (playingSamples[j].samples === samples)
            {
                if (this.IsPlaying())
                    playingSamples[j].source.stop();
                
                for (var k = playingSamples.length - 1; k > j; k--)
                {
                    var container = playingSamples.pop();
                    queuedSamples.unshift(container.samples);
                }
             
                playingSamples.pop();
                
                if (this.IsPlaying())
                    this.Stream();
                
                break;
            }
        }
    };
    
    this.IsLoaded = function()
    {
        return loaded;
    };
}
