package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanQueueFamily;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanQueueFamily;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueueFamilyStrategy;

/*
The Java side of Quorum's VulkanQueueFamily. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanQueueFamily), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanQueueFamily, with quorum-natives' src/vulkan).
*/
public class VulkanQueueFamily
{
    public Object me_;

    private final VulkanQueueFamilyStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanQueueFamily(this) : new DesktopVulkanQueueFamily(this);

    public boolean CanPresentToSurface(VulkanSurface_ quorumSurface)
    {
        return strategy.CanPresentToSurface(quorumSurface);
    }

    public int GetComputeQueueFamilyIndex(quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_ device)
    {
        return strategy.GetComputeQueueFamilyIndex(device);
    }

    public VulkanQueueFamilyStrategy GetStrategy()
    {
        return strategy;
    }
}
