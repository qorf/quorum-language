package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindIndexBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdBindIndexBuffer;

public class BindIndexBufferCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        BindIndexBufferCommand_ quorumCommand = (BindIndexBufferCommand_)me_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetBuffer()).plugin_;

        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        vkCmdBindIndexBuffer(pluginCommandBuffer.GetCommandBuffer(), pluginBuffer.GetBufferHandle(), quorumCommand.GetOffset(), quorumCommand.GetIndexType());
    }
}
