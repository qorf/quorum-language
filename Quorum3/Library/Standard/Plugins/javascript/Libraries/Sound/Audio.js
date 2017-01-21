function plugins_quorum_Libraries_Sound_Audio_()
{
    if (!plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio)
    {
        plugins_quorum_Libraries_Sound_Audio_.initialized_plugins_quorum_Libraries_Sound_Audio = true;
        plugins_quorum_Libraries_Sound_Audio_.audioContext = window.AudioContext || window.webkitAudioContext || new AudioContext;
    }
 
    var source;
    
    this.Load$quorum_Libraries_System_File = function(file)
    {
        source = plugins_quorum_Libraries_Sound_Audio_.audioContext.createBufferSource();
        
        var request = new XMLHttpRequest();
        request.open('GET', file.GetPath(), true);
        request.responseType = 'arraybuffer';
        
        request.onload = function()
        {
            var audioData = request.response;
            
            plugins_quorum_Libraries_Sound_Audio_.audioContext.decodeAudioData(audioData, function(buffer)
            {
                // Store the buffer, and connect the source to the necessary nodes.
            },
            function(e)
            {
                console.log("Error decoding audio data: + e.err");
            });
        };
        
        request.send();
    };
    
    this.LoadToStream$quorum_Libraries_System_File = function(file)
    {
        
    };
    
    this.Play = function()
    {
        
    };
    
    this.Stop = function()
    {
        
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
        
    };
    
    this.DisableLooping = function()
    {
        
    };
    
    this.IsLoopingEnabled = function()
    {
        
    };
    
    this.Dispose = function()
    {
        
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
        
    };
    
    this.SetY$quorum_number = function(newY)
    {
        
    };
    
    this.SetZ$quorum_number = function(newZ)
    {
        
    };
    
    this.SetPosition$quorum_number$quorum_number$quorum_number = function(newX, newY, newZ)
    {
        
    };
    
    this.GetX = function()
    {
        
    };
    
    this.GetY = function()
    {
        
    };
    
    this.GetZ = function()
    {
        
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
        
    };
    
    this.SetListenerY$quorum_number = function(y)
    {
        
    };
    
    this.SetListenerZ$quorum_number = function(z)
    {
        
    };
    
    this.SetListenerPosition$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        
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
        
    };
    
    this.GetListenerDirectionX = function()
    {
        
    };
    
    this.GetListenerDirectionY = function()
    {
        
    };
    
    this.GetListenerDirectionZ = function()
    {
        
    };
    
    this.SetListenerUp$quorum_number$quorum_number$quorum_number = function(x, y, z)
    {
        
    };
    
    this.GetListenerUpX = function()
    {
        
    };
    
    this.GetListenerUpY = function()
    {
        
    };
    
    this.GetListenerUpZ = function()
    {
        
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
