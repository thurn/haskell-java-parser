class Flags { final static boolean debug = true; }
class Test {
	public static void main(String[] args) {
		if (Flags.debug)
			System.out.println("debug is true");
	}
}
