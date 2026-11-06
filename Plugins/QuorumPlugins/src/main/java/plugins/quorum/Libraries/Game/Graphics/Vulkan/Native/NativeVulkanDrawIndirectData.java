package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDrawIndirectData;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataStrategy;

// The size of Vulkan's VkDrawIndirectCommand: four 4-byte integers.
public class NativeVulkanDrawIndirectData implements VulkanDrawIndirectDataStrategy
{
    public NativeVulkanDrawIndirectData(VulkanDrawIndirectData peer)
    {
    }

    public int GetByteSize()
    {
        return NativeVulkanDrawIndirectDataMappedMemory.STRIDE;
    }
}
