class Point {
	static int x = 2;
}
class Test extends Point {
	static double x = 4.7;
	public static void main(String[] args) {
		new Test().printX();
	}
	void printX() {
		System.out.println(x + " " + super.x);
	}
}
