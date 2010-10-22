class CheckedPoint extends Point {
	void move(int dx, int dy) {
		if ((x + dx) < 0 || (y + dy) < 0)
			throw new BadPointException();
		x += dx; y += dy;
	}
}
