class Point {
	Point() { setMasterID(); }
	int x, y;
	private int ID;
	private static int masterID = 0;
	private void setMasterID() { ID = masterID++; }
}
