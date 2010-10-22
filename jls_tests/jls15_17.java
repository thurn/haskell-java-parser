public class Doubler {
	static int two() { return two(1); }
	private static int two(int i) { return 2*i; }
}
class Test extends Doubler {	
	public static long two(long j) {return j+j; }
	public static void main(String[] args) {
		System.out.println(two(3));
		System.out.println(Doubler.two(3)); // compile-time error
	}
}
