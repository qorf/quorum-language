package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkPhysicalDeviceLimits;

public class VulkanPhysicalDeviceLimits {
    public java.lang.Object me_;
    VkPhysicalDeviceLimits limits = null;

    public int GetMaxImageDimension1D() {
        return limits.maxImageDimension1D();
    }
    public int GetMaxImageDimension2D() {
        return limits.maxImageDimension2D();
    }
    public int GetMaxImageDimension3D() {
        return limits.maxImageDimension3D();
    }
    public int GetMaxImageArrayLayers() {
        return limits.maxImageArrayLayers();
    }

    


    public int GetMaxPerStageDescriptorSampledImages() {

        return limits.maxPerStageDescriptorSampledImages();
    }

    public int GetMaxDescriptorSetSampledImages() {
        return limits.maxDescriptorSetSampledImages();
    }

    public void SetLimits(VkPhysicalDeviceLimits lim) {
        limits = lim;
    }

    public VkPhysicalDeviceLimits GetLimits() {
        return limits;
    }
}
