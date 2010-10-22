class Point { int x, y; }
class Element { int atomicNumber; }
class Test {
	public static void main(String[] args) {
		Point p = new Point();
		Element e = new Element();
		if (e instanceof Point) {											// compile-time error
			System.out.println("I get your point!");
			p = (Point)e;										// compile-time error
		}
	}
}
