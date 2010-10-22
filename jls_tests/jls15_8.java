class IntVector {
	int[] v;
	boolean equals(IntVector other) {
		if (this == other)
			return true;
		if (v.length != other.v.length)
			return false;
		for (int i = 0; i < v.length; i++)
			if (v[i] != other.v[i])
				return false;
		return true;
	}
}
