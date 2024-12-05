package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.vma.VmaAllocatorCreateInfo;
import org.lwjgl.util.vma.VmaVulkanFunctions;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

import static org.lwjgl.util.vma.Vma.vmaCreateAllocator;
import static org.lwjgl.util.vma.Vma.vmaDestroyAllocator;
import static org.lwjgl.vulkan.VK10.VK_SUCCESS;

public class VulkanMemoryAllocator
{
    public Object me_;

    long allocatorPointer = 0L;

    public boolean CreateNative(VulkanInstance_ quorumInstance, VulkanDevice_ quorumDevice)
    {
        VulkanInstance pluginInstance = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance)quorumInstance).plugin_;
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanPhysicalDevice pluginPhysicalDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumDevice.GetPhysicalDevice()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            PointerBuffer pointerBuffer = stack.mallocPointer(1);

            // Create the interface the VMA will use for accessing Vulkan functions through our structures.
            VmaVulkanFunctions vulkanFunctions = VmaVulkanFunctions.calloc(stack);
            vulkanFunctions.set(pluginInstance.GetInstance(), pluginDevice.GetDevice());

            VmaAllocatorCreateInfo createInfo = VmaAllocatorCreateInfo.calloc(stack);
            createInfo.instance(pluginInstance.GetInstance());
            createInfo.device(pluginDevice.GetDevice());
            createInfo.physicalDevice(pluginPhysicalDevice.GetDevice());
            createInfo.pVulkanFunctions(vulkanFunctions);

            int vulkanResult = vmaCreateAllocator(createInfo, pointerBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            allocatorPointer = pointerBuffer.get(0);
        }

        return true;
    }

    public void DisposeNative()
    {
        vmaDestroyAllocator(allocatorPointer);
        allocatorPointer = 0L;
    }

    public long GetAllocatorPointer()
    {
        return allocatorPointer;
    }
}
