package sigrun.common;

import java.util.ArrayList;
import java.util.List;

public class SEGYData {
    private List<TextHeader> textHeaders = new ArrayList<TextHeader>();
    private BinaryHeader binaryHeader;
    private List<Trace> traces = new ArrayList<Trace>();

    public List<TextHeader> getTextHeaders() {
        return textHeaders;
    }

    public void setTextHeaders(List<TextHeader> textHeaders) {
        this.textHeaders = textHeaders;
    }

    public BinaryHeader getBinaryHeader() {
        return binaryHeader;
    }

    public void setBinaryHeader(BinaryHeader binaryHeader) {
        this.binaryHeader = binaryHeader;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }
}
