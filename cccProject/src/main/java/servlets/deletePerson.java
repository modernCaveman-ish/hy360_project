/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connectDB.connectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stelios
 */
public class deletePerson extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        connectDB connect = new connectDB();
        Connection con = connect.connectionDB();
        Statement st = con.createStatement();
        
        String iban = request.getParameter("iban");
        //Not so fast, first debt has to be 0
        
        String iban_query  = "SELECT `iban`, `debt` FROM `person` WHERE iban = " + iban;
        ResultSet rs = st.executeQuery(iban_query);
        
        while(rs.next()) {
            int member_iban = rs.getInt(1);
//            int member_debt = rs.findColumn("debt");
            int member_debt = rs.getInt(2);
            System.out.print("The member iban is: " + member_iban);
            System.out.println(", and has debt: " + member_debt);
            
            if( member_debt == 0 ) {
                System.out.println("Deleting member with iban: " + member_iban); 
                String iban_str = Integer.toString(member_iban);
                String delete_query = "DELETE FROM `person` WHERE iban = '" + iban_str + "' ";
                st.execute(delete_query);
                
            } else if( member_debt != 0 ) {
                System.out.println("Error, member_debt is not 0, it is " + member_debt);
            }
        }
        

//        String query = "DELETE FROM `person` WHERE iban = '" + iban + "' ";
        
//        st.execute(query);
        con.close();
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet deletePerson</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Person with IBAN: " + request.getParameter("iban") + ", got deleted</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(deletePerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deletePerson.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(deletePerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deletePerson.class.getName()).log(Level.SEVERE, null, ex);
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
