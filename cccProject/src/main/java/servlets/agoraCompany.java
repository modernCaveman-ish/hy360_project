/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import database.tables.EditCompanyTable;
import database.tables.EditCompanyTransactionTable;
import database.tables.EditPersonTable;
import database.tables.EditPersonTransactionTable;
import database.tables.EditSellerTable;
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
import mainClasses.Company;
import mainClasses.CompanyTransaction;
import mainClasses.Seller;

/**
 *
 * @author Dimitra
 */
public class agoraCompany extends HttpServlet {

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
            out.println("<title>Servlet agoraCompany</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet agoraCompany at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
         try {
              connectDB connect = new connectDB();
              Connection con = connect.connectionDB();
              Statement stm = con.createStatement();

              EditCompanyTable ect = new EditCompanyTable();//gia ton pinaka ths etaireias
              EditSellerTable est = new EditSellerTable();//gia ton pinaka tou emporou
              CompanyTransaction ct = new CompanyTransaction();//synallagh metaksi emporou kai etaireias
              EditCompanyTransactionTable edpt = new EditCompanyTransactionTable();//gia twn pinaka synallagwn
              Company c = new Company();//object person
              Seller s = new Seller();//object seller

            String company_query = "SELECT `iban`, `name`, `debt`, `debt_limit`, `balance`, `exp_date` FROM `company` WHERE iban = '" + request.getParameter("iban_company") +"'";
            String seller_query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + request.getParameter("iban_seller") + "'";
            
           // Get company first
            ResultSet rs = stm.executeQuery(company_query);
           //kane set up to company
             while(rs.next()) {
                //set up company first
                c.setUpFromResultSet(rs);
               // c.print();
            }
             //Now get the seller 
            rs = stm.executeQuery(seller_query);
             while(rs.next()) {
                s.setUpFromResultSet(rs);
                s.print();
            }
              
              //ct.setUp(request);
  
        if ( !"0".equals(c.getBalance()))//kanonika prp >0 alla epeidh einai string den to pairnei
        {  
            edpt.insertCompanyTransaction(s);//??
            ect.updateCompany(c);
            est.updateSeller(s);//kane updates tous pinakes twn seller kai person meta th synallagh
        }else{
             System.out.println("Not enough balance!cannot buy!Available balance is :" + c.getBalance());
       }
           stm.close();
           con.close();
         
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
