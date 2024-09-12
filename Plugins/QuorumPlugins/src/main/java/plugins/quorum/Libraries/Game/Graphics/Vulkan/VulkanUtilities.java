package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkLayerProperties;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Language.Types.Text;

import java.nio.IntBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanUtilities
{
    public java.lang.Object me_ = null;

    public void GetSupportedLayers$quorum_Libraries_Containers_Array(Array_ resultArray)
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
}
