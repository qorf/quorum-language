package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanQueryCommandsStrategy;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

/*
The query and debug label commands on iOS and Android. Quorum's own Vulkan layer in C
(quorum-natives' src/vulkan) doesn't
have them yet, so nothing is recorded; NativeVulkanQueryPool never makes a
pool, so no query command is ever asked for here. To add them,
quorum-natives needs functions calling vkCmdResetQueryPool,
vkCmdWriteTimestamp, vkCmdBeginQuery, vkCmdEndQuery,
vkCmdCopyQueryPoolResults and, where VK_EXT_debug_utils is loaded,
vkCmdBeginDebugUtilsLabelEXT, vkCmdEndDebugUtilsLabelEXT and
vkSetDebugUtilsObjectNameEXT.
*/
public class NativeVulkanQueryCommands implements VulkanQueryCommandsStrategy
{
    public void RecordResetQueryPool(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordWriteTimestamp(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordBeginQuery(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordEndQuery(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordCopyQueryResults(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordBeginLabel(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public void RecordEndLabel(Object command, VulkanCommandBuffer_ buffer)
    {
    }

    public boolean CanLabel(VulkanCommandBuffer_ buffer)
    {
        return false;
    }

    public boolean SetObjectName(quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_ device, int objectType, long handle, String name)
    {
        return false;
    }
}
