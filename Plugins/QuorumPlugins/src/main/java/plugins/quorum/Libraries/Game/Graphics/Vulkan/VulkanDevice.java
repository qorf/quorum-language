package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanQueueFamily;

import java.nio.FloatBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateDevice;

public class VulkanDevice
{
    public Object me_;

    private VkDevice vulkanDevice;

    public boolean CreateNative(VulkanPhysicalDevice_ quorumPhysicalDevice, Array_ extensionNames, Array_ queueFamilies)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            int extensionCount = extensionNames != null ? extensionNames.GetSize() : 0;

            PointerBuffer requiredExtensions;
            if (extensionCount > 0)
                requiredExtensions = stack.mallocPointer(extensionCount);
            else
                requiredExtensions = null;

            // If we're using the descriptor indexing extension, we'll need to enable some extra features manually.
            boolean hasDescriptorIndexing = false;

            // Put the required extensions into our buffer of ASCII pointers.
            for (int i = 0; i < extensionCount; i++)
            {
                quorum.Libraries.Language.Types.Text_ quorumText = (quorum.Libraries.Language.Types.Text_) extensionNames.Get(i);
                requiredExtensions.put(stack.ASCII(quorumText.GetValue()));

                if (quorumText.GetValue() == "VK_EXT_descriptor_indexing")
                    hasDescriptorIndexing = true;
            }

            // After putting in our extensions, flip the buffer so it's ready to be read. This will set the capacity to match the number of elements put in it.
            if (requiredExtensions != null)
                requiredExtensions.flip();

            VulkanPhysicalDevice physicalDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumPhysicalDevice).plugin_;
            VkQueueFamilyProperties.Buffer familyProperties = physicalDevice.GetQueueFamilyProperties();

            int queueFamilyCount = familyProperties.capacity();
            VkDeviceQueueCreateInfo.Buffer queueCreationInfoBuffer = VkDeviceQueueCreateInfo.calloc(queueFamilyCount, stack);
            for (int i = 0; i < queueFamilyCount; i++)
            {
                /*
                Each queue in each family can have its own priority, where higher priority (theoretically) is prioritized first.
                In practice, drivers decide how to interact with these and may ignore them.
                In this case, we're setting all of the priorities to 0, the lowest possible value, or in other words
                we're ignoring the priority. Because we use "calloc", all of the values are initialized to 0.
                i.e. this line creates as many "0" priority values as there are queues in this queue family.
                */
                FloatBuffer priorities = stack.callocFloat(familyProperties.get(i).queueCount());

                /*
                Here we initialize the info struct for our queue(s). Set the type, what index it is within
                the physical device's internal array structure, and set our (all zero) priorities.
                */
                VkDeviceQueueCreateInfo currentFamilyInfo = queueCreationInfoBuffer.get(i);
                currentFamilyInfo.sType$Default();
                currentFamilyInfo.queueFamilyIndex(i);
                currentFamilyInfo.pQueuePriorities(priorities);

                // We also create a Quorum data structure for each queue family.
                VulkanQueueFamily quorumFamily = new VulkanQueueFamily();
                quorumFamily.Initialize(quorumPhysicalDevice, familyProperties.get(i).queueCount(), familyProperties.get(i).queueFlags(), i);
                queueFamilies.Add(quorumFamily);
            }

            // We'll need an info struct for creating the logical device itself as well. We'll use all the queue info structs as part of this.
            VkDeviceCreateInfo deviceCreateInfo = VkDeviceCreateInfo.calloc(stack);
            deviceCreateInfo.sType$Default();
            deviceCreateInfo.ppEnabledExtensionNames(requiredExtensions);
            deviceCreateInfo.pEnabledFeatures(physicalDevice.GetDeviceFeatures());
            deviceCreateInfo.pQueueCreateInfos(queueCreationInfoBuffer);

            // If we're using descriptor indexing, enable the extended features for it.
            if (hasDescriptorIndexing)
            {
                System.out.println("ENABLING INDEXING FEATURES!");
                VkPhysicalDeviceDescriptorIndexingFeatures indexingFeatures = VkPhysicalDeviceDescriptorIndexingFeatures.calloc(stack);
                indexingFeatures.sType$Default();
                indexingFeatures.descriptorBindingPartiallyBound(true);
                indexingFeatures.descriptorBindingSampledImageUpdateAfterBind(true);
                indexingFeatures.descriptorBindingUpdateUnusedWhilePending(true);
                indexingFeatures.descriptorBindingVariableDescriptorCount(true);
                indexingFeatures.runtimeDescriptorArray(true);
                deviceCreateInfo.pNext(indexingFeatures);
            }
            else
            {
                System.out.println("DIDN'T ENABLE INDEXING FEATURES!");
            }

            // Finally, we're ready to create the logical device object. Start by getting a pointer that we can use as a handle once the memory is allocated for it.
            PointerBuffer devicePointer = stack.mallocPointer(1);
            int vulkanResult = vkCreateDevice(physicalDevice.GetDevice(), deviceCreateInfo, null, devicePointer);
            System.out.println("Creation result code: " + vulkanResult);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            vulkanDevice = new VkDevice(devicePointer.get(0), physicalDevice.GetDevice(), deviceCreateInfo);
        }

        return true;
    }

    public VkDevice GetDevice()
    {
        return vulkanDevice;
    }
}
