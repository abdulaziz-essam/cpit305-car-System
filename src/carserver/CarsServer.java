
package carserver;

import java.io.IOException;
import java.io.*;

import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.*;
//import carpac.*;
import carpac.*;
public class CarsServer {

    
    public static void main(String[] args) throws IOException {
ServerSocket serverSocket=new ServerSocket(2000);
// Connection con = DriverManager.getConnection("jdbc:mysql:// sql10.freemysqlhosting.net:3306/sql10403387?useSSL=false", "sql10403387", "VMBa5edvm1");
        while (true) {            
            Socket socket=serverSocket.accept();
            new client_thread(socket).start();
            
        }
    }
    
}
