/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditSellerTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Seller;

/**
 *
 * @author Dimitra
 */
public class deleteSeller extends HttpServlet {

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
          
        EditSellerTable est = new EditSellerTable();
        Seller seller = new Seller();
        
        seller = est.getSeller(request.getParameter("iban"));
        
        if(seller.getDebt().equals("0")){   
             // Success
             est.deleteSeller(seller.getIban());
             
             response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteSeller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Delete Seller was successful<br>Seller with iban: " + seller.getIban() + ", deleted!!</h3>");
            out.println("</body>");
            out.println("</html>");
        }
             
        }else{
            //Unsuccessful
            System.out.println("Seller has debt and cannot be deleted the debt is: " + seller.getDebt());
             
             response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteSeller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Delete Seller was Unsuccessful <br>Seller with iban: " 
                    + seller.getIban() + ", has debt: " + seller.getDebt() + "</h3>");
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
      /*  EditSellerTable ect = new EditSellerTable();
        Seller seller = new Seller();
        
        seller = ect.getSeller(request.getParameter("iban"));
        if(seller.getDebt().equals("0"))
        {
             ect.deleteSeller(seller.getIban());
        }else{
             System.out.println("Seller has debt and cannot be deleted the debt is: " + seller.getDebt());
       }
    */
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

