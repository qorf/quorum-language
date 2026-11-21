package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandPoolStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

public class NativeVulkanCommandPool implements VulkanCommandPoolStrategy
{
    private long commandPoolHandle = 0L;

    public NativeVulkanCommandPool(VulkanCommandPool peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, int flags, int queueFamilyIndex)
    {
        commandPoolHandle = CreateCommandPool(NativeVulkan.DeviceHandle(quorumDevice), flags, queueFamilyIndex);
        return commandPoolHandle != 0L;
    }

    public long GetCommandPoolHandle()
    {
        return commandPoolHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (commandPoolHandle == 0L)
            return;
        DestroyCommandPool(NativeVulkan.DeviceHandle(quorumDevice), commandPoolHandle);
        commandPoolHandle = 0L;
    }

    private static native long CreateCommandPool(long device, int flags, int queueFamilyIndex);
    private static native void DestroyCommandPool(long device, long pool);
}
