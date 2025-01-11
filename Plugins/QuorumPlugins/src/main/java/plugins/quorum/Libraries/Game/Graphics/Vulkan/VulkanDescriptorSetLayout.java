package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDescriptorSetLayoutBinding;
import org.lwjgl.vulkan.VkDescriptorSetLayoutBindingFlagsCreateInfo;
import org.lwjgl.vulkan.VkDescriptorSetLayoutCreateInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayoutBinding_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateDescriptorSetLayout;
import static org.lwjgl.vulkan.VK12.VK_DESCRIPTOR_SET_LAYOUT_CREATE_UPDATE_AFTER_BIND_POOL_BIT;

public class VulkanDescriptorSetLayout
{
    public Object me_;

    private long layoutHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumBindings, int layoutFlags)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDescriptorSetLayoutCreateInfo createInfo = VkDescriptorSetLayoutCreateInfo.calloc(stack);
            createInfo.sType$Default();

            if (layoutFlags != 0)
                createInfo.flags(layoutFlags);


            // Convert the Quorum bindings into Vulkan objects.
            VkDescriptorSetLayoutBinding.Buffer bindings = VkDescriptorSetLayoutBinding.calloc(quorumBindings.GetSize(), stack);
            boolean hasBindingFlags = false;
            for (int i = 0; i < quorumBindings.GetSize(); i++)
            {
                VulkanDescriptorSetLayoutBinding_ quorumBinding = (VulkanDescriptorSetLayoutBinding_)quorumBindings.Get(i);
                VkDescriptorSetLayoutBinding vulkanBinding = bindings.get(0);
                vulkanBinding.binding(quorumBinding.GetBinding());

                vulkanBinding.descriptorCount(quorumBinding.GetDescriptorCount());
                vulkanBinding.descriptorType(quorumBinding.GetDescriptorType());
                vulkanBinding.stageFlags(quorumBinding.GetStageFlags());

                if (quorumBinding.GetImmutableSamplers() != null)
                {
                    Array_ immutableSamplers = quorumBinding.GetImmutableSamplers();
                    if (immutableSamplers.IsEmpty() == false)
                    {
                        LongBuffer samplerHandles = stack.mallocLong(immutableSamplers.GetSize());
                        for (int j = 0; j < immutableSamplers.GetSize(); j++)
                        {
                            VulkanSampler pluginSampler = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler)immutableSamplers.Get(j)).plugin_;
                            samplerHandles.put(pluginSampler.GetSamplerHandle());
                        }
                        samplerHandles.flip();

                        vulkanBinding.pImmutableSamplers(samplerHandles);
                    }
                }

                if (quorumBinding.GetBindingFlags() != 0)
                    hasBindingFlags = true;
            }

            createInfo.pBindings(bindings);

            /*
            If we have binding flags, we need an array of structures for the flags that we associate with the info.
            The n-th binding flag structure will associate to the n-th binding.
            The reason this step is done in such a strange way is because binding flags aren't part of the original
            Vulkan spec, but rather part of an extension that was promoted to part of the spec in Vulkan 1.2.
            */
            if (hasBindingFlags)
            {
                VkDescriptorSetLayoutBindingFlagsCreateInfo flagInfo = VkDescriptorSetLayoutBindingFlagsCreateInfo.calloc(stack);
                flagInfo.sType$Default();
                flagInfo.bindingCount(quorumBindings.GetSize());
                IntBuffer flagBuffer = stack.mallocInt(quorumBindings.GetSize());

                for (int i = 0; i < quorumBindings.GetSize(); i++)
                {
                    VulkanDescriptorSetLayoutBinding_ quorumBinding = (VulkanDescriptorSetLayoutBinding_)quorumBindings.Get(i);
                    flagBuffer.put(quorumBinding.GetBindingFlags());
                }
                flagBuffer.flip();
                flagInfo.pBindingFlags(flagBuffer);

                createInfo.pNext(flagInfo);
            }

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
