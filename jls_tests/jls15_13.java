class S { int x = 0; }
class T extends S { int x = 1; }
class Test {
	public static void main(String[] args) {
		T t = new T();
		System.out.println("t.x=" + t.x + when("t", t));
		S s = new S();
		System.out.println("s.x=" + s.x + when("s", s));
		s = t;
		System.out.println("s.x=" + s.x + when("s", s));
	}
	static String when(String name, Object t) {
		return " when " + name + " holds a "
			+ t.getClass() + " at run time.";
	}
}
