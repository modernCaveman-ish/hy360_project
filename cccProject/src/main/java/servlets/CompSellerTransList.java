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
import mainClasses.CompanyTransaction;
import mainClasses.PersonTransaction;

/**
 *
 * @author stelios
 */
public class CompSellerTransList extends HttpServlet {

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
            out.println("<title>Servlet CompSellerTransList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompSellerTransList at " + request.getContextPath() + "</h1>");
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
            String company_iban = request.getParameter("company_iban");
            String worker_id    = request.getParameter("worker_id");
            String seller_iban  = request.getParameter("seller_iban");
            String from_date    = request.getParameter("from_date");
            String to_date      = request.getParameter("to_date");
            ArrayList<String> answer = new ArrayList<String>();
            
            connectDB connect = new connectDB();
            Connection con = connect.connectionDB();
            Statement stm = con.createStatement();
            
            String query = "";
            
            // Build the query
            if( company_iban.isEmpty() || worker_id.isEmpty() || seller_iban.isEmpty() || from_date.isEmpty() || to_date.isEmpty() ) {
                query += "SELECT * FROM `company_transaction`";
            }
            
            // only company_iban filled
            if( !company_iban.isEmpty() && worker_id.isEmpty() && seller_iban.isEmpty() && from_date.isEmpty() && to_date.isEmpty() ) {
                query = "SELECT * FROM `company_transaction` WHERE iban_company='"+ company_iban +"'";
                
            } else if( company_iban.isEmpty() && !worker_id.isEmpty() && seller_iban.isEmpty() && from_date.isEmpty()  && to_date.isEmpty() ){ //only worker_id
                query = "SELECT * FROM `company_transaction` WHERE w_id='"+ worker_id +"'";
                
            } else if( company_iban.isEmpty() && worker_id.isEmpty() && !seller_iban.isEmpty() && from_date.isEmpty()  && to_date.isEmpty() ){ // only seller_iban
                query = "SELECT * FROM `company_transaction` WHERE iban_seller='"+ seller_iban +"'";
                
            } else if(company_iban.isEmpty() && worker_id.isEmpty() && seller_iban.isEmpty() && !from_date.isEmpty()  && to_date.isEmpty() ){
                query = "SELECT * FROM `company_transaction` WHERE tr_date >='"+ from_date +"'";
                
            } else if(company_iban.isEmpty() && worker_id.isEmpty() && seller_iban.isEmpty() && from_date.isEmpty()  && !to_date.isEmpty() ) {
                query = "SELECT * FROM `company_transaction` WHERE tr_date <='"+ to_date +"'";
                
            } else if(!company_iban.isEmpty() && !worker_id.isEmpty() && !seller_iban.isEmpty() && !from_date.isEmpty()  && !to_date.isEmpty() ) {
                query = "SELECT * FROM `company_transaction` WHERE iban_company='"+ company_iban +"' AND w_id='"+ worker_id +"' AND iban_seller='"+ seller_iban
                        +"' AND tr_date BETWEEN '"+ from_date +"' AND '"+ to_date +"'";
                
            }
            
            System.out.println("Query: " + query );
            CompanyTransaction trans = new CompanyTransaction();
            
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
                    out.println("<h5>Servlet CompanySellerListTrans answer:<br>"); 

                    for(String str : answer){
                        out.println(str);
                        out.println("<br>");
                    }

                    out.println("</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CompSellerTransList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompSellerTransList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
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
    
}
