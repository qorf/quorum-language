package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkCommandBuffer;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandBufferStrategy;

/*
The Java side of Quorum's VulkanCommandBuffer. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanCommandBuffer), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanCommandBuffer, with quorum-natives' src/vulkan).
*/
public class VulkanCommandBuffer
{
    public Object me_;

    private final VulkanCommandBufferStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanCommandBuffer(this) : new DesktopVulkanCommandBuffer(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanCommandPool_ quorumPool, int poolLevel)
    {
        return strategy.CreateNative(quorumDevice, quorumPool, poolLevel);
    }

    public boolean BeginRecording()
    {
        return strategy.BeginRecording();
    }

    public boolean EndRecording()
    {
        return strategy.EndRecording();
    }

    public void Reset(boolean releaseResources)
    {
        strategy.Reset(releaseResources);
    }

    // LWJGL's objects, for the other desktop strategies only.

    public VkCommandBuffer GetCommandBuffer()
    {
        return ((DesktopVulkanCommandBuffer)strategy).GetCommandBuffer();
    }

    public VulkanCommandBufferStrategy GetStrategy()
    {
        return strategy;
    }
}
