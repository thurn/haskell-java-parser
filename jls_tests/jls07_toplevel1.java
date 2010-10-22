package test;
import java.util.Vector;
class Point {
	int x, y;
}
interface Point {			// compile-time error #1
	int getR();
	int getTheta();
}
class Vector { Point[] pts; }		// compile-time error #2
