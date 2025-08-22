package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

import java.nio.IntBuffer;

import static org.lwjgl.vulkan.VK10.*;

public class VulkanPhysicalDevice
{
    public java.lang.Object me_;

    private VkPhysicalDevice physicalDevice = null;
    private quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits quorumLimits = null;
    private VkExtensionProperties.Buffer deviceExtensions = null;
    private VkPhysicalDeviceProperties deviceProperties = null;
    private VkQueueFamilyProperties.Buffer queueFamilyProperties = null;
    private VkPhysicalDeviceMemoryProperties memoryProperties = null;
    private VkPhysicalDeviceFeatures deviceFeatures = null;

    long memorySize = 0L;


    public boolean CreateNative(int featuresBitmask, Array_ extensionNames)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer physicalDeviceCountBuffer = stack.mallocInt(1);
            VkInstance instance = GetVkInstance();

            // Find out how many physical devices we have.
            int vulkanResult = vkEnumeratePhysicalDevices(instance, physicalDeviceCountBuffer, null);
            if (vulkanResult != VK_SUCCESS)
                return false;

            int physicalDeviceCount = physicalDeviceCountBuffer.get(0);
            if (physicalDeviceCount <= 0)
                return false;

            // Fetch a pointer to each of the available devices.
            PointerBuffer physicalDevicePointers = stack.mallocPointer(physicalDeviceCount);
            vulkanResult = vkEnumeratePhysicalDevices(instance, physicalDeviceCountBuffer, physicalDevicePointers);
            if (vulkanResult != VK_SUCCESS)
                return false;

            // We'll use the ConvertDeviceTypeToPriority function to try to get the best type of available device.
            // We begin with a value of -1, which any device we recognize will beat. We prioritize higher values.
            int candidateTypePriority = -1;
            long candidateMemorySize = 0L;
            VkPhysicalDevice candidateDevice = null;
            VkPhysicalDeviceProperties candidateProperties = null;
            VkQueueFamilyProperties.Buffer candidateFamilyProperties = null;
            VkExtensionProperties.Buffer candidateExtensions = null;
            VkPhysicalDeviceMemoryProperties candidateMemoryProperties = null;

            /*
            Iterate through all the physical devices available. Find one that supports the necessary extensions for
            rendering and displaying to the screen. If we have multiple, prioritize the best device type, breaking ties
            by whichever has more VRAM available.
            */
            for (int i = 0; i < physicalDeviceCount; i++)
            {
                VkPhysicalDevice currentDevice = new VkPhysicalDevice(physicalDevicePointers.get(i), instance);

                // We need a variety of different kinds of properties from our device. Allocate memory and fetch the properties.
                // We aren't using the MemoryStack to allocate these, so we'll need to manually free them if we don't keep them.
                VkPhysicalDeviceProperties currentProperties = VkPhysicalDeviceProperties.calloc();
                vkGetPhysicalDeviceProperties(currentDevice, currentProperties);

                IntBuffer sizeBuffer = stack.mallocInt(1);

                // Figure out how much memory is needed for the queue family properties, allocate that much space, then store them.
                vkGetPhysicalDeviceQueueFamilyProperties(currentDevice, sizeBuffer, null);
                VkQueueFamilyProperties.Buffer currentFamilyProperties = VkQueueFamilyProperties.calloc(sizeBuffer.get(0));
                vkGetPhysicalDeviceQueueFamilyProperties(currentDevice, sizeBuffer, currentFamilyProperties);

                boolean supportsFeatures = false;
                if (featuresBitmask == 0)
                {
                    supportsFeatures = true;
                }
                else if (currentFamilyProperties != null)
                {
                    int unsatisfiedFeatures = featuresBitmask;
                    int queueFamilyCount = currentFamilyProperties.capacity();
                    for (int j = 0; j < queueFamilyCount; j++)
                    {
                        // Check each of the queue families supported by the device. Check the usage flags against the
                        // required features bitmask. If we find one with the graphics flag enabled, that device can do graphics.
                        VkQueueFamilyProperties family = currentFamilyProperties.get(j);
                        int matchedFeatures = (family.queueFlags() & unsatisfiedFeatures);
                        if (matchedFeatures != 0)
                        {
                            // Zero out the bit(s) with features we've satisfied with this queue family.
                            int invertedFeatures = ~matchedFeatures;
                            unsatisfiedFeatures = unsatisfiedFeatures & invertedFeatures;

                            // If we meet this case, we've found queue families that meet all of our requested features.
                            if (unsatisfiedFeatures == 0)
                            {
                                supportsFeatures = true;
                                break;
                            }
                        }
                    }
                }

                if (!supportsFeatures)
                {
                    currentProperties.free();
                    currentFamilyProperties.free();
                    continue;
                }

                // Check how many extensions we have and store in the size buffer.
                vulkanResult = vkEnumerateDeviceExtensionProperties(currentDevice, (String)null, sizeBuffer, null);
                if (vulkanResult != VK_SUCCESS)
                {
                    currentProperties.free();
                    currentFamilyProperties.free();
                    return false;
                }

                // Get the extension info.
                VkExtensionProperties.Buffer currentExtensions = VkExtensionProperties.calloc(sizeBuffer.get(0));
                vkEnumerateDeviceExtensionProperties(currentDevice, (String)null, sizeBuffer, currentExtensions);
                if (vulkanResult != VK_SUCCESS)
                {
                    currentProperties.free();
                    currentFamilyProperties.free();
                    currentExtensions.free();
                    return false;
                }

                boolean hasExtensions = false;
                if (extensionNames == null || extensionNames.IsEmpty())
                {
                    hasExtensions = true;
                }
                else if (currentExtensions != null)
                {
                    int extensionCount = currentExtensions.capacity();
                    int foundExtensions = 0;
                    // Go through every supported extension and see if its name matches one of the requested. Since each
                    // extension should be unique, if we find as many matching extensions as we've requested, we must
                    // support all of them and this is a valid device.
                    for (int j = 0; j < extensionCount; j++)
                    {
                        String extensionName = currentExtensions.get(j).extensionNameString();
                        for (int k = 0; k < extensionNames.GetSize(); k++)
                        {
                            quorum.Libraries.Language.Types.Text_ quorumText = (quorum.Libraries.Language.Types.Text_) extensionNames.Get(i);
                            if (quorumText.GetValue().equals(extensionName))
                            {
                                foundExtensions++;
                                break;
                            }
                        }
                    }

                    if (foundExtensions >= extensionNames.GetSize())
                        hasExtensions = true;
                }

                if (!hasExtensions)
                {
                    currentProperties.free();
                    currentFamilyProperties.free();
                    currentExtensions.free();
                    continue;
                }

                VkPhysicalDeviceMemoryProperties currentMemory = VkPhysicalDeviceMemoryProperties.calloc();
                vkGetPhysicalDeviceMemoryProperties(currentDevice, currentMemory);
                long currentMemorySize = 0L;

                VkMemoryHeap.Buffer memoryHeaps = currentMemory.memoryHeaps();
                for (int j = 0; j < memoryHeaps.capacity(); j++)
                {
                    VkMemoryHeap heap = memoryHeaps.get(j);
                    if ((heap.flags() & VK_MEMORY_HEAP_DEVICE_LOCAL_BIT) != 0)
                    {
                        // This heap represents local storage on the GPU, or the GPU VRAM.
                        // The size is the size of VRAM in bytes. We want as much as possible.
                        long size = heap.size();
                        if (size > currentMemorySize)
                            currentMemorySize = size;
                    }
                }

                int currentDevicePriority = ConvertDeviceTypeToPriority(currentProperties.deviceType());

                if (candidateDevice == null || currentDevicePriority > candidateTypePriority || (currentDevicePriority == candidateTypePriority && currentMemorySize > candidateMemorySize))
                {
                    if (candidateDevice != null)
                    {
                        candidateProperties.free();
                        candidateFamilyProperties.free();
                        candidateExtensions.free();
                        candidateMemoryProperties.free();
                    }

                    candidateDevice = currentDevice;
                    candidateExtensions = currentExtensions;
                    candidateProperties = currentProperties;
                    candidateFamilyProperties = currentFamilyProperties;
                    candidateMemoryProperties = currentMemory;
                    candidateTypePriority = currentDevicePriority;

                    candidateMemorySize = currentMemorySize;
                }
            }

            // After we finish iterating through the devices, see if we got a valid option. If not, abort.
            if (candidateDevice == null)
                return false;

            physicalDevice = candidateDevice;
            deviceExtensions = candidateExtensions;
            deviceProperties = candidateProperties;
            quorumLimits = new quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits();
            VkPhysicalDeviceLimits limits = deviceProperties.limits();
            plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits plugin = quorumLimits.plugin_;
            plugin.SetLimits(limits);

            queueFamilyProperties = candidateFamilyProperties;
            memoryProperties = candidateMemoryProperties;

            deviceFeatures = VkPhysicalDeviceFeatures.calloc();
            vkGetPhysicalDeviceFeatures(physicalDevice, deviceFeatures);
        }

        return true;
    }

    public quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceLimits_ GetLimits() {
        return quorumLimits;
    }

    public VulkanInstance_ GetInstance()
    {
        return ((VulkanPhysicalDevice_)me_).GetInstance();
    }

    public VkInstance GetVkInstance()
    {
        VulkanInstance_ quorumInstance = GetInstance();
        return ((VulkanInstance)quorumInstance).plugin_.GetInstance();
    }

    /*
    Takes a physical device constant and returns an integer representing how high priority the type is for use, where
    higher is better. For example, we want to use a discrete GPU over an integrated one, so the former returns a
    higher priority value than the latter.
    */
    public int ConvertDeviceTypeToPriority(int deviceType)
    {
        switch(deviceType)
        {
            case VK_PHYSICAL_DEVICE_TYPE_OTHER:
                return 0;
            case VK_PHYSICAL_DEVICE_TYPE_CPU:
                return 1;
            case VK_PHYSICAL_DEVICE_TYPE_VIRTUAL_GPU:
                return 2;
            case VK_PHYSICAL_DEVICE_TYPE_INTEGRATED_GPU:
                return 3;
            case VK_PHYSICAL_DEVICE_TYPE_DISCRETE_GPU:
                return 4;
            default:
                return -1;
        }
    }

    public void Dispose()
    {
        if (physicalDevice == null)
            return;

        deviceExtensions.free();
        deviceProperties.free();
        queueFamilyProperties.free();
        memoryProperties.free();
        deviceFeatures.free();

        physicalDevice = null;
        deviceExtensions = null;
        deviceProperties = null;
        queueFamilyProperties = null;
        memoryProperties = null;
        deviceFeatures = null;
    }

    public VkPhysicalDevice GetDevice()
    {
        return physicalDevice;
    }

    public VkPhysicalDeviceProperties GetDeviceProperties()
    {
        return deviceProperties;
    }

    public VkExtensionProperties.Buffer GetDeviceExtensions()
    {
        return deviceExtensions;
    }

    public VkQueueFamilyProperties.Buffer GetQueueFamilyProperties()
    {
        return queueFamilyProperties;
    }

    public VkPhysicalDeviceMemoryProperties GetMemoryProperties()
    {
        return memoryProperties;
    }

    public VkPhysicalDeviceFeatures GetDeviceFeatures()
    {
        return deviceFeatures;
    }
}
