public class Box {
	private Object boxContents;
	public synchronized Object get() {
		Object contents = boxContents;
		boxContents = null;
		return contents;
	}
	public synchronized boolean put(Object contents) {
		if (boxContents != null)
			return false;
		boxContents = contents;
		return true;
	}
}
