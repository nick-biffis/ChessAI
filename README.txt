To compile the code:

java ChessAI.java

This should compile all its dependencies as well

After compiling, follow the instructions below.
To run the chess engine a number of args *must* be specified:
++++++++++++++++++++++++++++++++++++++++++++++++++++++++

java ChessAI <input.in> <color> <strategy> <max_depth>

++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Where:

input is: A.in, B.in, C.in (or one of our custom cases in the included directory)

color is: "white", "black"

strategy is: "sorted", "reverse", or "random"

max_depth is: 2, 4, 6, 8, 10 (I would recommend 4 or 6 for speedy computation)

++++++++++++++++++++++++++++++++++
Example:

java ChessAI A.in black sorted 4

++++++++++++++++++++++++++++++++++


SOME NOTES: Ignore quotations when passing arguments as well as <, > 

Sorted will use a strategy of best score first while alpha-beta pruning

Reverse will do the reverse of above.

Random will alpha-beta prune in random order

If you would like to use custom input files make sure to remove them from nested directory.

(There are some unit test classes that we used to test along the way if you would like to take a look )