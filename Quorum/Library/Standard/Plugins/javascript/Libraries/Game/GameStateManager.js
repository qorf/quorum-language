function plugins_quorum_Libraries_Game_GameStateManager_() 
{
    /*
    The currently running games are used as keys for the "registeredGames" Map object stored directly into the plugin definition,
    i.e., plugins_quorum_Libraries_Game_GameStateManager_.registeredGames.

    The stored values in the "registeredGames" map are Objects (or "gameInfo") with the following attached values:
    * game (The game that acts as a key to this value in the map)
    * focus (The Item that is considered focused in the Quorum logic -- this isn't the same as the document's focus)
    * application (The WebApplication Quorum class associated with the game)
    * display (The WebDisplay Quorum class associated with the game)
    * graphics (The WebGraphics Quorum class associated with the game)
    * nativeGraphics (The plugin for the WebGraphics class above, stored separately for convenience)
    * input (The WebInput Quorum class associated with the game)
    * nativePath (The path to where native libraries are located, stored as a string. The Web platform doesn't use this like other platforms do, so expect the empty string here.)
    * fontManager (The FontManager Quorum class associated with the game)

    The gameInfo also accepts external information associated with specific plugins. For example, the WebInput plugin
    associates game-specific input info with the game by attaching it to the gameInfo. Such associated info is always
    accessed via an intermediate object named after the plugin associated with it. For example, the array of currently
    pressed keys associated with this game is contributed by the WebInput plugin and can be seen below.

    // Example of externally-contributed plugin-associated game info
    gameInfo.plugins_quorum_Libraries_game_WebInput_.pressedKeys


    The game which is currently executing code is the "active game" stored in the WebApplication plugin, i.e.,
    plugins_quorum_Libraries_Game_WebApplication_.activeGame.
    */

    if (!plugins_quorum_Libraries_Game_GameStateManager_.initialized_plugins_quorum_Libraries_Game_GameStateManager_)
    {
        plugins_quorum_Libraries_Game_GameStateManager_.initialized_plugins_quorum_Libraries_Game_GameStateManager_ = true;
        // Setting a default font manager for font information in the event a game hasn't been made yet
        // Specifically needed for charts where charts need font information before the game is created
        plugins_quorum_Libraries_Game_GameStateManager_.defaultFontManager = new quorum_Libraries_Game_Graphics_Fonts_FontManager_();

        const gameMap = new Map();
        plugins_quorum_Libraries_Game_GameStateManager_.registeredGames = gameMap;

        // Register the global functions that manage the active game.
        plugins_quorum_Libraries_Game_GameStateManager_.RegisterGame = function(newGame)
        {
            var gameMap = plugins_quorum_Libraries_Game_GameStateManager_.registeredGames;

            if (newGame != null && gameMap.has(newGame) == false)
            {
                const gameInfo =
                {
                    game: newGame,
                    focus: null,
                    application: null,
                    display: null,
                    graphics: null,
                    nativeGraphics: null,
                    input: null,
                    nativePath: "",
                    operatingSystem: "Web Browser",
                    fontManager: new quorum_Libraries_Game_Graphics_Fonts_FontManager_(),
                    mainThreadID: "1",
                };

                gameMap.set(newGame, gameInfo);
            }
        };

        plugins_quorum_Libraries_Game_GameStateManager_.UnregisterGame = function(game)
        {
            gameMap.delete(game);
        };

        plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGame = function()
        {
            var active = plugins_quorum_Libraries_Game_WebApplication_.activeGame;

            // Ensure the game has been registered, and if it hasn't been, register it.
            plugins_quorum_Libraries_Game_GameStateManager_.RegisterGame(active);

            return active;
        };

        plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo = function()
        {
            var activeGame = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGame();
            if (activeGame == null)
                return null;

            return plugins_quorum_Libraries_Game_GameStateManager_.registeredGames.get(activeGame);
        };
    }

    this.RegisterGame = function(newGame)
    {
        plugins_quorum_Libraries_Game_GameStateManager_.RegisterGame(newGame);
    };

    this.UnregisterGame = function(game)
    {
        plugins_quorum_Libraries_Game_GameStateManager_.UnregisterGame(game);
    };

    // Grab the active game stored by the WebApplication plugin. This is the Game that is currently running its code
    // in the animation queue.
    this.GetActiveGame = function()
    {
        return plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGame();
    };

    this.GetActiveGameInfo = function()
    {
        return plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo();
    };

    this.SetGame$quorum_Libraries_Game_Game = function(game)
    {
        // Set this game as the active one, and try to register it.
        plugins_quorum_Libraries_Game_WebApplication_.activeGame = game;
        plugins_quorum_Libraries_Game_GameStateManager_.RegisterGame(game);
    };
    
    this.GetGame = function()
    {
        var gameInfo = this.GetActiveGameInfo();
        if (gameInfo == null) {
            return null;
        }
        return gameInfo.game;
    };
    
    this.SetFocus$quorum_Libraries_Interface_Item = function(item)
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.focus = item;
    };
    
    this.GetFocus = function()
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.focus;
    };
    
    this.SetApplication$quorum_Libraries_Game_Application = function(app) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.application = app;
    };
    
    this.GetApplication = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.application;
    };
    
    this.SetGameDisplay$quorum_Libraries_Game_GameDisplay = function(disp) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.display = disp;
    };
    
    this.GetGameDisplay = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.display;
    };
    
    this.SetGameGraphics$quorum_Libraries_Game_Graphics_GraphicsManager = function(gl20) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.graphics = gl20;
        gameInfo.nativeGraphics = gameInfo.graphics.plugin_;
    };
    
    this.GetGameGraphics = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.graphics;
    };
    
    this.GetInput = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.input;
    };
    
    this.SetInput$quorum_Libraries_Game_GameInput = function(gameInput) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.input = gameInput;
    };
    
    this.SetNativePath$quorum_text = function(path) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.nativePath = path;
    };
    
    this.GetNativePath = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.nativePath;
    };
    
    this.SetOperatingSystem$quorum_text = function(os) 
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.operatingSystem = os;
    };
    
    this.GetOperatingSystem = function() 
    {
        var gameInfo = this.GetActiveGameInfo();
        if (gameInfo == null) {
            return "Web Browser";
        }
        return gameInfo.operatingSystem;
    };
    
    this.SetFontManager$quorum_Libraries_Game_Graphics_Fonts_FontManager = function(fontManager)
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.fontManager = fontManager;
    };
    
    this.GetFontManager = function()
    {
        var gameInfo = this.GetActiveGameInfo();
        // if there is no active game but the font manager is needed provide the default for basic font information
        // needed for charts
        if (gameInfo == null) {
            return plugins_quorum_Libraries_Game_GameStateManager_.defaultFontManager;
        }
        return gameInfo.fontManager;
    };

    this.SetMainThreadName$quorum_text = function(name)
    {
        var gameInfo = this.GetActiveGameInfo();
        gameInfo.mainThreadID = name;
    };

    this.GetMainThreadName = function()
    {
        var gameInfo = this.GetActiveGameInfo();
        return gameInfo.mainThreadID;
    };
}