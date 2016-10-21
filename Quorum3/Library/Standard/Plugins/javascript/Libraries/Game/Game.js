function plugins_quorum_Libraries_Game_Game_() {
    this.GetSecondsBetweenFrames = function() {

    };
    this.SelectApplicationTypeNative = function() {
        return 4;
    };
}

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
        if (linked === 0)
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
    
    this.SetUniformVector2FromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = FetchUniformLocation(name);
        graphics.glUniform2fv(location, value);
    };
    
    this.SetUniformVector2AtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform2fv(location, value);
    };
    
    this.SetUniformVector3FromName = function(name, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        var location = FetchUniformLocation(name);
        graphics.glUniform3fv(location, value);
    };
    
    this.SetUniformVector3AtLocation = function(location, value)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        this.CheckManaged();
        graphics.glUniform3fv(location, value);
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
        return attributes[name] || -1;
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
        return uniforms[name] || -1;
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
