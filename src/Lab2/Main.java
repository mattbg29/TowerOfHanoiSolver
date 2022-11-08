/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  To run the program.  In Main, I get user input via the UserInput class,
 * then generate output via the IO class.
 * See README for details about the overall program.
 **************************************************************************** */

package Lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        UserInput userInput = new UserInput(inputReader);
        int maxTower = userInput.getInputNum();

        // If the userInput returned a maxTower less than or equal to 0,
        // the input was invalid.  The UserInput class will have printed
        // an error message to the console, and the program will end here.
        if (maxTower > 0) {
            String outputFile = userInput.getOutputFile();
            String outputFileSummary = userInput.getOutputFileSummary();
            double warningSize = userInput.getWarningSize(maxTower);

            try {
                IO runTrials;
                if (outputFileSummary.length() == 0) {
                    runTrials = new IO(maxTower, outputFile, inputReader, warningSize);
                } else {
                    PrintWriter summary = new PrintWriter(outputFileSummary);
                    runTrials = new IO(maxTower, outputFile, inputReader, warningSize, summary);
                }
                runTrials.runTests();
                inputReader.close();

            } catch (IOException e) {
                System.out.println("Error writing to the output file(s).  Please check output file name(s) and try again");
            }
        }
    }
}
