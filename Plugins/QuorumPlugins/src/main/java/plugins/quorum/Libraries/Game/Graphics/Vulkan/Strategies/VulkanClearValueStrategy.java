package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

/*
The platform-neutral part of VulkanClearValue: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanClearValueStrategy
{
    void SetNativeValues();
    void DisposeNative();
}
