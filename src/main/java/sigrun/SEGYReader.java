package sigrun;

import org.apache.logging.log4j.LogManager;
import sigrun.common.BinaryHeader;
import sigrun.common.SEGYModel;
import sigrun.common.TextHeader;
import sigrun.common.TraceHeader;
import sigrun.serialization.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("WeakerAccess")
public class SEGYReader {
    private final static Logger log = LogManager.getLogger(SEGYReader.class.getName());

    private final TextHeaderReader textHeaderReader;
    private final BinaryHeaderReader binaryHeaderReader;
    private final TraceHeaderReader traceHeaderReader;

    public SEGYReader(Charset cs, BinaryHeaderFormat bhf, TraceHeaderFormat thf) {
        this.textHeaderReader = new TextHeaderReader(cs);
        this.binaryHeaderReader = new BinaryHeaderReader(bhf);
        this.traceHeaderReader = new TraceHeaderReader(thf);
    }

    public TextHeader readTextHeader(BufferedInputStream is) throws SEGYFormatException, IOException {
        byte[] buffer = new byte[TextHeader.TEXT_HEADER_SIZE];

        if (is.read(buffer, 0, TextHeader.TEXT_HEADER_SIZE) < 0) {
            throw new SEGYFormatException("Cannot read text header, unexpected EOF.");
        }

        return textHeaderReader.read(buffer);
    }

    public BinaryHeader readBinaryHeader(BufferedInputStream is) throws SEGYFormatException, IOException {
        byte[] buffer = new byte[BinaryHeader.BIN_HEADER_LENGTH];

        if (is.read(buffer, 0, BinaryHeader.BIN_HEADER_LENGTH) < 0) {
            throw new SEGYFormatException("Cannot read binary header, unexpected EOF.");
        }

        return binaryHeaderReader.read(buffer);
    }

    public SEGYModel read(BufferedInputStream is) throws SEGYFormatException, IOException {
        final SEGYModel segy = new SEGYModel();

        segy.setTextHeader(readTextHeader(is));
        log.info("Text header has been read successfully");

        final BinaryHeader binHeader = readBinaryHeader(is);
        segy.setBinaryHeader(binHeader);
        log.info("Binary header has been read successfully");

        final int sampleSize = binHeader.getDataSampleCode().getSize();
        log.info(String.format("Sample size is %d", sampleSize));

        while (true) {
            byte[] buffer = new byte[TraceHeader.TRACE_HEADER_LENGTH];
            if (is.read(buffer, 0, TraceHeader.TRACE_HEADER_LENGTH) < 0) {
                break;
            }

            final TraceHeader header = traceHeaderReader.read(buffer);
            segy.addTraceHeader(header);
            final int skipLength = sampleSize * header.getNumberOfSamples();

            if (is.read(new byte[skipLength]) < 0) {
                throw new SEGYFormatException("Incorrect information in file, unexpected EOF");
            }

            log.trace(String.format("Trace header has been read. (%d, %d)", header.getSourceX(), header.getSourceY()));
        }

        log.trace("File ended");

        return segy;
    }
}

