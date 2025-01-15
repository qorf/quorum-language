package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkBufferImageCopy;
import org.lwjgl.vulkan.VkExtent3D;
import org.lwjgl.vulkan.VkOffset3D;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanImage;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyBufferToImageCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL;
import static org.lwjgl.vulkan.VK10.vkCmdCopyBufferToImage;

public class CopyBufferToImageCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumCommandBuffer)
    {
        CopyBufferToImageCommand_ quorumCommand = (CopyBufferToImageCommand_)me_;
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

            System.out.println("Offset = " + copyInfo.bufferOffset());
            System.out.println("Row Length = " + copyInfo.bufferRowLength());
            System.out.println("Buffer Image Height = " + copyInfo.bufferImageHeight());
            System.out.println("baseArrayLayer = " + copyInfo.imageSubresource().baseArrayLayer());
            System.out.println("layerCount = " + copyInfo.imageSubresource().layerCount());
            System.out.println("extent = " + copyInfo.imageExtent().width() + ", " + copyInfo.imageExtent().height() + ", " + copyInfo.imageExtent().depth());

            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer) quorumCommandBuffer).plugin_;
            vkCmdCopyBufferToImage(pluginCommandBuffer.GetCommandBuffer(), pluginSourceBuffer.GetBufferHandle(), pluginDestinationImage.GetVulkanImageHandle(),
                    VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL, copyInfo);
        }
    }
}
