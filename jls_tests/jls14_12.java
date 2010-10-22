class BlewIt extends Exception {
	BlewIt() { }
	BlewIt(String s) { super(s); }
}
class Test {
	static void blowUp() throws BlewIt { throw new BlewIt(); }
	public static void main(String[] args) {
		try {
			blowUp();
		} catch (RuntimeException r) {
			System.out.println("RuntimeException:" + r);
		} catch (BlewIt b) {
			System.out.println("BlewIt");
		}
	}
}
