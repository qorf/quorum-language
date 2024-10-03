package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.glfw.GLFWVulkan;
import org.lwjgl.system.MemoryStack;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;

import java.nio.LongBuffer;

public class VulkanSurface
{
    public Object me_;
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
}
