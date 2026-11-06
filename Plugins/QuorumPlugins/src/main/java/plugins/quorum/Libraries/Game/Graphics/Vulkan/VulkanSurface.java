package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanSurface;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanSurface;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSurfaceStrategy;

/*
The Java side of Quorum's VulkanSurface. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanSurface), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanSurface, with quorum-natives' src/vulkan).
*/
public class VulkanSurface
{
    public Object me_;

    private final VulkanSurfaceStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanSurface(this) : new DesktopVulkanSurface(this);

    public boolean CreateNative(VulkanInstance_ quorumInstance, GameDisplay_ display)
    {
        return strategy.CreateNative(quorumInstance, display);
    }

    public long GetSurfacePointer()
    {
        return strategy.GetSurfacePointer();
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    public VulkanSurfaceStrategy GetStrategy()
    {
        return strategy;
    }
}
