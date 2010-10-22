interface BaseColors {
	int RED = 1, GREEN = 2, BLUE = 4;
}
interface RainbowColors extends BaseColors {
	int YELLOW = 3, ORANGE = 5, INDIGO = 6, VIOLET = 7;
}
interface PrintColors extends BaseColors {
	int YELLOW = 8, CYAN = 16, MAGENTA = 32;
}
interface LotsOfColors extends RainbowColors, PrintColors {
	int FUCHSIA = 17, VERMILION = 43, CHARTREUSE = RED+90;
}
