package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

/*
Records each kind of Vulkan command into a command buffer. command is the
Quorum command (for example a DrawCommand), with the values to record.
*/
public interface VulkanCommandsStrategy
{
    void RecordBeginRenderPass(Object command, VulkanCommandBuffer_ buffer);
    void RecordBindComputePipeline(Object command, VulkanCommandBuffer_ buffer);
    void RecordBindDescriptorSet(Object command, VulkanCommandBuffer_ buffer);
    void RecordBindIndexBuffer(Object command, VulkanCommandBuffer_ buffer);
    void RecordBindPipeline(Object command, VulkanCommandBuffer_ buffer);
    void RecordBindVertexBuffer(Object command, VulkanCommandBuffer_ buffer);
    void RecordComputeShaderDispatch(Object command, VulkanCommandBuffer_ buffer);
    void RecordCopyBuffer(Object command, VulkanCommandBuffer_ buffer);
    void RecordCopyBufferToImage(Object command, VulkanCommandBuffer_ buffer);
    void RecordDraw(Object command, VulkanCommandBuffer_ buffer);
    void RecordDrawIndexed(Object command, VulkanCommandBuffer_ buffer);
    void RecordDrawIndirect(Object command, VulkanCommandBuffer_ buffer);
    int GetDefaultIndirectStride();
    void RecordEndRenderPass(Object command, VulkanCommandBuffer_ buffer);
    void RecordPipelineBarrier(Object command, VulkanCommandBuffer_ buffer);
    void RecordSetPushConstants(Object command, VulkanCommandBuffer_ buffer);
    void RecordSetScissor(Object command, VulkanCommandBuffer_ buffer);
    void RecordSetViewport(Object command, VulkanCommandBuffer_ buffer);
}
