package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.System.Logging.Logger_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanInstance;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanInstance;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanInstanceStrategy;

/*
The Java side of Quorum's VulkanInstance. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanInstance), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanInstance, with quorum-natives' src/vulkan).
*/
public class VulkanInstance
{
    public java.lang.Object me_;

    private final VulkanInstanceStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanInstance(this) : new DesktopVulkanInstance(this);

    public boolean CreateNative(String applicationName, int applicationVersion, int apiVersion, Array_ layerNames, Array_ extensionNames)
    {
        return strategy.CreateNative(applicationName, applicationVersion, apiVersion, layerNames, extensionNames);
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public Logger_ GetLogger()
    {
        return strategy.GetLogger();
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkInstance GetInstance()
    {
        return ((DesktopVulkanInstance)strategy).GetInstance();
    }

    public VulkanInstanceStrategy GetStrategy()
    {
        return strategy;
    }
}
