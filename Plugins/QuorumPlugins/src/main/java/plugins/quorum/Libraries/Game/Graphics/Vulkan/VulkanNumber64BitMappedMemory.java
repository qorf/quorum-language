package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Containers.Number64BitArray;
import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.DoubleBuffer;

public class VulkanNumber64BitMappedMemory
{
    public Object me_;

    DoubleBuffer doubleBuffer = null;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        doubleBuffer = MemoryUtil.memDoubleBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize() / 8);
    }

    public void SetNative(int index, double value)
    {
        doubleBuffer.put(index, value);
    }

    public void SetNative(int index, Number64BitArray_ quorumArray, int arrayOffset, int arrayItems)
    {
        double[] array = ((Number64BitArray)quorumArray).plugin_.doubles;
        // Only update the position if it doesn't match the index.
        // We want to maintain uninterrupted sequential insertion whenever possible.
        if (doubleBuffer.position() != index)
            doubleBuffer.position(index);

        doubleBuffer.put(array, arrayOffset, arrayItems);
    }

    public double GetNative(int index)
    {
        return doubleBuffer.get(index);
    }
}
