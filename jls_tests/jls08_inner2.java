class Outer {
	int i = 100;
	static void classMethod() {
		final int l = 200;
		class LocalInStaticContext{
			int k = i; // compile-time error
			int m = l; // ok
		}
	}
	void foo() {
		class Local { // a local class
			int j = i;
		}
	}
}
