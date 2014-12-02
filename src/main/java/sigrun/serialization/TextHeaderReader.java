package sigrun.serialization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sigrun.common.TextHeader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TextHeaderReader {
    private final static Logger log = LogManager.getLogger(TextHeaderReader.class.getName());

    public final Charset charset;

    public TextHeaderReader(Charset charset) {
        this.charset = charset;
    }

    public TextHeader read(byte[] buffer) {
        assert buffer.length == TextHeader.TEXT_HEADER_SIZE;

        final List<String> records = new ArrayList<String>(TextHeader.RECORDS_AMOUNT);

        for (int i = 0; i < TextHeader.RECORDS_AMOUNT; i++) {
            final int offset = i * TextHeader.STRING_LENGTH;
            final String record = new String(buffer, offset, TextHeader.STRING_LENGTH, charset);
            records.add(record);
        }

        return new TextHeader(records);
    }
}
