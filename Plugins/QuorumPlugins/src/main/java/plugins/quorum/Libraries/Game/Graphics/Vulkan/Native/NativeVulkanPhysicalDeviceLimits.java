package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanPhysicalDeviceLimitsStrategy;

/*
The device limits on iOS and Android: two arrays, filled by
NativeVulkanPhysicalDevice from quorum-natives' src/vulkan (its
NativeVulkanLimitTable.h, generated with this file from
VulkanPhysicalDeviceLimits.java). Limits that are arrays take one place per
element. Whole numbers and true/false are in integers; the rest in numbers.
*/
public class NativeVulkanPhysicalDeviceLimits implements VulkanPhysicalDeviceLimitsStrategy
{
    public static final int INTEGER_COUNT = 83;
    public static final int NUMBER_COUNT = 13;

    // Where each limit's first value is, in the order of the Get actions.
    private static final int[] SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 50, 51, 54, 55, 56, 57, 58, 0, 1, 59, 60, 2, 62, 63, 64, 65, 66, 4, 5, 67, 68, 69, 70, 71, 72, 6, 73, 74, 75, 76, 7, 9, 11, 12, 77, 78, 79, 80, 81, 82};

    private final long[] integers = new long[INTEGER_COUNT];
    private final double[] numbers = new double[NUMBER_COUNT];

    public long[] GetIntegers()
    {
        return integers;
    }

    public double[] GetNumbers()
    {
        return numbers;
    }

    // A cast, as LWJGL does: an unsigned value above 2^31 becomes negative.
    public int GetInteger(int limit, int index)
    {
        return (int)integers[SLOTS[limit] + index];
    }

    public double GetNumber(int limit, int index)
    {
        return numbers[SLOTS[limit] + index];
    }

    public boolean GetBoolean(int limit, int index)
    {
        return integers[SLOTS[limit] + index] != 0;
    }
}
