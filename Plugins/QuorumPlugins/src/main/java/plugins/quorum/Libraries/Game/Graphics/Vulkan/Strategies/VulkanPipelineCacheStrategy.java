package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanPipelineCache: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanPipelineCacheStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice);

    // Starts the cache from data saved earlier (GetData). Null or empty data gives an empty cache.
    // A driver ignores data from another device or driver version, which also gives an empty cache.
    boolean CreateNative(VulkanDevice_ quorumDevice, byte[] data);

    // What the cache holds now, to save and give back to CreateNative later. Never null.
    byte[] GetData();

    long GetVulkanCacheHandle();

    // Frees the cache. Pipelines made with it keep working.
    void DisposeNative(VulkanDevice_ quorumDevice);
}
