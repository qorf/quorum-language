package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanDescriptorPool: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDescriptorPoolStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, int descriptorType, int poolFlags, int descriptorCount, int maxSets);
    boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, int descriptorCount, int maxSets);
    boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, Array_ descriptorCounts, int maxSets);
    long GetPoolHandle();
    void DisposeNative(VulkanDevice_ quorumDevice);
}
