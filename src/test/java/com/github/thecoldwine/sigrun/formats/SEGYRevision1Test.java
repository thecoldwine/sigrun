package com.github.thecoldwine.sigrun.formats;

import com.github.thecoldwine.sigrun.serialization.BinaryHeaderFormat;
import com.github.thecoldwine.sigrun.serialization.TraceHeaderFormat;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aksen on 2017-03-30.
 */
public class SEGYRevision1Test {
    @Test
    public void testBinaryHeaderBuilding() {
        SEGYRevision rev1 = new SEGYRevision1();
        BinaryHeaderFormat fmt =  rev1.binaryHeaderFormat();

        assertEquals(4, fmt.lineNumberFormat.posStart);
        assertEquals(8, fmt.lineNumberFormat.posEnd);

        assertEquals(302, fmt.fixedLengthTraceFlagFormat.posStart);
        assertEquals(304, fmt.fixedLengthTraceFlagFormat.posEnd);
    }

    @Test
    public void testTraceHeaderBuilding() {
        SEGYRevision rev1 = new SEGYRevision1();
        TraceHeaderFormat fmt =  rev1.traceHeaderFormat();

        assertEquals(8, fmt.originalFieldRecordNumberFormat.posStart);
        assertEquals(12, fmt.originalFieldRecordNumberFormat.posEnd);

        assertEquals(108, fmt.delayRecordingTimeFormat.posStart);
        assertEquals(110, fmt.delayRecordingTimeFormat.posEnd);
    }
}