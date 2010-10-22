public class Point { int x, y; }
public interface Colorable { void setColor(int color); }
public class ColoredPoint extends Point implements Colorable 
{
	int color;
	public void setColor(int color) { this.color = color; }
}
class Test {
	public static void main(String[] args) {
		Point p = new Point();
		ColoredPoint cp = new ColoredPoint();
		// Okay because ColoredPoint is a subclass of Point:
		p = cp;
		// Okay because ColoredPoint implements Colorable:
		Colorable c = cp;
		// The following cause compile-time errors because
		// we cannot be sure they will succeed, depending on
		// the run-time type of p; a run-time check will be
		// necessary for the needed narrowing conversion and
		// must be indicated by including a cast:
		cp = p;				// p might be neither a ColoredPoint
						// nor a subclass of ColoredPoint
		c = p;				// p might not implement Colorable
	}
}
