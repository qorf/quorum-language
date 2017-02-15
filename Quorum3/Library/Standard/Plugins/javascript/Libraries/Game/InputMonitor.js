function plugins_quorum_Libraries_Game_InputMonitor_()
{
    this.IsKeyPressed$quorum_integer = function(keyCode)
    {
        if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            return plugins_quorum_Libraries_Game_WebInput_.pressedKeys[keyCode] === true;

        return false;
    };
    
    this.IsMouseButtonPressed$quorum_integer = function(buttonCode)
    {
        
    };
    
    this.GetMouseX = function()
    {
        
    };
    
    this.GetMouseY = function()
    {
        
    };
    
    this.IsClicked = function()
    {
        
    };
    
    this.IsScrolled = function()
    {
        
    };
}


