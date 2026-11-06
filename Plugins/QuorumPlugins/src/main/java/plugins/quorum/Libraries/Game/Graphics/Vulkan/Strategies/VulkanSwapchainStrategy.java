package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

/*
The platform-neutral part of VulkanSwapchain: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanSwapchainStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSurface_ quorumSurface, VulkanSwapchainInfo_ createInfo);
    boolean GetSwapchainImages(Array_ imageViewArray, VulkanDevice_ quorumDevice);
    long GetVulkanSwapchainHandle();
    void DisposeNative();
}
