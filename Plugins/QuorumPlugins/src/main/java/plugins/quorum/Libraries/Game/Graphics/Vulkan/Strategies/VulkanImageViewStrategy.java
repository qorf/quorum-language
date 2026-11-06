package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageViewInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_;

/*
The platform-neutral part of VulkanImageView: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanImageViewStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImage_ quorumImage, VulkanImageViewInfo_ info);
    long GetVulkanImageViewHandle();
    void Dispose();
}
