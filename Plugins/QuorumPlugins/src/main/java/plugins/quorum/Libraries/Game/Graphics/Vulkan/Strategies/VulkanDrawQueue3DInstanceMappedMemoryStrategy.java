package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Models.Vulkan.MaterialVulkan_;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

/*
The platform-neutral part of VulkanDrawQueue3DInstanceMappedMemory: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanDrawQueue3DInstanceMappedMemoryStrategy
{
    void CreateNative(VulkanBuffer_ quorumBuffer);
    void PrepareForTransfers();
    void TransferInstanceData(VulkanBuffer_ vertexBuffer, VulkanBuffer_ indexBuffer, MaterialVulkan_ material, Texture_ texture, Color_ color, Matrix4_ modelMatrix);
    void Dispose();
}
