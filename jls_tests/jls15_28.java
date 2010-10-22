class T1 {
	String s() { return "1"; }
}
class T2 extends T1 {
	String s() { return "2"; }
}
class T3 extends T2 {
	String s() { return "3"; }
	void test() {
		System.out.println("s()=\t\t"+s());
		System.out.println("super.s()=\t"+super.s());
		System.out.print("((T2)this).s()=\t");
			System.out.println(((T2)this).s());
		System.out.print("((T1)this).s()=\t");
			System.out.println(((T1)this).s());
	}
}
class Test {
	public static void main(String[] args) {
		T3 t3 = new T3();
		t3.test();
	}
}
