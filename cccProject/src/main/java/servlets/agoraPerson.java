/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import database.tables.EditPersonTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Person;
import mainClasses.PersonTransaction;
import mainClasses.Seller;

/**
 *
 * @author stelios
 */
public class agoraPerson extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet agoraPerson</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet agoraPerson at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
            connectDB connect = new connectDB();
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            //Create the entities
            Person person = new Person();
            EditPersonTable ept = new EditPersonTable();
            
            Seller seller = new Seller();
            
            request.getParameter("transactionType");
            PersonTransaction pt = new PersonTransaction();
            pt.setUp(request);
//            System.out.println("this is personTransaction and i print iban_person: " + pt.getIban_person() + pt.type);
            
            //get Person and his details
            String person_query = "SELECT `iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt` FROM `person` WHERE iban = '" + request.getParameter("iban_person") +"'";
            String seller_query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + request.getParameter("iban_seller") + "'";
            
            // Get Person first
            ResultSet rs = stm.executeQuery(person_query);
            
            while(rs.next()) {
                //set up a person first then the seller
                //person.setIban();
                person.setUpFromResultSet(rs);
                person.print();
            }
            
            //Now get the seller 
            rs = stm.executeQuery(seller_query);
            
            while(rs.next()) {
                seller.setUpFromResultSet(rs);
                seller.print();
            }
            
            // TODO FIX THE BALANCES AFTER THE BUY THIS IS JUST A TEST TO CHECK THAT ept.updatePerson WORKS!!!!
            person.setBalance("69");
            ept.updatePerson(person);

//            String query = "INSERT INTO `person_transaction`(`iban_person`, `iban_seller`, `type`, `cost`, `tr_date`) VALUES ('" + pt.getIban_person() +"','" + pt.getIban_seller() +"','" + pt.type +"','" + pt.getCost() +"','" + pt.getTr_date() +"')";
//            System.out.println(query);

            //Dirty work
//            connectDB connect = new connectDB();
//            Connection con = connect.connectionDB();
//            Statement stm = con.createStatement();



//            stm.execute(query);
            
            //disconnect
            stm.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
