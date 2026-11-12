package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanQueryPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanQueryPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueryPoolStrategy;

/*
The Java side of Quorum's VulkanQueryPool. Its work
is done by a strategy: LWJGL on the desktop (DesktopVulkanQueryPool), or
Quorum's own Vulkan layer in C on iOS and Android (NativeVulkanQueryPool,
which reports queries as unsupported until quorum-natives has them).
*/
public class VulkanQueryPool
{
    public Object me_;

    private final VulkanQueryPoolStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanQueryPool(this) : new DesktopVulkanQueryPool(this);

    public boolean CreateNative(VulkanDevice_ quorumDevice, int type, int count, int statistics)
    {
        return strategy.CreateNative(quorumDevice, type, count, statistics);
    }

    public boolean ReadTimestampsNative(int first, int count, double nanosecondsPerTick, int validBits, Number64BitArray_ into)
    {
        return strategy.ReadTimestamps(first, count, nanosecondsPerTick, validBits, into);
    }

    public boolean ReadResultsNative(int first, int count, int valuesPerQuery, Number64BitArray_ into)
    {
        return strategy.ReadResults(first, count, valuesPerQuery, into);
    }

    public long GetQueryPoolHandle()
    {
        return strategy.GetQueryPoolHandle();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanQueryPoolStrategy GetStrategy()
    {
        return strategy;
    }
}
