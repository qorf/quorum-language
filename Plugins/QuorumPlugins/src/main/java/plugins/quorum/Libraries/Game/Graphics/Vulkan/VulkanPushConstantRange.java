package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkPushConstantRange;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange_;

import static org.lwjgl.vulkan.VK10.VK_SHADER_STAGE_COMPUTE_BIT;

public class VulkanPushConstantRange {
    public Object me_;
    VkPushConstantRange.Buffer pushConstantBuffer = null;
    public void Create() {
        VulkanPushConstantRange_ quorumSide = (VulkanPushConstantRange_) me_;
        int capacity = quorumSide.GetCapacity();
        int size = quorumSide.GetSize();
        int offset = quorumSide.GetOffset();
        int stage = quorumSide.GetStage();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            VkPushConstantRange.Buffer vpcr = null;
            if (size > 0) {
                vpcr = VkPushConstantRange.calloc(1, stack)
                        .stageFlags(stage)
                        .offset(0)
                        .size(size);
                pushConstantBuffer = vpcr;
            }
        }
    }
}
