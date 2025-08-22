package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;

public class VulkanSwapchain
{
    public Object me_;

    private long vulkanSwapchainHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSurface_ quorumSurface, VulkanSwapchainInfo_ createInfo)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            // Start by extracting the Vulkan data structures from the plugins of our Quorum objects.
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VulkanPhysicalDevice pluginPhysicalDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice)quorumDevice.GetPhysicalDevice()).plugin_;
            VulkanSurface pluginSurface = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface)quorumSurface).plugin_;

            VkDevice vulkanDevice = pluginDevice.GetDevice();
            VkPhysicalDevice vulkanPhysicalDevice = pluginPhysicalDevice.GetDevice();
            long vulkanSurfacePointer = pluginSurface.GetSurfacePointer();

            // We'll need to check what the surface is able to do before we can start putting images to it.
            VkSurfaceCapabilitiesKHR surfaceCapabilities = VkSurfaceCapabilitiesKHR.calloc(stack);
            int vulkanResult = KHRSurface.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(vulkanPhysicalDevice, vulkanSurfacePointer, surfaceCapabilities);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            // Determine how many images we will use in the SwapChain.
            int maxImages = surfaceCapabilities.maxImageCount();
            int minImages = surfaceCapabilities.minImageCount();
            int imageCount = createInfo.GetImageCount();
            if (imageCount <= 0)
                imageCount = 3;
            if (maxImages != 0 && maxImages < imageCount)
                imageCount = maxImages;
            if (minImages > imageCount)
                imageCount = minImages;

            // Update the image count value stored in the Quorum level to reflect the actual number of images used.
            createInfo.SetImageCount(imageCount);


            // Figure out what available color formats we can use for the surface.
            IntBuffer sizeBuffer = stack.mallocInt(1);
            vulkanResult = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(vulkanPhysicalDevice, vulkanSurfacePointer, sizeBuffer, null);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            int formatsCount = sizeBuffer.get(0);
            if (formatsCount <= 0)
            {
                return false;
            }

            // The first call to vkGetPhysicalDeviceSurfaceFormatsKHR just gets us the count of how many formats there are.
            // We need to call it again with a buffer to store the results to get the formats themselves.
            VkSurfaceFormatKHR.Buffer surfaceFormats = VkSurfaceFormatKHR.calloc(formatsCount, stack);
            vulkanResult = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(vulkanPhysicalDevice, vulkanSurfacePointer, sizeBuffer, surfaceFormats);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            int imageFormat = createInfo.GetImageFormat();
            int colorSpace = createInfo.GetColorSpace();

            // Check through the surface formats to see if we have one that matches our requested format and color space.
            boolean parametersSupported = false;
            for (int i = 0; i< formatsCount; i++)
            {
                VkSurfaceFormatKHR surfaceFormat = surfaceFormats.get(i);
                if (surfaceFormat.format() == imageFormat && surfaceFormat.colorSpace() == colorSpace)
                {
                    parametersSupported = true;
                    createInfo.SetImageFormat(imageFormat);
                    createInfo.SetColorSpace(colorSpace);
                    break;
                }
            }

            if (!parametersSupported)
            {
                return false;
            }


            int imageWidth = createInfo.GetImageWidth();
            int imageHeight = createInfo.GetImageHeight();
            int finalWidth;
            int finalHeight;

            // Define the "extent", or the dimensions, of the images we'll be using for the Swapchain.
            VkExtent2D dimensions = VkExtent2D.calloc(stack);

            if (imageWidth <= 0)
            {
                finalWidth = surfaceCapabilities.maxImageExtent().width();
            }
            else
            {
                finalWidth = Math.min(imageWidth, surfaceCapabilities.maxImageExtent().width());
                finalWidth = Math.max(finalWidth, surfaceCapabilities.minImageExtent().width());
            }

            if (imageHeight <= 0)
            {
                finalHeight = surfaceCapabilities.maxImageExtent().height();
            }
            else
            {
                finalHeight = Math.min(imageHeight, surfaceCapabilities.maxImageExtent().height());
                finalHeight = Math.max(finalHeight, surfaceCapabilities.minImageExtent().height());
            }

            dimensions.set(finalWidth, finalHeight);
            createInfo.SetImageSize(finalWidth, finalHeight);

//            int imageWidth = createInfo.GetImageWidth();
//            int imageHeight = createInfo.GetImageHeight();
//
//            // Define the "extent", or the dimensions, of the images we'll be using for the Swapchain.
//            VkExtent2D dimensions = VkExtent2D.calloc(stack);
//            int finalWidth = Math.min(imageWidth, surfaceCapabilities.maxImageExtent().width());
//            finalWidth = Math.max(finalWidth, surfaceCapabilities.minImageExtent().width());
//
//            int finalHeight = Math.min(imageHeight, surfaceCapabilities.maxImageExtent().height());
//            finalHeight = Math.max(finalHeight, surfaceCapabilities.minImageExtent().height());

            dimensions.set(finalWidth, finalHeight);
            createInfo.SetImageSize(finalWidth, finalHeight);

            // If the image sharing mode is concurrent, the queue family indices MUST be provided.
            quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_ constants = (quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_) SharedClass.GetStaticClass("Libraries.Game.Graphics.Vulkan.VulkanConstants");
            if (createInfo.GetImageSharingMode() == constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__SHARING_MODE_CONCURRENT_()
                    && (createInfo.GetQueueFamilyIndices() == null || createInfo.GetQueueFamilyIndices().IsEmpty()))
            {
                return false;
            }

            // Create the info structure for the Swapchain.
            VkSwapchainCreateInfoKHR swapchainCreateInfo = VkSwapchainCreateInfoKHR.calloc(stack);
            swapchainCreateInfo.sType$Default();
            swapchainCreateInfo.surface(vulkanSurfacePointer);
            swapchainCreateInfo.minImageCount(imageCount);
            swapchainCreateInfo.imageFormat(imageFormat);
            swapchainCreateInfo.imageColorSpace(colorSpace);
            swapchainCreateInfo.imageExtent(dimensions);
            swapchainCreateInfo.imageArrayLayers(createInfo.GetImageArrayLayers());
            swapchainCreateInfo.imageUsage(createInfo.GetImageUsage());
            swapchainCreateInfo.imageSharingMode(createInfo.GetImageSharingMode());
            swapchainCreateInfo.compositeAlpha(createInfo.GetCompositeAlpha());
            swapchainCreateInfo.clipped(createInfo.GetClipped());

            // Set the preTransform if one is specified, or just use the one already in use otherwise.
            if (createInfo.GetPreTransform() <= 0)
            {
                swapchainCreateInfo.preTransform(surfaceCapabilities.currentTransform());
            }
            else
            {
                swapchainCreateInfo.preTransform(createInfo.GetPreTransform());
            }

            swapchainCreateInfo.presentMode(createInfo.GetPresentMode());

            // Set the array of indices for the queue families, if we have them. Only needed for concurrent access
            // (e.g., we have separate Graphics and Presentation queues)
            Array_ queueFamilyIndices = createInfo.GetQueueFamilyIndices();
            if (queueFamilyIndices != null && queueFamilyIndices.IsEmpty() == false)
            {
                IntBuffer indicesBuffer = stack.mallocInt(queueFamilyIndices.GetSize());
                for (int i = 0; i < queueFamilyIndices.GetSize(); i++)
                {
                    quorum.Libraries.Language.Types.Integer_ quorumInt = (quorum.Libraries.Language.Types.Integer_)queueFamilyIndices.Get(i);
                    indicesBuffer.put(quorumInt.GetValue());
                }
                indicesBuffer.flip();
                swapchainCreateInfo.queueFamilyIndexCount(queueFamilyIndices.GetSize());
                swapchainCreateInfo.pQueueFamilyIndices(indicesBuffer);
            }

            LongBuffer handleBuffer = stack.mallocLong(1);
            vulkanResult = KHRSwapchain.vkCreateSwapchainKHR(vulkanDevice, swapchainCreateInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            vulkanSwapchainHandle = handleBuffer.get(0);

            // After creating the swapchain, make sure we know how many images the system has provided for our swapchain.
            // We shouldn't assume the chain has actually given us the number we asked for.
            vulkanResult = KHRSwapchain.vkGetSwapchainImagesKHR(vulkanDevice, vulkanSwapchainHandle, sizeBuffer, null);
            if (vulkanResult != VK_SUCCESS)
            {
                return false;
            }

            createInfo.SetImageCount(sizeBuffer.get(0));
        }

        return true;
    }

    public boolean GetSwapchainImages(Array_ imageViewArray, VulkanDevice_ quorumDevice)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;
            VkDevice vulkanDevice = pluginDevice.GetDevice();

            IntBuffer sizeBuffer = stack.mallocInt(1);
            // Get the number of images directly from the swapchain.
            int vulkanResult = KHRSwapchain.vkGetSwapchainImagesKHR(vulkanDevice, vulkanSwapchainHandle, sizeBuffer, null);
            if (vulkanResult != VK_SUCCESS)
                return false;

            int imageCount = sizeBuffer.get(0);
            LongBuffer swapChainImageHandles = stack.mallocLong(imageCount);
            vulkanResult = KHRSwapchain.vkGetSwapchainImagesKHR(vulkanDevice, vulkanSwapchainHandle, sizeBuffer, swapChainImageHandles);
            if (vulkanResult != VK_SUCCESS)
                return false;

            // Get a reference to the swapchain info from the Quorum side. We need the image width/height so we can
            // assign that value to the images, so their dimensions are known later.
            quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_ quorumSwapchain = (quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_)me_;
            int imageWidth = quorumSwapchain.GetImageWidth();
            int imageHeight = quorumSwapchain.GetImageHeight();

            for (int i = 0; i < imageCount; i++)
            {
                quorum.Libraries.Game.Graphics.Vulkan.VulkanImage image = new quorum.Libraries.Game.Graphics.Vulkan.VulkanImage();
                VulkanImage imagePlugin = image.plugin_;
                imagePlugin.LoadFromHandle(swapChainImageHandles.get(i), quorumDevice, imageWidth, imageHeight);
                imageViewArray.Add(image);
            }
        }

        return true;
    }

    public long GetVulkanSwapchainHandle()
    {
        return vulkanSwapchainHandle;
    }
}
