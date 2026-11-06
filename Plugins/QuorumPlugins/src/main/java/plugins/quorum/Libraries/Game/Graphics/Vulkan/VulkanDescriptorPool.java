package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDescriptorPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDescriptorPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorPoolStrategy;

/*
The Java side of Quorum's VulkanDescriptorPool. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDescriptorPool), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDescriptorPool, with quorum-natives' src/vulkan).
*/
public class VulkanDescriptorPool
{
    public Object me_;

    private final VulkanDescriptorPoolStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDescriptorPool(this) : new DesktopVulkanDescriptorPool(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, int descriptorType, int poolFlags, int descriptorCount, int maxSets)
    {
        return strategy.CreateNative(quorumDevice, descriptorType, poolFlags, descriptorCount, maxSets);
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, int descriptorCount, int maxSets)
    {
        return strategy.CreateNative(quorumDevice, descriptorTypes, poolFlags, descriptorCount, maxSets);
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, Array_ descriptorCounts, int maxSets)
    {
        return strategy.CreateNative(quorumDevice, descriptorTypes, poolFlags, descriptorCounts, maxSets);
    }

    public long GetPoolHandle()
    {
        return strategy.GetPoolHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanDescriptorPoolStrategy GetStrategy()
    {
        return strategy;
    }
}
