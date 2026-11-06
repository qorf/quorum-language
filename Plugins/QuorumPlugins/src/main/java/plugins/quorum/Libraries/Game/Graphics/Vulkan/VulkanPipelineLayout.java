package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPipelineLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPipelineLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineLayoutStrategy;

/*
The Java side of Quorum's VulkanPipelineLayout. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPipelineLayout), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPipelineLayout, with quorum-natives' src/vulkan).
*/
public class VulkanPipelineLayout
{
    public Object me_;

    private final VulkanPipelineLayoutStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPipelineLayout(this) : new DesktopVulkanPipelineLayout(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptorSetLayouts)
    {
        return strategy.CreateNative(quorumDevice, quorumDescriptorSetLayouts);
    }

    public long GetLayoutHandle()
    {
        return strategy.GetLayoutHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanPipelineLayoutStrategy GetStrategy()
    {
        return strategy;
    }
}
