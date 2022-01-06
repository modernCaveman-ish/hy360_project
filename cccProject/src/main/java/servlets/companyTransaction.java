/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.CompanyTransaction;
import mainClasses.PersonTransaction;

/**
 *
 * @author Dimitra
 */

//TODO FIX ON BALANCE ON BUYER AND ON SELLER AND CHECK IF THE BALANCE IS ENOUGH
// THIS VERSION IS TOO STANDARD ONLY INSERTS IN THE DATABASE

public class companyTransaction extends HttpServlet {

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
            out.println("<title>Servlet personTransaction</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet personTransaction at " + request.getContextPath() + "</h1>");
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

            request.getParameter("transactionType");
            CompanyTransaction ct = new CompanyTransaction();
            ct.setUpFromServletRequest(request);
            //System.out.println("this is companyTransaction and i print iban_company: " + ct.getIban() + ct.getType());

            String query = "INSERT INTO `company_transaction`(`id`, `iban_company`, `w_id`,`iban_seller`,`type`, `cost`, `tr_date`) VALUES"
           + " ('" + ct.getId() +"','" + ct.getIban_company() +"','" + ct.getW_id() +"','"+ ct.getIban_seller()+ "','" + ct.getType() + "','"+ ct.getCost() +"','" + ct.getTr_date() +"')";
            System.out.println(query);

            connectDB connect = new connectDB();
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            stm.execute(query);


        } catch (SQLException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
