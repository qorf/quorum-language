package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceFeaturesStrategy;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

import static org.lwjgl.vulkan.VK10.vkGetPhysicalDeviceFeatures;
import static org.lwjgl.vulkan.VK11.vkGetPhysicalDeviceFeatures2;

public class DesktopVulkanPhysicalDeviceFeatures implements VulkanPhysicalDeviceFeaturesStrategy
{
    private final plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceFeatures peer;

    public DesktopVulkanPhysicalDeviceFeatures(plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDeviceFeatures peer)
    {
        this.peer = peer;
    }

    // Fields for the base features objects, as well as the structs for features introduced in later Vulkan releases.
    public VkPhysicalDeviceFeatures vulkanFeatures = null;
    public VkPhysicalDeviceFeatures2 vulkanFeatures2 = null;
    public VkPhysicalDeviceVulkan11Features features11 = null;
    public VkPhysicalDeviceVulkan12Features features12 = null;
    public VkPhysicalDeviceVulkan13Features features13 = null;
    public VkPhysicalDeviceVulkan14Features features14 = null;

    public void CreateNative()
    {
        vulkanFeatures = VkPhysicalDeviceFeatures.calloc();
        vulkanFeatures2 = VkPhysicalDeviceFeatures2.calloc();

        features11 = VkPhysicalDeviceVulkan11Features.calloc();
        features12 = VkPhysicalDeviceVulkan12Features.calloc();
        features13 = VkPhysicalDeviceVulkan13Features.calloc();
        features14 = VkPhysicalDeviceVulkan14Features.calloc();

        features11.sType$Default();
        features12.sType$Default();
        features13.sType$Default();
        features14.sType$Default();

        vulkanFeatures2.features(vulkanFeatures);
        vulkanFeatures2.pNext(features11);
        features11.pNext(features12.address());
        features12.pNext(features13.address());
        features13.pNext(features14.address());
    }

    public void GetSupportedDeviceFeatures(VulkanPhysicalDevice_ quorumDevice)
    {
        VkPhysicalDevice device = ((VulkanPhysicalDevice)quorumDevice).plugin_.GetDevice();
        vkGetPhysicalDeviceFeatures(device, vulkanFeatures);
        vkGetPhysicalDeviceFeatures2(device, vulkanFeatures2);
    }

    public void DisposeNative()
    {
        if (vulkanFeatures != null)
        {
            vulkanFeatures.free();
            vulkanFeatures2.free();
            features11.free();
            features12.free();
            features13.free();
            features14.free();

            vulkanFeatures = null;
            vulkanFeatures2 = null;
            features11 = null;
            features12 = null;
            features13 = null;
            features14 = null;
        }
    }

    public boolean Has(int feature)
    {
        switch (feature)
        {
            case 0: return vulkanFeatures.robustBufferAccess(); // RobustBufferAccess
            case 1: return vulkanFeatures.fullDrawIndexUint32(); // FullDrawIndexUint32
            case 2: return vulkanFeatures.imageCubeArray(); // ImageCubeArray
            case 3: return vulkanFeatures.independentBlend(); // IndependentBlend
            case 4: return vulkanFeatures.geometryShader(); // GeometryShader
            case 5: return vulkanFeatures.tessellationShader(); // TessellationShader
            case 6: return vulkanFeatures.sampleRateShading(); // SampleRateShading
            case 7: return vulkanFeatures.dualSrcBlend(); // DualSrcBlend
            case 8: return vulkanFeatures.logicOp(); // LogicOp
            case 9: return vulkanFeatures.multiDrawIndirect(); // MultiDrawIndirect
            case 10: return vulkanFeatures.drawIndirectFirstInstance(); // DrawIndirectFirstInstance
            case 11: return vulkanFeatures.depthClamp(); // DepthClamp
            case 12: return vulkanFeatures.depthBiasClamp(); // DepthBiasClamp
            case 13: return vulkanFeatures.fillModeNonSolid(); // FillModeNonSolid
            case 14: return vulkanFeatures.depthBounds(); // DepthBounds
            case 15: return vulkanFeatures.wideLines(); // WideLines
            case 16: return vulkanFeatures.largePoints(); // LargePoints
            case 17: return vulkanFeatures.alphaToOne(); // AlphaToOne
            case 18: return vulkanFeatures.multiViewport(); // MultiViewport
            case 19: return vulkanFeatures.samplerAnisotropy(); // SamplerAnisotropy
            case 20: return vulkanFeatures.textureCompressionETC2(); // TextureCompressionETC2
            case 21: return vulkanFeatures.textureCompressionASTC_LDR(); // TextureCompressionASTC_LDR
            case 22: return vulkanFeatures.textureCompressionBC(); // TextureCompressionBC
            case 23: return vulkanFeatures.occlusionQueryPrecise(); // OcclusionQueryPrecise
            case 24: return vulkanFeatures.pipelineStatisticsQuery(); // PipelineStatisticsQuery
            case 25: return vulkanFeatures.vertexPipelineStoresAndAtomics(); // VertexPipelineStoresAndAtomics
            case 26: return vulkanFeatures.fragmentStoresAndAtomics(); // FragmentStoresAndAtomics
            case 27: return vulkanFeatures.shaderTessellationAndGeometryPointSize(); // ShaderTessellationAndGeometryPointSize
            case 28: return vulkanFeatures.shaderImageGatherExtended(); // ShaderImageGatherExtended
            case 29: return vulkanFeatures.shaderStorageImageExtendedFormats(); // ShaderStorageImageExtendedFormats
            case 30: return vulkanFeatures.shaderStorageImageMultisample(); // ShaderStorageImageMultisample
            case 31: return vulkanFeatures.shaderStorageImageReadWithoutFormat(); // ShaderStorageImageReadWithoutFormat
            case 32: return vulkanFeatures.shaderStorageImageWriteWithoutFormat(); // ShaderStorageImageWriteWithoutFormat
            case 33: return vulkanFeatures.shaderUniformBufferArrayDynamicIndexing(); // ShaderUniformBufferArrayDynamicIndexing
            case 34: return vulkanFeatures.shaderSampledImageArrayDynamicIndexing(); // ShaderSampledImageArrayDynamicIndexing
            case 35: return vulkanFeatures.shaderStorageBufferArrayDynamicIndexing(); // ShaderStorageBufferArrayDynamicIndexing
            case 36: return vulkanFeatures.shaderStorageImageArrayDynamicIndexing(); // ShaderStorageImageArrayDynamicIndexing
            case 37: return vulkanFeatures.shaderClipDistance(); // ShaderClipDistance
            case 38: return vulkanFeatures.shaderCullDistance(); // ShaderCullDistance
            case 39: return vulkanFeatures.shaderFloat64(); // ShaderFloat64
            case 40: return vulkanFeatures.shaderInt64(); // ShaderInt64
            case 41: return vulkanFeatures.shaderInt16(); // ShaderInt16
            case 42: return vulkanFeatures.shaderResourceResidency(); // ShaderResourceResidency
            case 43: return vulkanFeatures.shaderResourceMinLod(); // ShaderResourceMinLod
            case 44: return vulkanFeatures.sparseBinding(); // SparseBinding
            case 45: return vulkanFeatures.sparseResidencyBuffer(); // SparseResidencyBuffer
            case 46: return vulkanFeatures.sparseResidencyImage2D(); // SparseResidencyImage2D
            case 47: return vulkanFeatures.sparseResidencyImage3D(); // SparseResidencyImage3D
            case 48: return vulkanFeatures.sparseResidency2Samples(); // SparseResidency2Samples
            case 49: return vulkanFeatures.sparseResidency4Samples(); // SparseResidency4Samples
            case 50: return vulkanFeatures.sparseResidency8Samples(); // SparseResidency8Samples
            case 51: return vulkanFeatures.sparseResidency16Samples(); // SparseResidency16Samples
            case 52: return vulkanFeatures.sparseResidencyAliased(); // SparseResidencyAliased
            case 53: return vulkanFeatures.variableMultisampleRate(); // VariableMultisampleRate
            case 54: return vulkanFeatures.inheritedQueries(); // InheritedQueries
            case 55: return features11.storageBuffer16BitAccess(); // StorageBuffer16BitAccess
            case 56: return features11.uniformAndStorageBuffer16BitAccess(); // UniformAndStorageBuffer16BitAccess
            case 57: return features11.storagePushConstant16(); // StoragePushConstant16
            case 58: return features11.storageInputOutput16(); // StorageInputOutput16
            case 59: return features11.multiview(); // Multiview
            case 60: return features11.multiviewGeometryShader(); // MultiviewGeometryShader
            case 61: return features11.multiviewTessellationShader(); // MultiviewTessellationShader
            case 62: return features11.variablePointersStorageBuffer(); // VariablePointersStorageBuffer
            case 63: return features11.variablePointers(); // VariablePointers
            case 64: return features11.protectedMemory(); // ProtectedMemory
            case 65: return features11.samplerYcbcrConversion(); // SamplerYcbcrConversion
            case 66: return features11.shaderDrawParameters(); // ShaderDrawParameters
            case 67: return features12.samplerMirrorClampToEdge(); // SamplerMirrorClampToEdge
            case 68: return features12.drawIndirectCount(); // DrawIndirectCount
            case 69: return features12.storageBuffer8BitAccess(); // StorageBuffer8BitAccess
            case 70: return features12.uniformAndStorageBuffer8BitAccess(); // UniformAndStorageBuffer8BitAccess
            case 71: return features12.storagePushConstant8(); // StoragePushConstant8
            case 72: return features12.shaderBufferInt64Atomics(); // ShaderBufferInt64Atomics
            case 73: return features12.shaderSharedInt64Atomics(); // ShaderSharedInt64Atomics
            case 74: return features12.shaderFloat16(); // ShaderFloat16
            case 75: return features12.shaderInt8(); // ShaderInt8
            case 76: return features12.descriptorIndexing(); // DescriptorIndexing
            case 77: return features12.shaderInputAttachmentArrayDynamicIndexing(); // ShaderInputAttachmentArrayDynamicIndexing
            case 78: return features12.shaderUniformTexelBufferArrayDynamicIndexing(); // ShaderUniformTexelBufferArrayDynamicIndexing
            case 79: return features12.shaderStorageTexelBufferArrayDynamicIndexing(); // ShaderStorageTexelBufferArrayDynamicIndexing
            case 80: return features12.shaderUniformBufferArrayNonUniformIndexing(); // ShaderUniformBufferArrayNonUniformIndexing
            case 81: return features12.shaderSampledImageArrayNonUniformIndexing(); // ShaderSampledImageArrayNonUniformIndexing
            case 82: return features12.shaderStorageBufferArrayNonUniformIndexing(); // ShaderStorageBufferArrayNonUniformIndexing
            case 83: return features12.shaderStorageImageArrayNonUniformIndexing(); // ShaderStorageImageArrayNonUniformIndexing
            case 84: return features12.shaderInputAttachmentArrayNonUniformIndexing(); // ShaderInputAttachmentArrayNonUniformIndexing
            case 85: return features12.shaderUniformTexelBufferArrayNonUniformIndexing(); // ShaderUniformTexelBufferArrayNonUniformIndexing
            case 86: return features12.shaderStorageTexelBufferArrayNonUniformIndexing(); // ShaderStorageTexelBufferArrayNonUniformIndexing
            case 87: return features12.descriptorBindingUniformBufferUpdateAfterBind(); // DescriptorBindingUniformBufferUpdateAfterBind
            case 88: return features12.descriptorBindingSampledImageUpdateAfterBind(); // DescriptorBindingSampledImageUpdateAfterBind
            case 89: return features12.descriptorBindingStorageImageUpdateAfterBind(); // DescriptorBindingStorageImageUpdateAfterBind
            case 90: return features12.descriptorBindingStorageBufferUpdateAfterBind(); // DescriptorBindingStorageBufferUpdateAfterBind
            case 91: return features12.descriptorBindingUniformTexelBufferUpdateAfterBind(); // DescriptorBindingUniformTexelBufferUpdateAfterBind
            case 92: return features12.descriptorBindingStorageTexelBufferUpdateAfterBind(); // DescriptorBindingStorageTexelBufferUpdateAfterBind
            case 93: return features12.descriptorBindingUpdateUnusedWhilePending(); // DescriptorBindingUpdateUnusedWhilePending
            case 94: return features12.descriptorBindingPartiallyBound(); // DescriptorBindingPartiallyBound
            case 95: return features12.descriptorBindingVariableDescriptorCount(); // DescriptorBindingVariableDescriptorCount
            case 96: return features12.runtimeDescriptorArray(); // RuntimeDescriptorArray
            case 97: return features12.samplerFilterMinmax(); // SamplerFilterMinmax
            case 98: return features12.scalarBlockLayout(); // ScalarBlockLayout
            case 99: return features12.imagelessFramebuffer(); // ImagelessFramebuffer
            case 100: return features12.uniformBufferStandardLayout(); // UniformBufferStandardLayout
            case 101: return features12.shaderSubgroupExtendedTypes(); // ShaderSubgroupExtendedTypes
            case 102: return features12.separateDepthStencilLayouts(); // SeparateDepthStencilLayouts
            case 103: return features12.hostQueryReset(); // HostQueryReset
            case 104: return features12.timelineSemaphore(); // TimelineSemaphore
            case 105: return features12.bufferDeviceAddress(); // BufferDeviceAddress
            case 106: return features12.bufferDeviceAddressCaptureReplay(); // BufferDeviceAddressCaptureReplay
            case 107: return features12.bufferDeviceAddressMultiDevice(); // BufferDeviceAddressMultiDevice
            case 108: return features12.vulkanMemoryModel(); // VulkanMemoryModel
            case 109: return features12.vulkanMemoryModelDeviceScope(); // VulkanMemoryModelDeviceScope
            case 110: return features12.vulkanMemoryModelAvailabilityVisibilityChains(); // VulkanMemoryModelAvailabilityVisibilityChains
            case 111: return features12.shaderOutputViewportIndex(); // ShaderOutputViewportIndex
            case 112: return features12.shaderOutputLayer(); // ShaderOutputLayer
            case 113: return features12.subgroupBroadcastDynamicId(); // SubgroupBroadcastDynamicId
            case 114: return features13.robustImageAccess(); // RobustImageAccess
            case 115: return features13.inlineUniformBlock(); // InlineUniformBlock
            case 116: return features13.descriptorBindingInlineUniformBlockUpdateAfterBind(); // DescriptorBindingInlineUniformBlockUpdateAfterBind
            case 117: return features13.pipelineCreationCacheControl(); // PipelineCreationCacheControl
            case 118: return features13.privateData(); // PrivateData
            case 119: return features13.shaderDemoteToHelperInvocation(); // ShaderDemoteToHelperInvocation
            case 120: return features13.shaderTerminateInvocation(); // ShaderTerminateInvocation
            case 121: return features13.subgroupSizeControl(); // SubgroupSizeControl
            case 122: return features13.computeFullSubgroups(); // ComputeFullSubgroups
            case 123: return features13.synchronization2(); // Synchronization2
            case 124: return features13.textureCompressionASTC_HDR(); // TextureCompressionASTC_HDR
            case 125: return features13.shaderZeroInitializeWorkgroupMemory(); // ShaderZeroInitializeWorkgroupMemory
            case 126: return features13.dynamicRendering(); // DynamicRendering
            case 127: return features13.shaderIntegerDotProduct(); // ShaderIntegerDotProduct
            case 128: return features13.maintenance4(); // Maintenance4
            case 129: return features14.globalPriorityQuery(); // GlobalPriorityQuery
            case 130: return features14.shaderSubgroupRotate(); // ShaderSubgroupRotate
            case 131: return features14.shaderSubgroupRotateClustered(); // ShaderSubgroupRotateClustered
            case 132: return features14.shaderFloatControls2(); // ShaderFloatControls2
            case 133: return features14.shaderExpectAssume(); // ShaderExpectAssume
            case 134: return features14.rectangularLines(); // RectangularLines
            case 135: return features14.bresenhamLines(); // BresenhamLines
            case 136: return features14.smoothLines(); // SmoothLines
            case 137: return features14.stippledRectangularLines(); // StippledRectangularLines
            case 138: return features14.stippledBresenhamLines(); // StippledBresenhamLines
            case 139: return features14.stippledSmoothLines(); // StippledSmoothLines
            case 140: return features14.vertexAttributeInstanceRateDivisor(); // VertexAttributeInstanceRateDivisor
            case 141: return features14.vertexAttributeInstanceRateZeroDivisor(); // VertexAttributeInstanceRateZeroDivisor
            case 142: return features14.indexTypeUint8(); // IndexTypeUint8
            case 143: return features14.dynamicRenderingLocalRead(); // DynamicRenderingLocalRead
            case 144: return features14.maintenance5(); // Maintenance5
            case 145: return features14.maintenance6(); // Maintenance6
            case 146: return features14.pipelineProtectedAccess(); // PipelineProtectedAccess
            case 147: return features14.pipelineRobustness(); // PipelineRobustness
            case 148: return features14.hostImageCopy(); // HostImageCopy
            case 149: return features14.pushDescriptor(); // PushDescriptor
            default: return false;
        }
    }

    public void Set(int feature, boolean value)
    {
        switch (feature)
        {
            case 0: vulkanFeatures.robustBufferAccess(value); break;
            case 1: vulkanFeatures.fullDrawIndexUint32(value); break;
            case 2: vulkanFeatures.imageCubeArray(value); break;
            case 3: vulkanFeatures.independentBlend(value); break;
            case 4: vulkanFeatures.geometryShader(value); break;
            case 5: vulkanFeatures.tessellationShader(value); break;
            case 6: vulkanFeatures.sampleRateShading(value); break;
            case 7: vulkanFeatures.dualSrcBlend(value); break;
            case 8: vulkanFeatures.logicOp(value); break;
            case 9: vulkanFeatures.multiDrawIndirect(value); break;
            case 10: vulkanFeatures.drawIndirectFirstInstance(value); break;
            case 11: vulkanFeatures.depthClamp(value); break;
            case 12: vulkanFeatures.depthBiasClamp(value); break;
            case 13: vulkanFeatures.fillModeNonSolid(value); break;
            case 14: vulkanFeatures.depthBounds(value); break;
            case 15: vulkanFeatures.wideLines(value); break;
            case 16: vulkanFeatures.largePoints(value); break;
            case 17: vulkanFeatures.alphaToOne(value); break;
            case 18: vulkanFeatures.multiViewport(value); break;
            case 19: vulkanFeatures.samplerAnisotropy(value); break;
            case 20: vulkanFeatures.textureCompressionETC2(value); break;
            case 21: vulkanFeatures.textureCompressionASTC_LDR(value); break;
            case 22: vulkanFeatures.textureCompressionBC(value); break;
            case 23: vulkanFeatures.occlusionQueryPrecise(value); break;
            case 24: vulkanFeatures.pipelineStatisticsQuery(value); break;
            case 25: vulkanFeatures.vertexPipelineStoresAndAtomics(value); break;
            case 26: vulkanFeatures.fragmentStoresAndAtomics(value); break;
            case 27: vulkanFeatures.shaderTessellationAndGeometryPointSize(value); break;
            case 28: vulkanFeatures.shaderImageGatherExtended(value); break;
            case 29: vulkanFeatures.shaderStorageImageExtendedFormats(value); break;
            case 30: vulkanFeatures.shaderStorageImageMultisample(value); break;
            case 31: vulkanFeatures.shaderStorageImageReadWithoutFormat(value); break;
            case 32: vulkanFeatures.shaderStorageImageWriteWithoutFormat(value); break;
            case 33: vulkanFeatures.shaderUniformBufferArrayDynamicIndexing(value); break;
            case 34: vulkanFeatures.shaderSampledImageArrayDynamicIndexing(value); break;
            case 35: vulkanFeatures.shaderStorageBufferArrayDynamicIndexing(value); break;
            case 36: vulkanFeatures.shaderStorageImageArrayDynamicIndexing(value); break;
            case 37: vulkanFeatures.shaderClipDistance(value); break;
            case 38: vulkanFeatures.shaderCullDistance(value); break;
            case 39: vulkanFeatures.shaderFloat64(value); break;
            case 40: vulkanFeatures.shaderInt64(value); break;
            case 41: vulkanFeatures.shaderInt16(value); break;
            case 42: vulkanFeatures.shaderResourceResidency(value); break;
            case 43: vulkanFeatures.shaderResourceMinLod(value); break;
            case 44: vulkanFeatures.sparseBinding(value); break;
            case 45: vulkanFeatures.sparseResidencyBuffer(value); break;
            case 46: vulkanFeatures.sparseResidencyImage2D(value); break;
            case 47: vulkanFeatures.sparseResidencyImage3D(value); break;
            case 48: vulkanFeatures.sparseResidency2Samples(value); break;
            case 49: vulkanFeatures.sparseResidency4Samples(value); break;
            case 50: vulkanFeatures.sparseResidency8Samples(value); break;
            case 51: vulkanFeatures.sparseResidency16Samples(value); break;
            case 52: vulkanFeatures.sparseResidencyAliased(value); break;
            case 53: vulkanFeatures.variableMultisampleRate(value); break;
            case 54: vulkanFeatures.inheritedQueries(value); break;
            case 55: features11.storageBuffer16BitAccess(value); break;
            case 56: features11.uniformAndStorageBuffer16BitAccess(value); break;
            case 57: features11.storagePushConstant16(value); break;
            case 58: features11.storageInputOutput16(value); break;
            case 59: features11.multiview(value); break;
            case 60: features11.multiviewGeometryShader(value); break;
            case 61: features11.multiviewTessellationShader(value); break;
            case 62: features11.variablePointersStorageBuffer(value); break;
            case 63: features11.variablePointers(value); break;
            case 64: features11.protectedMemory(value); break;
            case 65: features11.samplerYcbcrConversion(value); break;
            case 66: features11.shaderDrawParameters(value); break;
            case 67: features12.samplerMirrorClampToEdge(value); break;
            case 68: features12.drawIndirectCount(value); break;
            case 69: features12.storageBuffer8BitAccess(value); break;
            case 70: features12.uniformAndStorageBuffer8BitAccess(value); break;
            case 71: features12.storagePushConstant8(value); break;
            case 72: features12.shaderBufferInt64Atomics(value); break;
            case 73: features12.shaderSharedInt64Atomics(value); break;
            case 74: features12.shaderFloat16(value); break;
            case 75: features12.shaderInt8(value); break;
            case 76: features12.descriptorIndexing(value); break;
            case 77: features12.shaderInputAttachmentArrayDynamicIndexing(value); break;
            case 78: features12.shaderUniformTexelBufferArrayDynamicIndexing(value); break;
            case 79: features12.shaderStorageTexelBufferArrayDynamicIndexing(value); break;
            case 80: features12.shaderUniformBufferArrayNonUniformIndexing(value); break;
            case 81: features12.shaderSampledImageArrayNonUniformIndexing(value); break;
            case 82: features12.shaderStorageBufferArrayNonUniformIndexing(value); break;
            case 83: features12.shaderStorageImageArrayNonUniformIndexing(value); break;
            case 84: features12.shaderInputAttachmentArrayNonUniformIndexing(value); break;
            case 85: features12.shaderUniformTexelBufferArrayNonUniformIndexing(value); break;
            case 86: features12.shaderStorageTexelBufferArrayNonUniformIndexing(value); break;
            case 87: features12.descriptorBindingUniformBufferUpdateAfterBind(value); break;
            case 88: features12.descriptorBindingSampledImageUpdateAfterBind(value); break;
            case 89: features12.descriptorBindingStorageImageUpdateAfterBind(value); break;
            case 90: features12.descriptorBindingStorageBufferUpdateAfterBind(value); break;
            case 91: features12.descriptorBindingUniformTexelBufferUpdateAfterBind(value); break;
            case 92: features12.descriptorBindingStorageTexelBufferUpdateAfterBind(value); break;
            case 93: features12.descriptorBindingUpdateUnusedWhilePending(value); break;
            case 94: features12.descriptorBindingPartiallyBound(value); break;
            case 95: features12.descriptorBindingVariableDescriptorCount(value); break;
            case 96: features12.runtimeDescriptorArray(value); break;
            case 97: features12.samplerFilterMinmax(value); break;
            case 98: features12.scalarBlockLayout(value); break;
            case 99: features12.imagelessFramebuffer(value); break;
            case 100: features12.uniformBufferStandardLayout(value); break;
            case 101: features12.shaderSubgroupExtendedTypes(value); break;
            case 102: features12.separateDepthStencilLayouts(value); break;
            case 103: features12.hostQueryReset(value); break;
            case 104: features12.timelineSemaphore(value); break;
            case 105: features12.bufferDeviceAddress(value); break;
            case 106: features12.bufferDeviceAddressCaptureReplay(value); break;
            case 107: features12.bufferDeviceAddressMultiDevice(value); break;
            case 108: features12.vulkanMemoryModel(value); break;
            case 109: features12.vulkanMemoryModelDeviceScope(value); break;
            case 110: features12.vulkanMemoryModelAvailabilityVisibilityChains(value); break;
            case 111: features12.shaderOutputViewportIndex(value); break;
            case 112: features12.shaderOutputLayer(value); break;
            case 113: features12.subgroupBroadcastDynamicId(value); break;
            case 114: features13.robustImageAccess(value); break;
            case 115: features13.inlineUniformBlock(value); break;
            case 116: features13.descriptorBindingInlineUniformBlockUpdateAfterBind(value); break;
            case 117: features13.pipelineCreationCacheControl(value); break;
            case 118: features13.privateData(value); break;
            case 119: features13.shaderDemoteToHelperInvocation(value); break;
            case 120: features13.shaderTerminateInvocation(value); break;
            case 121: features13.subgroupSizeControl(value); break;
            case 122: features13.computeFullSubgroups(value); break;
            case 123: features13.synchronization2(value); break;
            case 124: features13.textureCompressionASTC_HDR(value); break;
            case 125: features13.shaderZeroInitializeWorkgroupMemory(value); break;
            case 126: features13.dynamicRendering(value); break;
            case 127: features13.shaderIntegerDotProduct(value); break;
            case 128: features13.maintenance4(value); break;
            case 129: features14.globalPriorityQuery(value); break;
            case 130: features14.shaderSubgroupRotate(value); break;
            case 131: features14.shaderSubgroupRotateClustered(value); break;
            case 132: features14.shaderFloatControls2(value); break;
            case 133: features14.shaderExpectAssume(value); break;
            case 134: features14.rectangularLines(value); break;
            case 135: features14.bresenhamLines(value); break;
            case 136: features14.smoothLines(value); break;
            case 137: features14.stippledRectangularLines(value); break;
            case 138: features14.stippledBresenhamLines(value); break;
            case 139: features14.stippledSmoothLines(value); break;
            case 140: features14.vertexAttributeInstanceRateDivisor(value); break;
            case 141: features14.vertexAttributeInstanceRateZeroDivisor(value); break;
            case 142: features14.indexTypeUint8(value); break;
            case 143: features14.dynamicRenderingLocalRead(value); break;
            case 144: features14.maintenance5(value); break;
            case 145: features14.maintenance6(value); break;
            case 146: features14.pipelineProtectedAccess(value); break;
            case 147: features14.pipelineRobustness(value); break;
            case 148: features14.hostImageCopy(value); break;
            case 149: features14.pushDescriptor(value); break;
        }
    }
}
