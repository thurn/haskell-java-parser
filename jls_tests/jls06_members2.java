class Point {
	float x, y;
	void move(int dx, int dy) { x += dx; y += dy; }
	void move(float dx, float dy) { x += dx; y += dy; }
	public String toString() { return "("+x+","+y+")"; }
}
