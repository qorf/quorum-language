package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDeviceStrategy;

/*
The Java side of Quorum's VulkanDevice. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDevice), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDevice, with quorum-natives' src/vulkan).
*/
public class VulkanDevice
{
    public Object me_;

    private final VulkanDeviceStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDevice(this) : new DesktopVulkanDevice(this);

    public boolean CreateNative(VulkanPhysicalDevice_ quorumPhysicalDevice, Array_ extensionNames, Array_ queueFamilies)
    {
        return strategy.CreateNative(quorumPhysicalDevice, extensionNames, queueFamilies);
    }

    public void WaitUntilIdle()
    {
        strategy.WaitUntilIdle();
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkDevice GetDevice()
    {
        return ((DesktopVulkanDevice)strategy).GetDevice();
    }

    public VulkanDeviceStrategy GetStrategy()
    {
        return strategy;
    }
}
