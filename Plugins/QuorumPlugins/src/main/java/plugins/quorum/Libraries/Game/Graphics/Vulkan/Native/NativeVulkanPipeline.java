package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan.VulkanShaderManager;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineColorBlendAttachmentState_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanVertexInputAttributeDescription_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanVertexInputBindingDescription_;

/*
A graphics pipeline on iOS and Android, made from the same Quorum info as on
the desktop. If the info has no layout, an empty one is made, and freed with
the pipeline.
*/
public class NativeVulkanPipeline implements VulkanPipelineStrategy
{
    private long pipelineHandle = 0L;
    private long layoutHandle = 0L;
    private boolean ownsLayout = false;

    public NativeVulkanPipeline(VulkanPipeline peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanRenderPass_ quorumRenderPass, VulkanPipelineCache_ quorumCache, Array_ quorumShaders, VulkanPipelineInfo_ info)
    {
        long device = NativeVulkan.DeviceHandle(quorumDevice);
        long renderPass = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumRenderPass).plugin_.GetRenderPassHandle();
        long cache = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache)quorumCache).plugin_.GetVulkanCacheHandle();

        Array_ attributes = info.GetVertexInputAttributeDescriptions();
        int[] attributeValues = new int[attributes.GetSize() * 4];
        for (int i = 0; i < attributes.GetSize(); i++)
        {
            VulkanVertexInputAttributeDescription_ description = (VulkanVertexInputAttributeDescription_)attributes.Get(i);
            attributeValues[i * 4] = description.GetBinding();
            attributeValues[i * 4 + 1] = description.GetLocation();
            attributeValues[i * 4 + 2] = description.GetFormat();
            attributeValues[i * 4 + 3] = description.GetOffset();
        }

        Array_ bindings = info.GetVertexInputBindingDescriptions();
        int[] bindingValues = new int[bindings.GetSize() * 3];
        for (int i = 0; i < bindings.GetSize(); i++)
        {
            VulkanVertexInputBindingDescription_ description = (VulkanVertexInputBindingDescription_)bindings.Get(i);
            bindingValues[i * 3] = description.GetBinding();
            bindingValues[i * 3 + 1] = description.GetStride();
            bindingValues[i * 3 + 2] = description.GetInputRate();
        }

        int[] stages = new int[quorumShaders.GetSize()];
        long[] modules = new long[quorumShaders.GetSize()];
        for (int i = 0; i < stages.length; i++)
        {
            Shader_ quorumShader = (Shader_)quorumShaders.Get(i);
            stages[i] = VulkanShaderManager.GetVulkanShaderType(quorumShader.GetType());
            modules[i] = VulkanShaderManager.GetVulkanShaderHandle(quorumShader);
        }

        int[] state = new int[] {
            info.GetTopology(), info.GetViewportCount(), info.GetScissorCount(), info.GetPolygonMode(), info.GetCullMode(),
            info.GetFrontFace(), info.GetRasterizationSamples(), info.GetDepthTestEnable() ? 1 : 0, info.GetDepthWriteEnable() ? 1 : 0,
            info.GetDepthCompareOp(), info.GetStencilTestEnable() ? 1 : 0
        };

        // As on the desktop, a negative blend factor or operation is "not set" (0).
        Array_ blends = info.GetPipelineColorBlendAttachmentStates();
        int[] blendValues = new int[blends.GetSize() * 8];
        for (int i = 0; i < blends.GetSize(); i++)
        {
            VulkanPipelineColorBlendAttachmentState_ blend = (VulkanPipelineColorBlendAttachmentState_)blends.Get(i);
            int at = i * 8;
            blendValues[at] = blend.GetColorWriteMask();
            blendValues[at + 1] = blend.GetBlendEnable() ? 1 : 0;
            blendValues[at + 2] = Set(blend.GetSourceColorBlendFactor());
            blendValues[at + 3] = Set(blend.GetDestinationColorBlendFactor());
            blendValues[at + 4] = Set(blend.GetColorBlendOp());
            blendValues[at + 5] = Set(blend.GetSourceAlphaBlendFactor());
            blendValues[at + 6] = Set(blend.GetDestinationAlphaBlendFactor());
            blendValues[at + 7] = Set(blend.GetAlphaBlendOp());
        }

        int[] dynamicStates = NativeVulkan.ToIntegers(info.GetDynamicStates());

        if (info.GetLayout() == null)
        {
            layoutHandle = NativeVulkanPipelineLayout.CreatePipelineLayout(device, new long[0], new int[0]);
            if (layoutHandle == 0L)
            {
                System.out.println("Failed to create layout");
                return false;
            }
            ownsLayout = true;
        }
        else
        {
            layoutHandle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)info.GetLayout()).plugin_.GetLayoutHandle();
        }

        pipelineHandle = CreateGraphicsPipeline(device, cache, renderPass, layoutHandle, attributeValues, bindingValues, stages, modules,
                state, (float)info.GetLineWidth(), blendValues, dynamicStates);
        if (pipelineHandle == 0L)
        {
            System.out.println("Failed to create pipeline");
            return false;
        }
        return true;
    }

    public long GetPipelineHandle()
    {
        return pipelineHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        long device = NativeVulkan.DeviceHandle(quorumDevice);
        if (pipelineHandle != 0L)
            DestroyPipeline(device, pipelineHandle);
        if (ownsLayout && layoutHandle != 0L)
            NativeVulkanPipelineLayout.DestroyPipelineLayout(device, layoutHandle);
        pipelineHandle = 0L;
        layoutHandle = 0L;
        ownsLayout = false;
    }

    private static int Set(int value)
    {
        return value >= 0 ? value : 0;
    }

    /*
    attributes: 4 per attribute (binding, location, format, offset); bindings:
    3 per binding (binding, stride, input rate); stages and modules: one per
    shader; state: topology, viewport count, scissor count, polygon mode, cull
    mode, front face, rasterization samples, depth test, depth write, depth
    compare op, stencil test; blends: 8 per attachment (color write mask,
    blend, source and destination color factors, color op, source and
    destination alpha factors, alpha op).
    */
    private static native long CreateGraphicsPipeline(long device, long cache, long renderPass, long layout, int[] attributes,
            int[] bindings, int[] stages, long[] modules, int[] state, float lineWidth, int[] blends, int[] dynamicStates);
    static native void DestroyPipeline(long device, long pipeline);
}
