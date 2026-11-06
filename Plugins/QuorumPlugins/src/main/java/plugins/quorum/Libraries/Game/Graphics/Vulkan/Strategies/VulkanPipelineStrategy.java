package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

/*
The platform-neutral part of VulkanPipeline: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanPipelineStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanRenderPass_ quorumRenderPass, VulkanPipelineCache_ quorumCache, Array_ quorumShaders, VulkanPipelineInfo_ info);
    long GetPipelineHandle();
    void DisposeNative(VulkanDevice_ quorumDevice);
}
