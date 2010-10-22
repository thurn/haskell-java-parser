class Super { void out() { System.out.println("Out"); } }
class Test extends Super {
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println("Way ");
		t.out();
	}
}
