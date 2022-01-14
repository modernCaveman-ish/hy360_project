/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditCompanyTable;
import database.tables.EditCompanyTransactionTable;
import database.tables.EditSellerTable;
import database.tables.EditWorkerTable;
import java.io.IOException;
import java.io.PrintWriter;
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
public class returnCompany2 extends HttpServlet {

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
        CompanyTransaction search_trans = new CompanyTransaction();
        
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
                out.println("<title>Servlet returnCompany</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error in Epistrofh, invalid values entered<br>iban_company: " + iban_company
                        + ", worker_id: " + worker_id + ", iban_seller: " + iban_seller + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        
        worker.print();
        company.print();
        
        Integer w_iban = Integer.parseInt(worker.getIban_company());
        Integer company_iban = Integer.parseInt(company.getIban());
        
        System.out.println("iban_worker_company: " + worker.getIban_company() + ", " + iban_company + ", iban_company: " + company.getIban() + ", " + worker_id);   
        //Check if worker is not from that company

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
        
        //search for previous transaction
        search_trans = ectt.getCompanyTransaction(iban_company, cost, iban_seller, trans_type, cost, tr_date);
        search_trans.print();
        
        
        //Actions for epistrofh
        success = company.epistrofi(Integer.parseInt(cost));
        seller.print();
     
        //SetUp CompanyTransaction
        if( success ){
            seller.epistrofi(Integer.parseInt(cost));
            // TODO insert new CompanyTransaction
            comp_trans.CompanyTransaction(iban_company, worker_id, iban_seller, trans_type, cost, tr_date);
            ectt.insertCompanyTransaction(comp_trans);
        
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet returnCompany</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet returnCompany at " + "DONE" + "</h1>");
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
                out.println("<title>Servlet returnCompany</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid Values for Epistrofh </h1>");
                out.println("</body>");
                out.println("</html>");
            }
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