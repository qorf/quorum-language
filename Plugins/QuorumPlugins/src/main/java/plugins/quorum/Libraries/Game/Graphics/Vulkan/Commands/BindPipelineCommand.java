package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindPipelineCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdBindPipeline;

public class BindPipelineCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        BindPipelineCommand_ quorumCommand = (BindPipelineCommand_)me_;
        VulkanPipeline pluginPipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline)quorumCommand.GetPipeline()).plugin_;

        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindPipeline(pluginBuffer.GetCommandBuffer(), quorumCommand.GetBindPoint(), pluginPipeline.GetPipelineHandle());
    }
}
