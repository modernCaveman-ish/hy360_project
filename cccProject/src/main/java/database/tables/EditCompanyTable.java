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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Person;
import mainClasses.Company;

/**
 *
 * @author stelios
 */

 // CRUD
public class EditCompanyTable {
    connectDB connect = new connectDB();

    public void insertCompany(Company company) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "INSERT INTO `company`(`iban`, `name`, `debt`, `debt_limit`, `balance`, `exp_date`) VALUES "
                    + "('" + company.getIban() + "','" + company.getName() + "','" + company.getDebt() + "','" + company.getDebt_limit() + "','" + company.getBalance() + "','" + company.getExp_date() + "')";
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

    public Company getCompany(String iban){
        try {
            Company company = new Company();
            
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            // String query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + iban + "'";
            String query = "SELECT `iban`, `name`, `debt`, `debt_limit`, `balance`, `exp_date` FROM `company` WHERE iban = '" + iban + "'";
            System.out.println(query);
            ResultSet rs = stm.executeQuery(query);
            
//            if( rs.next() == false ){
//                stm.close();
//                con.close();
//                System.out.println("Couldn't find Company with IBAN: " + iban);
//                return null;
//            }
            
//            rs.first();
            
            while (rs.next()) {
                company.setUpFromResultSet(rs);
            }
            
            stm.close();
            con.close();
            
            return company;
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        // error
        return null;
    }


    public void updateCompany(Company company) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "UPDATE `company` SET `iban`='" + company.getIban() + "',`name`='" + company.getName() 
                    + "',`debt`='" + company.getDebt() + "',`debt_limit`='" + company.getDebt_limit() + "',`balance`='" 
                    + company.getBalance() + "',`exp_date`='" + company.getExp_date() + "' WHERE iban='" + company.getIban() + "'";
            
            System.out.println("Edit CompanyTable this is the update query: " + query);
            
            stm.execute(query);
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCompany(String iban) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "DELETE FROM `company` WHERE iban = '" + iban +"' ";
            
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
