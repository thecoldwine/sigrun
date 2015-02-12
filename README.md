sigrun
======

Sigrun is simple and configurable pure Java Seg-Y parser that includes capabilities to build reports (or extract navigation)
for your SegY data.

At the moment library supports reading SegY file as a forward-only stream with custom format. Default rev0 and rev1 are
not supported at the moment as default classes but may be implemented via custom formats according standard.

Sample usage to read custom SEGY.

```java

public class SampleReadRoutine {
    private static final Logger logger = LoggerFactory.getLogger(SampleReadRoutine.class.getName());

    private static ParseProgressListener makeListener() {
        return new ParseProgressListener() {
            @Override
            public void progress(long read) {
                System.out.println("Progress changed to: " + read);
            }
        };
    }

    private static Set<ParseProgressListener> makeListenerSet() {
        Set<ParseProgressListener> result = new HashSet<ParseProgressListener>();
        result.add(makeListener());

        return result;
    }

    public static BinaryHeaderFormat makeBinHeaderFormat() {
        return BinaryHeaderFormatBuilder.aBinaryHeaderFormat()
                .withLineNumberFormat(FormatEntry.create(4, 8))
                .withSampleIntervalFormat(FormatEntry.create(16, 18))
                .withSamplesPerDataTraceFormat(FormatEntry.create(20, 22))
                .withDataSampleCodeFormat(FormatEntry.create(24, 26))
                .withSegyFormatRevNumberFormat(FormatEntry.create(300, 302))
                .withFixedLengthTraceFlagFormat(FormatEntry.create(302, 304))
                .withNumberOf3200ByteFormat(FormatEntry.create(304, 306))
                .build();
    }

    public static TraceHeaderFormat makeTraceHeaderFormat() {
        return TraceHeaderFormatBuilder.aTraceHeaderFormat().
                withEnsembleNumberFormat(FormatEntry.create(20, 24)).
                withSourceXFormat(FormatEntry.create(72, 76)).
                withSourceYFormat(FormatEntry.create(76, 80)).
                withXOfCDPPositionFormat(FormatEntry.create(180, 184)).
                withYOfCDPPositionFormat(FormatEntry.create(184, 188)).
                withNumberOfSamplesFormat(FormatEntry.create(114, 116)).
                build();
    }


    public static void main(String[] args) {
        String path = args[0];

        if (path == null || path.isEmpty()) {
            logger.error("Path is empty. Aborting");
            System.exit(1);
        }

        logger.info(path);

        try {
            // Getting FileChannel for file.
            FileChannel chan = new FileInputStream(path).getChannel();

            // Create factory for particular SegY format
            SEGYStreamFactory streamFactory = SEGYStreamFactory.create(
                    Charset.forName("Cp1047"),
                    makeBinHeaderFormat(),
                    makeTraceHeaderFormat());

            final long startTime = System.currentTimeMillis();

            // At this point reading actually started. EBCDIC and binary headers are parsed on
            // object construction.
            SEGYStream segyStream = streamFactory.makeStream(chan, makeListenerSet());

            printTextHeader(segyStream.getTextHeader());
            printBinHeaderInfo(segyStream.getBinaryHeader());

            // Iterates over SegyFile. Stream is forward only and may be iterated only once.
            for (SeismicTrace trace : segyStream) {
                printTraceInfo(trace);
            }

            final long timeEnd = System.currentTimeMillis() - startTime;
            System.out.println("Parsing took: " + timeEnd + " ms.");
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            System.exit(2);
        }


        System.exit(0);
    }

    private static void printTextHeader(TextHeader header) {
        System.out.println("Text Header info...");
        for (String s : header.getContents()) {
            System.out.println(s);
        }
    }

    private static void printBinHeaderInfo(BinaryHeader binaryHeader) {
        System.out.println("Binary Header info...");
        System.out.println("Data sample code:" + binaryHeader.getDataSampleCode());
    }

    private static void printTraceInfo(SeismicTrace trace) {
        System.out.println("Trace Header info...");
        System.out.println("Number of samples: " + trace.getHeader().getNumberOfSamples());

        System.out.println("Size of array: " + trace.getValues().length);
        System.out.printf("Values: %.10f : %.10f%n", trace.getMin(), trace.getMax());
        System.out.printf("Diff: %.10f%n", trace.getMax() - trace.getMin());
    }
}
```
