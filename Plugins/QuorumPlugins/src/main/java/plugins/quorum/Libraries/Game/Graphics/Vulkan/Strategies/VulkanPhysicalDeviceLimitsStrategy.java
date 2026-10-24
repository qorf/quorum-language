package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

/*
The device limits, read by number (the order of VulkanPhysicalDeviceLimits'
Get actions). index is for the limits that are arrays, and 0 for the rest.
*/
public interface VulkanPhysicalDeviceLimitsStrategy
{
    int GetInteger(int limit, int index);
    double GetNumber(int limit, int index);
    boolean GetBoolean(int limit, int index);
}
