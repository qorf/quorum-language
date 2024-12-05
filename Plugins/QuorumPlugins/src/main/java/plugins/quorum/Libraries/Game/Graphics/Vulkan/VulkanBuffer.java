package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.vma.VmaAllocationCreateInfo;
import org.lwjgl.util.vma.VmaAllocatorCreateInfo;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.util.vma.Vma.*;
import static org.lwjgl.vulkan.VK10.*;

public class VulkanBuffer
{
    public Object me_;

    // A handle to the VulkanBuffer's internal data structure.
    private long bufferHandle = 0L;

    // A handle to the chunk of allocated memory for this buffer. Used to find a region to map memory to.
    private long memoryHandle = 0L;

    // A pointer to the actual, raw memory after mapping has been performed.
    private long mappedMemoryPointer = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, int size, int bufferUsage, int memoryUsage,
                                int allocationFlags, int requiredMemoryFlags, int preferredMemoryFlags, int sharingMode)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanPhysicalDevice pluginPhysicalDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumDevice.GetPhysicalDevice()).plugin_;
        VulkanMemoryAllocator pluginMemoryAllocator = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator)quorumDevice.GetMemoryAllocator()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkBufferCreateInfo bufferCreateInfo = VkBufferCreateInfo.calloc(stack);
            bufferCreateInfo.sType$Default();
            bufferCreateInfo.size(size);
            bufferCreateInfo.usage(bufferUsage);
            bufferCreateInfo.sharingMode(sharingMode);

            VmaAllocationCreateInfo allocationCreateInfo = VmaAllocationCreateInfo.calloc(stack);
            if (memoryUsage == 0)
                allocationCreateInfo.usage(VMA_MEMORY_USAGE_AUTO);
            else
                allocationCreateInfo.usage(memoryUsage);

            if (allocationFlags != 0)
                allocationCreateInfo.flags(allocationFlags);
            if (requiredMemoryFlags != 0)
                allocationCreateInfo.requiredFlags(requiredMemoryFlags);
            if (preferredMemoryFlags != 0)
                allocationCreateInfo.preferredFlags(preferredMemoryFlags);

            PointerBuffer pointerBuffer = stack.callocPointer(1);
            LongBuffer handleBuffer = stack.callocLong(1);

            int vulkanResult = vmaCreateBuffer(pluginMemoryAllocator.GetAllocatorPointer(), bufferCreateInfo, allocationCreateInfo,
                    handleBuffer, pointerBuffer, null);

            if (vulkanResult != VK_SUCCESS)
                return false;

            bufferHandle = handleBuffer.get(0);
            memoryHandle = pointerBuffer.get(0);
        }

        return true;
    }

    /*
    Commented out below is the original implementation for CreateNative. This version is obsolete with
    the introduction of the VulkanMemoryAllocator (VMA) library, but the code is kept here for posterity while the
    system is being developed.
    -------------------------
    public boolean CreateNative(VulkanDevice_ quorumDevice, int size, int usageFlags, int memoryFlags, int sharingMode)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanPhysicalDevice pluginPhysicalDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumDevice.GetPhysicalDevice()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // Start by making the descriptor for the buffer. This won't allocate memory yet, but gives us a handle to the object.
            VkBufferCreateInfo bufferCreateInfo = VkBufferCreateInfo.calloc(stack);
            bufferCreateInfo.sType$Default();
            bufferCreateInfo.size(size);
            bufferCreateInfo.usage(usageFlags);
            bufferCreateInfo.sharingMode(sharingMode);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateBuffer(pluginDevice.GetDevice(), bufferCreateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            bufferHandle = handleBuffer.get(0);


            // Determine where the memory will be reserved, out of the different resources made available by the physical device.
            VkMemoryRequirements memoryRequirements = VkMemoryRequirements.calloc(stack);
            vkGetBufferMemoryRequirements(pluginDevice.GetDevice(), bufferHandle, memoryRequirements);

            VkMemoryAllocateInfo memoryAllocateInfo = VkMemoryAllocateInfo.calloc(stack);
            memoryAllocateInfo.sType$Default();
            memoryAllocateInfo.allocationSize(memoryRequirements.size());
            // Use our helper to figure out which index is associated with our preferred memory type. The helper contains
            // more details on what this is and why it's needed.
            memoryAllocateInfo.memoryTypeIndex(memoryTypeFromProperties(pluginPhysicalDevice.GetDevice(), memoryRequirements.memoryTypeBits(), memoryFlags));

            vulkanResult = vkAllocateMemory(pluginDevice.GetDevice(), memoryAllocateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            memoryHandle = handleBuffer.get(0);

            // Now that we've created both the buffer data structure and allocated a chunk of memory, associate the two with each other.
            vulkanResult = vkBindBufferMemory(pluginDevice.GetDevice(), bufferHandle, memoryHandle, 0);
            if (vulkanResult != VK_SUCCESS)
                return false;
        }

        return true;
    }
    */


    /*
    The physical device has several different ways it can organize memory. The way that this is exposed to Vulkan is
    as a sort of array of options, where we have to select the right index. Each index supports a different set of
    properties, as described by the property flags for each of the memory types. We need to check each index for one
    that supports all of the properties that we need.

    This part is frankly pretty annoying. There's extra details here:
    https://registry.khronos.org/vulkan/specs/1.3-extensions/man/html/VkPhysicalDeviceMemoryProperties.html
     */
    public static int memoryTypeFromProperties(VkPhysicalDevice physicalDevice, int typeBits, int reqsMask)
    {
        int result = -1;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPhysicalDeviceMemoryProperties memoryProperties = VkPhysicalDeviceMemoryProperties.calloc(stack);
            vkGetPhysicalDeviceMemoryProperties(physicalDevice, memoryProperties);

            VkMemoryType.Buffer memoryTypes = memoryProperties.memoryTypes();
            for (int i = 0; i < VK_MAX_MEMORY_TYPES; i++) {
                if ((typeBits & 1) == 1 && (memoryTypes.get(i).propertyFlags() & reqsMask) == reqsMask) {
                    result = i;
                    break;
                }
                typeBits >>= 1;
            }
            if (result < 0) {
                throw new RuntimeException("Failed to find memoryType");
            }
            return result;
        }
    }

    public boolean IsMapping()
    {
        return mappedMemoryPointer != 0L;
    }

    public boolean BeginMapping()
    {
        if (IsMapping())
            return true;

        VulkanBuffer_ quorumBuffer = (VulkanBuffer_)me_;
        VulkanDevice_ quorumDevice = quorumBuffer.GetDevice();
        VulkanMemoryAllocator pluginMemoryAllocator = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator)quorumDevice.GetMemoryAllocator()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            PointerBuffer pointerBuffer = stack.mallocPointer(1);
            int vulkanResult = vmaMapMemory(pluginMemoryAllocator.GetAllocatorPointer(), memoryHandle, pointerBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            mappedMemoryPointer = pointerBuffer.get(0);
        }

        return true;
    }

    public void StopMapping()
    {
        if (!IsMapping())
            return;

        VulkanBuffer_ quorumBuffer = (VulkanBuffer_)me_;
        VulkanDevice_ quorumDevice = quorumBuffer.GetDevice();
        VulkanMemoryAllocator pluginMemoryAllocator = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator)quorumDevice.GetMemoryAllocator()).plugin_;

        vmaUnmapMemory(pluginMemoryAllocator.GetAllocatorPointer(), mappedMemoryPointer);
    }

    public long GetMappedMemoryPointer()
    {
        return mappedMemoryPointer;
    }
}
