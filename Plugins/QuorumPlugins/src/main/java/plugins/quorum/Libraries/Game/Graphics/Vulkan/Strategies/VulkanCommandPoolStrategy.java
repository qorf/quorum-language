package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanCommandPool: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanCommandPoolStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, int flags, int queueFamilyIndex);
    long GetCommandPoolHandle();
}
