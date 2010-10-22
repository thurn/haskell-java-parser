class Point { int x, y; }
class ColoredPoint extends Point { int color; }
class Test {
	static int test(ColoredPoint p) {
		return p.color;
	}
	static String test(Point p) {
		return "Point";
	}
	public static void main(String[] args) {
		ColoredPoint cp = new ColoredPoint();
		String s = test(cp);											// compile-time error
	}
}
