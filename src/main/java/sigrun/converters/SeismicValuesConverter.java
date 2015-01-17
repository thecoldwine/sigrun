package sigrun.converters;

/**
 * Created by maksenov on 16/01/15.
 */
public interface SeismicValuesConverter {
    float[] convert(byte[] bytes);
}
