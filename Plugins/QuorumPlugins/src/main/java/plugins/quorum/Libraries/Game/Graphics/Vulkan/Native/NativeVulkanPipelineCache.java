package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineCacheStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanPipelineCache implements VulkanPipelineCacheStrategy
{
    private long cacheHandle = 0L;
    private long deviceHandle = 0L;

    public NativeVulkanPipelineCache(VulkanPipelineCache peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        deviceHandle = NativeVulkan.DeviceHandle(quorumDevice);
        cacheHandle = CreatePipelineCache(deviceHandle);
        return cacheHandle != 0L;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, byte[] data)
    {
        deviceHandle = NativeVulkan.DeviceHandle(quorumDevice);
        cacheHandle = CreatePipelineCacheWithData(deviceHandle, data);
        return cacheHandle != 0L;
    }

    public byte[] GetData()
    {
        if (deviceHandle == 0L || cacheHandle == 0L)
            return new byte[0];

        byte[] data = GetPipelineCacheData(deviceHandle, cacheHandle);
        if (data == null)
            return new byte[0];
        return data;
    }

    public long GetVulkanCacheHandle()
    {
        return cacheHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (cacheHandle == 0L)
            return;
        DestroyPipelineCache(NativeVulkan.DeviceHandle(quorumDevice), cacheHandle);
        cacheHandle = 0L;
    }

    private static native long CreatePipelineCache(long device);
    private static native void DestroyPipelineCache(long device, long cache);
    // data may be null or empty, for an empty cache.
    private static native long CreatePipelineCacheWithData(long device, byte[] data);
    private static native byte[] GetPipelineCacheData(long device, long cache);
}
