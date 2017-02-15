function plugins_quorum_Libraries_Game_WebInput_()
{
    if (!plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_)
    {
        plugins_quorum_Libraries_Game_WebInput_.mouseEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.keyboardEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.pressedKeys = {};
        
        plugins_quorum_Libraries_Game_WebInput_.KeyDown = function(event)
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, true);
                if (!plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = true;
                    plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
                }
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyUp = function(event)
        {
            if (plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas() === document.activeElement)
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, false);
                if (plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = false;
                    plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
                }
            }
        };
        
        /*
        public constant integer CLICKED_MOUSE = 1
        public constant integer MOVED_MOUSE = 2
        public constant integer DRAGGED_MOUSE = 3
        public constant integer RELEASED_MOUSE = 4
        public constant integer SCROLLED_MOUSE = 5
         */
        
        plugins_quorum_Libraries_Game_WebInput_.MouseDown = function(event)
        {
            var canvas = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
            if (canvas === document.activeElement)
            {
                var rect = canvas.getBoundingClientRect();
                alert("x = " + (event.clientX - rect.left));
                alert("y = " + (rect.bottom - event.clientY));
                alert("button = " + event.button);
                
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 1);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseUp = function(event)
        {
            
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseMove = function(event)
        {

        };
        
        document.addEventListener('keydown', plugins_quorum_Libraries_Game_WebInput_.KeyDown, false);
        document.addEventListener('keyup', plugins_quorum_Libraries_Game_WebInput_.KeyUp, false);
        document.addEventListener('mousedown', plugins_quorum_Libraries_Game_WebInput_.MouseDown, false);
        document.addEventListener('mouseup', plugins_quorum_Libraries_Game_WebInput_.MouseUp, false);
        document.addEventListener('mousemove', plugins_quorum_Libraries_Game_WebInput_.MouseMove, false);
    
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
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__FORWARD_DELETE_());
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
                    case "Pause":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAUSE_());
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
                    case "NumpadAdd":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_PLUS_());
                        break;
                    case "NumpadMultiply":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_STAR_());
                        break;
                    case "NumpadSubtract":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_MINUS_());
                        break;
                    case "NumpadDivide":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_SLASH_());
                        break;
                    case "NumpadDecimal":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_DECIMAL_());
                        break;
                    case "NumpadEnter":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_ENTER_());
                        break;
                    case "NumpadEqual":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_EQUALS_());
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
                    case "CapsLock":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CAPS_LOCK_());
                        break;
                    case "NumLock":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_LOCK_());
                        break;
                    case "ScrollLock":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SCROLL_LOCK_());
                        break;
                    case "F1":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F1_());
                        break;
                    case "F2":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F2_());
                        break;
                    case "F3":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F3_());
                        break;
                    case "F4":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F4_());
                        break;
                    case "F5":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F5_());
                        break;
                    case "F6":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F6_());
                        break;
                    case "F7":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F7_());
                        break;
                    case "F8":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F8_());
                        break;
                    case "F9":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F9_());
                        break;
                    case "F10":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F10_());
                        break;
                    case "F11":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F11_());
                        break;
                    case "F12":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F12_());
                        break;
                    default:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UNKNOWN_());
                }
                
                if (event.code !== "Tab" || plugins_quorum_Libraries_Game_WebInput_.keepTabFocus())
                    event.preventDefault();
            }
            else
            {
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
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__FORWARD_DELETE_());
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
                    case 192: // Grave/Tilde
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
                    case 19: // Pause
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PAUSE_());
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
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_STAR_());
                        break;
                    case 107: // Numpad +
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_PLUS_());
                        break;
                    case 109: // Numpad -
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_MINUS_());
                        break;
                    case 111: // Numpad /
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_SLASH_());
                        break;
                    case 110: // Numpad Decimal
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_DECIMAL_());
                        break;
                    case 13: // Numpad Enter
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_ENTER_());
                        break;
                    case 12: // Numpad Equal
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_EQUALS_());
                        break;
                    // The "lock" keys.
                    case 20: // Caps Lock
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__CAPS_LOCK_());
                        break;
                    case 144: // Num Lock
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_LOCK_());
                        break;
                    case 145: // Scroll Lock
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SCROLL_LOCK_());
                        break; 
                    // Function keys.
                    case 112: // F1
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F1_());
                        break; 
                    case 113: // F2
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F2_());
                        break; 
                    case 114: // F3
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F3_());
                        break; 
                    case 115: // F4
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F4_());
                        break; 
                    case 116: // F5
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F5_());
                        break; 
                    case 117: // F6
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F6_());
                        break; 
                    case 118: // F7
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F7_());
                        break; 
                    case 119: // F8
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F8_());
                        break; 
                    case 120: // F9
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F9_());
                        break; 
                    case 121: // F10
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F10_());
                        break; 
                    case 122: // F11
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F11_());
                        break; 
                    case 123: // F12
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F12_());
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
                    case 17: // Control Key
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
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UNKNOWN_());
                }
                
                if (event.keyCode !== 9 || plugins_quorum_Libraries_Game_WebInput_.keepTabFocus())
                    event.preventDefault();
            }
            
            return quorumEvent;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent = function(event, code)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_MouseEvent_();
            
            quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__mouseButton_(code);
        };
    
        plugins_quorum_Libraries_Game_WebInput_.keepTabFocus = function()
        {
            return plugins_quorum_Libraries_Game_GameStateManager_.application.plugin_.GetConfiguration().Get_Libraries_Game_WebConfiguration__keepTabFocus_();
        };
        
        plugins_quorum_Libraries_Game_WebInput_.disableContextMenu = function()
        {
            return plugins_quorum_Libraries_Game_GameStateManager_.application.plugin_.GetConfiguration().Get_Libraries_Game_WebConfiguration__disableContextMenu_();
        };
    
        plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_ = true;
    }
    
    this.CheckForEvents$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array = function(mEvents, kEvents)
    {
        for (var i = 0; i < plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.length; i++)
        {
            kEvents.Add$quorum_Libraries_Language_Object(plugins_quorum_Libraries_Game_WebInput_.keyboardEvents[i]);
        }
        
        for (var i = 0; i < plugins_quorum_Libraries_Game_WebInput_.mouseEvents.length; i++)
        {
            mEvents.Add$quorum_Libraries_Language_Object(plugins_quorum_Libraries_Game_WebInput_.mouseEvents[i]);
        }
        
        plugins_quorum_Libraries_Game_WebInput_.keyboardEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.mouseEvents = [];
    };
}