package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawIndexedCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdDrawIndexed;

public class DrawIndexedCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        DrawIndexedCommand_ quorumCommand = (DrawIndexedCommand_)me_;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        vkCmdDrawIndexed(pluginBuffer.GetCommandBuffer(), quorumCommand.GetIndexCount(), quorumCommand.GetInstanceCount(),
                quorumCommand.GetFirstIndex(), quorumCommand.GetVertexOffset(), quorumCommand.GetFirstInstance());
    }
}
