package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

/*
The platform-neutral part of VulkanDescriptorSet: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDescriptorSetStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout);
    void LinkResources(VulkanDevice_ quorumDevice, VulkanDescriptorResourcesInfo_ quorumResources);
    void LinkResources(VulkanDevice_ quorumDevice, Array_ quorumResourcesArray);
    long GetDescriptorSetHandle();
}
