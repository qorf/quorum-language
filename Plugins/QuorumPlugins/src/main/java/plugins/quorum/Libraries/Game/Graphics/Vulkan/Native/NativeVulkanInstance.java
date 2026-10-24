package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanInstanceStrategy;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanConstants_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_;
import quorum.Libraries.System.Logging.Logger_;

/*
The Vulkan instance on iOS and Android. The C also turns on portability
enumeration when Vulkan offers it (MoltenVK does), and makes a debug messenger
that writes to the system log if the debug utilities extension is asked for.
*/
public class NativeVulkanInstance implements VulkanInstanceStrategy
{
    private final VulkanInstance peer;
    private long instanceHandle = 0L;

    public NativeVulkanInstance(VulkanInstance peer)
    {
        this.peer = peer;
    }

    public boolean CreateNative(String applicationName, int applicationVersion, int apiVersion, Array_ layerNames, Array_ extensionNames)
    {
        VulkanConstants_ constants = (VulkanConstants_)SharedClass.GetStaticClass("Libraries.Game.Graphics.Vulkan.VulkanConstants");
        instanceHandle = CreateInstance(applicationName, applicationVersion,
                constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__VULKAN_ENGINE_NAME_(),
                constants.Get_Libraries_Game_Graphics_Vulkan_VulkanConstants__VULKAN_ENGINE_VERSION_(), apiVersion,
                NativeVulkan.ToStrings(layerNames), NativeVulkan.ToStrings(extensionNames));
        return instanceHandle != 0L;
    }

    public void Dispose()
    {
        if (instanceHandle == 0L)
            return;
        DestroyInstance(instanceHandle);
        instanceHandle = 0L;
    }

    public Logger_ GetLogger()
    {
        return ((VulkanInstance_)peer.me_).GetLogger();
    }

    public long GetInstanceHandle()
    {
        return instanceHandle;
    }

    public static long InstanceHandle(quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance_ instance)
    {
        return ((NativeVulkanInstance)((quorum.Libraries.Game.Graphics.Vulkan.VulkanInstance)instance).plugin_.GetStrategy()).GetInstanceHandle();
    }

    private static native long CreateInstance(String applicationName, int applicationVersion, String engineName, int engineVersion,
            int apiVersion, String[] layers, String[] extensions);
    private static native void DestroyInstance(long instance);
}
