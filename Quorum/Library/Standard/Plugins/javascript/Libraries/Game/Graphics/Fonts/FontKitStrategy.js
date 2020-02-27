function plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_() 
{
    if (!plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_)
    {
        plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_.testDrawable = new quorum_Libraries_Game_Graphics_Drawable_();
        plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_.testDrawable.LoadFilledRectangle$quorum_integer$quorum_integer(6, 12);
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
    
    this.SetColor$quorum_Libraries_Game_Graphics_Color = function(newColor)
    {
        color = newColor;
    };
    
    this.GetColor = function()
    {
        return color;
    };
    
    this.Dispose = function()
    {
        // NYI
    };
    
    this.GetGlyph$quorum_text = function(character)
    {
        var glyph = new quorum_Libraries_Game_Graphics_Glyph_();
        glyph.Set_Libraries_Game_Graphics_Glyph__texture_(plugins_quorum_Libraries_Game_Graphics_Fonts_FontKitStrategy_.testDrawable);
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