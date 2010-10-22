class Global {
	class Cyclic {}
	void foo() {
		new Cyclic(); // create a Global.Cyclic
		class Cyclic extends Cyclic{}; // circular definition
		{
			class Local{};
			{
			}
			class AnotherLocal {
				void bar() {
					class Local {}; // ok
				}
			}
		}
		class Local{}; // ok, not in scope of prior Local
   }
}
