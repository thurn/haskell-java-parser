class Super { static char s; }
class Test extends Super {
	public static void main(String[] args) {
		s = 'a';
		System.out.println(s);
	}
}
