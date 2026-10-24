package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

/*
The platform-neutral part of VulkanPushConstantRange: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanPushConstantRangeStrategy
{
    void Create();
    void Dispose();
}
