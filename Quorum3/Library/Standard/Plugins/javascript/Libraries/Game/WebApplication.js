function plugins_quorum_Libraries_Game_WebApplication_() 
{
    var frameInterval = 0;
    var elapsedTime = 0;
    var startTime = 0;
    var lastTime = 0;
    var currentTime = 0;
    var targetFPS = 30;
    
    var exitRequested = false;
    var configuration = null;
    var game = null;
 
    var manager = new GameStateManager();
    
    this.SetGame = function(g)
    {
        game = g;
    };
    
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
        
        var display = manager.GetGameDisplay();
        display.SetupDisplay();
        display.SetLastTime();
        
        var canvas = display.GetCanvas();
        // Use canvas to initialize WebGraphics.
        
        MainLoop(startTime);
    };
    
    this.MainLoop = function(timeStamp)
    {
        if (exitRequested)
            return;
        
        currentTime = timeStamp;
        elapsedTime = currentTime - lastTime;
        
        if (!configuration.capFramesPerSecond || elapsedTime > frameInterval)
        {
            // Set the lastTime to when the last render should have been, if
            // the timing was perfect. This ensures that a late render won't
            // delay future renders.
            lastTime = currentTime - (elapsedTime % frameInterval);
            
            game.ContinueGame();
        }
        
        requestAnimationFrame(MainLoop);
    };
    
    this.Exit = function()
    {
        exitRequested = true;
    };
    
}


