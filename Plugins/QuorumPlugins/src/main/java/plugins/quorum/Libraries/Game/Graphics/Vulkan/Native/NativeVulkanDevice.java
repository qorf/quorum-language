package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDeviceStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceFeatures;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily;

/*
The logical device on iOS and Android, made as the desktop makes it: every
queue of every family, the supported Vulkan 1.0 features, and the features
Quorum's renderer needs (64-bit integers in shaders, multi-draw indirect,
robust buffer access, buffer device addresses, scalar block layout, output
layers, and descriptor indexing when its extension is asked for). The C also
turns on VK_KHR_portability_subset when MoltenVK offers it, as Vulkan requires.
*/
public class NativeVulkanDevice implements VulkanDeviceStrategy
{
    private static final String DESCRIPTOR_INDEXING = "VK_EXT_descriptor_indexing";

    private final VulkanDevice peer;
    private long deviceHandle = 0L;
    private long physicalDeviceHandle = 0L;

    public NativeVulkanDevice(VulkanDevice peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanPhysicalDevice_ quorumPhysicalDevice, Array_ extensionNames, Array_ queueFamilies)
    {
        NativeVulkanPhysicalDevice physicalDevice = NativeVulkanPhysicalDevice.Of(quorumPhysicalDevice);
        physicalDeviceHandle = physicalDevice.GetPhysicalDeviceHandle();

        int familyCount = physicalDevice.GetQueueFamilyCount();
        int[] queueCounts = new int[familyCount];
        for (int i = 0; i < familyCount; i++)
        {
            queueCounts[i] = physicalDevice.GetQueueCount(i);

            // A Quorum data structure for each queue family, as on the desktop.
            VulkanQueueFamily quorumFamily = new VulkanQueueFamily();
            quorumFamily.Initialize(quorumPhysicalDevice, physicalDevice.GetQueueCount(i), physicalDevice.GetQueueFlags(i), i);
            queueFamilies.Add(quorumFamily);
        }

        String[] extensions = NativeVulkan.ToStrings(extensionNames);
        NativeVulkanPhysicalDeviceFeatures features = (NativeVulkanPhysicalDeviceFeatures)
                ((VulkanPhysicalDeviceFeatures)quorumPhysicalDevice.GetFeatures()).plugin_.GetStrategy();

        deviceHandle = CreateDevice(physicalDeviceHandle, extensions, queueCounts, features.GetBaseFeatures(),
                NativeVulkan.Contains(extensions, DESCRIPTOR_INDEXING));
        return deviceHandle != 0L;
    }

    public void WaitUntilIdle()
    {
        WaitForDevice(deviceHandle);
    }

    public long GetDeviceHandle()
    {
        return deviceHandle;
    }

    public long GetPhysicalDeviceHandle()
    {
        return physicalDeviceHandle;
    }

    private static native long CreateDevice(long physicalDevice, String[] extensions, int[] queueCounts, boolean[] baseFeatures,
            boolean descriptorIndexing);
    private static native void WaitForDevice(long device);
}
