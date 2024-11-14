package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Shaders.Shader;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import quorum.Libraries.Language.Types.Integer_;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanPipeline
{
    public Object me_;

    private long vulkanPipelineLayoutHandle = 0L;
    private long vulkanPipelineHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanRenderPass_ quorumRenderPass, VulkanPipelineCache_ quorumCache, Array_ quorumShaders, VulkanPipelineInfo_ info)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice devicePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VulkanRenderPass renderPassPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumRenderPass).plugin_;
            long pipelineCacheHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache)quorumCache).plugin_.GetVulkanCacheHandle();


            // Convert the Quorum attribute descriptions to Vulkan ones.
            Array_ vertexAttributeDescriptions = info.GetVertexInputAttributeDescriptions();
            int descriptionCount = vertexAttributeDescriptions.GetSize();
            VkVertexInputAttributeDescription.Buffer vkAttributeDescriptions = VkVertexInputAttributeDescription.calloc(descriptionCount, stack);

            for (int i = 0; i < descriptionCount; i++)
            {
                VulkanVertexInputAttributeDescription_ quorumDescription = (VulkanVertexInputAttributeDescription_)vertexAttributeDescriptions.Get(i);
                VkVertexInputAttributeDescription description = vkAttributeDescriptions.get(i);

                description.binding(quorumDescription.GetBinding());
                description.location(quorumDescription.GetLocation());
                description.format(quorumDescription.GetFormat());
                description.offset(quorumDescription.GetOffset());
            }


            // Convert the Quorum binding descriptions to Vulkan ones.
            Array_ vertexBindingDescriptions = info.GetVertexInputBindingDescriptions();
            int bindingCount = vertexBindingDescriptions.GetSize();
            VkVertexInputBindingDescription.Buffer vkBindingDescriptions = VkVertexInputBindingDescription.calloc(bindingCount, stack);

            for (int i = 0; i < bindingCount; i++)
            {
                VulkanVertexInputBindingDescription_ quorumDescription = (VulkanVertexInputBindingDescription_)vertexBindingDescriptions.Get(i);
                VkVertexInputBindingDescription description = vkBindingDescriptions.get(i);

                description.binding(quorumDescription.GetBinding());
                description.stride(quorumDescription.GetStride());
                description.inputRate(quorumDescription.GetInputRate());
            }


            // Create the Vulkan structure for the vertex input info.
            VkPipelineVertexInputStateCreateInfo vertexInputInfo = VkPipelineVertexInputStateCreateInfo.calloc(stack);
            vertexInputInfo.sType$Default();
            vertexInputInfo.pVertexAttributeDescriptions(vkAttributeDescriptions);
            vertexInputInfo.pVertexBindingDescriptions(vkBindingDescriptions);


            // Create the info needed for the shaders.
            ByteBuffer shaderEntryPointName = stack.UTF8("main");
            VkPipelineShaderStageCreateInfo.Buffer shaderStages = VkPipelineShaderStageCreateInfo.calloc(quorumShaders.GetSize(), stack);

            for (int i = 0; i < quorumShaders.GetSize(); i++)
            {
                Shader_ quorumShader = (Shader_)quorumShaders.Get(i);
                Shader shaderPlugin = ((quorum.Libraries.Game.Graphics.Shaders.Shader)quorumShader).plugin_;

                VkPipelineShaderStageCreateInfo shaderStage = shaderStages.get(i);
                shaderStage.sType$Default();
                shaderStage.stage(quorumShader.GetType());
                shaderStage.module(shaderPlugin.GetVulkanHandle());
                shaderStage.pName(shaderEntryPointName);
            }


            // Next, create a structure to define how the vertices will be assembled into primitive shapes.
            VkPipelineInputAssemblyStateCreateInfo assemblyStateInfo = VkPipelineInputAssemblyStateCreateInfo.calloc(stack);
            assemblyStateInfo.sType$Default();
            assemblyStateInfo.topology(info.GetTopology());

            // Now create a structure to describe the viewport we'll render to.
            VkPipelineViewportStateCreateInfo viewportStateInfo = VkPipelineViewportStateCreateInfo.calloc(stack);
            viewportStateInfo.sType$Default();
            viewportStateInfo.viewportCount(info.GetViewportCount());
            viewportStateInfo.scissorCount(info.GetScissorCount());

            // Describe the rasterization info.
            VkPipelineRasterizationStateCreateInfo rasterStateInfo = VkPipelineRasterizationStateCreateInfo.calloc(stack);
            rasterStateInfo.sType$Default();
            rasterStateInfo.polygonMode(info.GetPolygonMode());
            rasterStateInfo.cullMode(info.GetCullMode());
            rasterStateInfo.frontFace(info.GetFrontFace());
            rasterStateInfo.lineWidth((float)info.GetLineWidth());

            // Describe how we'll handle multisampling.
            VkPipelineMultisampleStateCreateInfo multisampleInfo = VkPipelineMultisampleStateCreateInfo.calloc(stack);
            multisampleInfo.sType$Default();
            multisampleInfo.rasterizationSamples(info.GetRasterizationSamples());


            // Process the Quorum blend attachment states into their Vulkan equivalents.
            Array_ quorumBlendAttachments = info.GetPipelineColorBlendAttachmentStates();
            int blendAttachmentCount = quorumBlendAttachments.GetSize();
            VkPipelineColorBlendAttachmentState.Buffer blendAttachmentStates = VkPipelineColorBlendAttachmentState.calloc(blendAttachmentCount, stack);

            for (int i = 0; i < blendAttachmentCount; i++)
            {
                VulkanPipelineColorBlendAttachmentState_ quorumState = (VulkanPipelineColorBlendAttachmentState_)quorumBlendAttachments.Get(i);
                VkPipelineColorBlendAttachmentState vulkanState = blendAttachmentStates.get(i);
                vulkanState.colorWriteMask(quorumState.GetColorWriteMask());

                if (quorumState.GetBlendEnable())
                    vulkanState.blendEnable(quorumState.GetBlendEnable());
                if (quorumState.GetSourceColorBlendFactor() >= 0)
                    vulkanState.srcColorBlendFactor(quorumState.GetSourceColorBlendFactor());
                if (quorumState.GetDestinationColorBlendFactor() >= 0)
                    vulkanState.dstColorBlendFactor(quorumState.GetDestinationColorBlendFactor());
                if (quorumState.GetColorBlendOp() >= 0)
                    vulkanState.colorBlendOp(quorumState.GetColorBlendOp());
                if (quorumState.GetSourceAlphaBlendFactor() >= 0)
                    vulkanState.srcAlphaBlendFactor(quorumState.GetSourceAlphaBlendFactor());
                if (quorumState.GetDestinationAlphaBlendFactor() >= 0)
                    vulkanState.dstAlphaBlendFactor(quorumState.GetDestinationAlphaBlendFactor());
                if (quorumState.GetAlphaBlendOp() >= 0)
                    vulkanState.alphaBlendOp(quorumState.GetAlphaBlendOp());
            }

            VkPipelineColorBlendStateCreateInfo colorBlendInfo = VkPipelineColorBlendStateCreateInfo.calloc(stack);
            colorBlendInfo.sType$Default();
            colorBlendInfo.pAttachments(blendAttachmentStates);


            // Get the requested dynamic states from Quorum.
            Array_ quorumDynamicStates = info.GetDynamicStates();
            int dynamicStateCount = quorumDynamicStates.GetSize();
            VkPipelineDynamicStateCreateInfo dynamicStateInfo = VkPipelineDynamicStateCreateInfo.calloc(stack);
            IntBuffer dynamicStateBuffer = stack.mallocInt(dynamicStateCount);

            for (int i = 0; i < dynamicStateCount; i++)
            {
                Integer_ quorumInteger = (Integer_)quorumDynamicStates.Get(i);
                dynamicStateBuffer.put(i, quorumInteger.GetValue());
            }

            dynamicStateInfo.sType$Default();
            dynamicStateInfo.pDynamicStates(dynamicStateBuffer);


            // Create a layout object for the pipeline.
            VkPipelineLayoutCreateInfo layoutInfo = VkPipelineLayoutCreateInfo.calloc(stack);
            layoutInfo.sType$Default();
            LongBuffer handleBuffer = stack.mallocLong(1);

            int vulkanResult = vkCreatePipelineLayout(devicePlugin.GetDevice(), layoutInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanPipelineLayoutHandle = handleBuffer.get(0);


            // Now we finally have all the necessary info to put together pipeline itself.
            VkGraphicsPipelineCreateInfo.Buffer pipelineCreateInfo = VkGraphicsPipelineCreateInfo.calloc(1, stack);
            pipelineCreateInfo.sType$Default();
            pipelineCreateInfo.pStages(shaderStages);
            pipelineCreateInfo.pVertexInputState(vertexInputInfo);
            pipelineCreateInfo.pInputAssemblyState(assemblyStateInfo);
            pipelineCreateInfo.pViewportState(viewportStateInfo);
            pipelineCreateInfo.pRasterizationState(rasterStateInfo);
            pipelineCreateInfo.pMultisampleState(multisampleInfo);
            pipelineCreateInfo.pColorBlendState(colorBlendInfo);
            pipelineCreateInfo.pDynamicState(dynamicStateInfo);
            pipelineCreateInfo.layout(vulkanPipelineLayoutHandle);
            pipelineCreateInfo.renderPass(renderPassPlugin.GetRenderPassHandle());

            vulkanResult = vkCreateGraphicsPipelines(devicePlugin.GetDevice(), pipelineCacheHandle, pipelineCreateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanPipelineHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetPipelineHandle()
    {
        return vulkanPipelineHandle;
    }
}
