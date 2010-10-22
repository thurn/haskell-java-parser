class Point implements Move {
	int x, y;
	abstract void move(int dx, int dy);
	void move(int dx, int dy) { x += dx; y += dy; }
}
