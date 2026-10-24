package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineCacheStrategy;

/*
The Java side of Quorum's VulkanPipelineCache. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPipelineCache), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPipelineCache, with quorum-natives' src/vulkan).
*/
public class VulkanPipelineCache
{
    public Object me_;

    private final VulkanPipelineCacheStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPipelineCache(this) : new DesktopVulkanPipelineCache(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        return strategy.CreateNative(quorumDevice);
    }

    public long GetVulkanCacheHandle()
    {
        return strategy.GetVulkanCacheHandle();
    }

    public VulkanPipelineCacheStrategy GetStrategy()
    {
        return strategy;
    }
}
