package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSurfaceStrategy;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

/*
The surface Vulkan draws on: on iOS, the display's CAMetalLayer
(vkCreateMetalSurfaceEXT); on Android, the display's SurfaceView
(vkCreateAndroidSurfaceKHR). Each platform's display is read in its own small
class, so neither platform loads the other's classes.
*/
public class NativeVulkanSurface implements VulkanSurfaceStrategy
{
    private long instanceHandle = 0L;
    private long surfaceHandle = 0L;

    public NativeVulkanSurface(VulkanSurface peer)
    {
    }

    public boolean CreateNative(VulkanInstance_ quorumInstance, GameDisplay_ display)
    {
        instanceHandle = NativeVulkanInstance.InstanceHandle(quorumInstance);
        if (VulkanPlatform.IsIOS())
            surfaceHandle = CreateMetalSurface(instanceHandle, NativeVulkanIOSDisplay.GetMetalLayer(display));
        else
            surfaceHandle = CreateAndroidSurface(instanceHandle, NativeVulkanAndroidDisplay.GetSurface(display));
        return surfaceHandle != 0L;
    }

    public long GetSurfacePointer()
    {
        return surfaceHandle;
    }

    /*
    Android takes a surface away when the app goes to the background, and
    gives a new one when it comes back, so the old Vulkan surface is destroyed
    then (VulkanGraphics:LoseSurface). iOS keeps its surface.
    */
    public void DisposeNative()
    {
        if (surfaceHandle != 0L)
            DestroySurface(instanceHandle, surfaceHandle);
        surfaceHandle = 0L;
    }

    private static native long CreateMetalSurface(long instance, long layer);
    private static native long CreateAndroidSurface(long instance, Object surface);
    private static native void DestroySurface(long instance, long surface);
}
