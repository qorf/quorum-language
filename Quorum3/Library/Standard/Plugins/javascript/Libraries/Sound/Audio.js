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
//                console.log("Checking for window context.");
//                plugins_quorum_Libraries_Sound_Audio_.audioContext = window.webkitAudioContext;
//                console.log("Checking for undefined context.");
//                if (plugins_quorum_Libraries_Sound_Audio_.audioContext === undefined)
//                {
//                    console.log("Making new context.");
                    plugins_quorum_Libraries_Sound_Audio_.audioContext = new webkitAudioContext;
//                }
//                console.log("Finishing audio context.");
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
    var gainNode;
    var loading = false;
    var looping = false;
    var onloadQueue = [];
   
    var pitch = 1.0;
    var gain = 1.0;
    var rotation = 0;
    var pan = 0;
    var fade = 0;
    
    var startTime = 0;
    var pauseTime = 0;
    
    panner = plugins_quorum_Libraries_Sound_Audio_.audioContext.createPanner();
    panner.panningModel = 'equalpower';
    panner.distanceModel = 'inverse';
    panner.refDistance = 1;
    panner.maxDistance = 10000;
    panner.rolloffFactor = 1;
    panner.coneInnerAngle = 360;
    panner.coneOuterAngle = 0;
    panner.coneOuterGain = 0;
    
    /*
     * Used only on Safari. On other browsers, this information is accessible
     * directly from the panner node.
     */
    var positions;
    var orientations;
    if (!panner.positionX)
    {
        positions = {};
        positions.x = 0;
        positions.y = 0;
        positions.z = 0;
        positions.listenerX = 0;
        positions.listenerY = 0;
        positions.listenerZ = 0;
        
        orientations = {};        
    }

    gainNode = plugins_quorum_Libraries_Sound_Audio_.audioContext.createGain();
    
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
        var runQueuedActions = this.RunQueuedActions;
        
        var request = new XMLHttpRequest();
        request.open('GET', file.GetPath(), true);
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
                runQueuedActions();
            },
            function(e)
            {
                console.log("Error decoding audio data: " + e.err);
            });
            
            loading = false;
        };
        
        loading = true;
        request.send();
    };
    
    this.LoadToStream$quorum_Libraries_System_File = function(file)
    {
        this.Load$quorum_Libraries_System_File(file);
    };
    
    this.Play = function Play()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(Play);
        else
        {
            source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
            source.buffer = soundBuffer;
            source.connect(panner);
            source.loop = looping;
            source.start(0);
            source.playbackRate.value = pitch;
            
            startTime = Date.now();
        }
    };
    
    this.Stop = function Stop()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(Stop);
        else if (source !== undefined && source !== null)
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
            onloadQueue.push(Stop);
        else if (source !== undefined && source !== null)
        {
            source.stop();
            pauseTime = Date.now();
        }
    };
    
    this.Resume = function()
    {
        if (plugins_quorum_Libraries_Sound_Audio_.audioContext.state === 'suspended')
                plugins_quorum_Libraries_Sound_Audio_.audioContext.resume();
        
        if (loading === true)
            onloadQueue.push(Play);
        else
        {
            source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
            source.buffer = soundBuffer;
            source.connect(panner);
            source.loop = looping;
            source.start(0, (pauseTime - startTime)/1000);
            source.playbackRate.value = pitch;
            
            pauseTime = 0;
        }
    };
    
    this.IsPlaying = function()
    {
        var duration = source.buffer.duration;
        return (startTime !== 0 && pauseTime === 0 && (looping || startTime + duration < Date.now()));
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
    
    this.SetPitch$quorum_number = function(p)
    {
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
        // Do nothing.
    };
    
    this.IsStreaming = function()
    {
        return false;
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
            
        }
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
        if (panner.positionX)
        {
            panner.positionX.value = newX;
            panner.positionY.value = newY;
            panner.positionZ.value = newZ;
        }
        else
            panner.setPosition(newX, newY, newZ);
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
        return plugins_quorum_Libraries_Sound_Audio_.listener.positionX;
    };
    
    this.GetListenerY = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.positionY;
    };
    
    this.GetListenerZ = function()
    {
        return plugins_quorum_Libraries_Sound_Audio_.listener.positionZ;
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
}
