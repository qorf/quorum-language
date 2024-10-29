package plugins.quorum.Libraries.Game.Graphics.Shaders;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkShaderModuleCreateInfo;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateShaderModule;

public class VulkanShaderManager
{
    public Object me_;

    public boolean CompileShaderNative(Shader_ quorumShader, VulkanDevice_ device, ByteArray_ codeArray)
    {
        byte[] rawBytes = ((ByteArray)codeArray).plugin_.getBytes();
        Shader shaderPlugin = ((quorum.Libraries.Game.Graphics.Shaders.Shader)quorumShader).plugin_;
        VkDevice vulkanDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)device).plugin_.GetDevice();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            ByteBuffer codeBuffer = stack.malloc(rawBytes.length);
            codeBuffer.put(rawBytes);

            VkShaderModuleCreateInfo createInfo = VkShaderModuleCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pCode(codeBuffer);

            LongBuffer handleBuffer = stack.mallocLong(1);

            int vulkanResult = vkCreateShaderModule(vulkanDevice, createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            shaderPlugin.SetVulkanHandle(handleBuffer.get(0));
        }

        return true;
    }
}
