package points;
public class Point {
	int x, y;
	public void move(int dx, int dy) {
		x += dx; y += dy;
		moves++;
	}
	public static int moves = 0;
}
