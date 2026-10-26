package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFence;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanFenceStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanFence implements VulkanFenceStrategy
{
    private final VulkanFence peer;
    private long fenceHandle = 0L;

    public NativeVulkanFence(VulkanFence peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, boolean signaled)
    {
        fenceHandle = CreateFence(NativeVulkan.DeviceHandle(quorumDevice), signaled);
        return fenceHandle != 0L;
    }

    public void DisposeNative()
    {
        DestroyFence(Device(), fenceHandle);
        fenceHandle = 0L;
    }

    // Time spent waiting on fences, for IOSDisplay's debug log.
    public static long waitNanos;

    public void Wait()
    {
        long start = System.nanoTime();
        WaitForFence(Device(), fenceHandle);
        waitNanos = waitNanos + System.nanoTime() - start;
    }

    public void Reset()
    {
        ResetFence(Device(), fenceHandle);
    }

    public long GetFenceHandle()
    {
        return fenceHandle;
    }

    private long Device()
    {
        return NativeVulkan.DeviceHandle(((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)peer.me_).GetDevice());
    }

    private static native long CreateFence(long device, boolean signaled);
    private static native void DestroyFence(long device, long fence);
    private static native void WaitForFence(long device, long fence);
    private static native void ResetFence(long device, long fence);
}
