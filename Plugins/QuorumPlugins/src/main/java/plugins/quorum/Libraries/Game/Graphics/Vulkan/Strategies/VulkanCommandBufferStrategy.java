package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanCommandBuffer: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanCommandBufferStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanCommandPool_ quorumPool, int poolLevel);
    boolean BeginRecording();
    boolean EndRecording();
    void Reset(boolean releaseResources);
}
