package util;

import java.util.Scanner;

/* 4. VALIDATION - for each non-"DONE" input line, split into 4 tokens
 *    (firstName, lastName, PID, grade) and validate:
 *
 *      firstName:
 *        - single word, alphabetic characters only
 *        - starts with a capital letter
 *        - no whitespace
 *
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
 *    collection (e.g. ArrayList<Student>).*/

public class InputValidation {


    public static String[] inputValidationStudentLine(Scanner meow){

            while(true){
                String line = meow.nextLine().trim();

                if (line.equals("DONE")) {
                    return null; // signal: no more students
                }

                String[] tokens = line.split("\\s+");

            if(tokens.length != 4){
                System.out.println("Invalid input. Please enter exactly 4 values: "
                        + "firstName lastName PID grade. Please try again:");
                continue;
            }

            String firstName = tokens[0];
            String lastName = tokens[1];
            String pid = tokens [2];
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

            // all 4 fields passed -> return them together
            return tokens;
        }  // End of While Loop
    } // End of inputValidationStudentLine method

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

    } // End of isValidFistName();


    public static boolean isValidLastName(String validLastName){
        if(validLastName == null || validLastName.trim().isEmpty()){
            return false;
        }

        if(!Character.isUpperCase(validLastName.charAt(0))){
            return false;
        }
        int dotCount = 0;  // count of dot character
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
        }  // end of for Loop

        return true;

    } // End of isValidLastName();


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
        } // End of for Loop
        return true;

    } // End of isValidPID method;


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

}  // End of InputValidation Class
