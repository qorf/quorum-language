package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanFence: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanFenceStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, boolean signaled);
    void DisposeNative();
    void Wait();
    void Reset();
    long GetFenceHandle();
}
