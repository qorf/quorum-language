package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkPipelineLayoutCreateInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreatePipelineLayout;

public class VulkanPipelineLayout
{
    public Object me_;

    private long layoutHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptorSetLayouts)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPipelineLayoutCreateInfo createInfo = VkPipelineLayoutCreateInfo.calloc(stack);
            createInfo.sType$Default();

            LongBuffer descriptorSetLayoutHandles = stack.callocLong(quorumDescriptorSetLayouts.GetSize());
            for (int i = 0; i < quorumDescriptorSetLayouts.GetSize(); i++)
            {
                VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumDescriptorSetLayouts.Get(i)).plugin_;
                descriptorSetLayoutHandles.put(pluginLayout.GetLayoutHandle());
            }
            descriptorSetLayoutHandles.flip();
            createInfo.pSetLayouts(descriptorSetLayoutHandles);

            LongBuffer handleBuffer = stack.callocLong(1);
            int vulkanResult = vkCreatePipelineLayout(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            layoutHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetLayoutHandle()
    {
        return layoutHandle;
    }
}
