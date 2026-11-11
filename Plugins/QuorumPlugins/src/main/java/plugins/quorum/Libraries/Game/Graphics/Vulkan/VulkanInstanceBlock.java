package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Models.Vulkan.MaterialVulkan_;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/*
The Java side of Quorum's VulkanInstanceBlock (Dense grass plan DG2): many
instances' data packed once, in the layout VulkanDrawQueue3DInstanceMappedMemory
writes, so a draw queue can take them all in one copy each frame. It is plain
Java memory in the processor's byte order, the same order the mapped GPU
buffers use on the desktop and on iOS and Android, so it works on both.
*/
public class VulkanInstanceBlock
{
    public Object me_;

    // The size of one instance, in bytes: VulkanDrawQueue3D's
    // INSTANCE_DATA_BYTE_SIZE (three 8-byte addresses, four 4-byte numbers,
    // and a 4 by 4 matrix of floats).
    public static final int INSTANCE_BYTE_SIZE = 104;

    private ByteBuffer buffer = Allocate(1);
    private int count = 0;

    private static ByteBuffer Allocate(int instances)
    {
        return ByteBuffer.allocateDirect(Math.max(1, instances) * INSTANCE_BYTE_SIZE).order(ByteOrder.nativeOrder());
    }

    public void SetCapacity(int instances)
    {
        if (instances * INSTANCE_BYTE_SIZE > buffer.capacity())
            buffer = Allocate(instances);
        Empty();
    }

    public void Empty()
    {
        buffer.clear();
        count = 0;
    }

    public int GetCount()
    {
        return count;
    }

    /*
    The packed instances, from the start of the block to the end of the
    last one, for a draw queue to copy.
    */
    public ByteBuffer GetPacked(int first, int instances)
    {
        ByteBuffer view = buffer.duplicate();
        view.position(first * INSTANCE_BYTE_SIZE);
        view.limit((first + instances) * INSTANCE_BYTE_SIZE);
        return view;
    }

    public void AddInstance(VulkanBuffer_ vertexBuffer, VulkanBuffer_ indexBuffer, MaterialVulkan_ material, Texture_ texture, Color_ color, Matrix4_ modelMatrix)
    {
        if (buffer.remaining() < INSTANCE_BYTE_SIZE)
        {
            ByteBuffer larger = Allocate(Math.max(count * 2, count + 1));
            buffer.flip();
            larger.put(buffer);
            buffer = larger;
        }

        VulkanBuffer vertexPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)vertexBuffer).plugin_;
        VulkanBuffer indexPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)indexBuffer).plugin_;
        VulkanBuffer materialPlugin = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)material.GetBuffer()).plugin_;
        buffer.putLong(vertexPlugin.GetBufferDeviceAddress());
        buffer.putLong(indexPlugin.GetBufferDeviceAddress());
        buffer.putLong(materialPlugin.GetBufferDeviceAddress());

        if (texture == null)
        {
            buffer.putInt(0);
            buffer.putInt(0);
        }
        else
        {
            // The texture's place inside its group, as the draw queue writes it.
            buffer.putInt(1);
            buffer.putInt(texture.GetVulkanTextureLocalIndex());
        }

        if (color == null)
        {
            buffer.putInt(0);
            buffer.putInt(0);
        }
        else
        {
            int a = (int)(255 * color.GetAlpha()) << 24;
            int b = (int)(255 * color.GetBlue()) << 16;
            int g = (int)(255 * color.GetGreen()) << 8;
            int r = (int)(255 * color.GetRed());
            buffer.putInt(1);
            buffer.putInt(a | b | g | r);
        }

        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column0_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column0_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column0_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column0_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column1_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column1_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column1_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column1_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column2_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column2_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column2_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column2_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row0column3_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row1column3_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row2column3_());
        buffer.putFloat((float)modelMatrix.Get_Libraries_Compute_Matrix4__row3column3_());
        count = count + 1;
    }
}
