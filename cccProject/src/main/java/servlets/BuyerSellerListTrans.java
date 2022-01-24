/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainClasses.PersonTransaction;
/**
 *
 * @author stelios
 */
public class BuyerSellerListTrans extends HttpServlet {

    public void printToNewPage(ArrayList<String> answer) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet agoraPerson</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>Servlet BuyerSellerListTrans answer:\n"); 
            
            for(String str : answer){
                out.println(str);
            }
            
            out.println("</h5>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Im in BuyerSellerListTrans doPost!!!");
            
            String person_iban = request.getParameter("person_iban");
            String seller_iban = request.getParameter("seller_iban");
            String from_date   = request.getParameter("from_date");
            String to_date     = request.getParameter("to_date");
            ArrayList<String> answer = new ArrayList<String>();
            
            // Empty field checking
            if( person_iban.isEmpty() ){
                System.out.println("person_iban is empty");
            } else {
                System.out.println("person_iban: " + person_iban);
            }
            
            if( seller_iban.isEmpty() ){
                System.out.println("seller_iban is empty");
            } else {
                System.out.println("seller_iban: " + seller_iban);
            }
            
            if( from_date.isEmpty() ) {
                System.out.println("from_date is blank");
            } else {
                System.out.println("From-date: " + from_date);
            }
            
            if( to_date.isEmpty() ) {
                System.out.println("to_date is blank");
            } else {
                System.out.println("to-date: " + to_date);
            }
            
            //SELECT * FROM `person_transaction` WHERE `iban_person` = '7' AND tr_date >= '2022-01-12';
            connectDB connect = new connectDB();
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            //Cases
            
            // only person_iban is filled
            if( !person_iban.isEmpty() && seller_iban.isEmpty() && from_date.isEmpty() && to_date.isEmpty() ){
                String query = "SELECT * FROM `person_transaction` WHERE `iban_person` = '"+ person_iban +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
                
            } else if ( person_iban.isEmpty() && !seller_iban.isEmpty() && from_date.isEmpty() && to_date.isEmpty() ) {// Only Seller is filled 
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());

                    }
                    response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
                
            } else if ( person_iban.isEmpty() && seller_iban.isEmpty() && !from_date.isEmpty() && to_date.isEmpty() ) {// Only From_date is filled 
                String query = "SELECT * FROM `person_transaction` WHERE `tr_date` >= '"+ from_date +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }   else if ( person_iban.isEmpty() && seller_iban.isEmpty() && from_date.isEmpty() && !to_date.isEmpty() ) {// Only From_date is filled 
                String query = "SELECT * FROM `person_transaction` WHERE `tr_date` <= '"+ to_date +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                       out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }   else if ( !person_iban.isEmpty() && !seller_iban.isEmpty() && from_date.isEmpty() && to_date.isEmpty() ) {// PERSON AND SELLER ONLY FILLED
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"' AND `iban_person` = '"+ person_iban +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }         
            }   else if ( !person_iban.isEmpty() && !seller_iban.isEmpty() && !from_date.isEmpty() && to_date.isEmpty() ) {// ONLY TO DATE FILLED
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"' AND `iban_person` = '"+ person_iban +"' AND `tr_date` >= '"+ from_date +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }        
            }   else if ( !person_iban.isEmpty() && !seller_iban.isEmpty() && from_date.isEmpty() && !to_date.isEmpty() ) {// ONLY FROM_DATE FILLED 
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"' AND `iban_person` = '"+ person_iban +"' AND `tr_date` <= '"+ to_date +"'";
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }   else if ( !person_iban.isEmpty() && !seller_iban.isEmpty() && !from_date.isEmpty() && !to_date.isEmpty() ) {// ALL FILLED
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"' AND `iban_person` = '"+ person_iban +"' AND `tr_date`" +
                        "BETWEEN '" + from_date +"' AND '"+ to_date +"'";
                // SELECT * FROM `person_transaction` WHERE tr_date BETWEEN '2022-01-12' AND '2022-01-19'; 
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }  
            }   else if ( person_iban.isEmpty() && !seller_iban.isEmpty() && !from_date.isEmpty() && !to_date.isEmpty() ) {// SELLER FROM_DATE TO_DATE FILLED 
                String query = "SELECT * FROM `person_transaction` WHERE `iban_seller` = '"+ seller_iban +"' AND `tr_date`" +
                        "BETWEEN '" + from_date +"' AND '"+ to_date +"'";
                // SELECT * FROM `person_transaction` WHERE tr_date BETWEEN '2022-01-12' AND '2022-01-19'; 
                System.out.println("Query: " + query );
                PersonTransaction trans = new PersonTransaction();
                
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    trans.setUpFromResultSet(rs);
                    System.out.println(trans.getString());
                    answer.add(trans.getString());
       
                }
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet agoraPerson</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Servlet BuyerSellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }              
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BuyerSellerListTrans.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuyerSellerListTrans.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}