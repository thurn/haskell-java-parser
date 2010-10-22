import points.Point3d;
class Point4d extends Point3d {
	int w;
	public void move(int dx, int dy, int dz, int dw) {
		x += dx; y += dy; z += dz; w += dw; // compile-time errors
	}
}
