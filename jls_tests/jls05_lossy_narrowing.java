class Test { // CLEANED UP LITERALS
	public static void main(String[] args) {

		// A narrowing of int to short loses high bits:
		System.out.println("(short)0x12345678==0x" +
					Integer.toHexString((short)0x12345678));

		// A int value not fitting in byte changes sign and magnitude:
		System.out.println("(byte)255==" + (byte)255);

		// A float value too big to fit gives largest int value:
		System.out.println("(int)1e20f==" + (int)1e20);

		// A NaN converted to int yields zero:
		System.out.println("(int)NaN==" + (int)Float.NaN);
		
		// A double value too large for float yields infinity:
		System.out.println("(float)-1e100==" + (float)-1e100);

		// A double value too small for float underflows to zero:
		System.out.println("(float)1e-50==" + (float)1e-50);
	}
}
