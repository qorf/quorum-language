package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanShadersStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
Shaders on iOS and Android: glslang (compiled into Quorum's Vulkan layer)
turns the GLSL into SPIR-V, as shaderc, which is built on glslang, does on the
desktop. As there, no optimization is done (shaderc's default), so
optimizeShaders makes no difference.
*/
public class NativeVulkanShaders implements VulkanShadersStrategy
{
    public byte[] CompileToSPIRV(String shaderName, String code, int shaderType, boolean optimizeShaders)
    {
        // C throws the RuntimeException, with glslang's messages, if it fails.
        return CompileShader(shaderName, code, shaderType);
    }

    public long CreateShaderModule(VulkanDevice_ device, byte[] code)
    {
        return CreateShaderModule(NativeVulkan.DeviceHandle(device), code);
    }

    private static native byte[] CompileShader(String shaderName, String code, int shaderType);
    private static native long CreateShaderModule(long device, byte[] code);
}
