package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDependencyInfo;
import org.lwjgl.vulkan.VkImageMemoryBarrier;
import org.lwjgl.vulkan.VkMemoryBarrier;
import org.lwjgl.vulkan.VkMemoryBarrier2;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanImage;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.PipelineBarrierCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetScissorCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageMemoryBarrier_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryBarrier_;

import static org.lwjgl.vulkan.VK10.vkCmdPipelineBarrier;
import static org.lwjgl.vulkan.VK13.vkCmdPipelineBarrier2;


public class PipelineBarrierCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        PipelineBarrierCommand_ quorumCommand = (PipelineBarrierCommand_)me_;

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
}
