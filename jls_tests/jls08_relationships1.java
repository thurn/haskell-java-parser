public interface Colorable {
	void setColor(int color);
	int getColor();
}
public interface Paintable extends Colorable {
	int MATTE = 0, GLOSSY = 1;
	void setFinish(int finish);
	int getFinish();
}
class Point { int x, y; }
class ColoredPoint extends Point implements Colorable {
	int color;
	public void setColor(int color) { this.color = color; }
	public int getColor() { return color; }
}
class PaintedPoint extends ColoredPoint implements Paintable 
{
	int finish;
	public void setFinish(int finish) {
		this.finish = finish;
	}
	public int getFinish() { return finish; }
}
