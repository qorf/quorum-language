package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanBufferStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
A buffer on iOS and Android, made with VMA as on the desktop, with its device
address when the usage asks for one.
*/
public class NativeVulkanBuffer implements VulkanBufferStrategy
{
    private final VulkanBuffer peer;
    private long bufferHandle = 0L;
    // The buffer's VMA allocation.
    private long memoryHandle = 0L;
    private long mappedMemoryPointer = 0L;
    private long bufferDeviceAddress = 0L;

    public NativeVulkanBuffer(VulkanBuffer peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, int size, int bufferUsage, int memoryUsage,
                                int allocationFlags, int requiredMemoryFlags, int preferredMemoryFlags, int sharingMode)
    {
        long[] results = new long[3];
        if (!CreateBuffer(NativeVulkan.DeviceHandle(quorumDevice), NativeVulkan.AllocatorHandle(quorumDevice), size, bufferUsage, memoryUsage,
                allocationFlags, requiredMemoryFlags, preferredMemoryFlags, sharingMode, results))
            return false;

        bufferHandle = results[0];
        memoryHandle = results[1];
        bufferDeviceAddress = results[2];
        return true;
    }

    public boolean IsMapping()
    {
        return mappedMemoryPointer != 0L;
    }

    public boolean BeginMapping()
    {
        if (IsMapping())
            return true;

        mappedMemoryPointer = MapBuffer(Allocator(), memoryHandle);
        return mappedMemoryPointer != 0L;
    }

    public void StopMapping()
    {
        if (!IsMapping())
            return;

        // VMA unmaps by allocation, not by the mapped address.
        UnmapBuffer(Allocator(), memoryHandle);
        mappedMemoryPointer = 0L;
    }

    public long GetMappedMemoryPointer()
    {
        return mappedMemoryPointer;
    }

    public long GetBufferHandle()
    {
        return bufferHandle;
    }

    public void Dispose()
    {
        // A second Dispose, or one before CreateNative, does nothing.
        if (bufferHandle == 0L)
            return;

        StopMapping();
        DestroyBuffer(Allocator(), bufferHandle, memoryHandle);
        bufferHandle = 0L;
        memoryHandle = 0L;
        bufferDeviceAddress = 0L;
    }

    public long GetBufferDeviceAddress()
    {
        return bufferDeviceAddress;
    }

    private long Allocator()
    {
        return NativeVulkan.AllocatorHandle(((VulkanBuffer_)peer.me_).GetDevice());
    }

    // results: the buffer, its allocation, and its device address (or 0).
    private static native boolean CreateBuffer(long device, long allocator, int size, int bufferUsage, int memoryUsage, int allocationFlags,
            int requiredMemoryFlags, int preferredMemoryFlags, int sharingMode, long[] results);
    private static native long MapBuffer(long allocator, long allocation);
    private static native void UnmapBuffer(long allocator, long allocation);
    private static native void DestroyBuffer(long allocator, long buffer, long allocation);
}
