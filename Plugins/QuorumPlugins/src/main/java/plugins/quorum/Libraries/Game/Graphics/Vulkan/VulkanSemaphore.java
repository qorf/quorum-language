package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkSemaphoreCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanSemaphore
{
    public Object me_;

    private long semaphoreHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VkDevice vulkanDevice = pluginDevice.GetDevice();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkSemaphoreCreateInfo createInfo = VkSemaphoreCreateInfo.calloc(stack);
            createInfo.sType$Default();
            LongBuffer handleBuffer = stack.mallocLong(1);

            int vulkanResult = vkCreateSemaphore(vulkanDevice, createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            semaphoreHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetSemaphoreHandle()
    {
        return semaphoreHandle;
    }

    public void Dispose()
    {
        VulkanDevice_ quorumDevice = ((VulkanSemaphore_)me_).Get_Libraries_Game_Graphics_Vulkan_VulkanSemaphore__device_();
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        vkDestroySemaphore(pluginDevice.GetDevice(), semaphoreHandle, null);
    }
}
