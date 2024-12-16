package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BindVertexBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.vkCmdBindVertexBuffers;


public class BindVertexBufferCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        BindVertexBufferCommand_ quorumCommand = (BindVertexBufferCommand_)me_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetBuffer()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

            LongBuffer vertexBuffers = stack.mallocLong(1);
            vertexBuffers.put(0, pluginBuffer.GetBufferHandle());
            LongBuffer offsets = stack.mallocLong(1);
            offsets.put(0, quorumCommand.GetOffset());

            vkCmdBindVertexBuffers(pluginCommandBuffer.GetCommandBuffer(), quorumCommand.GetFirstBinding(), vertexBuffers, offsets);
        }
    }
}
