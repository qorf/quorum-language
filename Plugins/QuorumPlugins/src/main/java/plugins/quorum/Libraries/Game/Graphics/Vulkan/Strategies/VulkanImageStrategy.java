package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageInfo_;

/*
The platform-neutral part of VulkanImage: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanImageStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImageInfo_ imageInfo);
    void LoadFromHandle(long handle, VulkanDevice_ device, int width, int height);
    long GetVulkanImageHandle();
    void Dispose();
}
