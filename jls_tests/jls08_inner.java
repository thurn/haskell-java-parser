class HasStatic{
	static int j = 100;
}
class Outer{
	class Inner extends HasStatic{
		static final int x = 3;		// ok - compile-time constant
	}
	static class NestedButNotInner{
		static int z = 5; 		// ok, not an inner class
	}
}
