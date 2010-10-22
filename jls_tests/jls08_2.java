package morePoints;
public class Point4d extends Point3d {
	public int w;
	public void move(int dx, int dy, int dz, int dw) {
		super.move(dx, dy, dz); w += dw;
	}
}
