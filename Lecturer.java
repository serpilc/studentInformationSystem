
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class Lecturer extends User {
    Lecturer(int ID,String _name,String _surname){
        super(ID,_name,_surname); // calling the super class constructor with ID,name and surname attributes
        lecturerMenu();
    }
    void lecturerMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select your choice from the menu.\n1.Messages\n2.Enter absenteeism\n3.Enter grades\n4.Check lesson\n5.Logout");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("1.Read unreaded messages\n2.Write message\n3.Back to top menu");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        readMessage();
                        break;
                    case 2:
                        System.out.println("Enter the ID of the person that you want to write message");
                        int toID = input.nextInt();
                        System.out.println("Enter the message that you want to send");
                        input.nextLine();
                        String message = input.nextLine();
                        writeMessage(message, toID);
                        break;
                    case 3:
                        lecturerMenu();
                        break;
                }
                break;
            case 2:
                enterAbsenteeism();
                break;
            case 3:
                enterGrades();
                break;
            case 4:
                checkLesson();
                break;
            case 5:
                logOut();
                break;
        }
    }
    void enterAbsenteeism()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the lesson ID");
        int lessonID =input.nextInt();
        System.out.println("Enter the student ID who was absent");
        int studentID=input.nextInt();
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO absenteeism (lessonID,studentID) VALUES (?,?)");
            ps.setInt(1,lessonID);
            ps.setInt(2,studentID);
            ps.executeUpdate();
            System.out.println("You succesfully entered the absenteeism");

        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------\n");
        lecturerMenu();

    }
    void enterGrades(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the lesson ID");
        int lessonID =input.nextInt();
        System.out.println("Enter the student ID");
        int studentID=input.nextInt();
        System.out.println("Enter the grade");
        int grade=input.nextInt();
        try {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query. inserting grade for specific lesson ID and student ID
            PreparedStatement ps = conn.prepareStatement("INSERT INTO grades (lessonID,studentID,grade) VALUES (?,?,?)");
            ps.setInt(1,lessonID);
            ps.setInt(2,studentID);
            ps.setInt(3,grade);
            ps.executeUpdate();
            ps = conn.prepareStatement("INSERT INTO students_lessons(lessonID,studentID) VALUES (?,?)");
            ps.setInt(1,lessonID);
            ps.setInt(2,studentID);
            ps.executeUpdate();
            System.out.println("You succesfully entered the grade for " + studentID );

        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------\n");
        lecturerMenu();
    }
    void checkLesson(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the ID of the lesson");
        int lessonID = input.nextInt();
        try {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query. lesson name and code
            PreparedStatement ps = conn.prepareStatement("SELECT name,code FROM lessons WHERE ID=?");
            ps.setInt(1,lessonID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String lessonName = rs.getString("name");
            String lessonCode = rs.getString("code");
            // SQl query. student count for specific lesson
            ps = conn.prepareStatement("SELECT COUNT(DISTINCT studentID) as studentCount FROM students_lessons WHERE lessonID=?");
            ps.setInt(1,lessonID);
            rs = ps.executeQuery();
            rs.next();
            int totalStudents = rs.getInt("studentCount");
            // SQl query. sum of grades / count of students = average
            ps = conn.prepareStatement("SELECT SUM(grade)/COUNT(studentID) as average FROM grades WHERE lessonID=?");
            ps.setInt(1,lessonID);
            rs = ps.executeQuery();
            rs.next();
            double average = rs.getDouble("average");
            System.out.println("\nLesson ID : " + lessonID +
                    "\n Lesson name : " + lessonName +
                    "\n Lesson code :" + lessonCode +
                    "\n Total number of students that takes that course : " + totalStudents +
                    "\n Average of the lesson : " + average);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------\n");
        lecturerMenu();
    }
}
