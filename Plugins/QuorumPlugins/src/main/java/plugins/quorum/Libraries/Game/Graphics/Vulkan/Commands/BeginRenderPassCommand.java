package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkRenderPassBeginInfo;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass;
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
        VulkanClearValue pluginClearValue = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue)quorumCommand.GetClearValue()).plugin_;
        VulkanFramebuffer pluginFramebuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer)quorumCommand.GetFramebuffer()).plugin_;

        int width = quorumCommand.GetRenderAreaWidth();
        int height = quorumCommand.GetRenderAreaHeight();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkRenderPassBeginInfo beginInfo = VkRenderPassBeginInfo.calloc(stack);
            beginInfo.sType$Default();
            beginInfo.renderPass(pluginRenderPass.GetRenderPassHandle());
            beginInfo.pClearValues(pluginClearValue.GetClearValue());
            beginInfo.renderArea(a -> a.extent().set(width, height));
            beginInfo.framebuffer(pluginFramebuffer.GetFramebufferHandle());

            vkCmdBeginRenderPass(pluginBuffer.GetCommandBuffer(), beginInfo, quorumCommand.GetCommandType());
        }
    }
}
