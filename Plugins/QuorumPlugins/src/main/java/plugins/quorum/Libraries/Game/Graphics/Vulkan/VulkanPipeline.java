package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPipeline;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineStrategy;

/*
The Java side of Quorum's VulkanPipeline. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPipeline), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPipeline, with quorum-natives' src/vulkan).
*/
public class VulkanPipeline
{
    public Object me_;

    private final VulkanPipelineStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPipeline(this) : new DesktopVulkanPipeline(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanRenderPass_ quorumRenderPass, VulkanPipelineCache_ quorumCache, Array_ quorumShaders, VulkanPipelineInfo_ info)
    {
        return strategy.CreateNative(quorumDevice, quorumRenderPass, quorumCache, quorumShaders, info);
    }

    public long GetPipelineHandle()
    {
        return strategy.GetPipelineHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanPipelineStrategy GetStrategy()
    {
        return strategy;
    }
}
