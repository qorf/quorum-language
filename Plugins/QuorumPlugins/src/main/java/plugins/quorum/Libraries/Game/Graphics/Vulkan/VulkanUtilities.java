package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWVulkan;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkExtensionProperties;
import org.lwjgl.vulkan.VkLayerProperties;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Language.Types.Text;

import java.nio.IntBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanUtilities
{
    public java.lang.Object me_ = null;

    public void GetSupportedLayers(Array_ resultArray)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // First, check how many layers are available for instances on this platform.
            IntBuffer sizeBuffer = stack.callocInt(1);
            vkEnumerateInstanceLayerProperties(sizeBuffer, null);
            int layerCount = sizeBuffer.get(0);

            // Once we know how many layers there are, allocate space for that many Properties, then fetch them.
            VkLayerProperties.Buffer propertiesBuffer = VkLayerProperties.calloc(layerCount, stack);
            vkEnumerateInstanceLayerProperties(sizeBuffer, propertiesBuffer);

            // Now iterate through the properties and add each layer's name to the array.
            for (int i = 0; i < layerCount; i++)
            {
                VkLayerProperties properties = propertiesBuffer.get(i);
                String layerName = properties.layerNameString();

                // Convert from Java String to Quorum Text, since that's what our Array uses.
                Text quorumText = new Text();
                quorumText.SetValue(layerName);
                resultArray.Add(quorumText);
            }

            // Our array now contains the names of each available layer.
            // When we exit the "try", any memory allocated onto the MemoryStack is automatically freed.
        }
    }

    public void GetSupportedExtensions(Array_ resultArray)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // Begin by checking how many extensions there are.
            IntBuffer sizeBuffer = stack.callocInt(1);
            vkEnumerateInstanceExtensionProperties((String)null, sizeBuffer, null);
            int extensionCount = sizeBuffer.get(0);

            // Now allocate space for that many extension properties and fetch them.
            VkExtensionProperties.Buffer extensionProperties = VkExtensionProperties.calloc(extensionCount, stack);
            vkEnumerateInstanceExtensionProperties((String) null, sizeBuffer, extensionProperties);

            // Iterate through the properties and add each extension's name to the array.
            for (int i = 0; i < extensionCount; i++)
            {
                VkExtensionProperties properties = extensionProperties.get(i);
                String extensionName = properties.extensionNameString();

                // Convert from Java String to Quorum Text, since that's what our Array uses.
                Text quorumText = new Text();
                quorumText.SetValue(extensionName);
                resultArray.Add(quorumText);
            }

            // Our array now contains the names of each available extension.
            // When we exit the "try", any memory allocated onto the MemoryStack is automatically freed.
        }
    }

    public void GetRequiredDisplayExtensions(Array_ resultArray)
    {
        // GLFW provides us the names via pointers to UTF-8 encoded strings.
        PointerBuffer pointers = GLFWVulkan.glfwGetRequiredInstanceExtensions();
        for (int i = 0; i < pointers.capacity(); i++)
        {
            String extensionName = pointers.getStringUTF8(i);

            // Convert the Strings to Quorum Text and add them to the array.
            Text quorumText = new Text();
            quorumText.SetValue(extensionName);
            resultArray.Add(quorumText);
        }
    }
}
