function plugins_quorum_Libraries_Game_WebApplication_() 
{
    var frameInterval = 0;
    var elapsedTime = 0;
    var startTime = 0;
    var lastTime = 0;
    var currentTime = 0;
    var targetFPS = 30;
    
    var configuration = null;
    var game = null;
    var display = null;
    var loopCall = null;
 
    var manager = new quorum_Libraries_Game_GameStateManager_();
    
    this.SetGame$quorum_Libraries_Game_Game = function(g)
    {
        game = g;
    };
    
    this.SetConfiguration$quorum_Libraries_Game_WebConfiguration = function(config)
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
        
        display = manager.GetGameDisplay();
        display.SetupDisplay();
        display.SetLastTime();
        
        var canvas = display.plugin_.GetCanvas();
        canvas.width = canvas.height * (canvas.clientWidth / canvas.clientHeight);
        var graphics = manager.GetGameGraphics();
        var initialized = graphics.plugin_.InitializeWebGL(canvas);
        
        if (!initialized)
        {
            return;
        }
        
        game.InitializeLayers();
        game.CreateGame();
        
        loopCall = this.MainLoop;
        this.MainLoop(startTime);
    };
    
    this.MainLoop = function(timeStamp)
    {
        if (game.Get_Libraries_Game_Game__exitRequested_())
        {
            var exitRequested = game.OnExit();
            
            if (exitRequested)
            {
                var accessibility = game.GetAccessibility();
                if (accessibility != null)
                {
                    accessibility.Shutdown();
                }
                
                return;
            }
            else
            {
                game.Set_Libraries_Game_Game__exitRequested_(false);
            }
        }
        
        currentTime = timeStamp;
        elapsedTime = currentTime - lastTime;
        
        if (!configuration.capFramesPerSecond || elapsedTime > frameInterval || display.RenderingRequested())
        {
            display.renderingRequested = false;
            display.UpdateTime();
            
            // Set the lastTime to when the last render should have been, if
            // the timing was perfect. This ensures that a late render won't
            // delay future renders.
            lastTime = currentTime - (elapsedTime % frameInterval);
            
            game.ContinueGame();
        }
        
        requestAnimationFrame(loopCall);
    };
    
    this.Exit = function()
    {
        game.Set_Libraries_Game_Game__exitRequested_(true);
    };
    
}


