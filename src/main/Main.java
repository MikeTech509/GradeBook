/* contains the main method which does the followings: first it gets
user’s input data from System.in verifies the data to make sure there is no problem
with the input data. Then, it asks for user’s commands and gets them via System.in.
Finally, it processes each command, and outputs the results to System.out */

/*
 * ============================================================
 * GRADEBOOK PROGRAM - DESIGN GUIDE
 * ============================================================
 *
 * ---------------------------------------------------------
 * PHASE 1: INPUT HANDLING
 * ---------------------------------------------------------
 * 1. Print welcome message:
 *      "Welcome to my grade book!"
 *      "Please enter the information of the first student using the
 *       following format: "firstName lastName PID grade"."
 *      "Press Enter when you are done."
 *
 * 2. Read first student's line of input.
 *
 * 3. Loop (while not "DONE"):
 *      - Print:
 *          "Please enter the information of the next student using
 *           the same format."
 *          "If there is no more students, please enter the keyword "DONE"."
 *          "Press Enter when you are done."
 *      - Read next line of input.
 *      - If input equals "DONE" (exact match), exit loop.
 *
 * 4. VALIDATION - for each non-"DONE" input line, split into 4 tokens
 *    (firstName, lastName, PID, grade) and validate:
 *
 *      firstName:
 *        - single word, alphabetic characters only
 *        - starts with a capital letter
 *        - no whitespace
 *
 *      lastName:
 *        - single word, alphabetic characters only
 *        - starts with a capital letter
 *        - no whitespace
 *        - may contain AT MOST one '.' character
 *
 *      PID:
 *        - exactly 7 digits
 *        - no leading zero (first digit != '0')
 *        - must parse as an integer
 *
 *      grade:
 *        - non-negative integer
 *        - must not exceed 100
 *
 *    If ANY field fails validation (or token count is wrong),
 *    do NOT add the student. Print an error / re-prompt and let the
 *    while loop ask again (do not advance to next student).
 *
 * 5. On success, create a Student object and store it in a
 *    collection (e.g. ArrayList<Student>).
 *
 * ---------------------------------------------------------
 * PHASE 2: COMMAND HANDLING
 * ---------------------------------------------------------
 * After "DONE" is entered, loop:
 *      - Print "Please enter a new command"
 *      - Read command line, split into tokens
 *      - Match against supported commands below
 *      - "quit" ends the loop and the program
 *
 * SUPPORTED COMMANDS:
 *
 *   min score
 *      -> find minimum numeric grade among all students, print it
 *
 *   min letter
 *      -> find student(s) with minimum score, convert to letter grade, print it
 *
 *   max score
 *      -> find maximum numeric grade among all students, print it
 *
 *   max letter
 *      -> find student(s) with maximum score, convert to letter grade, print it
 *
 *   letter XXXXXXX
 *      -> look up student by PID, print their letter grade
 *      -> handle "PID not found" case
 *
 *   name XXXXXXX
 *      -> look up student by PID, print "firstName lastName"
 *      -> handle "PID not found" case
 *
 *   change XXXXXXX YY
 *      -> look up student by PID, validate YY as a legal grade (0-100),
 *         update that student's score via setScore()
 *      -> handle "PID not found" case
 *
 *   average score
 *      -> compute (sum of all grades) / (number of students), print it
 *         (decide: print as double, round, or truncate? be consistent)
 *
 *   average letter
 *      -> take the average score above, convert to a letter grade, print it
 *
 *   median score
 *      -> sort a COPY of scores, find middle value
 *         (average the two middle values if count is even), print it
 *
 *   median letter
 *      -> take the median score above, convert to letter grade, print it
 *
 *   tab scores
 *      -> print header: "First Name\tLast Name\tPID\tScore"
 *      -> print one row per student, tab-separated
 *
 *   tab letters
 *      -> print header: "First Name\tLast Name\tPID\tLetter Grade"
 *      -> print one row per student, tab-separated (score converted to letter)
 *
 *   quit
 *      -> stop the command loop, end the program
 *
 * ---------------------------------------------------------
 * SUGGESTED CLASSES / METHODS
 * ---------------------------------------------------------
 *   class Student:
 *      - private fields: firstName, lastName, pid, score
 *      - getters/setters for each field
 *      - a method like getLetterGrade() that converts score -> letter
 *        using the grading scale (A, A-, B+, B, B-, C+, C, D, F)
 *
 *   class GradeBook (or all handled in Main):
 *      - ArrayList<Student> students
 *      - methods: addStudent(), findByPid(), minScore(), maxScore(),
 *        averageScore(), medianScore(), printTabScores(), printTabLetters()
 *      - a static/shared method scoreToLetter(double score) so grading
 *        logic isn't duplicated across min/max/average/median/letter commands
 *
 *   input validation:
 *      - consider regex for each field, e.g.
 *          firstName/lastName: ^[A-Z][a-zA-Z.]*$  (then separately enforce max one dot)
 *          PID:                ^[1-9][0-9]{6}$
 *          grade:              integer parse + range check 0-100
 * ============================================================
 */

package main;

import util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner meow = new Scanner(System.in);
        GradeBook gradebook = new GradeBook();

        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("\"firstName lastName PID grade\".");
        System.out.println("Press Enter when you are done.");

        while (true) {
            String[] tokens = InputValidation.inputValidationStudentLine(meow);
            if (tokens == null) break; // "DONE" was entered

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
