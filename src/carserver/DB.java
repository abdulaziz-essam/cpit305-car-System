
package carserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import carpac.*;
import java.util.ArrayList;


public class DB {
//    private String email;
//    private String password;
     Connection con ;

    public DB() throws SQLException {
//    con    = DriverManager.getConnection("jdbc:mysql:// sql10.freemysqlhosting.net:3306/sql10403387?useSSL=false", "sql10403387", "VMBa5edvm1");
  con    = DriverManager.getConnection("jdbc:mysql:// localhost:3306/carSystem?useSSL=false", "root", "");
        System.out.println("con complete ");
    }

    public Connection getCon() {
        return con;
    }
    

    public User log_In_Info(String email ,String password) throws SQLException{
       ArrayList<User>users= User.storeUSers(con);
        for(User u: users){
            if(u.getEmail().equalsIgnoreCase(email)&& u.getPassword().equalsIgnoreCase(password)){
            
                return u;
            }
            }
          
            return null;
        
     
    }
//        Connection con = DriverManager.getConnection("jdbc:mysql:// sql10.freemysqlhosting.net:3306/sql10403387?useSSL=false", "sql10403387", "VMBa5edvm1");
//
//        System.out.println("connection compeleted");
//      
//      Statement stat=con.createStatement();
//      stat.execute("create table if not exists aek (ID int primary key, password char(10) ,email char(30),carid int )");
////     stat.execute("create table if not exists az (ID int primary key, name char(20),password char(20) ,email char(20) ,carid char(20))");   
//      
//  String sql="insert into aek values (10,"+"'" + paaword+"',"+"'" + email+"'" +", 1234567)";
//  stat.execute(sql);
//          
////        System.out.println(sql); 
//// stat.execute("create table if not exists students (ID int primary key, name char(20) unique,GPA double default 4.5)");
////        //stat.execute("insert into students values (1,'Abdul Aziz Al buhiri', 4.6, 1234567)");
////        ResultSet rs;
////        //stat.execute("insert into students (name, st_no, id) values ('Faris Al Nahar', 7654321, 2)");
////        boolean x=stat.execute("insert into students values (3,'Abdullah Ibraheem', 4.7, 3216893)");
////        stat.executeUpdate(sql);

    
    public void user_info(String email,String Password) throws SQLException{
     
        System.out.println("connection compeleted");
      
      Statement stat=con.createStatement();
        String sql = "SELECT * FROM aek WHERE email = ? and password = ?";
        PreparedStatement statement = con.prepareStatement(sql);
//        statement.setString(1, email);
//        statement.setString(2, password);
 
        ResultSet result = statement.executeQuery();
        System.out.println("result    "+result.getCursorName());
                       
      
}
    
    public  void update(String pass,String email ,int id ) throws SQLException{
String q="UPDATE  user SET email= ?, password=? WHERE id= ?";
PreparedStatement pstat = con.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
pstat.setString(1, email);

            pstat.setString(2, pass);
         pstat.setInt(3, id);
         pstat.execute();
       
    }
        public void updateCar(int carID) throws SQLException{
String q="UPDATE  car SET avilable=? WHERE carid= ?";
PreparedStatement pstat = con.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
pstat.setBoolean(1, false);

pstat.setInt(2, carID);

         pstat.execute();
       
    }
    public void new_res(int start,int end,int userid ,int carId,double price) throws SQLException{
    

       
      
      Statement stat=con.createStatement();
      stat.execute("create table if not exists res2 (userID int , carID int ,start int,end int,price int )");
//     stat.execute("create table if not exists az (ID int primary key, name char(20),password char(20) ,email char(20) ,carid char(20))");   
      
  String sql="insert into res2 values ("+userid+","+carId+","+start+","+end+","+price+")";
  stat.execute(sql);
 
}


}
