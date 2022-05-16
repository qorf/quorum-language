function plugins_quorum_Libraries_Game_InputMonitor_()
{
    this.IsKeyPressed$quorum_integer = function(keyCode)
    {
        if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            return plugins_quorum_Libraries_Game_WebInput_.pressedKeys[keyCode] === true;

        return false;
    };
    
    this.IsMouseButtonPressed$quorum_integer = function(buttonCode)
    {
        if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
        {
            var buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons;
            switch (buttonCode)
            {
                /*
                Using the following Quorum MouseEvent constants:
                public constant integer ANY = -1
                public constant integer LEFT = 0
                public constant integer RIGHT = 1
                public constant integer MIDDLE = 2
                public constant integer BACK = 3
                public constant integer FORWARD = 4
                */
                
                case -1:
                    return buttons > 0;
                case 0:
                    return (buttons & 1) === 1;
                case 1:
                    return (buttons & 2) === 2;
                case 2:
                    return (buttons & 4) === 4;
                case 3:
                    return (buttons & 8) === 8;
                case 4:
                    return (buttons & 16) === 16;
            }
        }
        
        return false;
    };
    
    this.GetMouseX = function()
    {
        return plugins_quorum_Libraries_Game_WebInput_.mouseInfo.x;
    };
    
    this.GetMouseY = function()
    {
        return plugins_quorum_Libraries_Game_WebInput_.mouseInfo.y;
    };
    
    this.IsClicked = function()
    {
        if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            return plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons > 0;
    };
    
    this.IsScrolled = function()
    {
        var wheel = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.wheel;
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo.wheel = 0;
        return wheel;
    };
    
    this.NumberOfKeysPressed = function()
    {
        return plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount;
    };
}


