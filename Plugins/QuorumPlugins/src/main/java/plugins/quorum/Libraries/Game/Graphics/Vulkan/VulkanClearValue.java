package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkClearValue;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue_;

public class VulkanClearValue
{
    public Object me_;

    VkClearValue.Buffer clearValue = null;

    public void SetNativeValues()
    {
        if (clearValue == null)
        {
            clearValue = VkClearValue.calloc(1);
        }

        VulkanClearValue_ quorumValue = (VulkanClearValue_)me_;

        if (quorumValue.GetRed() >= 0 || quorumValue.GetGreen() >= 0 || quorumValue.GetBlue() >= 0 || quorumValue.GetAlpha() >= 0)
        {
            clearValue.apply(0,
                    v -> v.color().float32(0, (float) quorumValue.GetRed())
                            .float32(1, (float) quorumValue.GetGreen())
                            .float32(2, (float) quorumValue.GetBlue())
                            .float32(3, (float) quorumValue.GetAlpha()));
        }

        if (quorumValue.GetDepth() >= 0)
        {
            clearValue.apply(0, v -> v.depthStencil().depth((float)quorumValue.GetDepth()));
        }

        if (quorumValue.GetStencil() >= 0)
        {
            clearValue.apply(0, v -> v.depthStencil().stencil(quorumValue.GetStencil()));
        }
    }

    public void DisposeNative()
    {
        if (clearValue != null)
        {
            clearValue.free();
            clearValue = null;
        }
    }

    public VkClearValue.Buffer GetClearValue()
    {
        return clearValue;
    }
}
