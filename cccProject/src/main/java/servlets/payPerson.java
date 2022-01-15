/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import connectDB.connectDB;
import database.tables.EditPersonTable;
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

/**
 *
 * @author Dimitra
 */
public class payPerson extends HttpServlet {

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
        
        String iban_person = request.getParameter("iban_person");
        String cost        = request.getParameter("cost");
        String tr_date     = request.getParameter("tr_date");
        boolean paid = false; // Default
        
        EditPersonTable ept = new EditPersonTable();
        Person person = ept.getPerson(iban_person);
        
        if( cost == "" ){
            System.out.println("Cost is blank!!");
            paid = person.payDebt();
        } else {
            System.out.println("Cost is " + cost);
            paid = person.payDebt(Integer.parseInt(cost));
        }
        
        if(paid == true) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet payPerson</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>Person.payDebt() Success!!!<br>Person info: " + person.toString() + "</h3>");
                out.println("</body>");
                out.println("</html>");
            }
        } else if( paid == false ){
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet payPerson</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>Person.payDebt() Unsuccessful, invalid values...<br>Balance: " + person.getBalance() + "<br>Debt: " + person.getDebt() + "<br>Cost: " + cost + "</h3>");
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
//        try {
//              connectDB connect = new connectDB();
//              Connection con = connect.connectionDB();
//              Statement stm = con.createStatement();
//
//              EditPersonTable ept = new EditPersonTable();//gia ton pinaka tou idiwth
//              PersonTransaction pt = new PersonTransaction();//synallagh metaksi emporou kai idiwth
//              Person p = new Person();//object person
//            
//            String person_query = "SELECT `iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt` FROM `person` WHERE iban = '" + request.getParameter("iban_person") +"'";
//                 
//           // Get Person 
//            ResultSet rs = stm.executeQuery(person_query);
//           //kane set up to person
//
//             while(rs.next()) {
//                //set up a person first
//                p.setUpFromResultSet(rs);
//                p.print();
//            }
//
//              //pt.setUp(request);
//
//        //metatroph twn string se float
//
//        Float cot =  Float.parseFloat(pt.getCost());// cost pou thelw na ksexrewthei
//        Float blc = Float.parseFloat(p.getBalance());//to balance
//        Float dbt = Float.parseFloat(p.getDebt());//xreos idiwth
//
//        //edpt.insertPersonTransaction(pt);//oxiii den apothikeyoume plhrwmes
//
//        if ( dbt>0)//an o idwths exei xreh pragmatopoiei plhrwmh tou posou ofeilhs
//        {  
//            if(blc>=0){ //an exei arketo ypoloipo
//                  blc=blc-cot;//poso thelw na eksoflithei afaireitai ap to balance 
//                  dbt= dbt-cot;
//            }
//        }
//           Float new_balance = blc;//neo balance idiwth
//           Float new_debt = dbt;//neo balance idiwth
//
//           p.setBalance(new_balance.toString());
//           p.setDebt(new_debt.toString());
//                 
//           ept.updatePerson(p);
//       
//           stm.close();
//           con.close();
//       
//        } catch (SQLException ex) {
//            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(personTransaction.class.getName()).log(Level.SEVERE, null, ex);
//       }

        


    }    
}
    
