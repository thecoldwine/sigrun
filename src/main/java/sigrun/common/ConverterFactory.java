package sigrun.common;

import sigrun.converters.IBM360Converter;
import sigrun.converters.SeismicValuesConverter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by maksenov on 16/01/15.
 */
public class ConverterFactory {
    public static SeismicValuesConverter getConverter(DataSample sample) {
        switch (sample) {
            case IBM_FP:
                return new IBM360Converter();
            case IEEE_FP:
                return new IEEEConverter();
            default:
                throw new NotImplementedException();
        }
    }
}
