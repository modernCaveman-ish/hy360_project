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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Company;
import mainClasses.Person;
import mainClasses.Seller;
import mainClasses.User;

/**
 *
 * @author stelios
 */
public class ListUsersWithDebt extends HttpServlet {

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
        
        LinkedList<Company> comp_list = new LinkedList<Company>();
        LinkedList<Seller> seller_list = new LinkedList<Seller>();
        LinkedList<Person> person_list  = new LinkedList<Person>();
        LinkedList<User> ll = new LinkedList();
        
        
        //Company Query
        String comp_query = "SELECT * FROM `company` WHERE debt > '0'";
        //Seller Query
        String seller_query = "SELECT * FROM `seller` WHERE debt > '0'";
        //Person Query
        String person_query = "SELECT * FROM `person` WHERE debt > '0'";
        
        
        String mega_query = "(SELECT `iban`, `debt`, `name` FROM `seller` WHERE debt > 0 UNION ALL SELECT `iban`, `debt`, `name` FROM `person` WHERE debt > 0 UNION ALL SELECT `iban`, `debt`, `name` FROM `company` WHERE debt > 0) ORDER BY debt";
        
        ResultSet rs = stm.executeQuery(mega_query);
        
        
        while(rs.next()){
            User user = new User();
            user.setIban(rs.getString("iban"));
            user.setDebt(rs.getString("debt"));
            user.setName(rs.getString("name"));
            
            ll.add(user);
        }
        
        for(int i=0; i<ll.size(); i++){
            System.out.println(ll.get(i).getDebt());
        }
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListUsersWithDebt</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListUsersWithDebt <br>Info<br></h1>");
            for(int i=0; i<ll.size(); i++){
                out.println("<h3>Iban: " + ll.get(i).getIban() + ", Name: " + ll.get(i).getName() + ", Debt: " + ll.get(i).getDebt() + "</h3>");
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
            Logger.getLogger(ListUsersWithDebt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListUsersWithDebt.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListUsersWithDebt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListUsersWithDebt.class.getName()).log(Level.SEVERE, null, ex);
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
