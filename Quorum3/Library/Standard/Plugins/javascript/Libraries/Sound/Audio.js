function plugins_quorum_Libraries_Sound_Audio_()
{
    if (!plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio)
    {
        plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio = true;
        
        try
        {
            plugins_quorum_Libraries_Sound_Audio_.audioContext = window.AudioContext || window.webkitAudioContext || new AudioContext;
        }
        catch(error)
        {
            // If attempting to create a new AudioContext fails, we assume it's not supported.
            plugins_quorum_Libraries_Sound_Audio_.audioContext = undefined;
        }
        
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext === undefined)
        {
            alert("The Web Audio API couldn't be initialized. Your browser may not support it.");
        }
        
        plugins_quorum_Libraries_Sound_Audio_.listener = plugins_quorum_Libraries_Sound_Audio_.audioContext.listener;
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
        
        return;
    }
    
    var soundBuffer;
    var source;
    var panner;
    var loading = false;
    var looping = false;
    var onloadQueue = [];
    
    this.RunQueuedActions = function()
    {
        if (loading === true)
            return;
        
        for (var i = 0; i < onloadQueue.length; i++)
        {
            var func = onloadQueue[i];
            func();
        }
    };
    
    this.Load$quorum_Libraries_System_File = function(file)
    {   
        panner = plugins_quorum_Libraries_Sound_Audio_.audioContext.createPanner();
        panner.panningModel = 'HRTF';
        panner.distanceModel = 'inverse';
        panner.refDistance = 1;
        panner.maxDistance = 10000;
        panner.rolloffFactor = 1;
        panner.coneInnerAngle = 360;
        panner.coneOuterAngle = 0;
        panner.coneOuterGain = 0;
        
        var request = new XMLHttpRequest();
        request.open('GET', file.GetPath(), true);
        request.responseType = 'arraybuffer';
        
        request.onload = function()
        {
            var audioData = request.response;
            
            plugins_quorum_Libraries_Sound_Audio_.audioContext.decodeAudioData(audioData, function(buffer)
            {
                soundBuffer = buffer;
                panner.connect(plugins_quorum_Libraries_Sound_Audio_.audioContext.destination);
            },
            function(e)
            {
                console.log("Error decoding audio data: + e.err");
            });
            
            loading = false;
        };
        
        loading = true;
        request.send();
    };
    
    this.LoadToStream$quorum_Libraries_System_File = function(file)
    {
        
    };
    
    this.Play = function Play()
    {
        if (loading === true)
            onloadQueue.push(Play);
        else
        {
            source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
            source.buffer = soundBuffer;
            source.connect(panner);
            source.loop = looping;
            source.start(0);
        }
    };
    
    this.Stop = function Stop()
    {
        if (loading === true)
            onloadQueue.push(Stop);
        else if (source !== undefined && source !== null)
            source.stop();
    };
    
    this.Pause = function()
    {
        
    };
    
    this.Resume = function()
    {
        
    };
    
    this.IsPlaying = function()
    {
        
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
        return looping;
    };
    
    this.Dispose = function()
    {
        source = null;
        soundBuffer = null;
    };
    
    this.SetPitch$quorum_number = function(pitch)
    {
        
    };
    
    this.GetPitch = function()
    {
        
    };
    
    this.SetVolume$quorum_number = function()
    {
        
    };
    
    this.GetVolume = function()
    {
        
    };
    
    this.SetBalance$quorum_number = function(position)
    {
        
    };
    
    this.GetBalance = function()
    {
        
    };
    
    this.Stream = function()
    {
        
    };
    
    this.IsStreaming = function()
    {
        
    };
    
    this.SetFade$quorum_number = function(fade)
    {
        
    };
    
    this.GetFade = function()
    {
        
    };
    
    this.SetX$quorum_number = function(newX)
    {
        panner.positionX.value = newX;
    };
    
    this.SetY$quorum_number = function(newY)
    {
        panner.positionY.value = newY;
    };
    
    this.SetZ$quorum_number = function(newZ)
    {
        panner.positionZ.value = newZ;
    };
    
    this.SetPosition$quorum_number$quorum_number$quorum_number = function(newX, newY, newZ)
    {
        panner.positionX.value = newX;
        panner.positionY.value = newY;
        panner.positionZ.value = newZ;
    };
    
    this.GetX = function()
    {
        return panner.positionX.value;
    };
    
    this.GetY = function()
    {
        return panner.positionY.value;
    };
    
    this.GetZ = function()
    {
        return panner.positionZ.value;
    };
    
    this.EnableDoppler = function()
    {
        
    };
    
    this.DisableDoppler = function()
    {
        
    };
    
    this.IsDopplerEnabled = function()
    {
        
    };
    
    this.SetVelocityX$quorum_number = function(x)
    {
        
    };
    
    this.SetVelocityY$quorum_number = function(y)
    {
        
    };
    
    this.SetVelocityZ$quorum_number = function(z)
    {
        
    };
    
    this.SetVelocity$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        
    };
    
    this.GetVelocityX = function()
    {
        
    };
    
    this.GetVelocityY = function()
    {
        
    };
    
    this.GetVelocityZ = function()
    {
        
    };
    
    this.SetListenerX$quorum_number = function(x)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.positionX = x;
    };
    
    this.SetListenerY$quorum_number = function(y)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.positionY = y;
    };
    
    this.SetListenerZ$quorum_number = function(z)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.positionZ = z;
    };
    
    this.SetListenerPosition$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.positionX = x;
        plugins_quorum_Libraries_Sound_Audio_.listener.positionY = y;
        plugins_quorum_Libraries_Sound_Audio_.listener.positionZ = z;
    };
    
    this.SetListenerVelocityX$quorum_number = function(x)
    {
        
    };
    
    this.SetListenerVelocityY$quorum_number = function(y)
    {
        
    };
    
    this.SetListenerVelocityZ$quorum_number = function(z)
    {
        
    };
    
    this.SetListenerVelocity$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        
    };
    
    this.EnableListenerDoppler = function()
    {
        
    };
    
    this.DisableListenerDoppler = function()
    {
        
    };
    
    this.IsListenerDopplerEnabled = function()
    {
        
    };
    
    this.GetListenerX = function()
    {
        
    };
    
    this.GetListenerY = function()
    {
        
    };
    
    this.GetListenerZ = function()
    {
        
    };
    
    this.GetListenerVelocityX = function()
    {
        
    };
    
    this.GetListenerVelocityY = function()
    {
        
    };
    
    this.GetListenerVelocityZ = function()
    {
        
    };
    
    this.SetListenerDirection$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.forwardX.value = x;
        plugins_quorum_Libraries_Sound_Audio_.listener.forwardY.value = y;
        plugins_quorum_Libraries_Sound_Audio_.listener.forwardZ.value = z;
    };
    
    this.GetListenerDirectionX = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.forwardX.value;
    };
    
    this.GetListenerDirectionY = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.forwardY.value;
    };
    
    this.GetListenerDirectionZ = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.forwardZ.value;
    };
    
    this.SetListenerUp$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        plugins_quorum_Libraries_Sound_Audio_.listener.upX.value = x;
        plugins_quorum_Libraries_Sound_Audio_.listener.upY.value = y;
        plugins_quorum_Libraries_Sound_Audio_.listener.upZ.value = z;
    };
    
    this.GetListenerUpX = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.upX.value;
    };
    
    this.GetListenerUpY = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.upY.value;
    };
    
    this.GetListenerUpZ = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.upZ.value;
    };
    
    this.SetRotation$quorum_number = function(rotation)
    {
        
    };
    
    this.GetRotation = function()
    {
        
    };
    
    this.Rotate$quorum_number = function(rotation)
    {
        
    };
}
