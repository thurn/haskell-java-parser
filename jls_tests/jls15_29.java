class Test {
	public static void main(String[] args) {
		int[] a = { 11, 12, 13, 14 };
		int[] b = { 0, 1, 2, 3 };
		System.out.println(a[(a=b)[3]]);
	}
}
