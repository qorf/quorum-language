function plugins_quorum_Libraries_Game_Graphics_WebGraphics_() 
{
    var gl = null;
    
    // Gets the gl context to be drawn on. Returns true if successful, false if not.
    this.InitializeWebGL = function(canvas)
    {
        gl = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
        
        if (!gl)
        {
            alert("WebGL couldn't be initialized. Your browser may not support it.");
            return false;
        }
        
        this.gl = gl;
        
        // Set clear color to black, fully opaque
        gl.clearColor(0.0, 0.0, 0.0, 1.0);
        // Enable depth testing
        gl.enable(gl.DEPTH_TEST);
        // Near things obscure far things
        gl.depthFunc(gl.LEQUAL);
        // Clear the color as well as the depth buffer.
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
        
        return true;
    };
    
    this.ClearScreenColor$quorum_number$quorum_number$quorum_number$quorum_number = function(red, green, blue, alpha)
    {
        gl.clearColor(red, green, blue, alpha);
    };
    
    this.ClearScreen$quorum_integer = function(mask)
    {
        gl.clear(mask);
    };
    
    this.SetDrawingRegion$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height)
    {
        gl.viewport(x, y, width, height);
    };
    
    this.PixelStorageMode$quorum_integer$quorum_integer = function(type, parameter)
    {
        gl.pixelStorei(type, parameter);
    };
    
    this.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer = function (targetTexture, parameterType, parameterValue)
    {
        gl.texParameterf(targetTexture, parameterType, parameterValue);
    };
    
    // Expected parameters: integer, integer
    this.glBindTexture = function(target, texture)
    {
        gl.bindTexture(target, texture);
    };

    // Expected parameter: integer
    this.glGetString = function(name)
    {
        gl.getString(name);
    };
    
    // Expected parameter: integer
    this.glActiveTexture = function(texture)
    {
        gl.activeTexture(texture);
    };
    
    this.glGenTexture = function()
    {
        return gl.createTexture();
    };
    
    // Expected parameters: integer, IntBuffer
//    this.glGenTextures = function(count, buffer)
//    {
//        gl.createTexture();
//    };
    
    this.glGenBuffer = function()
    {
        return gl.createBuffer();
    };
    
    // Expected parameters: integer, integer
    this.glBindBuffer = function(target, buffer)
    {
        gl.bindBuffer(target, buffer);
    };
    
    // Expected parameters: integer, integer, Buffer, integer
    this.glBufferData = function(target, size, data, usage)
    {
        if (data === null || data === undefined)
        {
            gl.bufferData(target, size, usage);
        }
        else
        {
            gl.bufferData(target, data, usage);
        }
    };
    
    // Expected parameters: integer, integer, integer, Buffer
    this.glBufferSubData = function(target, offset, size, data)
    {
        if (data === null || data === undefined)
        {
            return;
        }

        gl.bufferSubData(target, offset, data);
    };
    
    // Expected parameter: integer
    this.glDeleteBuffer = function(buffer)
    {
        gl.deleteBuffer(buffer);
    };
    
    // Expected parameter: integer
    this.glDeleteTexture = function(texture)
    {
        gl.deleteTexture(texture);
    };
    
    // Expected parameter: boolean
    this.glDepthMask = function(flag)
    {
        gl.depthMask(flag);
    };
    
    // Expected parameter: integer
    this.glDisable = function(feature)
    {
        gl.disable(feature);
    };
    
    // Expected parameter: integer
    this.glEnable = function(feature)
    {
        gl.enable(feature);
    };
    
    // Expected parameter: integer
    this.glCreateShader = function(type)
    {
        return gl.createShader(type);
    };
    
    // Expected parameters: integer, string
    this.glShaderSource = function(shader, string)
    {
        gl.shaderSource(shader, string);
    };
    
    // Expected parameter: integer
    this.glCompileShader = function(shader)
    {
        gl.compileShader(shader);
    };
    
    // Expected parameters: integer, integer
    this.glGetShaderiv = function(shader, pname)
    {
        return gl.getShaderParameter(shader, pname);
    };
    
    // Expected parameter: integer
    this.glGetShaderInfoLog = function(shader)
    {
        return gl.getShaderInfoLog(shader);
    };
    
    // Expected parameter: integer
    this.glGetProgramInfoLog = function(program)
    {
        return gl.getProgramInfoLog(program);
    };
    
    // Expected parameters: integer, integer
    this.glGetProgramiv = function(program, pname)
    {
        return gl.getProgramParameter(program, pname);
    };

    // Expected parameters: integer, string
    this.glGetAttribLocation = function(program, name)
    {
        return gl.getAttribLocation(program, name);
    };
    
    this.glCreateProgram = function()
    {
        return gl.createProgram();
    };
    
    // Expected parameters: integer, integer
    this.glAttachShader = function(program, shader)
    {
        gl.attachShader(program, shader);
    };
    
    // Expected parameter: integer
    this.glLinkProgram = function(program)
    {
        gl.linkProgram(program);
    };
    
    // Expected parameters: integer, string
    this.glGetUniformLocation = function(program, name)
    {
        return gl.getUniformLocation(program, name);
    };
    
    // Expected parameters: integer, integer
    this.glUniform1i = function(location, x)
    {
        gl.uniform1i(location, x);
    };
    
    // Expected parameters: integer, integer, IntBuffer
//    this.glUniform1iv = function (location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, integer[], integer
    this.glUniform1iv = function(location, v)
    {
        gl.uniform1iv(location, v);
    };
    
    // Expected parameters: integer, float
    this.glUniform1f = function(location, x)
    {
        gl.uniform1f(location, x);
    };
    
    // Expected parameters: integer, integer, FloatBuffer
//    this.glUniform1fv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, float[], integer
    this.glUniform1fv = function(location, v)
    {
        gl.uniform1fv(location, v);
    };
    
    // Expected parameters: integer, integer, integer
    this.glUniform2i = function(location, x, y)
    {
        gl.uniform2i(location, x, y);
    };

    // Expected parameters: integer, integer, IntBuffer
//    this.glUniform2iv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, integer[]. integer
    this.glUniform2iv = function(location, v)
    {
        gl.uniform2iv(location, v);
    };
    
    // Expected parameters: integer, float, float
    this.glUniform2f = function(location, x, y)
    {
        gl.uniform2f(location, x, y);
    };
    
    // Expected parameters: integer, integer, FloatBuffer
//    this.glUniform2fv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, float[], integer
    this.glUniform2fv = function(location, v)
    {
        gl.uniform2fv(location, v);
    };
    
    // Expected parameters: integer, integer, integer, integer
    this.glUniform3i = function(location, x, y, z)
    {
        gl.uniform3i(location, x, y, z);
    };

    // Expected parameters: integer, integer, IntBuffer
//    this.glUniform3iv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, integer[], integer offset
    this.glUniform3iv = function(location, v)
    {
        gl.uniform3iv(location, v);
    };
    
    // Expected parameters: integer, float, float, float
    this.glUniform3f = function(location, x, y, z)
    {
        gl.uniform3f(location, x, y, z);
    };
    
    // Expected parameters: integer, integer, FloatBuffer
//    this.glUniform3fv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, float[], integer
    this.glUniform3fv = function(location, v)
    {
        gl.uniform3fv(location, v);
    };
    
    // Expected parameters: integer, integer, integer, integer, integer
    this.glUniform4i = function(location, x, y, z, w)
    {
        gl.uniform4i(location, x, y, z, w);
    };

    // Expected parameters: integer, integer, IntBuffer
//    this.glUniform4iv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, integer[], integer
    this.glUniform4iv = function(location, v)
    {
        gl.uniform4iv(location, v);
    };
    
    // Expected parameters: integer, float, float, float, float
    this.glUniform4f = function(location, x, y, z, w)
    {
        gl.uniform4f(location, x, y, z, w);
    };
    
    // Expected parameters: integer, integer, FloatBuffer
//    this.glUniform4fv = function(location, count, v)
//    {
//        
//    };

    // Expected parameters: integer, integer, float[], integer
    this.glUniform4fv = function(location, v)
    {
        gl.uniform4fv(location, v);
    };
    
    // Expected parameters: integer, integer, boolean, FloatBuffer
//    this.glUniformMatrix2fv = function(location, count, transpose, value)
//    {
//        
//    };

    // Expected parameters: integer, integer, boolean, float[], integer
    this.glUniformMatrix2fv = function(location, transpose, value)
    {
        gl.uniformMatrix2fv(location, transpose, value);
    };

    // Expected parameters: integer, integer, boolean, FloatBuffer
//    this.glUniformMatrix3fv = function(location, count, transpose, value)
//    {
//        
//    };

    // Expected parameters: integer, integer, boolean, float[], integer
    this.glUniformMatrix3fv = function(location, transpose, value)
    {
        gl.uniformMatrix3fv(location, transpose, value);
    };

    // Expected parameters: integer, integer, boolean, FloatBuffer
//    this.glUniformMatrix4fv = function(location, count, transpose, value)
//    {
//        
//    };

    // Expected parameters: integer, integer, boolean, float[], integer
    this.glUniformMatrix4fv = function(location, transpose, value)
    {
        gl.uniformMatrix4fv(location, transpose, value);
    };
    
    // Expected parameters: integer, integer, integer, boolean, integer, Buffer
//    this.glVertexAttribPointer = function(index, size, type, normalized, stride, buffer)
//    {
//        
//    };
    
    // Expected parameters: integer, integer, integer, boolean, integer, integer
    this.glVertexAttribPointer = function(index, size, type, normalized, stride, pointer)
    {
        gl.vertexAttribPointer(index, size, type, normalized, stride, pointer);
    };
    
    // Expected parameter: integer
    this.glUseProgram = function(program)
    {
        gl.useProgram(program);
    };
    
    // Expected parameter: integer
    this.glDeleteShader = function(shader)
    {
        gl.deleteShader(shader);
    };
    
    // Expected parameter: integer
    this.glDeleteProgram = function(program)
    {
        gl.deleteProgram(program);
    };
    
    // Expected parameter: integer
    this.glDisableVertexAttribArray = function(index)
    {
        gl.disableVertexAttribArray(index);
    };
    
    // Expected parameter: integer
    this.glEnableVertexAttribArray = function(index)
    {
        gl.enableVertexAttribArray(index);
    };
    
    // Expected parameters: integer, float, float, float, float
    this.glVertexAttrib4f = function(index, x, y, z, w)
    {
        gl.vertexAttrib4f(index, x, y, z, w);
    };
    
    // Expected parameters: index, FloatBuffer
    this.glVertexAttrib4fv = function(index, values)
    {
        gl.vertexAttrib4fv(index, values);
    };
    
    // Expected parameters: integer, integer, IntBuffer, Buffer - Returns string
    this.glGetActiveUniform = function(program, index)
    {
        return gl.getActiveUniform(program, index);
    };
    
    // Expected parameters: integer, integer, IntBuffer, Buffer - Returns string
    this.glGetActiveAttrib = function(program, index)
    {
        return gl.getActiveAttrib(program, index);
    };
    
    // Expected paramters: integer, integer, integer, integer
    this.glDrawElements = function(mode, count, type, offset)
    {
        gl.drawElements(mode, count, type, offset);
    };
    
    // Expected parameters: integer, integer, integer, Buffer
//    this.glDrawElements = function(mode, count, type, indices)
//    {
//        
//    };
    
    // Expected parameters: integer, integer, integer
    this.glDrawArrays = function(mode, first, count)
    {
        gl.drawArrays(mode, first, count);
    };
    
    // Expected parameters: integer, integer
    this.glBlendFunc = function(sfactor, dfactor)
    {
        gl.blendFunc(sfactor, dfactor);
    };
    
    // Expected parameters: integer, integer, integer, integer,
    //                      integer, integer, integer, integer, Buffer
    this.glTexImage2D = function(target, level, internalformat, width, height, border, format, type, pixels)
    {
        gl.texImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    };
    
    // Expected parameters: integer, IntBuffer
    this.glGetIntegerv = function(pname)
    {
        return gl.getParameter(pname);
    };
    
    // Expected parameters: integer
    this.glGetParameter = function(pname)
    {
        return gl.getParameter(pname);
    };
    
    // Expected parameter: integer
    this.glDepthFunc = function(func)
    {
        gl.depthFunc(func);
    };
    
    // Expected parameters: float, float
    this.glDepthRangef = function(zNear, zFar)
    {
        gl.depthRange(zNear, zFar);
    };
    
    // Expected parameters: integer
    this.glCullFace = function(mode)
    {
        gl.cullFace(mode);
    };     
}

