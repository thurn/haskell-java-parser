class Test {
	public static void main(String[] args) {
		int ia1[] = { 1, 2 };
		int ia2[] = (int[])ia1.clone();
		System.out.print((ia1 == ia2) + " ");
		ia1[1]++;
		System.out.println(ia2[1]);
	}
}
