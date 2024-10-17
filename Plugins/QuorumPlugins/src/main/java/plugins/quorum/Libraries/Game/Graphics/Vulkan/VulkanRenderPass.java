package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Vulkan.*;
import quorum.Libraries.Language.Types.Integer_;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.vulkan.VK10.VK_SUCCESS;
import static org.lwjgl.vulkan.VK10.vkCreateRenderPass;

public class VulkanRenderPass
{
    public Object me_;

    private long vulkanRenderPassHandle = 0L;

    public boolean CreateNative(VulkanDevice_ quorumDevice, Array_ quorumDescriptions,
            Array_ quorumSubpasses, Array_ quorumDependencies)
    {
        if (quorumDescriptions == null || quorumSubpasses == null || quorumSubpasses == null
                || quorumDescriptions.IsEmpty() || quorumSubpasses.IsEmpty() || quorumSubpasses.IsEmpty())
        {
            return false;
        }

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            VulkanDevice pluginDevice = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanDevice)quorumDevice).plugin_;

            VkAttachmentDescription.Buffer attachmentDescriptions = VkAttachmentDescription.calloc(quorumDescriptions.GetSize(), stack);
            for (int i = 0; i < quorumDescriptions.GetSize(); i++)
            {
                VulkanAttachmentDescription_ description = (VulkanAttachmentDescription_)quorumDescriptions.Get(i);
                VkAttachmentDescription vkDescription = attachmentDescriptions.get(i);

                if (description.GetFlags() >= 0)
                    vkDescription.flags(description.GetFlags());
                if (description.GetFormat() >= 0)
                    vkDescription.format(description.GetFormat());
                if (description.GetSamples() >= 0)
                    vkDescription.samples(description.GetSamples());
                if (description.GetLoadOp() >= 0)
                    vkDescription.loadOp(description.GetLoadOp());
                if (description.GetStoreOp() >= 0)
                    vkDescription.storeOp(description.GetStoreOp());
                if (description.GetInitialLayout() >= 0)
                    vkDescription.initialLayout(description.GetInitialLayout());
                if (description.GetFinalLayout() >= 0)
                    vkDescription.finalLayout(description.GetFinalLayout());
                if (description.GetStencilLoadOp() >= 0)
                    vkDescription.finalLayout(description.GetStencilLoadOp());
                if (description.GetStencilStoreOp() >= 0)
                    vkDescription.finalLayout(description.GetStencilStoreOp());
            }



            VkSubpassDescription.Buffer subpasses = VkSubpassDescription.calloc(quorumSubpasses.GetSize(), stack);
            for (int i = 0; i < quorumSubpasses.GetSize(); i++)
            {
                VkSubpassDescription subpass = subpasses.get(i);
                VulkanSubpassDescription_ quorumSubpass = (VulkanSubpassDescription_)quorumSubpasses.Get(i);

                subpass.pipelineBindPoint(quorumSubpass.GetPipelineBindPoint());

                if (quorumSubpass.GetFlags() >= 0)
                    subpass.flags(quorumSubpass.GetFlags());

                Array_ quorumColorReferences = quorumSubpass.GetColorAttachments();
                if (quorumColorReferences != null && !quorumColorReferences.IsEmpty())
                {
                    VkAttachmentReference.Buffer colorReferences = VkAttachmentReference.calloc(quorumColorReferences.GetSize(), stack);
                    for (int j = 0; j < quorumColorReferences.GetSize(); j++)
                    {
                        VulkanAttachmentReference_ reference = (VulkanAttachmentReference_)quorumColorReferences.Get(j);
                        VkAttachmentReference vkReference = colorReferences.get(j);

                        vkReference.attachment(reference.GetAttachmentIndex());
                        vkReference.layout(reference.GetLayout());
                    }

                    subpass.colorAttachmentCount(quorumColorReferences.GetSize());
                    subpass.pColorAttachments(colorReferences);
                }


                Array_ quorumInputReferences = quorumSubpass.GetInputAttachments();
                if (quorumInputReferences != null && !quorumInputReferences.IsEmpty())
                {
                    VkAttachmentReference.Buffer inputReferences = VkAttachmentReference.calloc(quorumInputReferences.GetSize(), stack);
                    for (int j = 0; j < quorumInputReferences.GetSize(); j++)
                    {
                        VulkanAttachmentReference_ reference = (VulkanAttachmentReference_)quorumInputReferences.Get(j);
                        VkAttachmentReference vkReference = inputReferences.get(j);

                        vkReference.attachment(reference.GetAttachmentIndex());
                        vkReference.layout(reference.GetLayout());
                    }

                    // For some reason, unlike the color attachments, there's a getter but no setter for this one.
                    //subpass.inputAttachmentCount(quorumInputReferences.GetSize());
                    subpass.pInputAttachments(inputReferences);
                }


                Array_ quorumResolveReferences = quorumSubpass.GetResolveAttachments();
                if (quorumResolveReferences != null && !quorumResolveReferences.IsEmpty())
                {
                    VkAttachmentReference.Buffer resolveReferences = VkAttachmentReference.calloc(quorumResolveReferences.GetSize(), stack);
                    for (int j = 0; j < quorumResolveReferences.GetSize(); j++)
                    {
                        VulkanAttachmentReference_ reference = (VulkanAttachmentReference_)quorumResolveReferences.Get(j);
                        VkAttachmentReference vkReference = resolveReferences.get(j);

                        vkReference.attachment(reference.GetAttachmentIndex());
                        vkReference.layout(reference.GetLayout());
                    }

                    //subpass.resolveAttachmentCount(quorumResolveReferences.GetSize());
                    subpass.pResolveAttachments(resolveReferences);
                }


                Array_ quorumPreserveIndices = quorumSubpass.GetPreserveAttachmentIndices();
                if (quorumPreserveIndices != null && !quorumPreserveIndices.IsEmpty())
                {
                    IntBuffer preserveIndices = stack.callocInt(quorumPreserveIndices.GetSize());
                    for (int j = 0; j < quorumPreserveIndices.GetSize(); j++)
                    {
                        Integer_ value = (Integer_)quorumPreserveIndices.Get(j);
                        int index = value.GetValue();
                        preserveIndices.put(j, index);
                    }

                    subpass.pPreserveAttachments(preserveIndices);
                }


                VulkanAttachmentReference_ quorumDepthStencilReference = quorumSubpass.GetDepthStencilAttachment();
                if (quorumDepthStencilReference != null)
                {
                    VkAttachmentReference stencilReference = VkAttachmentReference.calloc(stack);
                    stencilReference.attachment(quorumDepthStencilReference.GetAttachmentIndex());
                    stencilReference.layout(quorumDepthStencilReference.GetLayout());

                    subpass.pDepthStencilAttachment(stencilReference);
                }
            }

            VkSubpassDependency.Buffer subpassDependencies = VkSubpassDependency.calloc(quorumDependencies.GetSize(), stack);
            for (int i = 0; i < quorumDependencies.GetSize(); i++)
            {
                VkSubpassDependency subpassDependency = subpassDependencies.get(i);
                VulkanSubpassDependency_ quorumDependency = (VulkanSubpassDependency_)quorumDependencies.Get(i);

                subpassDependency.srcSubpass(quorumDependency.GetSourceSubpass());
                subpassDependency.dstSubpass(quorumDependency.GetDestinationSubpass());
                subpassDependency.srcStageMask(quorumDependency.GetSourceStageMask());
                subpassDependency.dstStageMask(quorumDependency.GetDestinationStageMask());
                subpassDependency.srcAccessMask(quorumDependency.GetSourceAccessMask());
                subpassDependency.dstAccessMask(quorumDependency.GetDestinationAccessMask());
                subpassDependency.dependencyFlags(quorumDependency.GetDependencyFlags());
            }


            // Finally, create the render pass.
            VkRenderPassCreateInfo createInfo = VkRenderPassCreateInfo.calloc(stack);
            createInfo.sType$Default();
            createInfo.pAttachments(attachmentDescriptions);
            createInfo.pSubpasses(subpasses);
            createInfo.pDependencies(subpassDependencies);

            LongBuffer handleBuffer = stack.mallocLong(1);
            int vulkanResult = vkCreateRenderPass(pluginDevice.GetDevice(), createInfo, null, handleBuffer);
            if (vulkanResult != VK_SUCCESS)
                return false;

            vulkanRenderPassHandle = handleBuffer.get(0);
        }

        return true;
    }

    public long GetRenderPassHandle()
    {
        return vulkanRenderPassHandle;
    }
}
