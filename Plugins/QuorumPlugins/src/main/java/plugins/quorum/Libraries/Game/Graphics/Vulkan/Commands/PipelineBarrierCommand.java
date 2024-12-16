package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDependencyInfo;
import org.lwjgl.vulkan.VkMemoryBarrier;
import org.lwjgl.vulkan.VkMemoryBarrier2;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.PipelineBarrierCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetScissorCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryBarrier_;

import static org.lwjgl.vulkan.VK10.vkCmdPipelineBarrier;
import static org.lwjgl.vulkan.VK13.vkCmdPipelineBarrier2;


public class PipelineBarrierCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        PipelineBarrierCommand_ quorumCommand = (PipelineBarrierCommand_)me_;
        VulkanMemoryBarrier_ quorumBarrier = quorumCommand.GetMemoryBarrier();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkMemoryBarrier.Buffer memoryBarrier = VkMemoryBarrier.calloc(1, stack);
            memoryBarrier.sType$Default();
            memoryBarrier.srcAccessMask(quorumBarrier.GetSourceAccessMask());
            memoryBarrier.dstAccessMask(quorumBarrier.GetDestinationAccessMask());

            vkCmdPipelineBarrier(pluginBuffer.GetCommandBuffer(), quorumBarrier.GetSourceStageMask(), quorumBarrier.GetDestinationStageMask(),
            0, memoryBarrier, null, null);
        }
    }
}
