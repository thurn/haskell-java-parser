package points;
public class Point {
	public int x, y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	public String toString() { return toString(""); }
	public String toString(String s) {
		return "(" + x + "," + y + s + ")";
	}
}
