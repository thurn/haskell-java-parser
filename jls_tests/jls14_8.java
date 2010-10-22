class Toomany {
	static void howMany(int k) {
		switch (k) {
		case 1:			System.out.print("one ");
		case 2:			System.out.print("too ");
		case 3:			System.out.println("many");
		}
	}
	public static void main(String[] args) {
		howMany(3);
		howMany(2);
		howMany(1);
	}
}
