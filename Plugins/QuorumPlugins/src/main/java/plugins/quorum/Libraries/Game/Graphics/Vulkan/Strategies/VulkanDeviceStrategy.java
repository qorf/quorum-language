package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

/*
The platform-neutral part of VulkanDevice: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDeviceStrategy
{
    boolean CreateNative(VulkanPhysicalDevice_ quorumPhysicalDevice, Array_ extensionNames, Array_ queueFamilies);
    void WaitUntilIdle();
}
