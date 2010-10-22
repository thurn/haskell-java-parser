class Test {
	public static void main(String[] args) throws Throwable {
		int ia[][] = { { 1 , 2}, null };
		int ja[][] = (int[][])ia.clone();
		System.out.print((ia == ja) + " ");
		System.out.println(ia[0] == ja[0] && ia[1] == ja[1]);
	}
}
