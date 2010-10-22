class Test {
	static int i = 0, j = 0;
	static synchronized void one() { i++; j++; }
	static synchronized void two() {
		System.out.println("i=" + i + " j=" + j);
	}
}