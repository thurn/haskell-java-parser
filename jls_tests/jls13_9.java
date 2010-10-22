class Hyper { String h = "Hyper"; }
class Super extends Hyper { char h = 'h'; }
class Test extends Super {
	public static void main(String[] args) {
		String s = new Test().h;
		System.out.println(s);
	}
}
