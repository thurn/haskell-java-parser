package points;
public class Point {
	public int x, y;
	public void move(int dx, int dy) { x += dx; y += dy; }
}
class PointList {
	Point next, prev;
}
