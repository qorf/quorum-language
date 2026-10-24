package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanFence;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanFenceStrategy;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkFenceCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class DesktopVulkanFence implements VulkanFenceStrategy
{
    private final VulkanFence peer;

    public DesktopVulkanFence(VulkanFence peer)
    {
        this.peer = peer;
    }

    private long fenceHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, boolean signaled)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

            VkFenceCreateInfo fenceCreateInfo = VkFenceCreateInfo.calloc(stack);
            fenceCreateInfo.sType$Default();
            fenceCreateInfo.flags(signaled ? VK_FENCE_CREATE_SIGNALED_BIT : 0);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateFence(pluginDevice.GetDevice(), fenceCreateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            fenceHandle = handleBuffer.get(0);
        }

        return true;
    }

    public void DisposeNative()
    {
        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)peer.me_).GetDevice();
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        vkDestroyFence(pluginDevice.GetDevice(), fenceHandle, null);
    }

    public void Wait()
    {
        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)peer.me_).GetDevice();
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        vkWaitForFences(pluginDevice.GetDevice(), fenceHandle, true, Long.MAX_VALUE);
    }

    public void Reset()
    {
        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)peer.me_).GetDevice();
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
        vkResetFences(pluginDevice.GetDevice(), fenceHandle);
    }

    public long GetFenceHandle()
    {
        return fenceHandle;
    }

}
