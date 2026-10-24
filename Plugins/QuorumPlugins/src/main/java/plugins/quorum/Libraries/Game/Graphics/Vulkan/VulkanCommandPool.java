package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanCommandPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanCommandPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandPoolStrategy;

/*
The Java side of Quorum's VulkanCommandPool. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanCommandPool), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanCommandPool, with quorum-natives' src/vulkan).
*/
public class VulkanCommandPool
{
    public Object me_;

    private final VulkanCommandPoolStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanCommandPool(this) : new DesktopVulkanCommandPool(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, int flags, int queueFamilyIndex)
    {
        return strategy.CreateNative(quorumDevice, flags, queueFamilyIndex);
    }

    public long GetCommandPoolHandle()
    {
        return strategy.GetCommandPoolHandle();
    }

    public VulkanCommandPoolStrategy GetStrategy()
    {
        return strategy;
    }
}
