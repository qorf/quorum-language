package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanClearValueStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanClearValue_;

/*
Two clear values, a color and a depth and stencil, as the bits of Vulkan's
VkClearValue (4 values of 32 bits each), as the desktop keeps them in LWJGL's
structures.
*/
public class NativeVulkanClearValue implements VulkanClearValueStrategy
{
    private final VulkanClearValue peer;
    private int[] values = null;

    public NativeVulkanClearValue(VulkanClearValue peer)
    {
        this.peer = peer;
    }

    public void SetNativeValues()
    {
        if (values == null)
            values = new int[8];

        VulkanClearValue_ quorumValue = (VulkanClearValue_)peer.me_;
        if (quorumValue.GetRed() >= 0 || quorumValue.GetGreen() >= 0 || quorumValue.GetBlue() >= 0 || quorumValue.GetAlpha() >= 0)
        {
            values[0] = Float.floatToRawIntBits((float)quorumValue.GetRed());
            values[1] = Float.floatToRawIntBits((float)quorumValue.GetGreen());
            values[2] = Float.floatToRawIntBits((float)quorumValue.GetBlue());
            values[3] = Float.floatToRawIntBits((float)quorumValue.GetAlpha());
        }

        values[4] = Float.floatToRawIntBits((float)quorumValue.GetDepth());
        values[5] = quorumValue.GetStencil();
    }

    public void DisposeNative()
    {
        values = null;
    }

    public int[] GetValues()
    {
        return values;
    }
}
