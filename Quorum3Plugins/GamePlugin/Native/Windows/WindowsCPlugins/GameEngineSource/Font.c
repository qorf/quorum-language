#include "Font.h"
#include "math.h"
#include "jni.h"

#include <ft2build.h>
#include FT_FREETYPE_H
//#pragma comment(lib, "freetype255b64.lib")
//#include FT_CACHE_H

FT_Library library;
FT_Face *face;
FT_Error error;


/* Code for use with the cache system. 
typedef struct FontRequestStruct
{
    const char* filePath;
    int faceIndex;
    double angle;
} FRequest, *FontRequest;

static FT_Error FontRequester(FTC_FaceID face_id, FT_Library library, FT_Pointer request_data, FT_Face *aface)
{
    FontRequest request = (FontRequest) face_id;
    
    error = FT_New_Face(library, request->filePath, request->faceIndex, aface);
    if (error)
        return error;
    
    
}
*/

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_InitFreeType(JNIEnv * env, jobject clazz)
{
    error = FT_Init_FreeType(&library);
    if (error)
        return 0;
    
    return (jlong)&library;
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_LoadFontC(JNIEnv * env, jobject jobj, jlong libHandle, jstring text)
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

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_SetSizeC(JNIEnv * env, jobject jobj, jlong faceHandle, jint size)
{
    long *ptr = (long*)faceHandle;
    FT_Face *f = (FT_Face*)ptr;
    
    error = FT_Set_Char_Size(*f, size << 6, 0, 72, 0);
    //error = FT_Set_Char_Size(face, size << 6, 0, 72, 0);
    // Error handling here -- need to determine proper way to return the error from here.
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_SetAngleC(JNIEnv * env, jobject jobj, jlong faceHandle, jdouble jAngle)
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

JNIEXPORT jobject JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_LoadBitmap(JNIEnv * env, jobject jobj, jlongArray bitmapData, jchar sym, jlong faceHandle)
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

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_GetLineHeightNative(JNIEnv * env, jobject jobj, jlong faceHandle)
{
    FT_Face *face = (FT_Face*)faceHandle; 
    return (*face)->size->metrics.height;
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_Font_DisposeC(JNIEnv * env, jobject jobj, jlong faceHandle)
{
    FT_Face *face = (FT_Face*)faceHandle;
    FT_Done_Face(*face);
}

/*
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Game_Font_InitCacheManager(JNIEnv * env, jclass clazz)
{
    


   // our custom face requester is dead simple
   static FT_Error
   my_face_requester( FTC_FaceID   face_id,
                      FT_Library   library,
                      FT_Pointer   request_data,
                      FT_Face     *aface )
   {
     MyFace  face = (MyFace) face_id;   // simple typecase

     return FT_New_Face( library, face->file_path, face->face_index, aface );
   }



   {
     FTC_Manager  manager;
     ...
     // initialize cache manager
     error = FTC_Manager_New(
                 library,
                 0,  // use default 
                 0,  // use default 
                 0,  // use default 
                 & my_face_requester,  // use our requester 
                 NULL,                 // don't need this.  
                 &manager );
   }
    
} */
