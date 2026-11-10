package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanCommandBuffer_;

/*
Records the query and debug label commands (Graphics timing plan T1) into a
command buffer. command is the Quorum command, with the values to record.
They are apart from VulkanCommandsStrategy so platforms without them can
leave them out.
*/
public interface VulkanQueryCommandsStrategy
{
    void RecordResetQueryPool(Object command, VulkanCommandBuffer_ buffer);
    void RecordWriteTimestamp(Object command, VulkanCommandBuffer_ buffer);
    void RecordBeginQuery(Object command, VulkanCommandBuffer_ buffer);
    void RecordEndQuery(Object command, VulkanCommandBuffer_ buffer);
    void RecordCopyQueryResults(Object command, VulkanCommandBuffer_ buffer);
    void RecordBeginLabel(Object command, VulkanCommandBuffer_ buffer);
    void RecordEndLabel(Object command, VulkanCommandBuffer_ buffer);
    boolean CanLabel(VulkanCommandBuffer_ buffer);
    boolean SetObjectName(quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_ device, int objectType, long handle, String name);
}
