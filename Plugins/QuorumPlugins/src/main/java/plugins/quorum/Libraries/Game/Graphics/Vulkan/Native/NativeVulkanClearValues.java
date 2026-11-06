package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanClearValuesStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues_;

/*
The clear values a render pass begins with, as the bits of Vulkan's
VkClearValue: 4 values of 32 bits for each. As on the desktop, the array is
made the first time, and a color sets only as many components as it has.
*/
public class NativeVulkanClearValues implements VulkanClearValuesStrategy
{
    private final VulkanClearValues peer;
    private int[] values = null;

    public NativeVulkanClearValues(VulkanClearValues peer)
    {
        this.peer = peer;
    }

    public void SystemUpdateNativeValues()
    {
        Array_ quorumArray = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValues)peer.me_).clearValues;
        if (values == null)
            values = new int[quorumArray.GetSize() * 4];

        for (int i = 0; i < quorumArray.GetSize() && i * 4 < values.length; i++)
        {
            VulkanClearValue_ quorumValue = (VulkanClearValue_)quorumArray.Get(i);
            int at = i * 4;
            if (quorumValue.IsColorClearValue())
            {
                int components = quorumValue.GetColorComponents();
                if (components >= 1)
                    values[at] = Float.floatToRawIntBits((float)quorumValue.GetRed());
                if (components >= 2)
                    values[at + 1] = Float.floatToRawIntBits((float)quorumValue.GetGreen());
                if (components >= 3)
                    values[at + 2] = Float.floatToRawIntBits((float)quorumValue.GetBlue());
                if (components >= 4)
                    values[at + 3] = Float.floatToRawIntBits((float)quorumValue.GetAlpha());
            }
            else
            {
                values[at] = Float.floatToRawIntBits((float)quorumValue.GetDepth());
                values[at + 1] = quorumValue.GetStencil();
            }
        }
    }

    public void DisposeNative()
    {
        values = null;
    }

    // The values for a render pass, brought up to date first, as the desktop's GetBuffer does.
    public int[] GetValues()
    {
        VulkanClearValues_ quorumValues = (VulkanClearValues_)peer.me_;
        if (quorumValues.IsDirty())
            quorumValues.UpdateNativeValues();
        return values;
    }
}
