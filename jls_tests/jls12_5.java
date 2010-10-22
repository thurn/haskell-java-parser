class Super {
	Super() { printThree(); }
	void printThree() { System.out.println("three"); }
}




class Test extends Super {
	int three = (int)Math.PI;													// That is, 3
	public static void main(String[] args) {
		Test t = new Test();
		t.printThree();
	}
	void printThree() { System.out.println(three); }
}
