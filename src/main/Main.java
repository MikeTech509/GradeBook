/**
 * Entry point for the Gradebook program. Handles two phases:
 *
 * Phase 1 - Input Handling: reads and validates student records
 * (firstName lastName PID grade) from the console until the user
 * enters "DONE".
 *
 * Phase 2 - Command Handling: repeatedly reads commands from the
 * console (e.g. "min score", "letter 1234567", "change 1234567 85")
 * and prints the corresponding result, until the user enters "quit".
 */
package main;

import util.*;
import java.util.Scanner;

public class Main {

    /**
     * Runs the gradebook program.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner meow = new Scanner(System.in);
        GradeBook gradebook = new GradeBook();

        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("\"firstName lastName PID grade\".");
        System.out.println("Press Enter when you are done.");

        while (true) {
            String[] tokens = InputValidation.inputValidationStudentLine(meow);
            if (tokens == null) break;

            int pid = Integer.parseInt(tokens[2]);
            int score = Integer.parseInt(tokens[3]);
            gradebook.addStudent(new Student(tokens[0], tokens[1], pid, new Grade(score)));

            System.out.println("Please enter the information of the next student using the same format.");
            System.out.println("If there is no more students, please enter the keyword \"DONE\".");
            System.out.println("Press Enter when you are done.");
        }

        boolean running = true;
        while (running) {
            System.out.println("Please enter a new command from the list below:");
            System.out.println("  min score                - minimum score");
            System.out.println("  min letter               - minimum letter grade");
            System.out.println("  max score                - maximum score");
            System.out.println("  max letter               - maximum letter grade");
            System.out.println("  average score            - average score");
            System.out.println("  average letter           - average letter grade");
            System.out.println("  median score             - median score");
            System.out.println("  median letter            - median letter grade");
            System.out.println("  letter <PID>             - command letter with given PID to get the letter grade");
            System.out.println("  name <PID>               - command name of student with given PID to get full name");
            System.out.println("  change <PID> <newGrade>  - command change with given PID and newGrade to change grade");
            System.out.println("  tab scores               - table of all students' scores");
            System.out.println("  tab letters              - table of all students' letter grades");
            System.out.println("  quit                     - exit the program");

            String[] command = meow.nextLine().trim().split("\\s+");
            String key = command[0] + (command.length > 1 ? " " + command[1] : "");

            switch (key) {
                case "min score":
                    System.out.println(gradebook.calcMinScore());
                    break;
                case "min letter":
                    System.out.println(gradebook.calcMinLetter());
                    break;
                case "max score":
                    System.out.println(gradebook.calcMaxScore());
                    break;
                case "max letter":
                    System.out.println(gradebook.calcMaxLetter());
                    break;
                case "average score":
                    System.out.println(gradebook.calculateAvg());
                    break;
                case "average letter":
                    System.out.println(gradebook.calcAvgLetter());
                    break;
                case "median score":
                    System.out.println(gradebook.calculateMedian());
                    break;
                case "median letter":
                    System.out.println(gradebook.calculateMedianLetter());
                    break;
                case "tab scores":
                    gradebook.printTabScores();
                    break;
                case "tab letters":
                    gradebook.printTabLetters();
                    break;

                // "letter <PID>", "name <PID>", "change <PID> <newGrade>", and "quit"
                // all take a variable argument after the command word, so they can't
                // be matched as fixed case labels and are handled here instead.
                default:
                    if (command[0].equals("letter")) {
                        Student oneStudent = gradebook.findByPid(Integer.parseInt(command[1]));
                        System.out.println(oneStudent == null ? "Student not found." : oneStudent.getGrade().getLetterGrade());
                    } else if (command[0].equals("name")) {
                        Student oneStudent = gradebook.findByPid(Integer.parseInt(command[1]));
                        System.out.println(oneStudent == null ? "Student not found." : oneStudent.getFullName());
                    } else if (command[0].equals("change")) {
                        gradebook.changeGrade(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    } else if (command[0].equals("quit")) {
                        running = false;
                    } else {
                        System.out.println("Unknown command.");
                    }
            }
        }
        meow.close();
    }
}