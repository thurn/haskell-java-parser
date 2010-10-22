all: test clean

test:
	@echo "Compiling..."
	ghc --make -O99 TestJava.hs
	@echo "Running..."
	./TestJava

coverage:
	@ghc --make -O99 -fhpc
	@./TestJava

report: coverage
	@hpc report TestJava

markup: coverage
	@hpc markup TestJava

clean:
	@rm -f *.hi
	@rm -f *.o
	@rm -f *.html
	@rm -f TestJava
	@rm -f TestJava.tix
