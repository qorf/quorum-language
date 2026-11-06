package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanMemoryAllocatorStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

// VMA, compiled into Quorum's Vulkan layer, made as on the desktop (with buffer device addresses).
public class NativeVulkanMemoryAllocator implements VulkanMemoryAllocatorStrategy
{
    private long allocatorPointer = 0L;

    public NativeVulkanMemoryAllocator(VulkanMemoryAllocator peer)
    {
    }

    public boolean CreateNative(VulkanInstance_ quorumInstance, VulkanDevice_ quorumDevice)
    {
        NativeVulkanDevice device = NativeVulkan.Device(quorumDevice);
        allocatorPointer = CreateAllocator(NativeVulkanInstance.InstanceHandle(quorumInstance), device.GetPhysicalDeviceHandle(),
                device.GetDeviceHandle());
        return allocatorPointer != 0L;
    }

    public void DisposeNative()
    {
        if (allocatorPointer != 0L)
            DestroyAllocator(allocatorPointer);
        allocatorPointer = 0L;
    }

    public long GetAllocatorPointer()
    {
        return allocatorPointer;
    }

    public double GetAllocatedBytes()
    {
        return CalculateAllocatedBytes(allocatorPointer);
    }

    public int GetAllocationCount()
    {
        return CalculateAllocationCount(allocatorPointer);
    }

    private static native long CreateAllocator(long instance, long physicalDevice, long device);
    private static native void DestroyAllocator(long allocator);
    private static native double CalculateAllocatedBytes(long allocator);
    private static native int CalculateAllocationCount(long allocator);
}
