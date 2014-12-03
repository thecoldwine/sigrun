package sigrun.reports;

import sigrun.common.BinaryHeader;
import sigrun.common.TextHeader;
import sigrun.common.TraceHeader;

import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings({"SameParameterValue", "WeakerAccess", "UnusedDeclaration"})
public abstract class ReportStrategy {
    @SuppressWarnings("WeakerAccess")
    public final static String DEFAULT_SEPARATOR = ",";

    private String customSeparator;
    private boolean printHeaderSupported = false;
    @SuppressWarnings("WeakerAccess")
    protected BinaryHeader binaryHeader;
    @SuppressWarnings("WeakerAccess")
    protected TextHeader textHeader;

    @SuppressWarnings("WeakerAccess")
    public ReportStrategy() {
        this(null, null);
    }

    @SuppressWarnings("WeakerAccess")
    public ReportStrategy(String separator) {
        this(null, null);
        this.customSeparator = separator;
    }

    @SuppressWarnings("WeakerAccess")
    public ReportStrategy(BinaryHeader binaryHeader, TextHeader textHeader) {
        this.binaryHeader = binaryHeader;
        this.textHeader = textHeader;
    }

    public final BinaryHeader getBinaryHeader() {
        return binaryHeader;
    }

    public final void setBinaryHeader(BinaryHeader binaryHeader) {
        this.binaryHeader = binaryHeader;
    }

    public final TextHeader getTextHeader() {
        return textHeader;
    }

    public final void setTextHeader(TextHeader textHeader) {
        this.textHeader = textHeader;
    }

    @SuppressWarnings("WeakerAccess")
    public final String getSeparator() {
        if (customSeparator != null && !customSeparator.equals(""))
            return customSeparator;
        else
            return DEFAULT_SEPARATOR;
    }

    @SuppressWarnings("WeakerAccess")
    public final void setCustomSeparator(String customSeparator) {
        this.customSeparator = customSeparator;

    }

    public boolean isPrintHeaderSupported() {
        return printHeaderSupported;
    }

    @SuppressWarnings("WeakerAccess")
    protected void setPrintHeaderSupported(boolean printHeaderSupported) {
        this.printHeaderSupported = printHeaderSupported;
    }

    public abstract void processTraceHeader(TraceHeader traceHeader, OutputStream outputStream) throws IOException;

    public abstract void printHeader(OutputStream outputStream) throws IOException;
}
