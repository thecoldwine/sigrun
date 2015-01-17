package sigrun.serialization;

import sigrun.common.ParseProgressListener;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by maksenov on 14/01/15.
 */
public abstract class AbstractReader {
    protected final Deque<ParseProgressListener> listeners = new ArrayDeque<ParseProgressListener>();

    protected synchronized void registerParseProgressListener(ParseProgressListener listener) {
        if (!listeners.contains(listener))
            this.listeners.addLast(listener);
    }

    protected synchronized void unregisterParseProgressListener(ParseProgressListener listener) {
        this.listeners.remove(listener);
    }

    protected synchronized void fireProgressEvent(final long read) {
        for (ParseProgressListener listener : this.listeners) {
            listener.progress(read);
        }
    }
}
