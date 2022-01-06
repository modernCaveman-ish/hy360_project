/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import connectDB.connectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Person;
import mainClasses.Seller;
import mainClasses.Worker;

/**
 *
 * @author stelios
 */

 // NEEDS TESTING
public class EditWorkerTable {
    connectDB connect = new connectDB();
    
    public void insertWorker(Worker worker) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();

            String query = "INSERT INTO `worker`(`iban_company`, `id`, `name`) VALUES ('" + worker.getIban_company() + "','" + worker.getId() + "','" + worker.getName() + "')";
            System.out.println(query);
            
            stm.execute(query);
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Worker getWorker(String id, String iban_company){
        try {
            Worker worker = new Worker();
            
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            // String query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + iban + "'";
            String query = "SELECT `iban_company`, `id`, `name` FROM `worker` WHERE iban_company = '" + iban_company + "' AND id = '" + id + "'";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                worker.setUpFromResultSet(rs);
            }
            
            stm.close();
            con.close();
            
            return worker;
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        // error
        return null;
    }
    
    
    public void updateWorker(Worker worker) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            // String query = "UPDATE `seller` SET `iban`='" + seller.getIban() + "',`name`='" + seller.getName() 
            //         + "',`debt`='" + seller.getDebt() + "',`promithia`='" + seller.getPromithia() + "',`profit`='" 
            //         + seller.getProfit() + "' WHERE iban = '" + seller.getIban() + "'";
            
            String query = "UPDATE `worker` SET `iban_company`='" + worker.getIban_company() + "',`id`='" + worker.getId() + "',`name`='" + worker.getName() 
            + "' WHERE iban_company = '" + worker.getIban_company() + "' AND id='" + worker.getId() + "'";

            System.out.println("Edit PersonTable this is the update query: " + query);
            
            stm.execute(query);
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteWorker(String iban_company, String id) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            // String query = "DELETE FROM `seller` WHERE iban = '" + iban +"' "
            String query = "DELETE FROM `worker` WHERE iban_company = '" + iban_company + "' AND id='" + id + "'";
            
            stm.execute(query);
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
