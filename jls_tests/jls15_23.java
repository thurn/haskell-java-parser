import points.*;
class Test {
	public static void main(String[] args) {
		ColoredPoint cp =
			new ColoredPoint(6, 6, ColoredPoint.RED);
		ColoredPoint cp2 =
			new ColoredPoint(3, 3, ColoredPoint.GREEN);
		cp.adopt(cp2);
		System.out.println("cp: " + cp);
	}
}
