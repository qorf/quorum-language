package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkPhysicalDeviceLimits;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPhysicalDeviceLimits;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPhysicalDeviceLimits;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceLimitsStrategy;

/*
The Java side of Quorum's VulkanPhysicalDeviceLimits. Each limit is read by
its number, through a strategy: LWJGL's structure on the desktop, or arrays
filled by Quorum's own Vulkan layer on iOS and Android.
*/
public class VulkanPhysicalDeviceLimits {
    public java.lang.Object me_;

    private final VulkanPhysicalDeviceLimitsStrategy strategy = VulkanPlatform.UsesNativeVulkan()
            ? new NativeVulkanPhysicalDeviceLimits() : new DesktopVulkanPhysicalDeviceLimits();

    public int GetMaxImageDimension1D()
    {
        return strategy.GetInteger(0, 0);
    }

    public int GetMaxImageDimension2D()
    {
        return strategy.GetInteger(1, 0);
    }

    public int GetMaxImageDimension3D()
    {
        return strategy.GetInteger(2, 0);
    }

    public int GetMaxImageArrayLayers()
    {
        return strategy.GetInteger(3, 0);
    }

    public int GetMaxTexelBufferElements()
    {
        return strategy.GetInteger(4, 0);
    }

    public int GetMaxUniformBufferRange()
    {
        return strategy.GetInteger(5, 0);
    }

    public int GetMaxStorageBufferRange()
    {
        return strategy.GetInteger(6, 0);
    }

    public int GetMaxPushConstantsSize()
    {
        return strategy.GetInteger(7, 0);
    }

    public int GetMaxMemoryAllocationCount()
    {
        return strategy.GetInteger(8, 0);
    }

    public int GetMaxSamplerAllocationCount()
    {
        return strategy.GetInteger(9, 0);
    }

    public int GetMaxPerStageDescriptorSamplers()
    {
        return strategy.GetInteger(10, 0);
    }

    public int GetMaxPerStageDescriptorUniformBuffers()
    {
        return strategy.GetInteger(11, 0);
    }

    public int GetMaxPerStageDescriptorStorageBuffers()
    {
        return strategy.GetInteger(12, 0);
    }

    public int GetMaxPerStageDescriptorSampledImages()
    {
        return strategy.GetInteger(13, 0);
    }

    public int GetMaxPerStageDescriptorStorageImages()
    {
        return strategy.GetInteger(14, 0);
    }

    public int GetMaxPerStageDescriptorInputAttachments()
    {
        return strategy.GetInteger(15, 0);
    }

    public int GetMaxPerStageResources()
    {
        return strategy.GetInteger(16, 0);
    }

    public int GetMaxDescriptorSetSamplers()
    {
        return strategy.GetInteger(17, 0);
    }

    public int GetMaxDescriptorSetUniformBuffers()
    {
        return strategy.GetInteger(18, 0);
    }

    public int GetMaxDescriptorSetUniformBuffersDynamic()
    {
        return strategy.GetInteger(19, 0);
    }

    public int GetMaxDescriptorSetStorageBuffers()
    {
        return strategy.GetInteger(20, 0);
    }

    public int GetMaxDescriptorSetStorageBuffersDynamic()
    {
        return strategy.GetInteger(21, 0);
    }

    public int GetMaxDescriptorSetStorageImages()
    {
        return strategy.GetInteger(22, 0);
    }

    public int GetMaxDescriptorSetInputAttachments()
    {
        return strategy.GetInteger(23, 0);
    }

    public int GetMaxVertexInputAttributes()
    {
        return strategy.GetInteger(24, 0);
    }

    public int GetMaxVertexInputBindings()
    {
        return strategy.GetInteger(25, 0);
    }

    public int GetMaxVertexInputAttributeOffset()
    {
        return strategy.GetInteger(26, 0);
    }

    public int GetMaxVertexInputBindingStride()
    {
        return strategy.GetInteger(27, 0);
    }

    public int GetMaxVertexOutputComponents()
    {
        return strategy.GetInteger(28, 0);
    }

    public int GetMaxTessellationGenerationLevel()
    {
        return strategy.GetInteger(29, 0);
    }

    public int GetMaxTessellationPatchSize()
    {
        return strategy.GetInteger(30, 0);
    }

    public int GetMaxTessellationControlPerVertexInputComponents()
    {
        return strategy.GetInteger(31, 0);
    }

    public int GetMaxTessellationControlPerVertexOutputComponents()
    {
        return strategy.GetInteger(32, 0);
    }

    public int GetMaxTessellationControlPerPatchOutputComponents()
    {
        return strategy.GetInteger(33, 0);
    }

    public int GetMaxTessellationControlTotalOutputComponents()
    {
        return strategy.GetInteger(34, 0);
    }

    public int GetMaxTessellationEvaluationInputComponents()
    {
        return strategy.GetInteger(35, 0);
    }

    public int GetMaxTessellationEvaluationOutputComponents()
    {
        return strategy.GetInteger(36, 0);
    }

    public int GetMaxGeometryShaderInvocations()
    {
        return strategy.GetInteger(37, 0);
    }

    public int GetMaxGeometryInputComponents()
    {
        return strategy.GetInteger(38, 0);
    }

    public int GetMaxGeometryOutputComponents()
    {
        return strategy.GetInteger(39, 0);
    }

    public int GetMaxGeometryOutputVertices()
    {
        return strategy.GetInteger(40, 0);
    }

    public int GetMaxGeometryTotalOutputComponents()
    {
        return strategy.GetInteger(41, 0);
    }

    public int GetMaxFragmentInputComponents()
    {
        return strategy.GetInteger(42, 0);
    }

    public int GetMaxFragmentOutputAttachments()
    {
        return strategy.GetInteger(43, 0);
    }

    public int GetMaxFragmentDualSrcAttachments()
    {
        return strategy.GetInteger(44, 0);
    }

    public int GetMaxFragmentCombinedOutputResources()
    {
        return strategy.GetInteger(45, 0);
    }

    public int GetMaxComputeSharedMemorySize()
    {
        return strategy.GetInteger(46, 0);
    }

    public int GetMaxComputeWorkGroupCount(int index)
    {
        return strategy.GetInteger(47, index);
    }

    public int GetMaxComputeWorkGroupInvocations()
    {
        return strategy.GetInteger(48, 0);
    }

    public int GetMaxComputeWorkGroupSize(int index)
    {
        return strategy.GetInteger(49, index);
    }

    public int GetSubPixelPrecisionBits()
    {
        return strategy.GetInteger(50, 0);
    }

    public int GetSubTexelPrecisionBits()
    {
        return strategy.GetInteger(51, 0);
    }

    public int GetMipmapPrecisionBits()
    {
        return strategy.GetInteger(52, 0);
    }

    public int GetMaxDrawIndexedIndexValue()
    {
        return strategy.GetInteger(53, 0);
    }

    public int GetMaxDrawIndirectCount()
    {
        return strategy.GetInteger(54, 0);
    }

    public double GetMaxSamplerLodBias()
    {
        return strategy.GetNumber(55, 0);
    }

    public double GetMaxSamplerAnisotropy()
    {
        return strategy.GetNumber(56, 0);
    }

    public int GetMaxViewports()
    {
        return strategy.GetInteger(57, 0);
    }

    public int GetMaxViewportDimensions(int index)
    {
        return strategy.GetInteger(58, index);
    }

    public double GetViewportBoundsRange(int index)
    {
        return strategy.GetNumber(59, index);
    }

    public int GetViewportSubPixelBits()
    {
        return strategy.GetInteger(60, 0);
    }

    public int GetMinTexelOffset()
    {
        return strategy.GetInteger(61, 0);
    }

    public int GetMaxTexelOffset()
    {
        return strategy.GetInteger(62, 0);
    }

    public int GetMinTexelGatherOffset()
    {
        return strategy.GetInteger(63, 0);
    }

    public int GetMaxTexelGatherOffset()
    {
        return strategy.GetInteger(64, 0);
    }

    public double GetMinInterpolationOffset()
    {
        return strategy.GetNumber(65, 0);
    }

    public double GetMaxInterpolationOffset()
    {
        return strategy.GetNumber(66, 0);
    }

    public int GetSubPixelInterpolationOffsetBits()
    {
        return strategy.GetInteger(67, 0);
    }

    public int GetMaxFramebufferWidth()
    {
        return strategy.GetInteger(68, 0);
    }

    public int GetMaxFramebufferHeight()
    {
        return strategy.GetInteger(69, 0);
    }

    public int GetMaxFramebufferLayers()
    {
        return strategy.GetInteger(70, 0);
    }

    public int GetMaxColorAttachments()
    {
        return strategy.GetInteger(71, 0);
    }

    public int GetMaxSampleMaskWords()
    {
        return strategy.GetInteger(72, 0);
    }

    public double GetTimestampPeriod()
    {
        return strategy.GetNumber(73, 0);
    }

    public int GetMaxClipDistances()
    {
        return strategy.GetInteger(74, 0);
    }

    public int GetMaxCullDistances()
    {
        return strategy.GetInteger(75, 0);
    }

    public int GetMaxCombinedClipAndCullDistances()
    {
        return strategy.GetInteger(76, 0);
    }

    public int GetDiscreteQueuePriorities()
    {
        return strategy.GetInteger(77, 0);
    }

    public double GetPointSizeRange(int index)
    {
        return strategy.GetNumber(78, index);
    }

    public double GetLineWidthRange(int index)
    {
        return strategy.GetNumber(79, index);
    }

    public double GetPointSizeGranularity()
    {
        return strategy.GetNumber(80, 0);
    }

    public double GetLineWidthGranularity()
    {
        return strategy.GetNumber(81, 0);
    }

    public int GetFrameBufferColorSampleCount()
    {
        return strategy.GetInteger(82, 0);
    }

    public int GetFrameBufferDepthSampleCount()
    {
        return strategy.GetInteger(83, 0);
    }

    public int GetFrameBufferStencilSampleCount()
    {
        return strategy.GetInteger(84, 0);
    }

    public boolean GetStrictLines()
    {
        return strategy.GetBoolean(85, 0);
    }

    public boolean GetStandardSampleLocations()
    {
        return strategy.GetBoolean(86, 0);
    }

    public int GetMaxDescriptorSetSampledImages()
    {
        return strategy.GetInteger(87, 0);
    }

    // LWJGL's structure, for DesktopVulkanPhysicalDevice only.
    public void SetLimits(VkPhysicalDeviceLimits lim)
    {
        ((DesktopVulkanPhysicalDeviceLimits)strategy).SetLimits(lim);
    }

    public VkPhysicalDeviceLimits GetLimits()
    {
        return ((DesktopVulkanPhysicalDeviceLimits)strategy).GetLimits();
    }

    public VulkanPhysicalDeviceLimitsStrategy GetStrategy()
    {
        return strategy;
    }
}
