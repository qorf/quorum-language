package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSurfaceStrategy;
import org.lwjgl.glfw.GLFWVulkan;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.KHRSurface;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

import java.nio.LongBuffer;

public class DesktopVulkanSurface implements VulkanSurfaceStrategy
{
    private final VulkanSurface peer;

    public DesktopVulkanSurface(VulkanSurface peer)
    {
        this.peer = peer;
    }
    private long vulkanSurfacePointer = 0L;

    public boolean CreateNative(VulkanInstance_ quorumInstance, GameDisplay_ display)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanInstance pluginInstance = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance)quorumInstance).plugin_;
            DesktopDisplay pluginDisplay = ((quorum.Libraries.Game.DesktopDisplay)display).plugin_;

            LongBuffer pointerBuffer = stack.mallocLong(1);
            GLFWVulkan.glfwCreateWindowSurface(pluginInstance.GetInstance(), pluginDisplay.window, null, pointerBuffer);
            vulkanSurfacePointer = pointerBuffer.get(0);
        }

        return (vulkanSurfacePointer != 0L);
    }

    public long GetSurfacePointer()
    {
        return vulkanSurfacePointer;
    }

    // Frees the surface (VulkanSurface:Dispose). The desktop keeps its
    // surface for as long as the window, so this is only called on purpose.
    public void DisposeNative()
    {
        if (vulkanSurfacePointer == 0L)
            return;
        VulkanInstance pluginInstance = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance)((quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_)peer.me_).GetInstance()).plugin_;
        KHRSurface.vkDestroySurfaceKHR(pluginInstance.GetInstance(), vulkanSurfacePointer, null);
        vulkanSurfacePointer = 0L;
    }
}
