package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPushConstantRangeStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange_;

// The push constant range, kept for NativeVulkanPipelineLayout.
public class NativeVulkanPushConstantRange implements VulkanPushConstantRangeStrategy
{
    private final VulkanPushConstantRange peer;

    // Stage flags, offset and size; empty when the size is 0, as the desktop
    // makes no range then.
    private int[] range = new int[0];

    public NativeVulkanPushConstantRange(VulkanPushConstantRange peer)
    {
        this.peer = peer;
    }

    public void Create()
    {
        VulkanPushConstantRange_ quorumSide = (VulkanPushConstantRange_)peer.me_;
        if (quorumSide.GetSize() > 0)
            range = new int[] {quorumSide.GetStage(), quorumSide.GetOffset(), quorumSide.GetSize()};
    }

    public void Dispose()
    {
        range = new int[0];
    }

    public int[] GetRange()
    {
        return range;
    }
}
