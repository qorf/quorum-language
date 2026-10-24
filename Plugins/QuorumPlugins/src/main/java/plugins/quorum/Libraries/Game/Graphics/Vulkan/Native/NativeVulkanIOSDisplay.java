package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import quorum.Libraries.Game.GameDisplay_;

// The iOS display's CAMetalLayer, for NativeVulkanSurface. Only loaded on iOS.
public class NativeVulkanIOSDisplay
{
    public static long GetMetalLayer(GameDisplay_ display)
    {
        return ((quorum.Libraries.Game.IOSDisplay)display).plugin_.GetMetalLayerHandle();
    }
}
