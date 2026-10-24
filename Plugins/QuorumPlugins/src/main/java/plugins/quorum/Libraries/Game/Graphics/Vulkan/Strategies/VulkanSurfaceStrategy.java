package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

/*
The platform-neutral part of VulkanSurface: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanSurfaceStrategy
{
    boolean CreateNative(VulkanInstance_ quorumInstance, GameDisplay_ display);
    long GetSurfacePointer();
    void DisposeNative();
}
