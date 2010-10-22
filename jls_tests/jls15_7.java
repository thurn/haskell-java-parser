class Test {
	static int id;
	public static void main(String[] args) {
		try {
			test(id = 1, oops(), id = 3);
		} catch (Exception e) {
			System.out.println(e + ", id=" + id);
		}
	}
	static int oops() throws Exception {
		throw new Exception("oops");
	}
	static int test(int a, int b, int c) {
		return a + b + c;
	}
}
