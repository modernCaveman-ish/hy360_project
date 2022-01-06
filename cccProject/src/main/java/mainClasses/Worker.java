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


 //Needs TESTING
public class Worker {
    String iban_company;
    String id;
    String name;

    public String getIban_company() {
        return iban_company;
    }

    public void setIban_company(String iban_company) {
        this.iban_company = iban_company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Set up Worker from request
    public void setUpFromRequest(HttpServletRequest req) {
        this.iban_company = req.getParameter("iban_company");
        this.id = req.getParameter("id");
        this.name = req.getParameter("name");
    }
    
    public void setUpFromResultSet(ResultSet rs) {    
        try {
            this.iban_company = rs.getString("iban_company");
            this.id = rs.getString("id");
            this.name = rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void print(){
        System.out.println("iban_company: " + this.iban_company + ", id: " + this.id + ", name: " + this.name);
    }
}
