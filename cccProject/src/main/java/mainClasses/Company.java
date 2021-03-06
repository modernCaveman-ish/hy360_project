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

import database.tables.EditCompanyTable;


public class Company extends User{
    
    String iban;
    String name;
    String debt;
    String debt_limit;//einai credit_limit
    String balance;
    String exp_date;

    public String getIban() {
        return iban;
    }

    public Company() {
    }

    
    
    public Company(Company comp) {
        this.iban = comp.getIban();
        this.name = comp.getName();
        this.debt = comp.getDebt();
        this.debt_limit = comp.getDebt_limit();
        this.balance = comp.getBalance();
        this.exp_date = comp.getExp_date();
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
    
    public String getString() {
        String info = "IBAN: " + this.getIban() + ", Debt: " + this.getDebt() 
                + ", Debt_limit: " + this.getDebt_limit() + ", Balance: " 
                + this.getBalance() + ", Exp_date: " + this.getExp_date();
        
        return info;
    }

    // Function gia agora
    public boolean agora(int cost){
        boolean done = false; //default
        Integer balance = Integer.parseInt(this.getBalance());
        EditCompanyTable ect = new EditCompanyTable();

        if( balance < cost ){
            System.out.println("Error Company.agora(), not enough balance for this action..");
            return false;
        } else {
            balance = balance - cost;
            this.setBalance(balance.toString());
            ect.updateCompany(this);
            return true;
        }
    }
    
    public void print(){
        System.out.println(this.toString());
    }
    
      public boolean epistrofi(int cost){
        boolean done = false; //default
        Integer balance = Integer.parseInt(this.getBalance());
        EditCompanyTable ect = new EditCompanyTable();

            balance = balance + cost;
            this.setBalance(balance.toString());
            ect.updateCompany(this);
            return true;    
    }
    
    public boolean payDebt() {

        Integer balance = Integer.parseInt(this.getBalance());
        Integer debt    = Integer.parseInt(this.getDebt());
        EditCompanyTable ect = new EditCompanyTable();
             
        if( balance == 0 ){
            System.out.println("Error company.payDebt(), balance is 0");
            return false;
        } else if ( balance > debt ) {
            balance = balance - debt;
            debt = 0;
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            ect.updateCompany(this);
            return true;
        } else if( debt > balance ){
            debt = debt - balance;
            balance = 0;
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            ect.updateCompany(this);
            return true;
        }
        return false;
    }
 public boolean payDebt(int cost) {

        Integer balance = Integer.parseInt(this.getBalance());
        Integer debt    = Integer.parseInt(this.getDebt());
        EditCompanyTable ect = new EditCompanyTable();
        
        balance = balance - cost;
        debt = debt - cost;
        
        if( balance < 0 ) {
            System.out.println("Error at company.payDebt(), not enough balance for this action, abort...");
            return false;
        } else if ( debt < 0 ) {
            System.out.println("Error at Company.payDebt(), invalid values debt will get a negative value, abort...");
            return false;
        } else if( debt == 0 ) {
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            System.out.println("Success Company.payDebt(), debt got paid!!");
            ect.updateCompany(this);
            return true;
        } else if( debt > 0 ) {
            this.setBalance(balance.toString());
            this.setDebt(debt.toString());
            System.out.println("Success Company.payDebt(), part of debt got paid!! Remaining debt now is " + this.getDebt());
            ect.updateCompany(this);
            return true;
        }
        
        return false;
    }
    
}
