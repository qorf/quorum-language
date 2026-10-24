package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanUtilities;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanUtilitiesStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Language.Types.Text;

public class NativeVulkanUtilities implements VulkanUtilitiesStrategy
{
    public NativeVulkanUtilities(VulkanUtilities peer)
    {
    }

    public void GetSupportedLayers(Array_ resultArray)
    {
        AddAll(resultArray, GetInstanceLayers());
    }

    public void GetSupportedExtensions(Array_ resultArray)
    {
        AddAll(resultArray, GetInstanceExtensions());
    }

    // What GLFW gives on the desktop: the surface extensions for this system.
    public void GetRequiredDisplayExtensions(Array_ resultArray)
    {
        if (VulkanPlatform.IsIOS())
            AddAll(resultArray, new String[] {"VK_KHR_surface", "VK_EXT_metal_surface"});
        else
            AddAll(resultArray, new String[] {"VK_KHR_surface", "VK_KHR_android_surface"});
    }

    private static void AddAll(Array_ resultArray, String[] values)
    {
        for (String value : values)
        {
            Text quorumText = new Text();
            quorumText.SetValue(value);
            resultArray.Add(quorumText);
        }
    }

    private static native String[] GetInstanceLayers();
    private static native String[] GetInstanceExtensions();
}
