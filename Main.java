/*
    SE115 Introduction to programming
    TERM PROJECT
    Student Information System
    Mustafa Kutay Yavuz 20120601057
    Sinem Çakır 20150601056
    Serpil Civan 20150601010


 */
import java.util.Scanner;
import java.sql.*;
public class Main {


    public static void main(String[] args) {


        System.out.println("Welcome to the students system.\n 1.Login\n 2.Register \n 3.Quit"); // main menu

    Scanner input = new Scanner(System.in);
    int deger = input.nextInt();
    if(deger ==1) {
        System.out.println("Enter the ID"); // login id
        int ID=input.nextInt();
        System.out.println("Enter the password"); // login password
        String password=input.next();
        //mysql connection starts
        try
        {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query.
            PreparedStatement ps = conn.prepareStatement("SELECT name,surname,ID,accountType FROM accounts WHERE id=? AND password=?");
            ps.setInt(1,ID);
            ps.setString(2,password);
            ResultSet rs =ps.executeQuery();

            // iterate through the java resultset
            boolean query=rs.next();
            if (query) {
                    ID= rs.getInt("ID");
                    String firstName = rs.getString("name");
                    String lastName = rs.getString("surname");
                    String accountType = rs.getString("accountType");


                    // print the results
                    System.out.println("Welcome " + firstName + " " + lastName + " !\n");
                ps = conn.prepareStatement("SELECT COUNT(message) as sayac FROM messages WHERE toID=? AND isRead=0");
                ps.setInt(1,ID);
                rs =ps.executeQuery();
                rs.next();
                int notRead= rs.getInt("sayac");
                if (notRead != 0)
                    System.out.println("You have " +notRead +" unread messages.");
                else
                    System.out.println("You dont have any unread messages.");

                    if (accountType.equals("student")) {
                        Student asd = new Student(ID, firstName, lastName); // creating object as accountType
                    }
                    else if (accountType.equals("lecturer"))
                    {
                        Lecturer asd = new Lecturer(ID,firstName,lastName); // creating object as accountType
                    }
                    else {
                        Admin asd = new Admin(ID, firstName, lastName); // creating object as accountType
                    }


                }
            else
                System.out.println("Wrong ID or password");






            ps.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }
    else if (deger == 2 ) { // register
        try {
            // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");
            System.out.println("Enter your name");
            String name = input.next();
            System.out.println("Enter your surname");
            String surname = input.next();
            System.out.println("Enter your password");
            String password = input.next();
            // our SQL query.
            PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts (accountType,password,name,surname) VALUES (?,?,?,?)");
            ps.setString(1, "student");
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, surname);
            ps.executeUpdate();
            System.out.println("Your account succesfully created.");
            ps = conn.prepareStatement("SELECT last_insert_id() as last_id FROM accounts "); // getting last inserted id to show user login id
            ResultSet rs = ps.executeQuery();
            rs.next();
            String lastid = rs.getString("last_id");
            System.out.println("You can use your password and id to login to the system.\nYour id is : " + lastid);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    else {
        System.out.println("Terminating the program");
        return;
    }
}



}
