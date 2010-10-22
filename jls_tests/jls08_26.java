class Point {
	int x = 0, y = 0;
	void move(int dx, int dy) { x += dx; y += dy; }
}
class SlowPoint extends Point {
	int xLimit, yLimit;
	void move(int dx, int dy) {
		super.move(limit(dx, xLimit), limit(dy, yLimit));
	}
	static int limit(int d, int limit) {
		return d > limit ? limit : d < -limit ? -limit : d;
	}
}
