package sigrun.converters;

/**
 * Created by maksenov on 16/01/15.
 */
public interface SeismicValuesConverter {
    public static final int BYTE_SIZE = 8;
    public static final int SHORT_SIZE = Short.SIZE / BYTE_SIZE;
    public static final int INT_SIZE = Integer.SIZE / BYTE_SIZE;
    public static final int FLOAT_SIZE = Float.SIZE / BYTE_SIZE;


    float[] convert(byte[] bytes);
}
