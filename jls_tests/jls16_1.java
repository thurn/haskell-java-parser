class Test {
void flow(boolean flag) {
	int k;
	if (flag)
		k = 3;
	else
		k = 4;
	System.out.println(k);
}
void flow(boolean flag) {
	int k;
	if (flag)
		k = 3;
	if (!flag)
		k = 4;
	System.out.println(k);	// k is not "definitely assigned" before here
}
void unflow(boolean flag) {
	final int k;
	if (flag) {
		k = 3;
		System.out.println(k);
	}
	else {
		k = 4;
		System.out.println(k);
	}
}
void unflow(boolean flag) {
	final int k;
	if (flag) {
		k = 3;
		System.out.println(k);
	}
	if (!flag) {
		k = 4; 		// k is not "definitely unassigned" before here
		System.out.println(k);
	}
}
}