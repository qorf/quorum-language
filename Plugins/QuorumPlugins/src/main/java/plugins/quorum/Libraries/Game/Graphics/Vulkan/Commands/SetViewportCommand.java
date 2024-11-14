package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkViewport;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetViewportCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdSetViewport;

public class SetViewportCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        SetViewportCommand_ quorumCommand = (SetViewportCommand_)me_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkViewport.Buffer viewport = VkViewport.calloc(1, stack);
            viewport.x(quorumCommand.GetX());
            viewport.y(quorumCommand.GetY());
            viewport.height(quorumCommand.GetHeight());
            viewport.width(quorumCommand.GetWidth());
            viewport.minDepth((float)quorumCommand.GetMinimumDepth());
            viewport.maxDepth((float)quorumCommand.GetMaximumDepth());

            vkCmdSetViewport(pluginBuffer.GetCommandBuffer(), 0, viewport);
        }
    }
}
