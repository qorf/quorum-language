package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueryPoolStrategy;
import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
Query pools on iOS and Android. Quorum's own Vulkan
layer in C (quorum-natives' src/vulkan) doesn't have queries yet, so pools
aren't made here: Create returns false, and the graphics timer reports that
timing isn't supported. To add them, quorum-natives needs functions that
call vkCreateQueryPool, vkDestroyQueryPool and vkGetQueryPoolResults (with
VK_QUERY_RESULT_64_BIT and VK_QUERY_RESULT_WITH_AVAILABILITY_BIT), and this
class calls them as DesktopVulkanQueryPool does with LWJGL.
*/
public class NativeVulkanQueryPool implements VulkanQueryPoolStrategy
{
    private final VulkanQueryPool peer;

    public NativeVulkanQueryPool(VulkanQueryPool peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, int type, int count, int statistics)
    {
        return false;
    }

    public long GetQueryPoolHandle()
    {
        return 0L;
    }

    public boolean ReadTimestamps(int first, int count, double nanosecondsPerTick, int validBits, Number64BitArray_ into)
    {
        return false;
    }

    public boolean ReadResults(int first, int count, int valuesPerQuery, Number64BitArray_ into)
    {
        return false;
    }

    public void Dispose()
    {
    }
}
