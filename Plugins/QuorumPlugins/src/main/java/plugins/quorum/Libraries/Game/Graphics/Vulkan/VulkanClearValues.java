package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanClearValues;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanClearValues;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanClearValuesStrategy;

/*
The Java side of Quorum's VulkanClearValues. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanClearValues), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanClearValues, with quorum-natives' src/vulkan).
*/
public class VulkanClearValues
{
    public Object me_;

    private final VulkanClearValuesStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanClearValues(this) : new DesktopVulkanClearValues(this);

    public void SystemUpdateNativeValues()
    {
        strategy.SystemUpdateNativeValues();
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkClearValue.Buffer GetBuffer()
    {
        return ((DesktopVulkanClearValues)strategy).GetBuffer();
    }

    public VulkanClearValuesStrategy GetStrategy()
    {
        return strategy;
    }
}
