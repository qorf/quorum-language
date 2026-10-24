package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanGraphics;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanGraphics;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanGraphicsStrategy;

/*
The Java side of Quorum's VulkanGraphics. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanGraphics), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanGraphics, with quorum-natives' src/vulkan).
*/
public class VulkanGraphics
{
    public Object me_;

    private final VulkanGraphicsStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanGraphics(this) : new DesktopVulkanGraphics(this);

    public int PrepareNextSwapchainImage(VulkanDevice_ quorumDevice, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore)
    {
        return strategy.PrepareNextSwapchainImage(quorumDevice, quorumSwapchain, quorumSemaphore);
    }

    public int GetCurrentSwapchainIndexNative()
    {
        return strategy.GetCurrentSwapchainIndexNative();
    }

    public boolean DisplayCurrentImageNative(VulkanQueue_ quorumQueue, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore, int imageIndex)
    {
        return strategy.DisplayCurrentImageNative(quorumQueue, quorumSwapchain, quorumSemaphore, imageIndex);
    }

    public VulkanGraphicsStrategy GetStrategy()
    {
        return strategy;
    }
}
