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
import mainClasses.CompanyTransaction;
import mainClasses.Seller;

/**
 *
 * @author stelios
 */
public class EditCompanyTransactionTable {
    connectDB connect = new connectDB();
    
    public void insertCompanyTransaction(CompanyTransaction ct) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
//            String query = "INSERT INTO `seller`(`iban`, `name`, `debt`, `promithia`, `profit`) VALUES "
//                    + "('" + seller.getIban() + "','" + seller.getName() + "','" + seller.getDebt() + "','" + seller.getPromithia() + "','" + seller.getProfit() + "')";
            
            String query = "INSERT INTO `company_transaction`(`iban_company`, `w_id`, `iban_seller`, `type`, `cost`, `tr_date`) VALUES','" + ct.getIban_company() + "','" 
                    + ct.getW_id() + "','" + ct.getIban_seller() + "','" + ct.getType() + "','" + ct.getTr_date() + "')";
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
    
    public Seller getSeller(String iban){
        try {
            Seller seller = new Seller();
            
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "SELECT `id`, `iban_company`, `w_id`, `iban_seller`, `type`, `cost`, `tr_date` FROM `company_transaction` WHERE 1";
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
