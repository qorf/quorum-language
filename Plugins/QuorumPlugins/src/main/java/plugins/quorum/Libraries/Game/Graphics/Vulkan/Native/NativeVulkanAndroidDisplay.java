package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import quorum.Libraries.Game.GameDisplay_;

// The Android display's Surface, for NativeVulkanSurface. Only loaded on Android.
public class NativeVulkanAndroidDisplay
{
    public static Object GetSurface(GameDisplay_ display)
    {
        return ((quorum.Libraries.Game.AndroidDisplay)display).plugin_.GetVulkanSurface();
    }
}
