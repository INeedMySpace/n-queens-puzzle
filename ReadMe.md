## N-Queens-Puzzle solver
The definition for problem - https://en.wikipedia.org/wiki/Eight_queens_puzzle.  
Kotlin's implementation using backtracking and branching reduction.
Uses BooleanArrays to keep track of occupied diagonals. Which appears, according to profiler, to be more effective than Sets.

Implementation is looking for all possible solutions, not only for fundamental ones.
Probably I missed the important requirement here, but reference implementation is also provided all solutions.  
No multithreading - just single threaded implementation.   
On my machine 15x15 solution runs about 15 sec (without printing out found solutions) and 16x16 solution runs about 90 sec.

### to build
`./gradlew clean build`

### to run
* unpack distribution  
`cd build/distribution`  
`tar xvf n-queens-puzzle-0.0.x.tar` or `unzip n-queens-puzzle-0.0.x.zip`  
`cd n-queens-puzzle-0.0.x/bin`  
* use `-h` or `--help` for command line parameters, e.g. `-v` or `--verbose` to print out found solutions, `-n N` to set the board size  
`./n-queens-puzzle -v -n 4` or `n-queens-puzzle.bat -n 8`
