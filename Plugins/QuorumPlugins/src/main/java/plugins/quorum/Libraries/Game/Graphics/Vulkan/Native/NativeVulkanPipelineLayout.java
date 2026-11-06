package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineLayoutStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange_;

public class NativeVulkanPipelineLayout implements VulkanPipelineLayoutStrategy
{
    private final VulkanPipelineLayout peer;
    private long layoutHandle = 0L;

    public NativeVulkanPipelineLayout(VulkanPipelineLayout peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptorSetLayouts)
    {
        quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout_ quorumSide = (quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineLayout_)peer.me_;
        VulkanPushConstantRange_ pushConstantRange = quorumSide.GetPushConstants();

        // stage, offset and size, or nothing if there's no range.
        int[] range = new int[0];
        if (pushConstantRange != null)
            range = ((NativeVulkanPushConstantRange)((quorum.Libraries.Game.Graphics.Vulkan.VulkanPushConstantRange)pushConstantRange).plugin_.GetStrategy()).GetRange();

        long[] setLayouts = new long[quorumDescriptorSetLayouts == null ? 0 : quorumDescriptorSetLayouts.GetSize()];
        for (int i = 0; i < setLayouts.length; i++)
            setLayouts[i] = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumDescriptorSetLayouts.Get(i)).plugin_.GetLayoutHandle();

        layoutHandle = CreatePipelineLayout(NativeVulkan.DeviceHandle(quorumDevice), setLayouts, range);
        if (layoutHandle == 0L)
        {
            System.out.println("Failed to make vkCreatePipelineLayout in VulkanPipelineLayout");
            return false;
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
        DestroyPipelineLayout(NativeVulkan.DeviceHandle(quorumDevice), layoutHandle);
        layoutHandle = 0L;
    }

    // pushConstantRange: stage flags, offset and size, or empty.
    static native long CreatePipelineLayout(long device, long[] setLayouts, int[] pushConstantRange);
    static native void DestroyPipelineLayout(long device, long layout);
}
