function plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_(quorumFont) {
	if (!plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_) {
		// Static initializer for the class.

		//Run main() which initializes FreeType
		var main_return = Module.cwrap('main', 'number');
		main_return();

		// Plug-In loaded = true
		plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.initialized_plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_ = true;
	}

	var color = null;
	this.me_ = quorumFont;

	this.LoadFontNative$quorum_Libraries_System_File = function (file) {
		// NYI
	};

	this.LoadFontNative$quorum_text = function (fontName) {

		var loadFontNativeC = Module.cwrap('LoadFontC', 'number', ['string']);
		var loadFontNativeResult = loadFontNativeC("fonts/" + fontName + ".ttf");
		if (loadFontNativeResult == 1) {
			// error loading font
			console.log('Error loading font:' + fontName);
		}

		this.SetSizeNative$quorum_integer(this.me_.GetSize());
		if (this.me_.GetAngle() != 0)
			this.SetAngleNative$quorum_number(this.me_.GetAngle());
	};

	this.IsFontAvailable$quorum_text = function (fontName) {
		// NYI
		return true;
	};

	this.SetSizeNative$quorum_integer = function (size) {
		var SetSizeC = Module.cwrap('SetSizeC', null, ['number']);
		SetSizeC(size);
	};

	this.SetAngleNative$quorum_number = function (angle) {
		var SetAngleC = Module.cwrap('SetAngleC', null, ['number']);
		SetAngleC(angle / 180 * Math.PI);
	};

	this.SetColorNative$quorum_Libraries_Game_Graphics_Color = function (newColor) {
		color = newColor;
	};

	this.GetColor = function () {
		return color;
	};

	this.DisposeNative = function () {
		var DisposeC = Module.cwrap('DisposeC', null, null);
		DisposeC();
	};

	this.GetGlyphNative$quorum_text = function (character) {
		// Create glyph
		var glyph = new quorum_Libraries_Game_Graphics_Glyph_();
		this.SetSizeNative$quorum_integer(this.me_.GetSize());
		// Load character into FreeType
		var loadChar = Module.cwrap('loadCharC', 'number', ['string']);
		var loadCharResult = loadChar(character);
		if (loadCharResult == 1) {
			// error loading glyph
			console.log('Error loading glyph.');
		}

		// Create data array for glyph metrics (7 * 4 bytes = 28 bytes)
		var offset = Module._malloc(28);

		// Setup call to getBitmapData from WASM module
		var getBitmapData = Module.cwrap('getBitmapDataC', 'number', ['number']);

		// Pass array pointer to getBitmapData
		getBitmapData(offset);

		// Create array and copy glyph metrics into it
		var bitmapData = [];
		bitmapData[0] = Module.getValue(offset, 'i32'); //glyph->bitmap_left;
		bitmapData[1] = Module.getValue(offset + 4, 'i32'); //glyph->bitmap_top;
		bitmapData[2] = Module.getValue(offset + 8, 'i32'); //glyph->bitmap.rows;
		bitmapData[3] = Module.getValue(offset + 12, 'i32'); //glyph->bitmap.width;
		bitmapData[4] = Module.getValue(offset + 16, 'i32'); //glyph->advance.x;
		bitmapData[5] = Module.getValue(offset + 20, 'i32'); //glyph->advance.y;
		bitmapData[6] = Module.getValue(offset + 24, 'i32'); //glyph->bitmap.pitch;

		// Free allocated memory
		Module._free(offset);

		// Calculate size of byte array for bitmap pixel buffer
		// size = |pitch|*rows
		var size = bitmapData[6];
		if (size < 0)
			size = -size;
		size *= bitmapData[2];

		// Allocate memory on Emscripten heap
		let num_bytes = size;
		let dataPtr = Module._malloc(num_bytes);
		let dataHeap = new Uint8Array(Module.HEAPU8.buffer, dataPtr, num_bytes);

		// Call WASM function
		var getBitmap = Module.cwrap('getBitmapC', null, ['number', 'number']);
		getBitmap(dataHeap.byteOffset, num_bytes);
		dataHeap = new Uint8Array(Module.HEAPU8.buffer, dataPtr, num_bytes);
		// Get data from Emscripten heap
		let bitmapBuffer = new Uint8Array(dataHeap.buffer, dataHeap.byteOffset, num_bytes);

		// Output buffer to log
		var i;
		var output = "";
		for (i = 0; i < size; i++) {
			output = output + bitmapBuffer[i].toString(10) + " ";
		}

		// Free allocated memory
		Module._free(dataHeap.byteOffset);

		if (character != ' ') {
			// Create Alpha pixel map
			var pixmap = new quorum_Libraries_Game_Graphics_PixelMap_();
			var map = pixmap.plugin_;
			var format = new quorum_Libraries_Game_Graphics_Format_();
			format.SetValue$quorum_integer(format.Get_Libraries_Game_Graphics_Format__ALPHA_());
			map.LoadFromFontBitmap(bitmapBuffer, bitmapData[3], bitmapData[2], format);

			// Create File texture data
			var texData = new quorum_Libraries_Game_Graphics_FileTextureData_();
			texData.InitializeFileTextureData$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_PixelMap$quorum_Libraries_Game_Graphics_Format$quorum_boolean(null, pixmap, null, false);
			//texData.SetDisposalState(false);

			// Create texture
			var texture = new quorum_Libraries_Game_Graphics_Texture_();
			texture.LoadFromTextureData$quorum_Libraries_Game_Graphics_TextureData(texData);

			// Create color
			var c = new quorum_Libraries_Game_Graphics_Color_();
			c.SetColor$quorum_number$quorum_number$quorum_number$quorum_number(color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
			texture.plugin_.fontColor = c;

			// Create texture region
			var region = new quorum_Libraries_Game_Graphics_TextureRegion_();
			region.LoadTextureRegion$quorum_Libraries_Game_Graphics_Texture(texture);

			glyph.texture = region;
		} else {
			glyph.texture = null;
		}

		//glyph.Set_Libraries_Game_Graphics_Glyph__texture_(plugins_quorum_Libraries_Game_Graphics_Fonts_FreeTypeStrategy_.testDrawable);
		glyph.Set_Libraries_Game_Graphics_Glyph__horizontalAdvance_(bitmapData[4] >> 6);
		glyph.Set_Libraries_Game_Graphics_Glyph__verticalAdvance_(bitmapData[5] >> 6);
		glyph.Set_Libraries_Game_Graphics_Glyph__lengthToGlyph_(bitmapData[0]);
		glyph.Set_Libraries_Game_Graphics_Glyph__heightFromBaseLine_(bitmapData[1]);


		return glyph;
	};

	this.GetKerning$quorum_text$quorum_text = function (currentCharacter, nextCharacter) {
		var GetKerningC = Module.cwrap('GetKerningC', 'number', ['string', 'string']);
		return GetKerningC(currentCharacter, nextCharacter);
	};

	this.FinishedLoading = function () {
		return true;
	};

	this.LoadImageSheet$Libraries_Game_Graphics_Fonts_FontImageSheet = function (imageSheet) {
		// NYI
	};

	this.LoadImageSheet$quorum_Libraries_Game_Graphics_Fonts_FontImageSheet = function (imageSheet) {
		// Image Sheet Row
		// The end of the row is the last image that appears on this row, or
		// 256 if this is the last row.
		// The height is the height of the tallest image in this row.
		class ImageSheetRow {
			constructor(height, endOfRow) {
				this.height = height;
				this.endOfRow = endOfRow;
			}
		}
		// Texture Region Data
		class TextureRegionData {
			constructor(x, y, width, height) {
				this.x = x;
				this.y = y;
				this.width = width;
				this.height = height;
			}
		}

		var texture = imageSheet.Get_Libraries_Game_Graphics_Fonts_FontImageSheet__imageSheet_();
		var table = new quorum_Libraries_Containers_Array_();
		table = imageSheet.Get_Libraries_Game_Graphics_Fonts_FontImageSheet__glyphTable_();

		var currentData = [];
		var pixels = [];
		var glyphs = [];
		var regionData = [];
		var rows = []; // Should change to queue since rows.shift() is O(n)

		// How much padding there should be between symbols on the ImageSheet.
		var padding = 1;

		var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
		var maxSize = graphics.glGetIntegerv(graphics.gl.MAX_TEXTURE_SIZE);

		var rowHeight = padding;
		var rowWidth = padding;
		var totalHeight = rowHeight;
		var totalWidth = rowWidth;

		// Load the ASCII characters.
		for (var current = 0; current < 256; current++) {
			/* The data parameter will contain the following information after a call to LoadBitmap:
				[0] : The distance from the cursor to the left side of the bitmap.
				[1] : The distance from the cursor to the top side of the bitmap.
				[2] : The number of rows in a bitmap.
				[3] : The number of pixels in each row of the bitmap.
				[4] : The distance to advance the cursor's X coordinate.
				[5] : The distance to advance the cursor's Y coordinate.

				LoadBitmap will also return a bitmap as a ByteBuffer so it can be drawn.
			*/
			this.SetSizeNative$quorum_integer(this.me_.GetSize());
			// Load character into FreeType
			var loadChar = Module.cwrap('loadCharC', 'number', ['string']);
			var loadCharResult = loadChar(String.fromCharCode(current));
			if (loadCharResult == 1) {
				// error loading glyph
				console.log('Error loading glyph.');
			}

			// Create data array for glyph metrics (7 * 4 bytes = 28 bytes)
			var offset = Module._malloc(28);

			// Setup call to getBitmapData from WASM module
			var getBitmapData = Module.cwrap('getBitmapDataC', 'number', ['number']);

			// Pass array pointer to getBitmapData
			getBitmapData(offset);

			// Create array and copy glyph metrics into it
			currentData[0] = Module.getValue(offset, 'i32'); //glyph->bitmap_left;
			currentData[1] = Module.getValue(offset + 4, 'i32'); //glyph->bitmap_top;
			currentData[2] = Module.getValue(offset + 8, 'i32'); //glyph->bitmap.rows;
			currentData[3] = Module.getValue(offset + 12, 'i32'); //glyph->bitmap.width;
			currentData[4] = Module.getValue(offset + 16, 'i32'); //glyph->advance.x;
			currentData[5] = Module.getValue(offset + 20, 'i32'); //glyph->advance.y;
			currentData[6] = Module.getValue(offset + 24, 'i32'); //glyph->bitmap.pitch;

			// Free allocated memory
			Module._free(offset);

			// Calculate size of byte array for bitmap pixel buffer
			// size = |pitch|*rows
			var size = currentData[6];
			if (size < 0)
				size = -size;
			size *= currentData[2];

			// Allocate memory on Emscripten heap
			let num_bytes = size;
			let dataPtr = Module._malloc(num_bytes);
			let dataHeap = new Uint8Array(Module.HEAPU8.buffer, dataPtr, num_bytes);

			// Call WASM function
			var getBitmap = Module.cwrap('getBitmapC', null, ['number', 'number']);
			getBitmap(dataHeap.byteOffset, num_bytes);
			dataHeap = new Uint8Array(Module.HEAPU8.buffer, dataPtr, num_bytes);
			// Get data from Emscripten heap
			let value = new Uint8Array(dataHeap.buffer, dataHeap.byteOffset, num_bytes);

			// Free allocated memory
			Module._free(dataHeap.byteOffset);

			var currentWidth = currentData[3];
			var currentHeight = currentData[2];

			if (currentWidth == 0 || currentHeight == 0) {
				// We use a single empty pixel to represent symbols that don't
				// have a visual representation, e.g. space or new line.
				currentWidth = 1;
				currentHeight = 1;
				var emptyPixel = new Uint8Array(1); // Initialized to zero
				pixels.push(emptyPixel.slice(0));
			}
			else {
				pixels.push(value.slice(0));
			}

			var glyph = new quorum_Libraries_Game_Graphics_Glyph_();
			glyph.Set_Libraries_Game_Graphics_Glyph__horizontalAdvance_(currentData[4] >> 6);
			glyph.Set_Libraries_Game_Graphics_Glyph__verticalAdvance_(currentData[5] >> 6);
			glyph.Set_Libraries_Game_Graphics_Glyph__lengthToGlyph_(currentData[0]);
			glyph.Set_Libraries_Game_Graphics_Glyph__heightFromBaseLine_(currentData[1]);
			glyphs[current] = glyph;

			table.Add$quorum_Libraries_Language_Object(glyph);

			var x = rowWidth;
			rowWidth += currentWidth + padding;

			if (rowWidth > totalWidth)
				totalWidth = rowWidth;

			if (rowWidth > maxSize) {
				var newRow = new ImageSheetRow(rowHeight, current - 1);
				rows.push(newRow);

				totalHeight += rowHeight;
				rowWidth = currentWidth;
				x = padding;
				rowHeight = padding;
			}

			if (currentHeight + padding > rowHeight) {
				rowHeight = currentHeight + padding;
				if (totalHeight + rowHeight > maxSize) {
					// We've exceeded the maximum size we can place in a Texture.
					// Return false to indicate failure.
					return false;
				}
			}

			regionData[current] = new TextureRegionData(x, totalHeight, currentWidth, currentHeight);
		}

		totalHeight += rowHeight + padding;

		// Add the current and final row to the queue.
		rows.push(new ImageSheetRow(rowHeight, 255));

		// Assemble the ByteBuffers into a single ByteBuffer for use by PixelMap.
		var destination = new Uint8Array(totalWidth * totalHeight);
		var currentRow = rows.shift(); // O(n)
		var currentSource = null;
		var currentRegion = null;
		var startOfRow = 0;
		var currentImage = 0;
		var destinationIndex = 0;

		for (var y = 0, subY = -padding; y < totalHeight; y++, subY++) {
			// The subY is the current row of pixels being rendered for the
			// current row of images. When it matches the height of the current
			// row of images, it's time to begin the next row.
			if (subY == currentRow.height) {
				startOfRow = currentRow.endOfRow + 1;

				if (rows.length > 0)
					currentRow = rows.shift(); // O(n)
				else
					currentRow = null;

				subY = -padding;
			}

			if (currentRow != null) {
				currentImage = startOfRow;
				currentSource = pixels[startOfRow];
				currentRegion = regionData[startOfRow];
			}

			for (var x = 0, subX = -padding; x < totalWidth; x++, subX++, destinationIndex++) {
				if (currentRow == null || currentImage > currentRow.endOfRow || subX < 0) {
					destination[destinationIndex] = 0;
					continue;
				}

				// The subX is the current pixel x position being rendered in
				// the current image. When it matches the width of the current
				// image, it's time to begin the next one.
				if (subX >= currentRegion.width) {
					currentImage++;
					if (currentImage <= currentRow.endOfRow) {
						currentSource = pixels[currentImage];
						currentRegion = regionData[currentImage];
						subX = -padding;
					}
					else {
						continue;
					}
				}

				if (subY >= currentRegion.height || subY < 0 || subX < 0) {
					destination[destinationIndex] = 0;
				}
				else {
					destination[destinationIndex] = currentSource[currentRegion.width * subY + subX];
				}
			}
		}

		// Create Alpha pixel map
		var pixmap = new quorum_Libraries_Game_Graphics_PixelMap_();
		var map = pixmap.plugin_;
		var format = new quorum_Libraries_Game_Graphics_Format_();
		format.SetValue$quorum_integer(format.Get_Libraries_Game_Graphics_Format__ALPHA_());
		map.LoadFromFontBitmap(destination, totalWidth, totalHeight, format);

		// Create File texture data
		var texData = new quorum_Libraries_Game_Graphics_FileTextureData_();
		texData.InitializeFileTextureData$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_PixelMap$quorum_Libraries_Game_Graphics_Format$quorum_boolean(null, pixmap, null, false);
		//texData.SetDisposalState(false);

		// Create texture
		var texture = new quorum_Libraries_Game_Graphics_Texture_();
		texture.LoadFromTextureData$quorum_Libraries_Game_Graphics_TextureData(texData);

		// Create color
		var c = new quorum_Libraries_Game_Graphics_Color_();
		c.SetColor$quorum_number$quorum_number$quorum_number$quorum_number(color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
		texture.plugin_.fontColor = c;

		for (var i = 0; i < 256; i++) {
			var data = regionData[i];
			var glyph = glyphs[i];
			var region = new quorum_Libraries_Game_Graphics_TextureRegion_();
			region.LoadTextureRegion$quorum_Libraries_Game_Graphics_Texture$quorum_integer$quorum_integer$quorum_integer$quorum_integer(texture, data.x, data.y, data.width, data.height);

			glyph.texture = region;
		}

		// True indicates successful loading of the ImageSheet and caching of Glyph data.
		return true;
	};

	this.SystemGetHeight = function () {
		return this.GetLineHeight();
	};

	this.SystemGetMaximumAscent = function () {
		return this.GetMaximumAscent();
	};

	this.SystemGetMaximumDescent = function () {
		return this.GetMaximumDescent();
	};

	this.SystemGetUnderlinePosition = function () {
		var GetMaximumAscentC = Module.cwrap('GetMaximumAscentC', 'number', null);
		return (GetMaximumAscentC() >> 6);
	};

	this.SystemGetUnderlineThickness = function () {
		var GetUnderlinePositionC = Module.cwrap('GetUnderlinePositionC', 'number', null);
		return (GetUnderlinePositionC() >> 6);
	};

	this.GetLineHeight = function () {
		var GetUnderlineThicknessC = Module.cwrap('GetUnderlineThicknessC', 'number', null);
		return (GetUnderlineThicknessC() >> 6);
	};

	this.GetAvailableFonts = function () {
		// NYI
		return null;
	};

	this.GetMaximumAscent = function () {
		var GetMaximumAscentC = Module.cwrap('GetMaximumAscentC', 'number', null);
		return (GetMaximumAscentC() >> 6);
	};

	this.GetMaximumDescent = function () {
		var GetMaximumDescentC = Module.cwrap('GetMaximumDescentC', 'number', null);
		return (GetMaximumDescentC() >> 6);
	};

	this.GetLineGap = function () {
		// NYI
	};
}