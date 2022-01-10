/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stelios
 */
public class Seller {
    String iban;
    String name;
    String debt;
    String promithia;
    String profit;

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

    public String getPromithia() {
        return promithia;
    }

    public void setPromithia(String promithia) {
        this.promithia = promithia;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
    
    // setUp seller from a given ResultSet
    public void setUpFromResultSet(ResultSet rs) {
        try {
            this.iban = rs.getString(1);
            this.name = rs.getString(2);
            this.debt = rs.getString(3);
            this.promithia = rs.getString(4);
            this.profit = rs.getString(5);
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
        
        //print all function
        public void print(){
        System.out.println("IBAN: " + this.iban + ", name: " + this.name + 
                ", debt: " + this.debt + ", promithia:" + this.promithia +
                ", profit:" + this.profit);
    }


         public void setUpFromRequest(HttpServletRequest req) {
        
        this.setIban(req.getParameter("iban"));
        this.setName(req.getParameter("name"));
        this.setDebt(req.getParameter("debt"));
        this.setPromithia(req.getParameter("promithia"));
        this.setProfit(req.getParameter("profit"));
    }
         
    public float calculateProfit(int cost){
        float new_profit = 0;
        Integer int_cost = new Integer(cost);
        float promithia = Float.parseFloat(this.getPromithia());
        float float_cost = int_cost.floatValue();
        
        new_profit = cost - (float_cost * promithia);
        System.out.println("calculateProfit: " + new_profit);
        
        return new_profit;
    }
}
