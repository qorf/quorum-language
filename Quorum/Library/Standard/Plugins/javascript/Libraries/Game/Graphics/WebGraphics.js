function plugins_quorum_Libraries_Game_Graphics_WebGraphics_() 
{
    var gl = null;
    
    // Gets the gl context to be drawn on. Returns true if successful, false if not.
    this.InitializeWebGL = function(canvas)
    {
        // Premultiplied alpha must be disabled in the canvas, or else items with transparency can calculate their colors wrong.
        // This can make pixels appear lighter than they should be, which is especially prominent in fonts where edges will blur out and appear thin.
        gl = canvas.getContext("webgl2", {premultipliedAlpha: false});
        
        if (!gl)
        {
            alert("WebGL 2 couldn't be initialized. Your browser may not support it.");
            return false;
        }
        
        this.gl = gl;
        
        // Set clear color to fully opaque dark gray.
        gl.clearColor(0.85, 0.85, 0.85, 1.0);
        // Enable depth testing
        gl.enable(gl.DEPTH_TEST);
        // Near things obscure far things
        gl.depthFunc(gl.LEQUAL);
        // Clear the color as well as the depth buffer.
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
        
        return true;
    };

    this.Dispose = function()
    {
        // Attempt to forcibly de-allocate the WebGL context, if the required extension is available.
        const loseContextExtension = gl.getExtension("WEBGL_lose_context");
        if (loseContextExtension)
            loseContextExtension.loseContext();

        gl = null;
    }
    
    this.HasContext = function()
    {
        return gl !== null && gl !== undefined;
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
    
    this.CreateShaderID$quorum_integer = function(shaderType)
    {
        return this.glCreateShader(shaderType);
    };

    this.SetShaderCode$quorum_integer$quorum_text = function(shaderID, source)
    {
        this.glShaderSource(shaderID, source);
    };

    this.CompileShader$quorum_integer = function(shaderID)
    {
        this.glCompileShader(shaderID);
    };

    this.IsShaderCompiled$quorum_integer = function(shaderID)
    {
        return this.glGetShaderiv(shaderID, gl.COMPILE_STATUS) != 0;
    };

    this.GetShaderLog$quorum_integer = function(shaderID)
    {
        return this.glGetShaderInfoLog(shaderID);
    };
    
   this.CreateShaderProgramID = function()
    {
        return this.glCreateProgram();
    };

    this.AttachShader$quorum_integer$quorum_integer = function(programID, shaderID)
    {
        this.glAttachShader(programID, shaderID);
    };

    this.LinkShaderProgram$quorum_integer = function(programID)
    {
        this.glLinkProgram(programID);
    };
    
    this.IsShaderProgramLinked$quorum_integer = function(programID)
    {
        return this.glGetProgramiv(programID, gl.LINK_STATUS) != 0;
    };

    this.GetShaderProgramLog$quorum_integer = function(programID)
    {
        return this.glGetProgramInfoLog(programID);
    };
    
    this.UseShaderProgram$quorum_integer = function(programID)
    {
        return this.glUseProgram(programID);
    };
    
    this.DeleteShaderProgram$quorum_integer = function(programID)
    {
        this.glDeleteProgram(programID);
    };

    this.DeleteShader$quorum_integer = function(programID)
    {
        this.glDeleteShader(programID);
    };
    
    this.EnableProperty$quorum_integer = function(property)
    {
        this.glEnable(property);
    };

    this.DisableProperty$quorum_integer = function(property)
    {
        this.glDisable(property);
    };

    this.SetDepthMask$quorum_boolean = function(mask)
    {
        this.glDepthMask(mask);
    };

    this.SetDepthFunction$quorum_integer = function(func)
    {
        this.glDepthFunc(func);
    };
    
    this.SetBlendFunction$quorum_integer$quorum_integer = function(source, destination)
    {
        this.glBlendFunc(source, destination);
    };
    
    this.SetCullFace$quorum_integer = function(face)
    {
        this.glCullFace(face);
    };

    this.SetDepthRange$quorum_number$quorum_number = function(near, far)
    {
        this.glDepthRangef(near, far);
    };
    
    this.GetMaxTextureUnits = function()
    {
        this.glGetIntegerv(gl.MAX_TEXTURE_IMAGE_UNITS);
    };
    
    this.SetActiveTextureID$quorum_integer = function(id)
    {
        this.glActiveTexture(id);
    };
    
    this.CreateBuffer = function()
    {
        return this.glGenBuffer();
    };

    this.BindBuffer$quorum_integer$quorum_integer = function(purpose, bufferID)
    {
        this.glBindBuffer(purpose, bufferID);
    };
    
    this.DeleteBuffer$quorum_integer = function(bufferID)
    {
        this.glDeleteBuffer(bufferID);
    };
    
    this.SetBuffer$quorum_integer$quorum_Libraries_Game_Graphics_Number32BitBuffer$quorum_integer = function(purpose, buffer, option)
    {
        this.glBufferData(purpose, buffer.plugin_.GetArray(), option);
    };

    this.SetBuffer$quorum_integer$quorum_Libraries_Game_Graphics_Integer32BitBuffer$quorum_integer = function(purpose, buffer, option)
    {
        this.glBufferData(purpose, buffer.plugin_.GetArray(), option);
    };
    
    this.EnableVertexInput$quorum_integer = function(id)
    {
        this.glEnableVertexAttribArray(id);
    };
    
    this.DisableVertexInput$quorum_integer = function(id)
    {
        this.glDisableVertexAttribArray(id);
    };
    
    this.DrawBuffer$quorum_integer$quorum_integer$quorum_integer = function(primitiveType, offset, count)
    {
        this.glDrawArrays(primitiveType, offset, count);
    };

    this.DrawIndexedBuffer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(primitiveType, count, indexType, indicesOffset)
    {
        this.glDrawElements(primitiveType, count, indexType, indicesOffset);
    };
    
    this.GetShaderInputCount$quorum_integer = function(programID)
    {
        return this.glGetProgramiv(programID, gl.ACTIVE_UNIFORMS);
    };

    this.GetShaderInputInformation$quorum_integer$quorum_integer$quorum_Libraries_Language_Types_Integer$quorum_Libraries_Language_Types_Integer$quorum_Libraries_Language_Types_Integer = function(programID, index, location, size, type)
    {
        var info = this.glGetActiveUniform(programID, index);
        location.SetValue$quorum_integer(this.glGetUniformLocation(programID, info.name));
        type.SetValue$quorum_integer(info.type);
        size.SetValue$quorum_integer(info.size);
        return info.name;
    };
    
    this.GetVertexInputCount$quorum_integer = function(programID)
    {
        return this.glGetProgramiv(programID, gl.ACTIVE_ATTRIBUTES);
    };

    this.GetVertexInputInformation$quorum_integer$quorum_integer$quorum_Libraries_Language_Types_Integer$quorum_Libraries_Language_Types_Integer$quorum_Libraries_Language_Types_Integer = function(programID, index, location, size, type)
    {
        var info = this.glGetActiveAttrib(programID, index);
        location.SetValue$quorum_integer(this.glGetAttribLocation(programID, info.name));
        type.SetValue$quorum_integer(info.type);
        size.SetValue$quorum_integer(info.size);
        return info.name;
    };
    
    this.SetShaderInput$quorum_integer$quorum_integer = function(uniformID, value)
    {
        this.glUniform1i(uniformID, value);
    };
    
    this.SetShaderInput$quorum_integer$quorum_integer$quorum_integer = function(uniformID, value1, value2)
    {
        this.glUniform2i(uniformID, value1, value2);
    };

    this.SetShaderInput$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(uniformID, value1, value2, value3)
    {
        this.glUniform3i(uniformID, value1, value2, value3);
    };

    this.SetShaderInput$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(uniformID, value1, value2, value3, value4)
    {
        this.glUniform4i(uniformID, value1, value2, value3, value4);
    };

    this.SetShaderInput$quorum_integer$quorum_number = function(uniformID, value)
    {
        this.glUniform1f(uniformID, value);
    };

    this.SetShaderInput$quorum_integer$quorum_number$quorum_number = function(uniformID, value1, value2)
    {
        this.glUniform2f(uniformID, value1, value2);
    };

    this.SetShaderInput$quorum_integer$quorum_number$quorum_number$quorum_number = function(uniformID, value1, value2, value3)
    {
        this.glUniform3f(uniformID, value1, value2, value3);
    };

    this.SetShaderInput$quorum_integer$quorum_number$quorum_number$quorum_number$quorum_number = function(uniformID, value1, value2, value3, value4)
    {
        this.glUniform4f(uniformID, value1, value2, value3, value4);
    };
    
    this.SetShaderInputArray$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer = function(uniformID, array, startIndex, length)
    {
        this.glUniform1fv(uniformID, array.plugin_.array_, length, startIndex);
    };

    this.SetShaderInputVector2Array$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer = function(uniformID, array, startIndex, length)
    {
        this.glUniform2fv(uniformID, array.plugin_.array_, length, startIndex);
    };

    this.SetShaderInputVector3Array$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer = function(uniformID, array, startIndex, length)
    {
        this.glUniform3fv(uniformID, array.plugin_.array_, length, startIndex);
    };

    this.SetShaderInputVector4Array$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_integer = function(uniformID, array, startIndex, length)
    {
        this.glUniform4fv(uniformID, array.plugin_.array_, length, startIndex);
    };

    this.SetShaderInputMatrix4Array$quorum_integer$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_boolean = function(uniformID, matrixCount, array, startIndex, transpose)
    {
        this.glUniformMatrix4fv(uniformID, transpose, array.plugin_.array_);
    };

    this.SetShaderInputMatrix3Array$quorum_integer$quorum_integer$quorum_Libraries_Containers_Number32BitArray$quorum_integer$quorum_boolean = function(uniformID, matrixCount, array, startIndex, transpose)
    {
        this.glUniformMatrix3fv(uniformID, transpose, array.plugin_.array_);
    };
    
    this.SetVertexInputInformation$quorum_integer$quorum_integer$quorum_integer$quorum_boolean$quorum_integer$quorum_integer = function(inputID, size, type, normalize, stride, offset)
    {
        this.glVertexAttribPointer(inputID, size, type, normalize, stride, offset);
    };
    
    this.SetVertexInputID$quorum_integer$quorum_integer$quorum_text = function(program, index, name)
    {
        gl.bindAttribLocation(program, index, name);
    };
    
    this.SetDefaultVertexValue$quorum_integer$quorum_number = function(location, x)
    {
        this.glVertexAttrib1f(location, x);
    };
    
    this.SetDefaultVertexValue$quorum_integer$quorum_number$quorum_number = function(location, x, y)
    {
        this.glVertexAttrib2f(location, x, y);
    };
    
    this.SetDefaultVertexValue$quorum_integer$quorum_number$quorum_number$quorum_number = function(location, x, y, z)
    {
        this.glVertexAttrib3f(location, x, y, z);
    };
    
    this.SetDefaultVertexValue$quorum_integer$quorum_number$quorum_number$quorum_number$quorum_number = function(location, x, y, z, w)
    {
        this.glVertexAttrib4f(location, x, y, z, w);
    };
    
    this.SetPixelClipping$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height)
    {
        this.glScissor(x, y, width, height);
    };
    
    this.GetGraphicsErrorCode = function()
    {
        return gl.getError();
    };
    
    this.CreateVertexArray = function()
    {
        return gl.createVertexArray();
    };
    
    this.DeleteVertexArray$quorum_integer = function(vao)
    {
        gl.deleteVertexArray(vao);
    };
    
    this.BindVertexArray$quorum_integer = function(array)
    {
        if (array === 0)
            array = null;
        gl.bindVertexArray(array);
    };
    
    this.GetGraphicsText$quorum_integer = function(code)
    {
        return this.glGetString(code);
    };

    this.GetGraphicsInteger$quorum_integer = function(code)
    {
        return this.glGetIntegerv(code);
    };
    
    // Expected parameters: integer, integer
    this.glBindTexture = function(target, texture)
    {
        if (texture === 0)
            texture = null;
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
        if (buffer === 0)
            buffer = null;
        gl.bindBuffer(target, buffer);
    };
    
    // Expected parameters: integer, ArrayBufferView (typed array object), integer
    this.glBufferData = function(target, data, usage)
    {
        gl.bufferData(target, data, usage);
    };
    
    // Expected parameters: integer, integer, ArrayBufferView (typed array object)
    this.glBufferSubData = function(target, offset, data)
    {
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

    // Expected parameters: WebGLLocation, boolean, array of floats (or Float32Array)
    this.glUniformMatrix2fv = function(location, transpose, value)
    {
        gl.uniformMatrix2fv(location, transpose, value);
    };

    // Expected parameters: WebGLLocation, boolean, array of floats (or Float32Array)
    this.glUniformMatrix3fv = function(location, transpose, value)
    {
        gl.uniformMatrix3fv(location, transpose, value);
    };

    // Expected parameters: WebGLLocation, boolean, array of floats (or Float32Array)
    this.glUniformMatrix4fv = function(location, transpose, value)
    {
        gl.uniformMatrix4fv(location, transpose, value);
    };
    
    // Expected parameters: integer, integer, integer, boolean, integer, integer
    this.glVertexAttribPointer = function(index, size, type, normalized, stride, pointer)
    {
        gl.vertexAttribPointer(index, size, type, normalized, stride, pointer);
    };
    
    // Expected parameter: integer
    this.glUseProgram = function(program)
    {
        if (program === 0)
            program = null;
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
    
    // Expected parameters: integer, float
    this.glVertexAttrib1f = function(index, x)
    {
        gl.vertexAttrib1f(index, x);
    };
    
    // Expected parameters: integer, float, float
    this.glVertexAttrib2f = function(index, x, y)
    {
        gl.vertexAttrib2f(index, x, y);
    };
    
    // Expected parameters: integer, float, float, float
    this.glVertexAttrib3f = function(index, x, y, z)
    {
        gl.vertexAttrib3f(index, x, y, z);
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
        if (Object.prototype.toString.call(pixels) === "[object HTMLImageElement]")
            gl.texImage2D(target, level, internalformat, format, type, pixels);
        else
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
    
    // Expected parameters: integer, integer, integer, integer
    this.glScissor = function(x, y, width, height)
    {
        gl.scissor(x, y, width, height);
    };

    /*
    Takes x, y, width, and height of the window. Also takes format (most commonly gl.RGBA),
    type (commonly gl.UNSIGNED_BYTE) and a buffer (must match associated type, usually Uint8Array).
    */
    this.glReadPixels = function(x, y, width, height, format, type, buffer)
    {
        gl.readPixels(x, y, width, height, format, type, buffer);
    };
}

