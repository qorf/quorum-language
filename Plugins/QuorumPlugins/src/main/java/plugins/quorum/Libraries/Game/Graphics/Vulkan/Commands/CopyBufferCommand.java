package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkBufferCopy;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyBufferCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdCopyBuffer;

public class CopyBufferCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        CopyBufferCommand_ quorumCommand = (CopyBufferCommand_)me_;
        VulkanBuffer pluginSourceBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetSourceBuffer()).plugin_;
        VulkanBuffer pluginDestinationBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetDestinationBuffer()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkBufferCopy.Buffer copyRegion = VkBufferCopy.calloc(1, stack);
            copyRegion.srcOffset(quorumCommand.GetSourceOffset());
            copyRegion.dstOffset(quorumCommand.GetDestinationOffset());
            copyRegion.size(quorumCommand.GetSize());

            VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer) quorumBuffer).plugin_;
            vkCmdCopyBuffer(pluginCommandBuffer.GetCommandBuffer(), pluginSourceBuffer.GetBufferHandle(), pluginDestinationBuffer.GetBufferHandle(), copyRegion);
        }
    }
}
