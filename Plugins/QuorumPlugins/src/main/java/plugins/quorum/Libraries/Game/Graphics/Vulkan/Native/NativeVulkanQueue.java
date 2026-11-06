package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueueStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubmitInfo_;

public class NativeVulkanQueue implements VulkanQueueStrategy
{
    private final VulkanQueue peer;
    private long queueHandle = 0L;

    public NativeVulkanQueue(VulkanQueue peer)
    {
        this.peer = peer;
    }

    public void CreateNative(VulkanDevice_ quorumDevice, VulkanQueueFamily_ queueFamily, int queueIndex)
    {
        queueHandle = GetQueue(NativeVulkan.DeviceHandle(quorumDevice), queueFamily.GetQueueFamilyIndex(), queueIndex);
    }

    public boolean SubmitCommands(VulkanSubmitInfo_ quorumInfo)
    {
        Array_ quorumBuffers = quorumInfo.GetCommandBuffers();
        long[] commandBuffers = new long[quorumBuffers.GetSize()];
        for (int i = 0; i < commandBuffers.length; i++)
            commandBuffers[i] = NativeVulkanCommandBuffer.Handle((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_)quorumBuffers.Get(i));

        long[] signalSemaphores = Semaphores(quorumInfo.GetSignalSemaphores());
        long[] waitSemaphores = Semaphores(quorumInfo.GetWaitSemaphores());
        int[] waitMasks = NativeVulkan.ToIntegers(quorumInfo.GetWaitDestinationStageMasks());

        long fence = 0L;
        if (quorumInfo.GetFence() != null)
            fence = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)quorumInfo.GetFence()).plugin_.GetFenceHandle();

        return Submit(queueHandle, commandBuffers, waitSemaphores, waitMasks, signalSemaphores, fence);
    }

    public long GetQueueHandle()
    {
        return queueHandle;
    }

    public static long Handle(quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue_ queue)
    {
        return ((NativeVulkanQueue)((quorum.Libraries.Game.Graphics.Vulkan.VulkanQueue)queue).plugin_.GetStrategy()).GetQueueHandle();
    }

    private static long[] Semaphores(Array_ quorumSemaphores)
    {
        if (quorumSemaphores == null)
            return new long[0];

        long[] handles = new long[quorumSemaphores.GetSize()];
        for (int i = 0; i < handles.length; i++)
            handles[i] = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore)quorumSemaphores.Get(i)).plugin_.GetSemaphoreHandle();
        return handles;
    }

    private static native long GetQueue(long device, int familyIndex, int queueIndex);
    private static native boolean Submit(long queue, long[] commandBuffers, long[] waitSemaphores, int[] waitMasks,
            long[] signalSemaphores, long fence);
}
