package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkPipelineCacheCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreatePipelineCache;

public class VulkanPipelineCache
{
    public Object me_;

    private long vulkanCacheHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        VulkanDevice device = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPipelineCacheCreateInfo createInfo = VkPipelineCacheCreateInfo.calloc(stack);
            createInfo.sType$Default();

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreatePipelineCache(device.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanCacheHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetVulkanCacheHandle() {
        return vulkanCacheHandle;
    }
}
