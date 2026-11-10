package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

/*
The platform-neutral part of VulkanQueryPool: a pool of Vulkan queries
(timestamps, occlusion counts or pipeline statistics), what the desktop
(LWJGL) and native (iOS and Android) strategies both do.
*/
public interface VulkanQueryPoolStrategy
{
    boolean CreateNative(VulkanDevice_ quorumDevice, int type, int count, int statistics);
    long GetQueryPoolHandle();
    boolean ReadTimestamps(int first, int count, double nanosecondsPerTick, int validBits, Number64BitArray_ into);
    boolean ReadResults(int first, int count, int valuesPerQuery, Number64BitArray_ into);
    void Dispose();
}
