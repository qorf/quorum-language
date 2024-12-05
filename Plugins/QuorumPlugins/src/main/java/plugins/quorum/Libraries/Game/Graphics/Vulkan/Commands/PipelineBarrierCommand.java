package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDependencyInfoKHR;
import org.lwjgl.vulkan.VkMemoryBarrier2KHR;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.PipelineBarrierCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetScissorCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryBarrier_;

import static org.lwjgl.vulkan.KHRSynchronization2.vkCmdPipelineBarrier2KHR;

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
            VkMemoryBarrier2KHR.Buffer memoryBarrier = VkMemoryBarrier2KHR.calloc(1, stack);
            memoryBarrier.sType$Default();
            memoryBarrier.srcStageMask(quorumBarrier.GetSourceStageMask());
            memoryBarrier.srcAccessMask(quorumBarrier.GetSourceAccessMask());
            memoryBarrier.dstStageMask(quorumBarrier.GetDestinationStageMask());
            memoryBarrier.dstAccessMask(quorumBarrier.GetDestinationAccessMask());

            VkDependencyInfoKHR dependencyInfo = VkDependencyInfoKHR.calloc(stack);
            dependencyInfo.sType$Default();
            dependencyInfo.pMemoryBarriers(memoryBarrier);

            vkCmdPipelineBarrier2KHR(pluginBuffer.GetCommandBuffer(), dependencyInfo);
        }
    }
}
