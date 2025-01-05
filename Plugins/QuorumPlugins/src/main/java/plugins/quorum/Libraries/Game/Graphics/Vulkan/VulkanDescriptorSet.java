package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDescriptorBufferInfo;
import org.lwjgl.vulkan.VkDescriptorSetAllocateInfo;
import org.lwjgl.vulkan.VkWriteDescriptorSet;
import quorum.Libraries.Game.Graphics.Vulkan.*;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanDescriptorSet
{
    public Object me_;

    private long descriptorSetHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout, VulkanBuffer_ quorumBuffer, int binding, int type)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumLayout).plugin_;
        VulkanDescriptorPool pluginPool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool)quorumPool).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        VulkanDescriptorSet_ quorumDescriptorSet = (VulkanDescriptorSet_)me_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer descriptorSetLayouts = stack.mallocLong(1);
            descriptorSetLayouts.put(0, pluginLayout.GetLayoutHandle());

            VkDescriptorSetAllocateInfo allocateInfo = VkDescriptorSetAllocateInfo.calloc(stack);
            allocateInfo.sType$Default();
            allocateInfo.descriptorPool(pluginPool.GetPoolHandle());
            allocateInfo.pSetLayouts(descriptorSetLayouts);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkAllocateDescriptorSets(pluginDevice.GetDevice(), allocateInfo, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
            {
                quorumDescriptorSet.Set_Libraries_Game_Graphics_Vulkan_VulkanDescriptorSet__failureCode_(vulkanResult);
                return false;
            }

            descriptorSetHandle = handleBuffer.get(0);

            VkDescriptorBufferInfo.Buffer bufferInfo = VkDescriptorBufferInfo.calloc(1, stack);
            bufferInfo.get(0).buffer(pluginBuffer.GetBufferHandle());
            bufferInfo.get(0).offset(0);
            bufferInfo.get(0).range(quorumBuffer.GetSize());

            VkWriteDescriptorSet.Buffer writeDescriptorSets = VkWriteDescriptorSet.calloc(1, stack);
            writeDescriptorSets.get(0).sType$Default();
            writeDescriptorSets.get(0).dstSet(descriptorSetHandle);
            writeDescriptorSets.get(0).dstBinding(binding);
            writeDescriptorSets.get(0).descriptorType(type);
            writeDescriptorSets.get(0).descriptorCount(1);
            writeDescriptorSets.get(0).pBufferInfo(bufferInfo);

            vkUpdateDescriptorSets(pluginDevice.GetDevice(), writeDescriptorSets, null);
        }

        return true;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout, VulkanDescriptorBufferInfo_ quorumBufferInfo)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumLayout).plugin_;
        VulkanDescriptorPool pluginPool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool)quorumPool).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBufferInfo.GetBuffer()).plugin_;
        VulkanDescriptorSet_ quorumDescriptorSet = (VulkanDescriptorSet_)me_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer descriptorSetLayouts = stack.mallocLong(1);
            descriptorSetLayouts.put(0, pluginLayout.GetLayoutHandle());

            VkDescriptorSetAllocateInfo allocateInfo = VkDescriptorSetAllocateInfo.calloc(stack);
            allocateInfo.sType$Default();
            allocateInfo.descriptorPool(pluginPool.GetPoolHandle());
            allocateInfo.pSetLayouts(descriptorSetLayouts);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkAllocateDescriptorSets(pluginDevice.GetDevice(), allocateInfo, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
            {
                quorumDescriptorSet.Set_Libraries_Game_Graphics_Vulkan_VulkanDescriptorSet__failureCode_(vulkanResult);
                return false;
            }

            descriptorSetHandle = handleBuffer.get(0);

            VkDescriptorBufferInfo.Buffer bufferInfo = VkDescriptorBufferInfo.calloc(1, stack);
            bufferInfo.get(0).buffer(pluginBuffer.GetBufferHandle());
            bufferInfo.get(0).offset(quorumBufferInfo.GetBufferOffset());
            bufferInfo.get(0).range(quorumBufferInfo.GetBufferRange());

            VkWriteDescriptorSet.Buffer writeDescriptorSets = VkWriteDescriptorSet.calloc(1, stack);
            writeDescriptorSets.get(0).sType$Default();
            writeDescriptorSets.get(0).dstSet(descriptorSetHandle);
            writeDescriptorSets.get(0).dstBinding(quorumBufferInfo.GetBinding());
            writeDescriptorSets.get(0).descriptorType(quorumBufferInfo.GetDescriptorType());
            writeDescriptorSets.get(0).descriptorCount(quorumBufferInfo.GetDescriptorCount());
            writeDescriptorSets.get(0).pBufferInfo(bufferInfo);

            vkUpdateDescriptorSets(pluginDevice.GetDevice(), writeDescriptorSets, null);
        }

        return true;
    }

    public long GetDescriptorSetHandle()
    {
        return descriptorSetHandle;
    }
}
