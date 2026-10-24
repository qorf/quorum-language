package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;

// Recorded by the platform's commands strategy (VulkanPlatform.GetCommands).
public class ComputeShaderDispatchCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanPlatform.GetCommands().RecordComputeShaderDispatch(me_, quorumBuffer);
    }
}
