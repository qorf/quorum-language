package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Models.Vulkan.MaterialVulkan_;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanDrawQueue3DInstanceMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanDrawQueue3DInstanceMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawQueue3DInstanceMappedMemoryStrategy;

/*
The Java side of Quorum's VulkanDrawQueue3DInstanceMappedMemory. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanDrawQueue3DInstanceMappedMemory), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanDrawQueue3DInstanceMappedMemory, with quorum-natives' src/vulkan).
*/
public class VulkanDrawQueue3DInstanceMappedMemory
{
    public Object me_;

    private final VulkanDrawQueue3DInstanceMappedMemoryStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanDrawQueue3DInstanceMappedMemory(this) : new DesktopVulkanDrawQueue3DInstanceMappedMemory(this);

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        strategy.CreateNative(quorumBuffer);
    }

    public void PrepareForTransfers()
    {
        strategy.PrepareForTransfers();
    }

    public void TransferInstanceData(VulkanBuffer_ vertexBuffer, VulkanBuffer_ indexBuffer, MaterialVulkan_ material, Texture_ texture, Color_ color, Matrix4_ modelMatrix)
    {
        strategy.TransferInstanceData(vertexBuffer, indexBuffer, material, texture, color, modelMatrix);
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanDrawQueue3DInstanceMappedMemoryStrategy GetStrategy()
    {
        return strategy;
    }
}
