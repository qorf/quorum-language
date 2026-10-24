package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Shaders.Vulkan.VulkanShaderManager;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanComputePipelineStrategy;
import quorum.Libraries.Game.Graphics.Shaders.Shader_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipelineInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanComputePipeline implements VulkanComputePipelineStrategy
{
    private long pipelineHandle = 0L;

    public NativeVulkanComputePipeline(VulkanComputePipeline peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanComputePipelineInfo_ quorumInfo)
    {
        long layout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumInfo.GetPipelineLayout()).plugin_.GetLayoutHandle();
        long cache = 0L;
        if (quorumInfo.GetPipelineCache() != null)
            cache = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache)quorumInfo.GetPipelineCache()).plugin_.GetVulkanCacheHandle();

        Shader_ quorumShader = quorumInfo.GetShader();
        pipelineHandle = CreateComputePipeline(NativeVulkan.DeviceHandle(quorumDevice), cache, layout,
                VulkanShaderManager.GetVulkanShaderType(quorumShader.GetType()), VulkanShaderManager.GetVulkanShaderHandle(quorumShader));
        if (pipelineHandle == 0L)
        {
            System.out.println("Failed to create Compute Pipeline from Cache Handle");
            return false;
        }
        return true;
    }

    public long GetPipelineHandle()
    {
        return pipelineHandle;
    }

    private static native long CreateComputePipeline(long device, long cache, long layout, int stage, long module);
}
