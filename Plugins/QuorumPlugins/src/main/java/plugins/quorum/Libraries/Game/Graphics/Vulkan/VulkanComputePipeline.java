package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanComputePipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanComputePipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanComputePipelineStrategy;

/*
The Java side of Quorum's VulkanComputePipeline. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanComputePipeline), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanComputePipeline, with quorum-natives' src/vulkan).
*/
public class VulkanComputePipeline
{
    public Object me_;

    private final VulkanComputePipelineStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanComputePipeline(this) : new DesktopVulkanComputePipeline(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanComputePipelineInfo_ quorumInfo)
    {
        return strategy.CreateNative(quorumDevice, quorumInfo);
    }

    public long GetPipelineHandle()
    {
        return strategy.GetPipelineHandle();
    }

    public VulkanComputePipelineStrategy GetStrategy()
    {
        return strategy;
    }
}
