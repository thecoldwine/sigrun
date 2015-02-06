package sigrun.common;

import sigrun.serialization.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * Created by maksenov on 15/01/15.
 */
public class SEGYStreamFactory {
    private final TextHeaderReader textHeaderReader;
    private final BinaryHeaderReader binaryHeaderReader;
    private final TraceHeaderReader traceHeaderReader;

    private SEGYStreamFactory(final Charset charset,
                              final BinaryHeaderFormat binaryHeaderFormat,
                              final TraceHeaderFormat traceHeaderFormat) {
        this.textHeaderReader = new TextHeaderReader(charset);
        this.binaryHeaderReader = new BinaryHeaderReader(binaryHeaderFormat);
        this.traceHeaderReader = new TraceHeaderReader(traceHeaderFormat);
    }

    public static SEGYStreamFactory create(final Charset charset,
                                    final BinaryHeaderFormat binaryHeaderFormat,
                                    final TraceHeaderFormat traceHeaderFormat) {
        return new SEGYStreamFactory(charset, binaryHeaderFormat, traceHeaderFormat);
    }

    public SEGYStream makeStream(FileChannel chan) {
        return makeStream(chan, Collections.<ParseProgressListener>emptySet());
    }

    public SEGYStream makeStream(FileChannel chan, Collection<ParseProgressListener> listeners) {
        return new SEGYStream(chan, textHeaderReader, binaryHeaderReader, traceHeaderReader, listeners);
    }

    public SEGYStream makeStream(FileInputStream fileInputStream) {
        return makeStream(fileInputStream, Collections.<ParseProgressListener>emptySet());
    }

    public SEGYStream makeStream(FileInputStream fileInputStream, Collection<ParseProgressListener> listeners) {
        final FileChannel chan = fileInputStream.getChannel();

        return new SEGYStream(chan, textHeaderReader, binaryHeaderReader, traceHeaderReader, listeners);
    }
}
