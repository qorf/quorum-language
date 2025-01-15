package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import plugins.quorum.Libraries.Game.Graphics.PixelMap;
import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

public class VulkanTextureManager
{
    public Object me_;

    public void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        ByteBuffer pixelBytes = pluginMap.GetPixels();

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            ByteBuffer byteBuffer = MemoryUtil.memByteBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
            byteBuffer.put(pixelBytes);
            byteBuffer.flip();
        }
    }
}
