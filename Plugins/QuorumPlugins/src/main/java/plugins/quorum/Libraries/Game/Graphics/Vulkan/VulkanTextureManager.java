package plugins.quorum.Libraries.Game.Graphics.Vulkan;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import plugins.quorum.Libraries.Game.Graphics.PixelMap;
import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer_;

import java.nio.ByteBuffer;

public class VulkanTextureManager
{
    public Object me_;

    public void TransferPixelData(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        // A duplicate from the start, so the PixelMap's own position never
        // moves and the same pixels can be copied again (Item 34).
        ByteBuffer pixelBytes = pluginMap.GetPixels().duplicate();
        pixelBytes.position(0);

        ByteBuffer target = MemoryUtil.memByteBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());
        target.put(pixelBytes);
    }
    public void TransferPixelDataWithMipMaps(PixelMap_ quorumMap, VulkanBuffer_ quorumBuffer, int levels, boolean packedNormals)
    {
        PixelMap pluginMap = ((quorum.Libraries.Game.Graphics.PixelMap)quorumMap).plugin_;
        VulkanBuffer pluginBuffer = ((quorum.Libraries.Game.Graphics.Vulkan.VulkanBuffer)quorumBuffer).plugin_;
        ByteBuffer pixelBytes = pluginMap.GetPixels();
        ByteBuffer target = MemoryUtil.memByteBuffer(pluginBuffer.GetMappedMemoryPointer(), quorumBuffer.GetSize());

        int width = quorumMap.GetWidth();
        int height = quorumMap.GetHeight();

        // The full size, read by index so the pixel buffer's position
        // doesn't matter.
        byte[] level = new byte[width * height * 4];
        for (int i = 0; i < level.length; i++)
            level[i] = pixelBytes.get(i);

        int offset = 0;
        for (int index = 0; index < levels; index++)
        {
            target.position(offset);
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
