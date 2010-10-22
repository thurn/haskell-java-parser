package points;
public class ColoredPoint extends Point {
	public static final int
		RED = 0, GREEN = 1, BLUE = 2;
	public static String[] COLORS =
		{ "red", "green", "blue" };
	public byte color;
	public ColoredPoint(int x, int y, int color) {
		super(x, y); this.color = (byte)color;
	}
	/** Copy all relevant fields of the argument into
		    this ColoredPoint object. */
	public void adopt(Point p) { x = p.x; y = p.y; }
	public String toString() {
		String s = "," + COLORS[color];
		return super.toString(s);
	}
}
