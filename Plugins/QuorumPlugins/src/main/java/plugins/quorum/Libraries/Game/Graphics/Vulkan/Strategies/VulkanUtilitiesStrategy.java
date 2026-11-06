package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Array_;

/*
The platform-neutral part of VulkanUtilities: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanUtilitiesStrategy
{
    void GetSupportedLayers(Array_ resultArray);
    void GetSupportedExtensions(Array_ resultArray);
    void GetRequiredDisplayExtensions(Array_ resultArray);
}
