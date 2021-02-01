function plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_() 
{
    if (!plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_)
    {
        // Static initializer for the class.
        
        plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_ = true;
    }
    
    var color = null;
    
    this.LoadFontNative$quorum_Libraries_System_File = function(file) 
    {
        // NYI
    };
    
    this.LoadFontNative$quorum_text = function(fontName) 
    {
        // NYI
    };
    
    this.IsFontAvailable$quorum_text = function(fontName) 
    {
        // NYI
        return true;
    };
    
    this.SetSizeNative$quorum_integer = function(size) 
    {
        // NYI
    };
    
    this.SetAngleNative$quorum_number = function(angle)
    {
        // NYI
    };
    
    this.SetColorNative$quorum_Libraries_Game_Graphics_Color = function(newColor)
    {
        color = newColor;
    };
    
    this.GetColor = function()
    {
        return color;
    };
    
    this.DisposeNative = function()
    {
        // NYI
    };
    
    this.GetGlyphNative$quorum_text = function(character)
    {
        var glyph = new quorum_Libraries_Game_Graphics_Glyph_();
        glyph.Set_Libraries_Game_Graphics_Glyph__texture_(plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.testDrawable);
        glyph.Set_Libraries_Game_Graphics_Glyph__horizontalAdvance_(8);
        glyph.Set_Libraries_Game_Graphics_Glyph__verticalAdvance_(0);
        glyph.Set_Libraries_Game_Graphics_Glyph__lengthToGlyph_(0);
        glyph.Set_Libraries_Game_Graphics_Glyph__heightFromBaseLine_(0);
        return glyph;
    };
    
    this.GetKerning$quorum_text$quorum_text = function(currentCharacter, nextCharacter)
    {
        // NYI
        return 0;
    };
    
    this.FinishedLoading = function()
    {
        return true;
    };
    
    this.LoadImageSheet$Libraries_Game_Graphics_Fonts_FontImageSheet = function(imageSheet)
    {
        // NYI
    };
    
    this.SystemGetHeight = function()
    {
        // NYI
    };
    
    this.SystemGetMaximumAscent = function()
    {
        // NYI
    };
    
    this.SystemGetMaximumDescent = function()
    {
        // NYI
    };
    
    this.SystemGetUnderlinePosition = function()
    {
        // NYI
    };
    
    this.SystemGetUnderlineThickness = function()
    {
        // NYI
    };
    
    this.GetLineHeight = function()
    {
        // NYI
        return 16;
    };
    
    this.GetAvailableFonts = function()
    {
        // NYI
        return null;
    };
    
    this.GetMaximumAscent = function()
    {
        // NYI
        return 0;
    };
    
    this.GetMaximumDescent = function()
    {
        // NYI
        return 0;
    };
    
    this.GetLineGap = function()
    {
        // NYI
        return 0;
    };
}