package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanUtilities;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanUtilities;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanUtilitiesStrategy;

/*
The Java side of Quorum's VulkanUtilities. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanUtilities), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanUtilities, with quorum-natives' src/vulkan).
*/
public class VulkanUtilities
{
    public java.lang.Object me_ = null;

    private final VulkanUtilitiesStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanUtilities(this) : new DesktopVulkanUtilities(this);

    public void GetSupportedLayers(Array_ resultArray)
    {
        strategy.GetSupportedLayers(resultArray);
    }

    public void GetSupportedExtensions(Array_ resultArray)
    {
        strategy.GetSupportedExtensions(resultArray);
    }

    public void GetRequiredDisplayExtensions(Array_ resultArray)
    {
        strategy.GetRequiredDisplayExtensions(resultArray);
    }

    public VulkanUtilitiesStrategy GetStrategy()
    {
        return strategy;
    }
}
