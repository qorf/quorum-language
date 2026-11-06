package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceFeatures;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceFeaturesStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

import java.util.Arrays;

/*
The device features on iOS and Android: one true/false per feature, in the
order of VulkanPhysicalDeviceFeatures' Native actions, filled by C from
Vulkan 1.0 to 1.4's feature structures (quorum-natives' src/vulkan/
NativeVulkanFeatureTable.h, generated with VulkanPhysicalDeviceFeatures).
*/
public class NativeVulkanPhysicalDeviceFeatures implements VulkanPhysicalDeviceFeaturesStrategy
{
    // The number of features, and how many of them are Vulkan 1.0's
    // (VkPhysicalDeviceFeatures), which come first.
    public static final int FEATURE_COUNT = 150;
    public static final int BASE_FEATURE_COUNT = 55;

    private final boolean[] features = new boolean[FEATURE_COUNT];

    public NativeVulkanPhysicalDeviceFeatures(VulkanPhysicalDeviceFeatures peer)
    {
    }

    public void CreateNative()
    {
        Arrays.fill(features, false);
    }

    public void GetSupportedDeviceFeatures(VulkanPhysicalDevice_ quorumDevice)
    {
        GetFeatures(NativeVulkanPhysicalDevice.Of(quorumDevice).GetPhysicalDeviceHandle(), features);
    }

    public void DisposeNative()
    {
        Arrays.fill(features, false);
    }

    public boolean Has(int feature)
    {
        return features[feature];
    }

    public void Set(int feature, boolean value)
    {
        features[feature] = value;
    }

    // The Vulkan 1.0 features, as the device is made with them.
    public boolean[] GetBaseFeatures()
    {
        return Arrays.copyOf(features, BASE_FEATURE_COUNT);
    }

    private static native void GetFeatures(long physicalDevice, boolean[] features);
}
