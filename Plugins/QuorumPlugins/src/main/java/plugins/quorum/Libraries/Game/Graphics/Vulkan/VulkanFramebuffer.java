package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkFramebufferCreateInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateFramebuffer;

public class VulkanFramebuffer
{
    public Object me_;

    private long framebufferHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ imageViewArray, VulkanRenderPass_ quorumRenderPass)
    {
        if (imageViewArray == null || imageViewArray.IsEmpty())
            return false;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer imageViewHandles = stack.mallocLong(imageViewArray.GetSize());
            int width = 0;
            int height = 0;
            int layers = 0;

            for (int i = 0; i < imageViewArray.GetSize(); i++)
            {
                VulkanImageView_ imageView = (VulkanImageView_)imageViewArray.Get(i);
                width = Math.max(width, imageView.GetImage().GetWidth());
                height = Math.max(height, imageView.GetImage().GetHeight());
                layers = Math.max(layers, imageView.GetInfo().GetLayerCount());

                long imageViewHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView)imageView).plugin_.GetVulkanImageViewHandle();

                imageViewHandles.put(i, imageViewHandle);
            }

            long renderPassHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumRenderPass).plugin_.GetRenderPassHandle();

            VkFramebufferCreateInfo createInfo = VkFramebufferCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pAttachments(imageViewHandles);
            createInfo.width(width);
            createInfo.height(height);
            createInfo.layers(layers);
            createInfo.renderPass(renderPassHandle);

            VkDevice vulkanDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_.GetDevice();

            LongBuffer resultHandle = stack.mallocLong(1);
            int vulkanResult = vkCreateFramebuffer(vulkanDevice, createInfo, null, resultHandle);
            if (vulkanResult != VK_SUCCESS)
                return false;

            framebufferHandle = resultHandle.get(0);
        }

        return true;
    }


    public long GetFramebufferHandle() {
        return framebufferHandle;
    }
}
