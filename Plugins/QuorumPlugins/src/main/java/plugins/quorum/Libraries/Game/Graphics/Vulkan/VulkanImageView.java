package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkImageViewCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageViewInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateImageView;

public class VulkanImageView
{
    public Object me_;

    private long vulkanImageViewHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImage_ quorumImage, VulkanImageViewInfo_ info)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VkDevice vulkanDevice = pluginDevice.GetDevice();

            VulkanImage pluginImage = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumImage).plugin_;
            long imageHandle = pluginImage.GetVulkanImageHandle();

            VkImageViewCreateInfo viewCreateInfo = VkImageViewCreateInfo.calloc(stack);
            viewCreateInfo.sType$Default();
            viewCreateInfo.image(imageHandle);
            viewCreateInfo.viewType(info.GetViewType());
            viewCreateInfo.format(info.GetFormat());

            // The subresource range describes the parts of an image that this view will access.
            // More info at: https://registry.khronos.org/vulkan/specs/1.3-extensions/man/html/VkImageSubresourceRange.html
            viewCreateInfo.subresourceRange().aspectMask(info.GetAspectMask());
            viewCreateInfo.subresourceRange().baseMipLevel(info.GetBaseMipLevel());
            viewCreateInfo.subresourceRange().levelCount(info.GetLevelCount());
            viewCreateInfo.subresourceRange().baseArrayLayer(info.GetBaseArrayLayer());
            viewCreateInfo.subresourceRange().layerCount(info.GetLayerCount());

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateImageView(vulkanDevice, viewCreateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanImageViewHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetVulkanImageViewHandle()
    {
        return vulkanImageViewHandle;
    }
}
