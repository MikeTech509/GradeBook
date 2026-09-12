package util;

/**
 * Represents a student's information: first name, last name,
 * a unique 7-digit PID, and their current Grade.
 */
public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;

    /**
     * Creates a Student with the given personal info and grade.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param pid       the student's unique 7-digit ID
     * @param grade     the student's Grade object
     */
    public Student(String firstName, String lastName, int pid, Grade grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pid = pid;
        this.grade = grade;
    }

    /**
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the student's 7-digit PID
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the new PID
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the student's Grade object
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * @param grade the new Grade object
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * @return the student's full name, formatted as "firstName lastName"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
