class Hyper { String h = "hyper"; }
class Super extends Hyper { String s = "super"; }
class Test {
	public static void main(String[] args) {
		System.out.println(new Super().h);
	}
}
