function plugins_quorum_Libraries_Game_Graphics_ImageSheet_()
{
    if (!plugins_quorum_Libraries_Game_Graphics_ImageSheet_.initialized_plugins_quorum_Libraries_Game_Graphics_ImageSheet_)
    {
        plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple = [];
        
        plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadValue = function(reader)
        {
            var line = reader.ReadLine();
            var colon = line.indexOf(':');
            if (colon == -1)
            {
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("There was an invalid line in an ImageSheet resource: " + line);
                throw exceptionInstance_;
            }
            return line.substring(colon + 1).trim();
        };
                
        plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple = function(reader)
        {
            var line = reader.ReadLine();
            var colon = line.indexOf(':');
            if (colon == -1)
            {
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("There was an invalid line in an ImageSheet resource: " + line);
                throw exceptionInstance_;
            }
            var i = 0;
            var lastMatch = colon + 1;
            for (i = 0; i < 3; i++)
            {
                var comma = line.indexOf(',', lastMatch);
                if (comma == -1)
                    break;
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[i] = line.substring(lastMatch, comma).trim();
                lastMatch = comma + 1;
            }
            plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[i] = line.substring(lastMatch).trim();
            return i + 1;
        };
        
        plugins_quorum_Libraries_Game_Graphics_ImageSheet_.initialized_plugins_quorum_Libraries_Game_Graphics_ImageSheet_ = true;
    }
    
    var textures = [];
    var regions = [];
    
    this.NewImageSheetData = function(packFile, imagesDirectory, flip)
    {
        var sheetData = {};
        sheetData.sheets = [];
        sheetData.regions = [];
        
        var CLAMP_TO_EDGE = 33071;
        var REPEAT = 10497;
        
        var reader = new quorum_Libraries_System_FileReader_();
        reader.OpenForRead$quorum_Libraries_System_File(packFile);
        
        var sheet = null;
        while (true)
        {
            if (reader.IsAtEndOfFile())
                break;
            var line = reader.ReadLine();
            if (line.trim().length === 0)
                sheet = null;
            else if (sheet === null)
            {
                var newSheet = new quorum_Libraries_System_File_();
                newSheet.SetWorkingDirectory$quorum_text(imagesDirectory);
                newSheet.SetPath$quorum_text(line);
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                
                var width = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                var height = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                
                var formatString = plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0].toLowerCase();
                var format;
                
                switch(formatString)
                {
                    case("intensity"):
                        format = 1;
                        break;
                    case("luminancealpha"):
                        format = 2;
                        break;
                    case("rgb888"):
                        format = 3;
                        break;
                    case("rgba8888"):
                        format = 4;
                        break;
                    case("rgb565"):
                        format = 5;
                        break;
                    case("rgba4444"):
                        format = 6;
                    default:
                        var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                        exceptionInstance_.SetErrorMessage$quorum_text("I could not recognize the format of this ImageSheet: " + plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                        throw exceptionInstance_;
                }
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                
                var minString = plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0].toLowerCase();
                var maxString = plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1].toLowerCase();
                var minFilter;
                var maxFilter;
                
                switch(minString)
                {
                    case("nearest"):
                        minFilter = 9728;
                        break;
                    case("linear"):
                        minFilter = 9729;
                        break;
                    case("mipmap"):
                        minFilter = 9987;
                        break;
                    case("mipmapnearestnearest"):
                        minFilter = 9984;
                        break;
                    case("mipmaplinearnearest"):
                        minFilter = 9985;
                        break;
                    case("mipmapnearestlinear"):
                        minFilter = 9986;
                        break;
                    case("mipmaplinearlinear"):
                        minFilter = 9987;
                        break;
                    default:
                        var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                        exceptionInstance_.SetErrorMessage$quorum_text("I could not recognize the filter in this ImageSheet: " + plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                        throw exceptionInstance_;
                }

                switch(maxString)
                {
                    case("nearest"):
                        maxFilter = 9728;
                        break;
                    case("linear"):
                        maxFilter = 9729;
                        break;
                    case("mipmap"):
                        maxFilter = 9987;
                        break;
                    case("mipmapnearestnearest"):
                        maxFilter = 9984;
                        break;
                    case("mipmaplinearnearest"):
                        maxFilter = 9985;
                        break;
                    case("mipmapnearestlinear"):
                        maxFilter = 9986;
                        break;
                    case("mipmaplinearlinear"):
                        maxFilter = 9987;
                        break;
                    default:
                        var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                        exceptionInstance_.SetErrorMessage$quorum_text("I could not recognize the filter in this ImageSheet: " + plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                        throw exceptionInstance_;
                }
                
                var isMipMap = false;
                
                if (!(minFilter === 9728 || minFilter === 9729))
                    isMipMap = true;
                
                var direction = plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadValue(reader);
                
                var repeatX = CLAMP_TO_EDGE;
                var repeatY = CLAMP_TO_EDGE;
                if (direction === "x")
                    repeatX = REPEAT;
                else if (direction === "y")
                    repeatY = REPEAT;
                else if (direction === "xy")
                {
                    repeatX = REPEAT;
                    repeatY = REPEAT;
                }
                
                // newSheet, width, height, isMipMap, format, minFilter, maxFilter, repeatX, repeatY
                sheet = {};
                sheet.textureFile = newSheet;
                sheet.width = width;
                sheet.height = height;
                sheet.useMipMaps = isMipMap;
                sheet.format = format;
                sheet.minFilter = minFilter;
                sheet.magFilter = maxFilter;
                sheet.uWrap = repeatX;
                sheet.vWrap = repeatY;
                sheetData.sheets.push(sheet);
            }
            else
            {
                var rotate = (plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadValue(reader) === 'true');
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                var left = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                var top = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                var width = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                var height = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                
                var region = {};
                region.sheet = sheet;
                region.left = left;
                region.top = top;
                region.width = width;
                region.height = height;
                region.name = line;
                region.rotate = rotate;
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                
                region.originalWidth = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                region.originalHeight = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                
                plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadTuple(reader);
                region.offsetX = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[0]);
                region.offsetY = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.tuple[1]);
                
                region.index = parseInt(plugins_quorum_Libraries_Game_Graphics_ImageSheet_.ReadValue(reader));
                
                if (flip)
                    region.flip = true;
                else
                    region.flip = false;
                
                sheetData.regions.push(region);
                return sheetData;
            }
        }
        
        reader.Close();
        
        var compareRegions = function(region1, region2)
        {
            var a = region1.index === -1 ? Number.MAX_VALUE : region1.index;
            var b = region2.index === -1 ? Number.MAX_VALUE : region2.index;
            return a - b;
        };
        
        sheetData.regions.sort(compareRegions);
    };
    
    this.LoadNative$quorum_Libraries_System_File = function(quorumFile)
    {
        var parent = quorumFile.GetParentDirectory();
        this.LoadNative$quorum_Libraries_System_File$quorum_Libraries_System_File(quorumFile, parent);
    };
    
    this.LoadNative$quorum_Libraries_System_File$quorum_Libraries_System_File = function(packFile, imagesDirectory)
    {
        this.LoadNative$quorum_Libraries_System_File$quorum_Libraries_System_File$quorum_boolean(packFile, imagesDirectory, false);
    };
    
    this.LoadNative$quorum_Libraries_System_File$quorum_Libraries_System_File$quorum_boolean = function(packFile, imagesDirectory, flip)
    {
        var data = this.NewImageSheetData(packFile, imagesDirectory, flip);
        this.LoadData(data);
    };
    
    this.LoadData = function(data)
    {
        for (var i = 0; i < data.sheets.length; i++)
        {
            var sheet = data.sheets[i];
            var texture = null;
            
            var minFilter = new quorum_Libraries_Game_Graphics_TextureFilter_();
            var magFilter = new quorum_Libraries_Game_Graphics_TextureFilter_();
            minFilter.ConstructTextureFilter$quorum_integer(sheet.minFilter);
            magFilter.ConstructTextureFilter$quorum_integer(sheet.magFilter);
            
            var uWrap = new quorum_Libraries_Game_Graphics_TextureWrap_();
            var vWrap = new quorum_Libraries_Game_Graphics_TextureWrap_();
            uWrap.ConstructTextureWrap$quorum_integer(sheet.uWrap);
            vWrap.ConstructTextureWrap$quorum_integer(sheet.vWrap);
            
            if (sheet.texture === undefined || sheet.texture === null)
            {
                texture = new quorum_Libraries_Game_Graphics_Texture_();
                var quorumFile = new quorum_Libraries_System_File_();
                quorumFile.SetWorkingDirectory$quorum_text(sheet.textureFile.GetWorkingDirectory());
                quorumFile.SetPath$quorum_text(sheet.textureFile.GetPath());
                var format = new quorum_Libraries_Game_Graphics_Format_();
                format.SetValue$quorum_integer(sheet.format);
                texture.LoadFromFile$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_Format$quorum_boolean(quorumFile, format, sheet.useMipMaps);
                sheet.texture = texture;
            }
            else
            {
                texture = sheet.texture;
            }
            
            texture.SetFilter$quorum_Libraries_Game_Graphics_TextureFilter$quorum_Libraries_Game_Graphics_TextureFilter(minFilter, magFilter);
            texture.SetWrap$quorum_Libraries_Game_Graphics_TextureWrap$quorum_Libraries_Game_Graphics_TextureWrap(uWrap, vWrap);
            
            textures.push(texture);
        }
        
        regions = data.regions;
    };
    
    this.Dispose = function()
    {
        for (var i = 0; i < textures.length; i++)
        {
            var texture = textures[i];
            texture.Dispose();
        }
        textures = [];
    };
    
    this.GetDrawableNative$quorum_text = function(name)
    {
        for (var i = 0; i < regions.length; i++)
        {
            if (regions[i].name === name)
                return this.NewDrawable(regions[i]);
        }
        return null;
    };
    
    this.GetDrawableNative$quorum_text$quorum_integer = function(name, index)
    {
        for (var i = 0; i < regions.length; i++)
        {
            var region = regions[i];
            if (!region.name === name)
                continue;
            if (region.index !== index)
                continue;
            return this.NewDrawable(regions[i]);
        }
        return null;
    };
    
    this.NewDrawable = function(region)
    {
        var drawable = new quorum_Libraries_Game_Graphics_Drawable_();
        drawable.Load$quorum_Libraries_Game_Graphics_Texture$quorum_integer$quorum_integer$quorum_integer$quorum_integer(
                region.sheet.texture, region.left, region.top, region.width, region.height);
        return drawable;
    };
    
}