package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSamplerInfo_;

/*
The platform-neutral part of VulkanSampler: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanSamplerStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSamplerInfo_ info);
    long GetSamplerHandle();
    void DisposeNative(VulkanDevice_ quorumDevice);
}
