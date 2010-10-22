class Test {
	public static void main(String[] args) {
		Point p = new Point();
		System.out.println("p.move(20,20):");
		p.move(20, 20);
		ColoredPoint cp = new ColoredPoint();
		System.out.println("cp.move(20,20):");
		cp.move(20, 20);
		p = new ColoredPoint();
		System.out.println("p.move(20,20), p colored:");
		p.move(20, 20);
	}
}
