package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPushConstantRange;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPushConstantRange;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPushConstantRangeStrategy;

/*
The Java side of Quorum's VulkanPushConstantRange. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPushConstantRange), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPushConstantRange, with quorum-natives' src/vulkan).
*/
public class VulkanPushConstantRange
{
    public Object me_;

    private final VulkanPushConstantRangeStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPushConstantRange(this) : new DesktopVulkanPushConstantRange(this);

    public void Create()
    {
        strategy.Create();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanPushConstantRangeStrategy GetStrategy()
    {
        return strategy;
    }
}
