interface PointInterface {
	void move(int dx, int dy);
}
interface RealPointInterface extends PointInterface {
	void move(float dx, float dy);
	void move(double dx, double dy);
}
