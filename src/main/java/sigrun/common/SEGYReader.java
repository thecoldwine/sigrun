package sigrun.common;

import sigrun.serialization.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class SEGYReader {
    private final TextHeaderReader textHeaderReader;
    private final BinaryHeaderReader binaryHeaderReader;
    private final TraceHeaderReader traceHeaderReader;

    public SEGYReader(Charset cs, BinaryHeaderFormat bhf, TraceHeaderFormat thf) {
        this.textHeaderReader = new TextHeaderReader(cs);
        this.binaryHeaderReader = new BinaryHeaderReader(bhf);
        this.traceHeaderReader = new TraceHeaderReader(thf);
    }

    public TextHeader readTextHeader(BufferedInputStream is) throws SEGYFormatException, IOException {
        final byte[] buffer = new byte[TextHeader.TEXT_HEADER_SIZE];

        if (is.read(buffer, 0, TextHeader.TEXT_HEADER_SIZE) < 0) {
            throw new SEGYFormatException("Cannot read text header, unexpected EOF.");
        }

        return textHeaderReader.read(buffer);
    }

    public BinaryHeader readBinaryHeader(BufferedInputStream is) throws SEGYFormatException, IOException {
        final byte[] buffer = new byte[BinaryHeader.BIN_HEADER_LENGTH];

        if (is.read(buffer, TextHeader.TEXT_HEADER_SIZE, BinaryHeader.BIN_HEADER_LENGTH) < 0) {
            throw new SEGYFormatException("Cannot read binary header, unexpected EOF.");
        }

        return binaryHeaderReader.read(buffer);
    }

    public SEGYModel read(BufferedInputStream is) throws SEGYFormatException, IOException {
        final SEGYModel segy = new SEGYModel();

        segy.setTextHeader(readTextHeader(is));

        final BinaryHeader binHeader = readBinaryHeader(is);
        segy.setBinaryHeader(binHeader);

        int offset = TextHeader.TEXT_HEADER_SIZE + BinaryHeader.BIN_HEADER_LENGTH;
        final int sampleSize = binHeader.getDataSampleCode().getSize();
        long read;

        while (true) {
            byte[] buffer = new byte[TraceHeader.TRACE_HEADER_LENGTH];
            if (is.read(buffer, offset, TraceHeader.TRACE_HEADER_LENGTH) < 0) {
                break;
            }

            final TraceHeader header = traceHeaderReader.read(buffer);
            segy.addTraceHeader(header);

            final int skipLength = sampleSize * header.getNumberOfSamples();
            read = is.skip(skipLength);

            if (read < 0) {
                throw new SEGYFormatException("Incorrect information in file, unexpected EOF");
            }

            offset += TraceHeader.TRACE_HEADER_LENGTH + read;
        }

        return segy;
    }
}

