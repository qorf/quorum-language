package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanSwapchain;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanSwapchain;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSwapchainStrategy;

/*
The Java side of Quorum's VulkanSwapchain. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanSwapchain), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanSwapchain, with quorum-natives' src/vulkan).
*/
public class VulkanSwapchain
{
    public Object me_;

    private final VulkanSwapchainStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanSwapchain(this) : new DesktopVulkanSwapchain(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSurface_ quorumSurface, VulkanSwapchainInfo_ createInfo)
    {
        return strategy.CreateNative(quorumDevice, quorumSurface, createInfo);
    }

    public boolean GetSwapchainImages(Array_ imageViewArray, VulkanDevice_ quorumDevice)
    {
        return strategy.GetSwapchainImages(imageViewArray, quorumDevice);
    }

    public long GetVulkanSwapchainHandle()
    {
        return strategy.GetVulkanSwapchainHandle();
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    public VulkanSwapchainStrategy GetStrategy()
    {
        return strategy;
    }
}
