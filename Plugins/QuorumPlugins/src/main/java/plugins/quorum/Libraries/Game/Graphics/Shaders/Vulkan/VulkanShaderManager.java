package plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.util.HashMap;

// Compile-time constants only: javac copies their values, so LWJGL isn't
// loaded for them on iOS and Android.
import static org.lwjgl.vulkan.VK10.*;

public class VulkanShaderManager
{
    public Object me_;

    private static HashMap<Shader_, Long> vulkanShaderHandles = new HashMap<>();

    // The module is made by the platform's shaders strategy (VulkanPlatform.GetShaders).
    public boolean CompileShaderNative(Shader_ quorumShader, VulkanDevice_ device, ByteArray_ codeArray)
    {
        byte[] rawBytes = ((ByteArray)codeArray).plugin_.getBytes();
        long handle = VulkanPlatform.GetShaders().CreateShaderModule(device, rawBytes);
        if (handle == 0L)
            return false;

        vulkanShaderHandles.put(quorumShader, handle);
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
