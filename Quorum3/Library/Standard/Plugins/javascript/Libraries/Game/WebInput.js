function plugins_quorum_Libraries_Game_WebInput_()
{
    if (!plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_)
    {
        plugins_quorum_Libraries_Game_WebInput_.KeyDown = function()
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                alert("keydown on canvas");
            }
            else
                alert("keydown off canvas");
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyUp = function()
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                alert("keyup on canvas");
            }
            else
                alert("keyup off canvas");
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseDown = function()
        {
            
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseUp = function()
        {
            
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseMove = function()
        {
            
        };
        
        document.addEventListener('keydown', plugins_quorum_Libraries_Game_WebInput_.KeyDown, false);
        document.addEventListener('keyup', plugins_quorum_Libraries_Game_WebInput_.KeyUp, false);
    
        plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_ = true;
    }
    
    var mouseEvents = [];
    var keyboardEvents = [];
    
    this.CheckForEvents$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array = function(mEvents, kEvents)
    {
        
    };
}