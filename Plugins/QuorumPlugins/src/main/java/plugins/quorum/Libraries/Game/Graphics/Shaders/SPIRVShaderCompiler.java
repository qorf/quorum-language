package plugins.quorum.Libraries.Game.Graphics.Shaders;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;

public class SPIRVShaderCompiler
{
    public Object me_;

    // Compiled by the platform's shaders strategy: shaderc on the desktop,
    // glslang on iOS and Android (VulkanPlatform.GetShaders).
    public ByteArray_ CompileNative(String shaderName, String code, int shaderType, boolean optimizeShaders)
    {
        byte[] compiledShader = VulkanPlatform.GetShaders().CompileToSPIRV(shaderName, code, shaderType, optimizeShaders);

        ByteArray resultArray = new ByteArray();
        resultArray.plugin_.setBytes(compiledShader);
        return resultArray;
    }
}
