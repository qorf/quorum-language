function plugins_quorum_Libraries_Game_GameStateManager_() 
{
    plugins_quorum_Libraries_Game_GameStateManager_.application = null;
    plugins_quorum_Libraries_Game_GameStateManager_.display = null;
    plugins_quorum_Libraries_Game_GameStateManager_.graphics = null;
    plugins_quorum_Libraries_Game_GameStateManager_.input = null;
    
    plugins_quorum_Libraries_Game_GameStateManager_.nativePath = null;
    plugins_quorum_Libraries_Game_GameStateManager_.operatingSystem = null;
    
    plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics = null;
    
    this.SetApplication$quorum_Libraries_Game_Application = function(app) 
    {
        application = app;
    };
    
    this.GetApplication = function() 
    {
        return application;
    };
    
    this.SetGameDisplay$quorum_Libraries_Game_GameDisplay = function(disp) 
    {
        display = disp;
    };
    
    this.GetGameDisplay = function() 
    {
        return display;
    };
    
    this.SetGameGraphics$quorum_Libraries_Game_Graphics_GraphicsManager = function(gl20) 
    {
        graphics = gl20;
        nativeGraphics = graphics.plugin_;
    };
    
    this.GetGameGraphics = function() 
    {
        return graphics;
    };
    
    this.GetInput = function() 
    {
        return input;
    };
    
    this.SetInput$quorum_Libraries_Game_GameInput = function(gameInput) 
    {
        input = gameInput;
    };
    
    this.SetNativePath$quorum_text = function(path) 
    {
        nativePath = path;
        //System.load(nativePath);
    };
    
    this.GetNativePath = function() 
    {
        return nativePath;
    };
    
    this.SetOperatingSystem$quorum_text = function(os) 
    {
        operatingSystem = os;
    };
    
    this.GetOperatingSystem = function() 
    {
        return operatingSystem;
    };
}
