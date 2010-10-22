interface Color { int RED=0, GREEN=1, BLUE=2; }
interface TrafficLight { int RED=0, YELLOW=1, GREEN=2; }
class Test implements Color, TrafficLight {
	public static void main(String[] args) {
		System.out.println(GREEN);			// compile-time error
		System.out.println(RED);		// compile-time error
	}
}
