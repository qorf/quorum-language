package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorSetLayoutStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayoutBinding_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.util.ArrayList;

public class NativeVulkanDescriptorSetLayout implements VulkanDescriptorSetLayoutStrategy
{
    private long layoutHandle = 0L;

    public NativeVulkanDescriptorSetLayout(VulkanDescriptorSetLayout peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumBindings, int layoutFlags)
    {
        int count = quorumBindings.GetSize();
        // binding, descriptor count, type, stage flags, binding flags, immutable sampler count
        int[] bindings = new int[count * 6];
        ArrayList<Long> samplers = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            VulkanDescriptorSetLayoutBinding_ binding = (VulkanDescriptorSetLayoutBinding_)quorumBindings.Get(i);
            int at = i * 6;
            bindings[at] = binding.GetBinding();
            bindings[at + 1] = binding.GetDescriptorCount();
            bindings[at + 2] = binding.GetDescriptorType();
            bindings[at + 3] = binding.GetStageFlags();
            bindings[at + 4] = binding.GetBindingFlags();

            Array_ immutableSamplers = binding.GetImmutableSamplers();
            int samplerCount = immutableSamplers == null ? 0 : immutableSamplers.GetSize();
            bindings[at + 5] = samplerCount;
            for (int j = 0; j < samplerCount; j++)
                samplers.add(((quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler)immutableSamplers.Get(j)).plugin_.GetSamplerHandle());
        }

        long[] samplerHandles = new long[samplers.size()];
        for (int i = 0; i < samplerHandles.length; i++)
            samplerHandles[i] = samplers.get(i);

        layoutHandle = CreateDescriptorSetLayout(NativeVulkan.DeviceHandle(quorumDevice), bindings, samplerHandles, layoutFlags);
        return layoutHandle != 0L;
    }

    public long GetLayoutHandle()
    {
        return layoutHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (layoutHandle == 0L)
            return;
        DestroyDescriptorSetLayout(NativeVulkan.DeviceHandle(quorumDevice), layoutHandle);
        layoutHandle = 0L;
    }

    /*
    bindings: 6 per binding (binding, descriptor count, descriptor type, stage
    flags, binding flags, number of immutable samplers); samplers: every
    binding's immutable samplers, in order. As on the desktop, binding flags
    are only given to Vulkan if a binding has some, and a binding with
    immutable samplers takes its descriptor count from them.
    */
    private static native long CreateDescriptorSetLayout(long device, int[] bindings, long[] samplers, int flags);
    private static native void DestroyDescriptorSetLayout(long device, long layout);
}
