package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanFence;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanFence;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanFenceStrategy;

/*
The Java side of Quorum's VulkanFence. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanFence), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanFence, with quorum-natives' src/vulkan).
*/
public class VulkanFence
{
    public Object me_;

    private final VulkanFenceStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanFence(this) : new DesktopVulkanFence(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, boolean signaled)
    {
        return strategy.CreateNative(quorumDevice, signaled);
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    public void Wait()
    {
        strategy.Wait();
    }

    public void Reset()
    {
        strategy.Reset();
    }

    public long GetFenceHandle()
    {
        return strategy.GetFenceHandle();
    }

    public VulkanFenceStrategy GetStrategy()
    {
        return strategy;
    }
}
