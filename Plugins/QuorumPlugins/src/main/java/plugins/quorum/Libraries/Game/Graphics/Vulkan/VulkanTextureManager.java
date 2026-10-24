package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanTextureManager;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanTextureManager;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanTextureManagerStrategy;

/*
The Java side of Quorum's VulkanTextureManager. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanTextureManager), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanTextureManager, with quorum-natives' src/vulkan).
*/
public class VulkanTextureManager
{
    public Object me_;

    private final VulkanTextureManagerStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanTextureManager(this) : new DesktopVulkanTextureManager(this);

    public void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer)
    {
        strategy.TransferPixelData(quorumMap, quorumBuffer);
    }

    public void TransferPixelDataWithMipMaps(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer, int levels, boolean packedNormals)
    {
        strategy.TransferPixelDataWithMipMaps(quorumMap, quorumBuffer, levels, packedNormals);
    }

    public VulkanTextureManagerStrategy GetStrategy()
    {
        return strategy;
    }
}
