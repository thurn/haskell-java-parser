class Super { static int taxi = 1729; }
class Sub extends Super {
	static { System.out.print("Sub "); }
}
class Test {
	public static void main(String[] args) {
		System.out.println(Sub.taxi);
	}
}
