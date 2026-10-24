package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.*;

/*
The platform-neutral part of VulkanComputePipeline: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanComputePipelineStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanComputePipelineInfo_ quorumInfo);
    long GetPipelineHandle();
}
