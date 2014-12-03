package sigrun.common;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class SEGYModel {
    private TextHeader textHeader;
    private BinaryHeader binaryHeader;
    private final List<TraceHeader> traceHeaders;

    public SEGYModel() {
        traceHeaders = new ArrayList<TraceHeader>();
    }

    public TextHeader getTextHeader() {
        return textHeader;
    }

    public void setTextHeader(TextHeader textHeader) {
        this.textHeader = textHeader;
    }

    public BinaryHeader getBinaryHeader() {
        return binaryHeader;
    }

    public void setBinaryHeader(BinaryHeader binaryHeader) {
        this.binaryHeader = binaryHeader;
    }

    public List<TraceHeader> getTraceHeaders() {
        return traceHeaders;
    }

    public void addTraceHeader(TraceHeader traceHeader) {
        traceHeaders.add(traceHeader);
    }
}