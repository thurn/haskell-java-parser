class Test {
	public static void main(String[] args) {
		RealPoint rp = new RealPoint();
		Point p = rp;
		rp.move(1.71828f, 4.14159f);
		p.move(1, -1);
		show(p.x, p.y);
		show(rp.x, rp.y);
		show(p.getX(), p.getY());
		show(rp.getX(), rp.getY());
	}
	static void show(int x, int y) {
		System.out.println("(" + x + ", " + y + ")");
	}
	static void show(float x, float y) {
		System.out.println("(" + x + ", " + y + ")");
	}
}
