package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineCacheStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanPipelineCache implements VulkanPipelineCacheStrategy
{
    private long cacheHandle = 0L;

    public NativeVulkanPipelineCache(VulkanPipelineCache peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        cacheHandle = CreatePipelineCache(NativeVulkan.DeviceHandle(quorumDevice));
        return cacheHandle != 0L;
    }

    public long GetVulkanCacheHandle()
    {
        return cacheHandle;
    }

    private static native long CreatePipelineCache(long device);
}
