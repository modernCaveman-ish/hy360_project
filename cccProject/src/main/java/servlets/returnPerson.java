/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
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
import mainClasses.Person;
import mainClasses.PersonTransaction;
import mainClasses.Seller;

/**
 *
 * @author Dimitra
 */
public class returnPerson extends HttpServlet {

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

              EditPersonTable ept = new EditPersonTable();//gia ton pinaka tou idiwth
              EditSellerTable est = new EditSellerTable();//gia ton pinaka tou emporou
              PersonTransaction pt = new PersonTransaction();//synallagh metaksi emporou kai idiwth
              EditPersonTransactionTable edpt = new EditPersonTransactionTable();//gia twn pinaka synallagwn
              Person p = new Person();//object person
              Seller s = new Seller();//object seller
              
            String person_query = "SELECT `iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt` FROM `person` WHERE iban = '" + request.getParameter("iban_person") +"'";
            String seller_query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + request.getParameter("iban_seller") + "'";
            
           // Get Person first
            ResultSet rs = stm.executeQuery(person_query);
           //kane set up to person
           // p.setUpPerson(request);
             while(rs.next()) {
                //set up a person first
                p.setUpFromResultSet(rs);
                p.print();
            }
             //Now get the seller 
            rs = stm.executeQuery(seller_query);
             while(rs.next()) {
                s.setUpFromResultSet(rs);
                s.print();
            }
              
              pt.setUp(request);

        //metatroph twn string se int
        int cot =  Integer.parseInt(pt.getCost());// cost
        Float pft = Float.parseFloat(s.getProfit());// to profit
        Float blc = Float.parseFloat(p.getBalance());//to balance
       // float promithia = Float.parseFloat(s.getPromithia());
         edpt.insertPersonTransaction(pt);//vale th synallagh(EPISTROFH)ston pinaka me tis synallages

            pft=pft-cot;//meiwnete to profit tou seller
             blc=blc + cot;//ayksanete to balanace tou idiwth
            //metatroph int se string

           Float new_balance = new Float(blc);

            p.setBalance(new_balance.toString());
            s.setProfit(pft.toString());
            
           ept.updatePerson(p);
           est.updateSeller(s);// updates tous pinakes twn seller kai person meta th synallagh

           stm.close();
           con.close();
       
        } catch (SQLException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
}

