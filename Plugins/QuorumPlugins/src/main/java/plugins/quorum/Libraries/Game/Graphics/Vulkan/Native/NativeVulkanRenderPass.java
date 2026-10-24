package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanRenderPass;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanRenderPassStrategy;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanAttachmentDescription_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanAttachmentReference_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubpassDependency_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanSubpassDescription_;

import java.util.ArrayList;

/*
A render pass on iOS and Android. The Quorum descriptions are flattened into
arrays of integers for C, in the layout CreateRenderPass describes. As on the
desktop, a negative value in an attachment description means "not set" (0).
*/
public class NativeVulkanRenderPass implements VulkanRenderPassStrategy
{
    private long renderPassHandle = 0L;

    public NativeVulkanRenderPass(VulkanRenderPass peer)
    {
    }

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptions, Array_ quorumSubpasses, Array_ quorumDependencies)
    {
        if (quorumDescriptions == null || quorumSubpasses == null || quorumDependencies == null
                || quorumDescriptions.IsEmpty() || quorumSubpasses.IsEmpty())
            return false;

        int[] attachments = new int[quorumDescriptions.GetSize() * 9];
        for (int i = 0; i < quorumDescriptions.GetSize(); i++)
        {
            VulkanAttachmentDescription_ description = (VulkanAttachmentDescription_)quorumDescriptions.Get(i);
            int at = i * 9;
            attachments[at] = Set(description.GetFlags());
            attachments[at + 1] = Set(description.GetFormat());
            attachments[at + 2] = Set(description.GetSamples());
            attachments[at + 3] = Set(description.GetLoadOp());
            attachments[at + 4] = Set(description.GetStoreOp());
            attachments[at + 5] = Set(description.GetInitialLayout());
            attachments[at + 6] = Set(description.GetFinalLayout());
            attachments[at + 7] = Set(description.GetStencilLoadOp());
            attachments[at + 8] = Set(description.GetStencilStoreOp());
        }

        ArrayList<Integer> subpasses = new ArrayList<>();
        for (int i = 0; i < quorumSubpasses.GetSize(); i++)
        {
            VulkanSubpassDescription_ subpass = (VulkanSubpassDescription_)quorumSubpasses.Get(i);
            Array_ colors = subpass.GetColorAttachments();
            Array_ inputs = subpass.GetInputAttachments();
            Array_ resolves = subpass.GetResolveAttachments();
            Array_ preserves = subpass.GetPreserveAttachmentIndices();
            VulkanAttachmentReference_ depth = subpass.GetDepthStencilAttachment();

            subpasses.add(subpass.GetPipelineBindPoint());
            subpasses.add(Set(subpass.GetFlags()));
            subpasses.add(Size(colors));
            subpasses.add(Size(inputs));
            subpasses.add(Size(resolves));
            subpasses.add(Size(preserves));
            subpasses.add(depth == null ? 0 : 1);
            subpasses.add(depth == null ? 0 : depth.GetAttachmentIndex());
            subpasses.add(depth == null ? 0 : depth.GetLayout());
            AddReferences(subpasses, colors);
            AddReferences(subpasses, inputs);
            AddReferences(subpasses, resolves);
            for (int index : NativeVulkan.ToIntegers(preserves))
                subpasses.add(index);
        }

        int[] dependencies = new int[quorumDependencies.GetSize() * 7];
        for (int i = 0; i < quorumDependencies.GetSize(); i++)
        {
            VulkanSubpassDependency_ dependency = (VulkanSubpassDependency_)quorumDependencies.Get(i);
            int at = i * 7;
            dependencies[at] = dependency.GetSourceSubpass();
            dependencies[at + 1] = dependency.GetDestinationSubpass();
            dependencies[at + 2] = dependency.GetSourceStageMask();
            dependencies[at + 3] = dependency.GetDestinationStageMask();
            dependencies[at + 4] = dependency.GetSourceAccessMask();
            dependencies[at + 5] = dependency.GetDestinationAccessMask();
            dependencies[at + 6] = dependency.GetDependencyFlags();
        }

        int[] subpassValues = new int[subpasses.size()];
        for (int i = 0; i < subpassValues.length; i++)
            subpassValues[i] = subpasses.get(i);

        renderPassHandle = CreateRenderPass(NativeVulkan.DeviceHandle(quorumDevice), attachments, quorumSubpasses.GetSize(), subpassValues,
                dependencies);
        return renderPassHandle != 0L;
    }

    public long GetRenderPassHandle()
    {
        return renderPassHandle;
    }

    public void DisposeNative(VulkanDevice_ quorumDevice)
    {
        if (renderPassHandle == 0L)
            return;
        DestroyRenderPass(NativeVulkan.DeviceHandle(quorumDevice), renderPassHandle);
        renderPassHandle = 0L;
    }

    private static int Set(int value)
    {
        return value >= 0 ? value : 0;
    }

    private static int Size(Array_ array)
    {
        return array == null ? 0 : array.GetSize();
    }

    private static void AddReferences(ArrayList<Integer> values, Array_ references)
    {
        if (references == null)
            return;
        for (int i = 0; i < references.GetSize(); i++)
        {
            VulkanAttachmentReference_ reference = (VulkanAttachmentReference_)references.Get(i);
            values.add(reference.GetAttachmentIndex());
            values.add(reference.GetLayout());
        }
    }

    /*
    attachments: 9 per attachment (flags, format, samples, load op, store op,
    initial layout, final layout, stencil load op, stencil store op).
    subpasses: for each, bind point, flags, then the counts of color, input
    and resolve references and preserved attachments, then whether it has a
    depth reference, its attachment and layout, then the color, input and
    resolve references (attachment and layout each), then the preserved
    attachments. dependencies: 7 per dependency (source and destination
    subpass, stage masks and access masks, flags).
    */
    private static native long CreateRenderPass(long device, int[] attachments, int subpassCount, int[] subpasses, int[] dependencies);
    private static native void DestroyRenderPass(long device, long renderPass);
}
