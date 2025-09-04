package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.KHRSurface;
import org.lwjgl.vulkan.VK10;
import org.lwjgl.vulkan.VkQueueFamilyProperties;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_;

import java.nio.IntBuffer;

import static org.lwjgl.vulkan.VK10.VK_TRUE;

public class VulkanQueueFamily
{
    public Object me_;

    // system action CanPresentToSurface(VulkanSurface surface) returns boolean
    public boolean CanPresentToSurface(VulkanSurface_ quorumSurface)
    {
        quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_ quorumFamily = (quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_)me_;
        quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice quorumDevice = (quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumFamily.GetPhysicalDevice();
        VulkanPhysicalDevice devicePlugin = quorumDevice.plugin_;

        VulkanSurface surfacePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface)quorumSurface).plugin_;

        int familyIndex = quorumFamily.GetQueueFamilyIndex();
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer resultBuffer = stack.mallocInt(1);
            KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR(devicePlugin.GetDevice(), familyIndex, surfacePlugin.GetSurfacePointer(), resultBuffer);

            return resultBuffer.get(0) == VK_TRUE;
        }
    }

    public int GetComputeQueueFamilyIndex(quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice device) {
        int index = -1;
        VulkanPhysicalDevice plugin = device.plugin_;
        VkQueueFamilyProperties.Buffer queueFamilyProps = plugin.GetQueueFamilyProperties();
        int numQueuesFamilies = queueFamilyProps.capacity();
        for (int i = 0; i < numQueuesFamilies; i++) {
            VkQueueFamilyProperties props = queueFamilyProps.get(i);
            boolean computeQueue = (props.queueFlags() & VK10.VK_QUEUE_COMPUTE_BIT) != 0;
            if (computeQueue) {
                index = i;
                break;
            }
        }
        return index;
    }
}
