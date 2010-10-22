class Test {
	public static void main(String[] args) {
		Test t = new Test();
		synchronized(t) {
			synchronized(t) {
				System.out.println("made it!");
			}
		}
	}
}
