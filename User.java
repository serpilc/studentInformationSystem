import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User { // main class
    private int ID;
    private String name;
    private String surname;

    User(){}
    User(int _ID,String _name,String _surname){ // parametirezed constructor
        ID=_ID;
        name=_name;
        surname=_surname;

    }
    void readMessage()
    {
        try {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query. counting unread messages where reciever is fromID
            PreparedStatement ps = conn.prepareStatement("SELECT message,fromID,COUNT(message) as okunmamis FROM messages WHERE toID=? AND isRead=0");
            ps.setInt(1,getID());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                if (rs.getInt("okunmamis") == 0) // checking if there is unread messages
                {
                    System.out.println("You dont have any unread messages.");

                }
                else {
                    String message = rs.getString("message");
                    int fromID = rs.getInt("fromID");
                    System.out.println("Message : " + message + "\n From : " + fromID);
                    ps = conn.prepareStatement("UPDATE messages SET isRead=1 WHERE message=? AND fromID=? AND toID=?");
                    ps.setString(1, message); // if there is a unread message after reading it change the isRead to 1
                    ps.setInt(2, fromID);
                    ps.setInt(3, getID());
                    ps.executeUpdate();
                }
            }

        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    int getID()
    {
        return ID;
    } // returns the ID
    void writeMessage(String message,int ID){
        try {
            // creating mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/student_information";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "mysql");

            // SQl query. inserting message sender = fromID reciever = toID
            PreparedStatement ps = conn.prepareStatement("INSERT INTO messages (fromID,toID,message) VALUES (?,?,?)");
            ps.setInt(1,getID());
            ps.setInt(2,ID);
            ps.setString(3,message);
            ps.executeUpdate();
            System.out.println("Your message succesfully sent.");
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
    void logOut(){
        System.out.println("You have succesfully logged out.");
        return;
    }
}
