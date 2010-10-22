public class Point { int x, y; }
public interface Colorable { void setColor(int color); }
public class ColoredPoint extends Point implements Colorable 
{
	int color;
	public void setColor(int color) { this.color = color; }
}
final class EndPoint extends Point { }
class Test {
	public static void main(String[] args) {
		Point p = new Point();
		ColoredPoint cp = new ColoredPoint();
		Colorable c;

		// The following may cause errors at run time because
		// we cannot be sure they will succeed; this possibility
		// is suggested by the casts:
		cp = (ColoredPoint)p;		// p might not reference an
						// object which is a ColoredPoint
						// or a subclass of ColoredPoint
		c = (Colorable)p;		// p might not be Colorable

		// The following are incorrect at compile time because
		// they can never succeed as explained in the text:
		Long l = (Long)p;		// compile-time error #1
		EndPoint e = new EndPoint();
		c = (Colorable)e;		// compile-time error #2
	}
}
