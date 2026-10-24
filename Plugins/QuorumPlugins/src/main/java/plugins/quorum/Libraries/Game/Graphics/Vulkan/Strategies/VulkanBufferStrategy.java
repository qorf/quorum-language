package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanBuffer: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanBufferStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, int size, int bufferUsage, int memoryUsage, int allocationFlags, int requiredMemoryFlags, int preferredMemoryFlags, int sharingMode);
    boolean IsMapping();
    boolean BeginMapping();
    void StopMapping();
    long GetMappedMemoryPointer();
    long GetBufferHandle();
    void Dispose();
    long GetBufferDeviceAddress();
}
