package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanRenderPass;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanRenderPass;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanRenderPassStrategy;

/*
The Java side of Quorum's VulkanRenderPass. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanRenderPass), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanRenderPass, with quorum-natives' src/vulkan).
*/
public class VulkanRenderPass
{
    public Object me_;

    private final VulkanRenderPassStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanRenderPass(this) : new DesktopVulkanRenderPass(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptions, Array_ quorumSubpasses, Array_ quorumDependencies)
    {
        return strategy.CreateNative(quorumDevice, quorumDescriptions, quorumSubpasses, quorumDependencies);
    }

    public long GetRenderPassHandle()
    {
        return strategy.GetRenderPassHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanRenderPassStrategy GetStrategy()
    {
        return strategy;
    }
}
