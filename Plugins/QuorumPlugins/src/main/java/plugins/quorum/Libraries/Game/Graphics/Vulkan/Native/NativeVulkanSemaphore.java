package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSemaphoreStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore_;

public class NativeVulkanSemaphore implements VulkanSemaphoreStrategy
{
    private final VulkanSemaphore peer;
    private long semaphoreHandle = 0L;

    public NativeVulkanSemaphore(VulkanSemaphore peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice)
    {
        semaphoreHandle = CreateSemaphore(NativeVulkan.DeviceHandle(quorumDevice));
        return semaphoreHandle != 0L;
    }

    public long GetSemaphoreHandle()
    {
        return semaphoreHandle;
    }

    public void Dispose()
    {
        if (semaphoreHandle == 0L)
            return;
        VulkanDevice_ quorumDevice = ((VulkanSemaphore_)peer.me_).Get_Libraries_Game_Graphics_Vulkan_VulkanSemaphore__device_();
        DestroySemaphore(NativeVulkan.DeviceHandle(quorumDevice), semaphoreHandle);
        semaphoreHandle = 0L;
    }

    private static native long CreateSemaphore(long device);
    private static native void DestroySemaphore(long device, long semaphore);
}
