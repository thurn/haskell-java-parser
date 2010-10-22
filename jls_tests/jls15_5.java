strictfp class Test {
	public static void main(String[] args) {
		double d = 8e+307;
		System.out.println(4.0 * d * 0.5);
		System.out.println(2.0 * d);
	}
}