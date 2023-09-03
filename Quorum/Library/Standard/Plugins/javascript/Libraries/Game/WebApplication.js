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

    this.SetCurrentlyActiveGame = function(targetGame)
    {
        plugins_quorum_Libraries_Game_WebApplication_.activeGame = targetGame;
    }

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
        
        let accessibility = game.GetAccessibility();
        if (accessibility)
        {
            accessibility.plugin_.Setup();
        }
        
        game.InitializeLayers();
        game.CreateGame();
        
        loopCall = this.MainLoop;
        this.MainLoop(startTime);
    };
    
    this.MainLoop = function(timeStamp)
    {
        // Note that because of how this is called via Javascript, we can't use "this.SetCurrentlyActiveGame()" because
        // the meaning of "this" will be different once this is called later by the requested animation frame call.
        plugins_quorum_Libraries_Game_WebApplication_.activeGame = game;

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

                // Wipe the screen clean.
                var graphics = manager.GetGameGraphics();
                // 16640 is the result of binary OR on GL_COLOR_BUFFER_BIT and GL_DEPTH_BUFFER_BIT.
                graphics.ClearScreen$quorum_integer(16640);

                // We need to handle the global resources stored by  the ShaderManager.
                // First get the shared copy of the class.
                var shaderManager = (global_Get_Shared_Class("Libraries.Game.Graphics.Shaders.ShaderManager") == null ? global_Add_Shared_Class("Libraries.Game.Graphics.Shaders.ShaderManager", new quorum_Libraries_Game_Graphics_Shaders_ShaderManager_()) : global_Get_Shared_Class("Libraries.Game.Graphics.Shaders.ShaderManager"));

                // Delete shader resources we no longer need.
                var programs = shaderManager.registeredPrograms.GetValueIterator();
                while (programs.HasNext())
                {
                    graphics.DeleteShaderProgram$quorum_integer(programs.Next().GetID());
                }

                var shaders = shaderManager.registeredShaders.GetValueIterator();
                while (shaders.HasNext())
                {
                    graphics.DeleteShader$quorum_integer(shaders.Next().GetID());
                }

                // Empty the ShaderManager's hash tables.
                shaderManager.registeredShaders.Empty();
                shaderManager.registeredPrograms.Empty();
                shaderManager.reloadableMeshes.Empty();

                // Stop currently playing sounds, if there are any.
                if (typeof plugins_quorum_Libraries_Sound_Audio_ === "function" && typeof plugins_quorum_Libraries_Sound_Audio_.StopAllSources === "function")
                {
                    plugins_quorum_Libraries_Sound_Audio_.StopAllSources();
                }


                // The canvas is created when running a Game so destroy this one so a new one can be created by another program
                display.Destroy();

                // The GameStateManager needs to reinitialized when another application is ran
                // NOTE other static initializers might need to be reset but these at least ensure that the FontManager is reloaded every time
                // NOTE: Due to changes in how GameStateManager works (managing each Game separately) the manager plugin should no longer be reinitialized.
//                if (plugins_quorum_Libraries_Game_GameStateManager_.initialized_plugins_quorum_Libraries_Game_GameStateManager_) {
//                    delete plugins_quorum_Libraries_Game_GameStateManager_.initialized_plugins_quorum_Libraries_Game_GameStateManager_;
//                }
                if (plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_) {
                    delete plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_;
                }

                // Reset values to their null defaults when the game ends.
                // If a new game starts on the same page, we don't want left over data.
                frameInterval = 0;
                elapsedTime = 0;
                startTime = 0;
                lastTime = 0;
                currentTime = 0;
                targetFPS = 30;

                configuration = null;
                game = null;
                display = null;
                loopCall = null;
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

    this.Log$quorum_text = function(value)
    {
        console.log(value);
    };

    this.SaveImageToDownloads$quorum_Libraries_Game_Graphics_PixelMap$quorum_text = function(pixelMap, fileName)
    {
        pixelMap.plugin_.SaveToDownloads(fileName);
    };
    
}


