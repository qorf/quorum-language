package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanNumber32BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanNumber32BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanNumber32BitMappedMemoryStrategy;

/*
The Java side of Quorum's VulkanNumber32BitMappedMemory. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanNumber32BitMappedMemory), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanNumber32BitMappedMemory, with quorum-natives' src/vulkan).
*/
public class VulkanNumber32BitMappedMemory
{
    public Object me_;

    private final VulkanNumber32BitMappedMemoryStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanNumber32BitMappedMemory(this) : new DesktopVulkanNumber32BitMappedMemory(this);

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        strategy.CreateNative(quorumBuffer);
    }

    public void SetNative(int index, double value)
    {
        strategy.SetNative(index, value);
    }

    public void SetNative(int index, Number32BitArray_ quorumArray, int arrayOffset, int arrayItems)
    {
        strategy.SetNative(index, quorumArray, arrayOffset, arrayItems);
    }

    public double GetNative(int index)
    {
        return strategy.GetNative(index);
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanNumber32BitMappedMemoryStrategy GetStrategy()
    {
        return strategy;
    }
}
