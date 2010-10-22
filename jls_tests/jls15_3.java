class Test {
	public static void main(String[] args) {
		int j = 1;
		try {
			int i = forgetIt() / (j = 2);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Now j = " + j);
		}
	}
	static int forgetIt() throws Exception {
		throw new Exception("I'm outta here!");
	}
}
