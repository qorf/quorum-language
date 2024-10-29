package plugins.quorum.Libraries.Game.Graphics.Shaders;

import quorum.Libraries.Game.Graphics.Shaders.Shader_;

public class Shader
{
    public Object me_;

    long vulkanHandle = 0L;

    public long GetVulkanHandle()
    {
        return vulkanHandle;
    }

    public void SetVulkanHandle(long handle)
    {
        vulkanHandle = handle;
    }

    public Shader_ GetQuorumShader()
    {
        return (Shader_)me_;
    }
}
