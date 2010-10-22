class Test extends points.Point {
	public void moveBack(int dx, int dy) {
		x -= dx; y -= dy; useCount++; totalUseCount++;
	}
}
