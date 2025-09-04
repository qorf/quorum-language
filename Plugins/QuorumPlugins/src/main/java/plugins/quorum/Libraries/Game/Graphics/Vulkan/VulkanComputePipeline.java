package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan.VulkanShaderManager;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipelineInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout_;
import quorum.Libraries.Language.Object_;

import java.nio.LongBuffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.vulkan.VK10.*;
public class VulkanComputePipeline {
    public Object me_;

    private long vulkanPipelineLayoutHandle = 0L;
    private long vulkanPipelineHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanComputePipelineInfo_ quorumInfo) {
        VulkanDevice quorumDevicePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VkDevice vkDevice = quorumDevicePlugin.GetDevice();

        Shader_ quorumShader = quorumInfo.GetShader();
        int pushConstantSize = quorumInfo.GetPushConstantsSize();
        Array_ quorumDescriptorSetLayouts = quorumInfo.GetDescriptorSetLayouts();
        long pipelineCacheHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache)quorumInfo.GetPipelineCache()).plugin_.GetVulkanCacheHandle();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            LongBuffer lp = stack.callocLong(1);
            ByteBuffer main = stack.UTF8("main");

            VkPipelineShaderStageCreateInfo.Buffer shaderStages = VkPipelineShaderStageCreateInfo.calloc(1, stack);
            VkPipelineShaderStageCreateInfo shaderStage = VkPipelineShaderStageCreateInfo.calloc(stack)
                    .sType$Default()
                    .stage(VulkanShaderManager.GetVulkanShaderType(quorumShader.GetType()))
                    .module(VulkanShaderManager.GetVulkanShaderHandle(quorumShader))
                    .pName(main);

            //Quorum doesn't currently support specialization constants. This may need to change.
            //More info here: https://docs.vulkan.org/spec/latest/chapters/pipelines.html#pipelines-specialization-constants
//            if (shaderModule.getSpecInfo() != null) {
//                shaderStage.pSpecializationInfo(shaderModule.getSpecInfo());
//            }

            VkPushConstantRange.Buffer vpcr = null;
            if (pushConstantSize > 0) {
                vpcr = VkPushConstantRange.calloc(1, stack)
                        .stageFlags(VK_SHADER_STAGE_COMPUTE_BIT)
                        .offset(0)
                        .size(pushConstantSize);
            }

            int numLayouts = quorumDescriptorSetLayouts.GetSize();

            LongBuffer ppLayout = stack.mallocLong(numLayouts);
            for (int i = 0; i < numLayouts; i++) {
                VulkanDescriptorSetLayout_ layout = (VulkanDescriptorSetLayout_) quorumDescriptorSetLayouts.Get(i);
                VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)layout).plugin_;
                ppLayout.put(i, pluginLayout.GetLayoutHandle());
            }
            VkPipelineLayoutCreateInfo pPipelineLayoutCreateInfo = VkPipelineLayoutCreateInfo.calloc(stack)
                    .sType$Default()
                    .pSetLayouts(ppLayout)
                    .pPushConstantRanges(vpcr);
            int vulkanResult = vkCreatePipelineLayout(vkDevice, pPipelineLayoutCreateInfo, null, lp);
            if (vulkanResult != VK_SUCCESS)
            {
                System.out.println("Failed to create Compute Pipeline: code = " + vulkanResult);
                return false;
            }
            vulkanPipelineLayoutHandle = lp.get(0);

            VkComputePipelineCreateInfo.Buffer computePipelineCreateInfo = VkComputePipelineCreateInfo.calloc(1, stack)
                    .sType$Default()
                    .stage(shaderStage)
                    .layout(vulkanPipelineLayoutHandle);

            //Left out. This is all that is left.
            vulkanResult = vkCreateComputePipelines(vkDevice, pipelineCacheHandle,
                    computePipelineCreateInfo, null, lp);
            if (vulkanResult != VK_SUCCESS)
            {
                System.out.println("Failed to create Compute Pipeline from Cache Handle: code = " + vulkanResult);
                return false;
            }
            vulkanPipelineHandle = lp.get(0);
        }

        return true;
    }
}
