package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.System.Logging.Logger_;

/*
The platform-neutral part of VulkanInstance: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanInstanceStrategy
{
    boolean CreateNative(String applicationName, int applicationVersion, int apiVersion, Array_ layerNames, Array_ extensionNames);
    void Dispose();
    Logger_ GetLogger();
}
