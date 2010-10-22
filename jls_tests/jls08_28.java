class Point {
	int x = 0, y = 0, color;
	void move(int dx, int dy) { x += dx; y += dy; }
	int getX() { return x; }
	int getY() { return y; }
}
class RealPoint extends Point {
	float x = 0.0f, y = 0.0f;
	void move(int dx, int dy) { move((float)dx, (float)dy); }
	void move(float dx, float dy) { x += dx; y += dy; }
	float getX() { return x; }
	float getY() { return y; }
}
