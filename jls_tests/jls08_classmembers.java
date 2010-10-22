class Point {
	int x, y;
	private Point() { reset(); }
	Point(int x, int y) { this.x = x; this.y = y; }
	private void reset() { this.x = 0; this.y = 0; }
}
class ColoredPoint extends Point {
	int color;
	void clear() { reset(); }		// error
}
class Test {
	public static void main(String[] args) {
		ColoredPoint c = new ColoredPoint(0, 0);	// error
		c.reset();				// error
	}
}
