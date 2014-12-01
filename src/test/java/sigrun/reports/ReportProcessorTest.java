package sigrun.reports;

import org.junit.Test;
import sigrun.common.BinaryHeader;
import sigrun.common.TextHeader;
import sigrun.serialization.BinaryHeaderSerializer;
import sigrun.serialization.Serializer;
import sigrun.serialization.TextHeaderSerializer;
import sigrun.serialization.TraceHeaderSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertArrayEquals;

public class ReportProcessorTest {
    @Test
    public void testProcess() throws Exception {
        final ReportProcessor reportProcessor = new ReportProcessor(new TextHeaderSerializer(),
                new BinaryHeaderSerializer(),
                new TraceHeaderSerializer());

        ByteArrayInputStream is = new ByteArrayInputStream(ReportProcessorTestConstants.TEST_DATA);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ReportStrategy strategy = new TestStrategy();
        Serializer<TextHeader> textHeaderSerializer = new TextHeaderSerializer();
        Serializer<BinaryHeader> binaryHeaderSerializer = new BinaryHeaderSerializer();

        reportProcessor.process(is, os, strategy);

        byte[] serializedTextHeader = textHeaderSerializer.serialize(strategy.getTextHeader());
        assertArrayEquals(ReportProcessorTestConstants.TEXT_HEADER, serializedTextHeader);
        System.out.println(strategy.getTextHeader().getCard(0));

        byte[] serializedBinaryHeader = binaryHeaderSerializer.serialize(strategy.getBinaryHeader());
        assertArrayEquals(ReportProcessorTestConstants.BINARY_HEADER, serializedBinaryHeader);
    }
}
