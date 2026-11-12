package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkQueryPoolCreateInfo;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueryPoolStrategy;
import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

/*
A pool of Vulkan queries with LWJGL. Results are
read without waiting: each query's value comes with an availability word,
and a read with any query not yet finished returns false.
*/
public class DesktopVulkanQueryPool implements VulkanQueryPoolStrategy
{
    private final VulkanQueryPool peer;
    private VkDevice device = null;
    private long queryPoolHandle = 0L;

    public DesktopVulkanQueryPool(VulkanQueryPool peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, int type, int count, int statistics)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        VkDevice vulkanDevice = pluginDevice.GetDevice();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkQueryPoolCreateInfo createInfo = VkQueryPoolCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.queryType(type);
            createInfo.queryCount(count);
            createInfo.pipelineStatistics(statistics);
            LongBuffer handleBuffer = stack.mallocLong(1);

            if (vkCreateQueryPool(vulkanDevice, createInfo, null, handleBuffer) != VK_SUCCESS)
                return false;

            queryPoolHandle = handleBuffer.get(0);
            device = vulkanDevice;
        }
        return true;
    }

    public long GetQueryPoolHandle()
    {
        return queryPoolHandle;
    }

    /*
    Reads timestamps as milliseconds after the first one read: each raw tick
    count is masked to the queue's valid bits, the first is subtracted (so a
    wrap of the counter still gives the right difference), and the result is
    multiplied by the device's nanoseconds per tick.
    */
    public boolean ReadTimestamps(int first, int count, double nanosecondsPerTick, int validBits, Number64BitArray_ into)
    {
        if (queryPoolHandle == 0L || count <= 0)
            return false;
        LongBuffer raw = MemoryUtil.memAllocLong(count * 2);
        try
        {
            int result = vkGetQueryPoolResults(device, queryPoolHandle, first, count, raw, 16,
                    VK_QUERY_RESULT_64_BIT | VK_QUERY_RESULT_WITH_AVAILABILITY_BIT);
            if (result != VK_SUCCESS && result != VK_NOT_READY)
                return false;
            for (int i = 0; i < count; i++)
            {
                if (raw.get(i * 2 + 1) == 0L)
                    return false;
            }
            long mask = (validBits <= 0 || validBits >= 64) ? -1L : ((1L << validBits) - 1L);
            long base = raw.get(0) & mask;
            double[] values = ((quorum.Libraries.Containers.Number64BitArray)into).plugin_.doubles;
            for (int i = 0; i < count && i < values.length; i++)
            {
                long ticks = ((raw.get(i * 2) & mask) - base) & mask;
                values[i] = ticks * nanosecondsPerTick / 1000000.0;
            }
            return true;
        }
        finally
        {
            MemoryUtil.memFree(raw);
        }
    }

    /*
    Reads occlusion counts or pipeline statistics: valuesPerQuery numbers
    for each query (1 for occlusion; one per statistic asked for), in order.
    */
    public boolean ReadResults(int first, int count, int valuesPerQuery, Number64BitArray_ into)
    {
        if (queryPoolHandle == 0L || count <= 0 || valuesPerQuery <= 0)
            return false;
        int stride = valuesPerQuery + 1;
        LongBuffer raw = MemoryUtil.memAllocLong(count * stride);
        try
        {
            int result = vkGetQueryPoolResults(device, queryPoolHandle, first, count, raw, stride * 8L,
                    VK_QUERY_RESULT_64_BIT | VK_QUERY_RESULT_WITH_AVAILABILITY_BIT);
            if (result != VK_SUCCESS && result != VK_NOT_READY)
                return false;
            for (int i = 0; i < count; i++)
            {
                if (raw.get(i * stride + valuesPerQuery) == 0L)
                    return false;
            }
            double[] values = ((quorum.Libraries.Containers.Number64BitArray)into).plugin_.doubles;
            for (int i = 0; i < count; i++)
            {
                for (int v = 0; v < valuesPerQuery; v++)
                {
                    int place = i * valuesPerQuery + v;
                    if (place < values.length)
                        values[place] = raw.get(i * stride + v);
                }
            }
            return true;
        }
        finally
        {
            MemoryUtil.memFree(raw);
        }
    }

    public void Dispose()
    {
        if (queryPoolHandle == 0L)
            return;
        vkDestroyQueryPool(device, queryPoolHandle, null);
        queryPoolHandle = 0L;
    }
}
