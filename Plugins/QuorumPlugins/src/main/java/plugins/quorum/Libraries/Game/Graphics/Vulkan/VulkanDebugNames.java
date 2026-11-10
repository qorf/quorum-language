package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool_;

/*
The Java side of Quorum's VulkanDebugNames (Graphics timing plan T1b). The
naming is done by the platform's query commands strategy, which leaves it
out where VK_EXT_debug_utils isn't loaded.
*/
public class VulkanDebugNames
{
    public Object me_;

    // VkObjectType values.
    private static final int BUFFER = 9;
    private static final int IMAGE = 10;
    private static final int QUERY_POOL = 12;
    private static final int PIPELINE = 19;

    public boolean NameImage(VulkanDevice_ device, VulkanImage_ image, String name)
    {
        long handle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImage)image).plugin_.GetVulkanImageHandle();
        return VulkanPlatform.GetQueryCommands().SetObjectName(device, IMAGE, handle, name);
    }

    public boolean NameBuffer(VulkanDevice_ device, VulkanBuffer_ buffer, String name)
    {
        long handle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)buffer).plugin_.GetBufferHandle();
        return VulkanPlatform.GetQueryCommands().SetObjectName(device, BUFFER, handle, name);
    }

    public boolean NamePipeline(VulkanDevice_ device, VulkanPipeline_ pipeline, String name)
    {
        long handle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPipeline)pipeline).plugin_.GetPipelineHandle();
        return VulkanPlatform.GetQueryCommands().SetObjectName(device, PIPELINE, handle, name);
    }

    public boolean NameQueryPool(VulkanDevice_ device, VulkanQueryPool_ pool, String name)
    {
        long handle = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanQueryPool)pool).plugin_.GetQueryPoolHandle();
        return VulkanPlatform.GetQueryCommands().SetObjectName(device, QUERY_POOL, handle, name);
    }
}
