class BadPointException extends Exception {
	BadPointException() { super(); }
	BadPointException(String s) { super(s); }
}
class Point {
	int x, y;
	void move(int dx, int dy) { x += dx; y += dy; }
}
class CheckedPoint extends Point {
	void move(int dx, int dy) throws BadPointException {
		if ((x + dx) < 0 || (y + dy) < 0)
			throw new BadPointException();
		x += dx; y += dy;
	}
}
