package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import org.lwjgl.vulkan.VkPhysicalDeviceLimits;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceLimitsStrategy;

public class DesktopVulkanPhysicalDeviceLimits implements VulkanPhysicalDeviceLimitsStrategy {
    VkPhysicalDeviceLimits limits = null;





















































































    public void SetLimits(VkPhysicalDeviceLimits lim) {
        limits = lim;
    }

    public VkPhysicalDeviceLimits GetLimits() {
        return limits;
    }

    public int GetInteger(int limit, int index)
    {
        switch (limit)
        {
            case 0: return limits.maxImageDimension1D();
            case 1: return limits.maxImageDimension2D();
            case 2: return limits.maxImageDimension3D();
            case 3: return limits.maxImageArrayLayers();
            case 4: return limits.maxTexelBufferElements();
            case 5: return limits.maxUniformBufferRange();
            case 6: return limits.maxStorageBufferRange();
            case 7: return limits.maxPushConstantsSize();
            case 8: return limits.maxMemoryAllocationCount();
            case 9: return limits.maxSamplerAllocationCount();
            case 10: return limits.maxPerStageDescriptorSamplers();
            case 11: return limits.maxPerStageDescriptorUniformBuffers();
            case 12: return limits.maxPerStageDescriptorStorageBuffers();
            case 13: return limits.maxPerStageDescriptorSampledImages();
            case 14: return limits.maxPerStageDescriptorStorageImages();
            case 15: return limits.maxPerStageDescriptorInputAttachments();
            case 16: return limits.maxPerStageResources();
            case 17: return limits.maxDescriptorSetSamplers();
            case 18: return limits.maxDescriptorSetUniformBuffers();
            case 19: return limits.maxDescriptorSetUniformBuffersDynamic();
            case 20: return limits.maxDescriptorSetStorageBuffers();
            case 21: return limits.maxDescriptorSetStorageBuffersDynamic();
            case 22: return limits.maxDescriptorSetStorageImages();
            case 23: return limits.maxDescriptorSetInputAttachments();
            case 24: return limits.maxVertexInputAttributes();
            case 25: return limits.maxVertexInputBindings();
            case 26: return limits.maxVertexInputAttributeOffset();
            case 27: return limits.maxVertexInputBindingStride();
            case 28: return limits.maxVertexOutputComponents();
            case 29: return limits.maxTessellationGenerationLevel();
            case 30: return limits.maxTessellationPatchSize();
            case 31: return limits.maxTessellationControlPerVertexInputComponents();
            case 32: return limits.maxTessellationControlPerVertexOutputComponents();
            case 33: return limits.maxTessellationControlPerPatchOutputComponents();
            case 34: return limits.maxTessellationControlTotalOutputComponents();
            case 35: return limits.maxTessellationEvaluationInputComponents();
            case 36: return limits.maxTessellationEvaluationOutputComponents();
            case 37: return limits.maxGeometryShaderInvocations();
            case 38: return limits.maxGeometryInputComponents();
            case 39: return limits.maxGeometryOutputComponents();
            case 40: return limits.maxGeometryOutputVertices();
            case 41: return limits.maxGeometryTotalOutputComponents();
            case 42: return limits.maxFragmentInputComponents();
            case 43: return limits.maxFragmentOutputAttachments();
            case 44: return limits.maxFragmentDualSrcAttachments();
            case 45: return limits.maxFragmentCombinedOutputResources();
            case 46: return limits.maxComputeSharedMemorySize();
            case 47: return limits.maxComputeWorkGroupCount(index);
            case 48: return limits.maxComputeWorkGroupInvocations();
            case 49: return limits.maxComputeWorkGroupSize(index);
            case 50: return limits.subPixelPrecisionBits();
            case 51: return limits.subTexelPrecisionBits();
            case 52: return limits.mipmapPrecisionBits();
            case 53: return limits.maxDrawIndexedIndexValue();
            case 54: return limits.maxDrawIndirectCount();
            case 57: return limits.maxViewports();
            case 58: return limits.maxViewportDimensions(index);
            case 60: return limits.viewportSubPixelBits();
            case 61: return limits.minTexelOffset();
            case 62: return limits.maxTexelOffset();
            case 63: return limits.minTexelGatherOffset();
            case 64: return limits.maxTexelGatherOffset();
            case 67: return limits.subPixelInterpolationOffsetBits();
            case 68: return limits.maxFramebufferWidth();
            case 69: return limits.maxFramebufferHeight();
            case 70: return limits.maxFramebufferLayers();
            case 71: return limits.maxColorAttachments();
            case 72: return limits.maxSampleMaskWords();
            case 74: return limits.maxClipDistances();
            case 75: return limits.maxCullDistances();
            case 76: return limits.maxCombinedClipAndCullDistances();
            case 77: return limits.discreteQueuePriorities();
            case 82: return limits.framebufferColorSampleCounts();
            case 83: return limits.framebufferDepthSampleCounts();
            case 84: return limits.framebufferStencilSampleCounts();
            case 87: return limits.maxDescriptorSetSampledImages();
            default: return 0;
        }
    }

    public double GetNumber(int limit, int index)
    {
        switch (limit)
        {
            case 55: return limits.maxSamplerLodBias();
            case 56: return limits.maxSamplerAnisotropy();
            case 59: return limits.viewportBoundsRange(index);
            case 65: return limits.minInterpolationOffset();
            case 66: return limits.maxInterpolationOffset();
            case 73: return limits.timestampPeriod();
            case 78: return limits.pointSizeRange(index);
            case 79: return limits.lineWidthRange(index);
            case 80: return limits.pointSizeGranularity();
            case 81: return limits.lineWidthGranularity();
            default: return 0;
        }
    }

    public boolean GetBoolean(int limit, int index)
    {
        switch (limit)
        {
            case 85: return limits.strictLines();
            case 86: return limits.standardSampleLocations();
            default: return false;
        }
    }
}
