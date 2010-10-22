class SynchSimple {
	int a = 1, b = 2;
	synchronized void to() {
		a = 3;
		b = 4;
	}
	void fro() {
		System.out.println("a= " + a + ", b=" + b);
	}
}