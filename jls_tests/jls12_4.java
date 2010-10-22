class Point {
	int x, y;
	Point() { x = 1; y = 1; }
}
class ColoredPoint extends Point {
	int color = 0xFF00FF;
}
class Test {
	public static void main(String[] args) {
		ColoredPoint cp = new ColoredPoint();
		System.out.println(cp.color);
	}
}
