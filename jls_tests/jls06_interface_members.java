interface Colors {
	int WHITE = 0, BLACK = 1;
}

interface Separates {
	int CYAN = 0, MAGENTA = 1, YELLOW = 2, BLACK = 3;
}

interface ColorsAndSeparates extends Colors, Separates {
	int DEFAULT = BLACK;								 	// compile-time error: ambiguous
}