package plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies;

import quorum.Libraries.Containers.Integer32BitArray_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

/*
The platform-neutral part of VulkanInteger32BitMappedMemory: what the desktop (LWJGL) and native
(iOS and Android) strategies both do.
*/
public interface VulkanInteger32BitMappedMemoryStrategy
{
    void CreateNative(VulkanBuffer_ quorumBuffer);
    void SetNative(int index, int value);
    void SetNative(int index, Integer32BitArray_ quorumArray, int arrayOffset, int arrayItems);
    int GetNative(int index);
    void Dispose();
}
