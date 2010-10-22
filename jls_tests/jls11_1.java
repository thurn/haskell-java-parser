class TestException extends Exception {
	TestException() { super(); }
	TestException(String s) { super(s); }
}
class Test {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			try {
				thrower(args[i]);
				System.out.println("Test \"" + args[i] +
					"\" didn't throw an exception");
			} catch (Exception e) {
				System.out.println("Test \"" + args[i] +
					"\" threw a " + e.getClass() +
					"\n        with message: " + e.getMessage());
			}
		}
	}
	static int thrower(String s) throws TestException {
		try {
			if (s.equals("divide")) {
				int i = 0;
				return i/i;
			}
			if (s.equals("null")) {
				s = null;
				return s.length();
			}
			if (s.equals("test"))
				throw new TestException("Test message");
			return 0;
		} finally {
			System.out.println("[thrower(\"" + s +
					"\") done]");
		}
	}
}
