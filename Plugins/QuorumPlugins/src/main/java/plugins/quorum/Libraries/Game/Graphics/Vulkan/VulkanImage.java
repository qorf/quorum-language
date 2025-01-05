package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.vma.VmaAllocationCreateInfo;
import org.lwjgl.vulkan.VkImageCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageInfo_;

import java.nio.LongBuffer;

import static org.lwjgl.util.vma.Vma.*;
import static org.lwjgl.vulkan.VK10.*;

public class VulkanImage
{
    public Object me_;

    private long vulkanImageHandle = 0L;

    /*
    A handle to the memory backing this image. Only present if this image was allocated via the Vulkan Memory Allocator,
    using the CreateNative function. If this image was generated elsewhere then wrapped (e.g. an image from the
    swapchain) then this will remain 0.
    */
    private long vulkanMemoryHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImageInfo_ imageInfo)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkImageCreateInfo createInfo = VkImageCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.extent().set(imageInfo.GetWidth(), imageInfo.GetHeight(), imageInfo.GetDepth());
            createInfo.imageType(imageInfo.GetImageType());
            createInfo.format(imageInfo.GetFormat());
            createInfo.mipLevels(imageInfo.GetMipLevels());
            createInfo.arrayLayers(imageInfo.GetArrayLayers());
            createInfo.samples(imageInfo.GetSamples());
            createInfo.tiling(imageInfo.GetTiling());
            createInfo.usage(imageInfo.GetUsage());
            createInfo.sharingMode(imageInfo.GetSharingMode());
            if (imageInfo.GetFlags() != 0)
                createInfo.flags(imageInfo.GetFlags());

//            LongBuffer handleBuffer = stack.mallocLong(1);
//
//            int vulkanResult = vkCreateImage(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
//            if (vulkanResult != VK_SUCCESS)
//                return false;
//
//            vulkanImageHandle = handleBuffer.get(0);

            VmaAllocationCreateInfo allocationCreateInfo = VmaAllocationCreateInfo.calloc(stack);
            allocationCreateInfo.usage(imageInfo.GetMemoryUsage());

            if (imageInfo.GetAllocationFlags() != 0)
                allocationCreateInfo.flags(imageInfo.GetAllocationFlags());
            if (imageInfo.GetRequiredMemoryFlags() != 0)
                allocationCreateInfo.requiredFlags(imageInfo.GetRequiredMemoryFlags());
            if (imageInfo.GetPreferredMemoryFlags() != 0)
                allocationCreateInfo.preferredFlags(imageInfo.GetPreferredMemoryFlags());

            VulkanMemoryAllocator pluginMemoryAllocator = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator)quorumDevice.GetMemoryAllocator()).plugin_;
            LongBuffer handleBuffer = stack.mallocLong(1);
            PointerBuffer pointerBuffer = stack.callocPointer(1);

            int vulkanResult = vmaCreateImage(pluginMemoryAllocator.GetAllocatorPointer(), createInfo, allocationCreateInfo, handleBuffer, pointerBuffer, null);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanImageHandle = handleBuffer.get(0);
            vulkanMemoryHandle = pointerBuffer.get(0);
        }

        return true;
    }

    /*
    Used at the plugin level to wrap a handle for a Vulkan image that was generated elsewhere, such as the images
    provided by the system for the swapchain.
    */
    public void LoadFromHandle(long handle, VulkanDevice_ device, int width, int height)
    {
        vulkanImageHandle = handle;

        quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_ quorumImage = (quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_)me_;
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__device_(device);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__initialized_(true);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__width_(width);
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__height_(height);
    }

    public long GetVulkanImageHandle()
    {
        return vulkanImageHandle;
    }


}
