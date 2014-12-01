package sigrun.serialization;

import sigrun.common.PunchCard;
import sigrun.common.TextHeader;

import java.util.Arrays;

import static sigrun.common.PunchCard.PUNCH_CARD_LENGTH;

public class TextHeaderSerializer extends Serializer<TextHeader> {
    @Override
    public byte[] serialize(TextHeader header) {
        byte[] result = new byte[TextHeader.TEXT_HEADER_LENGTH];
        for (int i = 0; i < TextHeader.PUNCH_CARD_AMOUNT; i++)
            System.arraycopy(header.getCard(i).rawContent(), 0, result,
                    i * PUNCH_CARD_LENGTH, PUNCH_CARD_LENGTH);

        return result;
    }

    @Override
    public TextHeader deserialize(byte[] bytes) {
        TextHeader header = new TextHeader();
        for (int i = 0; i < TextHeader.PUNCH_CARD_AMOUNT; i++) {
            header.addPunchCard(new PunchCard(Arrays.copyOfRange(bytes, i * PUNCH_CARD_LENGTH, (i + 1) * PUNCH_CARD_LENGTH)));
        }

        return header;
    }

    @Override
    public int lengthOfSerializableObject() {
        return TextHeader.TEXT_HEADER_LENGTH;
    }
}
