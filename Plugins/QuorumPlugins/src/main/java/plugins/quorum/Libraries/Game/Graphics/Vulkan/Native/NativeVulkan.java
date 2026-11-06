package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Language.Types.Integer_;
import quorum.Libraries.Language.Types.Text_;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/*
Helpers the native Vulkan strategies share: turning Quorum arrays into Java
ones for C, finding the handles of the objects Quorum passes in, and wrapping
GPU memory in Java buffers. The C is in quorum-natives' src/vulkan, which is
in the iOS archive and Android's libGameEngineCPlugins.so (loaded by Game).
*/
public class NativeVulkan
{
    // A Quorum Array<text> as Java strings. An undefined array is empty.
    public static String[] ToStrings(Array_ array)
    {
        if (array == null)
            return new String[0];

        String[] result = new String[array.GetSize()];
        for (int i = 0; i < result.length; i++)
            result[i] = ((Text_)array.Get(i)).GetValue();
        return result;
    }

    // A Quorum Array<integer> as Java ints. An undefined array is empty.
    public static int[] ToIntegers(Array_ array)
    {
        if (array == null)
            return new int[0];

        int[] result = new int[array.GetSize()];
        for (int i = 0; i < result.length; i++)
            result[i] = ((Integer_)array.Get(i)).GetValue();
        return result;
    }

    public static boolean Contains(String[] values, String value)
    {
        for (String candidate : values)
        {
            if (value.equals(candidate))
                return true;
        }
        return false;
    }

    public static NativeVulkanDevice Device(VulkanDevice_ device)
    {
        return (NativeVulkanDevice)((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)device).plugin_.GetStrategy();
    }

    public static long DeviceHandle(VulkanDevice_ device)
    {
        return Device(device).GetDeviceHandle();
    }

    public static long AllocatorHandle(VulkanDevice_ device)
    {
        return ((quorum.Libraries.Game.Graphics.Vulkan.VulkanMemoryAllocator)device.GetMemoryAllocator()).plugin_.GetAllocatorPointer();
    }

    public static plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer Buffer(VulkanBuffer_ buffer)
    {
        return ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)buffer).plugin_;
    }

    /*
    A Java buffer over memory C owns, such as mapped GPU memory, in the
    processor's byte order, as LWJGL's MemoryUtil makes them on the desktop.
    */
    public static ByteBuffer Wrap(long address, int size)
    {
        return WrapNative(address, size).order(ByteOrder.nativeOrder());
    }

    // A direct buffer C can read, in the processor's byte order.
    public static ByteBuffer Allocate(int size)
    {
        return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
    }

    private static native ByteBuffer WrapNative(long address, long size);
}
