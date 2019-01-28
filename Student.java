
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class Student extends User{
Student(int ID,String _name,String _surname){
    super(ID,_name,_surname); // calling the super class constructor with ID,name and surname attributes
    studentMenu();
}
void studentMenu()
{
    Scanner input = new Scanner(System.in);
    System.out.println("Select your choice from the menu.\n1.Messages\n2.View grades\n3.Check absenteeism\n4.Request document\n5.Logout");
    int choice=input.nextInt();
    switch(choice) {
        case 1: System.out.println("1.Read unreaded messages\n2.Write message\n3.Back to top menu");
        choice=input.nextInt();
            switch (choice) {
                case 1: readMessage();break;
                case 2:
                System.out.println("Enter the ID of the person that you want to write message");
                int toID = input.nextInt();
                System.out.println("Enter the message that you want to send");
                input.nextLine();
                String message = input.nextLine();
                writeMessage(message, toID);
                break;
                case 3:studentMenu();break;
            }
        break;
        case 2: viewGrades();break;
        case 3: checkAbsenteeism();break;
        case 4: requestDoc();break;
        case 5: logOut();break;
    }
}
void checkAbsenteeism()
{
    try {
        // creating mysql database connection
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/student_information";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

        // SQl query.
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(studentID) as absentCount,lessons.code as lessonCode FROM absenteeism INNER JOIN lessons ON lessons.ID = absenteeism.lessonID WHERE studentID=? GROUP BY lessonID ");
        ps.setInt(1,getID());
        ResultSet rs =ps.executeQuery();
        while (rs.next()) // iterating through result set
        {
            int absentCount = rs.getInt("absentCount");
            String lessonCode = rs.getString("lessonCode");
            System.out.println(lessonCode + " :" + absentCount);

        }

    }
    catch (Exception e)
    {
        System.err.println("Got an exception! ");
        System.err.println(e.getMessage());
    }
    System.out.println("---------------------\n");
    studentMenu();
}
void viewGrades(){
    try {
        // creating mysql database connection
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/student_information";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

        // SQl query. grade and lesson name for student
        PreparedStatement ps = conn.prepareStatement("SELECT grade,lessons.name as lessonName FROM grades INNER JOIN lessons ON grades.lessonID=lessons.ID WHERE studentID=? ");
        ps.setInt(1,getID());
        ResultSet rs =ps.executeQuery();
        System.out.println("Grades : ");
        while (rs.next())
        {
            int grade = rs.getInt("grade");
            String lessonName = rs.getString("lessonName");
            System.out.println(lessonName+ " : " + grade);

        }
        System.out.println("---------------------\n");

    }
    catch (Exception e)
    {
        System.err.println("Got an exception! ");
        System.err.println(e.getMessage());
    }
    studentMenu();
}
void requestDoc(){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the document number that you want to request\n1.Student document\n2.Intern document ");
    int choice = input.nextInt();
    switch (choice){
        case 1:
            try {
                // creating mysql database connection
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/student_information";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

                // SQl query. inserting document request to the database
                PreparedStatement ps = conn.prepareStatement("INSERT INTO documents (studentID,doc) VALUES (?,?)");
                ps.setInt(1,getID());
                ps.setString(2,"studentDocument");
                ps.executeUpdate();
                System.out.println("Your request is delivered.");
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            break;
        case 2:
            try {
                // creating mysql database connection
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/student_information";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

                // SQl query. inserting document request to database
                PreparedStatement ps = conn.prepareStatement("INSERT INTO documents (studentID,doc) VALUES (?,?)");
                ps.setInt(1,getID());
                ps.setString(2,"internDocument");
                ps.executeUpdate();
                System.out.println("Your request is delivered.");
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            break;
    }
    System.out.println("---------------------\n");
    studentMenu();

}

}
