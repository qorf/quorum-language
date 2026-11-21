package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineCacheStrategy;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.VkPipelineCacheCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_INCOMPLETE;
import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreatePipelineCache;
import static org.lwjgl.vulkan.VK10.vkGetPipelineCacheData;

public class DesktopVulkanPipelineCache implements VulkanPipelineCacheStrategy
{
    private final VulkanPipelineCache peer;

    public DesktopVulkanPipelineCache(VulkanPipelineCache peer)
    {
        this.peer = peer;
    }

    private long vulkanCacheHandle = 0L;
    private VulkanDevice device = null;

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        return CreateNative(quorumDevice, null);
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, byte[] data)
    {
        device = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        // Saved caches can be megabytes, more than LWJGL's stack holds, so the data goes in its own memory.
        ByteBuffer initialData = null;
        if (data != null && data.length > 0)
        {
            initialData = MemoryUtil.memAlloc(data.length);
            initialData.put(data).flip();
        }

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPipelineCacheCreateInfo createInfo = VkPipelineCacheCreateInfo.calloc(stack);
            createInfo.sType$Default();
            // Sets initialDataSize too; null leaves both empty.
            createInfo.pInitialData(initialData);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreatePipelineCache(device.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanCacheHandle = handleBuffer.get(0);
        }
        finally
        {
            if (initialData != null)
                MemoryUtil.memFree(initialData);
        }

        return true;
    }

    public byte[] GetData()
    {
        if (device == null || vulkanCacheHandle == 0L)
            return new byte[0];

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // Asked twice: once for the size, then for the bytes.
            PointerBuffer size = stack.mallocPointer(1);
            if (vkGetPipelineCacheData(device.GetDevice(), vulkanCacheHandle, size, null) != VK_SUCCESS)
                return new byte[0];

            int byteCount = (int)size.get(0);
            if (byteCount == 0)
                return new byte[0];

            ByteBuffer bytes = MemoryUtil.memAlloc(byteCount);
            try
            {
                int vulkanResult = vkGetPipelineCacheData(device.GetDevice(), vulkanCacheHandle, size, bytes);
                // VK_INCOMPLETE: the cache grew between the two calls; what was written is still valid.
                if (vulkanResult != VK_SUCCESS && vulkanResult != VK_INCOMPLETE)
                    return new byte[0];

                byte[] result = new byte[(int)size.get(0)];
                bytes.get(result);
                return result;
            }
            finally
            {
                MemoryUtil.memFree(bytes);
            }
        }
    }

    public long GetVulkanCacheHandle() {
        return vulkanCacheHandle;
    }
}
