package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.DrawIndirectCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdDrawIndirect;

public class DrawIndirectCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        DrawIndirectCommand_ quorumCommand = (DrawIndirectCommand_)me_;
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        VulkanBuffer pluginDataBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetDrawCommands()).plugin_;

        vkCmdDrawIndirect(pluginCommandBuffer.GetCommandBuffer(), pluginDataBuffer.GetBufferHandle(),
                quorumCommand.GetOffset(), quorumCommand.GetDrawCount(), quorumCommand.GetStride());
    }
}
