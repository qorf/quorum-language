package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkRect2D;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetScissorCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdSetScissor;

public class SetScissorCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        SetScissorCommand_ quorumCommand = (SetScissorCommand_)me_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkRect2D.Buffer scissorRegion = VkRect2D.calloc(1, stack);
            scissorRegion.extent(rect -> rect.width(quorumCommand.GetWidth()).height(quorumCommand.GetHeight()));
            scissorRegion.offset(rect -> rect.x(quorumCommand.GetX()).y(quorumCommand.GetY()));
            vkCmdSetScissor(pluginBuffer.GetCommandBuffer(), 0, scissorRegion);
        }
    }
}
