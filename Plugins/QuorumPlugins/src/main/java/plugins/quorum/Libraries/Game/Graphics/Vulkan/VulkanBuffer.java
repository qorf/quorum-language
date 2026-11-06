package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanBufferStrategy;

/*
The Java side of Quorum's VulkanBuffer. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanBuffer), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanBuffer, with quorum-natives' src/vulkan).
*/
public class VulkanBuffer
{
    public Object me_;

    private final VulkanBufferStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanBuffer(this) : new DesktopVulkanBuffer(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, int size, int bufferUsage, int memoryUsage, int allocationFlags, int requiredMemoryFlags, int preferredMemoryFlags, int sharingMode)
    {
        return strategy.CreateNative(quorumDevice, size, bufferUsage, memoryUsage, allocationFlags, requiredMemoryFlags, preferredMemoryFlags, sharingMode);
    }

    public boolean IsMapping()
    {
        return strategy.IsMapping();
    }

    public boolean BeginMapping()
    {
        return strategy.BeginMapping();
    }

    public void StopMapping()
    {
        strategy.StopMapping();
    }

    public long GetMappedMemoryPointer()
    {
        return strategy.GetMappedMemoryPointer();
    }

    public long GetBufferHandle()
    {
        return strategy.GetBufferHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public long GetBufferDeviceAddress()
    {
        return strategy.GetBufferDeviceAddress();
    }

    public VulkanBufferStrategy GetStrategy()
    {
        return strategy;
    }
}
