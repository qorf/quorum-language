package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

/*
The platform-neutral part of VulkanRenderPass: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanRenderPassStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptions, Array_ quorumSubpasses, Array_ quorumDependencies);
    long GetRenderPassHandle();
    void DisposeNative(VulkanDevice_ quorumDevice);
}
