class Test {
	public static void main(String[] args) {
		int divisor = 0;
		try {
			int i = 1 / (divisor * loseBig());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	static int loseBig() throws Exception {
		throw new Exception("Shuffle off to Buffalo!");
	}
}
