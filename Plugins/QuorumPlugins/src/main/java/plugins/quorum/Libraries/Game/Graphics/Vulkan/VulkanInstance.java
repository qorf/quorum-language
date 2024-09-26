package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import quorum.Libraries.System.Logging.Logger_;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.EXTDebugUtils.*;
import static org.lwjgl.vulkan.VK10.*;

public class VulkanInstance
{
    public java.lang.Object me_;

    // The actual instance in Vulkan.
    VkInstance vulkanInstance;

    // The info structure for the debug handler, if we're using one (in conjunction with validation layers).
    // We need to keep the info structure, in addition to the callback pointer, so we can free memory later.
    private VkDebugUtilsMessengerCreateInfoEXT debugUtilitiesInfo;
    private long debugHandlerPointer;

    // Bitmasks used for the Vulkan debug callback, if validation is on and the feature is supported.
    public static final int DEBUG_MESSAGE_SEVERITY_BITMASK = VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT |
            VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT;
    public static final int DEBUG_MESSAGE_TYPE_BITMASK = VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT |
            VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT |
            VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT;

    //private system action CreateNative(text applicationName, integer applicationVersion, integer apiVersion, boolean useValidationLayers) returns boolean
    public boolean CreateNative(String applicationName, int applicationVersion, int apiVersion, Array_ layerNames, Array_ extensionNames)
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


            // Create a buffer with pointers to Strings. We need to pass the layer names in this format.
            PointerBuffer requiredLayers = null;
            if (!layerNames.IsEmpty())
            {
                requiredLayers = stack.mallocPointer(layerNames.GetSize());

                // Get each Quorum text, and store it as a string of ASCII characters pointed to by the PointerBuffer.
                for (int i = 0; i < layerNames.GetSize(); i++)
                {
                    quorum.Libraries.Language.Types.Text_ quorumText = (quorum.Libraries.Language.Types.Text_) layerNames.Get(i);
                    requiredLayers.put(i, stack.ASCII(quorumText.GetValue()));
                }
            }

            // When we process the extension names, check if we have the debug extension. If so, we'll need to create a
            // callback for it later.
            boolean hasDebugExtension = false;

            // If we're using the portability extension (e.g., this instance is for Mac) we'll need to set a flag later.
            boolean hasPortabilityExtension = false;

            // Create a buffer with pointers to Strings of the extension names.
            PointerBuffer requiredExtensions = null;
            if (!extensionNames.IsEmpty())
            {
                requiredExtensions = stack.mallocPointer(extensionNames.GetSize());

                // Convert each Quorum text to an ASCII string and store the pointers to the strings.
                for (int i = 0; i < extensionNames.GetSize(); i++)
                {
                    quorum.Libraries.Language.Types.Text_ quorumText = (quorum.Libraries.Language.Types.Text_) extensionNames.Get(i);
                    String value = quorumText.GetValue();

                    // If we have the debug extension, track it so we can make the debug extension after this.
                    if (value.equals(constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__DEBUG_UTILITIES_EXTENSION_()))
                    {
                        hasDebugExtension = true;
                    }
                    else if (value.equals(constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__MAC_PORTABILITY_EXTENSION_())
                        || value.equals(constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__MAC_PORTABILITY_SUBSET_EXTENSION_()))
                    {
                        hasPortabilityExtension = true;
                    }

                    requiredExtensions.put(i, stack.ASCII(value));
                }
            }

            long debugUtilitiesPointer;

            // Create the info struct for the debug utilities, or set it to null.
            if (hasDebugExtension)
            {
                debugUtilitiesInfo = VkDebugUtilsMessengerCreateInfoEXT.calloc();
                debugUtilitiesInfo.sType$Default();
                debugUtilitiesInfo.messageSeverity(DEBUG_MESSAGE_SEVERITY_BITMASK);
                debugUtilitiesInfo.messageType(DEBUG_MESSAGE_TYPE_BITMASK);
                debugUtilitiesInfo.pfnUserCallback((messageSeverity, messageTypes, callbackDataPointer, userDataPointer) -> {
                    VkDebugUtilsMessengerCallbackDataEXT callbackData = VkDebugUtilsMessengerCallbackDataEXT.create(callbackDataPointer);
                    Logger_ logger = GetLogger();
                    if ((messageSeverity & VK_DEBUG_UTILS_MESSAGE_SEVERITY_INFO_BIT_EXT) != 0) {
                        logger.Log("Vulkan Debug Message, INFO: " + callbackData.pMessageString());
                    } else if ((messageSeverity & VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT) != 0) {
                        logger.Log("Vulkan Debug Message, WARNING: " + callbackData.pMessageString());
                    } else if ((messageSeverity & VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT) != 0) {
                        logger.Log("Vulkan Debug Message, ERROR: " + callbackData.pMessageString());
                    } else {
                        logger.Log("Vulkan Debug Message, DEBUG: " + callbackData.pMessageString());
                    }

                    return VK_FALSE;
                });

                debugUtilitiesPointer = debugUtilitiesInfo.address();
            }
            else
            {
                debugUtilitiesInfo = null;
                debugUtilitiesPointer = 0L;
            }


            VkInstanceCreateInfo instanceInfo = VkInstanceCreateInfo.calloc(stack);
            instanceInfo.pNext(debugUtilitiesPointer);
            instanceInfo.pApplicationInfo(appInfo);
            instanceInfo.ppEnabledLayerNames(requiredLayers);
            instanceInfo.ppEnabledLayerNames(requiredExtensions);

            // If we have the portability extension, set the required flag.
            if (hasPortabilityExtension)
            {
                // This is equivalent to the value of VK_INSTANCE_CREATE_ENUMERATE_PORTABILITY_BIT_KHR from the KHRPortabilityEnumeration.
                instanceInfo.flags(0x1);
            }

            PointerBuffer instancePointer = stack.mallocPointer(1);
            int resultCode = vkCreateInstance(instanceInfo, null, instancePointer);

            if (resultCode != VK_SUCCESS)
                return false;

            vulkanInstance = new VkInstance(instancePointer.get(0), instanceInfo);

            if (hasDebugExtension)
            {
                LongBuffer debugPointerBuffer = stack.mallocLong(1);
                resultCode = vkCreateDebugUtilsMessengerEXT(vulkanInstance, debugUtilitiesInfo, null, debugPointerBuffer);
                if (resultCode != VK_SUCCESS)
                    return false;

                debugHandlerPointer = debugPointerBuffer.get(0);
            }
            else
            {
                debugHandlerPointer = 0L;
            }
        }

        // If we've reached this point, the instance was created successfully.
        return true;
    }

    // Release resources associated with the instance. Also release debugging resources, if they're present.
    public void Dispose()
    {
        if (debugHandlerPointer != 0L)
            vkDestroyDebugUtilsMessengerEXT(vulkanInstance, debugHandlerPointer, null);

        vkDestroyInstance(vulkanInstance, null);

        if (debugUtilitiesInfo != null)
        {
            debugUtilitiesInfo.pfnUserCallback().free();
            debugUtilitiesInfo.free();
        }
    }

    public Logger_ GetLogger()
    {
        return ((VulkanInstance_)me_).GetLogger();
    }

    public VkInstance GetInstance()
    {
        return vulkanInstance;
    }
}
