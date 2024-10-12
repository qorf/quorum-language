package plugins.quorum.Libraries.Game.Graphics.Vulkan;

public class VulkanImage
{
    public Object me_;

    private long vulkanImageHandle = 0L;

    public void ForcePluginCreation()
    {
        // Do nothing. The Quorum function only exists to make sure a plugin will be generated for the Quorum object.
    }

    /*
    Used at the plugin level to wrap a handle for a Vulkan image that was generated elsewhere, such as the images
    provided by the system for the swapchain.
    */
    public void LoadFromHandle(long handle)
    {
        vulkanImageHandle = handle;

        quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_ quorumImage = (quorum.Libraries.Game.Graphics.Vulkan.VulkanImage_)me_;
        quorumImage.Set_Libraries_Game_Graphics_Vulkan_VulkanImage__initialized_(true);
    }

    public long GetVulkanImageHandle()
    {
        return vulkanImageHandle;
    }
}
