package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandBufferStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanCommandBuffer implements VulkanCommandBufferStrategy
{
    private long commandBufferHandle = 0L;

    public NativeVulkanCommandBuffer(VulkanCommandBuffer peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanCommandPool_ quorumPool, int poolLevel)
    {
        long pool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool)quorumPool).plugin_.GetCommandPoolHandle();
        commandBufferHandle = AllocateCommandBuffer(NativeVulkan.DeviceHandle(quorumDevice), pool, poolLevel);
        return commandBufferHandle != 0L;
    }

    public boolean BeginRecording()
    {
        return BeginCommandBuffer(commandBufferHandle);
    }

    public boolean EndRecording()
    {
        return EndCommandBuffer(commandBufferHandle);
    }

    public void Reset(boolean releaseResources)
    {
        ResetCommandBuffer(commandBufferHandle, releaseResources);
    }

    public long GetCommandBufferHandle()
    {
        return commandBufferHandle;
    }

    public static long Handle(quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_ buffer)
    {
        return ((NativeVulkanCommandBuffer)((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)buffer).plugin_.GetStrategy()).GetCommandBufferHandle();
    }

    private static native long AllocateCommandBuffer(long device, long pool, int level);
    private static native boolean BeginCommandBuffer(long commandBuffer);
    private static native boolean EndCommandBuffer(long commandBuffer);
    private static native void ResetCommandBuffer(long commandBuffer, boolean releaseResources);
}
