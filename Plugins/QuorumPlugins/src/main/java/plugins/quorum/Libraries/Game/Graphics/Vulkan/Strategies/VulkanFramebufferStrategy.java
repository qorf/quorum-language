package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;

/*
The platform-neutral part of VulkanFramebuffer: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanFramebufferStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, Array_ imageViewArray, VulkanRenderPass_ quorumRenderPass);
    long GetFramebufferHandle();
    void Dispose();
}
