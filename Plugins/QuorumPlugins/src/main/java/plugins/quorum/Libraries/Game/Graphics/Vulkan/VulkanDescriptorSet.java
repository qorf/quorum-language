package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.vulkan.VK12.VK_DESCRIPTOR_BINDING_VARIABLE_DESCRIPTOR_COUNT_BIT;

public class VulkanDescriptorSet
{
    public Object me_;

    private long descriptorSetHandle = 0L;


    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumLayout).plugin_;
        VulkanDescriptorPool pluginPool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool)quorumPool).plugin_;
        VulkanDescriptorSet_ quorumDescriptorSet = (VulkanDescriptorSet_)me_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer descriptorSetLayouts = stack.mallocLong(1);
            descriptorSetLayouts.put(0, pluginLayout.GetLayoutHandle());

            VkDescriptorSetAllocateInfo allocateInfo = VkDescriptorSetAllocateInfo.calloc(stack);
            allocateInfo.sType$Default();
            allocateInfo.descriptorPool(pluginPool.GetPoolHandle());
            allocateInfo.pSetLayouts(descriptorSetLayouts);

            // Check if we need to add variable descriptor count information. According to the spec, only the last
            // (e.g. highest numbered) binding is allowed to have variable size, so just check that.
            Array_ quorumBindings = quorumLayout.GetBindings();
            VulkanDescriptorSetLayoutBinding_ lastBinding = (VulkanDescriptorSetLayoutBinding_)quorumBindings.GetFromEnd();
            if ((lastBinding.GetBindingFlags() | VK_DESCRIPTOR_BINDING_VARIABLE_DESCRIPTOR_COUNT_BIT) != 0)
            {
                // We'll need a special structure to indicate how much space to be allocated for the descriptor of variable size.
                VkDescriptorSetVariableDescriptorCountAllocateInfo variableCountInfo = VkDescriptorSetVariableDescriptorCountAllocateInfo.calloc(stack);
                variableCountInfo.sType$Default();
                variableCountInfo.pDescriptorCounts(stack.callocInt(lastBinding.GetDescriptorCount()));
                allocateInfo.pNext(variableCountInfo);
            }

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkAllocateDescriptorSets(pluginDevice.GetDevice(), allocateInfo, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
            {
                quorumDescriptorSet.Set_Libraries_Game_Graphics_Vulkan_VulkanDescriptorSet__failureCode_(vulkanResult);
                return false;
            }

            descriptorSetHandle = handleBuffer.get(0);
        }

        return true;
    }

    public void LinkResources(VulkanDevice_ quorumDevice, VulkanDescriptorResourcesInfo_ quorumResources)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        Array_ quorumBuffers = quorumResources.GetBufferInfo();
        Array_ quorumImages = quorumResources.GetImageInfo();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkWriteDescriptorSet.Buffer writeDescriptorSets = VkWriteDescriptorSet.calloc(1, stack);
            writeDescriptorSets.get(0).sType$Default();
            writeDescriptorSets.get(0).dstSet(descriptorSetHandle);
            writeDescriptorSets.get(0).dstBinding(quorumResources.GetDestinationBinding());
            writeDescriptorSets.get(0).descriptorType(quorumResources.GetDescriptorType());
            writeDescriptorSets.get(0).descriptorCount(quorumResources.GetDescriptorCount());

            if (quorumBuffers != null && quorumBuffers.IsEmpty() == false)
            {
                VkDescriptorBufferInfo.Buffer bufferInfo = VkDescriptorBufferInfo.calloc(quorumBuffers.GetSize(), stack);
                for (int i = 0; i < quorumBuffers.GetSize(); i++)
                {
                    VulkanDescriptorBufferInfo_ quorumInfo = (VulkanDescriptorBufferInfo_)quorumBuffers.Get(i);
                    VkDescriptorBufferInfo vulkanInfo = bufferInfo.get(i);

                    if (quorumInfo.GetBuffer() != null)
                    {
                        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumInfo.GetBuffer()).plugin_;
                        vulkanInfo.buffer(pluginBuffer.GetBufferHandle());
                    }

                    vulkanInfo.offset(quorumInfo.GetOffset());
                    vulkanInfo.range(quorumInfo.GetRange());
                }
                writeDescriptorSets.get(0).pBufferInfo(bufferInfo);
            }

            if (quorumImages != null && quorumImages.IsEmpty() == false)
            {
                VkDescriptorImageInfo.Buffer imageInfo = VkDescriptorImageInfo.calloc(quorumImages.GetSize(), stack);
                for (int i = 0; i < quorumImages.GetSize(); i++)
                {
                    VulkanDescriptorImageInfo_ quorumInfo = (VulkanDescriptorImageInfo_)quorumImages.Get(i);
                    VkDescriptorImageInfo vulkanInfo = imageInfo.get(i);

                    if (quorumInfo.GetSampler() != null)
                    {
                        VulkanSampler pluginSampler = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler)quorumInfo.GetSampler()).plugin_;
                        vulkanInfo.sampler(pluginSampler.GetSamplerHandle());
                    }

                    if (quorumInfo.GetImageView() != null)
                    {
                        VulkanImageView pluginView = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView)quorumInfo.GetImageView()).plugin_;
                        vulkanInfo.imageView(pluginView.GetVulkanImageViewHandle());
                    }

                    vulkanInfo.imageLayout(quorumInfo.GetImageLayout());
                }
                writeDescriptorSets.get(0).pImageInfo(imageInfo);
            }

            vkUpdateDescriptorSets(pluginDevice.GetDevice(), writeDescriptorSets, null);
        }
    }

    public void LinkResources(VulkanDevice_ quorumDevice, Array_ quorumResourcesArray)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkWriteDescriptorSet.Buffer writeDescriptorSets = VkWriteDescriptorSet.calloc(quorumResourcesArray.GetSize(), stack);
            for (int j = 0; j < quorumResourcesArray.GetSize(); j++)
            {
                VulkanDescriptorResourcesInfo_ quorumResources = (VulkanDescriptorResourcesInfo_)quorumResourcesArray.Get(j);
                Array_ quorumBuffers = quorumResources.GetBufferInfo();
                Array_ quorumImages = quorumResources.GetImageInfo();
                writeDescriptorSets.get(j).sType$Default();
                writeDescriptorSets.get(j).dstSet(descriptorSetHandle);
                writeDescriptorSets.get(j).dstBinding(quorumResources.GetDestinationBinding());
                writeDescriptorSets.get(j).descriptorType(quorumResources.GetDescriptorType());
                writeDescriptorSets.get(j).descriptorCount(quorumResources.GetDescriptorCount());

                if (quorumBuffers != null && quorumBuffers.IsEmpty() == false)
                {
                    VkDescriptorBufferInfo.Buffer bufferInfo = VkDescriptorBufferInfo.calloc(quorumBuffers.GetSize(), stack);
                    for (int i = 0; i < quorumBuffers.GetSize(); i++)
                    {
                        VulkanDescriptorBufferInfo_ quorumInfo = (VulkanDescriptorBufferInfo_) quorumBuffers.Get(i);
                        VkDescriptorBufferInfo vulkanInfo = bufferInfo.get(i);

                        if (quorumInfo.GetBuffer() != null)
                        {
                            VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer) quorumInfo.GetBuffer()).plugin_;
                            vulkanInfo.buffer(pluginBuffer.GetBufferHandle());
                        }

                        vulkanInfo.offset(quorumInfo.GetOffset());
                        vulkanInfo.range(quorumInfo.GetRange());
                    }
                    writeDescriptorSets.get(j).pBufferInfo(bufferInfo);
                }

                if (quorumImages != null && quorumImages.IsEmpty() == false)
                {
                    VkDescriptorImageInfo.Buffer imageInfo = VkDescriptorImageInfo.calloc(quorumImages.GetSize(), stack);
                    for (int i = 0; i < quorumImages.GetSize(); i++)
                    {
                        VulkanDescriptorImageInfo_ quorumInfo = (VulkanDescriptorImageInfo_) quorumImages.Get(i);
                        VkDescriptorImageInfo vulkanInfo = imageInfo.get(i);

                        if (quorumInfo.GetSampler() != null)
                        {
                            VulkanSampler pluginSampler = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler) quorumInfo.GetSampler()).plugin_;
                            vulkanInfo.sampler(pluginSampler.GetSamplerHandle());
                        }

                        if (quorumInfo.GetImageView() != null)
                        {
                            VulkanImageView pluginView = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView) quorumInfo.GetImageView()).plugin_;
                            vulkanInfo.imageView(pluginView.GetVulkanImageViewHandle());
                        }

                        vulkanInfo.imageLayout(quorumInfo.GetImageLayout());
                    }
                    writeDescriptorSets.get(j).pImageInfo(imageInfo);
                }
            }
            vkUpdateDescriptorSets(pluginDevice.GetDevice(), writeDescriptorSets, null);
        }
    }

    public long GetDescriptorSetHandle()
    {
        return descriptorSetHandle;
    }
}
