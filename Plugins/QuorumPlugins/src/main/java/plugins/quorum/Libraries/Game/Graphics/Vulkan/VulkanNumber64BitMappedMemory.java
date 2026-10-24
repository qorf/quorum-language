package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanNumber64BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanNumber64BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanNumber64BitMappedMemoryStrategy;

/*
The Java side of Quorum's VulkanNumber64BitMappedMemory. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanNumber64BitMappedMemory), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanNumber64BitMappedMemory, with quorum-natives' src/vulkan).
*/
public class VulkanNumber64BitMappedMemory
{
    public Object me_;

    private final VulkanNumber64BitMappedMemoryStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanNumber64BitMappedMemory(this) : new DesktopVulkanNumber64BitMappedMemory(this);

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        strategy.CreateNative(quorumBuffer);
    }

    public void SetNative(int index, double value)
    {
        strategy.SetNative(index, value);
    }

    public void SetNative(int index, Number64BitArray_ quorumArray, int arrayOffset, int arrayItems)
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

    public VulkanNumber64BitMappedMemoryStrategy GetStrategy()
    {
        return strategy;
    }
}
