#ifdef __APPLE__
#include <TargetConditionals.h>

#ifdef TARGET_OS_IPHONE
#include "IOSGraphics.h"
//#include "gl2.h"
//#include "gl2ext.h"
#include <OpenGLES/ES2/gl.h>
#include <OpenGLES/ES2/glext.h>
#include <stdio.h>

static jclass bufferClass;
static jclass byteBufferClass;
static jclass charBufferClass;
static jclass shortBufferClass;
static jclass intBufferClass;
static jclass longBufferClass;
static jclass floatBufferClass;
static jclass doubleBufferClass;
static jclass OOMEClass;
static jclass UOEClass;
static jclass IAEClass;

static jmethodID positionID;


static void
nativeClassInitBuffer(JNIEnv *_env)
{
    jclass bufferClassLocal = _env->FindClass("java/nio/Buffer");
    bufferClass = (jclass) _env->NewGlobalRef(bufferClassLocal);

    byteBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/ByteBuffer"));
    charBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/CharBuffer"));
    shortBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/ShortBuffer"));
    intBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/IntBuffer"));
    longBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/LongBuffer"));
    floatBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/FloatBuffer"));
    doubleBufferClass = (jclass) _env->NewGlobalRef(_env->FindClass("java/nio/DoubleBuffer"));

    positionID = _env->GetMethodID(bufferClass, "position","()I");
    if(positionID == 0) _env->ThrowNew(IAEClass, "Couldn't fetch position() method");
}

static void
nativeClassInit(JNIEnv *_env)
{
    nativeClassInitBuffer(_env);

    jclass IAEClassLocal =
        _env->FindClass("java/lang/IllegalArgumentException");
    jclass OOMEClassLocal =
         _env->FindClass("java/lang/OutOfMemoryError");
    jclass UOEClassLocal =
         _env->FindClass("java/lang/UnsupportedOperationException");

    IAEClass = (jclass) _env->NewGlobalRef(IAEClassLocal);
    OOMEClass = (jclass) _env->NewGlobalRef(OOMEClassLocal);
    UOEClass = (jclass) _env->NewGlobalRef(UOEClassLocal);
}

static jint getElementSizeShift(JNIEnv *_env, jobject buffer) {
	if(_env->IsInstanceOf(buffer, byteBufferClass)) return 0;
	if(_env->IsInstanceOf(buffer, floatBufferClass)) return 2;
	if(_env->IsInstanceOf(buffer, shortBufferClass)) return 1;

	if(_env->IsInstanceOf(buffer, charBufferClass)) return 1;
	if(_env->IsInstanceOf(buffer, intBufferClass)) return 2;
	if(_env->IsInstanceOf(buffer, longBufferClass)) return 3;
	if(_env->IsInstanceOf(buffer, doubleBufferClass)) return 3;

	_env->ThrowNew(IAEClass, "buffer type unknown! (Not a ByteBuffer, ShortBuffer, etc.)");
	return 0;
}

inline jint getBufferPosition(JNIEnv *env, jobject buffer)
{
	jint ret = env->CallIntMethodA(buffer, positionID, 0);
	return  ret;
}

static void *
getDirectBufferPointer(JNIEnv *_env, jobject buffer) {
    if (!buffer) {
        return NULL;
    }
    void* buf = _env->GetDirectBufferAddress(buffer);
    if (buf) {
        jint position = getBufferPosition(_env, buffer);
        jint elementSizeShift = getElementSizeShift(_env, buffer);
        buf = ((char*) buf) + (position << elementSizeShift);
    } else {
        _env->ThrowNew(IAEClass, "Must use a native order direct Buffer");
    }
    return buf;
}

static const char* getString( JNIEnv *env, jstring string )
{
	return (const char*)env->GetStringUTFChars(string, NULL);
}

static void releaseString( JNIEnv *env, jstring string, const char* cString )
{
	env->ReleaseStringUTFChars(string, cString);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_init
  (JNIEnv *env, jclass)
{
	nativeClassInit( env );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glActiveTexture
  (JNIEnv *, jobject, jint texture)
{
	glActiveTexture( texture );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glAttachShader
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glAttachShader
  (JNIEnv *, jobject, jint program, jint shader)
{
	glAttachShader( program, shader );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBindAttribLocation
 * Signature: (IILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBindAttribLocation
  (JNIEnv *env, jobject, jint program, jint index, jstring name)
{
	const char* namePtr = getString( env, name );
	glBindAttribLocation( program, index, namePtr );
	releaseString( env, name, namePtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBindBuffer
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBindBuffer
  (JNIEnv *env, jobject, jint target, jint buffer)
{
	glBindBuffer( target, buffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBindFramebuffer
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBindFramebuffer
  (JNIEnv *env, jobject, jint target, jint framebuffer)
{
	glBindFramebuffer( target, framebuffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBindRenderbuffer
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBindRenderbuffer
  (JNIEnv *env, jobject, jint target, jint renderbuffer)
{
	glBindRenderbuffer( target, renderbuffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBindTexture
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBindTexture
  (JNIEnv *env, jobject, jint target, jint texture)
{
	glBindTexture( target, texture );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBlendColor
 * Signature: (FFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBlendColor
  (JNIEnv *env, jobject, jfloat red, jfloat green, jfloat blue, jfloat alpha)
{
	glBlendColor( red, green, blue, alpha );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBlendEquation
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBlendEquation
  (JNIEnv *env, jobject, jint mode)
{
	glBlendEquation( mode );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBlendEquationSeparate
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBlendEquationSeparate
  (JNIEnv *env, jobject, jint modeRGB, jint modeAlpha)
{
	glBlendEquationSeparate( modeRGB, modeAlpha );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBlendFunc
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBlendFunc
  (JNIEnv *env, jobject, jint sfactor, jint dfactor)
{
	glBlendFunc( sfactor, dfactor );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBlendFuncSeparate
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBlendFuncSeparate
  (JNIEnv *env, jobject, jint srcRGB, jint dstRGB, jint srcAlpha, jint dstAlpha)
{
	glBlendFuncSeparate( srcRGB, dstRGB, srcAlpha, dstAlpha);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBufferData
 * Signature: (IILjava/nio/Buffer;I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBufferData
  (JNIEnv * env, jobject, jint target, jint size, jobject data, jint usage)
{
	void* dataPtr = getDirectBufferPointer( env, data );
	glBufferData( target, size, dataPtr, usage );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glBufferSubData
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glBufferSubData
  (JNIEnv *env, jobject, jint target, jint offset, jint size, jobject data)
{
	void* dataPtr = getDirectBufferPointer( env, data );
	glBufferSubData( target, offset, size, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCheckFramebufferStatus
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCheckFramebufferStatus
  (JNIEnv *env, jobject, jint target)
{
	return glCheckFramebufferStatus( target );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glClear
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glClear
  (JNIEnv *env, jobject, jint mask)
{
	glClear( mask );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glClearColor
 * Signature: (FFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glClearColor
  (JNIEnv *env, jobject, jfloat red, jfloat green, jfloat blue, jfloat alpha)
{
	glClearColor( red, green, blue, alpha );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glClearDepthf
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glClearDepthf
  (JNIEnv *env, jobject, jfloat depth)
{
	glClearDepthf( depth );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glClearStencil
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glClearStencil
  (JNIEnv *env, jobject, jint s)
{
	glClearStencil( s );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glColorMask
 * Signature: (ZZZZ)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glColorMask
  (JNIEnv *env, jobject, jboolean red, jboolean green, jboolean blue, jboolean alpha)
{
	glColorMask( red, green, blue, alpha );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCompileShader
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCompileShader
  (JNIEnv *env, jobject, jint shader)
{
	glCompileShader( shader );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCompressedTexImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint internalFormat, jint width, jint height, jint border, jint imageSize, jobject data)
{
	void* dataPtr = getDirectBufferPointer( env, data );
	glCompressedTexImage2D( target, level, internalFormat, width, height, border, imageSize, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCompressedTexSubImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jobject data)
{
	void* dataPtr = getDirectBufferPointer( env, data );
	glCompressedTexSubImage2D( target, level, xoffset, yoffset, width, height, format, imageSize, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCopyTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCopyTexImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint  internalFormat, jint x, jint y, jint width, jint height, jint border)
{
	glCopyTexImage2D( target, level, internalFormat, x, y, width, height, border );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCopyTexSubImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCopyTexSubImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint xoffset, jint yoffset, jint x, jint y, jint width, jint height)
{
	glCopyTexSubImage2D( target, level, xoffset, yoffset, x, y, width, height );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCreateProgram
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCreateProgram
  (JNIEnv *env, jobject)
{
	return glCreateProgram( );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCreateShader
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCreateShader
  (JNIEnv *env, jobject, jint type)
{
	return glCreateShader( type );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glCullFace
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glCullFace
  (JNIEnv *env, jobject, jint mode)
{
	glCullFace( mode );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteBuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteBuffers
  (JNIEnv *env, jobject, jint n, jobject buffers)
{
	void* dataPtr = getDirectBufferPointer( env, buffers );
	glDeleteBuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteBuffer
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteBuffer
  (JNIEnv *, jobject, jint buffer)
{
	GLuint b = buffer;
	glDeleteBuffers (1, &b);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteFramebuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteFramebuffers
  (JNIEnv *env, jobject, jint n, jobject framebuffers)
{
	void* dataPtr = getDirectBufferPointer( env, framebuffers );
	glDeleteFramebuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteFramebuffer
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteFramebuffer
  (JNIEnv *, jobject, jint buffer)
{
	GLuint b = buffer;
	glDeleteFramebuffers(1, &b);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteProgram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteProgram
  (JNIEnv *env, jobject, jint program)
{
	glDeleteProgram( program );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteRenderbuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteRenderbuffers
  (JNIEnv *env, jobject, jint n, jobject renderbuffers)
{
	void* dataPtr = getDirectBufferPointer( env, renderbuffers );
	glDeleteRenderbuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteRenderbuffer
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteRenderbuffer
  (JNIEnv *, jobject, jint buffer)
{
	GLuint b = buffer;
	glDeleteRenderbuffers( 1, &b );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteShader
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteShader
  (JNIEnv *env, jobject, jint shader)
{
	glDeleteShader( shader );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteTextures
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteTextures
  (JNIEnv *env, jobject, jint n, jobject textures)
{
	void* dataPtr = getDirectBufferPointer( env, textures );
	glDeleteTextures( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDeleteTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDeleteTexture
  (JNIEnv *, jobject, jint buffer)
{
	GLuint b = buffer;
	glDeleteTextures(1, &b);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDepthFunc
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDepthFunc
  (JNIEnv *env, jobject, jint func)
{
	glDepthFunc( func );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDepthMask
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDepthMask
  (JNIEnv *env, jobject, jboolean flag)
{
	glDepthMask( flag );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDepthRangef
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDepthRangef
  (JNIEnv *env, jobject, jfloat zNear, jfloat zFar)
{
	glDepthRangef( zNear, zFar );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDetachShader
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDetachShader
  (JNIEnv *env, jobject, jint program, jint shader)
{
	glDetachShader( program, shader );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDisable
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDisable
  (JNIEnv *env, jobject, jint cap)
{
	glDisable( cap );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDisableVertexAttribArray
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDisableVertexAttribArray
  (JNIEnv *env, jobject, jint index)
{
	glDisableVertexAttribArray( index );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDrawArrays
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDrawArrays
  (JNIEnv *env, jobject, jint mode, jint first, jint count)
{
	glDrawArrays( mode, first, count );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glDrawElements
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDrawElements__IIILjava_nio_Buffer_2
(JNIEnv *env, jobject, jint mode, jint count, jint type, jobject indices)
{
	void* dataPtr = getDirectBufferPointer( env, indices );
	//__android_log_print(ANDROID_LOG_INFO, "GL2", "drawelements");
	glDrawElements( mode, count, type, dataPtr );
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glDrawElements__IIII
  (JNIEnv *, jobject, jint mode, jint count, jint type, jint indices)
{
	glDrawElements( mode, count, type, (const void*)indices );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glEnable
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glEnable
  (JNIEnv *env, jobject, jint cap)
{
	glEnable( cap );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glEnableVertexAttribArray
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glEnableVertexAttribArray
  (JNIEnv *env, jobject, jint index)
{
	glEnableVertexAttribArray( index );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glFinish
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glFinish
  (JNIEnv *env, jobject)
{
	glFinish();
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glFlush
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glFlush
  (JNIEnv *env, jobject)
{
	glFlush();
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glFramebufferRenderbuffer
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glFramebufferRenderbuffer
  (JNIEnv *env, jobject, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer)
{
	glFramebufferRenderbuffer( target, attachment, renderbuffertarget, renderbuffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glFramebufferTexture2D
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glFramebufferTexture2D
  (JNIEnv *env, jobject, jint target, jint attachment, jint textarget, jint texture, jint level)
{
	glFramebufferTexture2D( target, attachment, textarget, texture, level );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glFrontFace
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glFrontFace
  (JNIEnv *env, jobject, jint mode)
{ //XXXX
	glFrontFace( mode );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenBuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenBuffers
  (JNIEnv *env, jobject, jint n, jobject buffers)
{
	void* dataPtr = getDirectBufferPointer( env, buffers );
	glGenBuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenBuffer
  (JNIEnv *, jobject)
{
	GLuint result;
	glGenBuffers( 1, &result );
	return result;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenerateMipmap
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenerateMipmap
  (JNIEnv *env, jobject, jint target)
{
	glGenerateMipmap( target );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenFramebuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenFramebuffers
  (JNIEnv *env, jobject, jint n, jobject framebuffers)
{
	void* dataPtr = getDirectBufferPointer( env, framebuffers );
	glGenFramebuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenFramebuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenFramebuffer
  (JNIEnv *, jobject)
{
	GLuint result;
	glGenFramebuffers( 1, &result );
	return result;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenRenderbuffers
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenRenderbuffers
  (JNIEnv *env, jobject, jint n, jobject renderbuffers)
{
	void* dataPtr = getDirectBufferPointer( env, renderbuffers );
	glGenRenderbuffers( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenRenderbuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenRenderbuffer
  (JNIEnv *, jobject)
{
	GLuint result;
	glGenRenderbuffers( 1, &result );
	return result;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenTextures
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenTextures
  (JNIEnv *env, jobject, jint n, jobject textures)
{
	void* dataPtr = getDirectBufferPointer( env, textures );
	glGenTextures( n, (GLuint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGenTexture
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGenTexture
  (JNIEnv *, jobject)
{
	GLuint result;
	glGenTextures(1, &result);
	return result;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetActiveAttrib
 * Signature: (IIILjava/nio/Buffer;Ljava/nio/IntBuffer;Ljava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetActiveAttrib
  (JNIEnv *env, jobject, jint program, jint index, jobject size, jobject type )
{
	// FIXME is this wrong?
	char cname[2048];
	void* sizePtr = getDirectBufferPointer( env, size );
	void* typePtr = getDirectBufferPointer( env, type );
	glGetActiveAttrib( program, index, 2048, NULL, (GLint*)sizePtr, (GLenum*)typePtr, cname );

	return env->NewStringUTF( cname );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetActiveUniform
 * Signature: (IIILjava/nio/Buffer;Ljava/nio/IntBuffer;Ljava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetActiveUniform
  (JNIEnv *env, jobject, jint program, jint index, jobject size, jobject type)
{
	// FIXME is this wrong?
	char cname[2048];
	void* sizePtr = getDirectBufferPointer( env, size );
	void* typePtr = getDirectBufferPointer( env, type );
	glGetActiveUniform( program, index, 2048, NULL, (GLint*)sizePtr, (GLenum*)typePtr, cname );

	return env->NewStringUTF( cname );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetAttachedShaders
 * Signature: (IILjava/nio/Buffer;Ljava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetAttachedShaders
  (JNIEnv *env, jobject, jint program, jint maxcount, jobject count, jobject shaders)
{
	void* countPtr = getDirectBufferPointer( env, count );
	void* shaderPtr = getDirectBufferPointer( env, shaders );
	glGetAttachedShaders( program, maxcount, (GLsizei*)countPtr, (GLuint*)shaderPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetAttribLocation
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetAttribLocation
  (JNIEnv *env, jobject, jint program, jstring name)
{
	const char* cname = getString( env, name );
	int loc = glGetAttribLocation( program, cname );
	releaseString( env, name, cname );
	return loc;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetBooleanv
 * Signature: (ILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetBooleanv
  (JNIEnv *env, jobject, jint program, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetBooleanv( program, (GLboolean*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetBufferParameteriv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetBufferParameteriv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetBufferParameteriv( target, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetError
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetError
  (JNIEnv *env, jobject)
{
	return glGetError();
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetFloatv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetFloatv
  (JNIEnv *env, jobject, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetFloatv( pname, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetFramebufferAttachmentParameteriv
 * Signature: (IIILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetFramebufferAttachmentParameteriv
  (JNIEnv *env, jobject, jint target, jint attachment, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetFramebufferAttachmentParameteriv( target, attachment, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetIntegerv
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetIntegerv
  (JNIEnv *env, jobject, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetIntegerv( pname, (GLint*)dataPtr);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetProgramiv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetProgramiv
  (JNIEnv *env, jobject, jint program, jint pname, jobject params)
{
	void *dataPtr = getDirectBufferPointer( env, params );
	glGetProgramiv( program, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetProgramInfoLog
 * Signature: (IILjava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetProgramInfoLog
  (JNIEnv *env, jobject, jint program )
{
	char info[1024*10]; // FIXME 10k limit should suffice
	int length = 0;
	glGetProgramInfoLog( program, 1024*10, &length, info );
	return env->NewStringUTF( info );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetRenderbufferParameteriv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetRenderbufferParameteriv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetRenderbufferParameteriv( target, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetShaderiv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetShaderiv
  (JNIEnv *env, jobject, jint shader, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetShaderiv( shader, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetShaderInfoLog
 * Signature: (IILjava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetShaderInfoLog
  (JNIEnv *env, jobject, jint shader )
{
	char info[1024*10]; // FIXME 10k limit should suffice
	int length = 0;
	glGetShaderInfoLog( shader, 1024*10, &length, info );
	return env->NewStringUTF( info );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetShaderPrecisionFormat
 * Signature: (IILjava/nio/IntBuffer;Ljava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetShaderPrecisionFormat
  (JNIEnv *env, jobject, jint shadertype, jint precisiontype, jobject range, jobject precision)
{
	void* rangePtr = getDirectBufferPointer( env, range );
	void* precisionPtr = getDirectBufferPointer( env, precision );
	glGetShaderPrecisionFormat( shadertype, precisiontype, (GLint*)rangePtr, (GLint*)precisionPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetShaderSource
 * Signature: (IILjava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetShaderSource
  (JNIEnv *env, jobject, jint shader, jint bufsize, jobject length, jstring source)
{
	env->ThrowNew(UOEClass, "This method is not supported"); // FIXME won't implement this shit.
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetString
  (JNIEnv *env, jobject, jint name)
{
	const char * chars = (const char *)glGetString((GLenum)name);
	jstring output = env->NewStringUTF(chars);
	return output;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetTexParameterfv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetTexParameterfv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetTexParameterfv( target, pname, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetTexParameteriv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetTexParameteriv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetTexParameteriv( target, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetUniformfv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetUniformfv
  (JNIEnv *env, jobject, jint program, jint location, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetUniformfv( program, location, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetUniformiv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetUniformiv
  (JNIEnv *env, jobject, jint program, jint location, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetUniformiv( program, location, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetUniformLocation
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetUniformLocation
  (JNIEnv *env, jobject, jint program, jstring name)
{
	const char* cname = getString( env, name );
	int location = glGetUniformLocation( program, cname );
	releaseString( env, name, cname );
	return location;
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetVertexAttribfv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetVertexAttribfv
  (JNIEnv *env, jobject, jint index, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetVertexAttribfv( index, pname, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetVertexAttribiv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetVertexAttribiv
  (JNIEnv *env, jobject, jint index, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glGetVertexAttribiv( index, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glGetVertexAttribPointerv
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glGetVertexAttribPointerv
  (JNIEnv *env, jobject, jint index, jint pname, jobject pointer)
{
	env->ThrowNew(UOEClass, "This method is not supported"); // FIXME won't implement this shit
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glHint
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glHint
  (JNIEnv *env, jobject, jint target, jint mode)
{
	glHint( target, mode );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsBuffer
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsBuffer
  (JNIEnv *env, jobject, jint buffer)
{
	return glIsBuffer( buffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsEnabled
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsEnabled
  (JNIEnv *env, jobject, jint cap)
{
	return glIsEnabled( cap );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsFramebuffer
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsFramebuffer
  (JNIEnv *env, jobject, jint framebuffer)
{
	return glIsFramebuffer( framebuffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsProgram
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsProgram
  (JNIEnv *env, jobject, jint program)
{
	return glIsProgram( program );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsRenderbuffer
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsRenderbuffer
  (JNIEnv *env, jobject, jint renderbuffer)
{
	return glIsRenderbuffer( renderbuffer );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsShader
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsShader
  (JNIEnv *env, jobject, jint shader)
{
	return glIsShader( shader );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glIsTexture
 * Signature: (I)C
 */
JNIEXPORT jboolean JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glIsTexture
  (JNIEnv *env, jobject, jint texture)
{
	return glIsTexture( texture );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glLineWidth
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glLineWidth
  (JNIEnv *env, jobject, jfloat width)
{
	glLineWidth( width );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glLinkProgram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glLinkProgram
  (JNIEnv *env, jobject, jint program)
{
	glLinkProgram( program );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glPixelStorei
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glPixelStorei
  (JNIEnv *env, jobject, jint pname, jint param)
{
	glPixelStorei( pname, param );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glPolygonOffset
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glPolygonOffset
  (JNIEnv *env, jobject, jfloat factor, jfloat units)
{
	glPolygonOffset( factor, units );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glReadPixels
 * Signature: (IIIIIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glReadPixels
  (JNIEnv *env, jobject, jint x, jint y, jint width, jint height, jint format, jint type, jobject pixels)
{
	void* dataPtr = getDirectBufferPointer( env, pixels );
	glReadPixels( x, y, width, height, format, type, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glReleaseShaderCompiler
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glReleaseShaderCompiler
  (JNIEnv *env, jobject)
{
	glReleaseShaderCompiler();
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glRenderbufferStorage
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glRenderbufferStorage
  (JNIEnv *env, jobject, jint target, jint internalFormat, jint width, jint height)
{
	glRenderbufferStorage( target, internalFormat, width, height );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glSampleCoverage
 * Signature: (FZ)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glSampleCoverage
  (JNIEnv *env, jobject, jfloat value, jboolean inver)
{
	glSampleCoverage( value, inver );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glScissor
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glScissor
  (JNIEnv *env, jobject, jint x, jint y, jint width, jint height)
{
	glScissor( x, y, width, height );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glShaderBinary
 * Signature: (ILjava/nio/IntBuffer;ILjava/nio/Buffer;I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glShaderBinary
  (JNIEnv *env, jobject, jint n, jobject shaders, jint binaryformat, jobject binary, jint length)
{
	void* shaderPtr = getDirectBufferPointer( env, shaders );
	void* binaryPtr = getDirectBufferPointer( env, binary );
	glShaderBinary( n, (GLuint*)shaderPtr, binaryformat, binaryPtr, length );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glShaderSource
 * Signature: (IILjava/lang/String;Ljava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glShaderSource
  (JNIEnv *env, jobject, jint shader, jstring string )
{
	const char* cstring = getString( env, string );
	glShaderSource( shader, 1, &cstring, NULL );
	releaseString( env, string, cstring );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilFunc
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilFunc
  (JNIEnv *env, jobject, jint func, jint ref, jint mask)
{
	glStencilFunc( func, ref, mask );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilFuncSeparate
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilFuncSeparate
  (JNIEnv *env, jobject, jint face, jint func, jint ref, jint mask)
{
	glStencilFuncSeparate( face, func, ref, mask );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilMask
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilMask
  (JNIEnv *env, jobject, jint mask)
{
	glStencilMask( mask );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilMaskSeparate
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilMaskSeparate
  (JNIEnv *env, jobject, jint face, jint mask)
{
	glStencilMaskSeparate( face, mask );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilOp
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilOp
  (JNIEnv *env, jobject, jint fail, jint zFail, jint zpass)
{
	glStencilOp( fail, zFail, zpass );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glStencilOpSeparate
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glStencilOpSeparate
  (JNIEnv *env, jobject, jint face, jint fail, jint zFail, jint zPass)
{
	glStencilOpSeparate( face, fail, zFail, zPass );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexImage2D
 * Signature: (IIIIIIIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint format, jint type, jobject pixels)
{
	void* dataPtr = getDirectBufferPointer( env, pixels );
	glTexImage2D( target, level, internalformat, width, height, border, format, type, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexParameterf
 * Signature: (IIF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexParameterf
  (JNIEnv *env, jobject, jint target, jint pname, jfloat param)
{
	glTexParameterf( target, pname, param );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexParameterfv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexParameterfv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glTexParameterfv( target, pname, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexParameteri
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexParameteri
  (JNIEnv *env, jobject, jint target, jint pname, jint param)
{
	glTexParameteri( target, pname, param );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexParameteriv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexParameteriv
  (JNIEnv *env, jobject, jint target, jint pname, jobject params)
{
	void* dataPtr = getDirectBufferPointer( env, params );
	glTexParameteriv( target, pname, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glTexSubImage2D
 * Signature: (IIIIIIIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glTexSubImage2D
  (JNIEnv *env, jobject, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint type, jobject pixels)
{
	void* dataPtr = getDirectBufferPointer( env, pixels );
	glTexSubImage2D( target, level, xoffset, yoffset, width, height, format, type, dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1f
  (JNIEnv *env, jobject, jint location, jfloat x)
{
	glUniform1f( location, x );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1fv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1fv__IILjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform1fv( location, count, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1fv
 * Signature: (II[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1fv__II_3FI
  (JNIEnv *env, jobject, jint location, jint count, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform1fv( location, count, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1i
  (JNIEnv *env, jobject, jint location, jint x)
{
	glUniform1i( location, x );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1iv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1iv__IILjava_nio_IntBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform1iv( location, count, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform1iv
 * Signature: (II[II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform1iv__II_3II
	(JNIEnv *env, jobject, jint location, jint count, jintArray value, jint offset)
{
	int* v = (int*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform1iv( location, count, (GLint*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2f
  (JNIEnv *env, jobject, jint location, jfloat x, jfloat y)
{
	glUniform2f( location, x, y );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2fv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2fv__IILjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform2fv( location, count, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2fv
 * Signature: (II[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2fv__II_3FI
	(JNIEnv *env, jobject, jint location, jint count, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform2fv( location, count, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2i
  (JNIEnv *env, jobject, jint location, jint x, jint y)
{
	glUniform2i( location, x, y );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2iv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2iv__IILjava_nio_IntBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform2iv( location, count, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform2iv
 * Signature: (II[II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform2iv__II_3II
	(JNIEnv *env, jobject, jint location, jint count, jintArray value, jint offset)
{
	int* v = (int*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform2iv( location, count, (GLint*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3f
  (JNIEnv *env, jobject, jint location, jfloat x, jfloat y, jfloat z)
{
	glUniform3f( location, x, y, z );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3fv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3fv__IILjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform3fv( location, count, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3fv
 * Signature: (II[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3fv__II_3FI
	(JNIEnv *env, jobject, jint location, jint count, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform3fv( location, count, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3i
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3i
  (JNIEnv *env, jobject, jint location, jint x, jint y, jint z)
{
	glUniform3i( location, x, y, z );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3iv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3iv__IILjava_nio_IntBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform3iv( location, count, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform3iv
 * Signature: (II[II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform3iv__II_3II
	(JNIEnv *env, jobject, jint location, jint count, jintArray value, jint offset)
{
	int* v = (int*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform3iv( location, count, (GLint*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4f
  (JNIEnv *env, jobject, jint location, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glUniform4f( location, x, y, z, w );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4fv
 * Signature: (IILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4fv__IILjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform4fv( location, count, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4fv
 * Signature: (II[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4fv__II_3FI
	(JNIEnv *env, jobject, jint location, jint count, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform4fv( location, count, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4i
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4i
  (JNIEnv *env, jobject, jint location, jint x, jint y, jint z, jint w)
{
	glUniform4i( location, x, y, z, w );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4iv
 * Signature: (IILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4iv__IILjava_nio_IntBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jobject v)
{
	void* dataPtr = getDirectBufferPointer( env, v );
	glUniform4iv( location, count, (GLint*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniform4iv
 * Signature: (II[II)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniform4iv__II_3II
	(JNIEnv *env, jobject, jint location, jint count, jintArray value, jint offset)
{
	int* v = (int*)env->GetPrimitiveArrayCritical(value, 0);
	glUniform4iv( location, count, (GLint*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix2fv
 * Signature: (IIZLjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix2fv__IIZLjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jobject value)
{
	void* dataPtr = getDirectBufferPointer( env, value );
	glUniformMatrix2fv( location, count, transpose, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix2fv
 * Signature: (IIZ[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix2fv__IIZ_3FI
  (JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniformMatrix2fv( location, count, transpose, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix3fv
 * Signature: (IIZLjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix3fv__IIZLjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jobject value)
{
	void* dataPtr = getDirectBufferPointer( env, value );
	glUniformMatrix3fv( location, count, transpose, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix3fv
 * Signature: (IIZ[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix3fv__IIZ_3FI
	(JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniformMatrix3fv( location, count, transpose, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix4fv
 * Signature: (IIZLjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix4fv__IIZLjava_nio_FloatBuffer_2
  (JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jobject value)
{
	void* dataPtr = getDirectBufferPointer( env, value );
	glUniformMatrix4fv( location, count, transpose, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUniformMatrix4fv
 * Signature: (IIZ[FI)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUniformMatrix4fv__IIZ_3FI
	(JNIEnv *env, jobject, jint location, jint count, jboolean transpose, jfloatArray value, jint offset)
{
	float* v = (float*)env->GetPrimitiveArrayCritical(value, 0);
	glUniformMatrix4fv( location, count, transpose, (GLfloat*)&v[offset] );
	env->ReleasePrimitiveArrayCritical(value, v, 0);
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glUseProgram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glUseProgram
  (JNIEnv *env, jobject, jint program)
{
	glUseProgram( program );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glValidateProgram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glValidateProgram
  (JNIEnv *env, jobject, jint program)
{
	glValidateProgram( program );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib1f
  (JNIEnv *env, jobject, jint indx, jfloat x)
{
	glVertexAttrib1f( indx, x );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib1fv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib1fv
  (JNIEnv *env, jobject, jint indx, jobject values)
{
	void* dataPtr = getDirectBufferPointer( env, values );
	glVertexAttrib1fv( indx, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib2f
  (JNIEnv *env, jobject, jint indx, jfloat x, jfloat y)
{
	glVertexAttrib2f( indx, x, y );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib2fv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib2fv
  (JNIEnv *env, jobject, jint indx, jobject values)
{
	void* dataPtr = getDirectBufferPointer( env, values );
	glVertexAttrib2fv( indx, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib3f
  (JNIEnv *env, jobject, jint indx, jfloat x, jfloat y, jfloat z)
{
	glVertexAttrib3f( indx, x, y, z );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib3fv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib3fv
  (JNIEnv *env, jobject, jint indx, jobject values)
{
	void* dataPtr = getDirectBufferPointer( env, values );
	glVertexAttrib3fv( indx, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib4f
  (JNIEnv *env, jobject, jint indx, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glVertexAttrib4f( indx, x, y, z, w );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttrib4fv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttrib4fv
  (JNIEnv *env, jobject, jint indx, jobject values)
{
	void* dataPtr = getDirectBufferPointer( env, values );
	glVertexAttrib4fv( indx, (GLfloat*)dataPtr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glVertexAttribPointer
 * Signature: (IIIZILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttribPointer__IIIZILjava_nio_Buffer_2
  (JNIEnv *env, jobject, jint indx, jint size, jint type, jboolean normalized, jint stride, jobject ptr)
{
	void* dataPtr = getDirectBufferPointer( env, ptr );
	glVertexAttribPointer( indx, size, type, normalized, stride, dataPtr );
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glVertexAttribPointer__IIIZII
  (JNIEnv *, jobject, jint indx, jint size, jint type, jboolean normalized, jint stride, jint ptr)
{
	glVertexAttribPointer( indx, size, type, normalized, stride, (const void*)ptr );
}

/*
 * Class:     plugins_quorum_Libraries_Game_Graphics_IOSGraphics
 * Method:    glViewportJni
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Game_Graphics_IOSGraphics_glViewportJni
  (JNIEnv *env, jobject, jint x, jint y, jint width, jint height)
{
	glViewport( x, y, width, height );
}
#endif
#endif