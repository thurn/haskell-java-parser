class Test {
	static int m(byte a, int b) { return a+b; }
	static int m(short a, short b) { return a-b; }
	public static void main(String[] args) {
		System.out.println(m(12, 2));										// compile-time error
	}
}
