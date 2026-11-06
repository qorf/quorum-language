package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPhysicalDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPhysicalDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceStrategy;

/*
The Java side of Quorum's VulkanPhysicalDevice. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPhysicalDevice), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPhysicalDevice, with quorum-natives' src/vulkan).
*/
public class VulkanPhysicalDevice
{
    public java.lang.Object me_;

    private final VulkanPhysicalDeviceStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPhysicalDevice(this) : new DesktopVulkanPhysicalDevice(this);

    public boolean CreateNative(int featuresBitmask, Array_ extensionNames)
    {
        return strategy.CreateNative(featuresBitmask, extensionNames);
    }

    public quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits_ GetLimits()
    {
        return strategy.GetLimits();
    }

    public VulkanInstance_ GetInstance()
    {
        return strategy.GetInstance();
    }

    public int ConvertDeviceTypeToPriority(int deviceType)
    {
        return strategy.ConvertDeviceTypeToPriority(deviceType);
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkInstance GetVkInstance()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetVkInstance();
    }

    public VkPhysicalDevice GetDevice()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetDevice();
    }

    public VkPhysicalDeviceProperties GetDeviceProperties()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetDeviceProperties();
    }

    public VkExtensionProperties.Buffer GetDeviceExtensions()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetDeviceExtensions();
    }

    public VkQueueFamilyProperties.Buffer GetQueueFamilyProperties()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetQueueFamilyProperties();
    }

    public VkPhysicalDeviceMemoryProperties GetMemoryProperties()
    {
        return ((DesktopVulkanPhysicalDevice)strategy).GetMemoryProperties();
    }

    public VulkanPhysicalDeviceStrategy GetStrategy()
    {
        return strategy;
    }
}
