/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  To gather the following user input via the console: input file name
 * and the input on said file, output file name, output file summary name
 * (if selected), and warning threshold (if selected).
 **************************************************************************** */

package Lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserInput {
    Scanner inputReader;

    UserInput(Scanner inputReader) {
        this.inputReader = inputReader;
    }

    // Here I get the max tower from the input file.  If the system cannot find
    // the file given, the user will be asked to try again.  If the file is found,
    // this method will also check to ensure the input is correct, ie it is has on
    // its first line a single positive number.  Any input on future lines will be
    // disregarded, and any other input on the first line will result in an
    // error message and the program will end.
    int getInputNum() {
        File checkFile;
        String inputFile;

        boolean repeatCheck = false;
        int maxTower = 0;
        do {
            if (repeatCheck) {
                System.out.println("\nUnable to find file.  Please try again.\n");
            }
            System.out.println("\nPlease enter the input file source: ");
            inputFile = inputReader.nextLine();
            checkFile = new File(inputFile);
            if (checkFile.exists()) {
                try {
                    BufferedReader input = new BufferedReader(new FileReader(inputFile));
                    maxTower = Integer.parseInt(input.readLine());
                    input.close();
                } catch (IOException e) {
                    System.out.println("\nError reading input file");
                } catch (NumberFormatException e) {
                    System.out.println("\nBad input in file.  Input must be a single number on the first line. " +
                            "\nPlease review the file and try again.");
                    return 0;
                }
            }
            repeatCheck = true;
        } while (!checkFile.exists());
        if (maxTower <= 0) {
            System.out.println("\nInput file should be a single number on the first line greater than 0." +
                    "\nPlease check your file for correct entry and try again");
        }
        return maxTower;
    }

    // Retrieve the output file
    String getOutputFile() {
        System.out.println("\nPlease enter the output file source: ");
        return inputReader.nextLine();
    }

    // Retrieve the output summary file, if the user requests one
    String getOutputFileSummary() {
        System.out.println("\nPlease enter the output summary file source, or" +
                "\nleave blank for no summary output");
        String outputFileSummary = inputReader.nextLine();

        if (outputFileSummary.length() == 0) {
            outputFileSummary = "";
        }
        return outputFileSummary;
    }

    // Retrieve the output file size cutoff point, if the user requests one
    double getWarningSize(int maxTower) {
        if (maxTower > 10) {
            System.out.println("With a max tower size greater than 10, it is recommended that you " +
                    "\nset an output file size warning point, to keep this process from " +
                    "\nrunning into memory issues on your machine.  You will receive a warning " +
                    "\nafter each iteration that breaches this size, at which point you will be" +
                    "\nasked whether you want to continue.");
            while (true) {
                System.out.println("\nWould you like to set a warning size point?  Please enter in integer Megabytes." +
                        "\nLeave blank to default to 1000MB.  Enter 0 to omit warnings.");
                String warningSizeString = inputReader.nextLine();
                if (warningSizeString.length() == 0) {
                    return 1000;
                }
                try {
                    int warningSize = Integer.parseInt(warningSizeString);
                    if (warningSize == 0) {
                        return Double.POSITIVE_INFINITY;
                    }
                    return warningSize;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry.  Please try again.");
                }
            }
        }
        return 1000;
    }
}
