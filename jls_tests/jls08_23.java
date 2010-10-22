class Test {
	int count;
	synchronized void bump() { count++; }
	static int classCount;
	static synchronized void classBump() {
		classCount++;
	}
}
