package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSamplerInfo_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanSampler;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanSampler;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSamplerStrategy;

/*
The Java side of Quorum's VulkanSampler. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanSampler), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanSampler, with quorum-natives' src/vulkan).
*/
public class VulkanSampler
{
    public Object me_;

    private final VulkanSamplerStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanSampler(this) : new DesktopVulkanSampler(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSamplerInfo_ info)
    {
        return strategy.CreateNative(quorumDevice, info);
    }

    public long GetSamplerHandle()
    {
        return strategy.GetSamplerHandle();
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        strategy.DisposeNative(quorumDevice);
    }

    public VulkanSamplerStrategy GetStrategy()
    {
        return strategy;
    }
}
