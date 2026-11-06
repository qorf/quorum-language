package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_;

/*
The platform-neutral part of VulkanGraphics: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanGraphicsStrategy
{
    int PrepareNextSwapchainImage(VulkanDevice_ quorumDevice, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore);
    int GetCurrentSwapchainIndexNative();
    boolean DisplayCurrentImageNative(VulkanQueue_ quorumQueue, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore, int imageIndex);
}
