package morePoints;
class Point3d extends points.Point {
	public int z;
	public void move(int dx, int dy, int dz) {
		super.move(dx, dy); z += dz;
	}
	public void move(int dx, int dy) {
		move(dx, dy, 0);
	}
}
public class OnePoint {
	public static points.Point getOne() { 
		return new Point3d(); 
	}
}
