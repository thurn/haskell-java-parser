class Point {
	static int x = 2;
}
class Test extends Point {
	public static void main(String[] args) {
		new Test().printX();
	}
	void printX() {
		System.out.println(x + " " + super.x);
	}
}
