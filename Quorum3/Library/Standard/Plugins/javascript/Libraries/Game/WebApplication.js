function plugins_quorum_Libraries_Game_WebApplication_() 
{
    var frameInterval = 0;
    var startTime = 0;
    var lastTime = 0;
    var currentTime = 0;
    var targetFPS = 30;
    
    var exitRequested = false;
    var configuration = undefined;
    
    this.SetConfiguration = function(config)
    {
        configuration = config;
    };
    
    this.GetConfiguration = function()
    {
        return configuration;
    };
    
    this.SetupNative = function()
    {
        targetFPS = configuration.framesPerSecondLimit;
        frameInterval = 1000/targetFPS;
        
        startTime = window.performance.now();
        lastTime = startTime;
    };
    
    this.MainLoop = function()
    {
        // Do the main loop.
    };
    
    this.Exit = function()
    {
        exitRequested = true;
    };
    
}


