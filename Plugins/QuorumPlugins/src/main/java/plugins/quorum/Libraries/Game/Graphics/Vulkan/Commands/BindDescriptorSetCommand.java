package plugins.quorum.Libraries.Game.Graphics.Vulkan.Commands;

import org.lwjgl.system.MemoryStack;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.vkCmdBindDescriptorSets;

public class BindDescriptorSetCommand
{
    public Object me_;

    public void Record(VulkanCommandBuffer_ quorumCommandBuffer)
    {
        VulkanCommandBuffer pluginCommandBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumCommandBuffer).plugin_;
        quorum.Libraries.Game.Graphics.Vulkan.Commands.BindDescriptorSetCommand_ quorumCommand = (quorum.Libraries.Game.Graphics.Vulkan.Commands.BindDescriptorSetCommand_)me_;
        VulkanPipelineLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout)quorumCommand.GetPipelineLayout()).plugin_;
        VulkanDescriptorSet pluginDescriptorSet = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet)quorumCommand.GetDescriptorSet()).plugin_;
        int bindPoint = quorumCommand.GetBindPoint();
        int setIndex = quorumCommand.GetSetIndex();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            LongBuffer descriptorHandles = stack.mallocLong(1);
            descriptorHandles.put(0, pluginDescriptorSet.GetDescriptorSetHandle());
            vkCmdBindDescriptorSets(pluginCommandBuffer.GetCommandBuffer(), bindPoint, pluginLayout.GetLayoutHandle(), setIndex, descriptorHandles, null);
        }
    }
}
