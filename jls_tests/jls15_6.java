class Test {
	public static void main(String[] args) {
		String s = "going, ";
		print3(s, s, s = "gone");
	}
	static void print3(String a, String b, String c) {
		System.out.println(a + b + c);
	}
}
