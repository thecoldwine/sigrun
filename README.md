sigrun
======

Sigrun is simple and configurable pure Java Seg-Y parser that includes capabilities to build reports (or extract navigation)
for your SegY data.

At the moment library supports reading SegY file as a forward-only stream with custom format. Default rev0 and rev1 are
not supported at the moment as default classes but may be implemented via custom formats according standard.

Sample usage to read custom SEGY.

```java

// Format for binary header
BinaryHeaderFormat binHeaderFmt = BinaryHeaderFormatBuilder.aBinaryHeaderFormat()
                .withLineNumberFormat(FormatEntry.create(4, 8))
                .withSampleIntervalFormat(FormatEntry.create(16, 18))
                .withSamplesPerDataTraceFormat(FormatEntry.create(20, 22))
                .withDataSampleCodeFormat(FormatEntry.create(24, 26))
                .withSegyFormatRevNumberFormat(FormatEntry.create(300, 302))
                .withFixedLengthTraceFlagFormat(FormatEntry.create(302, 304))
                .withNumberOf3200ByteFormat(FormatEntry.create(304, 306))
                .build();

TraceHeaderFormat trcHeaderFmt = TraceHeaderFormatBuilder.aTraceHeaderFormat().
                withEnsembleNumberFormat(FormatEntry.create(20, 24)).
                withSourceXFormat(FormatEntry.create(72, 76)).
                withSourceYFormat(FormatEntry.create(76, 80)).
                withXOfCDPPositionFormat(FormatEntry.create(180, 184)).
                withYOfCDPPositionFormat(FormatEntry.create(184, 188)).
                withNumberOfSamplesFormat(FormatEntry.create(114, 116)).
                build();

// Make stream factory for particular SegyFormat
SEGYStreamFactory streamFactory = SEGYStreamFactory.create(
                    Charset.forName("Cp1047"),
                    binHeaderFmt,
                    trcHeaderFmt);

// Make java.nio.FileChannel for some file
FileChannel chan = new FileInputStream(path).getChannel();

// Make stream for file channel. At this moment parsing is started
SEGYStream segyStream = streamFactory.makeStream(chan, Collections.EMPTY_SET);

// EBCDIC header
TextHeader txtHeader = segyStream.getTextHeader();

// Binary header
BinaryHeader binHeader = segyStream.getBinaryHeader();

for (SeismicTrace trace : segyStream) {
  // Do something with seismic trace. Data values are already parsed and stored as float[]
}
```