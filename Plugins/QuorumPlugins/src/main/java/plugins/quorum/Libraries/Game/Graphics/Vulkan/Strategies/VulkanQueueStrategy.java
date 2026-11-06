package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubmitInfo_;

/*
The platform-neutral part of VulkanQueue: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanQueueStrategy
{
    void CreateNative(VulkanDevice_ quorumDevice, VulkanQueueFamily_ queueFamily, int queueIndex);
    boolean SubmitCommands(VulkanSubmitInfo_ quorumInfo);
}
