package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

public class VulkanPushConstantBuffer
{
    public Object me_;

    ByteBuffer buffer = null;

    public void Create(int size)
    {
        if (buffer != null)
            MemoryUtil.memFree(buffer);

        buffer = MemoryUtil.memAlloc(size);
    }

    public void ResetWritePosition()
    {
        buffer.position(0);
    }

    public void AddBufferPointer(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        System.out.println("Push Constant Pointer: " + pluginBuffer.GetBufferDeviceAddress());
        buffer.putLong(pluginBuffer.GetBufferDeviceAddress());
    }
    
    public void AddNumber32Bit(double value)
    {
        buffer.putFloat((float)value);
    }
    
    public void AddNumber64Bit(double value)
    {
        buffer.putDouble(value);
    }
    
    public void AddInteger32Bit(int value)
    {
        buffer.putInt(value);
    }
    
    public void AddMatrix4(Matrix4_ matrix)
    {
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row0column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row1column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row2column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row3column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row0column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row1column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row2column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row3column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row0column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row1column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row2column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row3column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row0column3_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row1column3_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row2column3_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix4__row3column3_());
    }

    public ByteBuffer GetByteBuffer()
    {
        return buffer;
    }
}
