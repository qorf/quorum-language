package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageViewInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanImageView;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanImageView;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanImageViewStrategy;

/*
The Java side of Quorum's VulkanImageView. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanImageView), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanImageView, with quorum-natives' src/vulkan).
*/
public class VulkanImageView
{
    public Object me_;

    private final VulkanImageViewStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanImageView(this) : new DesktopVulkanImageView(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImage_ quorumImage, VulkanImageViewInfo_ info)
    {
        return strategy.CreateNative(quorumDevice, quorumImage, info);
    }

    public long GetVulkanImageViewHandle()
    {
        return strategy.GetVulkanImageViewHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanImageViewStrategy GetStrategy()
    {
        return strategy;
    }
}
