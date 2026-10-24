package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDrawIndirectData;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawIndirectDataStrategy;
import org.lwjgl.vulkan.VkDrawIndirectCommand;

public class DesktopVulkanDrawIndirectData implements VulkanDrawIndirectDataStrategy
{
    private final VulkanDrawIndirectData peer;

    public DesktopVulkanDrawIndirectData(VulkanDrawIndirectData peer)
    {
        this.peer = peer;
    }

    public int GetByteSize()
    {
        return VkDrawIndirectCommand.SIZEOF;
    }
}
