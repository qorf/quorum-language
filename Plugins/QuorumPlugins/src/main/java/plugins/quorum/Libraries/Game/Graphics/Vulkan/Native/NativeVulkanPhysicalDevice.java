package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

/*
Chooses the GPU on iOS and Android, as the desktop does: one with queue
families for every requested feature and every requested extension, the best
type first (a discrete GPU over an integrated one), then the most memory.
Phones have one GPU, so this mostly finds it and checks it.
*/
public class NativeVulkanPhysicalDevice implements VulkanPhysicalDeviceStrategy
{
    private final VulkanPhysicalDevice peer;
    private long physicalDeviceHandle = 0L;
    private quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits quorumLimits = null;

    // Each queue family's queue count and flags, in the device's order.
    private int[] queueCounts = new int[0];
    private int[] queueFlags = new int[0];

    public NativeVulkanPhysicalDevice(VulkanPhysicalDevice peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(int featuresBitmask, Array_ extensionNames)
    {
        long instance = NativeVulkanInstance.InstanceHandle(GetInstance());
        physicalDeviceHandle = SelectPhysicalDevice(instance, featuresBitmask, NativeVulkan.ToStrings(extensionNames));
        if (physicalDeviceHandle == 0L)
            return false;

        int[] families = GetQueueFamilies(physicalDeviceHandle);
        int count = families.length / 2;
        queueCounts = new int[count];
        queueFlags = new int[count];
        for (int i = 0; i < count; i++)
        {
            queueCounts[i] = families[i * 2];
            queueFlags[i] = families[i * 2 + 1];
        }

        quorumLimits = new quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits();
        NativeVulkanPhysicalDeviceLimits limits = (NativeVulkanPhysicalDeviceLimits)quorumLimits.plugin_.GetStrategy();
        FillLimits(physicalDeviceHandle, limits.GetIntegers(), limits.GetNumbers());
        return true;
    }

    public quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits_ GetLimits()
    {
        return quorumLimits;
    }

    public VulkanInstance_ GetInstance()
    {
        return ((VulkanPhysicalDevice_)peer.me_).GetInstance();
    }

    // The same order as the desktop's; C uses its own copy to choose.
    public int ConvertDeviceTypeToPriority(int deviceType)
    {
        switch (deviceType)
        {
            case 0: // Other
                return 0;
            case 4: // CPU
                return 1;
            case 3: // Virtual GPU
                return 2;
            case 1: // Integrated GPU
                return 3;
            case 2: // Discrete GPU
                return 4;
            default:
                return -1;
        }
    }

    public void Dispose()
    {
        // The physical device belongs to the instance; nothing is freed.
        physicalDeviceHandle = 0L;
    }

    public long GetPhysicalDeviceHandle()
    {
        return physicalDeviceHandle;
    }

    public int GetQueueFamilyCount()
    {
        return queueCounts.length;
    }

    public int GetQueueCount(int family)
    {
        return queueCounts[family];
    }

    public int GetQueueFlags(int family)
    {
        return queueFlags[family];
    }

    public static NativeVulkanPhysicalDevice Of(VulkanPhysicalDevice_ device)
    {
        return (NativeVulkanPhysicalDevice)((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)device).plugin_.GetStrategy();
    }

    private static native long SelectPhysicalDevice(long instance, int featuresBitmask, String[] extensions);
    // Pairs of queue count and queue flags, one pair per family.
    private static native int[] GetQueueFamilies(long physicalDevice);
    private static native void FillLimits(long physicalDevice, long[] integers, double[] numbers);
}
