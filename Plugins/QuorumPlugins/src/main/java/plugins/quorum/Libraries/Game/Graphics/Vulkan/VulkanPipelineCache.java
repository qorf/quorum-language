package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPipelineCache;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPipelineCacheStrategy;

/*
The Java side of Quorum's VulkanPipelineCache. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPipelineCache), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPipelineCache, with quorum-natives' src/vulkan).
*/
public class VulkanPipelineCache
{
    public Object me_;

    private final VulkanPipelineCacheStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPipelineCache(this) : new DesktopVulkanPipelineCache(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        return strategy.CreateNative(quorumDevice);
    }

    public boolean CreateWithDataNative(VulkanDevice_ quorumDevice, ByteArray_ data)
    {
        byte[] bytes = null;
        if (data != null)
            bytes = ((quorum.Libraries.Containers.ByteArray)data).plugin_.getBytes();
        return strategy.CreateNative(quorumDevice, bytes);
    }

    public ByteArray_ GetDataNative()
    {
        quorum.Libraries.Containers.ByteArray array = new quorum.Libraries.Containers.ByteArray();
        array.plugin_.setBytes(strategy.GetData());
        return array;
    }

    /*
    Writes the cache's data to a file, replacing it. Done here rather than in each strategy, since
    it is plain Java on every platform. Returns false if there is nothing to save or the write fails.
    */
    public boolean SaveNative(String path)
    {
        byte[] bytes = strategy.GetData();
        if (bytes.length == 0)
            return false;

        try (java.io.FileOutputStream stream = new java.io.FileOutputStream(path))
        {
            stream.write(bytes);
            return true;
        }
        catch (java.io.IOException exception)
        {
            return false;
        }
    }

    public long GetVulkanCacheHandle()
    {
        return strategy.GetVulkanCacheHandle();
    }

    public VulkanPipelineCacheStrategy GetStrategy()
    {
        return strategy;
    }
}
