package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanDescriptorSetStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorBufferInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorImageInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorResourcesInfo_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayoutBinding_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSet_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;

import java.util.ArrayList;

public class NativeVulkanDescriptorSet implements VulkanDescriptorSetStrategy
{
    private final VulkanDescriptorSet peer;
    private long descriptorSetHandle = 0L;

    public NativeVulkanDescriptorSet(VulkanDescriptorSet peer)
    {
        this.peer = peer;
    }

    /*
    As on the desktop, the last binding's descriptor count is always given as
    the variable descriptor count (the desktop's check for the variable count
    flag is always true).
    */
    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanDescriptorPool_ quorumPool, VulkanDescriptorSetLayout_ quorumLayout)
    {
        long layout = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorSetLayout)quorumLayout).plugin_.GetLayoutHandle();
        long pool = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDescriptorPool)quorumPool).plugin_.GetPoolHandle();
        VulkanDescriptorSetLayoutBinding_ lastBinding = (VulkanDescriptorSetLayoutBinding_)quorumLayout.GetBindings().GetFromEnd();

        long[] result = new long[1];
        int vulkanResult = AllocateDescriptorSet(NativeVulkan.DeviceHandle(quorumDevice), pool, layout, lastBinding.GetDescriptorCount(), result);
        if (vulkanResult != 0)
        {
            ((VulkanDescriptorSet_)peer.me_).Set_Libraries_Game_Graphics_Vulkan_VulkanDescriptorSet__failureCode_(vulkanResult);
            return false;
        }

        descriptorSetHandle = result[0];
        return true;
    }

    public void LinkResources(VulkanDevice_ quorumDevice, VulkanDescriptorResourcesInfo_ quorumResources)
    {
        Writes writes = new Writes();
        writes.Add(quorumResources);
        writes.Update(NativeVulkan.DeviceHandle(quorumDevice), descriptorSetHandle);
    }

    public void LinkResources(VulkanDevice_ quorumDevice, Array_ quorumResourcesArray)
    {
        Writes writes = new Writes();
        for (int i = 0; i < quorumResourcesArray.GetSize(); i++)
            writes.Add((VulkanDescriptorResourcesInfo_)quorumResourcesArray.Get(i));
        writes.Update(NativeVulkan.DeviceHandle(quorumDevice), descriptorSetHandle);
    }

    public long GetDescriptorSetHandle()
    {
        return descriptorSetHandle;
    }

    /*
    The descriptor writes, flattened for C: 7 integers per write (binding,
    type, descriptor count, array element, buffer infos, image infos), then
    each buffer info as handle, offset and range, and each image info as
    sampler, image view and layout.
    */
    private static class Writes
    {
        ArrayList<Integer> writes = new ArrayList<>();
        ArrayList<Long> buffers = new ArrayList<>();
        ArrayList<Long> images = new ArrayList<>();
        int count = 0;

        void Add(VulkanDescriptorResourcesInfo_ resources)
        {
            Array_ quorumBuffers = resources.GetBufferInfo();
            Array_ quorumImages = resources.GetImageInfo();
            int bufferCount = quorumBuffers == null ? 0 : quorumBuffers.GetSize();
            int imageCount = quorumImages == null ? 0 : quorumImages.GetSize();

            writes.add(resources.GetDestinationBinding());
            writes.add(resources.GetDescriptorType());
            writes.add(resources.GetDescriptorCount());
            writes.add(resources.GetDestinationArrayElement());
            writes.add(bufferCount);
            writes.add(imageCount);
            writes.add(0);

            for (int i = 0; i < bufferCount; i++)
            {
                VulkanDescriptorBufferInfo_ info = (VulkanDescriptorBufferInfo_)quorumBuffers.Get(i);
                buffers.add(info.GetBuffer() == null ? 0L : NativeVulkan.Buffer(info.GetBuffer()).GetBufferHandle());
                buffers.add((long)info.GetOffset());
                buffers.add((long)info.GetRange());
            }
            for (int i = 0; i < imageCount; i++)
            {
                VulkanDescriptorImageInfo_ info = (VulkanDescriptorImageInfo_)quorumImages.Get(i);
                images.add(info.GetSampler() == null ? 0L : ((quorum.Libraries.Game.Graphics.Vulkan.VulkanSampler)info.GetSampler()).plugin_.GetSamplerHandle());
                images.add(info.GetImageView() == null ? 0L : ((quorum.Libraries.Game.Graphics.Vulkan.VulkanImageView)info.GetImageView()).plugin_.GetVulkanImageViewHandle());
                images.add((long)info.GetImageLayout());
            }
            count++;
        }

        void Update(long device, long descriptorSet)
        {
            int[] writeValues = new int[writes.size()];
            for (int i = 0; i < writeValues.length; i++)
                writeValues[i] = writes.get(i);
            long[] bufferValues = new long[buffers.size()];
            for (int i = 0; i < bufferValues.length; i++)
                bufferValues[i] = buffers.get(i);
            long[] imageValues = new long[images.size()];
            for (int i = 0; i < imageValues.length; i++)
                imageValues[i] = images.get(i);
            UpdateDescriptorSet(device, descriptorSet, count, writeValues, bufferValues, imageValues);
        }
    }

    // Returns Vulkan's result; the set is in result[0].
    private static native int AllocateDescriptorSet(long device, long pool, long layout, int variableDescriptorCount, long[] result);
    private static native void UpdateDescriptorSet(long device, long descriptorSet, int writeCount, int[] writes, long[] buffers, long[] images);
}
