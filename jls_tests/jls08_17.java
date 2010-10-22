interface Frob { float v = 2.0f; }
class SuperTest { int v = 3; }
class Test extends SuperTest implements Frob {
	public static void main(String[] args) {
		new Test().printV();
	}
	void printV() {
		System.out.println((super.v + Frob.v)/2);
	}
}
