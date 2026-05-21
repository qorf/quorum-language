package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.VkDrawIndirectCommand;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

public class VulkanDrawIndirectDataMappedMemory
{
    public Object me_;

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
        drawBuffer.free();
    }
}
