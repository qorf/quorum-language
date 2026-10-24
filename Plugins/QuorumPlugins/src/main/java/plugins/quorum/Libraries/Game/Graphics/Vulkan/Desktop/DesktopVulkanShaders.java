package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.shaderc.Shaderc;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkShaderModuleCreateInfo;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanShadersStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

/*
Shaders on the desktop: shaderc compiles them, and LWJGL makes the modules.
This is the code SPIRVShaderCompiler and VulkanShaderManager had.
*/
public class DesktopVulkanShaders implements VulkanShadersStrategy
{
    public byte[] CompileToSPIRV(String shaderName, String code, int shaderType, boolean optimizeShaders)
    {
        long compilerHandle = 0;
        long optionsHandle = 0;
        byte[] compiledShader;

        try
        {
            compilerHandle = Shaderc.shaderc_compiler_initialize();
            optionsHandle = Shaderc.shaderc_compile_options_initialize();

            // For tricky shader debugging, disabling optimization can prevent the compiler from stripping out
            // code that doesn't contribute to final outputs or removing intermediate values that would be easier to debug.
            if (!optimizeShaders)
                Shaderc.shaderc_compile_options_set_optimization_level(optionsHandle, 0);

            long result = Shaderc.shaderc_compile_into_spv(compilerHandle, code, shaderType, shaderName, "main", optionsHandle);

            if (Shaderc.shaderc_result_get_compilation_status(result) != Shaderc.shaderc_compilation_status_success)
            {
                throw new RuntimeException("Failed to compile shader \"" + shaderName + "\": " + Shaderc.shaderc_result_get_error_message(result));
            }

            ByteBuffer buffer = Shaderc.shaderc_result_get_bytes(result);
            compiledShader = new byte[buffer.remaining()];
            buffer.get(compiledShader);
        }
        finally
        {
            Shaderc.shaderc_compile_options_release(optionsHandle);
            Shaderc.shaderc_compiler_release(compilerHandle);
        }

        return compiledShader;
    }

    public long CreateShaderModule(VulkanDevice_ device, byte[] rawBytes)
    {
        VkDevice vulkanDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)device).plugin_.GetDevice();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            ByteBuffer codeBuffer = stack.malloc(rawBytes.length);
            codeBuffer.put(rawBytes);
            codeBuffer.flip();

            VkShaderModuleCreateInfo createInfo = VkShaderModuleCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pCode(codeBuffer);

            LongBuffer handleBuffer = stack.mallocLong(1);

            int vulkanResult = vkCreateShaderModule(vulkanDevice, createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return 0L;

            return handleBuffer.get(0);
        }
    }
}
