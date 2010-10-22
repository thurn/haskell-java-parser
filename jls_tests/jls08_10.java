class Z {
	static int peek() { return j; }
	static int i = peek();
	static int j = 1;
}
class Test {
	public static void main(String[] args) {
		System.out.println(Z.i);
	}
}
