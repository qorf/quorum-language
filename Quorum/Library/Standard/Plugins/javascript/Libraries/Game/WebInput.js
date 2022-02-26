function plugins_quorum_Libraries_Game_WebInput_()
{
    if (!plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_)
    {
        plugins_quorum_Libraries_Game_WebInput_.mouseEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.keyboardEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.textEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.resizeEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.pressedKeys = {};
        plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount = 0;
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo = {};
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo.x = 0;
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo.y = 0;
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = 0;
        plugins_quorum_Libraries_Game_WebInput_.mouseInfo.wheel = 0;
        
        plugins_quorum_Libraries_Game_WebInput_.IsFocused = function()
        {
            let accessibilityRoot;
            let game = plugins_quorum_Libraries_Game_GameStateManager_.game;
            if (game) {
                let accessibility = game.GetAccessibility();
                if (accessibility) {
                    accessibilityRoot = accessibility.plugin_.GetRoot();
                }
            }
            let canvas = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
            let element = document.activeElement;
            while (element) {
                if ((element === accessibilityRoot) || (element === canvas)) {
                    return true;
                }
                element = element.parentElement;
            }
            return false;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.TakeFocus = function()
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                return;
            }

            let game = plugins_quorum_Libraries_Game_GameStateManager_.game;
            let accessibility = game.GetAccessibility();
            accessibility.plugin_.InternalTakeFocus();
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ReleaseFocus = function()
        {
            if (!plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                return;
            }

            let game = plugins_quorum_Libraries_Game_GameStateManager_.game;
            let accessibility = game.GetAccessibility();
            accessibility.plugin_.InternalReleaseFocus();
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyDown = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, true);
                if (!plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = true;
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount++;
                    plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
                }
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyUp = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                if (event.code === "Escape" && plugins_quorum_Libraries_Game_WebInput_.keepTabFocus())
                {
                    // If we trap focus inside the canvas, give the user a way
                    // to get out. Do this in the key up handler to ensure
                    // Quorum gets the key up event for Escape, even though
                    // the canvas is about to lose focus.
                    plugins_quorum_Libraries_Game_WebInput_.ReleaseFocus();
                }

                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, false);
                if (plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = false;
                    plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount--;
                    plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
                }
            }
        };
        
        /*
        The mouse functions make use of these constants from the Quorum MouseEvent class:
        public constant integer CLICKED_MOUSE = 1
        public constant integer MOVED_MOUSE = 2
        public constant integer DRAGGED_MOUSE = 3
        public constant integer RELEASED_MOUSE = 4
        public constant integer SCROLLED_MOUSE = 5
        */

        plugins_quorum_Libraries_Game_WebInput_.IsMouseInCanvas = function(event)
        {
            var canvas = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
            var rect = canvas.getBoundingClientRect();
            return event.clientX >= rect.left && event.clientX <= rect.right && event.clientY >= rect.top && event.clientY <= rect.bottom;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseDown = function(event)
        {
            /*
             * Testing for mouse click using the dimensions of the rectangle
             * allows the first click on the window (i.e. the one that gives the
             * window focus) to trigger a mouse event, and prevents clicks from
             * outside the window being captured.
             */
            if (plugins_quorum_Libraries_Game_WebInput_.IsMouseInCanvas(event))
            {
                event.stopPropagation();
                event.preventDefault();
                plugins_quorum_Libraries_Game_WebInput_.TakeFocus();
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 1);
                plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseUp = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsMouseInCanvas(event))
            {
                event.stopPropagation();
                event.preventDefault();
            }

            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 4);
                plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseMove = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsMouseInCanvas(event))
            {
                event.stopPropagation();
                event.preventDefault();
            }

            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                // If no mouse buttons pressed, send code for MOVED_MOUSE. Otherwise, send DRAGGED_MOUSE.
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, event.buttons > 0 ? 3 : 2);
                plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseScroll = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsMouseInCanvas(event))
            {
                event.stopPropagation();
                event.preventDefault();
            }

            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 5);
                plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ContextMenu = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused())
            {
                if (plugins_quorum_Libraries_Game_WebInput_.disableContextMenu)
                {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
        };
        
        document.addEventListener('keydown', plugins_quorum_Libraries_Game_WebInput_.KeyDown, false);
        document.addEventListener('keyup', plugins_quorum_Libraries_Game_WebInput_.KeyUp, false);
        document.addEventListener('mousedown', plugins_quorum_Libraries_Game_WebInput_.MouseDown, false);
        document.addEventListener('mouseup', plugins_quorum_Libraries_Game_WebInput_.MouseUp, false);
        document.addEventListener('mousemove', plugins_quorum_Libraries_Game_WebInput_.MouseMove, false);
        document.addEventListener('wheel', plugins_quorum_Libraries_Game_WebInput_.MouseScroll, false);
        document.addEventListener('contextmenu', plugins_quorum_Libraries_Game_WebInput_.ContextMenu, false);
    
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent = function(event, pressed)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_KeyboardEvent_();
            if (pressed)
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PRESSED_KEY_());
            else
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RELEASED_KEY_());
            
            var createTextEvent = false;
            
            if (event.code !== undefined)
            {
                switch(event.code)
                {
                    case "Digit0":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_0_());
                        createTextEvent = true;
                        break;
                    case "Digit1":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_1_());
                        createTextEvent = true;
                        break;
                    case "Digit2":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_2_());
                        createTextEvent = true;
                        break;
                    case "Digit3":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_3_());
                        createTextEvent = true;
                        break;
                    case "Digit4":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_4_());
                        createTextEvent = true;
                        break;
                    case "Digit5":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_5_());
                        createTextEvent = true;
                        break;
                    case "Digit6":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_6_());
                        createTextEvent = true;
                        break;
                    case "Digit7":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_7_());
                        createTextEvent = true;
                        break;
                    case "Digit8":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_8_());
                        createTextEvent = true;
                        break;
                    case "Digit9":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_9_());
                        createTextEvent = true;
                        break;
                    case "AltLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_LEFT_());
                        break;
                    case "AltRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ALT_RIGHT_());
                        break;
                    case "Quote":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__APOSTROPHE_());
                        createTextEvent = true;
                        break;
                    case "Backslash":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSLASH_());
                        createTextEvent = true;
                        break;
                    case "Comma":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__COMMA_());
                        createTextEvent = true;
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
                        createTextEvent = true;
                        break;
                    case "Backquote":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__GRAVE_());
                        createTextEvent = true;
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
                        createTextEvent = true;
                        break;
                    case "BracketRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_BRACKET_());
                        createTextEvent = true;
                        break;
                    case "Minus":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__MINUS_());
                        createTextEvent = true;
                        break;
                    case "Period":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PERIOD_());
                        createTextEvent = true;
                        break;
                    case "Semicolon":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SEMICOLON_());
                        createTextEvent = true;
                        break;
                    case "ShiftLeft":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_LEFT_());
                        break;
                    case "ShiftRight":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SHIFT_RIGHT_());
                        break;
                    case "Slash":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SLASH_());
                        createTextEvent = true;
                        break;
                    case "Space":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SPACE_());
                        createTextEvent = true;
                        break;
                    case "NumpadAdd":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_PLUS_());
                        createTextEvent = true;
                        break;
                    case "NumpadMultiply":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_STAR_());
                        createTextEvent = true;
                        break;
                    case "NumpadSubtract":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_MINUS_());
                        createTextEvent = true;
                        break;
                    case "NumpadDivide":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_SLASH_());
                        createTextEvent = true;
                        break;
                    case "NumpadDecimal":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_DECIMAL_());
                        createTextEvent = true;
                        break;
                    case "NumpadEnter":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_ENTER_());
                        break;
                    case "NumpadEqual":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_EQUALS_());
                        createTextEvent = true;
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
                        createTextEvent = true;
                        break;
                    case "Numpad1":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_1_());
                        createTextEvent = true;
                        break;
                    case "Numpad2":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_2_());
                        createTextEvent = true;
                        break;
                    case "Numpad3":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_3_());
                        createTextEvent = true;
                        break;
                    case "Numpad4":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_4_());
                        createTextEvent = true;
                        break;
                    case "Numpad5":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_5_());
                        createTextEvent = true;
                        break;
                    case "Numpad6":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_6_());
                        createTextEvent = true;
                        break;
                    case "Numpad7":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_7_());
                        createTextEvent = true;
                        break;
                    case "Numpad8":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_8_());
                        createTextEvent = true;
                        break;
                    case "Numpad9":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_9_());
                        createTextEvent = true;
                        break;
                    case "KeyA":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__A_());
                        createTextEvent = true;
                        break;
                    case "KeyB":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__B_());
                        createTextEvent = true;
                        break;
                    case "KeyC":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__C_());
                        createTextEvent = true;
                        break;
                    case "KeyD":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        createTextEvent = true;
                        break;
                    case "KeyE":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__E_());
                        createTextEvent = true;
                        break;
                    case "KeyF":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F_());
                        createTextEvent = true;
                        break;
                    case "KeyG":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__G_());
                        createTextEvent = true;
                        break;
                    case "KeyH":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__H_());
                        createTextEvent = true;
                        break;
                    case "KeyI":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__I_());
                        createTextEvent = true;
                        break;
                    case "KeyJ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__J_());
                        createTextEvent = true;
                        break;
                    case "KeyK":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__K_());
                        createTextEvent = true;
                        break;
                    case "KeyL":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__L_());
                        createTextEvent = true;
                        break;
                    case "KeyM":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__M_());
                        createTextEvent = true;
                        break;
                    case "KeyN":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__N_());
                        createTextEvent = true;
                        break;
                    case "KeyO":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__O_());
                        createTextEvent = true;
                        break;
                    case "KeyP":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__P_());
                        createTextEvent = true;
                        break;
                    case "KeyQ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Q_());
                        createTextEvent = true;
                        break;
                    case "KeyR":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__R_());
                        createTextEvent = true;
                        break;
                    case "KeyS":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__S_());
                        createTextEvent = true;
                        break;
                    case "KeyT":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__T_());
                        createTextEvent = true;
                        break;
                    case "KeyU":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__U_());
                        createTextEvent = true;
                        break;
                    case "KeyV":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__V_());
                        createTextEvent = true;
                        break;
                    case "KeyW":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__W_());
                        createTextEvent = true;
                        break;
                    case "KeyX":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__X_());
                        createTextEvent = true;
                        break;
                    case "KeyY":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Y_());
                        createTextEvent = true;
                        break;
                    case "KeyZ":
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Z_());
                        createTextEvent = true;
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
                {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
            else
            {
                switch(event.keyCode)
                {
                    // Numbers, 0-9, above the letters on the keyboard.
                    case 48:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_0_());
                        createTextEvent = true;
                        break;
                    case 49:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_1_());
                        createTextEvent = true;
                        break;
                    case 50:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_2_());
                        createTextEvent = true;
                        break;
                    case 51:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_3_());
                        createTextEvent = true;
                        break;
                    case 52:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_4_());
                        createTextEvent = true;
                        break;
                    case 53:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_5_());
                        createTextEvent = true;
                        break;
                    case 54:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_6_());
                        createTextEvent = true;
                        break;
                    case 55:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_7_());
                        createTextEvent = true;
                        break;
                    case 56:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_8_());
                        createTextEvent = true;
                        break;
                    case 57:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUM_9_());
                        createTextEvent = true;
                        break;
                    // Numbers, 0-9, on the numpad.
                    case 96:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_0_());
                        createTextEvent = true;
                        break;
                    case 97:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_1_());
                        createTextEvent = true;
                        break;
                    case 98:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_2_());
                        createTextEvent = true;
                        break;
                    case 99:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_3_());
                        createTextEvent = true;
                        break;
                    case 100:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_4_());
                        createTextEvent = true;
                        break;
                    case 101:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_5_());
                        createTextEvent = true;
                        break;
                    case 102:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_6_());
                        createTextEvent = true;
                        break;
                    case 103:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_7_());
                        createTextEvent = true;
                        break;
                    case 104:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_8_());
                        createTextEvent = true;
                        break;
                    case 105:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_9_());
                        createTextEvent = true;
                        break;
                    // Letters on the keyboard.
                    case 65:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__A_());
                        createTextEvent = true;
                        break;
                    case 66:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__B_());
                        createTextEvent = true;
                        break;
                    case 67:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__C_());
                        createTextEvent = true;
                        break;
                    case 68:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__D_());
                        createTextEvent = true;
                        break;
                    case 69:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__E_());
                        createTextEvent = true;
                        break;
                    case 70:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__F_());
                        createTextEvent = true;
                        break;
                    case 71:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__G_());
                        createTextEvent = true;
                        break;
                    case 72:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__H_());
                        createTextEvent = true;
                        break;
                    case 73:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__I_());
                        createTextEvent = true;
                        break;
                    case 74:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__J_());
                        createTextEvent = true;
                        break;
                    case 75:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__K_());
                        createTextEvent = true;
                        break;
                    case 76:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__L_());
                        createTextEvent = true;
                        break;
                    case 77:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__M_());
                        createTextEvent = true;
                        break;
                    case 78:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__N_());
                        createTextEvent = true;
                        break;
                    case 79:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__O_());
                        createTextEvent = true;
                        break;
                    case 80:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__P_());
                        createTextEvent = true;
                        break;
                    case 81:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Q_());
                        createTextEvent = true;
                        break;
                    case 82:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__R_());
                        createTextEvent = true;
                        break;
                    case 83:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__S_());
                        createTextEvent = true;
                        break;
                    case 84:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__T_());
                        createTextEvent = true;
                        break;
                    case 85:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__U_());
                        createTextEvent = true;
                        break;
                    case 86:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__V_());
                        createTextEvent = true;
                        break;
                    case 87:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__W_());
                        createTextEvent = true;
                        break;
                    case 88:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__X_());
                        createTextEvent = true;
                        break;
                    case 89:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Y_());
                        createTextEvent = true;
                        break;
                    case 90:
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__Z_());
                        createTextEvent = true;
                        break;
                    // Unique keys on the keyboard that aren't part of the numpad.
                    case 222: // Apostrophe/Quote
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__APOSTROPHE_());
                        createTextEvent = true;
                        break;
                    case 188: // Comma / Left Angled Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__COMMA_());
                        createTextEvent = true;
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
                        createTextEvent = true;
                        break;
                    case 220: // Backslash
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__BACKSLASH_());
                        createTextEvent = true;
                        break;
                    case 13: // Enter
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__ENTER_());
                        break;
                    case 192: // Grave/Tilde
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__GRAVE_());
                        createTextEvent = true;
                        break;
                    case 187: // Equal/Plus
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__EQUALS_());
                        createTextEvent = true;
                        break;
                    case 189: // Minus/Underscore
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__MINUS_());
                        createTextEvent = true;
                        break;
                    case 190: // Period / Right Angled Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PERIOD_());
                        createTextEvent = true;
                        break;
                    case 219: // Left Bracket / Left Curly Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__LEFT_BRACKET_());
                        createTextEvent = true;
                        break;
                    case 221: // Right Bracket / Right Curly Bracket
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RIGHT_BRACKET_());
                        createTextEvent = true;
                        break;
                    case 186: // Semicolon / Colon
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__SEMICOLON_());
                        createTextEvent = true;
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
                        createTextEvent = true;
                        break;
                    case 107: // Numpad +
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_PLUS_());
                        createTextEvent = true;
                        break;
                    case 109: // Numpad -
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_MINUS_());
                        createTextEvent = true;
                        break;
                    case 111: // Numpad /
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_SLASH_());
                        createTextEvent = true;
                        break;
                    case 110: // Numpad Decimal
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__NUMPAD_DECIMAL_());
                        createTextEvent = true;
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
                {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
            
            if (createTextEvent && pressed)
                plugins_quorum_Libraries_Game_WebInput_.CreateQuorumTextInputEvent(event);
            
            return quorumEvent;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.CreateQuorumTextInputEvent = function(event)
        {
            if (event.ctrlKey || event.altKey || event.metaKey)
                return;
            
            var quorumEvent = new quorum_Libraries_Interface_Events_TextInputEvent_();
            
            if (event.key != null)
                quorumEvent.SetText$quorum_text(event.key);
            else
                return;
            
            quorumEvent.SetUnicodeValue$quorum_integer(event.key.charCodeAt(0));
            plugins_quorum_Libraries_Game_WebInput_.textEvents.push(quorumEvent);;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent = function(event, code)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_MouseEvent_();
            
            var canvas = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
            var rect = canvas.getBoundingClientRect();
            
            var x = event.clientX - rect.left;
            var y = rect.bottom - event.clientY;
            
            if (x < 0)
                x = 0;
            else if (x > rect.width)
                x = rect.width;
            
            if (y < 0)
                y = 0;
            else if (y > rect.height)
                y = rect.height;
            
            quorumEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(code);
            quorumEvent.Set_Libraries_Interface_Events_MouseEvent__x_(x);
            quorumEvent.Set_Libraries_Interface_Events_MouseEvent__y_(y);
            quorumEvent.Set_Libraries_Interface_Events_MouseEvent__movementX_(event.movementX);
            quorumEvent.Set_Libraries_Interface_Events_MouseEvent__movementY_(event.movementY);
            
            if (code === quorumEvent.Get_Libraries_Interface_Events_MouseEvent__MOVED_MOUSE_() || code === quorumEvent.Get_Libraries_Interface_Events_MouseEvent__SCROLLED_MOUSE_())
            {
                quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__NONE_());
            }
            else
            {
                switch(event.button)
                {
                    case 0:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__LEFT_());
                        break;
                    case 1:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__MIDDLE_());
                        break;
                    case 2:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__RIGHT_());
                        break;
                    case 3:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__BACK_());
                        break;
                    case 4:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__FRONT_());
                        break;
                    default:
                        quorumEvent.Set_Libraries_Interface_Events_MouseEvent__mouseButton_(quorumEvent.Get_Libraries_Interface_Events_MouseEvent__ANY_());
                }
            }
            
            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.x = x;
            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.y = y;
            
            if (event.buttons)
                plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = event.buttons;
            else
            {
                if (code === 1)
                    switch(event.button)
                    {
                        case 0:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 1;
                            break;
                        case 1:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 4;
                            break;
                        case 2:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 2;
                            break;
                        case 3:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 8;
                            break;
                        case 4:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 16;
                            break;
                    }
                else if (code === 4)
                    switch(event.button)
                    {
                        case 0:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !1;
                            break;
                        case 1:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !4;
                            break;
                        case 2:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !2;
                            break;
                        case 3:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !8;
                            break;
                        case 4:
                            plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !16;
                            break;
                    }
                else if (code === 2 && plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons > 0)
                    quorumEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(3);
            }
            
            if (event.deltaY !== undefined)
            {
                quorumEvent.Set_Libraries_Interface_Events_MouseEvent__scrollAmount_(-event.deltaY | 0);
                plugins_quorum_Libraries_Game_WebInput_.mouseInfo.wheel = -event.deltaY;
            }
            
            return quorumEvent;
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
    
    var lastWidth = 0;
    var lastHeight = 0;
    
    this.CheckForEvents$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array = function(mouseEvents, keyEvents, textEvents, resizeEvents)
    {
        // We manually construct the resize events here.
        var display = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
        if (lastWidth !== display.clientWidth || lastHeight !== display.clientHeight)
        {
            lastWidth = display.clientWidth;
            lastHeight = display.clientHeight;
            display.width = display.clientWidth;
            display.height = display.clientHeight;
            
            var quorumEvent = new quorum_Libraries_Interface_Events_ResizeEvent_();
            quorumEvent.SetWidth$quorum_integer(lastWidth);
            quorumEvent.SetHeight$quorum_integer(lastHeight);
            resizeEvents.Add$quorum_Libraries_Language_Object(quorumEvent);
        }
        
        for (var i = 0; i < plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.length; i++)
        {
            keyEvents.Add$quorum_Libraries_Language_Object(plugins_quorum_Libraries_Game_WebInput_.keyboardEvents[i]);
        }
        
        for (var i = 0; i < plugins_quorum_Libraries_Game_WebInput_.mouseEvents.length; i++)
        {
            mouseEvents.Add$quorum_Libraries_Language_Object(plugins_quorum_Libraries_Game_WebInput_.mouseEvents[i]);
        }
        
        for (var i = 0; i < plugins_quorum_Libraries_Game_WebInput_.textEvents.length; i++)
        {
            textEvents.Add$quorum_Libraries_Language_Object(plugins_quorum_Libraries_Game_WebInput_.textEvents[i]);
        }
        
        plugins_quorum_Libraries_Game_WebInput_.keyboardEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.mouseEvents = [];
        plugins_quorum_Libraries_Game_WebInput_.textEvents = [];
    };
}
