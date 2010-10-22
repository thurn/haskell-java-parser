class Test {
	public static void main(String[] args) {
		int index = 1;
		try {
			skedaddle()[index=2]++;
		} catch (Exception e) {
			System.out.println(e + ", index=" + index);
		}
	}
	static int[] skedaddle() throws Exception {
		throw new Exception("Ciao");
	}
}
