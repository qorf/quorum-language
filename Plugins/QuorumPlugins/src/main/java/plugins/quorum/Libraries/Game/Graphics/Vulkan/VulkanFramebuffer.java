package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkFramebufferCreateInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;

import java.nio.LongBuffer;

public class VulkanFramebuffer
{
    public Object me_;

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

//            long renderPassHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumRenderPass).plugin_.

            VkFramebufferCreateInfo createInfo = VkFramebufferCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pAttachments(imageViewHandles);
            createInfo.width(width);
            createInfo.height(height);
            createInfo.layers(layers);
            createInfo.renderPass();
        }
        if (true) // This isn't actually done yet
            return false;

        return true;
    }
}
