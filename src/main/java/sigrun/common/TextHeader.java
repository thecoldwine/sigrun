package sigrun.common;

import java.util.ArrayList;
import java.util.List;

public class TextHeader {
    public static final int PUNCH_CARD_AMOUNT = 40;
    public static final int TEXT_HEADER_LENGTH = PUNCH_CARD_AMOUNT * PunchCard.PUNCH_CARD_LENGTH;
    protected final List<PunchCard> content;

    public TextHeader() {
        content = new ArrayList<PunchCard>(TEXT_HEADER_LENGTH);
    }

    public void addPunchCard(PunchCard punchCard) {
        if (content.size() < TEXT_HEADER_LENGTH)
            content.add(punchCard);
        else
            throw new ArrayIndexOutOfBoundsException("Sorry, maximum capacity has been reached.");
    }

    public PunchCard getCard(Integer number) {
        return content.get(number);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (PunchCard e : content) {
            result.append(e);
        }

        return result.toString();
    }

    public Integer getSize() {
        return content.size();
    }
}
