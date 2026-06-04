package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdDraw;

public class DrawCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        DrawCommand_ quorumCommand = (DrawCommand_)me_;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        vkCmdDraw(pluginBuffer.GetCommandBuffer(), quorumCommand.GetVertexCount(), quorumCommand.GetInstanceCount(),
                quorumCommand.GetFirstVertex(), quorumCommand.GetFirstInstance());
    }
}
