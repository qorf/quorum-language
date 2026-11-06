package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

/*
The platform-neutral part of VulkanPhysicalDevice: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanPhysicalDeviceStrategy
{
    boolean CreateNative(int featuresBitmask, Array_ extensionNames);
    quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits_ GetLimits();
    VulkanInstance_ GetInstance();
    int ConvertDeviceTypeToPriority(int deviceType);
    void Dispose();
}
