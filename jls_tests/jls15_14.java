class S { int x = 0; int z() { return x; } }
class T extends S { int x = 1; int z() { return x; } }
class Test {
	public static void main(String[] args) {
		T t = new T();
		System.out.println("t.z()=" + t.z() + when("t", t));
		S s = new S();
		System.out.println("s.z()=" + s.z() + when("s", s));
		s = t;
		System.out.println("s.z()=" + s.z() + when("s", s));
	}
	static String when(String name, Object t) {
		return " when " + name + " holds a "
			+ t.getClass() + " at run time.";
	}
}
