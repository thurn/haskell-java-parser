public class Point {
	public int x, y;
	void move(int dx, int dy) { x += dx; y += dy; }
	public void moveAlso(int dx, int dy) { move(dx, dy); }
}