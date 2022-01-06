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
public class CompanyTransaction {
    String id, iban_company, w_id, iban_seller;
    enum Type { PISTWSH, XREWSH, PLHRWMI, EPISTROFI };
    Type type = Type.PISTWSH; // default
    String cost, tr_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIban_company() {
        return iban_company;
    }

    public void setIban_company(String iban_company) {
        this.iban_company = iban_company;
    }

    public String getW_id() {
        return w_id;
    }

    public void setW_id(String w_id) {
        this.w_id = w_id;
    }

    public String getIban_seller() {
        return iban_seller;
    }

    public void setIban_seller(String iban_seller) {
        this.iban_seller = iban_seller;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(Type type) {
        this.type = type;
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
    
    // setUp seller from a given ResultSet
    public void setUpFromResultSet(ResultSet rs) {
        try {
            this.id = rs.getString("id");
            this.iban_company = rs.getString("iban_company");
            this.w_id = rs.getString("w_id");
            this.iban_seller = rs.getString("iban_seller");
            this.cost = rs.getString("cost");
            this.tr_date = rs.getString("tr_date");
            //Thelei prosoxi
            String tr_type = rs.getString("type");
            switch(tr_type) {
            case "PISTWSH":
                this.type = Type.PISTWSH;
                break;
            case "XREWSH": 
                this.type = Type.XREWSH;
                break;
            case "PLHRWMI": 
                this.type = Type.PLHRWMI;
                break;
            case "EPISTROFI": 
                this.type = Type.EPISTROFI;
                break;    
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void setUpFromServletRequest(HttpServletRequest req){
        this.iban_company = req.getParameter("iban_company");
        this.iban_seller = req.getParameter("iban_seller");
        this.id = req.getParameter("id");
        this.w_id = req.getParameter("w_id");
        this.cost = req.getParameter("cost");
        this.tr_date = req.getParameter("tr_date");
        // thelei prosoxi
        String tr_type = req.getParameter("type");
        switch(tr_type) {
            case "PISTWSH":
                this.type = Type.PISTWSH;
                break;
            case "XREWSH": 
                this.type = Type.XREWSH;
                break;
            case "PLHRWMI": 
                this.type = Type.PLHRWMI;
                break;
            case "EPISTROFI": 
                this.type = Type.EPISTROFI;
                break;    
        }
    }
}
