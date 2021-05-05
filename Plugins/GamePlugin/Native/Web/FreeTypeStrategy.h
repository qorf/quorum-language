extern "C" {

    int InitializeFreeTypeC();


    int LoadFontC(char* font); //$quorum_text = function(fontName)


    int IsFontAvailableC(char* font); //$quorum_text = function(fontName)


    void SetSizeC(int size); //$quorum_integer = function(size)


    void SetAngleC(double angle); //$quorum_number = function(angle)


    void DisposeC(); // = function()


    int GetKerningC(char* current, char* next);


    long GetUnderlinePositionC();


    long GetUnderlineThicknessC();


    int GetLineHeightC(); // = function()


    int GetMaximumAscentC(); // = function()


    int GetMaximumDescentC(); // = function()


    int GetLineGapC(); // = function()


    void getBitmapC(unsigned int output_ptr, int num_bytes);


    int getBitmapDataC(int* bitmapData);


    int loadCharC(char* symbol);
}

