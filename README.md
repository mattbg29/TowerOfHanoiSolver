# TowerOfHanoiSolver
Program overview: When running this program, the user will be asked for an input
file that contains one input on the first line: a positive integer.  If the first
line contains anything other than this, or contains more than one integer, the user
will get an error message and the program will stop.  In addition, anything written
beyond the first line will be ignored.

The number on the first line of the input file represents the maximum tower that
the program will attempt to solve for.  The program will solve for every tower
size n from 1 through the max tower size both recursively and iteratively, outputting
the set of steps for each and the total number of steps and duration on the file
chosen by the user.  The program will also generate a summary page with just the
times of each version, if the user elects for this.  If the target max size is
in excess of 10, the user will be offered the chance to set a warning threshold on
the size of the output file which, when breached, will offer the user the ability to
end the process early.

In the output file, the user will see the recursive and iterative steps taken to solve
the puzzle, as well as information on how long each solution took (in nanoseconds),
how many total steps were involved in each, and how many total steps were expected.
The expected total number of steps is 2^n - 1.
