package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanMemoryAllocator;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanMemoryAllocator;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanMemoryAllocatorStrategy;

/*
The Java side of Quorum's VulkanMemoryAllocator. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanMemoryAllocator), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanMemoryAllocator, with quorum-natives' src/vulkan).
*/
public class VulkanMemoryAllocator
{
    public Object me_;

    private final VulkanMemoryAllocatorStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanMemoryAllocator(this) : new DesktopVulkanMemoryAllocator(this);

    public boolean CreateNative(VulkanInstance_ quorumInstance, VulkanDevice_ quorumDevice)
    {
        return strategy.CreateNative(quorumInstance, quorumDevice);
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    public long GetAllocatorPointer()
    {
        return strategy.GetAllocatorPointer();
    }

    public double GetAllocatedBytes()
    {
        return strategy.GetAllocatedBytes();
    }

    public int GetAllocationCount()
    {
        return strategy.GetAllocationCount();
    }

    public VulkanMemoryAllocatorStrategy GetStrategy()
    {
        return strategy;
    }
}
