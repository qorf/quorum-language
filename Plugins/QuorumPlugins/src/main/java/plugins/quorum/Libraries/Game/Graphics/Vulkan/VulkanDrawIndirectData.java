package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkDrawIndirectCommand;

public class VulkanDrawIndirectData
{
    public Object me_;

    public int GetByteSize()
    {
        return VkDrawIndirectCommand.SIZEOF;
    }
}
