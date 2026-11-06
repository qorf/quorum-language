package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import quorum.Libraries.Compute.Matrix3_;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import java.nio.ByteBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanPushConstantBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanPushConstantBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPushConstantBufferStrategy;

/*
The Java side of Quorum's VulkanPushConstantBuffer. Its work is done by a strategy: LWJGL on
the desktop (DesktopVulkanPushConstantBuffer), or Quorum's own Vulkan layer in C on iOS and
Android (NativeVulkanPushConstantBuffer, with quorum-natives' src/vulkan).
*/
public class VulkanPushConstantBuffer
{
    public Object me_;

    private final VulkanPushConstantBufferStrategy strategy = VulkanPlatform.UsesNativeVulkan() ? new NativeVulkanPushConstantBuffer(this) : new DesktopVulkanPushConstantBuffer(this);

    public void Create(int size)
    {
        strategy.Create(size);
    }

    public void ResetWritePosition()
    {
        strategy.ResetWritePosition();
    }

    public void AddBufferPointer(VulkanBuffer_ quorumBuffer)
    {
        strategy.AddBufferPointer(quorumBuffer);
    }

    public void AddNumber32Bit(double value)
    {
        strategy.AddNumber32Bit(value);
    }

    public void AddNumber64Bit(double value)
    {
        strategy.AddNumber64Bit(value);
    }

    public void AddInteger32Bit(int value)
    {
        strategy.AddInteger32Bit(value);
    }

    public void AddVector3(double x, double y, double z)
    {
        strategy.AddVector3(x, y, z);
    }

    public void AddVector4(double x, double y, double z, double w)
    {
        strategy.AddVector4(x, y, z, w);
    }

    public void AddMatrix3(Matrix3_ matrix)
    {
        strategy.AddMatrix3(matrix);
    }

    public void AddMatrix4(Matrix4_ matrix)
    {
        strategy.AddMatrix4(matrix);
    }

    public ByteBuffer GetByteBuffer()
    {
        return strategy.GetByteBuffer();
    }

    public void Dispose()
    {
        strategy.Dispose();
    }

    public VulkanPushConstantBufferStrategy GetStrategy()
    {
        return strategy;
    }
}
