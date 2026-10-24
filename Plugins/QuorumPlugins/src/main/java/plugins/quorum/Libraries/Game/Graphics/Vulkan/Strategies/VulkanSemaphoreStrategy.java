package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanSemaphore: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanSemaphoreStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice);
    long GetSemaphoreHandle();
    void Dispose();
}
