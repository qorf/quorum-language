package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan.VulkanShaderManager;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
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
        System.out.println("Creating Compute Pipeline");
        VulkanDevice quorumDevicePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VulkanPipelineLayout_ quorumPipelineLayout = quorumInfo.GetPipelineLayout();
        plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout pipelineLayoutPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout) quorumPipelineLayout).plugin_;
        VkDevice vkDevice = quorumDevicePlugin.GetDevice();

        Shader_ quorumShader = quorumInfo.GetShader();
        VulkanPushConstantRange_ pushConstantRange = quorumInfo.GetPushConstants();
        Array_ quorumDescriptorSetLayouts = quorumInfo.GetDescriptorSetLayouts();
        long pipelineCacheHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache)quorumInfo.GetPipelineCache()).plugin_.GetVulkanCacheHandle();

        System.out.println("Creating Memory Stack");
        try (MemoryStack stack = MemoryStack.stackPush()) {
            LongBuffer lp = stack.callocLong(1);
            ByteBuffer main = stack.UTF8("main");

            System.out.println("Creating Compute Shader Stages");
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

//            VkPushConstantRange.Buffer vpcr = null;
//            if (pushConstantRange != null) {
//                quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange r = (quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange) pushConstantRange;
//                vpcr = r.plugin_.pushConstantBuffer;
//            }
//
//            int numLayouts = quorumDescriptorSetLayouts.GetSize();
//
//            LongBuffer ppLayout = stack.mallocLong(numLayouts);
//            for (int i = 0; i < numLayouts; i++) {
//                VulkanDescriptorSetLayout_ layout = (VulkanDescriptorSetLayout_) quorumDescriptorSetLayouts.Get(i);
//                VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)layout).plugin_;
//                ppLayout.put(i, pluginLayout.GetLayoutHandle());
//            }
//            VkPipelineLayoutCreateInfo pPipelineLayoutCreateInfo = VkPipelineLayoutCreateInfo.calloc(stack)
//                    .sType$Default()
//                    .pSetLayouts(ppLayout)
//                    .pPushConstantRanges(vpcr);
//            int vulkanResult = vkCreatePipelineLayout(vkDevice, pPipelineLayoutCreateInfo, null, lp);
//            if (vulkanResult != VK_SUCCESS)
//            {
//                System.out.println("Failed to create Compute Pipeline: code = " + vulkanResult);
//                return false;
//            }
//            vulkanPipelineLayoutHandle = lp.get(0);

            vulkanPipelineLayoutHandle = pipelineLayoutPlugin.GetLayoutHandle();
            System.out.println("Compute Pipeline Layout Handle: " + vulkanPipelineLayoutHandle);

            VkComputePipelineCreateInfo.Buffer computePipelineCreateInfo = VkComputePipelineCreateInfo.calloc(1, stack)
                    .sType$Default()
                    .stage(shaderStage)
                    .layout(vulkanPipelineLayoutHandle);

            System.out.println("Calling vkCreateComputePipelines");
            //Left out. This is all that is left.
            int vulkanResult = vkCreateComputePipelines(vkDevice, pipelineCacheHandle,
                    computePipelineCreateInfo, null, lp);
            if (vulkanResult != VK_SUCCESS)
            {
                System.out.println("Failed to create Compute Pipeline from Cache Handle: code = " + vulkanResult);
                return false;
            }

            vulkanPipelineHandle = lp.get(0);
            System.out.println("Compute Pipeline Created with handle: " + vulkanPipelineHandle);
        }

        return true;
    }

    public long GetPipelineHandle()
    {
        return vulkanPipelineHandle;
    }
}
