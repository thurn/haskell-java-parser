class Test {
	static int v;
	static final int f = 3;
	public static void main(String[] args) {
		int i;
		i = 1;
		v = 2;
		f = 33;										// compile-time error
		System.out.println(i + " " + v + " " + f);
	}
}
