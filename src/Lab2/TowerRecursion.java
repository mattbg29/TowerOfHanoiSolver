/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  To print the steps of the solution to the Tower of Hanoi using recursion
 * and time how long the solution takes to print.
 **************************************************************************** */

package Lab2;

import java.io.PrintWriter;

public class TowerRecursion extends TowerOfHanoi {

    TowerRecursion(PrintWriter writer) {
        super(writer);
    }

    public void transform(int size) {
        long startTime = System.nanoTime();
        runRecursion(size, 'A', 'C', 'B');
        long endTime = System.nanoTime();
        totalTime = endTime - startTime;
    }

    // The process of printing the steps recursively requires no data structure,
    // as each step follows the last with a simple increment or decrement of the size
    // of the given tower, starting with the input size, and a swap of the
    // given start, middle, and/or end tower.
    private void runRecursion(int size, char start, char mid, char end) {
        if (size == 1) {
            printSteps(1, start, end);
        } else {
            runRecursion(--size, start, end, mid);
            printSteps(++size, start, end);
            runRecursion(--size, mid, start, end);
        }
    }


}

