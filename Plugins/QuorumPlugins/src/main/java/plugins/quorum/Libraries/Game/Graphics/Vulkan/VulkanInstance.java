package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkApplicationInfo;
import plugins.quorum.Libraries.Language.SharedClass;

import java.nio.ByteBuffer;

public class VulkanInstance
{
    public java.lang.Object me_;

    //private system action CreateNative(text applicationName, integer applicationVersion, integer apiVersion, boolean useValidationLayers) returns boolean
    public boolean CreateNative(String applicationName, int applicationVersion, int apiVersion, boolean useValidationLayers)
    {
        // The MemoryStack class is used to push data onto native memory without having to pass through JNI.
        // A MemoryStack allocated as part of a "try" is automatically deallocated at the end of the "try" block.
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // Get the VulkanConstants class so we can grab the constants we use for describing the engine name/version.
            quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_ constants = (quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_)SharedClass.GetStaticClass("Libraries.Game.Graphics.Vulkan.VulkanConstants");

            ByteBuffer instanceApplicationName = stack.UTF8(applicationName);
            ByteBuffer instanceEngineName = stack.UTF8(constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__VULKAN_ENGINE_NAME_());

            // Create a VkApplicationInfo structure that will describe the application using this instance.
            VkApplicationInfo appInfo = VkApplicationInfo.calloc(stack);

            appInfo.sType$Default();
            appInfo.pApplicationName(instanceApplicationName);
            appInfo.pEngineName(instanceEngineName);
            appInfo.applicationVersion(applicationVersion);
            appInfo.engineVersion(constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__VULKAN_ENGINE_VERSION_());
            appInfo.apiVersion(apiVersion);


        }

        // If we've reached this point, the instance was created successfully.
        return true;
    }
}
