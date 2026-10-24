package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkBufferCopy;
import org.lwjgl.vulkan.VkBufferImageCopy;
import org.lwjgl.vulkan.VkClearValue;
import org.lwjgl.vulkan.VkDependencyInfo;
import org.lwjgl.vulkan.VkDrawIndirectCommand;
import org.lwjgl.vulkan.VkExtent3D;
import org.lwjgl.vulkan.VkImageMemoryBarrier;
import org.lwjgl.vulkan.VkMemoryBarrier2;
import org.lwjgl.vulkan.VkMemoryBarrier;
import org.lwjgl.vulkan.VkOffset3D;
import org.lwjgl.vulkan.VkRect2D;
import org.lwjgl.vulkan.VkRenderPassBeginInfo;
import org.lwjgl.vulkan.VkViewport;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanImage;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindComputePipelineCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindIndexBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindPipelineCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindVertexBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyBufferToImageCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawIndexedCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawIndirectCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.PipelineBarrierCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetPushConstantsCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetScissorCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetViewportCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageMemoryBarrier_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryBarrier_;

import static org.lwjgl.vulkan.VK10.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL;
import static org.lwjgl.vulkan.VK10.vkCmdBeginRenderPass;
import static org.lwjgl.vulkan.VK10.vkCmdBindDescriptorSets;
import static org.lwjgl.vulkan.VK10.vkCmdBindIndexBuffer;
import static org.lwjgl.vulkan.VK10.vkCmdBindPipeline;
import static org.lwjgl.vulkan.VK10.vkCmdBindVertexBuffers;
import static org.lwjgl.vulkan.VK10.vkCmdCopyBuffer;
import static org.lwjgl.vulkan.VK10.vkCmdCopyBufferToImage;
import static org.lwjgl.vulkan.VK10.vkCmdDispatch;
import static org.lwjgl.vulkan.VK10.vkCmdDraw;
import static org.lwjgl.vulkan.VK10.vkCmdDrawIndexed;
import static org.lwjgl.vulkan.VK10.vkCmdDrawIndirect;
import static org.lwjgl.vulkan.VK10.vkCmdEndRenderPass;
import static org.lwjgl.vulkan.VK10.vkCmdPipelineBarrier;
import static org.lwjgl.vulkan.VK10.vkCmdPushConstants;
import static org.lwjgl.vulkan.VK10.vkCmdSetScissor;
import static org.lwjgl.vulkan.VK10.vkCmdSetViewport;
import static org.lwjgl.vulkan.VK13.vkCmdPipelineBarrier2;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandsStrategy;

/*
Records the Vulkan commands with LWJGL on the desktop. Each Record action is
the code the command's peer (in the Commands package) had, with the
Quorum command passed in.
*/
public class DesktopVulkanCommands implements VulkanCommandsStrategy
{
    public void RecordBeginRenderPass(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginRenderPassCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginRenderPassCommand_)command;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        VulkanRenderPass pluginRenderPass = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumCommand.GetRenderPass()).plugin_;
        VulkanFramebuffer pluginFramebuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer)quorumCommand.GetFramebuffer()).plugin_;
        VulkanClearValues pluginClearValues = null;
        if (quorumCommand.GetClearValues() != null)
            pluginClearValues = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues)quorumCommand.GetClearValues()).plugin_;

        int width = quorumCommand.GetRenderAreaWidth();
        int height = quorumCommand.GetRenderAreaHeight();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkRenderPassBeginInfo beginInfo = VkRenderPassBeginInfo.calloc(stack);
            beginInfo.sType$Default();
            beginInfo.renderPass(pluginRenderPass.GetRenderPassHandle());
            beginInfo.renderArea(a -> a.extent().set(width, height));
            beginInfo.framebuffer(pluginFramebuffer.GetFramebufferHandle());
            if (pluginClearValues != null)
                beginInfo.pClearValues(pluginClearValues.GetBuffer());

            vkCmdBeginRenderPass(pluginBuffer.GetCommandBuffer(), beginInfo, quorumCommand.GetCommandType());
        }
    }

    public void RecordBindComputePipeline(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        BindComputePipelineCommand_ quorumCommand = (BindComputePipelineCommand_)command;
        VulkanComputePipeline pluginPipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline)quorumCommand.GetPipeline()).plugin_;

        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindPipeline(pluginBuffer.GetCommandBuffer(), quorumCommand.GetBindPoint(), pluginPipeline.GetPipelineHandle());
    }

    public void RecordBindDescriptorSet(Object command, VulkanCommandBuffer_ quorumCommandBuffer)
    {
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumCommandBuffer).plugin_;
        quorum.Libraries.Game.Graphics.Vulkan.Commands.BindDescriptorSetCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.BindDescriptorSetCommand_)command;
        VulkanPipelineLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_;
        VulkanDescriptorSet pluginDescriptorSet = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet)quorumCommand.GetDescriptorSet()).plugin_;
        int bindPoint = quorumCommand.GetBindPoint();
        int setIndex = quorumCommand.GetSetIndex();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer descriptorHandles = stack.mallocLong(1);
            descriptorHandles.put(0, pluginDescriptorSet.GetDescriptorSetHandle());
            vkCmdBindDescriptorSets(pluginCommandBuffer.GetCommandBuffer(), bindPoint, pluginLayout.GetLayoutHandle(), setIndex, descriptorHandles, null);
        }
    }

    public void RecordBindIndexBuffer(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        BindIndexBufferCommand_ quorumCommand = (BindIndexBufferCommand_)command;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetBuffer()).plugin_;

        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindIndexBuffer(pluginCommandBuffer.GetCommandBuffer(), pluginBuffer.GetBufferHandle(), quorumCommand.GetOffset(), quorumCommand.GetIndexType());
    }

    public void RecordBindPipeline(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        BindPipelineCommand_ quorumCommand = (BindPipelineCommand_)command;
        VulkanPipeline pluginPipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline)quorumCommand.GetPipeline()).plugin_;

        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindPipeline(pluginBuffer.GetCommandBuffer(), quorumCommand.GetBindPoint(), pluginPipeline.GetPipelineHandle());
    }

    public void RecordBindVertexBuffer(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        BindVertexBufferCommand_ quorumCommand = (BindVertexBufferCommand_)command;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetBuffer()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

            LongBuffer vertexBuffers = stack.mallocLong(1);
            vertexBuffers.put(0, pluginBuffer.GetBufferHandle());
            LongBuffer offsets = stack.mallocLong(1);
            offsets.put(0, quorumCommand.GetOffset());

            vkCmdBindVertexBuffers(pluginCommandBuffer.GetCommandBuffer(), quorumCommand.GetFirstBinding(), vertexBuffers, offsets);
        }
    }

    public void RecordComputeShaderDispatch(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        quorum.Libraries.Game.Graphics.Vulkan.Commands.ComputeShaderDispatchCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.ComputeShaderDispatchCommand_) command;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        int x = quorumCommand.GetX();
        int y = quorumCommand.GetY();
        int z = quorumCommand.GetZ();
        vkCmdDispatch(pluginBuffer.GetCommandBuffer(), x, y, z);
    }

    public void RecordCopyBuffer(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        CopyBufferCommand_ quorumCommand = (CopyBufferCommand_)command;
        VulkanBuffer pluginSourceBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetSourceBuffer()).plugin_;
        VulkanBuffer pluginDestinationBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetDestinationBuffer()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkBufferCopy.Buffer copyRegion = VkBufferCopy.calloc(1, stack);
            copyRegion.srcOffset(quorumCommand.GetSourceOffset());
            copyRegion.dstOffset(quorumCommand.GetDestinationOffset());
            copyRegion.size(quorumCommand.GetSize());

            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer) quorumBuffer).plugin_;
            vkCmdCopyBuffer(pluginCommandBuffer.GetCommandBuffer(), pluginSourceBuffer.GetBufferHandle(), pluginDestinationBuffer.GetBufferHandle(), copyRegion);
        }
    }

    public void RecordCopyBufferToImage(Object command, VulkanCommandBuffer_ quorumCommandBuffer)
    {
        CopyBufferToImageCommand_ quorumCommand = (CopyBufferToImageCommand_)command;
        VulkanBuffer pluginSourceBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetSourceBuffer()).plugin_;
        VulkanImage pluginDestinationImage = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumCommand.GetDestinationImage()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkBufferImageCopy.Buffer copyInfo = VkBufferImageCopy.calloc(1, stack);
            copyInfo.bufferOffset(quorumCommand.GetBufferOffset());
            copyInfo.bufferRowLength(quorumCommand.GetBufferRowLength());
            copyInfo.bufferImageHeight(quorumCommand.GetBufferImageHeight());
            copyInfo.imageSubresource().aspectMask(quorumCommand.GetAspectMask());
            copyInfo.imageSubresource().mipLevel(quorumCommand.GetMipLevel());
            copyInfo.imageSubresource().baseArrayLayer(quorumCommand.GetBaseArrayLayer());
            copyInfo.imageSubresource().layerCount(quorumCommand.GetLayerCount());
            copyInfo.imageOffset(VkOffset3D.calloc(stack).set(quorumCommand.GetImageOffsetX(), quorumCommand.GetImageOffsetY(), quorumCommand.GetImageOffsetZ()));
            copyInfo.imageExtent(VkExtent3D.calloc(stack).set(quorumCommand.GetImageWidth(), quorumCommand.GetImageHeight(), quorumCommand.GetImageDepth()));


            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer) quorumCommandBuffer).plugin_;
            vkCmdCopyBufferToImage(pluginCommandBuffer.GetCommandBuffer(), pluginSourceBuffer.GetBufferHandle(), pluginDestinationImage.GetVulkanImageHandle(),
                    VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL, copyInfo);
        }
    }

    public void RecordDraw(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        DrawCommand_ quorumCommand = (DrawCommand_)command;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        vkCmdDraw(pluginBuffer.GetCommandBuffer(), quorumCommand.GetVertexCount(), quorumCommand.GetInstanceCount(),
                quorumCommand.GetFirstVertex(), quorumCommand.GetFirstInstance());
    }

    public void RecordDrawIndexed(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        DrawIndexedCommand_ quorumCommand = (DrawIndexedCommand_)command;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        vkCmdDrawIndexed(pluginBuffer.GetCommandBuffer(), quorumCommand.GetIndexCount(), quorumCommand.GetInstanceCount(),
                quorumCommand.GetFirstIndex(), quorumCommand.GetVertexOffset(), quorumCommand.GetFirstInstance());
    }

    public void RecordDrawIndirect(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        DrawIndirectCommand_ quorumCommand = (DrawIndirectCommand_)command;
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        VulkanBuffer pluginDataBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetDrawCommands()).plugin_;

        vkCmdDrawIndirect(pluginCommandBuffer.GetCommandBuffer(), pluginDataBuffer.GetBufferHandle(),
                quorumCommand.GetOffset(), quorumCommand.GetDrawCount(), quorumCommand.GetStride());
    }

    public int GetDefaultIndirectStride()
    {
        return VkDrawIndirectCommand.SIZEOF;
    }

    public void RecordEndRenderPass(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdEndRenderPass(pluginBuffer.GetCommandBuffer());
    }

    public void RecordPipelineBarrier(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        PipelineBarrierCommand_ quorumCommand = (PipelineBarrierCommand_)command;

        // If the barrier is an ImageMemoryBarrier, handle it here.
        if (quorumCommand.GetMemoryBarrier() instanceof VulkanImageMemoryBarrier_)
        {
            VulkanImageMemoryBarrier_ quorumBarrier = (VulkanImageMemoryBarrier_)quorumCommand.GetMemoryBarrier();
            VulkanImage pluginImage = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumBarrier.GetImage()).plugin_;

            try (MemoryStack stack = MemoryStack.stackPush())
            {
                VkImageMemoryBarrier.Buffer imageBarrier = VkImageMemoryBarrier.calloc(1, stack);
                imageBarrier.sType$Default();
                imageBarrier.srcAccessMask(quorumBarrier.GetSourceAccessMask());
                imageBarrier.dstAccessMask(quorumBarrier.GetDestinationAccessMask());
                imageBarrier.oldLayout(quorumBarrier.GetOldLayout());
                imageBarrier.newLayout(quorumBarrier.GetNewLayout());
                imageBarrier.srcQueueFamilyIndex(quorumBarrier.GetSourceQueueFamilyIndex());
                imageBarrier.dstQueueFamilyIndex(quorumBarrier.GetDestinationQueueFamilyIndex());
                imageBarrier.subresourceRange().aspectMask(quorumBarrier.GetAspectMask());
                imageBarrier.subresourceRange().baseMipLevel(quorumBarrier.GetBaseMipLevel());
                imageBarrier.subresourceRange().levelCount(quorumBarrier.GetLevelCount());
                imageBarrier.subresourceRange().baseArrayLayer(quorumBarrier.GetBaseArrayLayer());
                imageBarrier.subresourceRange().layerCount(quorumBarrier.GetLayerCount());
                imageBarrier.image(pluginImage.GetVulkanImageHandle());

                vkCmdPipelineBarrier(pluginBuffer.GetCommandBuffer(), quorumBarrier.GetSourceStageMask(), quorumBarrier.GetDestinationStageMask(),
                        0, null, null, imageBarrier);
            }
        }
        // If we don't hit a more specific kind of barrier, treat it as a standard MemoryBarrier.
        else
        {
            VulkanMemoryBarrier_ quorumBarrier = quorumCommand.GetMemoryBarrier();

            try (MemoryStack stack = MemoryStack.stackPush())
            {
                VkMemoryBarrier.Buffer memoryBarrier = VkMemoryBarrier.calloc(1, stack);
                memoryBarrier.sType$Default();
                memoryBarrier.srcAccessMask(quorumBarrier.GetSourceAccessMask());
                memoryBarrier.dstAccessMask(quorumBarrier.GetDestinationAccessMask());

                vkCmdPipelineBarrier(pluginBuffer.GetCommandBuffer(), quorumBarrier.GetSourceStageMask(), quorumBarrier.GetDestinationStageMask(),
                        0, memoryBarrier, null, null);
            }
        }
    }

    public void RecordSetPushConstants(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        SetPushConstantsCommand_ quorumCommand = (SetPushConstantsCommand_)command;
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        VulkanPipelineLayout pipelineLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_;
        ByteBuffer data = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantBuffer)quorumCommand.GetData()).plugin_.GetByteBuffer();
        data.position(0);
        data.limit(data.capacity());

        vkCmdPushConstants(pluginCommandBuffer.GetCommandBuffer(), pipelineLayout.GetLayoutHandle(),
                quorumCommand.GetStageFlags(), quorumCommand.GetOffset(), data);
    }

    public void RecordSetScissor(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        SetScissorCommand_ quorumCommand = (SetScissorCommand_)command;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkRect2D.Buffer scissorRegion = VkRect2D.calloc(1, stack);
            scissorRegion.extent(rect -> rect.width(quorumCommand.GetWidth()).height(quorumCommand.GetHeight()));
            scissorRegion.offset(rect -> rect.x(quorumCommand.GetX()).y(quorumCommand.GetY()));
            vkCmdSetScissor(pluginBuffer.GetCommandBuffer(), 0, scissorRegion);
        }
    }

    public void RecordSetViewport(Object command, VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        SetViewportCommand_ quorumCommand = (SetViewportCommand_)command;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkViewport.Buffer viewport = VkViewport.calloc(1, stack);
            viewport.x(quorumCommand.GetX());
            viewport.y(quorumCommand.GetY());
            viewport.height(quorumCommand.GetHeight());
            viewport.width(quorumCommand.GetWidth());
            viewport.minDepth((float)quorumCommand.GetMinimumDepth());
            viewport.maxDepth((float)quorumCommand.GetMaximumDepth());

            vkCmdSetViewport(pluginBuffer.GetCommandBuffer(), 0, viewport);
        }
    }
}
