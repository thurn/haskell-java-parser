class Point {
	int x, y;
	int useCount;
	Point(int x, int y) { this.x = x; this.y = y; }
	final static Point origin = new Point(0, 0);
}
