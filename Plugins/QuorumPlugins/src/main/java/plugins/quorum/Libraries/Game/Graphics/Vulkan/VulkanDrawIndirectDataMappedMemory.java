package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDrawIndirectDataMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDrawIndirectDataMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataMappedMemoryStrategy;

/*
The Java side of Quorum's VulkanDrawIndirectDataMappedMemory. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDrawIndirectDataMappedMemory), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDrawIndirectDataMappedMemory, with quorum-natives' src/vulkan).
*/
public class VulkanDrawIndirectDataMappedMemory
{
    public Object me_;

    private final VulkanDrawIndirectDataMappedMemoryStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDrawIndirectDataMappedMemory(this) : new DesktopVulkanDrawIndirectDataMappedMemory(this);

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        strategy.CreateNative(quorumBuffer);
    }

    public void SetNative(int index, int vertexCount, int instanceCount, int firstVertex, int firstInstance)
    {
        strategy.SetNative(index, vertexCount, instanceCount, firstVertex, firstInstance);
    }

    public int GetByteStride()
    {
        return strategy.GetByteStride();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanDrawIndirectDataMappedMemoryStrategy GetStrategy()
    {
        return strategy;
    }
}
