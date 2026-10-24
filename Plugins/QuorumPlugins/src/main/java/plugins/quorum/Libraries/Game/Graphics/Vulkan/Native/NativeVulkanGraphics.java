package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanGraphics;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanGraphicsStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_;

// Takes the next swapchain image, and shows it, on iOS and Android.
public class NativeVulkanGraphics implements VulkanGraphicsStrategy
{
    private static final int SUCCESS = 0;
    private static final int SUBOPTIMAL = 1000001003;
    private static final int OUT_OF_DATE = -1000001004;

    private int currentSwapchainIndex = 0;

    public NativeVulkanGraphics(VulkanGraphics peer)
    {
    }

    public int PrepareNextSwapchainImage(VulkanDevice_ quorumDevice, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore)
    {
        long swapchain = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain)quorumSwapchain).plugin_.GetVulkanSwapchainHandle();
        long semaphore = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore)quorumSemaphore).plugin_.GetSemaphoreHandle();
        int[] index = new int[1];
        int vulkanResult = AcquireImage(NativeVulkan.DeviceHandle(quorumDevice), swapchain, semaphore, index);

        if (vulkanResult == SUCCESS || vulkanResult == SUBOPTIMAL || vulkanResult == OUT_OF_DATE)
        {
            currentSwapchainIndex = index[0];

            // Android says "suboptimal" whenever the phone is turned from its
            // natural orientation, since the swapchain isn't pre-rotated
            // (NativeVulkanSwapchain). Remaking it wouldn't change that, so
            // it's treated as success; "out of date" still remakes it.
            if (vulkanResult == SUBOPTIMAL && VulkanPlatform.IsAndroid())
                return SUCCESS;
            return vulkanResult;
        }
        throw new RuntimeException("Could not acquire the next swapchain image! Error code: " + vulkanResult);
    }

    public int GetCurrentSwapchainIndexNative()
    {
        return currentSwapchainIndex;
    }

    public boolean DisplayCurrentImageNative(VulkanQueue_ quorumQueue, VulkanSwapchain_ quorumSwapchain, VulkanSemaphore_ quorumSemaphore, int imageIndex)
    {
        long swapchain = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain)quorumSwapchain).plugin_.GetVulkanSwapchainHandle();
        long semaphore = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore)quorumSemaphore).plugin_.GetSemaphoreHandle();
        int vulkanResult = Present(NativeVulkanQueue.Handle(quorumQueue), swapchain, semaphore, imageIndex);
        return vulkanResult == SUCCESS || vulkanResult == SUBOPTIMAL;
    }

    private static native int AcquireImage(long device, long swapchain, long semaphore, int[] index);
    private static native int Present(long queue, long swapchain, long waitSemaphore, int imageIndex);
}
