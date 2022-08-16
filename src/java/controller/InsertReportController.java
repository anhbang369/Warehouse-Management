/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryAccountant.DAOInventory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import time.Timer;
import inventoryAccountant.UserReportInventory;
import virtual.ListInventory;

/**
 *
 * @author 84348
 */
@WebServlet(name = "InsertReportController", urlPatterns = {"/InsertReportController"})
public class InsertReportController extends HttpServlet {

    private static final String ERROR = "createInventory.jsp";
    private static final String SUCCESS = "MainController?action=InsertReportDetail";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ListInventory ca = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
            boolean createRepor = false;
            if (ca != null) {
                if (ca.getListInventory().size() > 0) {
                    DAOInventory dao = new DAOInventory();
                    Timer t = new Timer();
                    String checkingDate = t.timeNow();
                    UserReportInventory userReport = new UserReportInventory(checkingDate);
                    createRepor = dao.createReport(userReport);
                    if (createRepor == true) {
                        url = SUCCESS;
                    }
                }else{
                request.setAttribute("ERROR_ADD", "You must add the product inside the inventory.");
            }

            }else{
                request.setAttribute("ERROR_ADD", "You must add the product inside the inventory.");
            }

        } catch (Exception e) {
            System.out.println("Error at InsertController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
