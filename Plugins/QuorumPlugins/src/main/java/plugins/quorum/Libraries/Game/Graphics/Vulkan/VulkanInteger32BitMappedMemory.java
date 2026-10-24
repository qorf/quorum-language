package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Integer32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanInteger32BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanInteger32BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanInteger32BitMappedMemoryStrategy;

/*
The Java side of Quorum's VulkanInteger32BitMappedMemory. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanInteger32BitMappedMemory), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanInteger32BitMappedMemory, with quorum-natives' src/vulkan).
*/
public class VulkanInteger32BitMappedMemory
{
    public Object me_;

    private final VulkanInteger32BitMappedMemoryStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanInteger32BitMappedMemory(this) : new DesktopVulkanInteger32BitMappedMemory(this);

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        strategy.CreateNative(quorumBuffer);
    }

    public void SetNative(int index, int value)
    {
        strategy.SetNative(index, value);
    }

    public void SetNative(int index, Integer32BitArray_ quorumArray, int arrayOffset, int arrayItems)
    {
        strategy.SetNative(index, quorumArray, arrayOffset, arrayItems);
    }

    public int GetNative(int index)
    {
        return strategy.GetNative(index);
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanInteger32BitMappedMemoryStrategy GetStrategy()
    {
        return strategy;
    }
}
