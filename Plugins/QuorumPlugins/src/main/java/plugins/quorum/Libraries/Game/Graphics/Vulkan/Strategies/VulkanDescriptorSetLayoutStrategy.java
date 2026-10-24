package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanDescriptorSetLayout: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDescriptorSetLayoutStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumBindings, int layoutFlags);
    long GetLayoutHandle();
    void DisposeNative(VulkanDevice_ quorumDevice);
}
