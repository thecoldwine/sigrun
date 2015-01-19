package sigrun.converters;

import sigrun.converters.SeismicValuesConverter;

import java.nio.ByteBuffer;

/**
 * Created by maksenov on 17/01/15.
 */
public class IEEEConverter implements SeismicValuesConverter {
    @Override
    public float[] convert(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("Bytes array cannot be null");
        }

        if ((bytes.length % Float.BYTES) != 0) {
            throw new IllegalArgumentException("Byte array has wrong length");
        }

        return ByteBuffer.wrap(bytes).asFloatBuffer().array();
    }
}
