class Test {
	public static void main(String[] args) {
		short s = 123;
		char c = s;			// error: would require cast
		s = c;				// error: would require cast
	}
}
