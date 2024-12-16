package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkDevice;
import org.lwjgl.vulkan.VkQueue;
import org.lwjgl.vulkan.VkSubmitInfo;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubmitInfo_;
import quorum.Libraries.Language.Types.Integer_;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanQueue
{
    public Object me_;

    VkQueue vulkanQueue;

    public void CreateNative(VulkanDevice_ quorumDevice, VulkanQueueFamily_ queueFamily, int queueIndex)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            PointerBuffer queuePointer = stack.mallocPointer(1);
            VulkanDevice devicePlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VkDevice vulkanDevice = devicePlugin.GetDevice();

            int familyIndex = queueFamily.GetQueueFamilyIndex();

            System.out.println("Family index: " + familyIndex + ", queueIndex: " + queueIndex);
            vkGetDeviceQueue(vulkanDevice, familyIndex, queueIndex, queuePointer);
            vulkanQueue = new VkQueue(queuePointer.get(0), vulkanDevice);
        }
    }

    public boolean SubmitCommands(VulkanSubmitInfo_ quorumInfo)
    {
        VulkanFence pluginFence = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanFence)quorumInfo.GetFence()).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkSubmitInfo submitInfo = VkSubmitInfo.calloc(stack);
            submitInfo.sType$Default();

            Array_ quorumBuffers = quorumInfo.GetCommandBuffers();
            PointerBuffer commandBuffers = stack.mallocPointer(quorumBuffers.GetSize());
            for (int i = 0; i < quorumBuffers.GetSize(); i++)
            {
                quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer quorumBuffer = (quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer) quorumBuffers.Get(i);
                quorumBuffer.DebugOutputNames();
                commandBuffers.put(quorumBuffer.plugin_.GetCommandBuffer());
            }
            commandBuffers.flip();

            Array_ quorumSignalSemaphores = quorumInfo.GetSignalSemaphores();
            LongBuffer signalSemaphores = stack.mallocLong(quorumSignalSemaphores.GetSize());
            for (int i = 0; i < quorumSignalSemaphores.GetSize(); i++)
            {
                quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore quorumSemaphore = (quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore) quorumSignalSemaphores.Get(i);
                signalSemaphores.put(quorumSemaphore.plugin_.GetSemaphoreHandle());
            }
            signalSemaphores.flip();

            Array_ quorumWaitSemaphores = quorumInfo.GetWaitSemaphores();
            LongBuffer waitSemaphores = stack.mallocLong(quorumWaitSemaphores.GetSize());
            for (int i = 0; i < quorumWaitSemaphores.GetSize(); i++)
            {
                quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore quorumSemaphore = (quorum.Libraries.Game.Graphics.Vulkan.VulkanSemaphore) quorumWaitSemaphores.Get(i);
                waitSemaphores.put(quorumSemaphore.plugin_.GetSemaphoreHandle());
            }
            waitSemaphores.flip();

            Array_ quorumWaitMasks = quorumInfo.GetWaitDestinationStageMasks();
            IntBuffer waitMasks = stack.mallocInt(quorumWaitMasks.GetSize());
            for (int i = 0; i < quorumWaitMasks.GetSize(); i++)
            {
                Integer_ mask = (Integer_)quorumWaitMasks.Get(i);
                waitMasks.put(mask.GetValue());
            }
            waitMasks.flip();

            submitInfo.pCommandBuffers(commandBuffers);
            submitInfo.pSignalSemaphores(signalSemaphores);
            submitInfo.waitSemaphoreCount(quorumWaitSemaphores.GetSize());
            submitInfo.pWaitSemaphores(waitSemaphores);
            submitInfo.pWaitDstStageMask(waitMasks);

            int vulkanResult = vkQueueSubmit(vulkanQueue, submitInfo, pluginFence.GetFenceHandle());
            if (vulkanResult != VK_SUCCESS)
                return false;
        }

        return true;
    }

    public VkQueue GetVulkanQueue()
    {
        return vulkanQueue;
    }
}
