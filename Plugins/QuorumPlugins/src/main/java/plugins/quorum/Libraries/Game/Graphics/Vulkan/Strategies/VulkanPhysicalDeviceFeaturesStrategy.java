package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

/*
The device features, read and written by number (the order of
VulkanPhysicalDeviceFeatures' Native actions, which is also the order of
quorum-natives' src/vulkan/NativeVulkanFeatureTable.h).
*/
public interface VulkanPhysicalDeviceFeaturesStrategy
{
    void CreateNative();
    void GetSupportedDeviceFeatures(VulkanPhysicalDevice_ quorumDevice);
    void DisposeNative();
    boolean Has(int feature);
    void Set(int feature, boolean value);
}
