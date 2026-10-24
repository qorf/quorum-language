package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Number64BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

/*
The platform-neutral part of VulkanNumber64BitMappedMemory: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanNumber64BitMappedMemoryStrategy
{
    void CreateNative(VulkanBuffer_ quorumBuffer);
    void SetNative(int index, double value);
    void SetNative(int index, Number64BitArray_ quorumArray, int arrayOffset, int arrayItems);
    double GetNative(int index);
    void Dispose();
}
