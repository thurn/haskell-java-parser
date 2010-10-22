interface Colorable {
	void setColor(int color);
	int getColor();
}
class Point { int x, y; };
class ColoredPoint extends Point implements Colorable {
	int color;
}
