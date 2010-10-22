class List {
	int value;
	List next;
	static List head = new List(0);
	List(int n) { value = n; next = head; head = this; }
}
class Test {
	public static void main(String[] args) {
		int id = 0, oldid = 0;
		try {
			for (;;) {
				++id;
				new List(oldid = id);
			}
		} catch (Error e) {
			System.out.println(e + ", " + (oldid==id));
		}
	}
}
