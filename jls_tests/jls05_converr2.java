class Point { int x, y; }
class ColoredPoint extends Point { int color; }
class Test {
	public static void main(String[] args) {
		long[] veclong = new long[100];
		Object o = veclong;				// okay
		Long l = veclong;				// compile-time error
		short[] vecshort = veclong;			// compile-time error
		Point[] pvec = new Point[100];
		ColoredPoint[] cpvec = new ColoredPoint[100];
		pvec = cpvec;					// okay
		pvec[0] = new Point();				// okay at compile time,
								// but would throw an
								// exception at run time
		cpvec = pvec;					// compile-time error
	}
}
