package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_;

/*
The platform-neutral part of VulkanQueueFamily: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanQueueFamilyStrategy
{
    boolean CanPresentToSurface(VulkanSurface_ quorumSurface);
    int GetComputeQueueFamilyIndex(quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_ device);
}
