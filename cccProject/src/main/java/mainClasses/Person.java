/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

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
public class Person {
    String balance;
    String debt;
    String debt_limit;
    String exp_date;
    String iban;
    String name;
    
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
    
}
