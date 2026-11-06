package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
Compiles GLSL shaders to SPIR-V, and makes Vulkan shader modules from SPIR-V:
with shaderc and LWJGL on the desktop, or with glslang and Quorum's own Vulkan
layer on iOS and Android. Used by SPIRVShaderCompiler and VulkanShaderManager.
*/
public interface VulkanShadersStrategy
{
    // shaderType is shaderc's kind (vertex 0, fragment 1, compute 2, geometry
    // 3, tessellation control 4, tessellation evaluation 5). Throws a
    // RuntimeException with the compiler's message if the code doesn't compile.
    byte[] CompileToSPIRV(String shaderName, String code, int shaderType, boolean optimizeShaders);

    // Returns the shader module's handle, or 0 if it couldn't be made.
    long CreateShaderModule(VulkanDevice_ device, byte[] code);
}
