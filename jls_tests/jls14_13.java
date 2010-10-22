class BlewIt extends Exception {
	BlewIt() { }
	BlewIt(String s) { super(s); }
}
class Test {
	static void blowUp() throws BlewIt {
		throw new NullPointerException();
	}
	public static void main(String[] args) {
		try {
			blowUp();
		} catch (BlewIt b) {
			System.out.println("BlewIt");
		} finally {
			System.out.println("Uncaught Exception");
		}
	}
}
