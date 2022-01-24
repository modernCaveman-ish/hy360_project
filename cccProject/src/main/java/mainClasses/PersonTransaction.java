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
public class PersonTransaction {
    String id, iban_person, iban_seller = "";
    enum Type { PISTWSH, XREWSH  };
    String cost = "";
    String tr_date = "";
    public Type type = Type.PISTWSH;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void setType(String type){
        if( type == "PISTWSH"){
            this.type = Type.PISTWSH;
        } else if( type == "XREWSH") {
            this.type = Type.XREWSH;
        }
    }
        
    public String getIban_person() {
        return iban_person;
    }

    public void setIban_person(String iban_person) {
        this.iban_person = iban_person;
    }

    public String getIban_seller() {
        return iban_seller;
    }

    public void setIban_seller(String iban_seller) {
        this.iban_seller = iban_seller;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTr_date() {
        return tr_date;
    }

    public void setTr_date(String tr_date) {
        this.tr_date = tr_date;
    }
    
    public void setUp(HttpServletRequest req){
        this.iban_person = req.getParameter("iban_person");
        this.iban_seller = req.getParameter("iban_seller");
        this.cost = req.getParameter("cost");
        this.tr_date = req.getParameter("tr_date");
        // now set the transactionType
        String tr_type = req.getParameter("transactionType");
        switch(tr_type) {
            case "PISTWSH":
                this.type = Type.PISTWSH;
                break;
            case "XREWSH": 
                this.type = Type.XREWSH;
                break;    
        }
    }
    
    
    public void setUpFromResultSet(ResultSet rs) {
        try {
            this.iban_person = rs.getString("iban_person");
            this.iban_seller = rs.getString("iban_seller");
            this.cost = rs.getString("cost");
            this.tr_date = rs.getString("tr_date");
            this.setType(rs.getString("type"));
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public String getString(){
        String info = "iban_person: " + this.iban_person + ", iban_seller: " + this.iban_seller +
                        ", cost: " + this.cost + ", tr_date: " + this.tr_date + ", type: " + this.getType().toString();
        return info;
    }
}
