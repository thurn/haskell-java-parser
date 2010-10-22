class Test {
	static void mountain() {
		System.out.println("Monadnock");
	}
	static Test favorite(){
		System.out.print("Mount ");
		return null;
	}
	public static void main(String[] args) {
		favorite().mountain();
	}
}
