package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkClearValue;
import org.lwjgl.vulkan.VkRenderPassBeginInfo;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import static org.lwjgl.vulkan.VK10.vkCmdBeginRenderPass;

public class BeginRenderPassCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginRenderPassCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginRenderPassCommand_)me_;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        VulkanRenderPass pluginRenderPass = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass)quorumCommand.GetRenderPass()).plugin_;
        VulkanFramebuffer pluginFramebuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer)quorumCommand.GetFramebuffer()).plugin_;
        VulkanClearValues pluginClearValues = null;
        if (quorumCommand.GetClearValues() != null)
            pluginClearValues = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues)quorumCommand.GetClearValues()).plugin_;

        int width = quorumCommand.GetRenderAreaWidth();
        int height = quorumCommand.GetRenderAreaHeight();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkRenderPassBeginInfo beginInfo = VkRenderPassBeginInfo.calloc(stack);
            beginInfo.sType$Default();
            beginInfo.renderPass(pluginRenderPass.GetRenderPassHandle());
            beginInfo.renderArea(a -> a.extent().set(width, height));
            beginInfo.framebuffer(pluginFramebuffer.GetFramebufferHandle());
            if (pluginClearValues != null)
                beginInfo.pClearValues(pluginClearValues.GetBuffer());

            vkCmdBeginRenderPass(pluginBuffer.GetCommandBuffer(), beginInfo, quorumCommand.GetCommandType());
        }
    }
}
