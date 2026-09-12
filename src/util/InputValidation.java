package util;

import java.util.Scanner;

/**
 * Provides static methods for reading and validating student
 * information entered via the console. A student record must
 * consist of 4 whitespace-separated fields: firstName, lastName,
 * PID, and grade. Validation rules:
 *
 *   firstName - single word, letters only, starts with a capital
 *   lastName  - single word, letters only, starts with a capital,
 *               may contain at most one '.'
 *   PID       - exactly 7 digits, no leading zero
 *   grade     - integer between 0 and 100 (inclusive)
 *
 * If any field is invalid, an error is printed and the caller is
 * re-prompted; nothing is returned until valid input is entered
 * or the user types "DONE".
 */
public class InputValidation {

    /**
     * Reads student-record lines from the given Scanner, re-prompting
     * on any invalid input, until either a fully valid line is entered
     * or the user types "DONE".
     *
     * @param meow the Scanner to read input from
     * @return an array of 4 validated tokens (firstName, lastName,
     *         PID, grade), or null if the user entered "DONE"
     */
    public static String[] inputValidationStudentLine(Scanner meow){

        while(true){
            String line = meow.nextLine().trim();

            if (line.equals("DONE")) {
                return null;
            }

            String[] tokens = line.split("\\s+");

            if(tokens.length != 4){
                System.out.println("Invalid input. Please enter exactly 4 values: "
                        + "firstName lastName PID grade. Please try again:");
                continue;
            }

            String firstName = tokens[0];
            String lastName = tokens[1];
            String pid = tokens[2];
            String grade = tokens[3];

            if (!isValidFirstName(firstName)) {
                System.out.println("Invalid first name. It must start with a capital letter, "
                        + "contain only letters, and have no spaces. Please try again:");
                continue;
            }
            if (!isValidLastName(lastName)) {
                System.out.println("Invalid last name. It must start with a capital letter, "
                        + "contain only letters (at most one '.'), and have no spaces. Please try again:");
                continue;
            }
            if (!isValidPID(pid)) {
                System.out.println("Invalid PID. It must be exactly 7 digits with no leading zero. "
                        + "Please try again:");
                continue;
            }
            if (!isValidGrade(grade)) {
                System.out.println("Invalid grade. It must be a whole number from 0 to 100. "
                        + "Please try again:");
                continue;
            }

            return tokens;
        }
    }

    /**
     * Validates a first name: non-empty, starts with an uppercase
     * letter, and contains only alphabetic characters.
     *
     * @param validFirstName the string to validate
     * @return true if the string is a valid first name
     */
    public static boolean isValidFirstName(String validFirstName) {
        if (validFirstName == null || validFirstName.trim().isEmpty()) {
            return false;
        }

        if (!Character.isUpperCase(validFirstName.charAt(0))) {
            return false;
        }

        for (int i = 0; i < validFirstName.length(); i++) {
            if (!Character.isLetter(validFirstName.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates a last name: non-empty, starts with an uppercase
     * letter, contains only alphabetic characters, and allows at
     * most one '.' character.
     *
     * @param validLastName the string to validate
     * @return true if the string is a valid last name
     */
    public static boolean isValidLastName(String validLastName){
        if(validLastName == null || validLastName.trim().isEmpty()){
            return false;
        }

        if(!Character.isUpperCase(validLastName.charAt(0))){
            return false;
        }
        int dotCount = 0;
        for(int i = 0; i < validLastName.length(); i++){
            char dot = validLastName.charAt(i);
            if(dot == '.'){
                dotCount++;
                if(dotCount > 1){
                    return false;
                }
            } else if(!Character.isLetter(dot)){
                return false;
            }
        }

        return true;
    }

    /**
     * Validates a PID: exactly 7 digits long, with no leading zero.
     *
     * @param validPid the string to validate
     * @return true if the string is a valid PID
     */
    public static boolean isValidPID(String validPid){
        if(validPid == null || validPid.length() != 7){
            return false;
        }
        if(validPid.charAt(0) == '0'){
            return false;
        }

        for(int i = 0; i < validPid.length(); i++){
            if(!Character.isDigit(validPid.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Validates a grade: a non-negative integer that does not
     * exceed 100.
     *
     * @param validGrade the string to validate
     * @return true if the string is a valid grade
     */
    public static boolean isValidGrade(String validGrade){
        if(validGrade == null || validGrade.trim().isEmpty()){
            return false;
        }
        for(int i = 0; i < validGrade.length(); i++){
            if(!Character.isDigit(validGrade.charAt(i))){
                return false;
            }
        }
        int parsedValue = Integer.parseInt(validGrade);

        return parsedValue >=0 && parsedValue <= 100;
    }
}
