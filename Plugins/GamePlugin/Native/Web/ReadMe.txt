Instructions to compile the QuorumFontLibrary WASM module with Emscripten:

You can either setup the Emscripten environment on your system and compile
using em++, or use the pre-configured Docker image at ryanpoppe/unlvwebfonts
by using the following command:

In Powershell:
docker run --rm -v ${pwd}:/src alleew/quorumwebfonts em++ FreeTypeStrategy.cpp -std=c++11 -O3 -s WASM=1 -s USE_FREETYPE=2 -s ASSERTIONS=1 -s LLD_REPORT_UNDEFINED --preload-file fonts -o load.js -s EXPORTED_FUNCTIONS='["_main","_InitializeFreeTypeC","_LoadFontC","_IsFontAvailableC","_SetSizeC","_SetAngleC","_DisposeC","_GetKerningC","_GetUnderlinePositionC","_GetUnderlineThicknessC","_GetLineHeightC","_GetMaximumAscentC","_GetMaximumDescentC","_loadCharC","_getBitmapC","_getBitmapDataC"]' -s EXPORTED_RUNTIME_METHODS='["cwrap","getValue"]' -s SAFE_HEAP=1 
In terminal:
docker run --rm -v ${pwd}:/src -u $(id -u):$(id -g)alleew/quorumwebfonts em++ FreeTypeStrategy.cpp -std=c++11 -O3 -s WASM=1 -s USE_FREETYPE=2 -s ASSERTIONS=1 -s LLD_REPORT_UNDEFINED --preload-file fonts -o load.js -s EXPORTED_FUNCTIONS='["_main","_InitializeFreeTypeC","_LoadFontC","_IsFontAvailableC","_SetSizeC","_SetAngleC","_DisposeC","_GetKerningC","_GetUnderlinePositionC","_GetUnderlineThicknessC","_GetLineHeightC","_GetMaximumAscentC","_GetMaximumDescentC","_loadCharC","_getBitmapC","_getBitmapDataC"]' -s EXPORTED_RUNTIME_METHODS='["cwrap","getValue"]' -s SAFE_HEAP=1

Add any fonts you want to the 'fonts' folder and they can be accessed from C++.
For example: Arial.ttf in the fonts folder can be loaded as a face in FreeType
using this line:

	error = FT_New_Face(library, "fonts/Arial.ttf", 0, &face);
	
*Due to the way that webAssembly works, the wasm module needs to be served to
the browser using a web server.

Loading the wasm module via HTML:
*The order in which scripts are loaded in the browser matters. First load the
standard library and Quorum game scripts in the head:

	<script src = "QuorumStandardLibrary.js"></script>
	<script src = "Default.js"></script>


Then in the body, create the canvas element:

	<canvas id="QuorumGraphicsCanvas" width="800" height="600" tabindex="0" style="outline: none">
		Your browser doesn't appear to support the 
		<code>&lt;canvas&gt;</code> element.
	</canvas>
		
Once the wasm binary has been downloaded and initialized, run the start() method
from Default.js:

	<script type="text/javascript">
		var Module = {
			onRuntimeInitialized: function () {
				Start();
		}
		};
	</script>

Lastly, run the script that loads the wasm binary:

	<script type="application/javascript" src="load.js"></script>
	
Below is the Default.html code:






<!DOCTYPE html>
<html>
    <head>
        <title>Test Web Application</title>
        <meta charset="UTF-8">
        <meta name="viewport"content="width=device-width, initial-scale=1.0">
        <script src = "QuorumStandardLibrary.js"></script>
        <script src = "Default.js"></script>
    </head>
    <body>
        <canvas id="QuorumGraphicsCanvas" width="800" height="600" tabindex="0" style="outline: none">
            Your browser doesn't appear to support the 
            <code>&lt;canvas&gt;</code> element.
        </canvas>
		<script type="text/javascript">
		var Module = {
			onRuntimeInitialized: function () {
				Start();
				console.log("start()");
		}
		};
			</script>
			<script type="application/javascript" src="load.js"></script>
    </body>
</html>