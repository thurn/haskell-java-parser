class Test {
	public static void main(String[] args) {
		String s = "one";
		if (s.startsWith(s = "two"))
			System.out.println("oops");
	}
}
