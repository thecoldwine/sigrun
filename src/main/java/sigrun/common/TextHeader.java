package sigrun.common;

import java.util.List;

public class TextHeader {
    public static final int STRING_LENGTH = 80;
    public static final int RECORDS_AMOUNT = 40;
    public static final int TEXT_HEADER_SIZE = STRING_LENGTH * RECORDS_AMOUNT;

    private final List<String> contents;

    public List<String> getContents() {
        return contents;
    }

    public TextHeader(final List<String> contents) {
        this.contents = contents;
        assert contents.size() == RECORDS_AMOUNT;
    }
}
