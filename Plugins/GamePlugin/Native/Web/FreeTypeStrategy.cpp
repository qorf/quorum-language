#include <iostream>
#include <cstdint>
#include "math.h"
#include "FreeTypeStrategy.h"

// EMSCRIPTEN WASM
#include <emscripten.h>

// FREETYPE
#include <ft2build.h>
#include FT_FREETYPE_H

FT_Library library;
FT_Face face;
FT_Error error;
FT_GlyphSlot glyph;

int InitializeFreeTypeC() {
    // Initialize FreeType
    error = FT_Init_FreeType(&library);
    if (error) {
        std::cerr << "Error initializing FreeType" << std::endl;
    }

    return error;
}

int LoadFontC(char* font)
{
    // Make sure requested font is arial
    char arialFont[] = "Arial";
    if (strcmp(font, arialFont) != 0) {
        std::cerr << "Only the arial font face is supported at this time." << std::endl;
        return 1;
    }

    // Load font
    error = FT_New_Face(library, "fonts/Arial.ttf", 0, &face);
    if (error == FT_Err_Unknown_File_Format) {
        std::cerr << "Font format is unsupported" << std::endl;
        return 1;
    }
    else if (error) {
        std::cerr << "Font file is missing or corrupted" << std::endl;
        return 1;
    }
    return 0;
}

int IsFontAvailableC(char* font)
{
    char arialFont[] = "Arial"; // Only using Arial right now...
    return strcmp(font, arialFont); // Return 1 (True) if font == 'Arial'
}

void SetSizeC(int size)
{
    error = FT_Set_Char_Size(face, size << 6, 0, 72, 0);
}

void SetAngleC(double angle)
{
    FT_Matrix matrix;

    matrix.xx = (FT_Fixed)(cos(angle) * 0x10000L);
    matrix.xy = (FT_Fixed)(-sin(angle) * 0x10000L);
    matrix.yx = (FT_Fixed)(sin(angle) * 0x10000L);
    matrix.yy = (FT_Fixed)(cos(angle) * 0x10000L);

    FT_Set_Transform(face, &matrix, 0);
}


void DisposeC()
{
    FT_Done_Face(face);
}

int GetKerningC(char* current, char* next)
{
    if (FT_HAS_KERNING(face)) {
        FT_Vector delta;
        FT_UInt current_index = FT_Get_Char_Index(face, current[0]);
        FT_UInt next_index = FT_Get_Char_Index(face, next[0]);
        FT_Get_Kerning(face, current_index, next_index,
            FT_KERNING_DEFAULT, &delta);
        return (int)(delta.x >> 6);
    } else
        return 0;
}

long GetUnderlinePositionC()
{
    return FT_MulFix(face->underline_position, face->size->metrics.y_scale);
}

long GetUnderlineThicknessC()
{
    return FT_MulFix(face->underline_thickness, face->size->metrics.y_scale);
}

int GetLineHeightC()
{
    return face->size->metrics.height;
}

int GetMaximumAscentC()
{
    return face->size->metrics.ascender;
}

int GetMaximumDescentC()
{
    return face->size->metrics.descender;
}

int loadCharC(char* symbol)
{
    char sym = *symbol;
    glyph = face->glyph;

    // If an error occurs during FT_Load_Char, return 1.
    error = FT_Load_Char(face, sym, FT_LOAD_RENDER);
    if (error) {
        std::cerr << "Error: FT_Load_Char " << error << std::endl;
        return 1;
    }
    else {
        return 0;
    }
}

void getBitmapC(unsigned int output_ptr, int num_bytes) {
    // Get FreeType bitmap buffer
    unsigned char* buf = glyph->bitmap.buffer;
    // Set output pointer
    uint8_t* dst = (uint8_t*)output_ptr;
    // Write data to output
    for (int i = 0; i < num_bytes; i++) {
        dst[i] = (uint8_t)buf[i];
    }
}

int getBitmapDataC(int* bitmapData)
{
    bitmapData[0] = glyph->bitmap_left;
    bitmapData[1] = glyph->bitmap_top;
    bitmapData[2] = glyph->bitmap.rows;
    bitmapData[3] = glyph->bitmap.width;
    bitmapData[4] = glyph->advance.x;
    bitmapData[5] = glyph->advance.y;
    bitmapData[6] = glyph->bitmap.pitch;

    return 0;
}

int main() {
    return InitializeFreeTypeC();
}