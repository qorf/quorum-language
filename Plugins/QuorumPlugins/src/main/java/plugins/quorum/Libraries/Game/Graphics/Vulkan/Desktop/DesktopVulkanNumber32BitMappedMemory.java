package plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanNumber32BitMappedMemory;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanNumber32BitMappedMemoryStrategy;
import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.FloatBuffer;

public class DesktopVulkanNumber32BitMappedMemory implements VulkanNumber32BitMappedMemoryStrategy
{
    private final VulkanNumber32BitMappedMemory peer;

    public DesktopVulkanNumber32BitMappedMemory(VulkanNumber32BitMappedMemory peer)
    {
        this.peer = peer;
    }

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

    public void Dispose()
    {
        /*
        Null out the buffer so it can be garbage collected. We don't manually free the memory because we didn't directly
        allocate it -- we just wrapped a chunk of memory that was allocated by the VulkanBuffer. The VulkanBuffer is
        responsible for deallocating the memory during an Unmap operation.
        */
        floatBuffer = null;
    }
}
