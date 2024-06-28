function plugins_quorum_Libraries_Game_WebDisplay_() 
{
    let container = null;
    let canvas = null;
    var configuration = null;
    
    var lastTime = 0;
    var deltaTime = 0;
    var totalTime = 0;
    var renderingRequested = false;

    // TBD: This needs to be addressed as part of getting the clipboard info!
    // A temporary half-measure for clipboard access. Lets the user copy and paste within the same application, even
    // if they can't paste text from outside the application.
    var localClipboard = "";
    
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
        let id;
        if (typeof currentUIContainer_$Global_ !== "undefined") {
            id = currentUIContainer_$Global_;
        } else {
            id = configuration.containerID;
        }
        container = document.getElementById(id);

        canvas = document.createElement("canvas");
        canvas.setAttribute("aria-hidden", "true");
        canvas.style.outline = "none";
        canvas.style.position = "absolute";
        canvas.style.left = 0;
        canvas.style.top = 0;
        canvas.style.width = "100%";
        canvas.style.height = "100%";
        container.appendChild(canvas);
    };
    
    this.GetCanvas = function()
    {
        return canvas;
    };
    
    this.GetContainer = function()
    {
        return container;
    };
    
    this.SetDisplayMode$quorum_integer$quorum_integer$quorum_boolean = function(width, height, fullscreen)
    {
        throw new Error("Not supported on the web platform; the size is defined by the container.");
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
        canvas.remove();
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

    this.GetClipboard = function()
    {
        return localClipboard;
    }

    this.SetClipboard$quorum_text = function(value)
    {
        localClipboard = value;
        navigator.clipboard.writeText(value);
    }
}

