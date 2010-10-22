class Super { void out() { System.out.println("out"); } }
class Test extends Super {
	public static void main(String[] args) {
		Test t = new Test();
		t.out();
	}
	void out() { super.out(); }
}
