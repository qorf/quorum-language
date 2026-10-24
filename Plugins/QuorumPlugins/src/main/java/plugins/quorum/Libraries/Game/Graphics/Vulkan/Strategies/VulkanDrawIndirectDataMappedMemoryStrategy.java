package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

/*
The platform-neutral part of VulkanDrawIndirectDataMappedMemory: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDrawIndirectDataMappedMemoryStrategy
{
    void CreateNative(VulkanBuffer_ quorumBuffer);
    void SetNative(int index, int vertexCount, int instanceCount, int firstVertex, int firstInstance);
    int GetByteStride();
    void Dispose();
}
