/* keeps a list of students info in the following data structure:
java.util.ArrayList⟨Student⟩.*/
package util;

import java.util.*;

public class GradeBook {
    private ArrayList<Student> listOfStudents;

	public GradeBook(){
		listOfStudents = new ArrayList<>();
	} // End of GradeBook();

	public void addStudent(Student student){
		listOfStudents.add(student);
	} // End of addStudent();

	public Student findByPid(int pid){
		for(Student student: listOfStudents){
			if(student.getPid() == pid){
				return student;
			}
		}
		return null;
	} // End of findByPid();

	public void changeGrade(int pid, int newScore) {
		Student s = findByPid(pid);
		if (s == null) {
			System.out.println("Student not found.");
		} else {
			s.getGrade().setScore(newScore);
		}
	} // End changeGrade();

	public boolean isEmpty(){
		return listOfStudents.isEmpty();

	} // End of isEmpty();

	public int calcMinScore(){
		int min = listOfStudents.get(0).getGrade().getScore();
		for(Student student: listOfStudents){
			if(student.getGrade().getScore() < min){
				min = student.getGrade().getScore();
			}
		}
		return min;
	} // End of calcMinimum

	public int calcMaxScore(){
		int max = listOfStudents.get(0).getGrade().getScore();
		for(Student student: listOfStudents){
			if(student.getGrade().getScore() > max){
				max = student.getGrade().getScore();
			}
		}
		return max;
	}  // End of calcMaximum

public String calcMinLetter(){
		Grade minLetter = new Grade(calcMinScore());

		return minLetter.getLetterGrade();

} // End of calcMinLetter();

public String calcMaxLetter(){
		Grade maxLetter = new Grade(calcMaxScore());
		return maxLetter.getLetterGrade();

} // End of calcMaxLetter();

    public double calculateAvg() {
	double sum = 0;
	for(Student s: listOfStudents)
	    sum += s.getGrade().getScore();
	return sum / listOfStudents.size();
    }
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

	public String calculateMedianLetter() {
		Grade roundCalcMedian = new Grade(Math.round(calculateMedian()));
		return roundCalcMedian.getLetterGrade();
	} // End of CalculateMedianLetter

	public String calcAvgLetter(){
		Grade avgLetter = new Grade((int) Math.round(calculateAvg()));
		return avgLetter.getLetterGrade();
	} // End of calcAvgLetter();


	public void printTabScores() {
		System.out.println("First Name\tLast Name\tPID\tScore");
		for (Student s : listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%d%n",
					s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
		}
	}

	public void printTabLetters() {
		System.out.println("First Name\tLast Name\tPID\tLetter Grade");
		for (Student s : listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%s%n",
					s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
		}
	}
}
