package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPlatform;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanSwapchainStrategy;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchainInfo_;

/*
The swapchain on iOS and Android, chosen as the desktop chooses it. Three
differences, for phones:
- If the surface doesn't offer the requested format and color space (many
  Android phones offer R8G8B8A8 but not the desktop's default B8G8R8A8), the
  other order of the same channels is used, or else the first format the
  surface offers. The info's image format and image view format are both
  changed, since the image views and the screen's render pass use the
  latter.
- If the surface doesn't offer the requested composite alpha (many Android
  phones offer only "inherit"), the first one it offers is used.
- On Android, when the phone is turned from its natural orientation, the
  swapchain is made upright (no pre-rotation) at the window's turned size, and
  Android's compositor turns it. (NativeVulkanGraphics ignores the "suboptimal"
  Android reports for that.)
*/
public class NativeVulkanSwapchain implements VulkanSwapchainStrategy
{
    // The places of the values in GetSurfaceCapabilities' result.
    private static final int MIN_IMAGES = 0;
    private static final int MAX_IMAGES = 1;
    private static final int CURRENT_WIDTH = 2;
    private static final int CURRENT_HEIGHT = 3;
    private static final int CURRENT_TRANSFORM = 4;
    private static final int COMPOSITE_ALPHA = 5;

    // VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR and ROTATE_270, and IDENTITY.
    private static final int ROTATE_90 = 0x2;
    private static final int ROTATE_270 = 0x8;
    private static final int IDENTITY = 0x1;
    // VK_PRESENT_MODE_FIFO_KHR, which every surface offers.
    private static final int PRESENT_MODE_FIFO = 2;
    // VK_FORMAT_R8G8B8A8_UNORM, VK_FORMAT_B8G8R8A8_UNORM, and their sRGB forms.
    private static final int R8G8B8A8_UNORM = 37;
    private static final int R8G8B8A8_SRGB = 43;
    private static final int B8G8R8A8_UNORM = 44;
    private static final int B8G8R8A8_SRGB = 50;

    private final VulkanSwapchain peer;
    private long swapchainHandle = 0L;

    public NativeVulkanSwapchain(VulkanSwapchain peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSurface_ quorumSurface, VulkanSwapchainInfo_ createInfo)
    {
        NativeVulkanDevice device = NativeVulkan.Device(quorumDevice);
        long physicalDevice = device.GetPhysicalDeviceHandle();
        long surface = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSurface)quorumSurface).plugin_.GetSurfacePointer();

        int[] capabilities = GetSurfaceCapabilities(physicalDevice, surface);
        if (capabilities == null)
            return false;

        // Determine how many images we will use in the swapchain.
        int maxImages = capabilities[MAX_IMAGES];
        int minImages = capabilities[MIN_IMAGES];
        int imageCount = createInfo.GetImageCount();
        if (imageCount <= 0)
            imageCount = 3;
        if (maxImages != 0 && maxImages < imageCount)
            imageCount = maxImages;
        if (minImages > imageCount)
            imageCount = minImages;
        createInfo.SetImageCount(imageCount);

        // The requested format and color space must be offered.
        int[] formats = GetSurfaceFormats(physicalDevice, surface);
        if (formats == null || formats.length == 0)
            return false;
        int imageFormat = createInfo.GetImageFormat();
        int colorSpace = createInfo.GetColorSpace();
        if (!Offers(formats, imageFormat, colorSpace))
        {
            int swapped = SwapChannelOrder(imageFormat);
            if (swapped != imageFormat && Offers(formats, swapped, colorSpace))
                imageFormat = swapped;
            else
            {
                imageFormat = formats[0];
                colorSpace = formats[1];
            }
            createInfo.SetImageFormat(imageFormat);
            createInfo.SetColorSpace(colorSpace);
            if (createInfo.GetImageViewInfo() != null)
                createInfo.GetImageViewInfo().SetFormat(imageFormat);
        }

        // The surface's size, as on the desktop.
        int finalWidth = capabilities[CURRENT_WIDTH];
        int finalHeight = capabilities[CURRENT_HEIGHT];
        int preTransform = createInfo.GetPreTransform() <= 0 ? capabilities[CURRENT_TRANSFORM] : createInfo.GetPreTransform();
        if (VulkanPlatform.IsAndroid() && createInfo.GetPreTransform() <= 0
                && (capabilities[CURRENT_TRANSFORM] & (ROTATE_90 | ROTATE_270)) != 0)
        {
            int swap = finalWidth;
            finalWidth = finalHeight;
            finalHeight = swap;
            preTransform = IDENTITY;
        }
        if (finalWidth <= 0)
            finalWidth = 1;
        if (finalHeight <= 0)
            finalHeight = 1;
        createInfo.SetImageSize(finalWidth, finalHeight);

        // If the image sharing mode is concurrent, the queue family indices must be given.
        quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_ constants = (quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_)SharedClass.GetStaticClass("Libraries.Game.Graphics.Vulkan.VulkanConstants");
        Array_ queueFamilyIndices = createInfo.GetQueueFamilyIndices();
        if (createInfo.GetImageSharingMode() == constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__SHARING_MODE_CONCURRENT_()
                && (queueFamilyIndices == null || queueFamilyIndices.IsEmpty()))
            return false;

        int compositeAlpha = createInfo.GetCompositeAlpha();
        int offeredAlpha = capabilities[COMPOSITE_ALPHA];
        if ((compositeAlpha & offeredAlpha) == 0 && offeredAlpha != 0)
            compositeAlpha = Integer.lowestOneBit(offeredAlpha);

        // Use the requested present mode only if the surface offers it. FIFO is always offered.
        int requestedMode = createInfo.GetPresentMode();
        int[] modes = GetPresentModes(physicalDevice, surface);
        boolean supported = false;
        for (int mode : modes)
        {
            if (mode == requestedMode)
                supported = true;
        }
        if (!supported)
            createInfo.SetPresentMode(PRESENT_MODE_FIFO);

        swapchainHandle = CreateSwapchain(device.GetDeviceHandle(), surface, imageCount, imageFormat, colorSpace, finalWidth, finalHeight,
                createInfo.GetImageArrayLayers(), createInfo.GetImageUsage(), createInfo.GetImageSharingMode(), compositeAlpha,
                createInfo.GetClipped(), preTransform, createInfo.GetPresentMode(), NativeVulkan.ToIntegers(queueFamilyIndices));
        if (swapchainHandle == 0L)
            return false;

        // The system may give more images than asked for.
        createInfo.SetImageCount(GetImages(device.GetDeviceHandle(), swapchainHandle).length);
        return true;
    }

    public boolean GetSwapchainImages(Array_ imageViewArray, VulkanDevice_ quorumDevice)
    {
        long[] handles = GetImages(NativeVulkan.DeviceHandle(quorumDevice), swapchainHandle);
        if (handles == null)
            return false;

        quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_ quorumSwapchain = (quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_)peer.me_;
        int imageWidth = quorumSwapchain.GetImageWidth();
        int imageHeight = quorumSwapchain.GetImageHeight();
        for (long handle : handles)
        {
            quorum.Libraries.Game.Graphics.Vulkan.VulkanImage image = new quorum.Libraries.Game.Graphics.Vulkan.VulkanImage();
            image.plugin_.LoadFromHandle(handle, quorumDevice, imageWidth, imageHeight);
            imageViewArray.Add(image);
        }
        return true;
    }

    public long GetVulkanSwapchainHandle()
    {
        return swapchainHandle;
    }

    // formats holds pairs of format and color space.
    private static boolean Offers(int[] formats, int format, int colorSpace)
    {
        for (int i = 0; i + 1 < formats.length; i += 2)
        {
            if (formats[i] == format && formats[i + 1] == colorSpace)
                return true;
        }
        return false;
    }

    // The same 8-bit channels in the other order (RGBA or BGRA), or the format as it is.
    private static int SwapChannelOrder(int format)
    {
        switch (format)
        {
            case R8G8B8A8_UNORM: return B8G8R8A8_UNORM;
            case B8G8R8A8_UNORM: return R8G8B8A8_UNORM;
            case R8G8B8A8_SRGB: return B8G8R8A8_SRGB;
            case B8G8R8A8_SRGB: return R8G8B8A8_SRGB;
            default: return format;
        }
    }

    public void DisposeNative()
    {
        if (swapchainHandle == 0L)
            return;
        VulkanDevice_ quorumDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSwapchain_)peer.me_).GetDevice();
        DestroySwapchain(NativeVulkan.DeviceHandle(quorumDevice), swapchainHandle);
        swapchainHandle = 0L;
    }

    // Minimum and maximum image counts, current width, height and transform,
    // and the composite alphas offered; null if Vulkan failed.
    private static native int[] GetSurfaceCapabilities(long physicalDevice, long surface);
    // Pairs of format and color space.
    private static native int[] GetSurfaceFormats(long physicalDevice, long surface);
    private static native int[] GetPresentModes(long physicalDevice, long surface);
    private static native long CreateSwapchain(long device, long surface, int imageCount, int imageFormat, int colorSpace, int width,
            int height, int arrayLayers, int usage, int sharingMode, int compositeAlpha, boolean clipped, int preTransform, int presentMode,
            int[] queueFamilyIndices);
    private static native long[] GetImages(long device, long swapchain);
    private static native void DestroySwapchain(long device, long swapchain);
}
