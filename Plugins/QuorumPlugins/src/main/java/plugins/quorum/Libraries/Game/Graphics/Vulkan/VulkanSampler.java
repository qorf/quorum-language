package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkSamplerCreateInfo;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSamplerInfo_;

import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateSampler;

public class VulkanSampler
{
    public Object me_;

    private long samplerHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, VulkanSamplerInfo_ info)
    {
        VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VkSamplerCreateInfo createInfo = VkSamplerCreateInfo.calloc(stack);
            createInfo.magFilter(info.GetMagnifyFilter());
            createInfo.minFilter(info.GetMinifyFilter());
            createInfo.mipmapMode(info.GetMipMapMode());
            createInfo.addressModeU(info.GetHorizontalWrapMode());
            createInfo.addressModeV(info.GetVerticalWrapMode());
            createInfo.addressModeW(info.GetDepthWrapMode());
            createInfo.mipLodBias((float)info.GetMipLodBias());
            createInfo.anisotropyEnable(info.GetAnisotropyEnable());
            createInfo.maxAnisotropy((float)info.GetMaxAnisotropy());
            createInfo.compareEnable(info.GetCompareEnable());
            createInfo.compareOp(info.GetCompareOp());
            createInfo.minLod((float)info.GetMinLod());
            createInfo.maxLod((float)info.GetMaxLod());
            createInfo.borderColor(info.GetBorderColor());
            createInfo.unnormalizedCoordinates(info.GetUnnormalizedCoordinates());

            if (info.GetFlags() != 0)
                createInfo.flags(info.GetFlags());

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateSampler(pluginDevice.GetDevice(), createInfo, null, handleBuffer);

            if (vulkanResult != VK_SUCCESS)
                return false;

            samplerHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetSamplerHandle()
    {
        return samplerHandle;
    }
}
