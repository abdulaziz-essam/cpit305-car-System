
package carserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import carpac.*;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class client_thread extends Thread{
Socket socket;
    String email;
    String password;
    DB db ;
    Res res;
      Connection con ;


  
   
    public client_thread(Socket socket) {
   this.socket=socket;
   
    }

    
    @Override
    public void run() {super.run();
        while (true) {            
    
        try {
             
            String command="";
       InputStream is=socket.getInputStream();
       OutputStream os=socket.getOutputStream();
       Scanner input=new Scanner(is);   
       PrintWriter pw=new PrintWriter(os,true);
            String line="aaaa";
            ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
   db=new DB();
           User user =null ;
            while (true) {                
             command=input.nextLine();
                     switch (command) {
                    case "login":
                          email=input.nextLine();
                        
                           password=input.nextLine();
                        
                     user=  db.log_In_Info(email,password);
                      
                    obj.writeObject((User)user);
                       
                        break;
                
                  case "update":
                try{           
                    email=input.nextLine();
//                       System.out.println(user.toString());
                           password=input.nextLine();
                                                                        
                   
                           db.update(password, email, user.getId());
                   } catch(Exception e){
                       System.out.println(e.getMessage());
                   }
                 
String date="2020/4/";
date+="3";

                     
                        break;
//                        2020-02-02";
                  case "addres":
                      
                      int reserveID = (int) (Math.random() * 100000);
                int  carId=Integer.parseInt(input.nextLine());
                String startDate=input.nextLine();
                           
                String endDate=input.nextLine();
                 String sDate="2021/4/"+ startDate;
                 int numberOfDays=Integer.parseInt(input.nextLine());
                 int end=Integer.parseInt(startDate)+numberOfDays;
                   String eDate="2021/4/"+ end;
                 if(end>30){
                     end=end-30;
                     eDate="2021/5/"+ end;
                 }
               
//                      System.out.println(eDate);
                Res.insert_res(db.getCon(),reserveID,  sDate,eDate+"",user.getId(), carId);
             int total=0;  
            db.updateCar(carId);
          ArrayList <Car>carP=Car.storeCars(db.getCon());
          for(Car c:carP){
              if(carId==c.getCarID()){
                total=  (int) (c.getPrice()*numberOfDays);
              }
              
          }
          pw.println(total+"");
                        break;
                        
            }
            }
           
//               email=  input.nextLine();
//            System.out.println("email: "+email);
//            password=input.nextLine();
//            System.out.println("password: "+password);

         
//            db.user_info("azoz.comggazoz.com", "2");
            
} catch (IOException ex) {
            Logger.getLogger(client_thread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(client_thread.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
}

}


