package plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkShaderModuleCreateInfo;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.HashMap;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanShaderManager
{
    public Object me_;

    private static HashMap<Shader_, Long> vulkanShaderHandles = new HashMap<>();

    public boolean CompileShaderNative(Shader_ quorumShader, VulkanDevice_ device, ByteArray_ codeArray)
    {
        byte[] rawBytes = ((ByteArray)codeArray).plugin_.getBytes();
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
                return false;

            vulkanShaderHandles.put(quorumShader, handleBuffer.get(0));
        }

        return true;
    }

    public static void AddVulkanShaderHandle(Shader_ quorumShader, long handle)
    {
        vulkanShaderHandles.put(quorumShader, handle);
    }

    public static void RemoveVulkanShaderHandle(Shader_ quorumShader)
    {
        if (vulkanShaderHandles.containsKey(quorumShader))
            vulkanShaderHandles.remove(quorumShader);
    }

    public static long GetVulkanShaderHandle(Shader_ quorumShader)
    {
        if (vulkanShaderHandles.containsKey(quorumShader))
            return vulkanShaderHandles.get(quorumShader);
        else
            return 0L;
    }

    public static int GetVulkanShaderType(int quorumShaderType)
    {
        if (quorumShaderType == 1)
            return VK_SHADER_STAGE_VERTEX_BIT;
        if (quorumShaderType == 2)
            return VK_SHADER_STAGE_FRAGMENT_BIT;
        if (quorumShaderType == 3)
            return VK_SHADER_STAGE_GEOMETRY_BIT;
        if (quorumShaderType == 4)
            return VK_SHADER_STAGE_TESSELLATION_CONTROL_BIT;
        if (quorumShaderType == 5)
            return VK_SHADER_STAGE_TESSELLATION_EVALUATION_BIT;
        if (quorumShaderType == 6)
            return VK_SHADER_STAGE_COMPUTE_BIT;

        return -1;
    }
}
