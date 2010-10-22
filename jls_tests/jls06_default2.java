package morepoints;
public class PlusPoint extends points.Point {
	public void move(int dx, int dy) {
		super.move(dx, dy);								// compile-time error
		moveAlso(dx, dy);
	}
}
