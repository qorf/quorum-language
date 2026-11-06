package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanNumber64BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanNumber64BitMappedMemoryStrategy;
import quorum.Libraries.Containers.Number64BitArray;
import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.DoubleBuffer;

// Mapped GPU memory as 64-bit numbers on iOS and Android: the desktop's code,
// with Java's own buffers instead of LWJGL's.
public class NativeVulkanNumber64BitMappedMemory implements VulkanNumber64BitMappedMemoryStrategy
{
    private final VulkanNumber64BitMappedMemory peer;

    public NativeVulkanNumber64BitMappedMemory(VulkanNumber64BitMappedMemory peer)
    {
        this.peer = peer;
    }

    DoubleBuffer doubleBuffer = null;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        doubleBuffer = NativeVulkan.Wrap(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize()).asDoubleBuffer();
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

    public void Dispose()
    {
        /*
        Null out the buffer so it can be garbage collected. We don't manually free the memory because we didn't directly
        allocate it -- we just wrapped a chunk of memory that was allocated by the VulkanBuffer. The VulkanBuffer is
        responsible for deallocating the memory during an Unmap operation.
        */
        doubleBuffer = null;
    }
}
