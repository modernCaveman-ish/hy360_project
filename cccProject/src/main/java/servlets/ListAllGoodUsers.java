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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Company;
import mainClasses.Person;
import mainClasses.Seller;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stelios
 */
public class ListAllGoodUsers extends HttpServlet {

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
        
        Company company = new Company();
        Person person   = new Person();
        Seller seller   = new Seller();
        
        connectDB connect = new connectDB();
        Connection con = connect.connectionDB();
        Statement stm = con.createStatement();
        
        ArrayList<Company> company_list = new ArrayList<Company>();
        ArrayList<Person> person_list   = new ArrayList<Person>();
        ArrayList<Seller> seller_list   = new ArrayList<Seller>();
        
        // Query for Companies
        String comp_query = "SELECT * FROM `company` WHERE debt = '0'";
        // Query for Persons
        String person_query = "SELECT * FROM `person` WHERE debt = '0'";
        // Query for Sellers
        String seller_query = "SELECT * FROM `seller` WHERE debt = '0'";
        
        //Get Companies
        ResultSet rs = stm.executeQuery(comp_query);
        
        //Put into the company_list
        while (rs.next()) {
            Company comp_dummy = new Company();
            comp_dummy.setUpFromResultSet(rs);
            company_list.add(comp_dummy);
        }
     
        // Get Persons
        rs = stm.executeQuery(person_query);
        
        // Put them in list
        while(rs.next()) {
            Person person_dummy = new Person();
            person_dummy.setUpFromResultSet(rs);
            person_list.add(person_dummy);
        }
        
        // Get All Sellers
        rs = stm.executeQuery(seller_query);
        
        //Put them in the list
        while(rs.next()) {
            Seller seller_dummy = new Seller();
            seller_dummy.setUpFromResultSet(rs);
            seller_list.add(seller_dummy);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAllGoodUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAllGoodUsers </h3><br><h3>Companies</h3><br>");
            for(int i=0; i<company_list.size(); i++){
                out.println(company_list.get(i).getString());
                out.println("<br>");
            }
            out.println("<br><h3>Persons</h3><br>");
            for(int i=0; i<person_list.size(); i++){
                out.println(person_list.get(i).toString());
                out.println("<br>");
            }
            out.println("<br><h3>Sellerss</h3><br>");
            for(int i=0; i<seller_list.size(); i++){
                out.println(seller_list.get(i).getString());
                out.println("<br>");
            }
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListAllGoodUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListAllGoodUsers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListAllGoodUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListAllGoodUsers.class.getName()).log(Level.SEVERE, null, ex);
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
