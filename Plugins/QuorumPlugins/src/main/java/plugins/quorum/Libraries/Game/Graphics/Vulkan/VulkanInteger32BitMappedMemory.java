package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Containers.Integer32BitArray;
import quorum.Libraries.Containers.Integer32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.IntBuffer;

public class VulkanInteger32BitMappedMemory
{
    public Object me_;

    IntBuffer intBuffer = null;

    public void CreateNative(VulkanBuffer_ quorumBuffer)
    {
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        intBuffer = MemoryUtil.memIntBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize() / 4);
    }

    public void SetNative(int index, int value)
    {
        intBuffer.put(index, value);
    }

    public void SetNative(int index, Integer32BitArray_ quorumArray, int arrayOffset, int arrayItems)
    {
        int[] array = ((Integer32BitArray)quorumArray).plugin_.ints;
        intBuffer.position(index);
        intBuffer.put(array, arrayOffset, arrayItems);
        intBuffer.position(0);
    }

    public int GetNative(int index)
    {
        return intBuffer.get(index);
    }
}
