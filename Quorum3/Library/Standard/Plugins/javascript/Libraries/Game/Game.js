/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Game_() {
    this.GetSecondsBetweenFrames = function() {

    };
    this.SelectApplicationTypeNative = function() {
        return 4;
    };
}

// Code for the plugin-only ShaderProgram class.
function plugins_quorum_Libraries_Game_Graphics_ShaderProgram_(vertexShader, fragmentShader) 
{
    
    if (!plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.initialized_plugins_quorum_Libraries_Game_Graphics_ShaderProgram_)
    {
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.initialized_plugins_quorum_Libraries_Game_Graphics_ShaderProgram_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.POSITION_ATTRIBUTE = "a_position";
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.NORMAL_ATTRIBUTE = "a_normal";
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.COLOR_ATTRIBUTE = "a_color";
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TEXCOORD_ATTRIBUTE = "a_texCoord";
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TANGENT_ATTRIBUTE = "a_tangent";
        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.BINORMAL_ATTRIBUTE = "a_binormal";

        plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.pedantic = true;
    }
    
    if (vertexShader === null || vertexShader === undefined)
    {
        return;
    }
    if (fragmentShader === null || fragmentShader === undefined)
    {
        return;
    }
    
    this.CompileShaders = function(vertexShader, fragmentShader) 
    {
        var gl = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics.gl;
        
        this.vertexShaderHandle = this.LoadShader(gl.VERTEX_SHADER, vertexShader);
        this.fragmentShaderHandle = this.LoadShader(gl.FRAGMENT_SHADER, fragmentShader);

        if (this.vertexShaderHandle === -1 || this.fragmentShaderHandle === -1) 
        {
            this.isCompiled = false;
            return;
        }
        
        this.program = this.LinkProgram();
        if (this.program === -1) 
        {
            this.isCompiled = false;
            return;
        }

        this.isCompiled = true;
    };
    
    this.LoadShader = function(type, source)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        var shader = graphics.glCreateShader(type);
        if (shader === undefined || shader === null)
        {
            return -1;
        }
        
        graphics.glShaderSource(shader, source);
        graphics.glCompileShader(shader);
        var compiled = graphics.glGetShaderiv(shader, graphics.gl.COMPILE_STATUS);
        
        if (compiled === false)
        {
            var infoLogLength = graphics.glGetProgramiv(program, graphics.gl.INFO_LOG_LENGTH);
            if (infoLogLength > 1)
            {
                var infoLog = graphics.glGetShaderInfoLog(shader);
                this.log = this.log + infoLog;
                this.log = this.log + "Version is: " + graphics.glGetString(graphics.gl.VERSION);
            }
            return -1;
        }
        
        return shader;
    };
    
    this.LinkProgram = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        program = graphics.glCreateProgram();
        if (program === 0)
            return -1;
        
        graphics.glAttachShader(program, this.vertexShaderHandle);
        graphics.glAttachShader(program, this.fragmentShaderHandle);
        graphics.glLinkProgram(program);
        
        var linked = graphics.glGetProgramiv(program, graphics.gl.LINK_STATUS);
        if (linked === false)
        {
            var infoLogLength = graphics.glGetProgramiv(program, graphics.gl.INFO_LOG_LENGTH);
            if (infoLogLength > 1)
            {
                this.log = graphics.glGetProgramInfoLog(program);
            }
            return -1;
        }
        return program;
    };
    
    this.GetLog = function()
    {
        if (this.isCompiled)
        {
            var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
            var infoLogLength = graphics.glGetProgramiv(program, graphics.gl.INFO_LOG_LENGTH);
            if (infoLogLength > 1)
            {
                this.log = graphics.glGetProgramInfoLog(program);
            }
            return this.log;
        }
        else
        {
            return this.log;
        }
    };
    
    this.IsCompiled = function()
    {
        return this.isCompiled;
    };
    
    this.FetchAttributeLocation = function(name)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var location = attributes[name] || -2;
        
        if (location === -2)
        {
            location = graphics.glGetAttribLocation(program, name);
            attributes[name] = location;
        }
        
        return location;
    };
    
    this.FetchUniformLocation = function(name)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var location = uniforms[name] || -2;
        
        if (location === -2)
        {
            location = graphics.glGetUniformLocation(program, name);
            if (location === -1 && plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().pedantic)
            {
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't find a uniform with the name '" + name + "' in the shader!");
                throw exceptionInstance_;   
            }
            
            uniforms[name] = location;
        }
        return location;
    };
    
    this.SetUniform1iFromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform1i(location, value);
    };
    
    this.SetUniform1iAtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform1i(location, value);
    };
    
    this.SetUniform2iFromName = function(name, value1, value2)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform2i(location, value1, value2);
    };
    
    this.SetUniform2iAtLocation = function(location, value1, value2)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform2i(location, value1, value2);
    };
    
    this.SetUniform3iFromName = function(name, value1, value2, value3)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform3i(location, value1, value2, value3);
    };
    
    this.SetUniform3iAtLocation = function(location, value1, value2, value3)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform3i(location, value1, value2, value3);
    };
    
    this.SetUniform4iFromName = function(name, value1, value2, value3, value4)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform4i(location, value1, value2, value3, value4);
    };
    
    this.SetUniform4iAtLocation = function(location, value1, value2, value3, value4)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform4i(location, value1, value2, value3, value4);
    };
    
    this.SetUniform1fFromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform1f(location, value);
    };
    
    this.SetUniform1fAtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform1f(location, value);
    };
    
    this.SetUniform2fFromName = function(name, value1, value2)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform2f(location, value1, value2);
    };
    
    this.SetUniform2fAtLocation = function(location, value1, value2)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform2f(location, value1, value2);
    };
    
    this.SetUniform3fFromName = function(name, value1, value2, value3)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform3f(location, value1, value2, value3);
    };
    
    this.SetUniform3fAtLocation = function(location, value1, value2, value3)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform3f(location, value1, value2, value3);
    };
    
    this.SetUniform4fFromName = function(name, value1, value2, value3, value4)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchUniformLocation(name);
        graphics.glUniform4f(location, value1, value2, value3, value4);
    };
    
    this.SetUniform4fAtLocation = function(location, value1, value2, value3, value4)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform4f(location, value1, value2, value3, value4);
    };
    
    this.SetUniformVector1FromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = FetchUniformLocation(name);
        graphics.glUniform1fv(location, value);
    };
    
    this.SetUniformVector1AtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform1fv(location, value);
    };
    
    this.SetUniformVector4FromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = FetchUniformLocation(name);
        graphics.glUniform4fv(location, value);
    };
    
    this.SetUniformVector4AtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform4fv(location, value);
    };
    
    this.SetUniformMatrix4FromName = function(name, matrix, transpose)
    {
        this.SetUniformMatrix4AtLocation(this.FetchUniformLocation(name), matrix, transpose);
    };
    
    this.SetUniformMatrix4AtLocation = function(location, matrix, transpose)
    {
        if (transpose === undefined)
        {
            transpose = false;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var value = this.Matrix4ToArray(matrix);
        graphics.glUniformMatrix4fv(location, transpose, value);
    };
    
    this.SetUniformMatrix3FromName = function(name, matrix, transpose)
    {
        this.SetUniformMatrix3AtLocation(this.FetchUniformLocation(name), matrix, transpose);
    };
    
    this.SetUniformMatrix3AtLocation = function(location, matrix, transpose)
    {
        if (transpose === undefined)
        {
            transpose = false;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var value = this.Matrix3ToArray(matrix);
        graphics.glUniformMatrix3fv(location, transpose, value);
    };
    
    this.SetUniformMatrix4FromArray = function(location, array, transpose)
    {
        if (transpose === undefined)
        {
            transpose = false;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniformMatrix4fv(location, array, transpose);
    };
    
    this.SetUniformMatrix3FromArray = function(location, array, transpose)
    {
        if (transpose === undefined)
        {
            transpose = false;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniformMatrix3fv(location, array, transpose);
    };
    
    this.SetUniformVector2FromName = function(name, values)
    {
        this.SetUniform2fFromName(name, values.GetX(), values.GetY());
    };
    
    this.SetUniformVector2AtLocation = function(location, values)
    {
        this.SetUniform2fAtLocation(location, values.GetX(), values.GetY());
    };
    
    this.SetUniformVector3FromName = function(name, values)
    {
        this.SetUniform3fFromName(name, values.GetX(), values.GetY(), values.GetZ());
    };
    
    this.SetUniformVector3AtLocation = function(location, values)
    {
        this.SetUniform3fAtLocation(location, values.GetX(), values.GetY(), values.GetZ());
    };
    
    this.SetUniformColorFromName = function(name, color)
    {
        this.SetUniform4fFromName(name, color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
    };
    
    this.SetUniformColorAtLocation = function(location, color)
    {
        this.SetUniform4fAtLocation(location, color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
    };
    
    this.SetAttributeColor = function(name, color)
    {
        this.SetAttribute(name, color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
    };
    
    this.SetVertexAttributeFromName = function(name, size, type, normalize, stride, offset)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = FetchAttributeLocation(name);
        if (location === -1)
            return;
        graphics.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    };
    
    this.SetVertexAttributeAtLocation = function(location, size, type, normalize, stride, offset)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    };
    
    this.Begin = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUseProgram(program);
    };
    
    this.End = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glUseProgram(null);
    };
    
    this.Dispose = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glUseProgram(null);
        graphics.glDeleteShader(this.vertexShaderHandle);
        graphics.glDeleteShader(this.fragmentShaderHandle);
        graphics.glDeleteProgram(program);
    };
    
    this.DisableVertexAttributeFromName = function(name)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchAttributeLocation(name);
        if (location === -1)
            return;
        graphics.glDisableVertexAttribArray(location);
    };
    
    this.DisableVertexAttributeAtLocation = function(location)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glDisableVertexAttribArray(location);
    };
    
    this.EnableVertexAttributeFromName = function(name)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = this.FetchAttributeLocation(name);
        if (location === -1)
            return;
        graphics.glEnableVertexAttribArray(location);
    };
    
    this.EnableVertexAttributeAtLocation = function(location)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glEnableVertexAttribArray(location);
    };
    
    this.CheckManaged = function()
    {
        if (this.invalidated)
        {
            this.CompileShaders(this.vertexShaderSource, this.fragmentShaderSource);
            this.invalidated = false;
        }
    };
    
    this.SetAttribute = function(name, value1, value2, value3, value4)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var location = FetchAttributeLocation(name);
        graphics.glVertexAttrib4f(location, value1, value2, value3, value4);
    };
    
    this.FetchAttributes = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var attributeCount = graphics.glGetProgramiv(program, graphics.gl.ACTIVE_ATTRIBUTES);
        var info;
        var location;
        
        this.attributeNames = [];
        
        for (var i = 0; i < attributeCount; i++)
        {
            info = graphics.glGetActiveAttrib(program, i);
            location = graphics.glGetAttribLocation(program, info.name);
            attributes[info.name] = location;
            attributeTypes[info.name] = info.type;
            attributeSizes[info.name] = info.size;
            this.attributeNames[i] = info.name;
        }
    };
    
    this.FetchUniforms = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var uniformCount = graphics.glGetProgramiv(program, graphics.gl.ACTIVE_UNIFORMS);
        var info;
        var location;
        
        this.uniformNames = [];
        
        for (var i = 0; i < uniformCount; i++)
        {
            info = graphics.glGetActiveUniform(program, i);
            location = graphics.glGetUniformLocation(program, info.name);
            uniforms[info.name] = location;
            uniformTypes[info.name] = info.type;
            uniformSizes[info.name] = info.size;
            this.uniformNames[i] = info.name;
        }
    };
    
    this.HasAttribute = function(name)
    {
        return name in attributes;
    };
    
    this.GetAttributeType = function(name)
    {
        return attributeTypes[name] || 0;
    };
    
    this.GetAttributeLocation = function(name)
    {
        if (!(name in attributes))
            return -1;
        
        return attributes[name];
    };
    
    this.GetAttributeSize = function(name)
    {
        return attributeSizes[name] || 0;
    };
    
    this.HasUniform = function(name)
    {
        return name in uniforms;
    };
    
    this.GetUniformType = function(name)
    {
        return uniformTypes[name] || 0;
    };
    
    this.GetUniformLocation = function(name)
    {
        if (!(name in uniforms))
            return -1;
        
        return uniforms[name];
    };
    
    this.GetUniformSize = function(name)
    {
        return uniformSizes[name] || 0;
    };
    
    this.GetAttributes = function()
    {
        return this.attributeNames;
    };
    
    this.GetUniforms = function()
    {
        return this.uniformNames;
    };
    
    this.GetVertexShaderSource = function()
    {
        return vertexShaderSource;
    };
    
    this.GetFragmentShaderSource = function()
    {
        return fragmentShaderSource;
    };
    
    this.Matrix3ToArray = function(matrix)
    {
        var temp = new Float32Array(9);
        
        temp[0] = matrix.row0column0;
        temp[1] = matrix.row1column0;
        temp[2] = matrix.row2column0;
        temp[3] = matrix.row0column1;
        temp[4] = matrix.row1column1;
        temp[5] = matrix.row2column1;
        temp[6] = matrix.row0column2;
        temp[7] = matrix.row1column2;
        temp[8] = matrix.row2column2;
        
        return temp;
    };
    
    this.Matrix4ToArray = function(matrix)
    {
        var temp = new Float32Array(16);
        
        temp[0] = matrix.row0column0;
        temp[1] = matrix.row1column0;
        temp[2] = matrix.row2column0;
        temp[3] = matrix.row3column0;
        temp[4] = matrix.row0column1;
        temp[5] = matrix.row1column1;
        temp[6] = matrix.row2column1;
        temp[7] = matrix.row3column1;
        temp[8] = matrix.row0column2;
        temp[9] = matrix.row1column2;
        temp[10] = matrix.row2column2;
        temp[11] = matrix.row3column2;
        temp[12] = matrix.row0column3;
        temp[13] = matrix.row1column3;
        temp[14] = matrix.row2column3;
        temp[15] = matrix.row3column3;
        
        return temp;
    };
    
    var exceptionInstance_;
    
    this.isCompiled = false;
    
    this.log = "";
    
    var uniforms = {};
    var uniformTypes = {};
    var uniformSizes = {};
    
    this.uniformNames = null;
    
    var attributes = {};
    var attributeTypes = {};
    var attributeSizes = {};

    this.attributeNames = null;
    
    // Whether this shader was invalidated.
    this.invalidated = false;

    this.referenceCount = 0;
    
    var program;
    
    // Source code for the vertex shader.
    this.vertexShaderSource = vertexShader;

    // Source code for the fragment shader.
    this.fragmentShaderSource = fragmentShader;

    this.CompileShaders(vertexShader, fragmentShader);
    if (this.IsCompiled())
    {
        this.FetchAttributes();
        this.FetchUniforms();
    }
    else
    {
        exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Failed to compile shader: " + this.GetLog());
        throw exceptionInstance_;
    }   
}

// Code for the plugin-only TextureBinder class.
function plugins_quorum_Libraries_Game_Graphics_TextureBinder_()
{
    /*
    The web implementation of this class does not include code to perform
    the tasks in round-robin fashion, unlike the desktop implemenation of this
    class (which can use round robin, but always uses weighted as the default
    and doesn't currently allow a user to change its method of operation).
    */
    
    this.Begin = function()
    {
        for (var i = 0; i < count; i++)
        {
            textures[i] = null;
            weights[i] = 0;
        }
    };
    
    this.End = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glActiveTexture(graphics.gl.TEXTURE0);
    };
    
    this.BindDescriptor = function(descriptor)
    {
        return this.BindTexture(descriptor, false);
    };
    
    this.Bind = function(texture)
    {
        tempDescriptor.SetDescriptor(texture, null, null, null, null);
        return this.BindTexture(tempDescriptor, false);
    };
    
    this.BindTexture = function(descriptor, rebind)
    {
        var texture = descriptor.texture;
        reused = false;
        
        var index = this.BindTextureWeighted(texture);
        var result = offset + index;
        
        if (reused)
        {
            if (rebind)
                texture.plugin_.Bind(result);
            else
            {
                var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
                graphics.glActiveTexture(graphics.gl.TEXTURE0 + result);
            }
        }
        
        texture.UnsafeSetWrap(descriptor.uWrap, descriptor.vWrap);
        texture.UnsafeSetFilter(descriptor.minFilter, descriptor.magFilter);
        return result;
    };
    
    this.GetMaxTextureUnits = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        return graphics.glGetIntegerv(graphics.gl.MAX_TEXTURE_IMAGE_UNITS);
    };
    
    this.BindTextureWeighted = function(texture)
    {
        var result = -1;
        var weight = weights[0];
        var wIndex = 0;
        for (var i = 0; i < count; i++)
        {
            if (textures[i] === texture)
            {
                result = i;
                weights[i] += reuseWeight;
            }
            else if (weights[i] < 0 || --weights[i] < weight)
            {
                weight = weights[i];
                wIndex = i;
            }
        }
        if (result < 0)
        {
            textures[wIndex] = texture;
            weights[wIndex] = 100;
            result = wIndex;
            texture.plugin_.Bind(offset + result);
        }
        else
            reused = true;
        
        return result;
    };
    
    this.MAX_GLES_UNITS = 32;
    
    var offset = 1;
    var count;
    var reuseWeight = 10;
    var textures = [];
    var weights = [];
    var reused;
    var tempDescriptor = new quorum_Libraries_Game_Graphics_TextureDescriptor_();
    var currentTexture = 0;
    
    if (this.GetMaxTextureUnits() > this.MAX_GLES_UNITS)
        count = this.GetMaxTextureUnits() - 1;
    else
        count = this.MAX_GLES_UNITS - 1;
}

// Code for the plugin-only RenderContext class.
function plugins_quorum_Libraries_Game_Graphics_RenderContext_()
{
    this.textureBinder = plugins_quorum_Libraries_Game_Graphics_TextureBinder_();
    
    var blending;
    var blendSFactor;
    var blendDFactor;
    var depthFunc;
    var depthRangeNear;
    var depthRangeFar;
    var depthMask;
    var cullFace;
    
    this.Begin = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        graphics.glDisable(graphics.gl.DEPTH_TEST);
        depthFunc = 0;
        graphics.glDepthMask(true);
        depthMask = true;
        graphics.glDisable(graphics.gl.BLEND);
        blending = false;
        graphics.glDisable(graphics.gl.CULL_FACE);
        cullFace = 0;
        blendSFactor = 0;
        blendDFactor = 0;
        textureBinder.Begin();
    };
    
    this.End = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        if (depthFunc !== 0)
            graphics.glDisable(graphics.gl.DEPTH_TEST);
        if (!depthMask)
            graphics.glDepthMask(true);
        if (blending)
            graphics.glDisable(graphics.gl.BLEND);
        if (cullFace > 0)
            graphics.glDisable(graphics.gl.CULL_FACE);
        
        textureBinder.End();
    };
    
    this.SetDepthMask = function(mask)
    {
        if (depthMask !== mask)
        {
            var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
            graphics.glDepthMask(mask);
            depthMask = mask;
        }
    };
    
    this.SetDepthTest = function(depthFunction, depthNear, depthFar)
    {
        if (depthNear === undefined)
            depthNear = 0;
        if (depthFar === undefined)
            depthFar = 0;
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        var wasEnabled = depthFunc !== 0;
        var enabled = depthFunction !== 0;
        if (depthFunc !== depthFunction)
        {
            depthFunc = depthFunction;
            if (enabled)
            {
                graphics.glEnable(graphics.gl.DEPTH_TEST);
                graphics.glDepthFunc(depthFunction);
            }
            else
                graphics.glDisable(graphics.gl.DEPTH_TEST);
        }
        if (enabled)
        {
            if (!wasEnabled || depthFunc !== depthFunction)
                graphics.glDepthFunc(depthFunc = depthFunction);
            if (!wasEnabled || depthRangeNear !== depthNear || depthRangeFar !== depthFar)
            {
                graphics.glDepthRangef(depthNear, depthFar);
                depthRangeNear = depthNear;
                depthRangeFar = depthFar;
            }
        }
    };
    
    this.SetBlending = function(enabled, sFactor, dFactor)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        if (enabled !== blending)
        {
            blending = enabled;
            if (enabled)
                graphics.glEnable(graphics.gl.BLEND);
            else
                graphics.glDisable(graphics.gl.BLEND);
        }
        
        if (enabled && (blendSFactor !== sFactor || blendDFactor !== dFactor))
        {
            graphics.glBlendFunc(sFactor, dFactor);
            blendSFactor = sFactor;
            blendDFactor = dFactor;
        }
    };
    
    this.SetCullFace = function(face)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        if (face !== cullFace)
        {
            cullFace = face;
            if ((face === graphics.gl.FRONT) || (face === graphics.gl.BACK) || (face === graphics.gl.FRONT_AND_BACK))
            {
                graphics.glEnable(graphics.gl.CULL_FACE);
                graphics.glCullFace(face);
            }
            else
                graphics.glDisable(graphics.gl.CULL_FACE);
        }
    };
}

// Code for the plugin-only ShaderProvider class.
function plugins_quorum_Libraries_Game_Graphics_ShaderProvider_()
{
    var shaders = [];
    this.config = new plugins_quorum_Libraries_Game_Graphics_DefaultShaderConfig_();
    
    this.GetShader = function(renderable)
    {
        var suggestedShader = renderable.plugin_.shader;
        var shader;
        if (suggestedShader !== null && suggestedShader.CanRender(renderable))
            return suggestedShader;
        for (var i = 0; i < shaders.length; i++)
        {
            shader = shaders[i];
            if (shader.CanRender(renderable))
                return shader;
        }
        shader = this.CreateShader(renderable);
        shader.Initialize();
        shaders.push(shader);
        return shader;
    };
    
    this.CreateShader = function(renderable)
    {
        return new plugins_quorum_Libraries_Game_Graphics_DefaultShader_(renderable, this.config);
    };
    
    this.Dispose = function()
    {
        for (var i = 0; i < shaders.length; i++)
        {
            var shader = shaders[i];
            shader.Dispose();
        }
        shaders = [];
    };
}

// Code for the plugin-only DefaultGLSLStrings class.
function plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_()
{
    if (!plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.initialized_plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_)
    {
        plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.initialized_plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.vertexShader = 
            "#if defined(diffuseTextureFlag) || defined(specularTextureFlag)\n" +
            "#define textureFlag\n" +
            "#endif\n" +
            "\n" +
            "#if defined(specularTextureFlag) || defined(specularColorFlag)\n" +
            "#define specularFlag\n" +
            "#endif\n" +
            "\n" +
            "#if defined(specularFlag) || defined(fogFlag)\n" +
            "#define cameraPositionFlag\n" +
            "#endif\n" +
            "\n" +
            "attribute vec3 a_position;\n" +
            "uniform mat4 u_projViewTrans;\n" +
            "\n" +
            "#if defined(colorFlag)\n" +
            "varying vec4 v_color;\n" +
            "attribute vec4 a_color;\n" +
            "#endif // colorFlag\n" +
            "\n" +
            "#ifdef normalFlag\n" +
            "attribute vec3 a_normal;\n" +
            "uniform mat3 u_normalMatrix;\n" +
            "varying vec3 v_normal;\n" +
            "#endif // normalFlag\n" +
            "\n" +
            "#ifdef textureFlag\n" +
            "attribute vec2 a_texCoord0;\n" +
            "#endif // textureFlag\n" +
            "\n" +
            "#ifdef diffuseTextureFlag\n" +
            "uniform vec4 u_diffuseUVTransform;\n" +
            "varying vec2 v_diffuseUV;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef specularTextureFlag\n" +
            "uniform vec4 u_specularUVTransform;\n" +
            "varying vec2 v_specularUV;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef boneWeight0Flag\n" +
            "#define boneWeightsFlag\n" +
            "attribute vec2 a_boneWeight0;\n" +
            "#endif //boneWeight0Flag\n" +
            "\n" +
            "#ifdef boneWeight1Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight1;\n" +
            "#endif //boneWeight1Flag\n" +
            "\n" +
            "#ifdef boneWeight2Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight2;\n" +
            "#endif //boneWeight2Flag\n" +
            "\n" +
            "#ifdef boneWeight3Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight3;\n" +
            "#endif //boneWeight3Flag\n" +
            "\n" +
            "#ifdef boneWeight4Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight4;\n" +
            "#endif //boneWeight4Flag\n" +
            "\n" +
            "#ifdef boneWeight5Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight5;\n" +
            "#endif //boneWeight5Flag\n" +
            "\n" +
            "#ifdef boneWeight6Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight6;\n" +
            "#endif //boneWeight6Flag\n" +
            "\n" +
            "#ifdef boneWeight7Flag\n" +
            "#ifndef boneWeightsFlag\n" +
            "#define boneWeightsFlag\n" +
            "#endif\n" +
            "attribute vec2 a_boneWeight7;\n" +
            "#endif //boneWeight7Flag\n" +
            "\n" +
            "#if defined(numBones) && defined(boneWeightsFlag)\n" +
            "#if (numBones > 0) \n" +
            "#define skinningFlag\n" +
            "#endif\n" +
            "#endif\n" +
            "\n" +
            "uniform mat4 u_worldTrans;\n" +
            "\n" +
            "#if defined(numBones)\n" +
            "#if numBones > 0\n" +
            "uniform mat4 u_bones[numBones];\n" +
            "#endif //numBones\n" +
            "#endif\n" +
            "\n" +
            "#ifdef shininessFlag\n" +
            "uniform float u_shininess;\n" +
            "#else\n" +
            "const float u_shininess = 20.0;\n" +
            "#endif // shininessFlag\n" +
            "\n" +
            "#ifdef blendedFlag\n" +
            "uniform float u_opacity;\n" +
            "varying float v_opacity;\n" +
            "\n" +
            "#ifdef alphaTestFlag\n" +
            "uniform float u_alphaTest;\n" +
            "varying float v_alphaTest;\n" +
            "#endif //alphaTestFlag\n" +
            "#endif // blendedFlag\n" +
            "\n" +
            "#ifdef lightingFlag\n" +
            "varying vec3 v_lightDiffuse;\n" +
            "\n" +
            "#ifdef ambientLightFlag\n" +
            "uniform vec3 u_ambientLight;\n" +
            "#endif // ambientLightFlag\n" +
            "\n" +
            "#ifdef ambientCubemapFlag\n" +
            "uniform vec3 u_ambientCubemap[6];\n" +
            "#endif // ambientCubemapFlag \n" +
            "\n" +
            "#ifdef sphericalHarmonicsFlag\n" +
            "uniform vec3 u_sphericalHarmonics[9];\n" +
            "#endif //sphericalHarmonicsFlag\n" +
            "\n" +
            "#ifdef specularFlag\n" +
            "varying vec3 v_lightSpecular;\n" +
            "#endif // specularFlag\n" +
            "\n" +
            "#ifdef cameraPositionFlag\n" +
            "uniform vec4 u_cameraPosition;\n" +
            "#endif // cameraPositionFlag\n" +
            "\n" +
            "#ifdef fogFlag\n" +
            "varying float v_fog;\n" +
            "#endif // fogFlag\n" +
            "\n" +
            "\n" +
            "#if defined(numDirectionalLights) && (numDirectionalLights > 0)\n" +
            "struct DirectionalLight\n" +
            "{\n" +
            "	vec3 color;\n" +
            "	vec3 direction;\n" +
            "};\n" +
            "uniform DirectionalLight u_dirLights[numDirectionalLights];\n" +
            "#endif // numDirectionalLights\n" +
            "\n" +
            "#if defined(numPointLights) && (numPointLights > 0)\n" +
            "struct PointLight\n" +
            "{\n" +
            "	vec3 color;\n" +
            "	vec3 position;\n" +
            "};\n" +
            "uniform PointLight u_pointLights[numPointLights];\n" +
            "#endif // numPointLights\n" +
            "\n" +
            "#if	defined(ambientLightFlag) || defined(ambientCubemapFlag) || defined(sphericalHarmonicsFlag)\n" +
            "#define ambientFlag\n" +
            "#endif //ambientFlag\n" +
            "\n" +
            "#ifdef shadowMapFlag\n" +
            "uniform mat4 u_shadowMapProjViewTrans;\n" +
            "varying vec3 v_shadowMapUv;\n" +
            "#define separateAmbientFlag\n" +
            "#endif //shadowMapFlag\n" +
            "\n" +
            "#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
            "varying vec3 v_ambientLight;\n" +
            "#endif //separateAmbientFlag\n" +
            "\n" +
            "#endif // lightingFlag\n" +
            "\n" +
            "void main() {\n" +
            "	#ifdef diffuseTextureFlag\n" +
            "		v_diffuseUV = u_diffuseUVTransform.xy + a_texCoord0 * u_diffuseUVTransform.zw;\n" +
            "	#endif //diffuseTextureFlag\n" +
            "	\n" +
            "	#ifdef specularTextureFlag\n" +
            "		v_specularUV = u_specularUVTransform.xy + a_texCoord0 * u_specularUVTransform.zw;\n" +
            "	#endif //specularTextureFlag\n" +
            "	\n" +
            "	#if defined(colorFlag)\n" +
            "		v_color = a_color;\n" +
            "	#endif // colorFlag\n" +
            "		\n" +
            "	#ifdef blendedFlag\n" +
            "		v_opacity = u_opacity;\n" +
            "		#ifdef alphaTestFlag\n" +
            "			v_alphaTest = u_alphaTest;\n" +
            "		#endif //alphaTestFlag\n" +
            "	#endif // blendedFlag\n" +
            "	\n" +
            "	#ifdef skinningFlag\n" +
            "		mat4 skinning = mat4(0.0);\n" +
            "		#ifdef boneWeight0Flag\n" +
            "			skinning += (a_boneWeight0.y) * u_bones[int(a_boneWeight0.x)];\n" +
            "		#endif //boneWeight0Flag\n" +
            "		#ifdef boneWeight1Flag				\n" +
            "			skinning += (a_boneWeight1.y) * u_bones[int(a_boneWeight1.x)];\n" +
            "		#endif //boneWeight1Flag\n" +
            "		#ifdef boneWeight2Flag		\n" +
            "			skinning += (a_boneWeight2.y) * u_bones[int(a_boneWeight2.x)];\n" +
            "		#endif //boneWeight2Flag\n" +
            "		#ifdef boneWeight3Flag\n" +
            "			skinning += (a_boneWeight3.y) * u_bones[int(a_boneWeight3.x)];\n" +
            "		#endif //boneWeight3Flag\n" +
            "		#ifdef boneWeight4Flag\n" +
            "			skinning += (a_boneWeight4.y) * u_bones[int(a_boneWeight4.x)];\n" +
            "		#endif //boneWeight4Flag\n" +
            "		#ifdef boneWeight5Flag\n" +
            "			skinning += (a_boneWeight5.y) * u_bones[int(a_boneWeight5.x)];\n" +
            "		#endif //boneWeight5Flag\n" +
            "		#ifdef boneWeight6Flag\n" +
            "			skinning += (a_boneWeight6.y) * u_bones[int(a_boneWeight6.x)];\n" +
            "		#endif //boneWeight6Flag\n" +
            "		#ifdef boneWeight7Flag\n" +
            "			skinning += (a_boneWeight7.y) * u_bones[int(a_boneWeight7.x)];\n" +
            "		#endif //boneWeight7Flag\n" +
            "	#endif //skinningFlag\n" +
            "\n" +
            "	#ifdef skinningFlag\n" +
            "		vec4 pos = u_worldTrans * skinning * vec4(a_position, 1.0);\n" +
            "	#else\n" +
            "		vec4 pos = u_worldTrans * vec4(a_position, 1.0);\n" +
            "	#endif\n" +
            "		\n" +
            "	gl_Position = u_projViewTrans * pos;\n" +
            "		\n" +
            "	#ifdef shadowMapFlag\n" +
            "		vec4 spos = u_shadowMapProjViewTrans * pos;\n" +
            "		v_shadowMapUv.xy = (spos.xy / spos.w) * 0.5 + 0.5;\n" +
            "		v_shadowMapUv.z = min(spos.z * 0.5 + 0.5, 0.998);\n" +
            "	#endif //shadowMapFlag\n" +
            "	\n" +
            "	#if defined(normalFlag)\n" +
            "		#if defined(skinningFlag)\n" +
            "			vec3 normal = normalize((u_worldTrans * skinning * vec4(a_normal, 0.0)).xyz);\n" +
            "		#else\n" +
            "			vec3 normal = normalize(u_normalMatrix * a_normal);\n" +
            "		#endif\n" +
            "		v_normal = normal;\n" +
            "	#endif // normalFlag\n" +
            "\n" +
            "    #ifdef fogFlag\n" +
            "        vec3 flen = u_cameraPosition.xyz - pos.xyz;\n" +
            "        float fog = dot(flen, flen) * u_cameraPosition.w;\n" +
            "        v_fog = min(fog, 1.0);\n" +
            "    #endif\n" +
            "\n" +
            "	#ifdef lightingFlag\n" +
            "		#if	defined(ambientLightFlag)\n" +
            "        	vec3 ambientLight = u_ambientLight;\n" +
            "		#elif defined(ambientFlag)\n" +
            "        	vec3 ambientLight = vec3(0.0);\n" +
            "		#endif\n" +
            "			\n" +
            "		#ifdef ambientCubemapFlag 		\n" +
            "			vec3 squaredNormal = normal * normal;\n" +
            "			vec3 isPositive  = step(0.0, normal);\n" +
            "			ambientLight += squaredNormal.x * mix(u_ambientCubemap[0], u_ambientCubemap[1], isPositive.x) +\n" +
            "					squaredNormal.y * mix(u_ambientCubemap[2], u_ambientCubemap[3], isPositive.y) +\n" +
            "					squaredNormal.z * mix(u_ambientCubemap[4], u_ambientCubemap[5], isPositive.z);\n" +
            "		#endif // ambientCubemapFlag\n" +
            "\n" +
            "		#ifdef sphericalHarmonicsFlag\n" +
            "			ambientLight += u_sphericalHarmonics[0];\n" +
            "			ambientLight += u_sphericalHarmonics[1] * normal.x;\n" +
            "			ambientLight += u_sphericalHarmonics[2] * normal.y;\n" +
            "			ambientLight += u_sphericalHarmonics[3] * normal.z;\n" +
            "			ambientLight += u_sphericalHarmonics[4] * (normal.x * normal.z);\n" +
            "			ambientLight += u_sphericalHarmonics[5] * (normal.z * normal.y);\n" +
            "			ambientLight += u_sphericalHarmonics[6] * (normal.y * normal.x);\n" +
            "			ambientLight += u_sphericalHarmonics[7] * (3.0 * normal.z * normal.z - 1.0);\n" +
            "			ambientLight += u_sphericalHarmonics[8] * (normal.x * normal.x - normal.y * normal.y);			\n" +
            "		#endif // sphericalHarmonicsFlag\n" +
            "\n" +
            "		#ifdef ambientFlag\n" +
            "			#ifdef separateAmbientFlag\n" +
            "				v_ambientLight = ambientLight;\n" +
            "				v_lightDiffuse = vec3(0.0);\n" +
            "			#else\n" +
            "				v_lightDiffuse = ambientLight;\n" +
            "			#endif //separateAmbientFlag\n" +
            "		#else\n" +
            "	        v_lightDiffuse = vec3(0.0);\n" +
            "		#endif //ambientFlag\n" +
            "\n" +
            "			\n" +
            "		#ifdef specularFlag\n" +
            "			v_lightSpecular = vec3(0.0);\n" +
            "			vec3 viewVec = normalize(u_cameraPosition.xyz - pos.xyz);\n" +
            "		#endif // specularFlag\n" +
            "			\n" +
            "		#if defined(numDirectionalLights) && (numDirectionalLights > 0) && defined(normalFlag)\n" +
            "			for (int i = 0; i < numDirectionalLights; i++) {\n" +
            "				vec3 lightDir = -u_dirLights[i].direction;\n" +
            "				float NdotL = clamp(dot(normal, lightDir), 0.0, 1.0);\n" +
            "				vec3 value = u_dirLights[i].color * NdotL;\n" +
            "				v_lightDiffuse += value;\n" +
            "				#ifdef specularFlag\n" +
            "					float halfDotView = max(0.0, dot(normal, normalize(lightDir + viewVec)));\n" +
            "					v_lightSpecular += value * pow(halfDotView, u_shininess);\n" +
            "				#endif // specularFlag\n" +
            "			}\n" +
            "		#endif // numDirectionalLights\n" +
            "\n" +
            "		#if defined(numPointLights) && (numPointLights > 0) && defined(normalFlag)\n" +
            "			for (int i = 0; i < numPointLights; i++) {\n" +
            "				vec3 lightDir = u_pointLights[i].position - pos.xyz;\n" +
            "				float dist2 = dot(lightDir, lightDir);\n" +
            "				lightDir *= inversesqrt(dist2);\n" +
            "				float NdotL = clamp(dot(normal, lightDir), 0.0, 1.0);\n" +
            "				vec3 value = u_pointLights[i].color * (NdotL / (1.0 + dist2));\n" +
            "				v_lightDiffuse += value;\n" +
            "				#ifdef specularFlag\n" +
            "					float halfDotView = max(0.0, dot(normal, normalize(lightDir + viewVec)));\n" +
            "					v_lightSpecular += value * pow(halfDotView, u_shininess);\n" +
            "				#endif // specularFlag\n" +
            "			}\n" +
            "		#endif // numPointLights\n" +
            "	#endif // lightingFlag\n" +
            "}";
    
        plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.fragmentShader =
            "#ifdef GL_ES \n" +
            "#define LOWP lowp\n" +
            "#define MED mediump\n" +
            "#define HIGH highp\n" +
            "precision mediump float;\n" +
            "#else\n" +
            "#define MED\n" +
            "#define LOWP\n" +
            "#define HIGH\n" +
            "#endif\n" +
            "\n" +
            "#if defined(specularTextureFlag) || defined(specularColorFlag)\n" +
            "#define specularFlag\n" +
            "#endif\n" +
            "\n" +
            "#ifdef normalFlag\n" +
            "varying vec3 v_normal;\n" +
            "#endif //normalFlag\n" +
            "\n" +
            "#if defined(colorFlag)\n" +
            "varying vec4 v_color;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef blendedFlag\n" +
            "varying float v_opacity;\n" +
            "#ifdef alphaTestFlag\n" +
            "varying float v_alphaTest;\n" +
            "#endif //alphaTestFlag\n" +
            "#endif //blendedFlag\n" +
            "\n" +
            "#if defined(diffuseTextureFlag) || defined(specularTextureFlag)\n" +
            "#define textureFlag\n" +
            "#endif\n" +
            "\n" +
            "#ifdef diffuseTextureFlag\n" +
            "varying MED vec2 v_diffuseUV;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef specularTextureFlag\n" +
            "varying MED vec2 v_specularUV;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef diffuseColorFlag\n" +
            "uniform vec4 u_diffuseColor;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef diffuseTextureFlag\n" +
            "uniform sampler2D u_diffuseTexture;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef specularColorFlag\n" +
            "uniform vec4 u_specularColor;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef specularTextureFlag\n" +
            "uniform sampler2D u_specularTexture;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef normalTextureFlag\n" +
            "uniform sampler2D u_normalTexture;\n" +
            "#endif\n" +
            "\n" +
            "#ifdef lightingFlag\n" +
            "varying vec3 v_lightDiffuse;\n" +
            "\n" +
            "#if	defined(ambientLightFlag) || defined(ambientCubemapFlag) || defined(sphericalHarmonicsFlag)\n" +
            "#define ambientFlag\n" +
            "#endif //ambientFlag\n" +
            "\n" +
            "#ifdef specularFlag\n" +
            "varying vec3 v_lightSpecular;\n" +
            "#endif //specularFlag\n" +
            "\n" +
            "#ifdef shadowMapFlag\n" +
            "uniform sampler2D u_shadowTexture;\n" +
            "uniform float u_shadowPCFOffset;\n" +
            "varying vec3 v_shadowMapUv;\n" +
            "#define separateAmbientFlag\n" +
            "\n" +
            "float getShadowness(vec2 offset)\n" +
            "{\n" +
            "    const vec4 bitShifts = vec4(1.0, 1.0 / 255.0, 1.0 / 65025.0, 1.0 / 160581375.0);\n" +
            "    return step(v_shadowMapUv.z, dot(texture2D(u_shadowTexture, v_shadowMapUv.xy + offset), bitShifts));//+(1.0/255.0));	\n" +
            "}\n" +
            "\n" +
            "float getShadow() \n" +
            "{\n" +
            "	return (//getShadowness(vec2(0,0)) + \n" +
            "			getShadowness(vec2(u_shadowPCFOffset, u_shadowPCFOffset)) +\n" +
            "			getShadowness(vec2(-u_shadowPCFOffset, u_shadowPCFOffset)) +\n" +
            "			getShadowness(vec2(u_shadowPCFOffset, -u_shadowPCFOffset)) +\n" +
            "			getShadowness(vec2(-u_shadowPCFOffset, -u_shadowPCFOffset))) * 0.25;\n" +
            "}\n" +
            "#endif //shadowMapFlag\n" +
            "\n" +
            "#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
            "varying vec3 v_ambientLight;\n" +
            "#endif //separateAmbientFlag\n" +
            "\n" +
            "#endif //lightingFlag\n" +
            "\n" +
            "#ifdef fogFlag\n" +
            "uniform vec4 u_fogColor;\n" +
            "varying float v_fog;\n" +
            "#endif // fogFlag\n" +
            "\n" +
            "void main() {\n" +
            "	#if defined(normalFlag) \n" +
            "		vec3 normal = v_normal;\n" +
            "	#endif // normalFlag\n" +
            "		\n" +
            "	#if defined(diffuseTextureFlag) && defined(diffuseColorFlag) && defined(colorFlag)\n" +
            "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * u_diffuseColor * v_color;\n" +
            "	#elif defined(diffuseTextureFlag) && defined(diffuseColorFlag)\n" +
            "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * u_diffuseColor;\n" +
            "	#elif defined(diffuseTextureFlag) && defined(colorFlag)\n" +
            "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * v_color;\n" +
            "	#elif defined(diffuseTextureFlag)\n" +
            "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV);\n" +
            "	#elif defined(diffuseColorFlag) && defined(colorFlag)\n" +
            "		vec4 diffuse = u_diffuseColor * v_color;\n" +
            "	#elif defined(diffuseColorFlag)\n" +
            "		vec4 diffuse = u_diffuseColor;\n" +
            "	#elif defined(colorFlag)\n" +
            "		vec4 diffuse = v_color;\n" +
            "	#else\n" +
            "		vec4 diffuse = vec4(1.0);\n" +
            "	#endif\n" +
            "\n" +
            "	#if (!defined(lightingFlag))  \n" +
            "		gl_FragColor.rgb = diffuse.rgb;\n" +
            "	#elif (!defined(specularFlag))\n" +
            "		#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
            "			#ifdef shadowMapFlag\n" +
            "				gl_FragColor.rgb = (diffuse.rgb * (v_ambientLight + getShadow() * v_lightDiffuse));\n" +
            "				//gl_FragColor.rgb = texture2D(u_shadowTexture, v_shadowMapUv.xy);\n" +
            "			#else\n" +
            "				gl_FragColor.rgb = (diffuse.rgb * (v_ambientLight + v_lightDiffuse));\n" +
            "			#endif //shadowMapFlag\n" +
            "		#else\n" +
            "			#ifdef shadowMapFlag\n" +
            "				gl_FragColor.rgb = getShadow() * (diffuse.rgb * v_lightDiffuse);\n" +
            "			#else\n" +
            "				gl_FragColor.rgb = (diffuse.rgb * v_lightDiffuse);\n" +
            "			#endif //shadowMapFlag\n" +
            "		#endif\n" +
            "	#else\n" +
            "		#if defined(specularTextureFlag) && defined(specularColorFlag)\n" +
            "			vec3 specular = texture2D(u_specularTexture, v_specularUV).rgb * u_specularColor.rgb * v_lightSpecular;\n" +
            "		#elif defined(specularTextureFlag)\n" +
            "			vec3 specular = texture2D(u_specularTexture, v_specularUV).rgb * v_lightSpecular;\n" +
            "		#elif defined(specularColorFlag)\n" +
            "			vec3 specular = u_specularColor.rgb * v_lightSpecular;\n" +
            "		#else\n" +
            "			vec3 specular = v_lightSpecular;\n" +
            "		#endif\n" +
            "			\n" +
            "		#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
            "			#ifdef shadowMapFlag\n" +
            "			gl_FragColor.rgb = (diffuse.rgb * (getShadow() * v_lightDiffuse + v_ambientLight)) + specular;\n" +
            "				//gl_FragColor.rgb = texture2D(u_shadowTexture, v_shadowMapUv.xy);\n" +
            "			#else\n" +
            "				gl_FragColor.rgb = (diffuse.rgb * (v_lightDiffuse + v_ambientLight)) + specular;\n" +
            "			#endif //shadowMapFlag\n" +
            "		#else\n" +
            "			#ifdef shadowMapFlag\n" +
            "				gl_FragColor.rgb = getShadow() * ((diffuse.rgb * v_lightDiffuse) + specular);\n" +
            "			#else\n" +
            "				gl_FragColor.rgb = (diffuse.rgb * v_lightDiffuse) + specular;\n" +
            "			#endif //shadowMapFlag\n" +
            "		#endif\n" +
            "	#endif //lightingFlag\n" +
            "\n" +
            "	#ifdef fogFlag\n" +
            "		gl_FragColor.rgb = mix(gl_FragColor.rgb, u_fogColor.rgb, v_fog);\n" +
            "	#endif // end fogFlag\n" +
            "\n" +
            "	#ifdef blendedFlag\n" +
            "		gl_FragColor.a = diffuse.a * v_opacity;\n" +
            "		#ifdef alphaTestFlag\n" +
            "			if (gl_FragColor.a <= v_alphaTest)\n" +
            "				discard;\n" +
            "		#endif\n" +
            "	#else\n" +
            "		gl_FragColor.a = 1.0;\n" +
            "	#endif\n" +
            "\n" +
            "}";
    
        this.GetDefaultVertexShader = function()
        {
            return plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.vertexShader;
        };
        
        this.GetDefaultFragmentShader = function()
        {
            return plugins_quorum_Libraries_Game_Graphics_DefaultGLSLStrings_.fragmentShader;
        };
    }
}

// Code for the plugin-only class DefaultShaderConfig.
function plugins_quorum_Libraries_Game_Graphics_DefaultShaderConfig_(vertexShader, fragmentShader)
{
    this.vertexShader = vertexShader;
    this.fragmentShader = fragmentShader;
    this.numDirectionalLights = 2;
    this.numPointLights = 5;
    this.numSpotLights = 0;
    this.numBones = 12;
    this.ignoreUnimplemented = true;
    this.defaultCullFace = -1;
    this.defaultDepthFunc = -1;
}

// Code for the plugin-only class DefaultShader.
function plugins_quorum_Libraries_Game_Graphics_DefaultShader_()
{
    // Beginning of code for the BaseShader.
    
    var uniforms = [];
    var validators = [];
    var setters = [];
    var locations = [];
    var globalUniforms = [];
    var localUniforms = [];
    var attributes = {};
    
    var currentMesh;
    var combinedAttributes = new quorum_Libraries_Game_Graphics_Attributes_();
    var tempArray = [];
    
    /*
     *  The following properties are used as public fields:
     *      this.program (A ShaderProgram plugin object)
     *      this.context (A RenderContext plugin object)
     *      this.camera  (A Camera Quorum object)
     */
    
    this.Register = function(alias, validator, setter)
    {
        if (locations !== null && locations !== undefined)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Can't register a uniform after calling Initialize().");
            throw exceptionInstance_;
        }
        
        var existing = this.GetUniformID(alias);
        if (existing >= 0)
        {
            validators[existing] = validator;
            setters[existing] = setter;
            return existing;
        }
        
        uniforms.push(alias);
        validators.push(validator);
        setters.push(setter);
        return uniforms.length - 1;
    };
    
    this.GetUniformID = function(alias)
    {
        var n = uniforms.length;
        for (var i = 0; i < n; i++)
        {
            if (uniforms[i] === alias)
                return i;
        }
        
        return -1;
    };
    
    this.GetUniformAlias = function(id)
    {
        return uniforms[id];
    };
    
    this.Initialize = function(program, renderable)
    {
        if (locations !== null && locations !== undefined)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The shader has already been initialized.");
            throw exceptionInstance_;
        }
        
        if (!program.IsCompiled())
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text(program.GetLog());
            throw exceptionInstance_;
        }
        
        this.program = program;
        
        var n = uniforms.length;
        locations = [];
        
        for (var i = 0; i < n; i++)
        {
            var input = uniforms[i];
            var validator = validators[i];
            var setter = setters[i];
            
            if (validator !== null && validator !== undefined && !validator.Validate(this, i, renderable))
                locations[i] -1;
            else
            {
                locations[i] = program.FetchUniformLocation(input, false);
                
                if (locations[i] >= 0 && setter !== null && setter !== undefined)
                {
                    if (setter.IsGlobal(this, i))
                        globalUniforms.push(i);
                    else
                        localUniforms.push(i);
                }
            }
            if (locations[i] < 0)
            {
                validators[i] = null;
                setters[i] = null;
            }
        }
        
        if (renderable !== null && renderable !== undefined)
        {
            var attrs = renderable.meshPart.mesh.GetVertexAttributes();
            var c = attrs.GetSize();
            
            for (var i = 0; i < c; i++)
            {
                var attr = attrs.GetAttribute(i);
                var location = program.GetAttributeLocation(attr.alias);
                if (location >= 0)
                    attributes[attr.GetKey()] = location;
            }
        }
    };
    
    this.Begin = function(camera, context)
    {
        this.camera = camera;
        this.context = context;
        this.program.Begin();
        currentMesh = null;
        
        var u = 0;
        for (var i = 0; i < globalUniforms.length; ++i)
        {
            u = globalUniforms[i];
            if (setters[i] !== null && setters[i] !== undefined)
                setters[i].Set(this, u, null, null);
        }
    };
    
    this.GetAttributeLocations = function(attrs)
    {
        tempArray = [];
        var n = attrs.GetSize();
        for (var i = 0; i < n; i++)
        {
            var temp = attributes[attrs.GetAttribute(i).GetKey()];
            if (temp === null || temp === undefined)
                temp = -1;
            
            tempArray.push(temp);
        }
        
        return tempArray;
    };
    
    this.Render = function(renderable, cAttributes)
    {
        if (cAttributes === undefined)
        {
            if (renderable.worldTransform.Determinant3x3() === 0)
                return;
            
            combinedAttributes.Empty();
            
            if (renderable.environment !== null && renderable.environment !== undefined)
                combinedAttributes.Add(renderable.environment);
            if (renderable.material !== null && renderable.material !== undefined)
                combinedAttributes.Add(renderable.material);
            
            cAttributes = combinedAttributes;
        }
        
        var u;
        for (var i = 0; i < localUniforms.length; ++i)
        {
            u = localUniforms[i];
            if (setters[u] !== undefined && setters[u] !== null)
                setters[u].Set(this, u, renderable, cAttributes);
        }
        if (currentMesh !== renderable.meshPart.mesh)
        {
            if (currentMesh !== null)
                currentMesh.plugin_.Unbind(program, tempArray);
            
            currentMesh = renderable.meshPart.mesh;
            
            var temp = this.GetAttributeLocations(renderable.meshPart.Get_Libraries_Game_Graphics_ModelData_MeshPart__mesh_().GetVertexAttributes());
            currentMesh.plugin_.Bind(program, temp);
        }
        
        var meshPart = renderable.meshPart;
        meshPart.mesh.plugin_.Render(this.program, meshPart.primitiveType, meshPart.indexOffset, meshPart.verticesCount, false);
    };
    
    this.End = function()
    {
        if (currentMesh !== null && currentMesh !== undefined)
        {
            currentMesh.plugin_.Unbind(this.program, tempArray);
            currentMesh = null;
        }
        this.program.End();
    };
    
    this.Dispose = function()
    {
        this.program = null;
        uniforms = [];
        validators = [];
        setters = [];
        localUniforms = [];
        globalUniforms = [];
        locations = null;
    };
    
    this.Has = function(inputID)
    {
        return inputID >= 0 && inputID < locations.length && locations[inputID] >= 0;
    };
    
    this.Location = function(inputID)
    {
        if (inputID >= 0 && inputID < locations.length)
            return locations[inputID];
        return -1;
    };
    
    this.SetMatrix4 = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        var temp = this.ConvertMatrix4ToArray(value);
        
        this.program.SetUniformMatrix4AtLocation(locations[uniform], temp);
        return true;
    };
    
    this.SetMatrix3 = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        var temp = this.ConvertMatrix3ToArray(value);
        
        this.program.SetUniformMatrix3AtLocation(locations[uniform], temp);
        return true;
    };
    
    this.SetVector3 = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniformVector3AtLocation(locations[uniform], value.GetX(), value.GetY(), value.GetZ());
        return true;
    };
    
    this.SetVector2 = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniformVector2AtLocation(locations[uniform], value.GetX(), value.GetY());
        return true;
    };

    this.SetColor = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniformColorAtLocation(locations[uniform], value);
        return true;
    };
    
    this.Set1f = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform1fAtLocation(locations[uniform], value);
        return true;
    };
    
    this.Set2f = function(uniform, v1, v2)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform2fAtLocation(locations[uniform], v1, v2);
        return true;
    };
    
    this.Set3f = function(uniform, v1, v2, v3)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform3fAtLocation(v1, v2, v3);
        return true;
    };
    
    this.Set4f = function(uniform, v1, v2, v3, v4)
    {
        if (locations[uniform] < 0)
            return false;
        program.SetUniform4fAtLocation(locations[uniform], v1, v2);
        return true;
    };
    
    this.Set1i = function(uniform, value)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform1iAtLocation(locations[uniform], value);
        return true;
    };
    
    this.Set2i = function(uniform, v1, v2)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform2iAtLocation(locations[uniform], v1, v2);
        return true;
    };
    
    this.Set3i = function(uniform, v1, v2, v3)
    {
        if (locations[uniform] < 0)
            return false;
        
        program.SetUniform3iAtLocation(locations[uniform], v1, v2, v3);
        return true;
    };
    
    this.Set4i = function(uniform, v1, v2, v3, v4)
    {
        if (locations[uniform] < 0)
            return false;
        
        this.program.SetUniform4iAtLocation(locations[uniform], v1, v2, v3, v4);
        return true;
    };
    
    this.ConvertMatrix4ToArray = function(m)
    {
        var temp = [m.row0column0, m.row1column0, m.row2column0, m.row3column0,
                    m.row0column1, m.row1column1, m.row2column1, m.row3column1,
                    m.row0column2, m.row1column2, m.row2column2, m.row3column2,
                    m.row0column3, m.row1column3, m.row2column3, m.row3column3];
                
        return temp;
    };
    
    this.ConvertMatrix3ToArray = function(m)
    {
        var temp = [m.row0column0, m.row1column0, m.row2column0,
                    m.row0column1, m.row1column1, m.row2column1,
                    m.row0column2, m.row1column2, m.row2column2];
                
        return temp;
    };
    
    this.NewUniform = function(alias, materialMask, environmentMask, overallMask)
    {
        var newUniform = {};
        newUniform.alias = alias;
        newUniform.materialMask = materialMask;
        newUniform.environmentMask = environmentMask;
        newUniform.overallMask = overallMask;
        newUniform.Validate = function(shader, inputID, renderable)
        {
            var matFlags;
            if ((renderable !== null && renderable !== undefined) && (renderable.material !== null && renderable.material !== undefined))
                matFlags = renderable.material.GetMask();
            else
                matFlags = 0;
            
            var envFlags;
            if ((renderable !== null && renderable !== undefined) && (renderable.environment !== null && renderable.environment !== undefined))
                envFlags = renderable.material.GetMask();
            else
                envFlags = 0;
            
            return ((matFlags & materialMask) === materialMask) && ((envFlags & environmentMask) === environmentMask)
                && (((matFlags | envFlags) & overallMask) === overallMask);
        };
        
        return newUniform;
    };
    
    // Beginning of the code for the DefaultShader.
    if (!plugins_quorum_Libraries_Game_Graphics_DefaultShader_.initialized_plugins_quorum_Libraries_Game_Graphics_DefaultShader_)
    {
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.initialized_plugins_quorum_Libraries_Game_Graphics_DefaultShader_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.GetMatrixValue = function(matrix, index)
        {
            switch(index)
            {
                case 0:
                    return matrix.row0column0;
                case 1:
                    return matrix.row1column0;
                case 2:
                    return matrix.row2column0;
                case 3:
                    return matrix.row3column0;
                case 4:
                    return matrix.row0column1;
                case 5:
                    return matrix.row1column1;
                case 6:
                    return matrix.row2column1;
                case 7:
                    return matrix.row3column1; 
                case 8:
                    return matrix.row0column2;
                case 9:
                    return matrix.row1column2;
                case 10:
                    return matrix.row2column2;
                case 11:
                    return matrix.row3column2;
                case 12:
                    return matrix.row0column3;
                case 13:
                    return matrix.row1column3;
                case 14:
                    return matrix.row2column3;
                default:
                    return matrix.row3column3;
            }
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.blendingAttribute = new quorum_Libraries_Game_Graphics_BlendingAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute = new quorum_Libraries_Game_Graphics_TextureAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute = new quorum_Libraries_Game_Graphics_ColorAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.numberAttribute = new quorum_Libraries_Game_Graphics_NumberAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.integerAttribute = new quorum_Libraries_Game_Graphics_IntegerAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.depthTestAttribute = new quorum_Libraries_Game_Graphics_DepthTestAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.pointLightsAttribute = new quorum_Libraries_Game_Graphics_PointLightsAttribute_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.directionalLightsAttribute = new quorum_Libraries_Game_Graphics_DirectionalLightsAttribute_();

        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projTrans = this.NewUniform("u_projTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTrans = this.NewUniform("u_viewTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTrans = this.NewUniform("u_projViewTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraPosition = this.NewUniform("u_cameraPosition", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraDirection = this.NewUniform("u_cameraDirection", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraUp = this.NewUniform("u_cameraUp", 0, 0, 0);

        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTrans = this.NewUniform("u_worldTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTrans = this.NewUniform("u_viewWorldTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTrans = this.NewUniform("u_projViewWorldTrans", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrix = this.NewUniform("u_normalMatrix", 0, 0, 0);
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.bones = this.NewUniform("u_bones", 0, 0, 0);

        var tempAttribute = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.numberAttribute;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.shininess = this.NewUniform("u_shininess", 0, 0, tempAttribute.GetShininessValue());
        tempAttribute = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.blendingAttribute;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.opacity = this.NewUniform("u_opacity", 0, 0, tempAttribute.GetBlendedValue());

        tempAttribute = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseColor = this.NewUniform("u_diffuseColor", 0, 0, tempAttribute.GetDiffuseValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularColor = this.NewUniform("u_specularColor", 0, 0, tempAttribute.GetSpecularValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveColor = this.NewUniform("u_emissiveColor", 0, 0, tempAttribute.GetEmissiveValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionColor = this.NewUniform("u_reflectionColor", 0, 0, tempAttribute.GetReflectionValue());

        tempAttribute = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseTexture = this.NewUniform("u_diffuseTexture", 0, 0, tempAttribute.GetDiffuseValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseUVTransform = this.NewUniform("u_diffuseUVTransform", 0, 0, tempAttribute.GetDiffuseValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularTexture = this.NewUniform("u_specularTexture", 0, 0, tempAttribute.GetSpecularValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularUVTransform = this.NewUniform("u_specularUVTransform", 0, 0, tempAttribute.GetSpecularValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveTexture = this.NewUniform("u_emissiveTexture", 0, 0, tempAttribute.GetEmissiveValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveUVTransform = this.NewUniform("u_emissiveUVTransform", 0, 0, tempAttribute.GetEmissiveValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionTexture = this.NewUniform("u_reflectionTexture", 0, 0, tempAttribute.GetReflectionValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionUVTransform = this.NewUniform("u_reflectionUVTransform", 0, 0, tempAttribute.GetReflectionValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalTexture = this.NewUniform("u_normalTexture", 0, 0, tempAttribute.GetNormalValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalUVTransform = this.NewUniform("u_normalUVTransform", 0, 0, tempAttribute.GetNormalValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientTexture = this.NewUniform("u_ambientTexture", 0, 0, tempAttribute.GetAmbientValue());
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientUVTransform = this.NewUniform("u_ambientUVTransform", 0, 0, tempAttribute.GetAmbientValue());
        tempAttribute = null;

        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.alphaTest = this.NewUniform("u_alphaTest");
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientCube = this.NewUniform("u_ambientCubemap");
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.dirLights = this.NewUniform("u_dirLights");
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.pointLights = this.NewUniform("u_pointLights");
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.spotLights = this.NewUniform("u_spotLights");
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.environmentCubemap = this.NewUniform("u_environmentCubemap");
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.tmpAttributes = new quorum_Libraries_Game_Graphics_Attributes_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.usage = new quorum_Libraries_Game_Graphics_VertexAttributes_();
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.implementedFlags = 
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.blendingAttribute.GetBlendedValue() |
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetDiffuseValue() |
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetDiffuseValue() |
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetSpecularValue() |
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.numberAttribute.GetShininessValue();
    
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.optionalAttributes =
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.integerAttribute.GetCullFaceValue() |
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.depthTestAttribute.GetDepthTestValue();

        var isGlobal = function()
        {
            return true;
        };
        
        var isLocal = function()
        {
            return false;
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projTransSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetMatrix4(inputID, shader.camera.projection);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.matrix = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.matrix.Set(shader.camera.combined);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertViewZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.matrix);
            shader.SetMatrix4(inputID, plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewTransSetter.matrix);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.matrix = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.matrix.Set(shader.camera.combined);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertViewZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.matrix);
            shader.SetMatrix4(inputID, plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewTransSetter.matrix);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraPositionSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraPositionSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraPositionSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.Set4f(inputID, shader.camera.position.GetX(), shader.camera.position.GetY(),
                shader.camera.position.GetZ(), 1.1881 / shader.camera.far * shader.camera.far);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraDirectionSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraDirectionSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraDirectionSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetVector3(inputID, shader.camera.direction);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraUpSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraUpSetter.IsGlobal = isGlobal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.cameraUpSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetVector3(inputID, shader.camera.up);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.matrix = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.matrix.Set(renderable.worldTransform);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertPositionZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.matrix);
            shader.SetMatrix4(inputID, plugins_quorum_Libraries_Game_Graphics_DefaultShader_.worldTransSetter.matrix);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp2 = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp.SetMatrix4(shader.camera.view);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp2.SetMatrix4(shader.camera.worldTransform);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertViewZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertPositionZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp2);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp.Multiply(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.viewWorldTransSetter.temp2);
            shader.SetMatrix4(inputID, temp);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp2 = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp.SetMatrix4(shader.camera.combined);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp2.SetMatrix4(renderable.worldTransform);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertPositionZ(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp2);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp.Multiply(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp2);
            shader.SetMatrix4(inputID, plugins_quorum_Libraries_Game_Graphics_DefaultShader_.projViewWorldTransSetter.temp);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.temp = new quorum_Libraries_Compute_Matrix4_();
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.temp.SetMatrix4(renderable.worldTransform);
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.temp.Inverse();
            plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalMatrixSetter.temp.Transpose();
            shader.SetMatrix4(inputID, temp);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.NewBones = function(numBones)
        {
            var newBones = {};
            newBones.idtMatrix = new quorum_Libraries_Compute_Matrix4_();
            newBones.bones = [];
            newBones.bones.length = numBones * 16;
            newBones.IsGlobal = isLocal;
            newBones.Set = function(shader, inputID, renderable, combinedAttributes)
            {
                for (var i = 0; i < this.bones.length; i++)
                {
                    var idx = (i / 16) | 0;
                    if (renderable.bones === null || renderable.bones === undefined ||
                        idx >= renderable.bones.GetSize() || renderable.bones.Get(idx) === null
                        || renderable.bones.Get(idx) === undefined)
                    {
                        this.bones[i] = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.GetMatrixValue(this.idtMatrix, i % 16);
                    }
                    else
                    {
                        this.bones[i] = plugins_quorum_Libraries_Game_Graphics_DefaultShader_.GetMatrixValue(renderable.bones.Get(idx), i % 16);
                    }
                }
                shader.program.SetUniformMatrix4AtLocation(shader.Location(inputID), this.bones, 0, this.bones.length);
            };
            
            return newBones;
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.shininessSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.shininessSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.shininessSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.Set1f(inputID, combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.numberAttribute.GetShininessValue()).value);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseColorSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseColorSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseColorSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.Set1f(inputID, combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetDiffuseValue()).color);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetDiffuseValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.diffuseUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetDiffuseValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularColorSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularColorSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularColorSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetColor(inputID, combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute).color);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetSpecularValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.specularUVTransformSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetSpecularValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveColorSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveColorSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveColorSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetColor(inputID, combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetEmissiveValue()).color);
        };

        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetEmissiveValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.emissiveUVTransformSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetEmissiveValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionColorSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionColorSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionColorSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            shader.SetColor(inputID, combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetReflectionValue()).color);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetReflectionValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.reflectionUVTransformSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetReflectionValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetNormalValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.normalUVTransformSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetNormalValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientTextureSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientTextureSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientTextureSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var unit = shader.context.textureBinder.Bind(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.textureAttribute.GetAmbientValue()).descriptor);
            shader.Set1i(inputID, unit);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientUVTransformSetter = {};
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientUVTransformSetter.IsGlobal = isLocal;
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.ambientUVTransformSetter.Set = function(shader, inputID, renderable, combinedAttributes)
        {
            var ta = combinedAttributes.GetAttribute(textureAttribute.GetAmbientValue());
            shader.Set4f(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.NewACubemap = function(dirLightsOffset, pointLightsOffset)
        {
            var newCubemap = {};
            newCubemap.ones = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
            newCubemap.cacheAmbientCubemap = new quorum_Libraries_Game_Graphics_AmbientCubemap_();
            newCubemap.dirLightsOffset = dirLightsOffset;
            newCubemap.pointLightsOffset = pointLightsOffset;
            newCubemap.directionalLightsAttribute = new quorum_Libraries_Game_Graphics_DirectionalLightsAttribute_();
            newCubemap.pointLightsAttribute = new quorum_Libraries_Game_Graphics_PointLightsAttribute_();
            
            newCubemap.Set = function(shader, inputID, renderable, combinedAttributes)
            {
                if (renderable.environment === null || renderable.environment === undefined || !renderable.environment.ContainsLighting())
                    shader.program.SetUniformVector3(shader.Location(inputID), this.ones, 0, this.ones.length);
                else
                {
                    var tmpV1 = renderable.worldTransform.GetTranslation();
                    tmpV1.Scale(1, 1, -1);
                    if (combinedAttributes.HasAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetAmbientLightValue()))
                        this.cacheAmbientCubemap.SetColor(combinedAttributes.GetAttribute(plugins_quorum_Libraries_Game_Graphics_DefaultShader_.colorAttribute.GetAmbientLightValue()).color);
                    
                    if (combinedAttributes.HasAttribute(this.directionalLightsAttribute.GetDirectionalLightsValue()));
                    {
                        var lights = combinedAttributes.GetAttribute(this.directionalLightsAttribute.GetDirectionalLightsValue()).lights;
                        for (var i = this.dirLightsOffset; i < lights.GetSize(); i++)
                            this.cacheAmbientCubemap.Add(lights.Get(i).GetColor(), lights.Get(i).direction);
                    }
                    
                    if (combinedAttributes.HasAttribute(this.pointLightsAttribute.GetPointLightsValue()))
                    {
                        var lights = combinedAttributes.GetAttribute(this.pointLightsAttribute.GetPointLightsValue()).lights;
                        for (var i = this.pointLightsOffset; i < lights.GetSize(); i++)
                            this.cacheAmbientCubemap.Add(lights.Get(i).GetColor(), lights.Get(i).GetGlobalPosition(),
                                tmpV1, lights.Get(i).intensity);
                    }
                    
                    this.cacheAmbientCubemap.Clamp();
                    var temp = [];
                    for (var i = 0; i < this.cacheAmbientCubemap.data.GetSize(); i++)
                        temp[i] = this.cacheAmbientCubemap.GetDataAtIndex(i);
                    
                    shader.program.SetUniformVector3AtLocation(shader.Location(inputID), temp, 0, temp.length);
                }
            };
            
            return newCubemap;
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertViewZ = function(matrix)
        {
            matrix.row0column0 = -matrix.row0column0;
            matrix.row0column1 = -matrix.row0column1;
            matrix.row0column3 = -matrix.row0column3;
            
            matrix.row1column2 = -matrix.row1column2;
            matrix.row2column2 = -matrix.row2column2;
            matrix.row3column2 = -matrix.row3column2;
        };
        
        plugins_quorum_Libraries_Game_Graphics_DefaultShader_.InvertPositionZ = function(matrix)
        {
            matrix.row2column3 = -matrix.row2column3;
        };
    }
}