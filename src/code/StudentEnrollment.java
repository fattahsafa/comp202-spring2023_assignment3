package code;

import java.util.List;

import given.StudentInfo;

/*
 * A StudentEnrolment class that should:
 * - Be efficiently (O(log n)) searchable by student name
 * - Be efficiently (O(log n)) searchable by student number
 * - Be searchable by e-mail (can be O(n)) 
 * 
 * The StudentEnrolment assumes that names and numbers will be unique
 * 
 * You need to use your own data structures to implement this
 * 
 * 
 */
public class StudentEnrollment {

  /*
   * ADD MORE FIELDS IF NEEDED
   * 
   */

  public StudentEnrollment() {
    /*
     * TO BE IMPLEMENTED
     * 
     */
  }

  // Returns the number of students in the Student Enrollment system
  public int size() {
    return 0;
  }

  // Returns true if the Student Enrollment system is empty, false otherwise
  public boolean isEmpty() {
    return false;
  }

  //Adds a new student or overwrites an existing student with the given info
  // Args should be given in the order of e-mail and address which is handled for
  // you
  // Note that it can also be empty. If you do not want to update a field pass
  // null
  public void addStudent(String name, String number, String... args) {
    int numArgs = args.length;
    String email = null;
    String address = null;

    /*
     * Add stuff here if needed
     */

    if (numArgs > 0)
      if (args[0] != null)
        email = args[0];
    if (numArgs > 1)
      if (args[1] != null)
        address = args[1];
    if (numArgs > 2)
      System.out.println("Ignoring extra arguments");

    /*
     * TO BE IMPLEMENTED
     * 
     */
  }

  // Return the student info with the given name
  // Return null if it does not exists
  // Should be O(log n)!
  public StudentInfo searchByName(String name) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;
  }

  // Return the student info with the given student number
  // Return null if it does not exists
  // Should be O(log n)!
  public StudentInfo searchByStudentNumber(String studentNumber) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;

  }

  // Return the student info with the given e-mail
  // Return null if it does not exists
  // Can be O(n)
  public StudentInfo searchByEmail(String email) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;

  }

  // Removes the student with the given name
  // Returns true if there is a student with the given name to delete, false otherwise
  public boolean removeByName(String name) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return false;
  }

  // Removes the student with the given name
  // Returns true if there is a student with the given number to delete, false otherwise
  public boolean removeByStudentNumber(String studentNumber) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return false;
  }

  // Returns the number associated with the name
  public String getStudentNumber(String name) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;
  }

  // Returns the name associated with the number
  public String getName(String number) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;
  }
  
  // Update the email of the student with the given name
  // Returns true if there is a student with the given name to update, false otherwise
  public boolean updateEmail(String name, String email) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return false;
  }
  
  // Update the address of the student with the given name
  // Returns true if there is a student with the given name to update, false otherwise
  public boolean updateAddress(String name, String address) {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return false;
  }

  // Returns a list containing the students in sorted order by name
  public List<StudentInfo> getStudents() {
    /*
     * TO BE IMPLEMENTED
     * 
     */
    return null;
  }

  // Prints the students in sorted order by name
  public void printStudents() {
    /*
     * TO BE IMPLEMENTED
     * 
     */
  }
}
