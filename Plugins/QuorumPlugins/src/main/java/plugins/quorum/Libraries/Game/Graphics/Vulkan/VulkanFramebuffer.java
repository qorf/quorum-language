package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanFramebuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanFramebufferStrategy;

/*
The Java side of Quorum's VulkanFramebuffer. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanFramebuffer), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanFramebuffer, with quorum-natives' src/vulkan).
*/
public class VulkanFramebuffer
{
    public Object me_;

    private final VulkanFramebufferStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanFramebuffer(this) : new DesktopVulkanFramebuffer(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ imageViewArray, VulkanRenderPass_ quorumRenderPass)
    {
        return strategy.CreateNative(quorumDevice, imageViewArray, quorumRenderPass);
    }

    public long GetFramebufferHandle()
    {
        return strategy.GetFramebufferHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanFramebufferStrategy GetStrategy()
    {
        return strategy;
    }
}
