package given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import code.StudentEnrollment;

public class GradeStudentEnrollment {
  public static void main(String arg[]) throws IOException {
    String fileNameDatabase = "database.txt";
    String fileNameQuery = "query.txt";
    StudentEnrollment studentEnrollment = new StudentEnrollment();
    PrintStream previous = System.out; 

    File output = new File("student_enrollment_output.txt");
    output.createNewFile();
    PrintStream fileOut = new PrintStream(output);
    System.setOut(fileOut);

    
    try {
      BufferedReader bufferReaderDatabase = new BufferedReader(new FileReader(fileNameDatabase));
      BufferedReader bufferReaderQuery = new BufferedReader(new FileReader(fileNameQuery));
      String line, line2;

      while ((line = bufferReaderDatabase.readLine()) != null) {
        String[] linesplit = line.split("\\s+");
        studentEnrollment.addStudent(linesplit[0].concat(linesplit[1]), linesplit[2]);
      }
      bufferReaderDatabase.close();

      System.out.println("Number of students: " + studentEnrollment.size());
      studentEnrollment.printStudents();
      System.out.println();

      System.out.println("Applying the operations from the query list");
      while ((line2 = bufferReaderQuery.readLine()) != null) {

        String[] linesplit = line2.split("\\s+");
        if (linesplit[0].equalsIgnoreCase("insert")) {
          if (linesplit.length == 4)
            studentEnrollment.addStudent(linesplit[1].concat(linesplit[2]), linesplit[3]);
          else if (linesplit.length == 5) {
            studentEnrollment.addStudent(linesplit[1].concat(linesplit[2]), linesplit[3], linesplit[4]);
          } else if (linesplit.length > 5) {
            StringBuilder address = new StringBuilder(100);
            for (int i = 5; i < linesplit.length; i++) {
              address.append(linesplit[i] + " ");
            }
            studentEnrollment.addStudent(linesplit[1].concat(linesplit[2]), linesplit[3], linesplit[4], address.toString());
          } else
            System.out.println("Wrong input query!");

        } else if (linesplit[0].equalsIgnoreCase("delete")) {
          if (linesplit[1].equalsIgnoreCase("name")) {
            if (!studentEnrollment.removeByName(linesplit[2].concat(linesplit[3])))
              System.out.println(
                  "Was not able to find the name, " + linesplit[2] + " " + linesplit[3] + ", in students to delete");
          }
            else if (linesplit[1].equalsIgnoreCase("number")) {
              if (!studentEnrollment.removeByStudentNumber(linesplit[2]))
                System.out.println("Was not able to find the number, " + linesplit[2] + ", in students to delete");
            }
        } else if (linesplit[0].equalsIgnoreCase("update")) {
          String name = linesplit[2].concat(linesplit[3]);
          if (linesplit[1].equalsIgnoreCase("email"))
            if (!studentEnrollment.updateEmail(name, linesplit[4]))
              System.out.println("Was not able to find the name, " + linesplit[2] + " " + linesplit[3]
                  + ", in students to update the e-mail");
            else if (linesplit[1].equalsIgnoreCase("address")) {
              StringBuilder address = new StringBuilder(100);
              for (int i = 5; i < linesplit.length; i++) {
                address.append(linesplit[i] + " ");
              }
              if (!studentEnrollment.updateAddress(name, address.toString()))
                System.out.println("Was not able to find the name, " + linesplit[2] + " " + linesplit[3]
                    + ", in students to update the address");
            }
        } else if (linesplit[0].equalsIgnoreCase("displayinfo")) {
          StudentInfo ci;
          if (linesplit[1].equalsIgnoreCase("name")) {
            String name = linesplit[2].concat(linesplit[3]);
            ci = studentEnrollment.searchByName(name);
            if (ci == null)
              System.out.println("Cannot find the student with name " + name);
            else
              System.out.println(ci.toDeepString());
          }
          if (linesplit[1].equalsIgnoreCase("number")) {
            ci = studentEnrollment.searchByStudentNumber(linesplit[2]);
            if (ci == null)
              System.out.println("Cannot find the student with number " + linesplit[2]);
            else
              System.out.println(ci.toDeepString());
          }
          if (linesplit[1].equalsIgnoreCase("email")) {
            ci = studentEnrollment.searchByEmail(linesplit[2]);
            if (ci == null)
              System.out.println("Cannot find the student with email " + linesplit[2]);
            else
              System.out.println(ci.toDeepString());
          }
        } else if (linesplit[0].equalsIgnoreCase("GetNumber")) {
          String number = studentEnrollment.getStudentNumber(linesplit[1].concat(linesplit[2]));
          if (number == null)
            System.out.println("Cannot find the student with name " + linesplit[1].concat(linesplit[2]));
          else
            System.out.println(linesplit[1] + linesplit[2] + ": " + number);
          System.out.println();
        } else if (linesplit[0].equalsIgnoreCase("GetName")) {
          String number = studentEnrollment.getName(linesplit[1]);
          if (number == null)
            System.out.println("Cannot find the student with number " + linesplit[1]);
          else
            System.out.println(linesplit[1] + ": " + number);
          System.out.println();
        }
      }

      bufferReaderQuery.close();
      System.out.println("Number of students after the queries: " + studentEnrollment.size());

      studentEnrollment.printStudents();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.setOut(previous);
  }
}
