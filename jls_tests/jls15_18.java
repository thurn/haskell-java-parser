class ColoredPoint {
	int x, y;
	byte color;
	void setColor(byte color) { this.color = color; }
}
class Test {
	public static void main(String[] args) {
		ColoredPoint cp = new ColoredPoint();
		byte color = 37;
		cp.setColor(color);
		cp.setColor(37);											// compile-time error
	}
}
