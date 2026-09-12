/**
 * Manages a collection of Student objects, stored internally as
 * java.util.ArrayList<Student>. Supports adding students, looking
 * them up by PID, updating grades, and computing statistics
 * (min, max, average, median) across the whole class.
 */
package util;

import java.util.*;

public class GradeBook {
	private ArrayList<Student> listOfStudents;

	/**
	 * Creates an empty gradebook.
	 */
	public GradeBook(){
		listOfStudents = new ArrayList<>();
	}

	/**
	 * Adds a new student to the gradebook.
	 *
	 * @param student the student to add
	 */
	public void addStudent(Student student){
		listOfStudents.add(student);
	}

	/**
	 * Searches for a student by PID.
	 *
	 * @param pid the PID to search for
	 * @return the matching student, or null if not found
	 */
	public Student findByPid(int pid){
		for(Student student: listOfStudents){
			if(student.getPid() == pid){
				return student;
			}
		}
		return null;
	}

	/**
	 * Updates the score of the student with the given PID.
	 * Prints an error message if no student with that PID exists.
	 *
	 * @param pid the PID of the student to update
	 * @param newScore the new score to assign
	 */
	public void changeGrade(int pid, int newScore) {
		Student student = findByPid(pid);
		if (student == null) {
			System.out.println("Student not found.");
		} else {
			student.getGrade().setScore(newScore);
		}
	}

	/**
	 * @return true if the gradebook has no students
	 */
	public boolean isEmpty(){
		return listOfStudents.isEmpty();
	}

	/**
	 * @return the lowest score among all students
	 */
	public int calcMinScore(){
		int min = listOfStudents.get(0).getGrade().getScore();
		for(Student student: listOfStudents){
			if(student.getGrade().getScore() < min){
				min = student.getGrade().getScore();
			}
		}
		return min;
	}

	/**
	 * @return the highest score among all students
	 */
	public int calcMaxScore(){
		int max = listOfStudents.get(0).getGrade().getScore();
		for(Student student: listOfStudents){
			if(student.getGrade().getScore() > max){
				max = student.getGrade().getScore();
			}
		}
		return max;
	}

	/**
	 * @return the letter grade corresponding to the minimum score
	 */
	public String calcMinLetter(){
		Grade minLetter = new Grade(calcMinScore());
		return minLetter.getLetterGrade();
	}

	/**
	 * @return the letter grade corresponding to the maximum score
	 */
	public String calcMaxLetter(){
		Grade maxLetter = new Grade(calcMaxScore());
		return maxLetter.getLetterGrade();
	}

	/**
	 * @return the average score across all students
	 */
	public double calculateAvg() {
		double sum = 0;
		for(Student s: listOfStudents)
			sum += s.getGrade().getScore();
		return sum / listOfStudents.size();
	}

	/**
	 * @return the median score across all students (averages the
	 *         two middle scores if there is an even number of students)
	 */
	public float calculateMedian() {
		int i = 0, n = listOfStudents.size();
		int[] scores = new int[n];
		for(Student s: listOfStudents)
			scores[i++] = s.getGrade().getScore();
		Arrays.sort(scores);
		if (n % 2 == 0)
			return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
		else
			return scores[n / 2];
	}

	/**
	 * @return the letter grade corresponding to the (rounded) median score
	 */
	public String calculateMedianLetter() {
		Grade roundCalcMedian = new Grade(Math.round(calculateMedian()));
		return roundCalcMedian.getLetterGrade();
	}

	/**
	 * @return the letter grade corresponding to the (rounded) average score
	 */
	public String calcAvgLetter(){
		Grade avgLetter = new Grade((int) Math.round(calculateAvg()));
		return avgLetter.getLetterGrade();
	}

	/**
	 * Prints a tab-separated table of all students showing
	 * first name, last name, PID, and numeric score.
	 */
	public void printTabScores() {
		System.out.println("First Name\tLast Name\tPID\tScore");
		for (Student s : listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%d%n",
					s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
		}
	}

	/**
	 * Prints a tab-separated table of all students showing
	 * first name, last name, PID, and letter grade.
	 */
	public void printTabLetters() {
		System.out.println("First Name\tLast Name\tPID\tLetter Grade");
		for (Student s : listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%s%n",
					s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
		}
	}
}
