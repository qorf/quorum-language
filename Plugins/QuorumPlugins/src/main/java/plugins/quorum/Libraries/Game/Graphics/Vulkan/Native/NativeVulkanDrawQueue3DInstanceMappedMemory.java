package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDrawQueue3DInstanceMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDrawQueue3DInstanceMappedMemoryStrategy;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Models.Vulkan.MaterialVulkan_;
import quorum.Libraries.Game.Graphics.Models.Vulkan.MeshVulkan_;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

// The 3D draw queue's instance data, written into mapped GPU memory on iOS and
// Android: the desktop's code, with Java's own buffers instead of LWJGL's.
public class NativeVulkanDrawQueue3DInstanceMappedMemory implements VulkanDrawQueue3DInstanceMappedMemoryStrategy
{
    private final VulkanDrawQueue3DInstanceMappedMemory peer;

    public NativeVulkanDrawQueue3DInstanceMappedMemory(VulkanDrawQueue3DInstanceMappedMemory peer)
    {
        this.peer = peer;
    }

    ByteBuffer byteBuffer;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        byteBuffer = NativeVulkan.Wrap(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
    }

    public void PrepareForTransfers()
    {
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
    }

    public void TransferInstanceData(VulkanBuffer_ vertexBuffer, VulkanBuffer_ indexBuffer, MaterialVulkan_ material, Texture_ texture, Color_ color, Matrix4_ modelMatrix)
    {
        VulkanBuffer vertexPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)vertexBuffer).plugin_;
        VulkanBuffer indexPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)indexBuffer).plugin_;
        VulkanBuffer materialPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)material.GetBuffer()).plugin_;
        long vertexAddress = vertexPlugin.GetBufferDeviceAddress();
        long indexAddress = indexPlugin.GetBufferDeviceAddress();
        long materialAddress = materialPlugin.GetBufferDeviceAddress();

        byteBuffer.putLong(vertexAddress);
        byteBuffer.putLong(indexAddress);
        byteBuffer.putLong(materialAddress);

        if (texture == null)
        {
            byteBuffer.putInt(0);
            byteBuffer.putInt(0);
        }
        else
        {
            byteBuffer.putInt(1);
            // The shader reads the override from the texture group bound for
            // the draw, so it needs the texture's place inside that group, as
            // materials give theirs (MaterialVulkan). The overall place only
            // matched it in the first group.
            byteBuffer.putInt(texture.GetVulkanTextureLocalIndex());
        }

        if (color == null)
        {
            byteBuffer.putInt(0);
            byteBuffer.putInt(0);
        }
        else
        {
            int a = (int)(255 * color.GetAlpha()) << 24;
            int b = (int)(255 * color.GetBlue()) << 16;
            int g = (int)(255 * color.GetGreen()) << 8;
            int r = (int)(255 * color.GetRed());
            int result = a | b | g | r;

            byteBuffer.putInt(1);
            byteBuffer.putInt(result);
        }

        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column0_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column0_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column0_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column0_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column1_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column1_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column1_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column1_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column2_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column2_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column2_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column2_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column3_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column3_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column3_());
        byteBuffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column3_());
    }

    public void Dispose()
    {
        /*
        Null out the buffer so it can be garbage collected. We don't manually free the memory because we didn't directly
        allocate it -- we just wrapped a chunk of memory that was allocated by the VulkanBuffer. The VulkanBuffer is
        responsible for deallocating the memory during an Unmap operation.
        */
        byteBuffer = null;
    }
}
