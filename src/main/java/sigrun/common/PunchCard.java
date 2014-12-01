package sigrun.common;

import java.io.UnsupportedEncodingException;

public class PunchCard {
    public static final String EBCDIC_ENCODING_CP = "Cp1047";
    private static final int HEADER_LENGTH = 3;
    public static final int PUNCH_CARD_LENGTH = 80;

    private final byte[] content;

    public PunchCard(byte[] content) {
        this.content = content;
    }

    public String getCardNumber() {
        try {
            return new String(content, 0, HEADER_LENGTH, EBCDIC_ENCODING_CP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("EBCDIC Encoding is not supported. Can't process the punch card");
        }
    }

    public String getCardContent() {
        try {
            return new String(content, HEADER_LENGTH, PUNCH_CARD_LENGTH - HEADER_LENGTH, EBCDIC_ENCODING_CP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("EBCDIC Encoding is not supported. Can't process the punch card");
        }
    }

    @Override
    public String toString() {
        try {
            return new String(content, 0, PUNCH_CARD_LENGTH, EBCDIC_ENCODING_CP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "Invalid card";
    }

    public byte[] rawContent() {
        return content.clone();
    }
}