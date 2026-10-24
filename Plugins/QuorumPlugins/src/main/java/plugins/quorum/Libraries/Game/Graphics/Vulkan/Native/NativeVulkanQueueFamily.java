package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueueFamilyStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_;

public class NativeVulkanQueueFamily implements VulkanQueueFamilyStrategy
{
    // VK_QUEUE_COMPUTE_BIT
    private static final int COMPUTE = 0x2;

    private final VulkanQueueFamily peer;

    public NativeVulkanQueueFamily(VulkanQueueFamily peer)
    {
        this.peer = peer;
    }

    public boolean CanPresentToSurface(VulkanSurface_ quorumSurface)
    {
        quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_ quorumFamily = (quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_)peer.me_;
        long physicalDevice = NativeVulkanPhysicalDevice.Of(quorumFamily.GetPhysicalDevice()).GetPhysicalDeviceHandle();
        long surface = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface)quorumSurface).plugin_.GetSurfacePointer();
        return CanPresent(physicalDevice, quorumFamily.GetQueueFamilyIndex(), surface);
    }

    public int GetComputeQueueFamilyIndex(VulkanPhysicalDevice_ device)
    {
        NativeVulkanPhysicalDevice physicalDevice = NativeVulkanPhysicalDevice.Of(device);
        for (int i = 0; i < physicalDevice.GetQueueFamilyCount(); i++)
        {
            if ((physicalDevice.GetQueueFlags(i) & COMPUTE) != 0)
                return i;
        }
        return -1;
    }

    private static native boolean CanPresent(long physicalDevice, int familyIndex, long surface);
}
