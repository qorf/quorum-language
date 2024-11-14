package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.KHRSwapchain;
import org.lwjgl.vulkan.VkPresentInfoKHR;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_;

import java.nio.IntBuffer;

import static org.lwjgl.vulkan.KHRSwapchain.VK_SUBOPTIMAL_KHR;
import static org.lwjgl.vulkan.VK10.VK_SUCCESS;

public class VulkanGraphics
{
    public Object me_;

    public boolean PrepareNextSwapchainImage(VulkanDevice_ quorumDevice, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanSwapchain pluginSwapchain = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain)quorumSwapchain).plugin_;
        VulkanSemaphore pluginSemaphore = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore)quorumSemaphore).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer imageIndexBuffer = stack.mallocInt(1);
            int vulkanResult = KHRSwapchain.vkAcquireNextImageKHR(pluginDevice.GetDevice(), pluginSwapchain.GetVulkanSwapchainHandle(),
                    Long.MAX_VALUE, pluginSemaphore.GetSemaphoreHandle(), 0L, imageIndexBuffer);

            if (vulkanResult == KHRSwapchain.VK_ERROR_OUT_OF_DATE_KHR)
                return false;
            else if (vulkanResult == VK_SUBOPTIMAL_KHR)
                ; // Do nothing. Despite the warning message, the Swapchain is still usable in this state.
            else if (vulkanResult != VK_SUCCESS)
                throw new RuntimeException("Could not acquire the next swapchain image! Error code: " + vulkanResult);

            int imageIndex = imageIndexBuffer.get(0);
        }

        return true;
    }

    public boolean DisplayCurrentImageNative(VulkanQueue_ quorumQueue, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore, int imageIndex)
    {
        VulkanQueue pluginQueue = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue)quorumQueue).plugin_;
        VulkanSwapchain pluginSwapchain = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain)quorumSwapchain).plugin_;
        VulkanSemaphore pluginSemaphore = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore)quorumSemaphore).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPresentInfoKHR presentInfo = VkPresentInfoKHR.calloc(stack);
            presentInfo.sType$Default();
            presentInfo.pWaitSemaphores(stack.longs(pluginSemaphore.GetSemaphoreHandle()));
            presentInfo.swapchainCount(1);
            presentInfo.pSwapchains(stack.longs(pluginSwapchain.GetVulkanSwapchainHandle()));
            presentInfo.pImageIndices(stack.ints(imageIndex));

            int vulkanResult = KHRSwapchain.vkQueuePresentKHR(pluginQueue.GetVulkanQueue(), presentInfo);
            if (vulkanResult != VK_SUCCESS && vulkanResult != VK_SUBOPTIMAL_KHR)
                return false;
        }

        return true;
    }
}
