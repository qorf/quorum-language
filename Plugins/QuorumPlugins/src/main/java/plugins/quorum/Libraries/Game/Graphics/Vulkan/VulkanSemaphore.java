package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanSemaphore;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanSemaphore;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSemaphoreStrategy;

/*
The Java side of Quorum's VulkanSemaphore. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanSemaphore), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanSemaphore, with quorum-natives' src/vulkan).
*/
public class VulkanSemaphore
{
    public Object me_;

    private final VulkanSemaphoreStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanSemaphore(this) : new DesktopVulkanSemaphore(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        return strategy.CreateNative(quorumDevice);
    }

    public long GetSemaphoreHandle()
    {
        return strategy.GetSemaphoreHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanSemaphoreStrategy GetStrategy()
    {
        return strategy;
    }
}
