package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

/*
The platform-neutral part of VulkanTextureManager: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanTextureManagerStrategy
{
    void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer);
    void TransferPixelDataWithMipMaps(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer, int levels, boolean packedNormals);
}
