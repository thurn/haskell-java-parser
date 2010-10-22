class BufferEmpty extends Exception {
	BufferEmpty() { super(); }
	BufferEmpty(String s) { super(s); }
}
class BufferError extends Exception {
	BufferError() { super(); }
	BufferError(String s) { super(s); }
}
public interface Buffer {
	char get() throws BufferEmpty, BufferError;
}
public abstract class InfiniteBuffer implements Buffer {
	abstract char get() throws BufferError;
}
