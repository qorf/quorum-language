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

    public int GetMaxTexelBufferElements() {
        return limits.maxTexelBufferElements();
    }

    public int GetMaxUniformBufferRange() {
        return limits.maxUniformBufferRange();
    }

    public int GetMaxStorageBufferRange() {
        return limits.maxStorageBufferRange();
    }

    public int GetMaxPushConstantsSize() {
        return limits.maxPushConstantsSize();
    }

    public int GetMaxMemoryAllocationCount() {
        return limits.maxMemoryAllocationCount();
    }

    public int GetMaxSamplerAllocationCount() {
        return limits.maxSamplerAllocationCount();
    }

    public int GetMaxPerStageDescriptorSamplers() {
        return limits.maxPerStageDescriptorSamplers();
    }

    public int GetMaxPerStageDescriptorUniformBuffers() {
        return limits.maxPerStageDescriptorUniformBuffers();
    }

    public int GetMaxPerStageDescriptorStorageBuffers() {
        return limits.maxPerStageDescriptorStorageBuffers();
    }

    public int GetMaxPerStageDescriptorSampledImages() {
        return limits.maxPerStageDescriptorSampledImages();
    }

    public int GetMaxPerStageDescriptorStorageImages() {
        return limits.maxPerStageDescriptorStorageImages();
    }

    public int GetMaxPerStageDescriptorInputAttachments() {
        return limits.maxPerStageDescriptorInputAttachments();
    }

    public int GetMaxPerStageResources() {
        return limits.maxPerStageResources();
    }

    public int GetMaxDescriptorSetSamplers() {
        return limits.maxDescriptorSetSamplers();
    }

    public int GetMaxDescriptorSetUniformBuffers() {
        return limits.maxDescriptorSetUniformBuffers();
    }

    public int GetMaxDescriptorSetUniformBuffersDynamic() {
        return limits.maxDescriptorSetUniformBuffersDynamic();
    }

    public int GetMaxDescriptorSetStorageBuffers() {
        return limits.maxDescriptorSetStorageBuffers();
    }

    public int GetMaxDescriptorSetStorageBuffersDynamic() {
        return limits.maxDescriptorSetStorageBuffersDynamic();
    }

    public int GetMaxDescriptorSetStorageImages() {
        return limits.maxDescriptorSetStorageImages();
    }

    public int GetMaxDescriptorSetInputAttachments() {
        return limits.maxDescriptorSetInputAttachments();
    }

    public int GetMaxVertexInputAttributes() {
        return limits.maxVertexInputAttributes();
    }

    public int GetMaxVertexInputBindings() {
        return limits.maxVertexInputBindings();
    }

    public int GetMaxVertexInputAttributeOffset() {
        return limits.maxVertexInputAttributeOffset();
    }

    public int GetMaxVertexInputBindingStride() {
        return limits.maxVertexInputBindingStride();
    }

    public int GetMaxVertexOutputComponents() {
        return limits.maxVertexOutputComponents();
    }

    public int GetMaxTessellationGenerationLevel() {
        return limits.maxTessellationGenerationLevel();
    }

    public int GetMaxTessellationPatchSize() {
        return limits.maxTessellationPatchSize();
    }

    public int GetMaxTessellationControlPerVertexInputComponents() {
        return limits.maxTessellationControlPerVertexInputComponents();
    }

    public int GetMaxTessellationControlPerVertexOutputComponents() {
        return limits.maxTessellationControlPerVertexOutputComponents();
    }

    public int GetMaxTessellationControlPerPatchOutputComponents() {
        return limits.maxTessellationControlPerPatchOutputComponents();
    }

    public int GetMaxTessellationControlTotalOutputComponents() {
        return limits.maxTessellationControlTotalOutputComponents();
    }

    public int GetMaxTessellationEvaluationInputComponents() {
        return limits.maxTessellationEvaluationInputComponents();
    }

    public int GetMaxTessellationEvaluationOutputComponents() {
        return limits.maxTessellationEvaluationOutputComponents();
    }

    public int GetMaxGeometryShaderInvocations() {
        return limits.maxGeometryShaderInvocations();
    }

    public int GetMaxGeometryInputComponents() {
        return limits.maxGeometryInputComponents();
    }

    public int GetMaxGeometryOutputComponents() {
        return limits.maxGeometryOutputComponents();
    }

    public int GetMaxGeometryOutputVertices() {
        return limits.maxGeometryOutputVertices();
    }

    public int GetMaxGeometryTotalOutputComponents() {
        return limits.maxGeometryTotalOutputComponents();
    }

    public int GetMaxFragmentInputComponents() {
        return limits.maxFragmentInputComponents();
    }
    public int GetMaxFragmentOutputAttachments() {
        return limits.maxFragmentOutputAttachments();
    }

    public int GetMaxFragmentDualSrcAttachments() {
        return limits.maxFragmentDualSrcAttachments();
    }

    public int GetMaxFragmentCombinedOutputResources() {
        return limits.maxFragmentCombinedOutputResources();
    }

    public int GetMaxComputeSharedMemorySize() {
        return limits.maxComputeSharedMemorySize();
    }

    public int GetMaxComputeWorkGroupCount(int index) {
        return limits.maxComputeWorkGroupCount(index);
    }

    public int GetMaxComputeWorkGroupInvocations() {
        return limits.maxComputeWorkGroupInvocations();
    }

    public int GetMaxComputeWorkGroupSize(int index) {
        return limits.maxComputeWorkGroupSize(index);
    }

    public int GetSubPixelPrecisionBits() {
        return limits.subPixelPrecisionBits();
    }

    public int GetSubTexelPrecisionBits() {
        return limits.subTexelPrecisionBits();
    }

    public int GetMipmapPrecisionBits() {
        return limits.mipmapPrecisionBits();
    }

    public int GetMaxDrawIndexedIndexValue() {
        return limits.maxDrawIndexedIndexValue();
    }

    public int GetMaxDrawIndirectCount() {
        return limits.maxDrawIndirectCount();
    }

    public double GetMaxSamplerLodBias() {
        return limits.maxSamplerLodBias();
    }

    public double GetMaxSamplerAnisotropy() {
        return limits.maxSamplerAnisotropy();
    }

    public int GetMaxViewports() {
        return limits.maxViewports();
    }

    public int GetMaxViewportDimensions(int index) {
        return limits.maxViewportDimensions(index);
    }

    public double GetViewportBoundsRange(int index) {
        return limits.viewportBoundsRange(index);
    }

    public int GetViewportSubPixelBits() {
        return limits.viewportSubPixelBits();
    }

    public int GetMinTexelOffset() {
        return limits.minTexelOffset();
    }

    public int GetMaxTexelOffset() {
        return limits.maxTexelOffset();
    }

    public int GetMinTexelGatherOffset() {
        return limits.minTexelGatherOffset();
    }

    public int GetMaxTexelGatherOffset() {
        return limits.maxTexelGatherOffset();
    }

    public double GetMinInterpolationOffset() {
        return limits.minInterpolationOffset();
    }

    public double GetMaxInterpolationOffset() {
        return limits.maxInterpolationOffset();
    }

    public int GetSubPixelInterpolationOffsetBits() {
        return limits.subPixelInterpolationOffsetBits();
    }

    public int GetMaxFramebufferWidth() {
        return limits.maxFramebufferWidth();
    }

    public int GetMaxFramebufferHeight() {
        return limits.maxFramebufferHeight();
    }

    public int GetMaxFramebufferLayers() {
        return limits.maxFramebufferLayers();
    }

    public int GetMaxColorAttachments() {
        return limits.maxColorAttachments();
    }

    public int GetMaxSampleMaskWords() {
        return limits.maxSampleMaskWords();
    }

    public double GetTimestampPeriod() {
        return limits.timestampPeriod();
    }

    public int GetMaxClipDistances() {
        return limits.maxClipDistances();
    }

    public int GetMaxCullDistances() {
        return limits.maxCullDistances();
    }

    public int GetMaxCombinedClipAndCullDistances() {
        return limits.maxCombinedClipAndCullDistances();
    }

    public int GetDiscreteQueuePriorities() {
        return limits.discreteQueuePriorities();
    }

    public double GetPointSizeRange(int index) {
        return limits.pointSizeRange(index);
    }

    public double GetLineWidthRange(int index) {
        return limits.lineWidthRange(index);
    }

    public double GetPointSizeGranularity() {
        return limits.pointSizeGranularity();
    }

    public double GetLineWidthGranularity() {
        return limits.lineWidthGranularity();
    }

    public boolean GetStrictLines() {
        return limits.strictLines();
    }

    public boolean GetStandardSampleLocations() {
        return limits.standardSampleLocations();
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
