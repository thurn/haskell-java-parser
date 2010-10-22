class Hyper {
	void hello() { System.out.println("hello from Hyper"); }
}
class Super extends Hyper {
	void hello() { System.out.println("hello from Super"); }
}
class Test {
	public static void main(String[] args) {
		new Super().hello();
	}
}
