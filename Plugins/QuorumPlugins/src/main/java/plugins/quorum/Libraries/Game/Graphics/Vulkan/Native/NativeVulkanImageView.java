package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanImageViewStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageViewInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_;

public class NativeVulkanImageView implements VulkanImageViewStrategy
{
    private final VulkanImageView peer;
    private long imageViewHandle = 0L;

    public NativeVulkanImageView(VulkanImageView peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImage_ quorumImage, VulkanImageViewInfo_ info)
    {
        long image = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)quorumImage).plugin_.GetVulkanImageHandle();
        imageViewHandle = CreateImageView(NativeVulkan.DeviceHandle(quorumDevice), image, info.GetViewType(), info.GetFormat(),
                info.GetAspectMask(), info.GetBaseMipLevel(), info.GetLevelCount(), info.GetBaseArrayLayer(), info.GetLayerCount());
        return imageViewHandle != 0L;
    }

    public long GetVulkanImageViewHandle()
    {
        return imageViewHandle;
    }

    public void Dispose()
    {
        if (imageViewHandle == 0L)
            return;

        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView_)peer.me_).Get_Libraries_Game_Graphics_Vulkan_VulkanImageView__device_();
        DestroyImageView(NativeVulkan.DeviceHandle(quorumDevice), imageViewHandle);
        imageViewHandle = 0L;
    }

    private static native long CreateImageView(long device, long image, int viewType, int format, int aspectMask, int baseMipLevel,
            int levelCount, int baseArrayLayer, int layerCount);
    private static native void DestroyImageView(long device, long imageView);
}
