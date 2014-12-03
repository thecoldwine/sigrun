package sigrun.reports;

import sigrun.common.TraceHeader;

import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings("UnusedDeclaration")
public class PetrobankNavigationStrategy extends ReportStrategy {
    @SuppressWarnings("WeakerAccess")
    protected static final String NAVIGATION_FORMAT = "%s %-12d %-12d %-12d %-12d %-12d%n";
    @SuppressWarnings("WeakerAccess")
    protected static final String RECORD_PREFIX = "R";
    private int counter = 1;

    /*
        H
        R         1     532488    6982937          0    2010001
        R         2     532501    6982937          0    2010001
        R         3     532513    6982937          0    2010001
        R         4     532526    6982937          0    2010001
    */

    public PetrobankNavigationStrategy() {
        super();
        this.setPrintHeaderSupported(true);
    }

    @Override
    public void processTraceHeader(TraceHeader traceHeader, OutputStream outputStream) throws IOException {
        String result = String.format(NAVIGATION_FORMAT,
                RECORD_PREFIX,
                counter++,
                traceHeader.getSourceX(),
                traceHeader.getSourceY(),
                getShotPointNumber(traceHeader),
                binaryHeader.getLineNumber());

        outputStream.write(result.getBytes("UTF-8"));
    }

    private int getShotPointNumber(TraceHeader traceHeader) {
        int constant = traceHeader.getScalarForSPNumber();
        int SPNumber = traceHeader.getShotpointNumber();
        if (constant == 0)
            return SPNumber;
        else if (constant > 0)
            return SPNumber * constant;
        else
            return SPNumber / constant;
    }

    @Override
    public void printHeader(OutputStream outputStream) throws IOException {
        outputStream.write(("H" + System.getProperty("line.separator")).getBytes("UTF-8"));
    }
}
