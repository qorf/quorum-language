package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkQueue;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_;

import static org.lwjgl.vulkan.VK10.vkGetDeviceQueue;

public class VulkanQueue
{
    public Object me_;

    VkQueue vulkanQueue;

    public void CreateNative(VulkanDevice_ quorumDevice, VulkanQueueFamily_ queueFamily, int queueIndex)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            PointerBuffer queuePointer = stack.mallocPointer(1);
            VulkanDevice devicePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VkDevice vulkanDevice = devicePlugin.GetDevice();

            int familyIndex = queueFamily.GetQueueFamilyIndex();

            System.out.println("Family index: " + familyIndex + ", queueIndex: " + queueIndex);
            vkGetDeviceQueue(vulkanDevice, familyIndex, queueIndex, queuePointer);
            vulkanQueue = new VkQueue(queuePointer.get(0), vulkanDevice);
        }
    }
}
