/**
 * Represents a single grade, consisting of a numeric score (0-100)
 * and its corresponding letter grade (A, A-, ..., F). The letter
 * grade is automatically recalculated whenever the score changes,
 * so the two values can never fall out of sync.
 *
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

    private int score;
    private String letterGrade;

    /**
     * Creates a Grade with the given score and immediately computes
     * its corresponding letter grade.
     *
     * @param score the numeric score, expected to be between 0 and 100
     */
    public Grade(int score){
        this.score = score;
        updatedLetterGrade();
    }

    /**
     * Updates the score and recalculates the letter grade to match.
     *
     * @param newScore the new numeric score to assign
     */
    public void setScore(int newScore){
        this.score = newScore;
        updatedLetterGrade();
    }

    /**
     * @return the current numeric score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the letter grade corresponding to the current score
     */
    public String getLetterGrade(){
        return letterGrade;
    }

    /**
     * Recalculates the letter grade based on the current score,
     * using the standard grading scale. Called automatically by
     * the constructor and by setScore() so the letter grade is
     * always kept in sync with the score.
     *
     * @return the newly computed letter grade
     */
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
    }
}
