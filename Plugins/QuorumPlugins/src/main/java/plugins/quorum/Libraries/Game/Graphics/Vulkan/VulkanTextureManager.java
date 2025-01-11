package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import plugins.quorum.Libraries.Game.Graphics.PixelMap;
import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

public class VulkanTextureManager
{
    public Object me_;

    public void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;

        throw new RuntimeException("NYI!");
    }
}
