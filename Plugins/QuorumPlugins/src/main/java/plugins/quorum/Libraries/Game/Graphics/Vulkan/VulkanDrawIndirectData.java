package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDrawIndirectData;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDrawIndirectData;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataStrategy;

/*
The Java side of Quorum's VulkanDrawIndirectData. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDrawIndirectData), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDrawIndirectData, with quorum-natives' src/vulkan).
*/
public class VulkanDrawIndirectData
{
    public Object me_;

    private final VulkanDrawIndirectDataStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDrawIndirectData(this) : new DesktopVulkanDrawIndirectData(this);

    public int GetByteSize()
    {
        return strategy.GetByteSize();
    }

    public VulkanDrawIndirectDataStrategy GetStrategy()
    {
        return strategy;
    }
}
