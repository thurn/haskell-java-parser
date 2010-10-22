class Twomany {
	static void howMany(int k) {
		switch (k) {
		case 1:			System.out.println("one");
					break;					// exit the switch
		case 2:			System.out.println("two");
					break;					// exit the switch
		case 3:			System.out.println("many");
					break;					// not needed, but good style
		}
	}
	public static void main(String[] args) {
		howMany(1);
		howMany(2);
		howMany(3);
	}
}
