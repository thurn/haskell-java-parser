class Point {
	int x, y;
	void move(int dx, int dy) {
		x += dx; y += dy; totalMoves++;
	}
	private static int totalMoves;
	void printMoves() { System.out.println(totalMoves); }
}
class Point3d extends Point {
	int z;
	void move(int dx, int dy, int dz) {
		super.move(dx, dy); z += dz; totalMoves++;
	}
}
