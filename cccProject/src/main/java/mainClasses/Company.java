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
import javax.servlet.http.HttpServletRequest;


public class Company {
    
    String iban;
    String name;
    String debt;
    String debt_limit;//einai credit_limit
    String balance;
    String exp_date;

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
   public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public void setUpFromRequest(HttpServletRequest req) {
        
        this.setIban(req.getParameter("iban"));
        this.setName(req.getParameter("name"));
        this.setDebt(req.getParameter("debt"));
        this.setDebt_limit(req.getParameter("debt_limit"));
        this.setBalance(req.getParameter("balance"));
        // special way for date
        this.setExp_date(req.getParameter("exp_date"));
    }

    // setUp company from a given ResultSet
    public void setUpFromResultSet(ResultSet rs) {
        try {
            this.iban = rs.getString(1);
            this.name = rs.getString(2);
            this.debt = rs.getString(3);
            this.debt_limit = rs.getString(4);
            this.balance = rs.getString(5);
            this.exp_date = rs.getString(6);
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

}
