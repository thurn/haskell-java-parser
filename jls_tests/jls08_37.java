class Top {
	int x;
	class Dummy {
		Dummy(Object o) {}
	}
	class Inside extends Dummy {
		Inside() {
			super(new Object() { int r = x; }); // error
		}
		Inside(final int y) {
			super(new Object() { int r = y; }); // correct
		}
	}
}
