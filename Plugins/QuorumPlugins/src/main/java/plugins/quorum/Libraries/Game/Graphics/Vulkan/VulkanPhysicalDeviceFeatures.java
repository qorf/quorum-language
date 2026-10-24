package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.vulkan.VkPhysicalDeviceFeatures;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanPhysicalDevice_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPhysicalDeviceFeatures;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPhysicalDeviceFeatures;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceFeaturesStrategy;

/*
The Java side of Quorum's VulkanPhysicalDeviceFeatures. Each feature is read
and written by its number, through a strategy: LWJGL's structures on the
desktop, or an array filled by Quorum's own Vulkan layer on iOS and Android.
*/
public class VulkanPhysicalDeviceFeatures
{
    public Object me_;

    private final VulkanPhysicalDeviceFeaturesStrategy strategy = VulkanPlatform.UsesNativeVulkan()
            ? new NativeVulkanPhysicalDeviceFeatures(this) : new DesktopVulkanPhysicalDeviceFeatures(this);

    public void CreateNative()
    {
        strategy.CreateNative();
    }

    public void GetSupportedDeviceFeatures(VulkanPhysicalDevice_ quorumDevice)
    {
        strategy.GetSupportedDeviceFeatures(quorumDevice);
    }

    public void DisposeNative()
    {
        strategy.DisposeNative();
    }

    public boolean NativeHasRobustBufferAccess()
    {
        return strategy.Has(0);
    }

    public void NativeSetRobustBufferAccess(boolean value)
    {
        strategy.Set(0, value);
    }

    public boolean NativeHasFullDrawIndexUint32()
    {
        return strategy.Has(1);
    }

    public void NativeSetFullDrawIndexUint32(boolean value)
    {
        strategy.Set(1, value);
    }

    public boolean NativeHasImageCubeArray()
    {
        return strategy.Has(2);
    }

    public void NativeSetImageCubeArray(boolean value)
    {
        strategy.Set(2, value);
    }

    public boolean NativeHasIndependentBlend()
    {
        return strategy.Has(3);
    }

    public void NativeSetIndependentBlend(boolean value)
    {
        strategy.Set(3, value);
    }

    public boolean NativeHasGeometryShader()
    {
        return strategy.Has(4);
    }

    public void NativeSetGeometryShader(boolean value)
    {
        strategy.Set(4, value);
    }

    public boolean NativeHasTessellationShader()
    {
        return strategy.Has(5);
    }

    public void NativeSetTessellationShader(boolean value)
    {
        strategy.Set(5, value);
    }

    public boolean NativeHasSampleRateShading()
    {
        return strategy.Has(6);
    }

    public void NativeSetSampleRateShading(boolean value)
    {
        strategy.Set(6, value);
    }

    public boolean NativeHasDualSrcBlend()
    {
        return strategy.Has(7);
    }

    public void NativeSetDualSrcBlend(boolean value)
    {
        strategy.Set(7, value);
    }

    public boolean NativeHasLogicOp()
    {
        return strategy.Has(8);
    }

    public void NativeSetLogicOp(boolean value)
    {
        strategy.Set(8, value);
    }

    public boolean NativeHasMultiDrawIndirect()
    {
        return strategy.Has(9);
    }

    public void NativeSetMultiDrawIndirect(boolean value)
    {
        strategy.Set(9, value);
    }

    public boolean NativeHasDrawIndirectFirstInstance()
    {
        return strategy.Has(10);
    }

    public void NativeSetDrawIndirectFirstInstance(boolean value)
    {
        strategy.Set(10, value);
    }

    public boolean NativeHasDepthClamp()
    {
        return strategy.Has(11);
    }

    public void NativeSetDepthClamp(boolean value)
    {
        strategy.Set(11, value);
    }

    public boolean NativeHasDepthBiasClamp()
    {
        return strategy.Has(12);
    }

    public void NativeSetDepthBiasClamp(boolean value)
    {
        strategy.Set(12, value);
    }

    public boolean NativeHasFillModeNonSolid()
    {
        return strategy.Has(13);
    }

    public void NativeSetFillModeNonSolid(boolean value)
    {
        strategy.Set(13, value);
    }

    public boolean NativeHasDepthBounds()
    {
        return strategy.Has(14);
    }

    public void NativeSetDepthBounds(boolean value)
    {
        strategy.Set(14, value);
    }

    public boolean NativeHasWideLines()
    {
        return strategy.Has(15);
    }

    public void NativeSetWideLines(boolean value)
    {
        strategy.Set(15, value);
    }

    public boolean NativeHasLargePoints()
    {
        return strategy.Has(16);
    }

    public void NativeSetLargePoints(boolean value)
    {
        strategy.Set(16, value);
    }

    public boolean NativeHasAlphaToOne()
    {
        return strategy.Has(17);
    }

    public void NativeSetAlphaToOne(boolean value)
    {
        strategy.Set(17, value);
    }

    public boolean NativeHasMultiViewport()
    {
        return strategy.Has(18);
    }

    public void NativeSetMultiViewport(boolean value)
    {
        strategy.Set(18, value);
    }

    public boolean NativeHasSamplerAnisotropy()
    {
        return strategy.Has(19);
    }

    public void NativeSetSamplerAnisotropy(boolean value)
    {
        strategy.Set(19, value);
    }

    public boolean NativeHasTextureCompressionETC2()
    {
        return strategy.Has(20);
    }

    public void NativeSetTextureCompressionETC2(boolean value)
    {
        strategy.Set(20, value);
    }

    public boolean NativeHasTextureCompressionASTC_LDR()
    {
        return strategy.Has(21);
    }

    public void NativeSetTextureCompressionASTC_LDR(boolean value)
    {
        strategy.Set(21, value);
    }

    public boolean NativeHasTextureCompressionBC()
    {
        return strategy.Has(22);
    }

    public void NativeSetTextureCompressionBC(boolean value)
    {
        strategy.Set(22, value);
    }

    public boolean NativeHasOcclusionQueryPrecise()
    {
        return strategy.Has(23);
    }

    public void NativeSetOcclusionQueryPrecise(boolean value)
    {
        strategy.Set(23, value);
    }

    public boolean NativeHasPipelineStatisticsQuery()
    {
        return strategy.Has(24);
    }

    public void NativeSetPipelineStatisticsQuery(boolean value)
    {
        strategy.Set(24, value);
    }

    public boolean NativeHasVertexPipelineStoresAndAtomics()
    {
        return strategy.Has(25);
    }

    public void NativeSetVertexPipelineStoresAndAtomics(boolean value)
    {
        strategy.Set(25, value);
    }

    public boolean NativeHasFragmentStoresAndAtomics()
    {
        return strategy.Has(26);
    }

    public void NativeSetFragmentStoresAndAtomics(boolean value)
    {
        strategy.Set(26, value);
    }

    public boolean NativeHasShaderTessellationAndGeometryPointSize()
    {
        return strategy.Has(27);
    }

    public void NativeSetShaderTessellationAndGeometryPointSize(boolean value)
    {
        strategy.Set(27, value);
    }

    public boolean NativeHasShaderImageGatherExtended()
    {
        return strategy.Has(28);
    }

    public void NativeSetShaderImageGatherExtended(boolean value)
    {
        strategy.Set(28, value);
    }

    public boolean NativeHasShaderStorageImageExtendedFormats()
    {
        return strategy.Has(29);
    }

    public void NativeSetShaderStorageImageExtendedFormats(boolean value)
    {
        strategy.Set(29, value);
    }

    public boolean NativeHasShaderStorageImageMultisample()
    {
        return strategy.Has(30);
    }

    public void NativeSetShaderStorageImageMultisample(boolean value)
    {
        strategy.Set(30, value);
    }

    public boolean NativeHasShaderStorageImageReadWithoutFormat()
    {
        return strategy.Has(31);
    }

    public void NativeSetShaderStorageImageReadWithoutFormat(boolean value)
    {
        strategy.Set(31, value);
    }

    public boolean NativeHasShaderStorageImageWriteWithoutFormat()
    {
        return strategy.Has(32);
    }

    public void NativeSetShaderStorageImageWriteWithoutFormat(boolean value)
    {
        strategy.Set(32, value);
    }

    public boolean NativeHasShaderUniformBufferArrayDynamicIndexing()
    {
        return strategy.Has(33);
    }

    public void NativeSetShaderUniformBufferArrayDynamicIndexing(boolean value)
    {
        strategy.Set(33, value);
    }

    public boolean NativeHasShaderSampledImageArrayDynamicIndexing()
    {
        return strategy.Has(34);
    }

    public void NativeSetShaderSampledImageArrayDynamicIndexing(boolean value)
    {
        strategy.Set(34, value);
    }

    public boolean NativeHasShaderStorageBufferArrayDynamicIndexing()
    {
        return strategy.Has(35);
    }

    public void NativeSetShaderStorageBufferArrayDynamicIndexing(boolean value)
    {
        strategy.Set(35, value);
    }

    public boolean NativeHasShaderStorageImageArrayDynamicIndexing()
    {
        return strategy.Has(36);
    }

    public void NativeSetShaderStorageImageArrayDynamicIndexing(boolean value)
    {
        strategy.Set(36, value);
    }

    public boolean NativeHasShaderClipDistance()
    {
        return strategy.Has(37);
    }

    public void NativeSetShaderClipDistance(boolean value)
    {
        strategy.Set(37, value);
    }

    public boolean NativeHasShaderCullDistance()
    {
        return strategy.Has(38);
    }

    public void NativeSetShaderCullDistance(boolean value)
    {
        strategy.Set(38, value);
    }

    public boolean NativeHasShaderFloat64()
    {
        return strategy.Has(39);
    }

    public void NativeSetShaderFloat64(boolean value)
    {
        strategy.Set(39, value);
    }

    public boolean NativeHasShaderInt64()
    {
        return strategy.Has(40);
    }

    public void NativeSetShaderInt64(boolean value)
    {
        strategy.Set(40, value);
    }

    public boolean NativeHasShaderInt16()
    {
        return strategy.Has(41);
    }

    public void NativeSetShaderInt16(boolean value)
    {
        strategy.Set(41, value);
    }

    public boolean NativeHasShaderResourceResidency()
    {
        return strategy.Has(42);
    }

    public void NativeSetShaderResourceResidency(boolean value)
    {
        strategy.Set(42, value);
    }

    public boolean NativeHasShaderResourceMinLod()
    {
        return strategy.Has(43);
    }

    public void NativeSetShaderResourceMinLod(boolean value)
    {
        strategy.Set(43, value);
    }

    public boolean NativeHasSparseBinding()
    {
        return strategy.Has(44);
    }

    public void NativeSetSparseBinding(boolean value)
    {
        strategy.Set(44, value);
    }

    public boolean NativeHasSparseResidencyBuffer()
    {
        return strategy.Has(45);
    }

    public void NativeSetSparseResidencyBuffer(boolean value)
    {
        strategy.Set(45, value);
    }

    public boolean NativeHasSparseResidencyImage2D()
    {
        return strategy.Has(46);
    }

    public void NativeSetSparseResidencyImage2D(boolean value)
    {
        strategy.Set(46, value);
    }

    public boolean NativeHasSparseResidencyImage3D()
    {
        return strategy.Has(47);
    }

    public void NativeSetSparseResidencyImage3D(boolean value)
    {
        strategy.Set(47, value);
    }

    public boolean NativeHasSparseResidency2Samples()
    {
        return strategy.Has(48);
    }

    public void NativeSetSparseResidency2Samples(boolean value)
    {
        strategy.Set(48, value);
    }

    public boolean NativeHasSparseResidency4Samples()
    {
        return strategy.Has(49);
    }

    public void NativeSetSparseResidency4Samples(boolean value)
    {
        strategy.Set(49, value);
    }

    public boolean NativeHasSparseResidency8Samples()
    {
        return strategy.Has(50);
    }

    public void NativeSetSparseResidency8Samples(boolean value)
    {
        strategy.Set(50, value);
    }

    public boolean NativeHasSparseResidency16Samples()
    {
        return strategy.Has(51);
    }

    public void NativeSetSparseResidency16Samples(boolean value)
    {
        strategy.Set(51, value);
    }

    public boolean NativeHasSparseResidencyAliased()
    {
        return strategy.Has(52);
    }

    public void NativeSetSparseResidencyAliased(boolean value)
    {
        strategy.Set(52, value);
    }

    public boolean NativeHasVariableMultisampleRate()
    {
        return strategy.Has(53);
    }

    public void NativeSetVariableMultisampleRate(boolean value)
    {
        strategy.Set(53, value);
    }

    public boolean NativeHasInheritedQueries()
    {
        return strategy.Has(54);
    }

    public void NativeSetInheritedQueries(boolean value)
    {
        strategy.Set(54, value);
    }

    public boolean NativeHasStorageBuffer16BitAccess()
    {
        return strategy.Has(55);
    }

    public void NativeSetStorageBuffer16BitAccess(boolean value)
    {
        strategy.Set(55, value);
    }

    public boolean NativeHasUniformAndStorageBuffer16BitAccess()
    {
        return strategy.Has(56);
    }

    public void NativeSetUniformAndStorageBuffer16BitAccess(boolean value)
    {
        strategy.Set(56, value);
    }

    public boolean NativeHasStoragePushConstant16()
    {
        return strategy.Has(57);
    }

    public void NativeSetStoragePushConstant16(boolean value)
    {
        strategy.Set(57, value);
    }

    public boolean NativeHasStorageInputOutput16()
    {
        return strategy.Has(58);
    }

    public void NativeSetStorageInputOutput16(boolean value)
    {
        strategy.Set(58, value);
    }

    public boolean NativeHasMultiview()
    {
        return strategy.Has(59);
    }

    public void NativeSetMultiview(boolean value)
    {
        strategy.Set(59, value);
    }

    public boolean NativeHasMultiviewGeometryShader()
    {
        return strategy.Has(60);
    }

    public void NativeSetMultiviewGeometryShader(boolean value)
    {
        strategy.Set(60, value);
    }

    public boolean NativeHasMultiviewTessellationShader()
    {
        return strategy.Has(61);
    }

    public void NativeSetMultiviewTessellationShader(boolean value)
    {
        strategy.Set(61, value);
    }

    public boolean NativeHasVariablePointersStorageBuffer()
    {
        return strategy.Has(62);
    }

    public void NativeSetVariablePointersStorageBuffer(boolean value)
    {
        strategy.Set(62, value);
    }

    public boolean NativeHasVariablePointers()
    {
        return strategy.Has(63);
    }

    public void NativeSetVariablePointers(boolean value)
    {
        strategy.Set(63, value);
    }

    public boolean NativeHasProtectedMemory()
    {
        return strategy.Has(64);
    }

    public void NativeSetProtectedMemory(boolean value)
    {
        strategy.Set(64, value);
    }

    public boolean NativeHasSamplerYcbcrConversion()
    {
        return strategy.Has(65);
    }

    public void NativeSetSamplerYcbcrConversion(boolean value)
    {
        strategy.Set(65, value);
    }

    public boolean NativeHasShaderDrawParameters()
    {
        return strategy.Has(66);
    }

    public void NativeSetShaderDrawParameters(boolean value)
    {
        strategy.Set(66, value);
    }

    public boolean NativeHasSamplerMirrorClampToEdge()
    {
        return strategy.Has(67);
    }

    public void NativeSetSamplerMirrorClampToEdge(boolean value)
    {
        strategy.Set(67, value);
    }

    public boolean NativeHasDrawIndirectCount()
    {
        return strategy.Has(68);
    }

    public void NativeSetDrawIndirectCount(boolean value)
    {
        strategy.Set(68, value);
    }

    public boolean NativeHasStorageBuffer8BitAccess()
    {
        return strategy.Has(69);
    }

    public void NativeSetStorageBuffer8BitAccess(boolean value)
    {
        strategy.Set(69, value);
    }

    public boolean NativeHasUniformAndStorageBuffer8BitAccess()
    {
        return strategy.Has(70);
    }

    public void NativeSetUniformAndStorageBuffer8BitAccess(boolean value)
    {
        strategy.Set(70, value);
    }

    public boolean NativeHasStoragePushConstant8()
    {
        return strategy.Has(71);
    }

    public void NativeSetStoragePushConstant8(boolean value)
    {
        strategy.Set(71, value);
    }

    public boolean NativeHasShaderBufferInt64Atomics()
    {
        return strategy.Has(72);
    }

    public void NativeSetShaderBufferInt64Atomics(boolean value)
    {
        strategy.Set(72, value);
    }

    public boolean NativeHasShaderSharedInt64Atomics()
    {
        return strategy.Has(73);
    }

    public void NativeSetShaderSharedInt64Atomics(boolean value)
    {
        strategy.Set(73, value);
    }

    public boolean NativeHasShaderFloat16()
    {
        return strategy.Has(74);
    }

    public void NativeSetShaderFloat16(boolean value)
    {
        strategy.Set(74, value);
    }

    public boolean NativeHasShaderInt8()
    {
        return strategy.Has(75);
    }

    public void NativeSetShaderInt8(boolean value)
    {
        strategy.Set(75, value);
    }

    public boolean NativeHasDescriptorIndexing()
    {
        return strategy.Has(76);
    }

    public void NativeSetDescriptorIndexing(boolean value)
    {
        strategy.Set(76, value);
    }

    public boolean NativeHasShaderInputAttachmentArrayDynamicIndexing()
    {
        return strategy.Has(77);
    }

    public void NativeSetShaderInputAttachmentArrayDynamicIndexing(boolean value)
    {
        strategy.Set(77, value);
    }

    public boolean NativeHasShaderUniformTexelBufferArrayDynamicIndexing()
    {
        return strategy.Has(78);
    }

    public void NativeSetShaderUniformTexelBufferArrayDynamicIndexing(boolean value)
    {
        strategy.Set(78, value);
    }

    public boolean NativeHasShaderStorageTexelBufferArrayDynamicIndexing()
    {
        return strategy.Has(79);
    }

    public void NativeSetShaderStorageTexelBufferArrayDynamicIndexing(boolean value)
    {
        strategy.Set(79, value);
    }

    public boolean NativeHasShaderUniformBufferArrayNonUniformIndexing()
    {
        return strategy.Has(80);
    }

    public void NativeSetShaderUniformBufferArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(80, value);
    }

    public boolean NativeHasShaderSampledImageArrayNonUniformIndexing()
    {
        return strategy.Has(81);
    }

    public void NativeSetShaderSampledImageArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(81, value);
    }

    public boolean NativeHasShaderStorageBufferArrayNonUniformIndexing()
    {
        return strategy.Has(82);
    }

    public void NativeSetShaderStorageBufferArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(82, value);
    }

    public boolean NativeHasShaderStorageImageArrayNonUniformIndexing()
    {
        return strategy.Has(83);
    }

    public void NativeSetShaderStorageImageArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(83, value);
    }

    public boolean NativeHasShaderInputAttachmentArrayNonUniformIndexing()
    {
        return strategy.Has(84);
    }

    public void NativeSetShaderInputAttachmentArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(84, value);
    }

    public boolean NativeHasShaderUniformTexelBufferArrayNonUniformIndexing()
    {
        return strategy.Has(85);
    }

    public void NativeSetShaderUniformTexelBufferArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(85, value);
    }

    public boolean NativeHasShaderStorageTexelBufferArrayNonUniformIndexing()
    {
        return strategy.Has(86);
    }

    public void NativeSetShaderStorageTexelBufferArrayNonUniformIndexing(boolean value)
    {
        strategy.Set(86, value);
    }

    public boolean NativeHasDescriptorBindingUniformBufferUpdateAfterBind()
    {
        return strategy.Has(87);
    }

    public void NativeSetDescriptorBindingUniformBufferUpdateAfterBind(boolean value)
    {
        strategy.Set(87, value);
    }

    public boolean NativeHasDescriptorBindingSampledImageUpdateAfterBind()
    {
        return strategy.Has(88);
    }

    public void NativeSetDescriptorBindingSampledImageUpdateAfterBind(boolean value)
    {
        strategy.Set(88, value);
    }

    public boolean NativeHasDescriptorBindingStorageImageUpdateAfterBind()
    {
        return strategy.Has(89);
    }

    public void NativeSetDescriptorBindingStorageImageUpdateAfterBind(boolean value)
    {
        strategy.Set(89, value);
    }

    public boolean NativeHasDescriptorBindingStorageBufferUpdateAfterBind()
    {
        return strategy.Has(90);
    }

    public void NativeSetDescriptorBindingStorageBufferUpdateAfterBind(boolean value)
    {
        strategy.Set(90, value);
    }

    public boolean NativeHasDescriptorBindingUniformTexelBufferUpdateAfterBind()
    {
        return strategy.Has(91);
    }

    public void NativeSetDescriptorBindingUniformTexelBufferUpdateAfterBind(boolean value)
    {
        strategy.Set(91, value);
    }

    public boolean NativeHasDescriptorBindingStorageTexelBufferUpdateAfterBind()
    {
        return strategy.Has(92);
    }

    public void NativeSetDescriptorBindingStorageTexelBufferUpdateAfterBind(boolean value)
    {
        strategy.Set(92, value);
    }

    public boolean NativeHasDescriptorBindingUpdateUnusedWhilePending()
    {
        return strategy.Has(93);
    }

    public void NativeSetDescriptorBindingUpdateUnusedWhilePending(boolean value)
    {
        strategy.Set(93, value);
    }

    public boolean NativeHasDescriptorBindingPartiallyBound()
    {
        return strategy.Has(94);
    }

    public void NativeSetDescriptorBindingPartiallyBound(boolean value)
    {
        strategy.Set(94, value);
    }

    public boolean NativeHasDescriptorBindingVariableDescriptorCount()
    {
        return strategy.Has(95);
    }

    public void NativeSetDescriptorBindingVariableDescriptorCount(boolean value)
    {
        strategy.Set(95, value);
    }

    public boolean NativeHasRuntimeDescriptorArray()
    {
        return strategy.Has(96);
    }

    public void NativeSetRuntimeDescriptorArray(boolean value)
    {
        strategy.Set(96, value);
    }

    public boolean NativeHasSamplerFilterMinmax()
    {
        return strategy.Has(97);
    }

    public void NativeSetSamplerFilterMinmax(boolean value)
    {
        strategy.Set(97, value);
    }

    public boolean NativeHasScalarBlockLayout()
    {
        return strategy.Has(98);
    }

    public void NativeSetScalarBlockLayout(boolean value)
    {
        strategy.Set(98, value);
    }

    public boolean NativeHasImagelessFramebuffer()
    {
        return strategy.Has(99);
    }

    public void NativeSetImagelessFramebuffer(boolean value)
    {
        strategy.Set(99, value);
    }

    public boolean NativeHasUniformBufferStandardLayout()
    {
        return strategy.Has(100);
    }

    public void NativeSetUniformBufferStandardLayout(boolean value)
    {
        strategy.Set(100, value);
    }

    public boolean NativeHasShaderSubgroupExtendedTypes()
    {
        return strategy.Has(101);
    }

    public void NativeSetShaderSubgroupExtendedTypes(boolean value)
    {
        strategy.Set(101, value);
    }

    public boolean NativeHasSeparateDepthStencilLayouts()
    {
        return strategy.Has(102);
    }

    public void NativeSetSeparateDepthStencilLayouts(boolean value)
    {
        strategy.Set(102, value);
    }

    public boolean NativeHasHostQueryReset()
    {
        return strategy.Has(103);
    }

    public void NativeSetHostQueryReset(boolean value)
    {
        strategy.Set(103, value);
    }

    public boolean NativeHasTimelineSemaphore()
    {
        return strategy.Has(104);
    }

    public void NativeSetTimelineSemaphore(boolean value)
    {
        strategy.Set(104, value);
    }

    public boolean NativeHasBufferDeviceAddress()
    {
        return strategy.Has(105);
    }

    public void NativeSetBufferDeviceAddress(boolean value)
    {
        strategy.Set(105, value);
    }

    public boolean NativeHasBufferDeviceAddressCaptureReplay()
    {
        return strategy.Has(106);
    }

    public void NativeSetBufferDeviceAddressCaptureReplay(boolean value)
    {
        strategy.Set(106, value);
    }

    public boolean NativeHasBufferDeviceAddressMultiDevice()
    {
        return strategy.Has(107);
    }

    public void NativeSetBufferDeviceAddressMultiDevice(boolean value)
    {
        strategy.Set(107, value);
    }

    public boolean NativeHasVulkanMemoryModel()
    {
        return strategy.Has(108);
    }

    public void NativeSetVulkanMemoryModel(boolean value)
    {
        strategy.Set(108, value);
    }

    public boolean NativeHasVulkanMemoryModelDeviceScope()
    {
        return strategy.Has(109);
    }

    public void NativeSetVulkanMemoryModelDeviceScope(boolean value)
    {
        strategy.Set(109, value);
    }

    public boolean NativeHasVulkanMemoryModelAvailabilityVisibilityChains()
    {
        return strategy.Has(110);
    }

    public void NativeSetVulkanMemoryModelAvailabilityVisibilityChains(boolean value)
    {
        strategy.Set(110, value);
    }

    public boolean NativeHasShaderOutputViewportIndex()
    {
        return strategy.Has(111);
    }

    public void NativeSetShaderOutputViewportIndex(boolean value)
    {
        strategy.Set(111, value);
    }

    public boolean NativeHasShaderOutputLayer()
    {
        return strategy.Has(112);
    }

    public void NativeSetShaderOutputLayer(boolean value)
    {
        strategy.Set(112, value);
    }

    public boolean NativeHasSubgroupBroadcastDynamicId()
    {
        return strategy.Has(113);
    }

    public void NativeSetSubgroupBroadcastDynamicId(boolean value)
    {
        strategy.Set(113, value);
    }

    public boolean NativeHasRobustImageAccess()
    {
        return strategy.Has(114);
    }

    public void NativeSetRobustImageAccess(boolean value)
    {
        strategy.Set(114, value);
    }

    public boolean NativeHasInlineUniformBlock()
    {
        return strategy.Has(115);
    }

    public void NativeSetInlineUniformBlock(boolean value)
    {
        strategy.Set(115, value);
    }

    public boolean NativeHasDescriptorBindingInlineUniformBlockUpdateAfterBind()
    {
        return strategy.Has(116);
    }

    public void NativeSetDescriptorBindingInlineUniformBlockUpdateAfterBind(boolean value)
    {
        strategy.Set(116, value);
    }

    public boolean NativeHasPipelineCreationCacheControl()
    {
        return strategy.Has(117);
    }

    public void NativeSetPipelineCreationCacheControl(boolean value)
    {
        strategy.Set(117, value);
    }

    public boolean NativeHasPrivateData()
    {
        return strategy.Has(118);
    }

    public void NativeSetPrivateData(boolean value)
    {
        strategy.Set(118, value);
    }

    public boolean NativeHasShaderDemoteToHelperInvocation()
    {
        return strategy.Has(119);
    }

    public void NativeSetShaderDemoteToHelperInvocation(boolean value)
    {
        strategy.Set(119, value);
    }

    public boolean NativeHasShaderTerminateInvocation()
    {
        return strategy.Has(120);
    }

    public void NativeSetShaderTerminateInvocation(boolean value)
    {
        strategy.Set(120, value);
    }

    public boolean NativeHasSubgroupSizeControl()
    {
        return strategy.Has(121);
    }

    public void NativeSetSubgroupSizeControl(boolean value)
    {
        strategy.Set(121, value);
    }

    public boolean NativeHasComputeFullSubgroups()
    {
        return strategy.Has(122);
    }

    public void NativeSetComputeFullSubgroups(boolean value)
    {
        strategy.Set(122, value);
    }

    public boolean NativeHasSynchronization2()
    {
        return strategy.Has(123);
    }

    public void NativeSetSynchronization2(boolean value)
    {
        strategy.Set(123, value);
    }

    public boolean NativeHasTextureCompressionASTC_HDR()
    {
        return strategy.Has(124);
    }

    public void NativeSetTextureCompressionASTC_HDR(boolean value)
    {
        strategy.Set(124, value);
    }

    public boolean NativeHasShaderZeroInitializeWorkgroupMemory()
    {
        return strategy.Has(125);
    }

    public void NativeSetShaderZeroInitializeWorkgroupMemory(boolean value)
    {
        strategy.Set(125, value);
    }

    public boolean NativeHasDynamicRendering()
    {
        return strategy.Has(126);
    }

    public void NativeSetDynamicRendering(boolean value)
    {
        strategy.Set(126, value);
    }

    public boolean NativeHasShaderIntegerDotProduct()
    {
        return strategy.Has(127);
    }

    public void NativeSetShaderIntegerDotProduct(boolean value)
    {
        strategy.Set(127, value);
    }

    public boolean NativeHasMaintenance4()
    {
        return strategy.Has(128);
    }

    public void NativeSetMaintenance4(boolean value)
    {
        strategy.Set(128, value);
    }

    public boolean NativeHasGlobalPriorityQuery()
    {
        return strategy.Has(129);
    }

    public void NativeSetGlobalPriorityQuery(boolean value)
    {
        strategy.Set(129, value);
    }

    public boolean NativeHasShaderSubgroupRotate()
    {
        return strategy.Has(130);
    }

    public void NativeSetShaderSubgroupRotate(boolean value)
    {
        strategy.Set(130, value);
    }

    public boolean NativeHasShaderSubgroupRotateClustered()
    {
        return strategy.Has(131);
    }

    public void NativeSetShaderSubgroupRotateClustered(boolean value)
    {
        strategy.Set(131, value);
    }

    public boolean NativeHasShaderFloatControls2()
    {
        return strategy.Has(132);
    }

    public void NativeSetShaderFloatControls2(boolean value)
    {
        strategy.Set(132, value);
    }

    public boolean NativeHasShaderExpectAssume()
    {
        return strategy.Has(133);
    }

    public void NativeSetShaderExpectAssume(boolean value)
    {
        strategy.Set(133, value);
    }

    public boolean NativeHasRectangularLines()
    {
        return strategy.Has(134);
    }

    public void NativeSetRectangularLines(boolean value)
    {
        strategy.Set(134, value);
    }

    public boolean NativeHasBresenhamLines()
    {
        return strategy.Has(135);
    }

    public void NativeSetBresenhamLines(boolean value)
    {
        strategy.Set(135, value);
    }

    public boolean NativeHasSmoothLines()
    {
        return strategy.Has(136);
    }

    public void NativeSetSmoothLines(boolean value)
    {
        strategy.Set(136, value);
    }

    public boolean NativeHasStippledRectangularLines()
    {
        return strategy.Has(137);
    }

    public void NativeSetStippledRectangularLines(boolean value)
    {
        strategy.Set(137, value);
    }

    public boolean NativeHasStippledBresenhamLines()
    {
        return strategy.Has(138);
    }

    public void NativeSetStippledBresenhamLines(boolean value)
    {
        strategy.Set(138, value);
    }

    public boolean NativeHasStippledSmoothLines()
    {
        return strategy.Has(139);
    }

    public void NativeSetStippledSmoothLines(boolean value)
    {
        strategy.Set(139, value);
    }

    public boolean NativeHasVertexAttributeInstanceRateDivisor()
    {
        return strategy.Has(140);
    }

    public void NativeSetVertexAttributeInstanceRateDivisor(boolean value)
    {
        strategy.Set(140, value);
    }

    public boolean NativeHasVertexAttributeInstanceRateZeroDivisor()
    {
        return strategy.Has(141);
    }

    public void NativeSetVertexAttributeInstanceRateZeroDivisor(boolean value)
    {
        strategy.Set(141, value);
    }

    public boolean NativeHasIndexTypeUint8()
    {
        return strategy.Has(142);
    }

    public void NativeSetIndexTypeUint8(boolean value)
    {
        strategy.Set(142, value);
    }

    public boolean NativeHasDynamicRenderingLocalRead()
    {
        return strategy.Has(143);
    }

    public void NativeSetDynamicRenderingLocalRead(boolean value)
    {
        strategy.Set(143, value);
    }

    public boolean NativeHasMaintenance5()
    {
        return strategy.Has(144);
    }

    public void NativeSetMaintenance5(boolean value)
    {
        strategy.Set(144, value);
    }

    public boolean NativeHasMaintenance6()
    {
        return strategy.Has(145);
    }

    public void NativeSetMaintenance6(boolean value)
    {
        strategy.Set(145, value);
    }

    public boolean NativeHasPipelineProtectedAccess()
    {
        return strategy.Has(146);
    }

    public void NativeSetPipelineProtectedAccess(boolean value)
    {
        strategy.Set(146, value);
    }

    public boolean NativeHasPipelineRobustness()
    {
        return strategy.Has(147);
    }

    public void NativeSetPipelineRobustness(boolean value)
    {
        strategy.Set(147, value);
    }

    public boolean NativeHasHostImageCopy()
    {
        return strategy.Has(148);
    }

    public void NativeSetHostImageCopy(boolean value)
    {
        strategy.Set(148, value);
    }

    public boolean NativeHasPushDescriptor()
    {
        return strategy.Has(149);
    }

    public void NativeSetPushDescriptor(boolean value)
    {
        strategy.Set(149, value);
    }

    // LWJGL's structure, for DesktopVulkanDevice only.
    public VkPhysicalDeviceFeatures GetVulkanFeatures()
    {
        return ((DesktopVulkanPhysicalDeviceFeatures)strategy).vulkanFeatures;
    }

    public VulkanPhysicalDeviceFeaturesStrategy GetStrategy()
    {
        return strategy;
    }
}
