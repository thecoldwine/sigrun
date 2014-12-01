package sigrun.reports;

import sigrun.common.BinaryHeader;
import sigrun.common.TextHeader;
import sigrun.common.TraceHeader;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;

public class NavigationStrategy extends ReportStrategy {
    public static final int ESTIMATED_STRING_LENGTH = 512;

    public NavigationStrategy() {
        super();
    }

    public NavigationStrategy(String separator) {
        super(separator);
    }

    public NavigationStrategy(BinaryHeader binaryHeader, TextHeader textHeader) {
        super(binaryHeader, textHeader);
    }

    public NavigationStrategy(BinaryHeader binaryHeader, TextHeader textHeader, String separator) {
        super(binaryHeader, textHeader);
        setCustomSeparator(separator);
    }

    @Override
    public void processTraceHeader(TraceHeader traceHeader, OutputStream outputStream) throws IOException {
        StringBuilder builder = new StringBuilder(ESTIMATED_STRING_LENGTH);
        builder.append(binaryHeader.getLineNumber());
        builder.append(getSeparator());
        builder.append(traceHeader.getCrossLineNumber());
        builder.append(getSeparator());
        builder.append(traceHeader.getInLineNumber());
        builder.append(getSeparator());
        builder.append(traceHeader.getShotpointNumber());
        builder.append(getSeparator());
        builder.append(traceHeader.getSourceX());
        builder.append(getSeparator());
        builder.append(traceHeader.getSourceY());
        builder.append(System.getProperty("line.separator"));

        outputStream.write(builder.toString().getBytes("UTF-8"));
    }

    @Override
    public void printHeader(OutputStream outputStream) throws IOException {
        throw new NotImplementedException();
    }
}
