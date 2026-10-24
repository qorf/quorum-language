package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandPoolStrategy;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkCommandPoolCreateInfo;
import org.lwjgl.vulkan.VkDevice;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateCommandPool;

public class DesktopVulkanCommandPool implements VulkanCommandPoolStrategy
{
    private final VulkanCommandPool peer;

    public DesktopVulkanCommandPool(VulkanCommandPool peer)
    {
        this.peer = peer;
    }

    private long commandPoolHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, int flags, int queueFamilyIndex)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VkDevice vulkanDevice = pluginDevice.GetDevice();

            VkCommandPoolCreateInfo createInfo = VkCommandPoolCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.flags(flags);
            createInfo.queueFamilyIndex(queueFamilyIndex);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateCommandPool(vulkanDevice, createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            commandPoolHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetCommandPoolHandle()
    {
        return commandPoolHandle;
    }
}
