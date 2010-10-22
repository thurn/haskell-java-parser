interface Fish { int getNumberOfScales(); }
interface Piano { int getNumberOfScales(); }
class Tuna implements Fish, Piano {
	// You can tune a piano, but can you tuna fish?
	int getNumberOfScales() { return 91; }
}
