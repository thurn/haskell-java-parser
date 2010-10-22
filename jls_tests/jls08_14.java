class Point {
	int x = 2;
}
class Test extends Point {
	double x = 4.7;
	void printBoth() {
		System.out.println(x + " " + super.x);
	}
	public static void main(String[] args) {
		Test sample = new Test();
		sample.printBoth();
		System.out.println(sample.x + " " + 
								((Point)sample).x);
	}
}
