package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;

// Recorded by the platform's query commands strategy (VulkanPlatform.GetQueryCommands).
public class ResetQueryPoolCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanPlatform.GetQueryCommands().RecordResetQueryPool(me_, quorumBuffer);
    }
}
