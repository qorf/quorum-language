package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Compute.Matrix3_;
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

    public void AddVector3(double x, double y, double z)
    {
        buffer.putFloat((float)x);
        buffer.putFloat((float)y);
        buffer.putFloat((float)z);
    }

    public void AddVector4(double x, double y, double z, double w)
    {
        buffer.putFloat((float)x);
        buffer.putFloat((float)y);
        buffer.putFloat((float)z);
        buffer.putFloat((float)w);
    }

    public void AddMatrix3(Matrix3_ matrix)
    {
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row0column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row1column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row2column0_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row0column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row1column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row2column1_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row0column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row1column2_());
        buffer.putFloat((float)matrix.Get_Libraries_Compute_Matrix3__row2column2_());
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

    public void Dispose()
    {
        if (buffer != null)
            MemoryUtil.memFree(buffer);
        buffer = null;
    }
}
