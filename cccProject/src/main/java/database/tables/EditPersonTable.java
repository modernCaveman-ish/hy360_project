/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import connectDB.connectDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Person;

/**
 *
 * @author stelios
 */
public class EditPersonTable {
    
    connectDB connect = new connectDB();
    
    public void insertNewPerson(Person person) {
        try {
            Connection con = connect.connectionDB();
            Statement st = con.createStatement();
            
//            String query = "INSERT INTO `person` (`iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt`) "
//                    + "VALUES "
//                    + "('" + person.getIban() + "', "
//                    + "'" +  person.getName() + "', "
//                    + "'" +  person.getBalance() + "', "
//                    + "'" + person.getExp_date() + "', "
//                    + "'" + person.getDebt_limit() + "', "
//                    + "'" +  person.getDebt() +"')";
            
            //fix the date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(person.getExp_date());

            String query = "INSERT INTO `person` (`iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt`) "
                    + "VALUES "
                    + "('" + person.getIban() + "', "
                    + "'" +  person.getName() + "', "
                    + "'" +  person.getBalance() + "', "
                    + "'" + date /* person.getExp_date() */ + "', "
                    + "'" + person.getDebt_limit() + "', "
                    + "'" +  person.getDebt() +"')";
            
            System.out.println(query);
            
            st.executeQuery(query);
            st.close();
            System.out.println("Done!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void deletePerson(int iban) {
//        Connection con = connect.connectionDB();
//    }
}
