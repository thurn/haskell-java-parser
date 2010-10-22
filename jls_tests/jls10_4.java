class A implements Cloneable, java.io.Serializable {
	public final int length = X;
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}
}
