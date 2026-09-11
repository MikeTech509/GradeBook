/* public class Grade: keeps a single grade in the form of both score
(from 0 to 100) and letter grade (A, A-, . . . , F).*/

/*
 * Grading Scale
 * ----------------------------------
 * Letter Grade | Range (%)
 * ----------------------------------
 * A            | 95 - 100
 * A-           | 90 - 94.99
 * B+           | 87 - 89.99
 * B            | 83 - 86.99
 * B-           | 80 - 82.99
 * C+           | 77 - 79.99
 * C            | 70 - 76.99
 * D            | 60 - 69.99
 * F            | 0  - 59.99
 * ----------------------------------
 */

package util;

public class Grade {

    private int score;             // private field to hold score
    private String letterGrade;    // private field to hold letterGrade

    public Grade(int score){
        this.score = score;
        updatedLetterGrade();
    } // constructor

    public void setScore(int newScore){
        this.score = newScore;
        updatedLetterGrade();
    } // End of setScore();


    public int getScore() {

        return score;
    } // End of GetScore();

    public String getLetterGrade(){
        return letterGrade;

    } // end of getLetterGrade();

    public String updatedLetterGrade() {

        if (score >= 95) letterGrade = "A";
        else if(score >= 90 ) letterGrade = "A-";
        else if(score >= 87) letterGrade = "B+";
        else if(score >= 83) letterGrade = "B";
        else if(score >= 80) letterGrade = "B-";
        else if(score >= 77) letterGrade = "C+";
        else if(score >= 70) letterGrade = "C";
        else if(score >= 60) letterGrade = "D";
        else letterGrade = "F";

        return letterGrade;
    } // End of UpdatedLetterGrade();
}
