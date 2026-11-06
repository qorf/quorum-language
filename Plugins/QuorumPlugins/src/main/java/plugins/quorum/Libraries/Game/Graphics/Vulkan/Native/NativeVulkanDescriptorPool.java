package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorPoolStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.util.Arrays;

public class NativeVulkanDescriptorPool implements VulkanDescriptorPoolStrategy
{
    private long poolHandle = 0L;

    public NativeVulkanDescriptorPool(VulkanDescriptorPool peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, int descriptorType, int poolFlags, int descriptorCount, int maxSets)
    {
        return Create(quorumDevice, new int[] {descriptorType}, new int[] {descriptorCount}, poolFlags, maxSets);
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, int descriptorCount, int maxSets)
    {
        int[] types = NativeVulkan.ToIntegers(descriptorTypes);
        int[] counts = new int[types.length];
        Arrays.fill(counts, descriptorCount);
        return Create(quorumDevice, types, counts, poolFlags, maxSets);
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ descriptorTypes, int poolFlags, Array_ descriptorCounts, int maxSets)
    {
        if (descriptorTypes.GetSize() != descriptorCounts.GetSize())
            return false;
        return Create(quorumDevice, NativeVulkan.ToIntegers(descriptorTypes), NativeVulkan.ToIntegers(descriptorCounts), poolFlags, maxSets);
    }

    private boolean Create(VulkanDevice_ quorumDevice, int[] types, int[] counts, int poolFlags, int maxSets)
    {
        poolHandle = CreateDescriptorPool(NativeVulkan.DeviceHandle(quorumDevice), types, counts, poolFlags, maxSets);
        return poolHandle != 0L;
    }

    public long GetPoolHandle()
    {
        return poolHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (poolHandle == 0L)
            return;
        DestroyDescriptorPool(NativeVulkan.DeviceHandle(quorumDevice), poolHandle);
        poolHandle = 0L;
    }

    private static native long CreateDescriptorPool(long device, int[] types, int[] counts, int flags, int maxSets);
    private static native void DestroyDescriptorPool(long device, long pool);
}
