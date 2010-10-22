class SynchSample {
	int a = 1, b = 2;
	synchronized void hither() {
		a = b;
	}
	synchronized void yon() {
		b = a;
	}
}
