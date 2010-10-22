class Test {
	public static void main(String[] args) {
		int k = 1;
		int[] a = { 1 };
		k += (k = 4) * (k + 2);
		a[0] += (a[0] = 4) * (a[0] + 2);
		System.out.println("k==" + k + " and a[0]==" + a[0]);
	}
}
