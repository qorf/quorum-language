package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkCommandBuffer;
import org.lwjgl.vulkan.VkCommandBufferAllocateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkAllocateCommandBuffers;

public class VulkanCommandBuffer
{
    public Object me_;

    VkCommandBuffer commandBuffer;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanCommandPool_ quorumPool, int poolLevel)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VulkanCommandPool pluginPool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool)quorumPool).plugin_;

            VkCommandBufferAllocateInfo commandBufferAllocateInfo = VkCommandBufferAllocateInfo.calloc(stack);
            commandBufferAllocateInfo.sType$Default();
            commandBufferAllocateInfo.commandPool(pluginPool.GetCommandPoolHandle());
            commandBufferAllocateInfo.level(poolLevel);
            commandBufferAllocateInfo.commandBufferCount(1);

            PointerBuffer pointerBuffer = stack.mallocPointer(1);
            int vulkanResult = vkAllocateCommandBuffers(pluginDevice.GetDevice(), commandBufferAllocateInfo, pointerBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            commandBuffer = new VkCommandBuffer(pointerBuffer.get(0), pluginDevice.GetDevice());
        }

        return true;
    }

    public VkCommandBuffer GetCommandBuffer()
    {
        return commandBuffer;
    }
}
