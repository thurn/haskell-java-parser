package p2;
class SonOfOuter extends p1.Outer {
	void foo() {
 		new Inner(); // compile-time access error
	}
}

