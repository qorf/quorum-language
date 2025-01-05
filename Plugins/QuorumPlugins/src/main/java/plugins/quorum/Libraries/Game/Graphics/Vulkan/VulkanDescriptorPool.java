package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDescriptorPoolCreateInfo;
import org.lwjgl.vulkan.VkDescriptorPoolSize;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateDescriptorPool;

public class VulkanDescriptorPool
{
    public Object me_;

    private long poolHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, int descriptorType, int descriptorCount, int maxSets)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDescriptorPoolSize.Buffer poolSizes = VkDescriptorPoolSize.calloc(1, stack);
            VkDescriptorPoolSize poolSize = poolSizes.get(0);
            poolSize.type(descriptorType);
            poolSize.descriptorCount(descriptorCount);

            VkDescriptorPoolCreateInfo createInfo = VkDescriptorPoolCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pPoolSizes(poolSizes);
            createInfo.maxSets(maxSets);

            LongBuffer handleBuffer = stack.callocLong(1);

            int vulkanResult = vkCreateDescriptorPool(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            poolHandle = handleBuffer.get(0);
        }

        return true;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, Array_ descriptorCounts, int maxSets)
    {
        if (descriptorTypes.GetSize() != descriptorCounts.GetSize())
            return false;

        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDescriptorPoolSize.Buffer poolSizes = VkDescriptorPoolSize.calloc(descriptorTypes.GetSize(), stack);
            for (int i = 0; i < descriptorTypes.GetSize(); i++)
            {
                // Cast Quorum array items to Integer_, get their values, store as Java ints.
                int descriptorType = (int)((quorum.Libraries.Language.Types.Integer_)descriptorTypes.Get(i)).GetValue();
                int descriptorCount = (int)((quorum.Libraries.Language.Types.Integer_)descriptorCounts.Get(i)).GetValue();

                VkDescriptorPoolSize poolSize = poolSizes.get(i);
                poolSize.type(descriptorType);
                poolSize.descriptorCount(descriptorCount);
            }

            VkDescriptorPoolCreateInfo createInfo = VkDescriptorPoolCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pPoolSizes(poolSizes);
            createInfo.maxSets(maxSets);

            LongBuffer handleBuffer = stack.callocLong(1);

            int vulkanResult = vkCreateDescriptorPool(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            poolHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetPoolHandle()
    {
        return poolHandle;
    }
}
