class Hyper {
	void hello() { System.out.println("hello from Hyper"); }
}
class Super extends Hyper { }
class Test extends Super {
	public static void main(String[] args) {
		new Test().hello();
	}
	void hello() {
		super.hello();
	}
}
