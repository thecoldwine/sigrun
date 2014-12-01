package sigrun.serialization;

import sigrun.converters.ByteANumberConverter;

import static sigrun.converters.NumberByteAConverter.writeIntToByteA;
import static sigrun.converters.NumberByteAConverter.writeShortToByteA;

public abstract class Serializer<T> {
    private int internalOffset;
    public static final int INT_SIZE = Integer.SIZE / 8;
    public static final int SHORT_SIZE = Short.SIZE / 8;

    protected final int getInternalOffset() {
        return internalOffset;
    }

    protected final void setInternalOffset(int internalOffset) {
        this.internalOffset = internalOffset;
    }

    protected final void writeInt(Integer source, byte[] destination) {
        if (source == null)
            source = 0;

        writeIntToByteA(source, destination, internalOffset);
        internalOffset += INT_SIZE;
    }

    protected final void writeShort(Short source, byte[] destination) {
        if (source == null)
            source = 0;

        writeShortToByteA(source, destination, internalOffset);
        internalOffset += SHORT_SIZE;
    }

    protected final void writeArray(byte[] source, byte[] destination) {
        System.arraycopy(source, 0, destination, internalOffset, source.length);

        internalOffset += source.length;
    }

    protected final int byteAToInt(final byte[] source) {
        int result = ByteANumberConverter.byteAToInt(source, internalOffset);
        internalOffset += INT_SIZE;

        return result;
    }

    protected final short byteAToShort(final byte[] source) {
        short result = ByteANumberConverter.byteAToShort(source, internalOffset);
        internalOffset += SHORT_SIZE;

        return result;
    }

    public abstract byte[] serialize(T object);

    public abstract T deserialize(byte[] bytes);

    public abstract int lengthOfSerializableObject();
}