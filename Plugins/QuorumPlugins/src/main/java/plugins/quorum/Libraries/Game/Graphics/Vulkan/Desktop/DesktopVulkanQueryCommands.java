package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VKCapabilitiesInstance;
import org.lwjgl.vulkan.VkCommandBuffer;
import org.lwjgl.vulkan.VkDebugUtilsLabelEXT;
import org.lwjgl.vulkan.VkDebugUtilsObjectNameInfoEXT;
import org.lwjgl.vulkan.VkDevice;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueryCommandsStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginLabelCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.BeginQueryCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.CopyQueryResultsCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.EndQueryCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.ResetQueryPoolCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.Commands.WriteTimestampCommand_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool_;

import static org.lwjgl.vulkan.EXTDebugUtils.*;
import static org.lwjgl.vulkan.VK10.*;

/*
Records the query and debug label commands with LWJGL on the desktop.
Labels need the instance extension
VK_EXT_debug_utils; without it they are left out, so a program can always
ask for them.
*/
public class DesktopVulkanQueryCommands implements VulkanQueryCommandsStrategy
{
    private static VkCommandBuffer Buffer(VulkanCommandBuffer_ quorumBuffer)
    {
        VulkanCommandBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer)quorumBuffer).plugin_;
        return pluginBuffer.GetCommandBuffer();
    }

    private static long Pool(VulkanQueryPool_ quorumPool)
    {
        return ((quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool)quorumPool).plugin_.GetQueryPoolHandle();
    }

    public void RecordResetQueryPool(Object command, VulkanCommandBuffer_ buffer)
    {
        ResetQueryPoolCommand_ quorumCommand = (ResetQueryPoolCommand_)command;
        vkCmdResetQueryPool(Buffer(buffer), Pool(quorumCommand.GetQueryPool()), quorumCommand.GetFirst(), quorumCommand.GetCount());
    }

    public void RecordWriteTimestamp(Object command, VulkanCommandBuffer_ buffer)
    {
        WriteTimestampCommand_ quorumCommand = (WriteTimestampCommand_)command;
        vkCmdWriteTimestamp(Buffer(buffer), quorumCommand.GetStage(), Pool(quorumCommand.GetQueryPool()), quorumCommand.GetIndex());
    }

    public void RecordBeginQuery(Object command, VulkanCommandBuffer_ buffer)
    {
        BeginQueryCommand_ quorumCommand = (BeginQueryCommand_)command;
        int flags = quorumCommand.IsPrecise() ? VK_QUERY_CONTROL_PRECISE_BIT : 0;
        vkCmdBeginQuery(Buffer(buffer), Pool(quorumCommand.GetQueryPool()), quorumCommand.GetIndex(), flags);
    }

    public void RecordEndQuery(Object command, VulkanCommandBuffer_ buffer)
    {
        EndQueryCommand_ quorumCommand = (EndQueryCommand_)command;
        vkCmdEndQuery(Buffer(buffer), Pool(quorumCommand.GetQueryPool()), quorumCommand.GetIndex());
    }

    public void RecordCopyQueryResults(Object command, VulkanCommandBuffer_ buffer)
    {
        CopyQueryResultsCommand_ quorumCommand = (CopyQueryResultsCommand_)command;
        long target = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumCommand.GetBuffer()).plugin_.GetBufferHandle();
        vkCmdCopyQueryPoolResults(Buffer(buffer), Pool(quorumCommand.GetQueryPool()), quorumCommand.GetFirst(), quorumCommand.GetCount(),
                target, quorumCommand.GetOffset(), quorumCommand.GetStride(), quorumCommand.GetFlags());
    }

    private static VKCapabilitiesInstance Capabilities(VkDevice device)
    {
        return device.getPhysicalDevice().getInstance().getCapabilities();
    }

    public boolean CanLabel(VulkanCommandBuffer_ buffer)
    {
        VKCapabilitiesInstance capabilities = Capabilities(Buffer(buffer).getDevice());
        return capabilities.VK_EXT_debug_utils && capabilities.vkCmdBeginDebugUtilsLabelEXT != 0L;
    }

    public void RecordBeginLabel(Object command, VulkanCommandBuffer_ buffer)
    {
        if (!CanLabel(buffer))
            return;
        BeginLabelCommand_ quorumCommand = (BeginLabelCommand_)command;
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDebugUtilsLabelEXT label = VkDebugUtilsLabelEXT.calloc(stack);
            label.sType$Default();
            label.pLabelName(stack.UTF8(quorumCommand.GetLabel()));
            label.color(0, (float)quorumCommand.GetRed());
            label.color(1, (float)quorumCommand.GetGreen());
            label.color(2, (float)quorumCommand.GetBlue());
            label.color(3, (float)quorumCommand.GetAlpha());
            vkCmdBeginDebugUtilsLabelEXT(Buffer(buffer), label);
        }
    }

    public void RecordEndLabel(Object command, VulkanCommandBuffer_ buffer)
    {
        if (!CanLabel(buffer))
            return;
        vkCmdEndDebugUtilsLabelEXT(Buffer(buffer));
    }

    public boolean SetObjectName(quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_ quorumDevice, int objectType, long handle, String name)
    {
        VkDevice device = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_.GetDevice();
        VKCapabilitiesInstance capabilities = Capabilities(device);
        if (!capabilities.VK_EXT_debug_utils || capabilities.vkSetDebugUtilsObjectNameEXT == 0L || handle == 0L)
            return false;
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkDebugUtilsObjectNameInfoEXT info = VkDebugUtilsObjectNameInfoEXT.calloc(stack);
            info.sType$Default();
            info.objectType(objectType);
            info.objectHandle(handle);
            info.pObjectName(stack.UTF8(name));
            return vkSetDebugUtilsObjectNameEXT(device, info) == VK_SUCCESS;
        }
    }
}
