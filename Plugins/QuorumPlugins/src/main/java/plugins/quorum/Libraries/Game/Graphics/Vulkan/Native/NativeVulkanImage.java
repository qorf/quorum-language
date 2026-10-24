package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanImage;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanImageStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageInfo_;

public class NativeVulkanImage implements VulkanImageStrategy
{
    private final VulkanImage peer;
    private long imageHandle = 0L;

    // The VMA allocation behind the image, or 0 for an image made elsewhere
    // (the swapchain's), which isn't ours to free.
    private long memoryHandle = 0L;

    public NativeVulkanImage(VulkanImage peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImageInfo_ imageInfo)
    {
        int[] values = new int[] {
            imageInfo.GetWidth(), imageInfo.GetHeight(), imageInfo.GetDepth(), imageInfo.GetImageType(), imageInfo.GetFormat(),
            imageInfo.GetMipLevels(), imageInfo.GetArrayLayers(), imageInfo.GetSamples(), imageInfo.GetTiling(), imageInfo.GetUsage(),
            imageInfo.GetSharingMode(), imageInfo.GetFlags(), imageInfo.GetMemoryUsage(), imageInfo.GetAllocationFlags(),
            imageInfo.GetRequiredMemoryFlags(), imageInfo.GetPreferredMemoryFlags()
        };
        long[] results = new long[2];
        if (!CreateImage(NativeVulkan.AllocatorHandle(quorumDevice), values, results))
            return false;

        imageHandle = results[0];
        memoryHandle = results[1];
        return true;
    }

    public void LoadFromHandle(long handle, VulkanDevice_ device, int width, int height)
    {
        imageHandle = handle;

        quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_ quorumImage = (quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_)peer.me_;
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__device_(device);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__initialized_(true);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__width_(width);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__height_(height);
    }

    public long GetVulkanImageHandle()
    {
        return imageHandle;
    }

    public void Dispose()
    {
        if (imageHandle == 0L || memoryHandle == 0L)
            return;

        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_)peer.me_).GetDevice();
        DestroyImage(NativeVulkan.AllocatorHandle(quorumDevice), imageHandle, memoryHandle);
        imageHandle = 0L;
        memoryHandle = 0L;
    }

    // values: width, height, depth, image type, format, mip levels, array
    // layers, samples, tiling, usage, sharing mode, flags, then VMA's memory
    // usage, allocation flags, required and preferred memory flags. results:
    // the image and its allocation.
    private static native boolean CreateImage(long allocator, int[] values, long[] results);
    private static native void DestroyImage(long allocator, long image, long allocation);
}
