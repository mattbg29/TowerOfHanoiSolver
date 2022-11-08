/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  An abstract superclass that provides the framework for the
 * recursive and iterative transformation classes
 **************************************************************************** */


package Lab2;

import java.io.PrintWriter;

public abstract class TowerOfHanoi {
    protected long totalTime;
    protected PrintWriter writer;
    private double numSteps;

    TowerOfHanoi(PrintWriter writer) {
        this.writer = writer;
    }

    // Prints the steps
    protected void printSteps(int disk, char start, char end) {
        writer.println("Move disk " + disk + " from tower " + start + " to tower " + end);
        numSteps++;
    }

    abstract void transform(int size);

    long getTotalTime() {
        return totalTime;
    }

    double getNumSteps() {
        return numSteps;
    }
}
