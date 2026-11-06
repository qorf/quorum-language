package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanCommands;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Desktop.DesktopVulkanShaders;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanCommands;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Native.NativeVulkanShaders;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanCommandsStrategy;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanShadersStrategy;

/*
Chooses how Vulkan is reached. On the desktop, the Vulkan peers use LWJGL. On
iOS and Android, where LWJGL doesn't run, they use Quorum's own Vulkan layer in
C (quorum-natives' src/vulkan), which calls MoltenVK on iOS and the phone's own
Vulkan on Android. Each peer asks this class which strategy to make.
*/
public class VulkanPlatform
{
    private static final boolean IOS = DetectIOS();
    private static final boolean ANDROID = DetectAndroid();
    private static VulkanCommandsStrategy commands = null;
    private static VulkanShadersStrategy shaders = null;

    private static boolean DetectIOS()
    {
        String os = System.getProperty("os.name");
        return os != null && os.contains("iOS");
    }

    private static boolean DetectAndroid()
    {
        String runtime = System.getProperty("java.runtime.name");
        return runtime != null && runtime.contains("Android Runtime");
    }

    // True on iOS and Android: the native strategies are used.
    public static boolean UsesNativeVulkan()
    {
        return IOS || ANDROID;
    }

    public static boolean IsIOS()
    {
        return IOS;
    }

    public static boolean IsAndroid()
    {
        return ANDROID;
    }

    // The strategy the command peers (in the Commands package) record with.
    public static VulkanCommandsStrategy GetCommands()
    {
        if (commands == null)
            commands = UsesNativeVulkan() ? new NativeVulkanCommands() : new DesktopVulkanCommands();
        return commands;
    }

    // The strategy SPIRVShaderCompiler and VulkanShaderManager use.
    public static VulkanShadersStrategy GetShaders()
    {
        if (shaders == null)
            shaders = UsesNativeVulkan() ? new NativeVulkanShaders() : new DesktopVulkanShaders();
        return shaders;
    }
}
