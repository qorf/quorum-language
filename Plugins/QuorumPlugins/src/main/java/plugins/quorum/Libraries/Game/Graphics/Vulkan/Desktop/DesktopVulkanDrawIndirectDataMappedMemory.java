package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDrawIndirectDataMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataMappedMemoryStrategy;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.VkDrawIndirectCommand;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

public class DesktopVulkanDrawIndirectDataMappedMemory implements VulkanDrawIndirectDataMappedMemoryStrategy
{
    private final VulkanDrawIndirectDataMappedMemory peer;

    public DesktopVulkanDrawIndirectDataMappedMemory(VulkanDrawIndirectDataMappedMemory peer)
    {
        this.peer = peer;
    }

    VkDrawIndirectCommand.Buffer drawBuffer = null;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        ByteBuffer dataBuffer = MemoryUtil.memByteBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
        drawBuffer = new VkDrawIndirectCommand.Buffer(dataBuffer);
    }

    public void SetNative(int index, int vertexCount, int instanceCount, int firstVertex, int firstInstance)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDrawIndirectCommand data = VkDrawIndirectCommand.calloc(stack);
            data.vertexCount(vertexCount);
            data.instanceCount(instanceCount);
            data.firstVertex(firstVertex);
            data.firstInstance(firstInstance);

            drawBuffer.put(index, data);
        }
    }

    public int GetByteStride()
    {
        return VkDrawIndirectCommand.SIZEOF;
    }

    public void Dispose()
    {
        // The memory belongs to the VulkanBuffer, which VMA frees; only the
        // view of it is dropped here.
        drawBuffer = null;
    }
}
