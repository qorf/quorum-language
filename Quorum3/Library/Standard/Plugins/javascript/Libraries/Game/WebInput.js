function plugins_quorum_Libraries_Game_WebInput_()
{
    if (!plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_)
    {
        plugins_quorum_Libraries_Game_WebInput_.KeyDown = function(event)
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, true);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyUp = function(event)
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                
            }
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
    
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent = function(event, pressed)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_KeyboardEvent_();
            if (pressed)
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PRESSED_KEY_());
            else
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RELEASED_KEY_());
            
            if (event.code !== undefined)
            {
                switch(event.code)
                {
                    case "Digit0":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_0_());
                        break;
                    case "Digit1":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_1_());
                        break;
                    case "Digit2":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_2_());
                        break;
                    case "Digit3":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_3_());
                        break;
                    case "Digit4":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_4_());
                        break;
                    case "Digit5":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_5_());
                        break;
                    case "Digit6":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_6_());
                        break;
                    case "Digit7":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_7_());
                        break;
                    case "Digit8":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_8_());
                        break;
                    case "Digit9":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_9_());
                        break;
                    case "AltLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_LEFT_());
                        break;
                    case "AltRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_RIGHT_());
                        break;
                    case "Quote":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__APOSTROPHE_());
                        break;
                    case "Backslash":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSLASH_());
                        break;
                    case "Comma":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__COMMA_());
                        break;
                    case "Delete":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__FORWARD_DEL_());
                        break;
                    case "Backspace":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSPACE_());
                        break;
                    case "ArrowUp":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UP_());
                        break;
                    case "ArrowDown":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__DOWN_());
                        break;
                    case "ArrowLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__LEFT_());
                        break;
                    case "ArrowRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_());
                        break;
                    case "Enter":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ENTER_());
                        break;
                    case "Equal":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__EQUALS_());
                        break;
                    case "Backquote":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__GRAVE_());
                        break;
                    case "Home":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__HOME_());
                        break;
                    case "Insert":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__INSERT_());
                        break;
                    case "BracketLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__LEFT_BRACKET_());
                        break;
                    case "BracketRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_BRACKET_());
                        break;
                    case "Minus":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__MINUS_());
                        break;
                    case "Period":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PERIOD_());
                        break;
                    case "NumpadAdd":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PLUS_());
                        break;
                    case "Semicolon":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SEMICOLON_());
                        break;
                    case "ShiftLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_LEFT_());
                        break;
                    case "ShiftRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_RIGHT_());
                        break;
                    case "Slash":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SLASH_());
                        break;
                    case "Space":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SPACE_());
                        break;
                    case "NumpadMultiply":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__STAR_());
                        break;
                    case "Tab":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__TAB_());
                        break;
                    case "ControlLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CONTROL_LEFT_());
                        break;
                    case "ControlRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CONTROL_RIGHT_());
                        break;
                    case "Escape":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ESCAPE_());
                        break;
                    case "End":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__END_());
                        break;
                    case "PageUp":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAGE_UP_());
                        break;
                    case "PageDown":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAGE_DOWN_());
                        break;
                    case "Numpad0":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_0_());
                        break;
                    case "Numpad1":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_1_());
                        break;
                    case "Numpad2":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_2_());
                        break;
                    case "Numpad3":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_3_());
                        break;
                    case "Numpad4":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_4_());
                        break;
                    case "Numpad5":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_5_());
                        break;
                    case "Numpad6":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_6_());
                        break;
                    case "Numpad7":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_7_());
                        break;
                    case "Numpad8":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_8_());
                        break;
                    case "Numpad9":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_9_());
                        break;
                    case "KeyA":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__A_());
                        break;
                    case "KeyB":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__B_());
                        break;
                    case "KeyC":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__C_());
                        break;
                    case "KeyD":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyE":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__E_());
                        break;
                    case "KeyF":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F_());
                        break;
                    case "KeyG":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__G_());
                        break;
                    case "KeyH":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__H_());
                        break;
                    case "KeyI":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__I_());
                        break;
                    case "KeyJ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyK":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyL":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyM":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyN":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyO":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyP":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyQ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyR":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyS":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyT":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyU":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyV":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyW":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyX":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyY":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case "KeyZ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    default:
                        alert("I did not catch that key code: " + event.code);
                }
                
                if (event.code !== "Tab")
                {
                    event.preventDefault();
                    alert("Prevented default behavior.");
                }
            }
            else
            {
                alert("The keyCode was " + event.keyCode);
                
                switch(event.keyCode)
                {
                    // Numbers, 0-9, above the letters on the keyboard.
                    case 48:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_0_());
                        break;
                    case 49:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_1_());
                        break;
                    case 50:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_2_());
                        break;
                    case 51:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_3_());
                        break;
                    case 52:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_4_());
                        break;
                    case 53:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_5_());
                        break;
                    case 54:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_6_());
                        break;
                    case 55:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_7_());
                        break;
                    case 56:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_8_());
                        break;
                    case 57:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_9_());
                        break;
                    // Numbers, 0-9, on the numpad.
                    case 96:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_0_());
                        break;
                    case 97:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_1_());
                        break;
                    case 98:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_2_());
                        break;
                    case 99:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_3_());
                        break;
                    case 100:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_4_());
                        break;
                    case 101:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_5_());
                        break;
                    case 102:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_6_());
                        break;
                    case 103:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_7_());
                        break;
                    case 104:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_8_());
                        break;
                    case 105:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_9_());
                        break;
                    // Letters on the keyboard.
                    case 65:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__A_());
                        break;
                    case 66:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__B_());
                        break;
                    case 67:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__C_());
                        break;
                    case 68:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        break;
                    case 69:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__E_());
                        break;
                    case 70:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F_());
                        break;
                    case 71:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__G_());
                        break;
                    case 72:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__H_());
                        break;
                    case 73:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__I_());
                        break;
                    case 74:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__J_());
                        break;
                    case 75:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__K_());
                        break;
                    case 76:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__L_());
                        break;
                    case 77:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__M_());
                        break;
                    case 78:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__N_());
                        break;
                    case 79:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__O_());
                        break;
                    case 80:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__P_());
                        break;
                    case 81:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Q_());
                        break;
                    case 82:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__R_());
                        break;
                    case 83:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__S_());
                        break;
                    case 84:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__T_());
                        break;
                    case 85:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__U_());
                        break;
                    case 86:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__V_());
                        break;
                    case 87:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__W_());
                        break;
                    case 88:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__X_());
                        break;
                    case 89:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Y_());
                        break;
                    case 90:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Z_());
                        break;
                    // Unique keys on the keyboard that aren't part of the numpad.
                    case 222: // Apostrophe/Quote
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__APOSTROPHE_());
                        break;
                    case 188: // Comma / Left Angled Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__COMMA_());
                        break;
                    case 8: // Backspace
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSPACE_());
                        break;
                    case 46: // Forward Delete
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__FORWARD_DEL_());
                        break;
                    case 37: // Left Arrow
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__LEFT_());
                        break;
                    case 38: // Up Arrow
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UP_());
                        break;
                    case 39: // Right Arrow
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_());
                        break;
                    case 40: // Down Arrow
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__DOWN_());
                        break;
                    case 191: // Slash
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SLASH_());
                        break;
                    case 220: // Backslash
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSLASH_());
                        break;
                    case 13: // Enter
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ENTER_());
                        break;
                    case 176: // Grave/Tilde
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__GRAVE_());
                        break;
                    case 187: // Equal/Plus
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__EQUALS_());
                        break;
                    case 189: // Minus/Underscore
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__MINUS_());
                        break;
                    case 190: // Period / Right Angled Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PERIOD_());
                        break;
                    case 219: // Left Bracket / Left Curly Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__LEFT_BRACKET_());
                        break;
                    case 221: // Right Bracket / Right Curly Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_BRACKET_());
                        break;
                    case 186: // Semicolon / Colon
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__DOWN_());
                        break;
                    case 32: // Space
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SPACE_());
                        break;
                    case 9: // Tab
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__TAB_());
                        break;
                    case 27: // Escape
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ESCAPE_());
                        break;
                    case 35: // End
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__END_());
                        break;
                    case 36: // Home
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__HOME_());
                        break;
                    case 45: // Insert
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__INSERT_());
                        break;
                    case 33: // Page Up
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAGE_UP_());
                        break;
                    case 34: // Page Down
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAGE_DOWN_());
                        break;
                    // Other keys near the numpad.
                    case 106: // Numpad *
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__STAR_());
                        break;
                    case 107: // Numpad +
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PLUS_());
                        break;
                    // Keys which have a key on both the left and right.
                    case 18: // Alt Key
                        if (event.location === KeyboardEvent.DOM_KEY_LOCATION_LEFT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_LEFT_());
                        }
                        else if (event.location === KeyboardEvent.DOM_KEY_LOCATION_RIGHT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_RIGHT_());
                        }
                        break;
                    case 18: // Alt Key
                        if (event.location === KeyboardEvent.DOM_KEY_LOCATION_LEFT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_LEFT_());
                        }
                        else if (event.location === KeyboardEvent.DOM_KEY_LOCATION_RIGHT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_RIGHT_());
                        }
                        break;
                    case 16: // Shift Key
                        if (event.location === KeyboardEvent.DOM_KEY_LOCATION_LEFT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_LEFT_());
                        }
                        else if (event.location === KeyboardEvent.DOM_KEY_LOCATION_RIGHT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_RIGHT_());
                        }
                        break;
                    case 18: // Control Key
                        if (event.location === KeyboardEvent.DOM_KEY_LOCATION_LEFT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CONTROL_LEFT_());
                        }
                        else if (event.location === KeyboardEvent.DOM_KEY_LOCATION_RIGHT)
                        {
                            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CONTROL_RIGHT_());
                        }
                        break;
                    default:
                        alert("I did not recognize this keyCode: " + event.keyCode);
                }
                
                if (event.keyCode !== 9)
                {
                    event.preventDefault();
                    alert("Prevented default behavior.");
                }
            }
//            alert("event.code = " + event.code);
            
            return quorumEvent;
        };
    
        plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_ = true;
    }
    
    var mouseEvents = [];
    var keyboardEvents = [];
    
    this.CheckForEvents$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array = function(mEvents, kEvents)
    {
        
    };
}