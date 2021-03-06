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

/**
 *
 * @author stelios
 */
public class EditPersonTable {
    
    connectDB connect = new connectDB();
    
    //Create Classes to make everything easier
    public void updatePerson(Person person) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "UPDATE `person` SET `iban`='" + person.getIban() + "',`name`='" + person.getName() 
                    + "',`balance`='" + person.getBalance() + "',`exp_date`='" + person.getExp_date() + "',`debt_limit`='" 
                    + person.getDebt_limit() + "',`debt`='" + person.getDebt() + "' WHERE iban='" + person.getIban() + "'";
            
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
    
    //TODO create delete insert person for the editPersonTable CRUD: CREATE, READ, UPDATE, DELETE
    
    public void deletePerson(String iban){
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "DELETE FROM `person` WHERE iban = '" + iban +"' ";
            
            stm.execute(query);
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertPerson(Person person) {
        try {
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "INSERT INTO `person`(`iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt`) VALUES "
                    + "('" + person.getIban() + "','" + person.getName() + "','" + person.getBalance() + "','" + person.getExp_date() + "','" + person.getDebt_limit() + "','" + person.getDebt() + "')";
            
            stm.execute(query);
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Person getPerson(String iban) {
        try {
            Person person = new Person();
            
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "SELECT `iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt` FROM `person` WHERE iban = '" + iban + "'";
            
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                person.setUpFromResultSet(rs);
            }
            
            stm.close();
            con.close();
            
            return person;
            
        } catch (SQLException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPersonTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        // error
        return null;
    }
}
