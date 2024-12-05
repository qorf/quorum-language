package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.FloatBuffer;

public class VulkanNumber32BitMappedMemory
{
    public Object me_;

    FloatBuffer floatBuffer = null;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        floatBuffer = MemoryUtil.memFloatBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize() / 4);
    }

    public void SetNative(int index, double value)
    {
        floatBuffer.put(index, (float)value);
    }

    public void SetNative(int index, Number32BitArray_ quorumArray, int arrayOffset, int arrayItems)
    {
        float[] array = ((Number32BitArray)quorumArray).plugin_.floats;
        // Only update the position if it doesn't match the index.
        // We want to maintain uninterrupted sequential insertion whenever possible.
        if (floatBuffer.position() != index)
            floatBuffer.position(index);

        floatBuffer.put(array, arrayOffset, arrayItems);
    }

    public double GetNative(int index)
    {
        return floatBuffer.get(index);
    }
}
