package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDrawIndirectDataMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataMappedMemoryStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

/*
Indirect draw commands written into mapped GPU memory on iOS and Android, in
the layout of Vulkan's VkDrawIndirectCommand: vertex count, instance count,
first vertex and first instance, 4 bytes each.
*/
public class NativeVulkanDrawIndirectDataMappedMemory implements VulkanDrawIndirectDataMappedMemoryStrategy
{
    public static final int STRIDE = 16;

    private ByteBuffer drawBuffer = null;

    public NativeVulkanDrawIndirectDataMappedMemory(VulkanDrawIndirectDataMappedMemory peer)
    {
    }

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        drawBuffer = NativeVulkan.Wrap(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
    }

    public void SetNative(int index, int vertexCount, int instanceCount, int firstVertex, int firstInstance)
    {
        int at = index * STRIDE;
        drawBuffer.putInt(at, vertexCount);
        drawBuffer.putInt(at + 4, instanceCount);
        drawBuffer.putInt(at + 8, firstVertex);
        drawBuffer.putInt(at + 12, firstInstance);
    }

    public int GetByteStride()
    {
        return STRIDE;
    }

    public void Dispose()
    {
        // The memory belongs to the VulkanBuffer, which VMA frees; only the
        // view of it is dropped here.
        drawBuffer = null;
    }
}
