package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Compute.Matrix3_;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import java.nio.ByteBuffer;

/*
The platform-neutral part of VulkanPushConstantBuffer: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanPushConstantBufferStrategy
{
    void Create(int size);
    void ResetWritePosition();
    void AddBufferPointer(VulkanBuffer_ quorumBuffer);
    void AddNumber32Bit(double value);
    void AddNumber64Bit(double value);
    void AddInteger32Bit(int value);
    void AddVector3(double x, double y, double z);
    void AddVector4(double x, double y, double z, double w);
    void AddMatrix3(Matrix3_ matrix);
    void AddMatrix4(Matrix4_ matrix);
    ByteBuffer GetByteBuffer();
    void Dispose();
}
