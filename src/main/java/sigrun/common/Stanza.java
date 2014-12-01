package sigrun.common;

public class Stanza {
    public static final Stanza END_STANZA = new Stanza("((SEG: EndText))");

    protected final String stanzaHeader;

    public Stanza(String stanzaHeader) {
        this.stanzaHeader = stanzaHeader;
    }
}