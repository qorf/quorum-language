package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSamplerStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSamplerInfo_;

public class NativeVulkanSampler implements VulkanSamplerStrategy
{
    private long samplerHandle = 0L;

    public NativeVulkanSampler(VulkanSampler peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSamplerInfo_ info)
    {
        int[] values = new int[] {
            info.GetMagnifyFilter(), info.GetMinifyFilter(), info.GetMipMapMode(), info.GetHorizontalWrapMode(),
            info.GetVerticalWrapMode(), info.GetDepthWrapMode(), info.GetAnisotropyEnable() ? 1 : 0, info.GetCompareEnable() ? 1 : 0,
            info.GetCompareOp(), info.GetBorderColor(), info.GetUnnormalizedCoordinates() ? 1 : 0, info.GetFlags()
        };
        float[] numbers = new float[] {
            (float)info.GetMipLodBias(), (float)info.GetMaxAnisotropy(), (float)info.GetMinLod(), (float)info.GetMaxLod()
        };
        samplerHandle = CreateSampler(NativeVulkan.DeviceHandle(quorumDevice), values, numbers);
        return samplerHandle != 0L;
    }

    public long GetSamplerHandle()
    {
        return samplerHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (samplerHandle == 0L)
            return;
        DestroySampler(NativeVulkan.DeviceHandle(quorumDevice), samplerHandle);
        samplerHandle = 0L;
    }

    // values: magnify and minify filters, mipmap mode, the three wrap modes,
    // anisotropy, compare, compare op, border color, unnormalized coordinates,
    // flags. numbers: mip LOD bias, maximum anisotropy, minimum and maximum LOD.
    private static native long CreateSampler(long device, int[] values, float[] numbers);
    private static native void DestroySampler(long device, long sampler);
}
