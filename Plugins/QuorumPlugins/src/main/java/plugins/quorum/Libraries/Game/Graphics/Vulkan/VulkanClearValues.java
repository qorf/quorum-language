package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkClearValue;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues_;

public class VulkanClearValues
{
    public Object me_;

    VkClearValue.Buffer buffer = null;

    public void SystemUpdateNativeValues()
    {
        Array_ quorumArray = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues)me_).clearValues;

        if (buffer == null)
        {
            buffer = VkClearValue.calloc(quorumArray.GetSize());
        }

        for (int i = 0; i < quorumArray.GetSize(); i++)
        {
            VulkanClearValue_ quorumValue = (VulkanClearValue_)quorumArray.Get(i);
            if (quorumValue.IsColorClearValue())
            {
                buffer.apply(i, v -> v.color().float32(0, (float) quorumValue.GetRed())
                        .float32(1, (float) quorumValue.GetGreen())
                        .float32(2, (float) quorumValue.GetBlue())
                        .float32(3, (float) quorumValue.GetAlpha()));
            }
            else
            {
                buffer.apply(i, v -> v.depthStencil().depth((float) quorumValue.GetDepth()));
                buffer.apply(i, v -> v.depthStencil().stencil(quorumValue.GetStencil()));
            }
        }
    }

    public void DisposeNative()
    {
        if (buffer != null)
        {
            buffer.free();
            buffer = null;
        }
    }

    public VkClearValue.Buffer GetBuffer()
    {
        VulkanClearValues_ quorumValues = (VulkanClearValues_)me_;
        if (quorumValues.IsDirty())
            quorumValues.UpdateNativeValues();

        return buffer;
    }
}
