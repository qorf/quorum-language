package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDescriptorSet;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDescriptorSet;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorSetStrategy;

/*
The Java side of Quorum's VulkanDescriptorSet. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDescriptorSet), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDescriptorSet, with quorum-natives' src/vulkan).
*/
public class VulkanDescriptorSet
{
    public Object me_;

    private final VulkanDescriptorSetStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDescriptorSet(this) : new DesktopVulkanDescriptorSet(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout)
    {
        return strategy.CreateNative(quorumDevice, quorumPool, quorumLayout);
    }

    public void LinkResources(VulkanDevice_ quorumDevice, VulkanDescriptorResourcesInfo_ quorumResources)
    {
        strategy.LinkResources(quorumDevice, quorumResources);
    }

    public void LinkResources(VulkanDevice_ quorumDevice, Array_ quorumResourcesArray)
    {
        strategy.LinkResources(quorumDevice, quorumResourcesArray);
    }

    public long GetDescriptorSetHandle()
    {
        return strategy.GetDescriptorSetHandle();
    }

    public VulkanDescriptorSetStrategy GetStrategy()
    {
        return strategy;
    }
}
