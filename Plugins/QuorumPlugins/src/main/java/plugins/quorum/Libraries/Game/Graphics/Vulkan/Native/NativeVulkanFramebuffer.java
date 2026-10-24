package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanFramebufferStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;

public class NativeVulkanFramebuffer implements VulkanFramebufferStrategy
{
    private final VulkanFramebuffer peer;
    private long framebufferHandle = 0L;

    public NativeVulkanFramebuffer(VulkanFramebuffer peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ imageViewArray, VulkanRenderPass_ quorumRenderPass)
    {
        if (imageViewArray == null || imageViewArray.IsEmpty())
            return false;

        long[] imageViews = new long[imageViewArray.GetSize()];
        int width = 0;
        int height = 0;
        int layers = 0;
        for (int i = 0; i < imageViews.length; i++)
        {
            VulkanImageView_ imageView = (VulkanImageView_)imageViewArray.Get(i);
            width = Math.max(width, imageView.GetImage().GetWidth());
            height = Math.max(height, imageView.GetImage().GetHeight());
            layers = Math.max(layers, imageView.GetInfo().GetLayerCount());
            imageViews[i] = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView)imageView).plugin_.GetVulkanImageViewHandle();
        }

        long renderPass = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumRenderPass).plugin_.GetRenderPassHandle();
        framebufferHandle = CreateFramebuffer(NativeVulkan.DeviceHandle(quorumDevice), renderPass, imageViews, width, height, layers);
        return framebufferHandle != 0L;
    }

    public long GetFramebufferHandle()
    {
        return framebufferHandle;
    }

    public void Dispose()
    {
        if (framebufferHandle == 0L)
            return;

        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer_)peer.me_).Get_Libraries_Game_Graphics_Vulkan_VulkanFramebuffer__device_();
        DestroyFramebuffer(NativeVulkan.DeviceHandle(quorumDevice), framebufferHandle);
        framebufferHandle = 0L;
    }

    private static native long CreateFramebuffer(long device, long renderPass, long[] imageViews, int width, int height, int layers);
    private static native void DestroyFramebuffer(long device, long framebuffer);
}
