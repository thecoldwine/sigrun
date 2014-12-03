package sigrun.converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public final class ByteANumberConverter {
    private static final Logger log = LogManager.getLogger(ByteANumberConverter.class.getName());
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
     * Converts a range of bytes to signed short (Short in Java) with the changing of index values (for length of Short
     * type in bytes).
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading the value
     * @param index  - pointer to by steps next after number bytes;
     *               This one computes as <code>offset + Short.SIZE / 8</code>
     * @return short value
     * @throws IndexOutOfBoundsException if offset + Short.SIZE greater than <b>source.length</b> value.
     * @throws NullPointerException      if source array is null or index value is null.
     * @throws IllegalArgumentException  if offset less than zero or index less than zero
     *                                   <p/>
     *                                   <b>Note: there are no exception if index will be greater than source length!</b>
     */
    @SuppressWarnings("UnusedAssignment")
    public static short byteAToShort(final byte[] source, final int offset, Integer index) {
        if (index == null)
            throw new NullPointerException("Index cannot be null");

        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than zero");

        short result = byteAToShort(source, offset);
        index += Short.SIZE / 8 + offset;

        return result;
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

    /**
     * Convert a range of byte array to signed int (Integer in Java)
     *
     * @param source - source array of bytes
     * @param offset - offset for start of reading of value
     * @param index  - pointer to byte steps next after number bytes;
     *               This one computes as <code>offset + Integer.SIZE / 8</code>
     * @return int value
     * @throws IndexOutOfBoundsException if offset + Integer.SIZE greater than
     *                                   <b>source.length</b> value.
     * @throws NullPointerException      if source array is null or index is null
     * @throws IllegalArgumentException  if offset less than zero or index less than zero
     */
    @SuppressWarnings("UnusedAssignment")
    public static int byteAToInt(final byte[] source, final int offset, Integer index) {
        if (index == null)
            throw new NullPointerException("Index cannot be null");

        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than zero");

        int result = byteAToInt(source, offset);
        index += Integer.SIZE / 8 + offset;

        return result;
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