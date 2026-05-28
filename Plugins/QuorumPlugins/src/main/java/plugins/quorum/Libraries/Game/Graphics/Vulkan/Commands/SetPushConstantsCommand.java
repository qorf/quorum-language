package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.SetPushConstantsCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import java.nio.ByteBuffer;

import static org.lwjgl.vulkan.VK10.vkCmdPushConstants;

public class SetPushConstantsCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumBuffer)
    {
        SetPushConstantsCommand_ quorumCommand = (SetPushConstantsCommand_)me_;
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;

        VulkanPipelineLayout pipelineLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_;
        ByteBuffer data = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantBuffer)quorumCommand.GetData()).plugin_.GetByteBuffer();
        data.position(0);
        data.limit(data.capacity());

        vkCmdPushConstants(pluginCommandBuffer.GetCommandBuffer(), pipelineLayout.GetLayoutHandle(),
                quorumCommand.GetStageFlags(), quorumCommand.GetOffset(), data);
    }
}
