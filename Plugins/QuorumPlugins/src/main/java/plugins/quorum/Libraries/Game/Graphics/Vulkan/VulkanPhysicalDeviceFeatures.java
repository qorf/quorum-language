package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.*;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;

import static org.lwjgl.vulkan.VK10.vkGetPhysicalDeviceFeatures;
import static org.lwjgl.vulkan.VK11.vkGetPhysicalDeviceFeatures2;

public class VulkanPhysicalDeviceFeatures
{
    public Object me_;

    // Fields for the base features objects, as well as the structs for features introduced in later Vulkan releases.
    VkPhysicalDeviceFeatures vulkanFeatures = null;
    VkPhysicalDeviceFeatures2 vulkanFeatures2 = null;
    VkPhysicalDeviceVulkan11Features features11 = null;
    VkPhysicalDeviceVulkan12Features features12 = null;
    VkPhysicalDeviceVulkan13Features features13 = null;
    VkPhysicalDeviceVulkan14Features features14 = null;

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

    public boolean NativeHasRobustBufferAccess()
    {
        return vulkanFeatures.robustBufferAccess();
    }

    public void NativeSetRobustBufferAccess(boolean value)
    {
        vulkanFeatures.robustBufferAccess(value);
    }

    public boolean NativeHasFullDrawIndexUint32()
    {
        return vulkanFeatures.fullDrawIndexUint32();
    }

    public void NativeSetFullDrawIndexUint32(boolean value)
    {
        vulkanFeatures.fullDrawIndexUint32(value);
    }

    public boolean NativeHasImageCubeArray()
    {
        return vulkanFeatures.imageCubeArray();
    }

    public void NativeSetImageCubeArray(boolean value)
    {
        vulkanFeatures.imageCubeArray(value);
    }

    public boolean NativeHasIndependentBlend()
    {
        return vulkanFeatures.independentBlend();
    }

    public void NativeSetIndependentBlend(boolean value)
    {
        vulkanFeatures.independentBlend(value);
    }

    public boolean NativeHasGeometryShader()
    {
        return vulkanFeatures.geometryShader();
    }

    public void NativeSetGeometryShader(boolean value)
    {
        vulkanFeatures.geometryShader(value);
    }

    public boolean NativeHasTessellationShader()
    {
        return vulkanFeatures.tessellationShader();
    }

    public void NativeSetTessellationShader(boolean value)
    {
        vulkanFeatures.tessellationShader(value);
    }

    public boolean NativeHasSampleRateShading()
    {
        return vulkanFeatures.sampleRateShading();
    }

    public void NativeSetSampleRateShading(boolean value)
    {
        vulkanFeatures.sampleRateShading(value);
    }

    public boolean NativeHasDualSrcBlend()
    {
        return vulkanFeatures.dualSrcBlend();
    }

    public void NativeSetDualSrcBlend(boolean value)
    {
        vulkanFeatures.dualSrcBlend(value);
    }

    public boolean NativeHasLogicOp()
    {
        return vulkanFeatures.logicOp();
    }

    public void NativeSetLogicOp(boolean value)
    {
        vulkanFeatures.logicOp(value);
    }

    public boolean NativeHasMultiDrawIndirect()
    {
        return vulkanFeatures.multiDrawIndirect();
    }

    public void NativeSetMultiDrawIndirect(boolean value)
    {
        vulkanFeatures.multiDrawIndirect(value);
    }

    public boolean NativeHasDrawIndirectFirstInstance()
    {
        return vulkanFeatures.drawIndirectFirstInstance();
    }

    public void NativeSetDrawIndirectFirstInstance(boolean value)
    {
        vulkanFeatures.drawIndirectFirstInstance(value);
    }

    public boolean NativeHasDepthClamp()
    {
        return vulkanFeatures.depthClamp();
    }

    public void NativeSetDepthClamp(boolean value)
    {
        vulkanFeatures.depthClamp(value);
    }

    public boolean NativeHasDepthBiasClamp()
    {
        return vulkanFeatures.depthBiasClamp();
    }

    public void NativeSetDepthBiasClamp(boolean value)
    {
        vulkanFeatures.depthBiasClamp(value);
    }

    public boolean NativeHasFillModeNonSolid()
    {
        return vulkanFeatures.fillModeNonSolid();
    }

    public void NativeSetFillModeNonSolid(boolean value)
    {
        vulkanFeatures.fillModeNonSolid(value);
    }

    public boolean NativeHasDepthBounds()
    {
        return vulkanFeatures.depthBounds();
    }

    public void NativeSetDepthBounds(boolean value)
    {
        vulkanFeatures.depthBounds(value);
    }

    public boolean NativeHasWideLines()
    {
        return vulkanFeatures.wideLines();
    }

    public void NativeSetWideLines(boolean value)
    {
        vulkanFeatures.wideLines(value);
    }

    public boolean NativeHasLargePoints()
    {
        return vulkanFeatures.largePoints();
    }

    public void NativeSetLargePoints(boolean value)
    {
        vulkanFeatures.largePoints(value);
    }

    public boolean NativeHasAlphaToOne()
    {
        return vulkanFeatures.alphaToOne();
    }

    public void NativeSetAlphaToOne(boolean value)
    {
        vulkanFeatures.alphaToOne(value);
    }

    public boolean NativeHasMultiViewport()
    {
        return vulkanFeatures.multiViewport();
    }

    public void NativeSetMultiViewport(boolean value)
    {
        vulkanFeatures.multiViewport(value);
    }

    public boolean NativeHasSamplerAnisotropy()
    {
        return vulkanFeatures.samplerAnisotropy();
    }

    public void NativeSetSamplerAnisotropy(boolean value)
    {
        vulkanFeatures.samplerAnisotropy(value);
    }

    public boolean NativeHasTextureCompressionETC2()
    {
        return vulkanFeatures.textureCompressionETC2();
    }

    public void NativeSetTextureCompressionETC2(boolean value)
    {
        vulkanFeatures.textureCompressionETC2(value);
    }

    public boolean NativeHasTextureCompressionASTC_LDR()
    {
        return vulkanFeatures.textureCompressionASTC_LDR();
    }

    public void NativeSetTextureCompressionASTC_LDR(boolean value)
    {
        vulkanFeatures.textureCompressionASTC_LDR(value);
    }

    public boolean NativeHasTextureCompressionBC()
    {
        return vulkanFeatures.textureCompressionBC();
    }

    public void NativeSetTextureCompressionBC(boolean value)
    {
        vulkanFeatures.textureCompressionBC(value);
    }

    public boolean NativeHasOcclusionQueryPrecise()
    {
        return vulkanFeatures.occlusionQueryPrecise();
    }

    public void NativeSetOcclusionQueryPrecise(boolean value)
    {
        vulkanFeatures.occlusionQueryPrecise(value);
    }

    public boolean NativeHasPipelineStatisticsQuery()
    {
        return vulkanFeatures.pipelineStatisticsQuery();
    }

    public void NativeSetPipelineStatisticsQuery(boolean value)
    {
        vulkanFeatures.pipelineStatisticsQuery(value);
    }

    public boolean NativeHasVertexPipelineStoresAndAtomics()
    {
        return vulkanFeatures.vertexPipelineStoresAndAtomics();
    }

    public void NativeSetVertexPipelineStoresAndAtomics(boolean value)
    {
        vulkanFeatures.vertexPipelineStoresAndAtomics(value);
    }

    public boolean NativeHasFragmentStoresAndAtomics()
    {
        return vulkanFeatures.fragmentStoresAndAtomics();
    }

    public void NativeSetFragmentStoresAndAtomics(boolean value)
    {
        vulkanFeatures.fragmentStoresAndAtomics(value);
    }

    public boolean NativeHasShaderTessellationAndGeometryPointSize()
    {
        return vulkanFeatures.shaderTessellationAndGeometryPointSize();
    }

    public void NativeSetShaderTessellationAndGeometryPointSize(boolean value)
    {
        vulkanFeatures.shaderTessellationAndGeometryPointSize(value);
    }

    public boolean NativeHasShaderImageGatherExtended()
    {
        return vulkanFeatures.shaderImageGatherExtended();
    }

    public void NativeSetShaderImageGatherExtended(boolean value)
    {
        vulkanFeatures.shaderImageGatherExtended(value);
    }

    public boolean NativeHasShaderStorageImageExtendedFormats()
    {
        return vulkanFeatures.shaderStorageImageExtendedFormats();
    }

    public void NativeSetShaderStorageImageExtendedFormats(boolean value)
    {
        vulkanFeatures.shaderStorageImageExtendedFormats(value);
    }

    public boolean NativeHasShaderStorageImageMultisample()
    {
        return vulkanFeatures.shaderStorageImageMultisample();
    }

    public void NativeSetShaderStorageImageMultisample(boolean value)
    {
        vulkanFeatures.shaderStorageImageMultisample(value);
    }

    public boolean NativeHasShaderStorageImageReadWithoutFormat()
    {
        return vulkanFeatures.shaderStorageImageReadWithoutFormat();
    }

    public void NativeSetShaderStorageImageReadWithoutFormat(boolean value)
    {
        vulkanFeatures.shaderStorageImageReadWithoutFormat(value);
    }

    public boolean NativeHasShaderStorageImageWriteWithoutFormat()
    {
        return vulkanFeatures.shaderStorageImageWriteWithoutFormat();
    }

    public void NativeSetShaderStorageImageWriteWithoutFormat(boolean value)
    {
        vulkanFeatures.shaderStorageImageWriteWithoutFormat(value);
    }

    public boolean NativeHasShaderUniformBufferArrayDynamicIndexing()
    {
        return vulkanFeatures.shaderUniformBufferArrayDynamicIndexing();
    }

    public void NativeSetShaderUniformBufferArrayDynamicIndexing(boolean value)
    {
        vulkanFeatures.shaderUniformBufferArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderSampledImageArrayDynamicIndexing()
    {
        return vulkanFeatures.shaderSampledImageArrayDynamicIndexing();
    }

    public void NativeSetShaderSampledImageArrayDynamicIndexing(boolean value)
    {
        vulkanFeatures.shaderSampledImageArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderStorageBufferArrayDynamicIndexing()
    {
        return vulkanFeatures.shaderStorageBufferArrayDynamicIndexing();
    }

    public void NativeSetShaderStorageBufferArrayDynamicIndexing(boolean value)
    {
        vulkanFeatures.shaderStorageBufferArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderStorageImageArrayDynamicIndexing()
    {
        return vulkanFeatures.shaderStorageImageArrayDynamicIndexing();
    }

    public void NativeSetShaderStorageImageArrayDynamicIndexing(boolean value)
    {
        vulkanFeatures.shaderStorageImageArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderClipDistance()
    {
        return vulkanFeatures.shaderClipDistance();
    }

    public void NativeSetShaderClipDistance(boolean value)
    {
        vulkanFeatures.shaderClipDistance(value);
    }

    public boolean NativeHasShaderCullDistance()
    {
        return vulkanFeatures.shaderCullDistance();
    }

    public void NativeSetShaderCullDistance(boolean value)
    {
        vulkanFeatures.shaderCullDistance(value);
    }

    public boolean NativeHasShaderFloat64()
    {
        return vulkanFeatures.shaderFloat64();
    }

    public void NativeSetShaderFloat64(boolean value)
    {
        vulkanFeatures.shaderFloat64(value);
    }

    public boolean NativeHasShaderInt64()
    {
        return vulkanFeatures.shaderInt64();
    }

    public void NativeSetShaderInt64(boolean value)
    {
        vulkanFeatures.shaderInt64(value);
    }

    public boolean NativeHasShaderInt16()
    {
        return vulkanFeatures.shaderInt16();
    }

    public void NativeSetShaderInt16(boolean value)
    {
        vulkanFeatures.shaderInt16(value);
    }

    public boolean NativeHasShaderResourceResidency()
    {
        return vulkanFeatures.shaderResourceResidency();
    }

    public void NativeSetShaderResourceResidency(boolean value)
    {
        vulkanFeatures.shaderResourceResidency(value);
    }

    public boolean NativeHasShaderResourceMinLod()
    {
        return vulkanFeatures.shaderResourceMinLod();
    }

    public void NativeSetShaderResourceMinLod(boolean value)
    {
        vulkanFeatures.shaderResourceMinLod(value);
    }

    public boolean NativeHasSparseBinding()
    {
        return vulkanFeatures.sparseBinding();
    }

    public void NativeSetSparseBinding(boolean value)
    {
        vulkanFeatures.sparseBinding(value);
    }

    public boolean NativeHasSparseResidencyBuffer()
    {
        return vulkanFeatures.sparseResidencyBuffer();
    }

    public void NativeSetSparseResidencyBuffer(boolean value)
    {
        vulkanFeatures.sparseResidencyBuffer(value);
    }

    public boolean NativeHasSparseResidencyImage2D()
    {
        return vulkanFeatures.sparseResidencyImage2D();
    }

    public void NativeSetSparseResidencyImage2D(boolean value)
    {
        vulkanFeatures.sparseResidencyImage2D(value);
    }

    public boolean NativeHasSparseResidencyImage3D()
    {
        return vulkanFeatures.sparseResidencyImage3D();
    }

    public void NativeSetSparseResidencyImage3D(boolean value)
    {
        vulkanFeatures.sparseResidencyImage3D(value);
    }

    public boolean NativeHasSparseResidency2Samples()
    {
        return vulkanFeatures.sparseResidency2Samples();
    }

    public void NativeSetSparseResidency2Samples(boolean value)
    {
        vulkanFeatures.sparseResidency2Samples(value);
    }

    public boolean NativeHasSparseResidency4Samples()
    {
        return vulkanFeatures.sparseResidency4Samples();
    }

    public void NativeSetSparseResidency4Samples(boolean value)
    {
        vulkanFeatures.sparseResidency4Samples(value);
    }

    public boolean NativeHasSparseResidency8Samples()
    {
        return vulkanFeatures.sparseResidency8Samples();
    }

    public void NativeSetSparseResidency8Samples(boolean value)
    {
        vulkanFeatures.sparseResidency8Samples(value);
    }

    public boolean NativeHasSparseResidency16Samples()
    {
        return vulkanFeatures.sparseResidency16Samples();
    }

    public void NativeSetSparseResidency16Samples(boolean value)
    {
        vulkanFeatures.sparseResidency16Samples(value);
    }

    public boolean NativeHasSparseResidencyAliased()
    {
        return vulkanFeatures.sparseResidencyAliased();
    }

    public void NativeSetSparseResidencyAliased(boolean value)
    {
        vulkanFeatures.sparseResidencyAliased(value);
    }

    public boolean NativeHasVariableMultisampleRate()
    {
        return vulkanFeatures.variableMultisampleRate();
    }

    public void NativeSetVariableMultisampleRate(boolean value)
    {
        vulkanFeatures.variableMultisampleRate(value);
    }

    public boolean NativeHasInheritedQueries()
    {
        return vulkanFeatures.inheritedQueries();
    }

    public void NativeSetInheritedQueries(boolean value)
    {
        vulkanFeatures.inheritedQueries(value);
    }

    public boolean NativeHasStorageBuffer16BitAccess()
    {
        return features11.storageBuffer16BitAccess();
    }

    public void NativeSetStorageBuffer16BitAccess(boolean value)
    {
        features11.storageBuffer16BitAccess(value);
    }

    public boolean NativeHasUniformAndStorageBuffer16BitAccess()
    {
        return features11.uniformAndStorageBuffer16BitAccess();
    }

    public void NativeSetUniformAndStorageBuffer16BitAccess(boolean value)
    {
        features11.uniformAndStorageBuffer16BitAccess(value);
    }

    public boolean NativeHasStoragePushConstant16()
    {
        return features11.storagePushConstant16();
    }

    public void NativeSetStoragePushConstant16(boolean value)
    {
        features11.storagePushConstant16(value);
    }

    public boolean NativeHasStorageInputOutput16()
    {
        return features11.storageInputOutput16();
    }

    public void NativeSetStorageInputOutput16(boolean value)
    {
        features11.storageInputOutput16(value);
    }

    public boolean NativeHasMultiview()
    {
        return features11.multiview();
    }

    public void NativeSetMultiview(boolean value)
    {
        features11.multiview(value);
    }

    public boolean NativeHasMultiviewGeometryShader()
    {
        return features11.multiviewGeometryShader();
    }

    public void NativeSetMultiviewGeometryShader(boolean value)
    {
        features11.multiviewGeometryShader(value);
    }

    public boolean NativeHasMultiviewTessellationShader()
    {
        return features11.multiviewTessellationShader();
    }

    public void NativeSetMultiviewTessellationShader(boolean value)
    {
        features11.multiviewTessellationShader(value);
    }

    public boolean NativeHasVariablePointersStorageBuffer()
    {
        return features11.variablePointersStorageBuffer();
    }

    public void NativeSetVariablePointersStorageBuffer(boolean value)
    {
        features11.variablePointersStorageBuffer(value);
    }

    public boolean NativeHasVariablePointers()
    {
        return features11.variablePointers();
    }

    public void NativeSetVariablePointers(boolean value)
    {
        features11.variablePointers(value);
    }

    public boolean NativeHasProtectedMemory()
    {
        return features11.protectedMemory();
    }

    public void NativeSetProtectedMemory(boolean value)
    {
        features11.protectedMemory(value);
    }

    public boolean NativeHasSamplerYcbcrConversion()
    {
        return features11.samplerYcbcrConversion();
    }

    public void NativeSetSamplerYcbcrConversion(boolean value)
    {
        features11.samplerYcbcrConversion(value);
    }

    public boolean NativeHasShaderDrawParameters()
    {
        return features11.shaderDrawParameters();
    }

    public void NativeSetShaderDrawParameters(boolean value)
    {
        features11.shaderDrawParameters(value);
    }

    public boolean NativeHasSamplerMirrorClampToEdge()
    {
        return features12.samplerMirrorClampToEdge();
    }

    public void NativeSetSamplerMirrorClampToEdge(boolean value)
    {
        features12.samplerMirrorClampToEdge(value);
    }

    public boolean NativeHasDrawIndirectCount()
    {
        return features12.drawIndirectCount();
    }

    public void NativeSetDrawIndirectCount(boolean value)
    {
        features12.drawIndirectCount(value);
    }

    public boolean NativeHasStorageBuffer8BitAccess()
    {
        return features12.storageBuffer8BitAccess();
    }

    public void NativeSetStorageBuffer8BitAccess(boolean value)
    {
        features12.storageBuffer8BitAccess(value);
    }

    public boolean NativeHasUniformAndStorageBuffer8BitAccess()
    {
        return features12.uniformAndStorageBuffer8BitAccess();
    }

    public void NativeSetUniformAndStorageBuffer8BitAccess(boolean value)
    {
        features12.uniformAndStorageBuffer8BitAccess(value);
    }

    public boolean NativeHasStoragePushConstant8()
    {
        return features12.storagePushConstant8();
    }

    public void NativeSetStoragePushConstant8(boolean value)
    {
        features12.storagePushConstant8(value);
    }

    public boolean NativeHasShaderBufferInt64Atomics()
    {
        return features12.shaderBufferInt64Atomics();
    }

    public void NativeSetShaderBufferInt64Atomics(boolean value)
    {
        features12.shaderBufferInt64Atomics(value);
    }

    public boolean NativeHasShaderSharedInt64Atomics()
    {
        return features12.shaderSharedInt64Atomics();
    }

    public void NativeSetShaderSharedInt64Atomics(boolean value)
    {
        features12.shaderSharedInt64Atomics(value);
    }

    public boolean NativeHasShaderFloat16()
    {
        return features12.shaderFloat16();
    }

    public void NativeSetShaderFloat16(boolean value)
    {
        features12.shaderFloat16(value);
    }

    public boolean NativeHasShaderInt8()
    {
        return features12.shaderInt8();
    }

    public void NativeSetShaderInt8(boolean value)
    {
        features12.shaderInt8(value);
    }

    public boolean NativeHasDescriptorIndexing()
    {
        return features12.descriptorIndexing();
    }

    public void NativeSetDescriptorIndexing(boolean value)
    {
        features12.descriptorIndexing(value);
    }

    public boolean NativeHasShaderInputAttachmentArrayDynamicIndexing()
    {
        return features12.shaderInputAttachmentArrayDynamicIndexing();
    }

    public void NativeSetShaderInputAttachmentArrayDynamicIndexing(boolean value)
    {
        features12.shaderInputAttachmentArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderUniformTexelBufferArrayDynamicIndexing()
    {
        return features12.shaderUniformTexelBufferArrayDynamicIndexing();
    }

    public void NativeSetShaderUniformTexelBufferArrayDynamicIndexing(boolean value)
    {
        features12.shaderUniformTexelBufferArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderStorageTexelBufferArrayDynamicIndexing()
    {
        return features12.shaderStorageTexelBufferArrayDynamicIndexing();
    }

    public void NativeSetShaderStorageTexelBufferArrayDynamicIndexing(boolean value)
    {
        features12.shaderStorageTexelBufferArrayDynamicIndexing(value);
    }

    public boolean NativeHasShaderUniformBufferArrayNonUniformIndexing()
    {
        return features12.shaderUniformBufferArrayNonUniformIndexing();
    }

    public void NativeSetShaderUniformBufferArrayNonUniformIndexing(boolean value)
    {
        features12.shaderUniformBufferArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderSampledImageArrayNonUniformIndexing()
    {
        return features12.shaderSampledImageArrayNonUniformIndexing();
    }

    public void NativeSetShaderSampledImageArrayNonUniformIndexing(boolean value)
    {
        features12.shaderSampledImageArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderStorageBufferArrayNonUniformIndexing()
    {
        return features12.shaderStorageBufferArrayNonUniformIndexing();
    }

    public void NativeSetShaderStorageBufferArrayNonUniformIndexing(boolean value)
    {
        features12.shaderStorageBufferArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderStorageImageArrayNonUniformIndexing()
    {
        return features12.shaderStorageImageArrayNonUniformIndexing();
    }

    public void NativeSetShaderStorageImageArrayNonUniformIndexing(boolean value)
    {
        features12.shaderStorageImageArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderInputAttachmentArrayNonUniformIndexing()
    {
        return features12.shaderInputAttachmentArrayNonUniformIndexing();
    }

    public void NativeSetShaderInputAttachmentArrayNonUniformIndexing(boolean value)
    {
        features12.shaderInputAttachmentArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderUniformTexelBufferArrayNonUniformIndexing()
    {
        return features12.shaderUniformTexelBufferArrayNonUniformIndexing();
    }

    public void NativeSetShaderUniformTexelBufferArrayNonUniformIndexing(boolean value)
    {
        features12.shaderUniformTexelBufferArrayNonUniformIndexing(value);
    }

    public boolean NativeHasShaderStorageTexelBufferArrayNonUniformIndexing()
    {
        return features12.shaderStorageTexelBufferArrayNonUniformIndexing();
    }

    public void NativeSetShaderStorageTexelBufferArrayNonUniformIndexing(boolean value)
    {
        features12.shaderStorageTexelBufferArrayNonUniformIndexing(value);
    }

    public boolean NativeHasDescriptorBindingUniformBufferUpdateAfterBind()
    {
        return features12.descriptorBindingUniformBufferUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingUniformBufferUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingUniformBufferUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingSampledImageUpdateAfterBind()
    {
        return features12.descriptorBindingSampledImageUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingSampledImageUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingSampledImageUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingStorageImageUpdateAfterBind()
    {
        return features12.descriptorBindingStorageImageUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingStorageImageUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingStorageImageUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingStorageBufferUpdateAfterBind()
    {
        return features12.descriptorBindingStorageBufferUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingStorageBufferUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingStorageBufferUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingUniformTexelBufferUpdateAfterBind()
    {
        return features12.descriptorBindingUniformTexelBufferUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingUniformTexelBufferUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingUniformTexelBufferUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingStorageTexelBufferUpdateAfterBind()
    {
        return features12.descriptorBindingStorageTexelBufferUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingStorageTexelBufferUpdateAfterBind(boolean value)
    {
        features12.descriptorBindingStorageTexelBufferUpdateAfterBind(value);
    }

    public boolean NativeHasDescriptorBindingUpdateUnusedWhilePending()
    {
        return features12.descriptorBindingUpdateUnusedWhilePending();
    }

    public void NativeSetDescriptorBindingUpdateUnusedWhilePending(boolean value)
    {
        features12.descriptorBindingUpdateUnusedWhilePending(value);
    }

    public boolean NativeHasDescriptorBindingPartiallyBound()
    {
        return features12.descriptorBindingPartiallyBound();
    }

    public void NativeSetDescriptorBindingPartiallyBound(boolean value)
    {
        features12.descriptorBindingPartiallyBound(value);
    }

    public boolean NativeHasDescriptorBindingVariableDescriptorCount()
    {
        return features12.descriptorBindingVariableDescriptorCount();
    }

    public void NativeSetDescriptorBindingVariableDescriptorCount(boolean value)
    {
        features12.descriptorBindingVariableDescriptorCount(value);
    }

    public boolean NativeHasRuntimeDescriptorArray()
    {
        return features12.runtimeDescriptorArray();
    }

    public void NativeSetRuntimeDescriptorArray(boolean value)
    {
        features12.runtimeDescriptorArray(value);
    }

    public boolean NativeHasSamplerFilterMinmax()
    {
        return features12.samplerFilterMinmax();
    }

    public void NativeSetSamplerFilterMinmax(boolean value)
    {
        features12.samplerFilterMinmax(value);
    }

    public boolean NativeHasScalarBlockLayout()
    {
        return features12.scalarBlockLayout();
    }

    public void NativeSetScalarBlockLayout(boolean value)
    {
        features12.scalarBlockLayout(value);
    }

    public boolean NativeHasImagelessFramebuffer()
    {
        return features12.imagelessFramebuffer();
    }

    public void NativeSetImagelessFramebuffer(boolean value)
    {
        features12.imagelessFramebuffer(value);
    }

    public boolean NativeHasUniformBufferStandardLayout()
    {
        return features12.uniformBufferStandardLayout();
    }

    public void NativeSetUniformBufferStandardLayout(boolean value)
    {
        features12.uniformBufferStandardLayout(value);
    }

    public boolean NativeHasShaderSubgroupExtendedTypes()
    {
        return features12.shaderSubgroupExtendedTypes();
    }

    public void NativeSetShaderSubgroupExtendedTypes(boolean value)
    {
        features12.shaderSubgroupExtendedTypes(value);
    }

    public boolean NativeHasSeparateDepthStencilLayouts()
    {
        return features12.separateDepthStencilLayouts();
    }

    public void NativeSetSeparateDepthStencilLayouts(boolean value)
    {
        features12.separateDepthStencilLayouts(value);
    }

    public boolean NativeHasHostQueryReset()
    {
        return features12.hostQueryReset();
    }

    public void NativeSetHostQueryReset(boolean value)
    {
        features12.hostQueryReset(value);
    }

    public boolean NativeHasTimelineSemaphore()
    {
        return features12.timelineSemaphore();
    }

    public void NativeSetTimelineSemaphore(boolean value)
    {
        features12.timelineSemaphore(value);
    }

    public boolean NativeHasBufferDeviceAddress()
    {
        return features12.bufferDeviceAddress();
    }

    public void NativeSetBufferDeviceAddress(boolean value)
    {
        features12.bufferDeviceAddress(value);
    }

    public boolean NativeHasBufferDeviceAddressCaptureReplay()
    {
        return features12.bufferDeviceAddressCaptureReplay();
    }

    public void NativeSetBufferDeviceAddressCaptureReplay(boolean value)
    {
        features12.bufferDeviceAddressCaptureReplay(value);
    }

    public boolean NativeHasBufferDeviceAddressMultiDevice()
    {
        return features12.bufferDeviceAddressMultiDevice();
    }

    public void NativeSetBufferDeviceAddressMultiDevice(boolean value)
    {
        features12.bufferDeviceAddressMultiDevice(value);
    }

    public boolean NativeHasVulkanMemoryModel()
    {
        return features12.vulkanMemoryModel();
    }

    public void NativeSetVulkanMemoryModel(boolean value)
    {
        features12.vulkanMemoryModel(value);
    }

    public boolean NativeHasVulkanMemoryModelDeviceScope()
    {
        return features12.vulkanMemoryModelDeviceScope();
    }

    public void NativeSetVulkanMemoryModelDeviceScope(boolean value)
    {
        features12.vulkanMemoryModelDeviceScope(value);
    }

    public boolean NativeHasVulkanMemoryModelAvailabilityVisibilityChains()
    {
        return features12.vulkanMemoryModelAvailabilityVisibilityChains();
    }

    public void NativeSetVulkanMemoryModelAvailabilityVisibilityChains(boolean value)
    {
        features12.vulkanMemoryModelAvailabilityVisibilityChains(value);
    }

    public boolean NativeHasShaderOutputViewportIndex()
    {
        return features12.shaderOutputViewportIndex();
    }

    public void NativeSetShaderOutputViewportIndex(boolean value)
    {
        features12.shaderOutputViewportIndex(value);
    }

    public boolean NativeHasShaderOutputLayer()
    {
        return features12.shaderOutputLayer();
    }

    public void NativeSetShaderOutputLayer(boolean value)
    {
        features12.shaderOutputLayer(value);
    }

    public boolean NativeHasSubgroupBroadcastDynamicId()
    {
        return features12.subgroupBroadcastDynamicId();
    }

    public void NativeSetSubgroupBroadcastDynamicId(boolean value)
    {
        features12.subgroupBroadcastDynamicId(value);
    }

    public boolean NativeHasRobustImageAccess()
    {
        return features13.robustImageAccess();
    }

    public void NativeSetRobustImageAccess(boolean value)
    {
        features13.robustImageAccess(value);
    }

    public boolean NativeHasInlineUniformBlock()
    {
        return features13.inlineUniformBlock();
    }

    public void NativeSetInlineUniformBlock(boolean value)
    {
        features13.inlineUniformBlock(value);
    }

    public boolean NativeHasDescriptorBindingInlineUniformBlockUpdateAfterBind()
    {
        return features13.descriptorBindingInlineUniformBlockUpdateAfterBind();
    }

    public void NativeSetDescriptorBindingInlineUniformBlockUpdateAfterBind(boolean value)
    {
        features13.descriptorBindingInlineUniformBlockUpdateAfterBind(value);
    }

    public boolean NativeHasPipelineCreationCacheControl()
    {
        return features13.pipelineCreationCacheControl();
    }

    public void NativeSetPipelineCreationCacheControl(boolean value)
    {
        features13.pipelineCreationCacheControl(value);
    }

    public boolean NativeHasPrivateData()
    {
        return features13.privateData();
    }

    public void NativeSetPrivateData(boolean value)
    {
        features13.privateData(value);
    }

    public boolean NativeHasShaderDemoteToHelperInvocation()
    {
        return features13.shaderDemoteToHelperInvocation();
    }

    public void NativeSetShaderDemoteToHelperInvocation(boolean value)
    {
        features13.shaderDemoteToHelperInvocation(value);
    }

    public boolean NativeHasShaderTerminateInvocation()
    {
        return features13.shaderTerminateInvocation();
    }

    public void NativeSetShaderTerminateInvocation(boolean value)
    {
        features13.shaderTerminateInvocation(value);
    }

    public boolean NativeHasSubgroupSizeControl()
    {
        return features13.subgroupSizeControl();
    }

    public void NativeSetSubgroupSizeControl(boolean value)
    {
        features13.subgroupSizeControl(value);
    }

    public boolean NativeHasComputeFullSubgroups()
    {
        return features13.computeFullSubgroups();
    }

    public void NativeSetComputeFullSubgroups(boolean value)
    {
        features13.computeFullSubgroups(value);
    }

    public boolean NativeHasSynchronization2()
    {
        return features13.synchronization2();
    }

    public void NativeSetSynchronization2(boolean value)
    {
        features13.synchronization2(value);
    }

    public boolean NativeHasTextureCompressionASTC_HDR()
    {
        return features13.textureCompressionASTC_HDR();
    }

    public void NativeSetTextureCompressionASTC_HDR(boolean value)
    {
        features13.textureCompressionASTC_HDR(value);
    }

    public boolean NativeHasShaderZeroInitializeWorkgroupMemory()
    {
        return features13.shaderZeroInitializeWorkgroupMemory();
    }

    public void NativeSetShaderZeroInitializeWorkgroupMemory(boolean value)
    {
        features13.shaderZeroInitializeWorkgroupMemory(value);
    }

    public boolean NativeHasDynamicRendering()
    {
        return features13.dynamicRendering();
    }

    public void NativeSetDynamicRendering(boolean value)
    {
        features13.dynamicRendering(value);
    }

    public boolean NativeHasShaderIntegerDotProduct()
    {
        return features13.shaderIntegerDotProduct();
    }

    public void NativeSetShaderIntegerDotProduct(boolean value)
    {
        features13.shaderIntegerDotProduct(value);
    }

    public boolean NativeHasMaintenance4()
    {
        return features13.maintenance4();
    }

    public void NativeSetMaintenance4(boolean value)
    {
        features13.maintenance4(value);
    }

    public boolean NativeHasGlobalPriorityQuery()
    {
        return features14.globalPriorityQuery();
    }

    public void NativeSetGlobalPriorityQuery(boolean value)
    {
        features14.globalPriorityQuery(value);
    }

    public boolean NativeHasShaderSubgroupRotate()
    {
        return features14.shaderSubgroupRotate();
    }

    public void NativeSetShaderSubgroupRotate(boolean value)
    {
        features14.shaderSubgroupRotate(value);
    }

    public boolean NativeHasShaderSubgroupRotateClustered()
    {
        return features14.shaderSubgroupRotateClustered();
    }

    public void NativeSetShaderSubgroupRotateClustered(boolean value)
    {
        features14.shaderSubgroupRotateClustered(value);
    }

    public boolean NativeHasShaderFloatControls2()
    {
        return features14.shaderFloatControls2();
    }

    public void NativeSetShaderFloatControls2(boolean value)
    {
        features14.shaderFloatControls2(value);
    }

    public boolean NativeHasShaderExpectAssume()
    {
        return features14.shaderExpectAssume();
    }

    public void NativeSetShaderExpectAssume(boolean value)
    {
        features14.shaderExpectAssume(value);
    }

    public boolean NativeHasRectangularLines()
    {
        return features14.rectangularLines();
    }

    public void NativeSetRectangularLines(boolean value)
    {
        features14.rectangularLines(value);
    }

    public boolean NativeHasBresenhamLines()
    {
        return features14.bresenhamLines();
    }

    public void NativeSetBresenhamLines(boolean value)
    {
        features14.bresenhamLines(value);
    }

    public boolean NativeHasSmoothLines()
    {
        return features14.smoothLines();
    }

    public void NativeSetSmoothLines(boolean value)
    {
        features14.smoothLines(value);
    }

    public boolean NativeHasStippledRectangularLines()
    {
        return features14.stippledRectangularLines();
    }

    public void NativeSetStippledRectangularLines(boolean value)
    {
        features14.stippledRectangularLines(value);
    }

    public boolean NativeHasStippledBresenhamLines()
    {
        return features14.stippledBresenhamLines();
    }

    public void NativeSetStippledBresenhamLines(boolean value)
    {
        features14.stippledBresenhamLines(value);
    }

    public boolean NativeHasStippledSmoothLines()
    {
        return features14.stippledSmoothLines();
    }

    public void NativeSetStippledSmoothLines(boolean value)
    {
        features14.stippledSmoothLines(value);
    }

    public boolean NativeHasVertexAttributeInstanceRateDivisor()
    {
        return features14.vertexAttributeInstanceRateDivisor();
    }

    public void NativeSetVertexAttributeInstanceRateDivisor(boolean value)
    {
        features14.vertexAttributeInstanceRateDivisor(value);
    }

    public boolean NativeHasVertexAttributeInstanceRateZeroDivisor()
    {
        return features14.vertexAttributeInstanceRateZeroDivisor();
    }

    public void NativeSetVertexAttributeInstanceRateZeroDivisor(boolean value)
    {
        features14.vertexAttributeInstanceRateZeroDivisor(value);
    }

    public boolean NativeHasIndexTypeUint8()
    {
        return features14.indexTypeUint8();
    }

    public void NativeSetIndexTypeUint8(boolean value)
    {
        features14.indexTypeUint8(value);
    }

    public boolean NativeHasDynamicRenderingLocalRead()
    {
        return features14.dynamicRenderingLocalRead();
    }

    public void NativeSetDynamicRenderingLocalRead(boolean value)
    {
        features14.dynamicRenderingLocalRead(value);
    }

    public boolean NativeHasMaintenance5()
    {
        return features14.maintenance5();
    }

    public void NativeSetMaintenance5(boolean value)
    {
        features14.maintenance5(value);
    }

    public boolean NativeHasMaintenance6()
    {
        return features14.maintenance6();
    }

    public void NativeSetMaintenance6(boolean value)
    {
        features14.maintenance6(value);
    }

    public boolean NativeHasPipelineProtectedAccess()
    {
        return features14.pipelineProtectedAccess();
    }

    public void NativeSetPipelineProtectedAccess(boolean value)
    {
        features14.pipelineProtectedAccess(value);
    }

    public boolean NativeHasPipelineRobustness()
    {
        return features14.pipelineRobustness();
    }

    public void NativeSetPipelineRobustness(boolean value)
    {
        features14.pipelineRobustness(value);
    }

    public boolean NativeHasHostImageCopy()
    {
        return features14.hostImageCopy();
    }

    public void NativeSetHostImageCopy(boolean value)
    {
        features14.hostImageCopy(value);
    }

    public boolean NativeHasPushDescriptor()
    {
        return features14.pushDescriptor();
    }

    public void NativeSetPushDescriptor(boolean value)
    {
        features14.pushDescriptor(value);
    }
}
