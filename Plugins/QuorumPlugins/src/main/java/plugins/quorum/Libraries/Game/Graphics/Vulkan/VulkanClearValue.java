package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanClearValue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanClearValueStrategy;

/*
The Java side of Quorum's VulkanClearValue. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanClearValue), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanClearValue, with quorum-natives' src/vulkan).
*/
public class VulkanClearValue
{
    public Object me_;

    private final VulkanClearValueStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanClearValue(this) : new DesktopVulkanClearValue(this);

    public void SetNativeValues()
    {
        strategy.SetNativeValues();
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkClearValue.Buffer GetClearValue()
    {
        return ((DesktopVulkanClearValue)strategy).GetClearValue();
    }

    public VulkanClearValueStrategy GetStrategy()
    {
        return strategy;
    }
}
