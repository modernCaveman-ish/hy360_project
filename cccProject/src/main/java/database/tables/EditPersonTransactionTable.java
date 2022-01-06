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
import mainClasses.PersonTransaction;


/**
 *
 * @author stelios
 */
public class EditPersonTransactionTable {
    connectDB connect = new connectDB();
    
    public void insertPersonTransaction(PersonTransaction pt) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "INSERT INTO `person_transaction`(`iban_person`, `iban_seller`, `type`, `cost`, `tr_date`) VALUES "
                    + "('" + pt.getIban_person() + "','" + pt.getIban_seller() + "','" + pt.getType() + "','" + pt.getCost() + "','" + pt.getTr_date() + "')";
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
    
    public Seller getPersonTransaction(String iban){
        try {
            Seller seller = new Seller();
            
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + iban + "'";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                seller.setUpFromResultSet(rs);
            }
            
            stm.close();
            con.close();
            
            return seller;
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        // error
        return null;
    }
    
    
    public void updateSeller(Seller seller) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "UPDATE `seller` SET `iban`='" + seller.getIban() + "',`name`='" + seller.getName() 
                    + "',`debt`='" + seller.getDebt() + "',`promithia`='" + seller.getPromithia() + "',`profit`='" 
                    + seller.getProfit() + "' WHERE iban = '" + seller.getIban() + "'";
            
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
    
    public void deleteSeller(String iban) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "DELETE FROM `seller` WHERE iban = '" + iban +"' ";
            
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
