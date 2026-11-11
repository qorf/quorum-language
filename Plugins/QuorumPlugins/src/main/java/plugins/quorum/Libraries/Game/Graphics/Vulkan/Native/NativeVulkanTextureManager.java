package plugins.quorum.Libraries.Game.Graphics.Vulkan.Native;

import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.VulkanTextureManager;
import plugins.quorum.Libraries.Game.Graphics.Vulkan.Strategies.VulkanTextureManagerStrategy;
import plugins.quorum.Libraries.Game.Graphics.PixelMap;
import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

// Copies pixels into mapped GPU memory on iOS and Android: the desktop's code,
// with Java's own buffers instead of LWJGL's.
public class NativeVulkanTextureManager implements VulkanTextureManagerStrategy
{
    private final VulkanTextureManager peer;

    public NativeVulkanTextureManager(VulkanTextureManager peer)
    {
        this.peer = peer;
    }

    public void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        // A duplicate from the start, so the PixelMap's own position never
        // moves and the same pixels can be copied again (Item 34).
        ByteBuffer pixelBytes = pluginMap.GetPixels().duplicate();
        pixelBytes.position(0);

        ByteBuffer target = NativeVulkan.Wrap(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
        target.put(pixelBytes);
    }
    /*
    Writes the full size and each smaller level. With a cutout above 0 (a
    cut-out picture, such as a grass card, Dense grass plan DG5), each
    smaller level's alpha is scaled so the same share of its pixels passes
    the cutout as in the full size: plain averaging mixes thin blades with
    the clear space around them and drops them below the cutout, so far
    grass thins out and flickers. The levels are still made from each
    other unscaled; only what is written is scaled.
    */
    public void TransferPixelDataWithMipMaps(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer, int levels, boolean packedNormals, double cutout)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        ByteBuffer pixelBytes = pluginMap.GetPixels();
        ByteBuffer target = NativeVulkan.Wrap(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());

        int width = quorumMap.GetWidth();
        int height = quorumMap.GetHeight();

        // The full size, read by index so the pixel buffer's position
        // doesn't matter.
        byte[] level = new byte[width * height * 4];
        for (int i = 0; i < level.length; i++)
            level[i] = pixelBytes.get(i);

        double threshold = cutout * 255.0;
        double fullCoverage = cutout > 0 ? Coverage(level, threshold, 1.0) : 0;

        int offset = 0;
        for (int index = 0; index < levels; index++)
        {
            target.position(offset);
            if (cutout > 0 && index > 0)
                target.put(KeepCoverage(level, threshold, fullCoverage));
            else
                target.put(level);
            offset += level.length;

            if (index + 1 < levels)
            {
                int smallerWidth = Math.max(1, width / 2);
                int smallerHeight = Math.max(1, height / 2);
                level = MakeSmallerLevel(level, width, height, smallerWidth, smallerHeight, packedNormals);
                width = smallerWidth;
                height = smallerHeight;
            }
        }
        target.position(0);
    }

    // Averages each 2 by 2 block of pixels into one. At an odd edge, the
    // last row or column is used twice.
    private static byte[] MakeSmallerLevel(byte[] source, int width, int height, int smallerWidth, int smallerHeight, boolean packedNormals)
    {
        byte[] result = new byte[smallerWidth * smallerHeight * 4];
        int[] corners = new int[4];
        for (int y = 0; y < smallerHeight; y++)
        {
            int y0 = Math.min(y * 2, height - 1);
            int y1 = Math.min(y * 2 + 1, height - 1);
            for (int x = 0; x < smallerWidth; x++)
            {
                int x0 = Math.min(x * 2, width - 1);
                int x1 = Math.min(x * 2 + 1, width - 1);
                corners[0] = (y0 * width + x0) * 4;
                corners[1] = (y0 * width + x1) * 4;
                corners[2] = (y1 * width + x0) * 4;
                corners[3] = (y1 * width + x1) * 4;
                int out = (y * smallerWidth + x) * 4;

                if (!packedNormals)
                {
                    for (int channel = 0; channel < 4; channel++)
                    {
                        int sum = 0;
                        for (int corner : corners)
                            sum += source[corner + channel] & 0xFF;
                        result[out + channel] = (byte)((sum + 2) / 4);
                    }
                    continue;
                }

                // Packed normals: average the directions and the lengths
                // (ambient occlusion) apart, and roughness plainly.
                double directionX = 0, directionY = 0, directionZ = 0, lengthSum = 0;
                int alphaSum = 0;
                for (int corner : corners)
                {
                    double normalX = (source[corner] & 0xFF) / 127.5 - 1.0;
                    double normalY = (source[corner + 1] & 0xFF) / 127.5 - 1.0;
                    double normalZ = (source[corner + 2] & 0xFF) / 127.5 - 1.0;
                    double length = Math.sqrt(normalX * normalX + normalY * normalY + normalZ * normalZ);
                    lengthSum += length;
                    if (length > 0.000001)
                    {
                        directionX += normalX / length;
                        directionY += normalY / length;
                        directionZ += normalZ / length;
                    }
                    alphaSum += source[corner + 3] & 0xFF;
                }
                double directionLength = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                if (directionLength < 0.000001)
                {
                    directionX = 0;
                    directionY = 0;
                    directionZ = 1;
                    directionLength = 1;
                }
                double scale = (lengthSum / 4.0) / directionLength;
                result[out] = ToByte((directionX * scale + 1.0) * 127.5);
                result[out + 1] = ToByte((directionY * scale + 1.0) * 127.5);
                result[out + 2] = ToByte((directionZ * scale + 1.0) * 127.5);
                result[out + 3] = (byte)((alphaSum + 2) / 4);
            }
        }
        return result;
    }

    // The share of a level's pixels whose alpha, times a scale, reaches the
    // threshold (Dense grass plan DG5).
    private static double Coverage(byte[] level, double threshold, double scale)
    {
        int pixels = level.length / 4;
        if (pixels == 0)
            return 0;
        int passing = 0;
        for (int i = 3; i < level.length; i += 4)
        {
            if ((level[i] & 0xFF) * scale >= threshold)
                passing++;
        }
        return passing / (double)pixels;
    }

    // A copy of a level with its alpha scaled so its coverage is about the
    // full size's: the smallest scale that reaches it, found by halving
    // (Castano's coverage-preserving mipmaps). Colors are unchanged.
    private static byte[] KeepCoverage(byte[] level, double threshold, double fullCoverage)
    {
        double low = 0.0;
        double high = 16.0;
        if (Coverage(level, threshold, high) < fullCoverage)
            low = high;
        else
        {
            for (int step = 0; step < 12; step++)
            {
                double middle = (low + high) / 2.0;
                if (Coverage(level, threshold, middle) < fullCoverage)
                    low = middle;
                else
                    high = middle;
            }
        }
        double scale = high;
        byte[] result = level.clone();
        for (int i = 3; i < result.length; i += 4)
            result[i] = ToByte((level[i] & 0xFF) * scale);
        return result;
    }

    private static byte ToByte(double value)
    {
        long rounded = Math.round(value);
        if (rounded < 0)
            rounded = 0;
        if (rounded > 255)
            rounded = 255;
        return (byte)rounded;
    }
}
