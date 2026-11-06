package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkQueue;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubmitInfo_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanQueue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanQueue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueueStrategy;

/*
The Java side of Quorum's VulkanQueue. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanQueue), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanQueue, with quorum-natives' src/vulkan).
*/
public class VulkanQueue
{
    public Object me_;

    private final VulkanQueueStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanQueue(this) : new DesktopVulkanQueue(this);

    public void CreateNative(VulkanDevice_ quorumDevice, VulkanQueueFamily_ queueFamily, int queueIndex)
    {
        strategy.CreateNative(quorumDevice, queueFamily, queueIndex);
    }

    public boolean SubmitCommands(VulkanSubmitInfo_ quorumInfo)
    {
        return strategy.SubmitCommands(quorumInfo);
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkQueue GetVulkanQueue()
    {
        return ((DesktopVulkanQueue)strategy).GetVulkanQueue();
    }

    public VulkanQueueStrategy GetStrategy()
    {
        return strategy;
    }
}
