class Point {
	final int EDGE = 20;
	int x, y;
	void move(int dx, int dy) {
		x += dx; y += dy;
		if (Math.abs(x) >= EDGE || Math.abs(y) >= EDGE)
			clear();
	}
	void clear() {
		System.out.println("\tPoint clear");
		x = 0; y = 0;
	}
}
class ColoredPoint extends Point {
	int color;
	void clear() {
		System.out.println("\tColoredPoint clear");
		super.clear();
		color = 0;
	}
}