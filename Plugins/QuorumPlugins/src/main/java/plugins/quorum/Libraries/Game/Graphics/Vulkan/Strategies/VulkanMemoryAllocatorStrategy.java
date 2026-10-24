package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

/*
The platform-neutral part of VulkanMemoryAllocator: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanMemoryAllocatorStrategy
{
    boolean CreateNative(VulkanInstance_ quorumInstance, VulkanDevice_ quorumDevice);
    void DisposeNative();
    long GetAllocatorPointer();
    double GetAllocatedBytes();
    int GetAllocationCount();
}
