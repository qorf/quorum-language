package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandsStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.*;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageMemoryBarrier_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryBarrier_;

import java.nio.ByteBuffer;

/*
Records the Vulkan commands on iOS and Android, with the same values the
desktop's DesktopVulkanCommands records with LWJGL.
*/
public class NativeVulkanCommands implements VulkanCommandsStrategy
{
    // VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL, as the desktop copies into images.
    private static final int TRANSFER_DESTINATION_LAYOUT = 7;

    public void RecordBeginRenderPass(Object command, VulkanCommandBuffer_ buffer)
    {
        BeginRenderPassCommand_ quorumCommand = (BeginRenderPassCommand_)command;
        long renderPass = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumCommand.GetRenderPass()).plugin_.GetRenderPassHandle();
        long framebuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer)quorumCommand.GetFramebuffer()).plugin_.GetFramebufferHandle();
        int[] clearValues = null;
        if (quorumCommand.GetClearValues() != null)
            clearValues = ((NativeVulkanClearValues)((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues)quorumCommand.GetClearValues()).plugin_.GetStrategy()).GetValues();

        BeginRenderPass(Handle(buffer), renderPass, framebuffer, quorumCommand.GetRenderAreaWidth(), quorumCommand.GetRenderAreaHeight(),
                clearValues, quorumCommand.GetCommandType());
    }

    public void RecordBindComputePipeline(Object command, VulkanCommandBuffer_ buffer)
    {
        BindComputePipelineCommand_ quorumCommand = (BindComputePipelineCommand_)command;
        long pipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline)quorumCommand.GetPipeline()).plugin_.GetPipelineHandle();
        BindPipeline(Handle(buffer), quorumCommand.GetBindPoint(), pipeline);
    }

    public void RecordBindDescriptorSet(Object command, VulkanCommandBuffer_ buffer)
    {
        BindDescriptorSetCommand_ quorumCommand = (BindDescriptorSetCommand_)command;
        long layout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_.GetLayoutHandle();
        long descriptorSet = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet)quorumCommand.GetDescriptorSet()).plugin_.GetDescriptorSetHandle();
        BindDescriptorSet(Handle(buffer), quorumCommand.GetBindPoint(), layout, quorumCommand.GetSetIndex(), descriptorSet);
    }

    public void RecordBindIndexBuffer(Object command, VulkanCommandBuffer_ buffer)
    {
        BindIndexBufferCommand_ quorumCommand = (BindIndexBufferCommand_)command;
        BindIndexBuffer(Handle(buffer), BufferHandle(quorumCommand.GetBuffer()), quorumCommand.GetOffset(), quorumCommand.GetIndexType());
    }

    public void RecordBindPipeline(Object command, VulkanCommandBuffer_ buffer)
    {
        BindPipelineCommand_ quorumCommand = (BindPipelineCommand_)command;
        long pipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline)quorumCommand.GetPipeline()).plugin_.GetPipelineHandle();
        BindPipeline(Handle(buffer), quorumCommand.GetBindPoint(), pipeline);
    }

    public void RecordBindVertexBuffer(Object command, VulkanCommandBuffer_ buffer)
    {
        BindVertexBufferCommand_ quorumCommand = (BindVertexBufferCommand_)command;
        BindVertexBuffer(Handle(buffer), quorumCommand.GetFirstBinding(), BufferHandle(quorumCommand.GetBuffer()), quorumCommand.GetOffset());
    }

    public void RecordComputeShaderDispatch(Object command, VulkanCommandBuffer_ buffer)
    {
        ComputeShaderDispatchCommand_ quorumCommand = (ComputeShaderDispatchCommand_)command;
        Dispatch(Handle(buffer), quorumCommand.GetX(), quorumCommand.GetY(), quorumCommand.GetZ());
    }

    public void RecordCopyBuffer(Object command, VulkanCommandBuffer_ buffer)
    {
        CopyBufferCommand_ quorumCommand = (CopyBufferCommand_)command;
        CopyBuffer(Handle(buffer), BufferHandle(quorumCommand.GetSourceBuffer()), BufferHandle(quorumCommand.GetDestinationBuffer()),
                quorumCommand.GetSourceOffset(), quorumCommand.GetDestinationOffset(), quorumCommand.GetSize());
    }

    public void RecordCopyBufferToImage(Object command, VulkanCommandBuffer_ buffer)
    {
        CopyBufferToImageCommand_ quorumCommand = (CopyBufferToImageCommand_)command;
        long image = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumCommand.GetDestinationImage()).plugin_.GetVulkanImageHandle();
        int[] values = new int[] {
            quorumCommand.GetBufferOffset(), quorumCommand.GetBufferRowLength(), quorumCommand.GetBufferImageHeight(),
            quorumCommand.GetAspectMask(), quorumCommand.GetMipLevel(), quorumCommand.GetBaseArrayLayer(), quorumCommand.GetLayerCount(),
            quorumCommand.GetImageOffsetX(), quorumCommand.GetImageOffsetY(), quorumCommand.GetImageOffsetZ(),
            quorumCommand.GetImageWidth(), quorumCommand.GetImageHeight(), quorumCommand.GetImageDepth()
        };
        CopyBufferToImage(Handle(buffer), BufferHandle(quorumCommand.GetSourceBuffer()), image, TRANSFER_DESTINATION_LAYOUT, values);
    }

    public void RecordDraw(Object command, VulkanCommandBuffer_ buffer)
    {
        DrawCommand_ quorumCommand = (DrawCommand_)command;
        Draw(Handle(buffer), quorumCommand.GetVertexCount(), quorumCommand.GetInstanceCount(), quorumCommand.GetFirstVertex(),
                quorumCommand.GetFirstInstance());
    }

    public void RecordDrawIndexed(Object command, VulkanCommandBuffer_ buffer)
    {
        DrawIndexedCommand_ quorumCommand = (DrawIndexedCommand_)command;
        DrawIndexed(Handle(buffer), quorumCommand.GetIndexCount(), quorumCommand.GetInstanceCount(), quorumCommand.GetFirstIndex(),
                quorumCommand.GetVertexOffset(), quorumCommand.GetFirstInstance());
    }

    public void RecordDrawIndirect(Object command, VulkanCommandBuffer_ buffer)
    {
        DrawIndirectCommand_ quorumCommand = (DrawIndirectCommand_)command;
        DrawIndirect(Handle(buffer), BufferHandle(quorumCommand.GetDrawCommands()), quorumCommand.GetOffset(), quorumCommand.GetDrawCount(),
                quorumCommand.GetStride());
    }

    public int GetDefaultIndirectStride()
    {
        return NativeVulkanDrawIndirectDataMappedMemory.STRIDE;
    }

    public void RecordEndRenderPass(Object command, VulkanCommandBuffer_ buffer)
    {
        EndRenderPass(Handle(buffer));
    }

    public void RecordPipelineBarrier(Object command, VulkanCommandBuffer_ buffer)
    {
        PipelineBarrierCommand_ quorumCommand = (PipelineBarrierCommand_)command;

        // An image memory barrier, or else a plain memory barrier, as on the desktop.
        if (quorumCommand.GetMemoryBarrier() instanceof VulkanImageMemoryBarrier_)
        {
            VulkanImageMemoryBarrier_ quorumBarrier = (VulkanImageMemoryBarrier_)quorumCommand.GetMemoryBarrier();
            long image = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumBarrier.GetImage()).plugin_.GetVulkanImageHandle();
            int[] values = new int[] {
                quorumBarrier.GetSourceStageMask(), quorumBarrier.GetDestinationStageMask(),
                quorumBarrier.GetSourceAccessMask(), quorumBarrier.GetDestinationAccessMask(),
                quorumBarrier.GetOldLayout(), quorumBarrier.GetNewLayout(),
                quorumBarrier.GetSourceQueueFamilyIndex(), quorumBarrier.GetDestinationQueueFamilyIndex(),
                quorumBarrier.GetAspectMask(), quorumBarrier.GetBaseMipLevel(), quorumBarrier.GetLevelCount(),
                quorumBarrier.GetBaseArrayLayer(), quorumBarrier.GetLayerCount()
            };
            ImageBarrier(Handle(buffer), image, values);
        }
        else
        {
            VulkanMemoryBarrier_ quorumBarrier = quorumCommand.GetMemoryBarrier();
            MemoryBarrier(Handle(buffer), quorumBarrier.GetSourceStageMask(), quorumBarrier.GetDestinationStageMask(),
                    quorumBarrier.GetSourceAccessMask(), quorumBarrier.GetDestinationAccessMask());
        }
    }

    public void RecordSetPushConstants(Object command, VulkanCommandBuffer_ buffer)
    {
        SetPushConstantsCommand_ quorumCommand = (SetPushConstantsCommand_)command;
        long layout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_.GetLayoutHandle();
        // The whole buffer, as on the desktop.
        ByteBuffer data = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantBuffer)quorumCommand.GetData()).plugin_.GetByteBuffer();
        PushConstants(Handle(buffer), layout, quorumCommand.GetStageFlags(), quorumCommand.GetOffset(), data, data.capacity());
    }

    public void RecordSetScissor(Object command, VulkanCommandBuffer_ buffer)
    {
        SetScissorCommand_ quorumCommand = (SetScissorCommand_)command;
        SetScissor(Handle(buffer), quorumCommand.GetX(), quorumCommand.GetY(), quorumCommand.GetWidth(), quorumCommand.GetHeight());
    }

    public void RecordSetViewport(Object command, VulkanCommandBuffer_ buffer)
    {
        SetViewportCommand_ quorumCommand = (SetViewportCommand_)command;
        SetViewport(Handle(buffer), quorumCommand.GetX(), quorumCommand.GetY(), quorumCommand.GetWidth(), quorumCommand.GetHeight(),
                (float)quorumCommand.GetMinimumDepth(), (float)quorumCommand.GetMaximumDepth());
    }

    private static long Handle(VulkanCommandBuffer_ buffer)
    {
        return NativeVulkanCommandBuffer.Handle(buffer);
    }

    private static long BufferHandle(VulkanBuffer_ buffer)
    {
        return NativeVulkan.Buffer(buffer).GetBufferHandle();
    }

    // clearValues: 4 values of 32 bits for each VkClearValue, or null.
    private static native void BeginRenderPass(long commandBuffer, long renderPass, long framebuffer, int width, int height,
            int[] clearValues, int contents);
    private static native void EndRenderPass(long commandBuffer);
    private static native void BindPipeline(long commandBuffer, int bindPoint, long pipeline);
    private static native void BindDescriptorSet(long commandBuffer, int bindPoint, long layout, int setIndex, long descriptorSet);
    private static native void BindIndexBuffer(long commandBuffer, long buffer, long offset, int indexType);
    private static native void BindVertexBuffer(long commandBuffer, int firstBinding, long buffer, long offset);
    private static native void Dispatch(long commandBuffer, int x, int y, int z);
    private static native void CopyBuffer(long commandBuffer, long source, long destination, long sourceOffset, long destinationOffset,
            long size);
    // values: buffer offset, row length, image height, aspect mask, mip level,
    // base array layer, layer count, image offset x, y, z, and width, height, depth.
    private static native void CopyBufferToImage(long commandBuffer, long buffer, long image, int layout, int[] values);
    private static native void Draw(long commandBuffer, int vertexCount, int instanceCount, int firstVertex, int firstInstance);
    private static native void DrawIndexed(long commandBuffer, int indexCount, int instanceCount, int firstIndex, int vertexOffset,
            int firstInstance);
    private static native void DrawIndirect(long commandBuffer, long buffer, long offset, int drawCount, int stride);
    // values: source and destination stage masks, source and destination
    // access masks, old and new layouts, source and destination queue
    // families, aspect mask, base mip level, level count, base array layer,
    // layer count.
    private static native void ImageBarrier(long commandBuffer, long image, int[] values);
    private static native void MemoryBarrier(long commandBuffer, int sourceStageMask, int destinationStageMask, int sourceAccessMask,
            int destinationAccessMask);
    private static native void PushConstants(long commandBuffer, long layout, int stageFlags, int offset, ByteBuffer data, int size);
    private static native void SetScissor(long commandBuffer, int x, int y, int width, int height);
    private static native void SetViewport(long commandBuffer, float x, float y, float width, float height, float minimumDepth,
            float maximumDepth);
}
