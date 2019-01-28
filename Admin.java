import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin extends User {
    Admin(int ID,String _name,String _surname){
        super(ID,_name,_surname); // calling the super class constructor with ID,name and surname attributes
        adminMenu();
    }
    void adminMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Select your choice from the menu.\n1.Messages\n2.Create lesson");
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
                    case 3:adminMenu();break;
                }
                break;
            case 2: createLesson();break;
        }
    }
    void createLesson()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter code of the lesson");
        String code = input.next();
        System.out.println("Enter the name of the lesson");
        input.nextLine();
        String lessonName = input.nextLine();
        System.out.println(lessonName);
        try {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query. inserting lesson name and code to the database
            PreparedStatement ps = conn.prepareStatement("INSERT INTO lessons (name,code) VALUES (?,?)");
            ps.setString(1,lessonName);
            ps.setString(2,code);
            ps.executeUpdate();
            System.out.println("Lesson succesfully created.");
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------\n");
        adminMenu();
    }

}
