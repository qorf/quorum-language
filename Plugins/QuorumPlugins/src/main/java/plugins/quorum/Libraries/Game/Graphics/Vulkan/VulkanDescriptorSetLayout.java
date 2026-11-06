package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDescriptorSetLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDescriptorSetLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorSetLayoutStrategy;

/*
The Java side of Quorum's VulkanDescriptorSetLayout. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDescriptorSetLayout), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDescriptorSetLayout, with quorum-natives' src/vulkan).
*/
public class VulkanDescriptorSetLayout
{
    public Object me_;

    private final VulkanDescriptorSetLayoutStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDescriptorSetLayout(this) : new DesktopVulkanDescriptorSetLayout(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumBindings, int layoutFlags)
    {
        return strategy.CreateNative(quorumDevice, quorumBindings, layoutFlags);
    }

    public long GetLayoutHandle()
    {
        return strategy.GetLayoutHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanDescriptorSetLayoutStrategy GetStrategy()
    {
        return strategy;
    }
}
