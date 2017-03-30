package com.github.thecoldwine.sigrun.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by aksen on 2017-03-30.
 */
public class UtilitiesTest {
    @Test
    public void createFormatFieldName() throws Exception {
        String samplesPerDataTrace = "samplesPerDataTrace";
        Assert.assertEquals("withSamplesPerDataTraceFormat", Utilities.makeFormatMethodName(samplesPerDataTrace));
    }

}