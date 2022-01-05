package connectDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author stelios
 */
public class connectDB {
    
    private static final String URL = "jdbc:mysql://localhost";
    private static final String DATABASE = "ccc_base";
    private static final int PORT = 3306;
    private static final String UNAME = "root"; 
    private static final String PASSWD = "";
    
    
    public static Connection connectDB() throws ClassNotFoundException, SQLException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL + ":" + PORT + "/" + DATABASE, UNAME, PASSWD);
    }

    public Connection connectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL + ":" + PORT + "/" + DATABASE, UNAME, PASSWD);
    }
    
}
