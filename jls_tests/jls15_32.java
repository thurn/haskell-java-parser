class Test {
	public static void main(String[] args) {
		int[] a = null;
		try {
			int i = a[vamoose()];
			System.out.println(i);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	static int vamoose() throws Exception {
		throw new Exception("Twenty-three skidoo!");
	}
}
