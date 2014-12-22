package sigrun.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public final class ByteANumberConverter {
    private static final Logger log = LoggerFactory.getLogger(ByteANumberConverter.class.getName());
    private static final int BYTE = 0x000000FF;
    private static final int LENGTH_32 = 4;

    /**
     * Converts a range of byte array to unsigned short (Integer in Java).
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading of value
     * @return unsigned value in wider integral type.
     * @throws IndexOutOfBoundsException if offset + Short.SIZE greater than
     *                                   <b>source.length</b> value.
     * @throws NullPointerException      if source array is null
     * @throws IllegalArgumentException  if offset less than zero
     */
    public static int byteAToUnsignedShort(byte[] source, int offset) {
        if (source == null)
            throw new NullPointerException("Source cannot be null");

        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be less than zero");

        final long size = source.length;
        if ((offset + 1) > size)
            throw new IndexOutOfBoundsException("Can't get short number from this array with this offset.");

        int firstByte = BYTE & source[offset];
        int secondByte = BYTE & source[offset + 1];

        return firstByte << 8 | secondByte;
    }

    /**
     * Converts a range of byte array to unsigned int (Long in Java).
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading of value
     * @return unsigned value in wider integral type
     * @throws IndexOutOfBoundsException if offset + Integer.SIZE greater than
     *                                   <b>source.length</b> value.
     * @throws NullPointerException      if source array is null
     * @throws IllegalArgumentException  if offset less than zero
     */
    public static long byteAToUnsignedInt(byte[] source, int offset) {
        if (source == null)
            throw new NullPointerException("Source cannot be null");

        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be less than zero");

        final long size = source.length;
        if ((offset + 3) >= size)
            throw new IndexOutOfBoundsException("Can't get short number from this array with this offset.");

        int firstByte = BYTE & source[offset];
        int secondByte = BYTE & source[offset + 1];
        int thirdByte = BYTE & source[offset + 2];
        int fourthByte = BYTE & source[offset + 3];

        return ((long) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte) & 0xFFFFFFFFL);
    }

    /**
     * Converts a range of byte array to signed short (Short in Java)
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading the value
     * @return unsigned value in wider integral type
     * @throws IndexOutOfBoundsException if offset + Short.SIZE greater than
     *                                   <b>source.length</b> value.
     * @throws NullPointerException      if source array is null
     * @throws IllegalArgumentException  if offset less than zero
     */
    public static short byteAToShort(byte[] source, int offset) {
        if (source == null)
            throw new NullPointerException("Source cannot be null");

        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be less than zero");

        final long size = source.length;
        if ((offset + 1) >= size)
            throw new IndexOutOfBoundsException("Can't get short number from this array with this offset");

        int firstByte = BYTE & source[offset];
        int secondByte = BYTE & source[offset + 1];

        return (short) (firstByte << 8 | secondByte);
    }


    /**
     * Converts a range of byte array to signed int (Integer in Java)
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading of value
     * @return unsigned value in wider integral type
     * @throws IndexOutOfBoundsException if offset + Integer.SIZE greater than
     *                                   <b>source.length</b> value.
     * @throws NullPointerException      if source array is null
     * @throws IllegalArgumentException  if offset less than zero
     */
    public static int byteAToInt(byte[] source, int offset) {
        if (source == null)
            throw new NullPointerException("Source cannot be null");

        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be less than zero");

        final long size = source.length;
        if ((offset + 3) >= size)
            throw new IndexOutOfBoundsException("Can't get int number from this array with this offset");

        int firstByte = BYTE & source[offset];
        int secondByte = BYTE & source[offset + 1];
        int thirdByte = BYTE & source[offset + 2];
        int fourthByte = BYTE & source[offset + 3];

        return (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte);
    }

    public static float byteAToFloatIEEE754(byte[] buffer, int offset) {
        if (buffer.length < (offset + LENGTH_32)) {
            throw new IndexOutOfBoundsException();
        }

        byte[] number = Arrays.copyOfRange(buffer, offset, LENGTH_32);
        int b1 = BYTE & number[offset];
        int b2 = BYTE & number[offset + 1];
        int b3 = BYTE & number[offset + 2];
        int b4 = BYTE & number[offset + 3];

        return (float) (b1 << 24 | b2 << 16 | b3 << 8 | b4);
    }
}