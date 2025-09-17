package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import static org.lwjgl.vulkan.VK10.vkCmdDispatch;

public class ComputeShaderDispatchCommand {

    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        quorum.Libraries.Game.Graphics.Vulkan.Commands.ComputeShaderDispatchCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.ComputeShaderDispatchCommand_) me_;
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        int x = quorumCommand.GetX();
        int y = quorumCommand.GetY();
        int z = quorumCommand.GetZ();
        vkCmdDispatch(pluginBuffer.GetCommandBuffer(), x, y, z);
    }
}
