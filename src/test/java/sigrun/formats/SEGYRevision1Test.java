package sigrun.formats;

import org.junit.Assert;
import org.junit.Test;
import sigrun.serialization.BinaryHeaderFormat;
import sigrun.serialization.TraceHeaderFormat;

import static org.junit.Assert.*;

/**
 * Created by aksen on 2017-03-30.
 */
public class SEGYRevision1Test {
    @Test
    public void testBinaryHeaderBuilding() {
        SEGYRevision rev1 = new SEGYRevision1();
        BinaryHeaderFormat fmt =  rev1.binaryHeaderFormat();

        Assert.assertEquals(4, fmt.lineNumberFormat.posStart);
        Assert.assertEquals(8, fmt.lineNumberFormat.posEnd);

        Assert.assertEquals(302, fmt.fixedLengthTraceFlagFormat.posStart);
        Assert.assertEquals(304, fmt.fixedLengthTraceFlagFormat.posEnd);
    }

    @Test
    public void testTraceHeaderBuilding() {
        SEGYRevision rev1 = new SEGYRevision1();
        TraceHeaderFormat fmt =  rev1.traceHeaderFormat();

        Assert.assertEquals(8, fmt.originalFieldRecordNumberFormat.posStart);
        Assert.assertEquals(12, fmt.originalFieldRecordNumberFormat.posEnd);

        Assert.assertEquals(108, fmt.delayRecordingTimeFormat.posStart);
        Assert.assertEquals(110, fmt.delayRecordingTimeFormat.posEnd);
    }
}