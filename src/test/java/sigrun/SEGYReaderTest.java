package sigrun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sigrun.common.SEGYModel;
import sigrun.serialization.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

@SuppressWarnings("WeakerAccess")
public class SEGYReaderTest {
    private static final Logger log = LoggerFactory.getLogger(SEGYReaderTest.class.getName());

    public static BinaryHeaderFormat makeBinHeaderFormat() {
        return BinaryHeaderFormatBuilder.aBinaryHeaderFormat()
                .withLineNumberFormat(FormatEntry.create(4, 8))
                .withSampleIntervalFormat(FormatEntry.create(16, 18))
                .withSamplesPerDataTraceFormat(FormatEntry.create(20, 22))
                .withDataSampleCodeFormat(FormatEntry.create(24, 26))
                .withSegyFormatRevNumberFormat(FormatEntry.create(300, 302))
                .withFixedLengthTraceFlagFormat(FormatEntry.create(302, 304))
                .withNumberOf3200ByteFormat(FormatEntry.create(304, 306))
                .build();
    }

    public static TraceHeaderFormat makeTraceHeaderFormat() {
        return TraceHeaderFormatBuilder.aTraceHeaderFormat().
                withEnsembleNumberFormat(FormatEntry.create(20, 24)).
                withSourceXFormat(FormatEntry.create(72, 76)).
                withSourceYFormat(FormatEntry.create(76, 80)).
                withXOfCDPPositionFormat(FormatEntry.create(180, 184)).
                withYOfCDPPositionFormat(FormatEntry.create(184, 188)).
                withNumberOfSamplesFormat(FormatEntry.create(114, 116)).
                build();
    }

    public static void main(String[] args) {
        final String filePath = args[0];
        BufferedInputStream is = null;
        try {
            final long startTime = System.currentTimeMillis();

            is = new BufferedInputStream(new FileInputStream(filePath));
            final BinaryHeaderFormat binHeaderFmt = makeBinHeaderFormat();
            final TraceHeaderFormat traceHeaderFmt = makeTraceHeaderFormat();
            final SEGYReader rdr = new SEGYReader(Charset.forName("Cp1047"), binHeaderFmt, traceHeaderFmt);
            SEGYModel segyModel = rdr.read(is);

            final long endTime = System.currentTimeMillis();

            log.info(String.format("Processing took %d milliseconds", endTime - startTime));

            log.info(String.format("Trace header count: %d", segyModel.getTraceHeaders().size()));
        } catch (FileNotFoundException e) {
            log.info(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            log.info(e.getMessage());

            System.exit(1);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.exit(0);
    }
}
