function plugins_quorum_Libraries_Game_WebDisplay_() 
{
    var canvas = null;
    var configuration = null;
    
    var lastTime = 0;
    var deltaTime = 0;
    var totalTime = 0;
    var renderingRequested = false;
    
    this.SetConfigurationNative$quorum_Libraries_Game_WebConfiguration = function(config)
    {
        configuration = config;
    };
    
    this.GetConfiguration = function()
    {
        return configuration;
    };
    
    this.SetupDisplay = function()
    {
        if (typeof currentIDECanvas_$Global_ !== 'undefined') {
            canvas = document.getElementById(currentIDECanvas_$Global_);
        } else {
            var id = configuration.canvasID;
            canvas = document.getElementById(id);
        }
    };
    
    this.GetCanvas = function()
    {
        return canvas;
    };
    
    this.SetDisplayMode$quorum_integer$quorum_integer$quorum_boolean = function(width, height, fullscreen)
    {
        // Currently resizes canvas but does not support fullscreen.
        canvas.width = width;
        canvas.height = height;
        
        return canvas.width === width && canvas.height === height && !fullscreen;
    };
    
    this.GetWidth = function()
    {
        return canvas.width;
    };
    
    this.GetHeight = function()
    {
        return canvas.height;
    };
    
    this.SetVSync$quorum_boolean = function(vsync)
    {
        // It's not clear if we can reliably set the vSync with webGL.
    };
    
    this.GetResize = function()
    {
        return false;
    };
    
    this.WasResized = function()
    {
        return false;
    };
    
    this.RequestRendering = function()
    {
        renderingRequested = true;
    };
    
    this.RenderingRequested = function()
    {
        return renderingRequested;
    };
    
    this.Destroy = function()
    {
        // It's not clear if a user should be able to close the game display, 
        // since it exists on a canvas on a webpage.
    };
    
    this.UpdateTime = function()
    {
        var time = window.performance.now();
        deltaTime = (time - lastTime)/1000.0;
        lastTime = time;
        totalTime = totalTime + deltaTime;
    };
    
    this.SetLastTime = function()
    {
        lastTime = window.performance.now();
    };
    
    this.GetSecondsBetweenFrames = function()
    {
        return deltaTime;
    };
    
    this.GetSecondsSinceStart = function()
    {
        return totalTime;
    };
    
    this.GetPixelScaleFactor = function()
    {
        return 1.0;
    };
    
    this.IsAvailable = function()
    {
        var manager = new quorum_Libraries_Game_GameStateManager_();
        var graphics = manager.GetGameGraphics();
        if (graphics !== null && graphics !== undefined)
            return graphics.plugin_.HasContext();
        else
            return false;
    };
}

