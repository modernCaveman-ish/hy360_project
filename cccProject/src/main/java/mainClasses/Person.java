/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

import database.tables.EditPersonTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stelios
 */
public class Person extends User{
    String balance;
    String debt;
    String debt_limit;
    String exp_date;
    String iban;
    String name;

    public Person() {
    }
    
    public Person(Person person) {
        this.balance = person.getBalance();
        this.debt = person.getDebt();
        this.debt_limit = person.getDebt_limit();
        this.exp_date = person.getExp_date();
        this.iban = person.getIban();
        this.name = person.getName();
    }
    
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getDebt_limit() {
        return debt_limit;
    }

    public void setDebt_limit(String debt_limit) {
        this.debt_limit = debt_limit;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String date) {
        this.exp_date = date;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpPerson(HttpServletRequest req) {
        
            String exp_date_string = req.getParameter("exp_date");

            this.setIban(req.getParameter("iban"));
            this.setName(req.getParameter("name"));
            this.setBalance(req.getParameter("balance"));
            this.setDebt(req.getParameter("debt"));
            this.setDebt_limit(req.getParameter("debt_limit"));
            // special way for date
            this.setExp_date(exp_date_string);
    }

    public void setUpFromResultSet(ResultSet rs) {
        try {
            this.iban = rs.getString(1);
            this.name = rs.getString(2);
            this.balance = rs.getString(3);
            this.exp_date = rs.getString(4);
            this.debt_limit = rs.getString(5);
            this.debt = rs.getString(6);
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void print(){
        System.out.println("IBAN: " + this.iban + ", name: " + this.name + ", balance: " 
                + this.balance + ", exp_date: " + this.exp_date + ", debt_limit: " 
                + this.debt_limit + ", debt: " + this.debt);
    }
    
    public String toString() {
        String info = "IBAN: " + this.iban + ", name: " + this.name + ", balance: " 
                + this.balance + ", exp_date: " + this.exp_date + ", debt_limit: " 
                + this.debt_limit + ", debt: " + this.debt;
        
        return info;
    }
    
    public String getString(){
        String info = "IBAN: " + this.iban + ", name: " + this.name + ", balance: " 
                + this.balance + ", exp_date: " + this.exp_date + ", debt_limit: " 
                + this.debt_limit + ", debt: " + this.debt;
        
        return info;
    }
    
    
    public boolean payDebt() {
        Integer balance = Integer.parseInt(this.getBalance());
        Integer debt    = Integer.parseInt(this.getDebt());
        EditPersonTable ept = new EditPersonTable();
        
//        System.out.println("We are in Person.payDebt()");
        
        if( balance == 0 ){
            System.out.println("Error person.payDebt(), balance is 0");
            return false;
        } else if ( balance > debt ) {
            balance = balance - debt;
            debt = 0;
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            ept.updatePerson(this);
            return true;
        } else if( debt > balance ){
            debt = debt - balance;
            balance = 0;
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            ept.updatePerson(this);
            return true;
        }
        return false;
    }
    
    public boolean payDebt(int cost) {
        Integer balance = Integer.parseInt(this.getBalance());
        Integer debt    = Integer.parseInt(this.getDebt());
        EditPersonTable ept = new EditPersonTable();
        
        balance = balance - cost;
        debt = debt - cost;
        
        if( balance < 0 ) {
            System.out.println("Error at person.payDebt(), not enough balance for this action, abort...");
            return false;
        } else if ( debt < 0 ) {
            System.out.println("Error at Person.payDebt(), invalid values debt will get a negative value, abort...");
            return false;
        } else if( debt == 0 ) {
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            System.out.println("Success Person.payDebt(), debt got paid!!");
            ept.updatePerson(this);
            return true;
        } else if( debt > 0 ) {
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            System.out.println("Success Person.payDebt(), part of debt got paid!! Remaining debt now is " + this.getDebt());
            ept.updatePerson(this);
            return true;
        }
        
        return false;
    }
}
