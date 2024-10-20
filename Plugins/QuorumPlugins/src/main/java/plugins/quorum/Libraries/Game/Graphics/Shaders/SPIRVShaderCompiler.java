package plugins.quorum.Libraries.Game.Graphics.Shaders;

import org.lwjgl.util.shaderc.Shaderc;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;

import java.nio.ByteBuffer;

public class SPIRVShaderCompiler
{
    public Object me_;

    public ByteArray_ Compile(String shaderName, String code, int shaderType)
    {
        ByteArray resultArray = null;

        long compilerHandle = 0;
        long optionsHandle = 0;
        byte[] compiledShader;

        try
        {
            compilerHandle = Shaderc.shaderc_compiler_initialize();
            optionsHandle = Shaderc.shaderc_compile_options_initialize();

            long result = Shaderc.shaderc_compile_into_spv(compilerHandle, code, shaderType, shaderName, "main", optionsHandle);

            if (Shaderc.shaderc_result_get_compilation_status(result) != Shaderc.shaderc_compilation_status_success)
            {
                throw new RuntimeException("Failed to compile shader \"" + shaderName + "\": " + Shaderc.shaderc_result_get_error_message(result));
            }

            ByteBuffer buffer = Shaderc.shaderc_result_get_bytes(result);
            compiledShader = new byte[buffer.remaining()];
            buffer.get(compiledShader);

            resultArray = new ByteArray();
            resultArray.plugin_.setBytes(compiledShader);
        }
        finally
        {
            Shaderc.shaderc_compile_options_release(optionsHandle);
            Shaderc.shaderc_compiler_release(compilerHandle);
        }

        return resultArray;
    }
}
