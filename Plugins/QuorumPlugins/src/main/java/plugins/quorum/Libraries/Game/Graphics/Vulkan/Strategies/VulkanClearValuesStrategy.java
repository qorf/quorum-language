package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

/*
The platform-neutral part of VulkanClearValues: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanClearValuesStrategy
{
    void SystemUpdateNativeValues();
    void DisposeNative();
}
