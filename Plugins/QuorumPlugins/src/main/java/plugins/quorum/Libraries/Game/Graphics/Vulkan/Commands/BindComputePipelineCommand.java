package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindComputePipelineCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdBindPipeline;

public class BindComputePipelineCommand {
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        BindComputePipelineCommand_ quorumCommand = (BindComputePipelineCommand_)me_;
        VulkanComputePipeline pluginPipeline = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanComputePipeline)quorumCommand.GetPipeline()).plugin_;

        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindPipeline(pluginBuffer.GetCommandBuffer(), quorumCommand.GetBindPoint(), pluginPipeline.GetPipelineHandle());
    }
}



