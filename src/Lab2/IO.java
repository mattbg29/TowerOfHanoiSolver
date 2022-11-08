/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  To take the information retrieved from UserInput and process the
 * recursive and iterative tower transformations, printing each step, the total
 * steps, and the length of time each takes
 **************************************************************************** */

package Lab2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IO {
    private final int maxTower;
    private final PrintWriter output;
    private PrintWriter summary = null;
    private final Path outputFile;
    private boolean printSummary = false;
    private final Scanner inputReader;
    private final double warningSize;

    IO(int maxTower, String output, Scanner inputReader, double warningSize) throws FileNotFoundException {
        this.maxTower = maxTower;
        this.output = new PrintWriter(output);
        outputFile = Paths.get(output);
        this.inputReader = inputReader;
        this.warningSize = warningSize;
    }

    IO(int maxTower, String output, Scanner inputReader, double warningSize, PrintWriter summary) throws FileNotFoundException {
        this(maxTower, output, inputReader, warningSize);
        this.summary = summary;
        printSummary = true;
    }

    // Here I iterate from 1 through the max tower size as provided by the input file,
    // issuing a warning with the expected output file size and transform process duration
    // of the next case if the current output file exceeds a user-defined warning threshold,
    // offering to let the user quit.
    void runTests() throws IOException {
        double outputTime = 0;

        outer:
        for (int i = 1; i <= maxTower; i++) {
            double outputSize = roundTwoDecimals(Files.size(outputFile) / 1000000.0);
            double outputTimeSecs = roundTwoDecimals(outputTime / 1000000000.0);

            if (outputSize > warningSize) {
                boolean choiceMade = false;
                System.out.println("Warning: after tower size " + (i - 1) + ", your output file size is " + outputSize + "MB " +
                        "\nand your most recent trial took " + outputTimeSecs + " seconds." +
                        "\nEach further trial will approximately double that size and take twice that duration.");
                while (!choiceMade) {
                    System.out.println("\nWould you like to continue? Enter 0 for no, 1 for yes.");
                    String choice = inputReader.nextLine();
                    if (choice.equals("0")) {
                        System.out.println("Trials ended");
                        break outer;
                    } else if (choice.equals("1")) {
                        choiceMade = true;
                    } else {
                        System.out.println("Invalid choice.  Please try again");
                    }
                }
            }

            output.println("\n------------------ Case: n = " + i + " ------------------");
            output.println("Steps using recursion:");
            TowerOfHanoi towerR = new TowerRecursion(output);
            towerR.transform(i);

            output.println("\nSteps using iteration:");
            TowerIteration towerI = new TowerIteration(output);
            towerI.transform(i);
            //towerI.printFinalTower(); // useful for debugging
            output.println();

            String outputMsg = "When n = " + i + ", Recursion | Iteration total time in " +
                    "nanoseconds: " + towerR.getTotalTime() + " |" + towerI.getTotalTime();
            output.println(outputMsg);

            double expectedNumSteps = Math.pow(2, i) - 1;

            output.println("Total number of steps, Recursion | Iteration | Expected: "
                    + String.format("%.0f", towerR.getNumSteps())
                    + " | " + String.format("%.0f", towerI.getNumSteps())
                    + " | " + String.format("%.0f", expectedNumSteps));

            outputTime = towerI.getTotalTime() + towerI.getTotalTime();

            if (printSummary) {
                summary.println(outputMsg);
            }
        }
        output.close();
        if (printSummary) {
            summary.close();
        }
    }

    // Rounds a double to two decimal places
    double roundTwoDecimals(double input) {
        return Math.round(input * 100) / 100.0;
    }
}




