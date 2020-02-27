#include "FreeTypeStrategy.h"
#include "math.h"
#include "jni.h"

#include <ft2build.h>
#include FT_FREETYPE_H
//#pragma comment(lib, "freetype255b64.lib")
//#include FT_CACHE_H

FT_Library library;
FT_Face *face;
FT_Error error;


JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_InitFreeType(JNIEnv * env, jobject clazz)
{
    error = FT_Init_FreeType(&library);
    if (error)
        return 0;
    
    return (jlong)&library;
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_LoadFontC(JNIEnv * env, jobject jobj, jlong libHandle, jstring text)
{
    const char * path;
    
    face = (FT_Face *)malloc(sizeof(FT_Face));

    path = (*env)->GetStringUTFChars( env, text, NULL );
    
    FT_Library *lib = (FT_Library*)libHandle;
    
    error = FT_New_Face(*lib, path, 0, face);
    if (error)
        return 0;
    
    return (jlong)face;
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_SetSizeC(JNIEnv * env, jobject jobj, jlong faceHandle, jint size)
{
    long *ptr = (long*)faceHandle;
    FT_Face *f = (FT_Face*)ptr;
    
    error = FT_Set_Char_Size(*f, size << 6, 0, 72, 0);
    //error = FT_Set_Char_Size(face, size << 6, 0, 72, 0);
    // Error handling here -- need to determine proper way to return the error from here.
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_SetAngleC(JNIEnv * env, jobject jobj, jlong faceHandle, jdouble jAngle)
{
    FT_Matrix matrix;
    
    matrix.xx = (FT_Fixed)( cos( jAngle ) * 0x10000L );
    matrix.xy = (FT_Fixed)(-sin( jAngle ) * 0x10000L );
    matrix.yx = (FT_Fixed)( sin( jAngle ) * 0x10000L );
    matrix.yy = (FT_Fixed)( cos( jAngle ) * 0x10000L );
    
    FT_Face *f = (FT_Face*)faceHandle;
    
    FT_Set_Transform(*f, &matrix, 0);
    //FT_Set_Transform(face, &matrix, 0);
}

JNIEXPORT jobject JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_LoadBitmap(JNIEnv * env, jobject jobj, jlongArray bitmapData, jchar sym, jlong faceHandle)
{
    char symbol = sym;
    
    FT_Face *face = (FT_Face*)faceHandle;
    
    FT_GlyphSlot glyph = (*face)->glyph;
    
    // If an error occurs during FT_Load_Char, return null.
    if (FT_Load_Char(*face, symbol, FT_LOAD_RENDER))
        return NULL;
    
    jlong* data = (jlong*)(*env)->GetPrimitiveArrayCritical(env, bitmapData, 0);
    data[0] = glyph->bitmap_left;
    data[1] = glyph->bitmap_top;
    data[2] = glyph->bitmap.rows;
    data[3] = glyph->bitmap.width;
    data[4] = glyph->advance.x;
    data[5] = glyph->advance.y;
        
    (*env)->ReleasePrimitiveArrayCritical(env, bitmapData, data, 0);
    
    // If no error occurs, the bitmap must be retrieved and returned as a ByteBuffer.
    int size = glyph->bitmap.pitch;
    if (size < 0)
        size = -size;
    size *= glyph->bitmap.rows;
    
    // (*env)-> ?
    return (*env)->NewDirectByteBuffer(env, glyph->bitmap.buffer, size);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_GetLineHeightNative(JNIEnv * env, jobject jobj, jlong faceHandle)
{
    FT_Face *face = (FT_Face*)faceHandle; 
    return (*face)->size->metrics.height;
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_GetMaximumAscentNative(JNIEnv* env, jobject jobj, jlong faceHandle)
{
    FT_Face* face = (FT_Face*)faceHandle;
    return (*face)->size->metrics.ascender;
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_GetMaximumDescentNative(JNIEnv* env, jobject jobj, jlong faceHandle)
{
    FT_Face* face = (FT_Face*)faceHandle;
    return (*face)->size->metrics.descender;
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_GetUnderlinePositionNative(JNIEnv* env, jobject jobj, jlong faceHandle)
{
    FT_Face* face = (FT_Face*)faceHandle;
    /*
    FT_MulFix is used in combination with the underline position (expressed in font units) and the y scale
    to convert to fractional pixel coordinates (the same format used for the other getter functions here).
    */
    return FT_MulFix((*face)->underline_position, (*face)->size->metrics.y_scale);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_GetUnderlineThicknessNative(JNIEnv* env, jobject jobj, jlong faceHandle)
{
    FT_Face* face = (FT_Face*)faceHandle;
    /*
    FT_MulFix is used in combination with the underline position (expressed in font units) and the y scale
    to convert to fractional pixel coordinates (the same format used for the other getter functions here).
    */
    return FT_MulFix((*face)->underline_thickness, (*face)->size->metrics.y_scale);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_DisposeC(JNIEnv * env, jobject jobj, jlong faceHandle)
{
    FT_Face *face = (FT_Face*)faceHandle;
    FT_Done_Face(*face);
}
