package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineLayoutStrategy;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkPipelineLayoutCreateInfo;
import org.lwjgl.vulkan.VkPushConstantRange;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.*;

public class DesktopVulkanPipelineLayout implements VulkanPipelineLayoutStrategy
{
    private final VulkanPipelineLayout peer;

    public DesktopVulkanPipelineLayout(VulkanPipelineLayout peer)
    {
        this.peer = peer;
    }

    private long layoutHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptorSetLayouts)
    {
        quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout_ quorumSide = (quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout_) peer.me_;
        VulkanPushConstantRange_ pushConstantRange = quorumSide.GetPushConstants();

        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkPipelineLayoutCreateInfo createInfo = VkPipelineLayoutCreateInfo.calloc(stack);
            createInfo.sType$Default();

            VkPushConstantRange.Buffer vpcr = null;
            if (pushConstantRange != null) {
                quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange r = (quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange) pushConstantRange;
                vpcr = ((DesktopVulkanPushConstantRange)r.plugin_.GetStrategy()).pushConstantBuffer;
                createInfo.pPushConstantRanges(vpcr);
            }

            if (quorumDescriptorSetLayouts != null)
            {
                LongBuffer descriptorSetLayoutHandles = stack.callocLong(quorumDescriptorSetLayouts.GetSize());
                for (int i = 0; i < quorumDescriptorSetLayouts.GetSize(); i++)
                {
                    VulkanDescriptorSetLayout pluginLayout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout) quorumDescriptorSetLayouts.Get(i)).plugin_;
                    descriptorSetLayoutHandles.put(pluginLayout.GetLayoutHandle());
                }
                descriptorSetLayoutHandles.flip();
                createInfo.pSetLayouts(descriptorSetLayoutHandles);
            }
            else
            {
                createInfo.pSetLayouts(null);
            }

            LongBuffer handleBuffer = stack.callocLong(1);
            int vulkanResult = vkCreatePipelineLayout(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS) {
                System.out.println("Failed to make vkCreatePipelineLayout in VulkanPipelineLayout");
                return false;
            }

            layoutHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetLayoutHandle()
    {
        return layoutHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (layoutHandle == 0L)
            return;
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        vkDestroyPipelineLayout(pluginDevice.GetDevice(), layoutHandle, null);
        layoutHandle = 0L;
    }
}
