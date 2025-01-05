package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDescriptorSetLayoutBinding;
import org.lwjgl.vulkan.VkDescriptorSetLayoutCreateInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayoutBinding_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateDescriptorSetLayout;

public class VulkanDescriptorSetLayout
{
    public Object me_;

    private long layoutHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumBindings)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDescriptorSetLayoutCreateInfo createInfo = VkDescriptorSetLayoutCreateInfo.calloc(stack);
            createInfo.sType$Default();

            // Convert the Quorum bindings into Vulkan objects.
            VkDescriptorSetLayoutBinding.Buffer bindings = VkDescriptorSetLayoutBinding.calloc(quorumBindings.GetSize(), stack);
            for (int i = 0; i < quorumBindings.GetSize(); i++)
            {
                VulkanDescriptorSetLayoutBinding_ quorumBinding = (VulkanDescriptorSetLayoutBinding_)quorumBindings.Get(i);
                VkDescriptorSetLayoutBinding vulkanBinding = bindings.get(0);
                vulkanBinding.binding(quorumBinding.GetBinding());
                vulkanBinding.descriptorCount(quorumBinding.GetDescriptorCount());
                vulkanBinding.descriptorType(quorumBinding.GetDescriptorType());
                vulkanBinding.stageFlags(quorumBinding.GetStageFlags());
            }

            createInfo.pBindings(bindings);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateDescriptorSetLayout(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
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
