interface Colors {
	int WHITE = 0, BLACK = 1;
}
interface Separates {
	int CYAN = 0, MAGENTA = 1, YELLOW = 2, BLACK = 3;
}
class Test implements Colors, Separates {
	public static void main(String[] args) {
		System.out.println(BLACK); // compile-time error: ambiguous
	}
}