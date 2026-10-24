package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImageInfo_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanImage;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanImage;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanImageStrategy;

/*
The Java side of Quorum's VulkanImage. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanImage), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanImage, with quorum-natives' src/vulkan).
*/
public class VulkanImage
{
    public Object me_;

    private final VulkanImageStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanImage(this) : new DesktopVulkanImage(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanImageInfo_ imageInfo)
    {
        return strategy.CreateNative(quorumDevice, imageInfo);
    }

    public void LoadFromHandle(long handle, VulkanDevice_ device, int width, int height)
    {
        strategy.LoadFromHandle(handle, device, width, height);
    }

    public long GetVulkanImageHandle()
    {
        return strategy.GetVulkanImageHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanImageStrategy GetStrategy()
    {
        return strategy;
    }
}
