// The names of keys, by Quorum key code, as the desktop version
// (plugins.quorum.Libraries.Interface.Events.KeyboardEvent) gives them. A
// code with no name returns "Unknown", as on the desktop.
function plugins_quorum_Libraries_Interface_Events_KeyboardEvent_() {
    var plainNames = {
        0: "Unknown", // UNKNOWN
        3: "Home", // HOME
        7: "0", // NUM_0
        8: "1", // NUM_1
        9: "2", // NUM_2
        10: "3", // NUM_3
        11: "4", // NUM_4
        12: "5", // NUM_5
        13: "6", // NUM_6
        14: "7", // NUM_7
        15: "8", // NUM_8
        16: "9", // NUM_9
        19: "Up", // UP
        20: "Down", // DOWN
        21: "Left", // LEFT
        22: "Right", // RIGHT
        26: "Power", // POWER
        28: "Clear", // CLEAR
        29: "a", // A
        30: "b", // B
        31: "c", // C
        32: "d", // D
        33: "e", // E
        34: "f", // F
        35: "g", // G
        36: "h", // H
        37: "i", // I
        38: "j", // J
        39: "k", // K
        40: "l", // L
        41: "m", // M
        42: "n", // N
        43: "o", // O
        44: "p", // P
        45: "q", // Q
        46: "r", // R
        47: "s", // S
        48: "t", // T
        49: "u", // U
        50: "v", // V
        51: "w", // W
        52: "x", // X
        53: "y", // Y
        54: "z", // Z
        67: "Backspace", // BACKSPACE
        55: ",", // COMMA
        56: ".", // PERIOD
        57: "Left Alt", // ALT_LEFT
        58: "Right Alt", // ALT_RIGHT
        59: "Left Shift", // SHIFT_LEFT
        60: "Right Shift", // SHIFT_RIGHT
        61: "Tab", // TAB
        62: "Space", // SPACE
        63: "Left Meta", // META_LEFT
        64: "Right Meta", // META_RIGHT
        66: "Enter", // ENTER
        68: "`", // GRAVE
        69: "-", // MINUS
        70: "=", // EQUALS
        71: "[", // LEFT_BRACKET
        72: "]", // RIGHT_BRACKET
        73: "\\", // BACKSLASH
        74: ";", // SEMICOLON
        75: "'", // APOSTROPHE
        76: "/", // SLASH
        78: "Num Lock", // NUM_LOCK
        77: "Caps Lock", // CAPS_LOCK
        80: "Scroll Lock", // SCROLL_LOCK
        92: "Page Up", // PAGE_UP
        93: "Page Down", // PAGE_DOWN
        197: "Pause", // PAUSE
        183: "Print Screen", // PRINT_SCREEN
        112: "Forward Delete", // FORWARD_DELETE
        129: "Left Control", // CONTROL_LEFT
        130: "Right Control", // CONTROL_RIGHT
        131: "Escape", // ESCAPE
        132: "End", // END
        133: "Insert", // INSERT
        144: "Numpad0", // NUMPAD_0
        145: "Numpad1", // NUMPAD_1
        146: "Numpad2", // NUMPAD_2
        147: "Numpad3", // NUMPAD_3
        148: "Numpad4", // NUMPAD_4
        149: "Numpad5", // NUMPAD_5
        150: "Numpad6", // NUMPAD_6
        151: "Numpad7", // NUMPAD_7
        152: "Numpad8", // NUMPAD_8
        153: "Numpad9", // NUMPAD_9
        83: ".", // NUMPAD_DECIMAL
        81: "+", // NUMPAD_PLUS
        156: "Enter", // NUMPAD_ENTER
        82: "-", // NUMPAD_MINUS
        181: "/", // NUMPAD_SLASH
        17: "*", // NUMPAD_STAR
        84: "=", // NUMPAD_EQUALS
        243: ":", // COLON
        244: "F1", // F1
        245: "F2", // F2
        246: "F3", // F3
        247: "F4", // F4
        248: "F5", // F5
        249: "F6", // F6
        250: "F7", // F7
        251: "F8", // F8
        252: "F9", // F9
        253: "F10", // F10
        254: "F11", // F11
        255: "F12" // F12
    };
    var shiftNames = {
        0: "Unknown", // UNKNOWN
        3: "Home", // HOME
        7: ")", // NUM_0
        8: "!", // NUM_1
        9: "@", // NUM_2
        10: "#", // NUM_3
        11: "$", // NUM_4
        12: "%", // NUM_5
        13: "^", // NUM_6
        14: "&", // NUM_7
        15: "*", // NUM_8
        16: "(", // NUM_9
        19: "Up", // UP
        20: "Down", // DOWN
        21: "Left", // LEFT
        22: "Right", // RIGHT
        26: "Power", // POWER
        28: "Clear", // CLEAR
        29: "A", // A
        30: "B", // B
        31: "C", // C
        32: "D", // D
        33: "E", // E
        34: "F", // F
        35: "G", // G
        36: "H", // H
        37: "I", // I
        38: "J", // J
        39: "K", // K
        40: "L", // L
        41: "M", // M
        42: "N", // N
        43: "O", // O
        44: "P", // P
        45: "Q", // Q
        46: "R", // R
        47: "S", // S
        48: "T", // T
        49: "U", // U
        50: "V", // V
        51: "W", // W
        52: "X", // X
        53: "Y", // Y
        54: "Z", // Z
        55: "<", // COMMA
        56: ">", // PERIOD
        57: "Left Alt", // ALT_LEFT
        58: "Right Alt", // ALT_RIGHT
        59: "Left Shift", // SHIFT_LEFT
        60: "Right Shift", // SHIFT_RIGHT
        61: "Tab", // TAB
        62: "Space", // SPACE
        63: "Left Meta", // META_LEFT
        64: "Right Meta", // META_RIGHT
        66: "Enter", // ENTER
        67: "Backspace", // BACKSPACE
        68: "~", // GRAVE
        69: "_", // MINUS
        70: "+", // EQUALS
        71: "{", // LEFT_BRACKET
        72: "}", // RIGHT_BRACKET
        73: "|", // BACKSLASH
        74: ":", // SEMICOLON
        75: "\"", // APOSTROPHE
        76: "?", // SLASH
        78: "Num Lock", // NUM_LOCK
        77: "Caps Lock", // CAPS_LOCK
        80: "Scroll Lock", // SCROLL_LOCK
        92: "Page Up", // PAGE_UP
        93: "Page Down", // PAGE_DOWN
        197: "Pause", // PAUSE
        183: "Print Screen", // PRINT_SCREEN
        112: "Forward Delete", // FORWARD_DELETE
        129: "Left Control", // CONTROL_LEFT
        130: "Right Control", // CONTROL_RIGHT
        131: "Escape", // ESCAPE
        132: "End", // END
        133: "Insert", // INSERT
        144: "Numpad0", // NUMPAD_0
        145: "Numpad1", // NUMPAD_1
        146: "Numpad2", // NUMPAD_2
        147: "Numpad3", // NUMPAD_3
        148: "Numpad4", // NUMPAD_4
        149: "Numpad5", // NUMPAD_5
        150: "Numpad6", // NUMPAD_6
        151: "Numpad7", // NUMPAD_7
        152: "Numpad8", // NUMPAD_8
        153: "Numpad9", // NUMPAD_9
        83: ".", // NUMPAD_DECIMAL
        156: "Enter", // NUMPAD_ENTER
        81: "+", // NUMPAD_PLUS
        82: "-", // NUMPAD_MINUS
        181: "/", // NUMPAD_SLASH
        17: "*", // NUMPAD_STAR
        84: "=", // NUMPAD_EQUALS
        243: ":", // COLON
        244: "F1", // F1
        245: "F2", // F2
        246: "F3", // F3
        247: "F4", // F4
        248: "F5", // F5
        249: "F6", // F6
        250: "F7", // F7
        251: "F8", // F8
        252: "F9", // F9
        253: "F10", // F10
        254: "F11", // F11
        255: "F12" // F12
    };

    this.ToTextNative$quorum_integer = function(keycode) {
        var name = plainNames[keycode];
        return (name === undefined) ? "Unknown" : name;
    };
    this.ToTextShiftNative$quorum_integer = function(keycode) {
        var name = shiftNames[keycode];
        return (name === undefined) ? "Unknown" : name;
    };
}
