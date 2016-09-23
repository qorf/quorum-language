function plugins_quorum_Libraries_Game_Graphics_ShaderProgram_(vertexShader, fragmentShader) 
{
    
    this.CompileShaders = function(vertexShader, fragmentShader) 
    {
        var gl = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics.gl;
        
        this.vertexShaderHandle = this.LoadShader(gl.VERTEX_SHADER, vertexShader);
        this.fragmentShaderHandle = this.LoadShader(gl.FRAGMENT_SHADER, fragmentShader);

        if (vertexShaderHandle === -1 || fragmentShaderHandle === -1) 
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
        if (shader === 0)
        {
            return -1;
        }
        
        graphics.glShaderSource(shader, source);
        graphics.glCompileShader(shader);
        var compiled = graphics.getShaderiv(shader, graphics.gl.COMPILE_STATUS);
        
        if (compiled === 0)
        {
            var infoLogLength = graphics.getProgramiv(program, graphics.gl.INFO_LOG_LENGTH);
            if (infoLogLength > 1)
            {
                var infoLog = graphics.glGetShaderInfoLog(shader);
                this.log = this.log + infoLog;
                this.log = this.log + "Version is: " + graphics.glGetString(gl.gl.VERSION);
            }
            return -1;
        }
        
        return shader;
    };
    
    this.LinkProgram = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var program = graphics.glCreateProgram();
        if (program === 0)
            return -1;
        
        graphics.glAttachShader(program, vertexShaderHandle);
        graphics.glAttachShader(program, fragmentShaderHandle);
        graphics.glLinkProgram(program);
        
//        ByteBuffer tmp = ByteBuffer.allocateDirect(4);
//        tmp.order(ByteOrder.nativeOrder());
//        IntBuffer intbuf = tmp.asIntBuffer();

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
    
    this.FetchAttributes = function()
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        var attributeCount = graphics.glGetProgramiv(program, graphics.gl.ACTIVE_ATTRIBUTES);
        var info;
        var location;
        
        this.attributeNames = [];
        
        for (i = 0; i < attributeCount; i++)
        {
            info = graphics.glGetActiveAttrib(program, i);
            location = graphics.glGetAttribLocation(program, info.name);
            //attributes.put(info.name, location);
            //attributeTypes.put(info.name, info.type);
            //attributeSizes.put(info.name, info.size);
            attributeNames[i] = info.name;
        }
    };
    
    
    var exceptionInstance_;
    
    if (vertexShader === null || vertexShader === undefined)
    {
        exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("A ShaderProgram can't be initialized with an undefined vertex shader!");
        throw exceptionInstance_;
    }
    if (fragmentShader === null || fragmentShader === undefined)
    {
        exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("A ShaderProgram can't be initialized with an undefined fragment shader!");
        throw exceptionInstance_;
    }
    
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().POSITION_ATTRIBUTE = "a_position";
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().NORMAL_ATTRIBUTE = "a_normal";
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().COLOR_ATTRIBUTE = "a_color";
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().TEXCOORD_ATTRIBUTE = "a_texCoord";
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().TANGENT_ATTRIBUTE = "a_tangent";
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().BINORMAL_ATTRIBUTE = "a_binormal";
    
    plugins_quorum_Libraries_Game_Graphics_ShaderProgram_().pedantic = true;
    
    //private final static ObjectMap<quorum.Libraries.Game.Application_, Array<ShaderProgram>> shaders = new ObjectMap<quorum.Libraries.Game.Application_, Array<ShaderProgram>>();
    
    this.isCompiled = false;
    
    this.log = "";
    
    //    private final ObjectIntMap<String> uniforms = new ObjectIntMap<String>();
//    private final ObjectIntMap<String> uniformTypes = new ObjectIntMap<String>();
//    private final ObjectIntMap<String> uniformSizes = new ObjectIntMap<String>();
    
    this.uniformNames = null;
    
//    private final ObjectIntMap<String> attributes = new ObjectIntMap<String>();
//    private final ObjectIntMap<String> attributeTypes = new ObjectIntMap<String>();
//    private final ObjectIntMap<String> attributeSizes = new ObjectIntMap<String>();

    this.attributeNames = null;
    
//    private final FloatBuffer matrix = BufferUtils.newFloatBuffer(16);

    // Whether this shader was invalidated.
    this.invalidated = false;

    this.referenceCount = 0;
    
//    final static IntBuffer intbuf = BufferUtils.newIntBuffer(1);
    
//    IntBuffer type = BufferUtils.newIntBuffer(1);

    // Source code for the vertex shader.
    this.vertexShaderSource = vertexShader;

    // Source code for the fragment shader.
    this.fragmentShaderSource = fragmentShader;

    this.CompileShaders(vertexShader, fragmentShader);
    if (this.IsCompiled())
    {
        this.FetchAttributes();
        this.FetchUniforms();
        //AddManagedShader(GameStateManager.GetApplication(), this);
    }
    else
    {
        exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Failed to compile shader: " + this.GetLog());
        throw exceptionInstance_;
    }
    
}