/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import database.tables.EditCompanyTable;
import database.tables.EditCompanyTransactionTable;
import database.tables.EditSellerTable;
import database.tables.EditWorkerTable;
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
import mainClasses.Worker;

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        String iban_company = request.getParameter("iban_company");
        String iban_seller  = request.getParameter("iban_seller");
        String cost         = request.getParameter("cost");
        String tr_date      = request.getParameter("tr_date");
        String trans_type   = request.getParameter("transactionType");
        String worker_id    = request.getParameter("w_id");
        boolean success     = false; // default

        EditCompanyTable ect = new EditCompanyTable();
        EditCompanyTransactionTable ectt = new EditCompanyTransactionTable();
        EditSellerTable est = new EditSellerTable();
        EditWorkerTable ewt = new EditWorkerTable();
        
        Company company = ect.getCompany(iban_company);
        Seller  seller  = est.getSeller(iban_seller);
        CompanyTransaction comp_trans = new CompanyTransaction();
        Worker worker   = ewt.getWorker(worker_id, iban_company);
        
        System.out.println("IBAN_seller: "  + iban_seller);
        System.out.println("HERE");
        
        
        // Check if null
        if( company.getIban() == null || seller.getIban() == null || worker.getIban_company() == null ){
            //Error Page
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet agoraCompany</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error in Agora, invalid values entered<br>iban_company: " + iban_company
                        + ", worker_id: " + worker_id + ", iban_seller: " + iban_seller + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
        
        worker.print();
        company.print();
        
        System.out.println("iban_worker_company: " + worker.getIban_company() + ", " + iban_company + ", iban_company: " + company.getIban() + ", " + worker_id);
        Integer w_iban = Integer.parseInt(worker.getIban_company());
        Integer company_iban = Integer.parseInt(company.getIban());
        
        
        //Check if worker is not from that company
        if( !worker.getIban_company().equals(company.getIban()) ){
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet agoraCompany</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error in Agora, iban_worker is not the same with iban_company<br>iban_company: " 
                        + company.getIban() + "<br>iban_worker: " + worker.getIban_company() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
        

        //Actions for agora 
        success = company.agora(Integer.parseInt(cost));
        seller.print();
//        seller.agora(Integer.parseInt(cost));
        
        //SetUp CompanyTransaction
        if( success ){
            seller.agora(Integer.parseInt(cost));
            // TODO insert new CompanyTransaction
            comp_trans.CompanyTransaction(iban_company, worker_id, iban_seller, trans_type, cost, tr_date);
            ectt.insertCompanyTransaction(comp_trans);
        
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet agoraCompany</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet agoraCompany Successful <br>Transaction Info:" + 
                   comp_trans.getString() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }


        }else {
            //print message of failure
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet agoraCompany</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid Values for Agora </h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        return;
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
            Logger.getLogger(agoraCompany.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agoraCompany.class.getName()).log(Level.SEVERE, null, ex);
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
//      try {
//              connectDB connect = new connectDB();
//              Connection con = connect.connectionDB();
//              Statement stm = con.createStatement();
//
//              EditCompanyTable ect = new EditCompanyTable();//gia ton pinaka ths etaireias
//              EditSellerTable est = new EditSellerTable();//gia ton pinaka tou emporou
//              CompanyTransaction ct = new CompanyTransaction();//synallagh metaksi emporou kai etaireias
//              EditCompanyTransactionTable edct = new EditCompanyTransactionTable();
//              EditWorkerTable ewt = new EditWorkerTable();//gia ton pinaka tou worker
//
//              Company c = new Company();//object company
//              Seller s = new Seller();//object seller
//              Worker w=new Worker();
//              
//            String company_query = "SELECT `iban`, `name`, `debt`,`debt_limit`,`balance`, `exp_date` FROM `company` WHERE iban = '" + request.getParameter("iban_company") +"'";
//            String seller_query = "SELECT `iban`, `name`, `debt`, `promithia`, `profit` FROM `seller` WHERE iban = '" + request.getParameter("iban_seller") + "'";
//            String worker_query = "SELECT `iban_company`, `id`, `name` FROM `worker` WHERE iban = '" + request.getParameter("iban_company") +"'";
//            
//            ResultSet rs = stm.executeQuery(company_query);
//           //kane set up to company
//             while(rs.next()) {
//                //set up company first
//                c.setUpFromResultSet(rs);
//            }
//            rs = stm.executeQuery(worker_query);
//             //worker
//             while(rs.next()) {
//                //set up worker
//                w.setUpFromResultSet(rs);
//            }
//             //Now get the seller 
//            rs = stm.executeQuery(seller_query);
//             while(rs.next()) {
//                s.setUpFromResultSet(rs);
//                s.print();
//            }
//              
//              ct.setUpFromResultSet(rs);
//    
//        //metatroph twn string se int
//       // int cr_limit = Integer.parseInt(c.getDebt_limit());
//        int cot =  Integer.parseInt(ct.getCost());// cost
//        int pft = Integer.parseInt(s.getProfit());// to profit
//        int blc = Integer.parseInt(c.getBalance());//to balance
//        if ( blc>0)
//        {  
//            edct.insertCompanyTransaction(ct);//vale th synallagh ston pinaka me tis synallages ths etaireias(Agora)
//            ewt.insertWorker(w);//vale twn worker ston pinaka workers afou ekane mia synallagh
//
//            pft+=cot;//prakseis
//             blc=blc - cot;
//            //metatroph int se string
//
//           String str1 = Integer.toString(pft);
//         //String str2 = Integer.toString(cot);
//           String str3 = Integer.toString(blc);
//    
//            c.setBalance(str3);
//            s.setProfit(str1);
//            ect.updateCompany(c);
//            est.updateSeller(s);// updates tous pinakes twn seller kai company meta th synallagh
//            ewt.updateWorker(w);
//        }else{
//             System.out.println("Not enough balance!!Available balance is: " + c.getBalance());
//       }
//           stm.close();
//           con.close();
//         
//        } catch (SQLException ex) {
//            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
//        } 
// 
        } catch (SQLException ex) {
            Logger.getLogger(agoraCompany.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agoraCompany.class.getName()).log(Level.SEVERE, null, ex);
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
