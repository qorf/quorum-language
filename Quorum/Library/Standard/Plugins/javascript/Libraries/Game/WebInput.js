function plugins_quorum_Libraries_Game_WebInput_()
{
    // Lazy-loaded value. Cached the first time HasTouchScreen() is called.
    let supportsTouchScreen = undefined;

    /*
    Whether or not the virtual keyboard is currently open. This is checked in a couple ways:
    1. Try to use the VirtualKeyboard API: https://developer.mozilla.org/en-US/docs/Web/API/VirtualKeyboard_API
        If this API is supported, we can detect when the virtual keyboard opens/closes by watching for changes in its bounding box via geometrychange events.
        This is the most reliable method for supported platforms, but as of 7/16/2024 support is limited (e.g. Firefox and Safari do not support it).
    2. Try to use the VisualViewport API: https://developer.mozilla.org/en-US/docs/Web/API/Visual_Viewport_API
        We can make an educated guess using the size of the web page's viewport relative to the total screen height.
        If more than a quarter of the screen height isn't being used for the viewport (or in other words, more than 25% of the screen is rendering something
        other than our web page), there's a high likelihood that it's being used for a virtual keyboard.
        We do this in conjunction with browser detection to only use this on mobile or tablet devices. On desktop browsers,
        we don't want to risk accidentally turning off keyboard support if the web page isn't maximized to the whole screen.
    3. If we can't use either of these, as a last resort fall back to browser detection. We can't tell if the virtual keyboard is open at any time or not,
        so we react as if the virtual keyboard might be open at any time if using a mobile web browser.
    */
    let virtualKeyboardOpen = false;

    // Constants to determine how we're evaluating the virtual keyboard being open or not.
    const DETECTION_STYLE_UNINITIALIZED = 0;
    const DETECTION_STYLE_VIRTUAL_KEYBOARD = 1;
    const DETECTION_STYLE_VISUAL_VIEWPORT = 2;
    const DETECTION_STYLE_BROWSER_DETECTION = 3;
    let virtualKeyboardDetection = DETECTION_STYLE_UNINITIALIZED;

    if (!plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_)
    {
        /*
        This value indicates if we have failed to capture information via KeyDown on the last key event.
        If so, this can result in missing events, especially text events we need for TextBox/TextField.
        In this specific case, we rely on Input event handlers from the hidden text areas in shadow DOM.
        Importantly, though, we don't want to double-input if we are correctly processing KeyDown inputs,
        so we need to track this on a per-key basis.

        This particular case often occurs when using the default keyboard on Android devices.
        */
        plugins_quorum_Libraries_Game_WebInput_.lastKeyDownFailed = false;

        // Returns true if the browser supports touch, false otherwise.
        plugins_quorum_Libraries_Game_WebInput_.HasTouchScreen = function()
        {
            if (supportsTouchScreen !== undefined)
                return supportsTouchScreen;

            // Implementation taken with minor adaptations from the MDN documentation here: https://developer.mozilla.org/en-US/docs/Web/HTTP/Browser_detection_using_the_user_agent
            let navigator = window.navigator;
            let hasTouchScreen = false;
            if ("maxTouchPoints" in navigator)
            {
                hasTouchScreen = navigator.maxTouchPoints > 0;
            }
            else if ("msMaxTouchPoints" in navigator)
            {
                hasTouchScreen = navigator.msMaxTouchPoints > 0;
            }
            else
            {
                const mQ = window.matchMedia?.("(pointer:coarse)");
                if (mQ?.media === "(pointer:coarse)")
                {
                    hasTouchScreen = !!mQ.matches;
                }
                else if ("orientation" in window)
                {
                    hasTouchScreen = true; // deprecated, but good fallback
                }
                else
                {
                // Only as a last resort, fall back to user agent sniffing
                const UA = navigator.userAgent;
                hasTouchScreen =
                  /\b(BlackBerry|webOS|iPhone|IEMobile)\b/i.test(UA) ||
                  /\b(Android|Windows Phone|iPad|iPod)\b/i.test(UA);
                }
            }

            supportsTouchScreen = hasTouchScreen;
            return supportsTouchScreen;
        };

        plugins_quorum_Libraries_Game_WebInput_.IsMobileBrowser = function()
        {
            // This unpleasant blob of code uses a very convoluted regular expression to try to determine what browser is being used.
            // It tries to scrape the information from the userAgent info, or falls back to vendor or Opera-specific old values just in case.
            // The code is taken from this StackOverflow post: https://stackoverflow.com/questions/11381673/detecting-a-mobile-browser
            let check = false;
            (function(a)
            {
                if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))
                    check = true;
            }) (navigator.userAgent||navigator.vendor||window.opera);

            return check;
        }

        plugins_quorum_Libraries_Game_WebInput_.IsMobileOrTabletBrowser = function()
        {
            // This unpleasant blob of code uses a very convoluted regular expression to try to determine what browser is being used.
            // This version includes tablet browsers, in addition to phones and similar "traditional" mobile devices.
            // It tries to scrape the information from the userAgent info, or falls back to vendor or Opera-specific old values just in case.
            // The code is taken from this StackOverflow post: https://stackoverflow.com/questions/11381673/detecting-a-mobile-browser
            let check = false;
            (function(a)
            {
                if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino|android|ipad|playbook|silk/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))
                    check = true;
            }) (navigator.userAgent||navigator.vendor||window.opera);
            return check;
        }

        plugins_quorum_Libraries_Game_WebInput_.IsVirtualKeyboardOpen = function()
        {
            return virtualKeyboardOpen;
        }

        plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardShow = function()
        {
            virtualKeyboardOpen = true;
        }

        plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardHide = function()
        {
            virtualKeyboardOpen = false;
        }

        // If we previously defined the listener functions, remove the old ones before we do any more initialization.
        if (plugins_quorum_Libraries_Game_WebInput_.KeyDown !== undefined)
        {
            document.removeEventListener('keydown', plugins_quorum_Libraries_Game_WebInput_.KeyDown);
            document.removeEventListener('keyup', plugins_quorum_Libraries_Game_WebInput_.KeyUp);
            document.removeEventListener('pointerdown', plugins_quorum_Libraries_Game_WebInput_.MouseDown);
            document.removeEventListener('pointerup', plugins_quorum_Libraries_Game_WebInput_.MouseUp);
            document.removeEventListener('pointermove', plugins_quorum_Libraries_Game_WebInput_.MouseMove);
            document.removeEventListener('contextmenu', plugins_quorum_Libraries_Game_WebInput_.ContextMenu);
            document.removeEventListener('wheel', plugins_quorum_Libraries_Game_WebInput_.MouseScroll);
        }

        plugins_quorum_Libraries_Game_WebInput_.IsFocused = function(targetGame)
        {
            let accessibilityRoot;
            let game = targetGame;
            if (game) {
                let accessibility = game.GetAccessibility();
                if (accessibility) {
                    accessibilityRoot = accessibility.plugin_.GetRoot();
                }
            }
            let element = document.activeElement;
            while (element) {
                if (element === accessibilityRoot) {
                    return true;
                }
                element = element.parentElement;
            }
            return false;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.TakeFocus = function(game)
        {
            if (game == null)
                return;

            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(game))
            {
                return;
            }

            let accessibility = game.GetAccessibility();
            accessibility.plugin_.InternalTakeFocus();
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ReleaseFocus = function(game)
        {
            if (game == null)
                return;

            if (!plugins_quorum_Libraries_Game_WebInput_.IsFocused(game))
            {
                return;
            }

            let accessibility = game.GetAccessibility();
            accessibility.plugin_.InternalReleaseFocus();
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyDown = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsVirtualKeyboardOpen() === true)
            {
                // If the virtual keyboard is open, don't process the key event.
                // If the user is in a TextBox/TextField, rely on text input events instead.
                return;
            }

            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetFocusedGameInfo();

            if (gameInfo !== null)
            {
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, true, gameInfo);
                if (!gameInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    gameInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = true;
                    gameInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount++;
                    gameInfo.plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
                }
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.KeyUp = function(event)
        {
            if (plugins_quorum_Libraries_Game_WebInput_.IsVirtualKeyboardOpen() === true)
            {
                // If the virtual keyboard is open, don't process the key event.
                // If the user is in a TextBox/TextField, rely on text input events instead.
                return;
            }

            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetFocusedGameInfo();

            if (gameInfo)
            {
                if (event.code === "Escape" && gameInfo.game)
                {
                    // Since we trap focus inside the canvas, give the user a way
                    // to get out. Do this in the key up handler to ensure
                    // Quorum gets the key up event for Escape, even though
                    // the canvas is about to lose focus.
                    plugins_quorum_Libraries_Game_WebInput_.ReleaseFocus(gameInfo.game);
                }
            }

            // Iterate over every registered game. If any game thinks that key is being held, inform it when it is released,
            // even if that game doesn't currently have the focus.
            // Prevents issues where keys get "stuck" because focus changed between a key being pressed and released.
            var map = plugins_quorum_Libraries_Game_GameStateManager_.registeredGames;
            for (let [key, value] of map)
            {
                let currentInfo = value;

                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent(event, false, currentInfo);
                if (currentInfo && currentInfo.plugins_quorum_Libraries_Game_WebInput_ && currentInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()])
                {
                    currentInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeys[quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__keyCode_()] = false;
                    currentInfo.plugins_quorum_Libraries_Game_WebInput_.pressedKeysCount--;
                    currentInfo.plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.push(quorumEvent);
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

        // Return the GameInfo from the GameStateManager that's associated with the canvas that was moused over, if any.
        // If the event wasn't over a canvas, returns null.
        plugins_quorum_Libraries_Game_WebInput_.GetMousedGameInfo = function(event)
        {
            var map = plugins_quorum_Libraries_Game_GameStateManager_.registeredGames;
            for (let [key, value] of map)
            {
                var gameInfo = value;

                let target = event.target;
                let canvas = null;
                if (gameInfo.display != null && gameInfo.display.plugin_.GetCanvas())
                    canvas = gameInfo.display.plugin_.GetCanvas();

                if (target && canvas && (target === canvas))
                {
                    return gameInfo;
                }
            }

            return null;
        };

        plugins_quorum_Libraries_Game_WebInput_.GetFocusedGameInfo = function()
        {
            var map = plugins_quorum_Libraries_Game_GameStateManager_.registeredGames;
            for (let [key, value] of map)
            {
                var gameInfo = value;
                if (gameInfo.display != null && plugins_quorum_Libraries_Game_WebInput_.IsFocused(gameInfo.game))
                    return gameInfo;
            }

            return null;
        };

        plugins_quorum_Libraries_Game_WebInput_.MouseDown = function(event)
        {
            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetMousedGameInfo(event);

            if (gameInfo !== null)
            {
                event.stopPropagation();
                event.preventDefault();
                plugins_quorum_Libraries_Game_WebInput_.TakeFocus(gameInfo.game);
                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 1, gameInfo);
                gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseUp = function(event)
        {
            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetMousedGameInfo(event);

            if (gameInfo !== null)
            {
                event.stopPropagation();
                event.preventDefault();

                if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(gameInfo.game))
                {
                    var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 4, gameInfo);
                    gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
                }
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseMove = function(event)
        {
            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetMousedGameInfo(event);

            if (gameInfo !== null)
            {
                event.stopPropagation();
                event.preventDefault();

                if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(gameInfo.game))
                {
                    // If no mouse buttons pressed, send code for MOVED_MOUSE. Otherwise, send DRAGGED_MOUSE.
                    var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, event.buttons > 0 ? 3 : 2, gameInfo);
                    gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
                }
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.MouseScroll = function(event)
        {
            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetMousedGameInfo(event);

            if (gameInfo !== null && plugins_quorum_Libraries_Game_WebInput_.IsFocused(gameInfo.game))
            {
                event.stopPropagation();
                event.preventDefault();

                var quorumEvent = plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent(event, 5, gameInfo);
                gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents.push(quorumEvent);
            }
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ContextMenu = function(event)
        {
            var gameInfo = plugins_quorum_Libraries_Game_WebInput_.GetFocusedGameInfo();

            if (gameInfo !== null)
            {
                if (plugins_quorum_Libraries_Game_WebInput_.disableContextMenu)
                {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
        };

        plugins_quorum_Libraries_Game_WebInput_.InputText = function(event)
        {
            let eventTarget = event.target;

            /*
            When the text is directly modified in a shadow DOM text box or text field,
            update the corresponding Quorum element to match.
            */
            if (eventTarget.quorumItem && eventTarget.quorumItem.SetText$quorum_text)
            {
                let quorumItem = eventTarget.quorumItem;
                let selection = eventTarget.quorumItem.GetSelection();

                let startIndex = eventTarget.selectionStart;
                let endIndex = eventTarget.selectionEnd;

                if (quorumItem.GetText() !== eventTarget.value)
                {
                    quorumItem.SetText$quorum_text(eventTarget.value);

                    // Update the selection indices, if possible.
                    if (selection && selection.Set$quorum_integer$quorum_integer$quorum_boolean$quorum_boolean)
                    {
                        selection.Set$quorum_integer$quorum_integer$quorum_boolean$quorum_boolean(startIndex, endIndex, true, false);
                    }

                    // Ensure the caret is in the right spot.
                    if (quorumItem.SetCaretPosition$quorum_integer)
                    {
                        quorumItem.SetCaretPosition$quorum_integer(endIndex);
                    }

                    // Changing the Quorum text updates the position of the selection in shadow DOM. Make sure it's set back to correct values.
                    eventTarget.selectionStart = startIndex;
                    eventTarget.selectionEnd = endIndex;

                    return;
                }
            }

            /*
            The last key down event may have failed if the keyboard didn't support it (probably because it's the Android
            soft keyboard). If the last event failed and we couldn't set a text box / text field directly, see if the
            input event has any useful information that we can manually send as a Quorum TextInputEvent.
            */
            if (plugins_quorum_Libraries_Game_WebInput_.lastKeyDownFailed === false || event.data == null || event.data === "")
                return;

            var quorumEvent = new quorum_Libraries_Interface_Events_TextInputEvent_();
            quorumEvent.SetText$quorum_text(event.data);

            quorumEvent.SetUnicodeValue$quorum_integer(event.data.charCodeAt(0));
            plugins_quorum_Libraries_Game_WebInput_.textEvents.push(quorumEvent);
        };

        plugins_quorum_Libraries_Game_WebInput_.PointerCancel = function(event)
        {
            plugins_quorum_Libraries_Game_WebInput_.MouseUp(event);
        }

        // Determine how we're going to listen for virtual keyboard usage, based on what tools are available in the browser.
        if (virtualKeyboardDetection === DETECTION_STYLE_UNINITIALIZED)
        {
            // Code adapted from sample code provided in MDN documentation: https://developer.mozilla.org/en-US/docs/Web/API/VirtualKeyboard_API
            if ("virtualKeyboard" in navigator)
            {
                virtualKeyboardDetection = DETECTION_STYLE_VIRTUAL_KEYBOARD;
                navigator.virtualKeyboard.overlaysContent = true;

                navigator.virtualKeyboard.addEventListener("geometrychange", (event) =>
                {
                    const { x, y, width, height } = event.target.boundingRect;
                    if (height > 1)
                    {
                        plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardShow();
                    }
                    else
                    {
                        plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardHide();
                    }
                });
            }
            // Approach using VisualViewport is adapated from this StackOverflow post: https://stackoverflow.com/questions/47841986/detecting-the-opening-or-closing-of-a-virtual-keyboard-on-a-touchscreen-device
            else if ("visualViewport" in window)
            {
                virtualKeyboardDetection = DETECTION_STYLE_VISUAL_VIEWPORT;
                // Note that we only actually apply this method if we're using a device we suspect will have a virtual keyboard.
                if (plugins_quorum_Libraries_Game_WebInput_.IsMobileOrTabletBrowser())
                {
                    const VIEWPORT_VS_CLIENT_HEIGHT_RATIO = 0.75;
                    window.visualViewport.addEventListener("resize", function (event)
                    {
                        var heightOfViewportRatio = (event.target.height * event.target.scale) / window.screen.height;
                        if (heightOfViewportRatio < VIEWPORT_VS_CLIENT_HEIGHT_RATIO)
                        {
                            plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardShow();
                        }
                        else
                        {
                            plugins_quorum_Libraries_Game_WebInput_.OnVirtualKeyboardHide();
                        }
                    });
                }
            }
            // If we don't have anything else available, presume that the virtual keyboard is always open on mobile/tablet browsers, for the purposes of key up/down processing.
            // Note that this case should occur only rarely, as the VisualViewport API is widely supported.
            else
            {
                virtualKeyboardDetection = DETECTION_STYLE_BROWSER_DETECTION;
                virtualKeyboardOpen = plugins_quorum_Libraries_Game_WebInput_.IsMobileOrTabletBrowser();
            }
        }

        document.addEventListener('keydown', plugins_quorum_Libraries_Game_WebInput_.KeyDown, false);
        document.addEventListener('keyup', plugins_quorum_Libraries_Game_WebInput_.KeyUp, false);
        document.addEventListener('pointerdown', plugins_quorum_Libraries_Game_WebInput_.MouseDown, false);
        document.addEventListener('pointerup', plugins_quorum_Libraries_Game_WebInput_.MouseUp, false);
        document.addEventListener('pointermove', plugins_quorum_Libraries_Game_WebInput_.MouseMove, false);
        document.addEventListener('pointercancel', plugins_quorum_Libraries_Game_WebInput_.MouseCancel, false);
        document.addEventListener('contextmenu', plugins_quorum_Libraries_Game_WebInput_.ContextMenu, false);

        /*
        NOTE: The wheel listener requires the "passive: false" parameter, or else it can't prevent event propagation.
        This is because Chrome automatically treats document-level mousewheel listeners as "passive" (and therefore
        unable to stop the events) for performance reasons. This technically means a minor performance penalty, but
        if we don't do this, the whole web page will scroll at the same time as our application.
        */
        document.addEventListener('wheel', plugins_quorum_Libraries_Game_WebInput_.MouseScroll, {passive: false});
    
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumKeyEvent = function(event, pressed, gameInfo)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_KeyboardEvent_();
            if (pressed)
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__PRESSED_KEY_());
            else
                quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__eventType_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__RELEASED_KEY_());
            
            var createTextEvent = false;

            // Always starts as false. Will be set to true if we fall into a default "key unknown" case.
            plugins_quorum_Libraries_Game_WebInput_.lastKeyDownFailed = false;

            if (event.code !== undefined && event.code !== "")
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
                        plugins_quorum_Libraries_Game_WebInput_.lastKeyDownFailed = true;
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UNKNOWN_());
                }
                
                event.stopPropagation();
                event.preventDefault();
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
                        plugins_quorum_Libraries_Game_WebInput_.lastKeyDownFailed = true;
                        quorumEvent.Set_Libraries_Interface_Events_KeyboardEvent__keyCode_(quorumEvent.Get_Libraries_Interface_Events_KeyboardEvent__UNKNOWN_());
                }
                
                event.stopPropagation();
                event.preventDefault();
            }
            
            if (createTextEvent && pressed)
                plugins_quorum_Libraries_Game_WebInput_.CreateQuorumTextInputEvent(event, gameInfo);
            
            return quorumEvent;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.CreateQuorumTextInputEvent = function(event, gameInfo)
        {
            if (event.ctrlKey || event.altKey || event.metaKey)
                return;
            
            var quorumEvent = new quorum_Libraries_Interface_Events_TextInputEvent_();
            
            if (event.key != null)
                quorumEvent.SetText$quorum_text(event.key);
            else
                return;

            quorumEvent.SetUnicodeValue$quorum_integer(event.key.charCodeAt(0));
            gameInfo.plugins_quorum_Libraries_Game_WebInput_.textEvents.push(quorumEvent);
        };
        
        plugins_quorum_Libraries_Game_WebInput_.ConvertToQuorumMouseEvent = function(event, code, gameInfo)
        {
            var quorumEvent = new quorum_Libraries_Interface_Events_MouseEvent_();
            
            var canvas = gameInfo.display.plugin_.GetCanvas();
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

            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.x = x;
            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.y = y;
            
            if (event.buttons)
                gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = event.buttons;
            else
            {
                if (code === 1)
                    switch(event.button)
                    {
                        case 0:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 1;
                            break;
                        case 1:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 4;
                            break;
                        case 2:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 2;
                            break;
                        case 3:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 8;
                            break;
                        case 4:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons | 16;
                            break;
                    }
                else if (code === 4)
                    switch(event.button)
                    {
                        case 0:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !1;
                            break;
                        case 1:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !4;
                            break;
                        case 2:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !2;
                            break;
                        case 3:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !8;
                            break;
                        case 4:
                            gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons = gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons & !16;
                            break;
                    }
                else if (code === 2 && gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.buttons > 0)
                    quorumEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(3);
            }
            
            if (event.deltaY !== undefined)
            {
                quorumEvent.Set_Libraries_Interface_Events_MouseEvent__scrollAmount_(-event.deltaY | 0);
                gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseInfo.wheel = -event.deltaY;
            }
            
            return quorumEvent;
        };
        
        plugins_quorum_Libraries_Game_WebInput_.disableContextMenu = function()
        {
            return plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().application.plugin_.GetConfiguration().Get_Libraries_Game_WebConfiguration__disableContextMenu_();
        };
    
        plugins_quorum_Libraries_Game_WebInput_.initialized_plugins_quorum_Libraries_Game_WebInput_ = true;
    }
    
    var lastWidth = 0;
    var lastHeight = 0;
    
    this.CheckForEvents$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array$quorum_Libraries_Containers_Array = function(mouseEvents, keyEvents, textEvents, resizeEvents)
    {
        var gameInfo = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo();

        // Ensure the game info has been initialized for input.
        if (gameInfo.plugins_quorum_Libraries_Game_WebInput_ == undefined)
        {
            gameInfo.plugins_quorum_Libraries_Game_WebInput_ =
            {
                mouseEvents: [],
                keyboardEvents: [],
                textEvents: [],
                resizeEvents: [],
                pressedKeys: {},
                pressedKeysCount: 0,
                mouseInfo:
                {
                    x: 0,
                    y: 0,
                    buttons: 0,
                    wheel: 0,
                },
            }
        }

        // We manually construct the resize events here.
        var display = gameInfo.display.plugin_.GetCanvas();
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
        
        for (var i = 0; i < gameInfo.plugins_quorum_Libraries_Game_WebInput_.keyboardEvents.length; i++)
        {
            keyEvents.Add$quorum_Libraries_Language_Object(gameInfo.plugins_quorum_Libraries_Game_WebInput_.keyboardEvents[i]);
        }
        
        for (var i = 0; i < gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents.length; i++)
        {
            mouseEvents.Add$quorum_Libraries_Language_Object(gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents[i]);
        }
        
        for (var i = 0; i < gameInfo.plugins_quorum_Libraries_Game_WebInput_.textEvents.length; i++)
        {
            textEvents.Add$quorum_Libraries_Language_Object(gameInfo.plugins_quorum_Libraries_Game_WebInput_.textEvents[i]);
        }
        
        gameInfo.plugins_quorum_Libraries_Game_WebInput_.keyboardEvents = [];
        gameInfo.plugins_quorum_Libraries_Game_WebInput_.mouseEvents = [];
        gameInfo.plugins_quorum_Libraries_Game_WebInput_.textEvents = [];
    };
}
