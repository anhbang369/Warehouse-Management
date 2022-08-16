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
import inventoryAccountant.UserInventoryVirtual;
import virtual.ListInventory;

/**
 *
 * @author 84348
 */
@WebServlet(name = "InsertReportDetailController", urlPatterns = {"/InsertReportDetailController"})
public class InsertReportDetailController extends HttpServlet {

    private static final String ERROR = "createInventory.jsp";
    private static final String SUCCESS = "MainController?action=ShowInventory";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ListInventory ca = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
            boolean createReporDetail = false;
            DAOInventory dao = new DAOInventory();
            if (ca != null) {
                if (ca.getListInventory().size() > 0) {
                    for (UserInventoryVirtual tm : ca.getListInventory().values()){
                        int reportID = dao.getReportID();
                        String productID = tm.getProductID();
                        int quality = tm.getQuality();
                        int quantityInChecking = tm.getQuantityInChecking();
                        int quantity = tm.getQuantity();
                        String note = tm.getNote();
                        UserInventoryVirtual reportDetail = new UserInventoryVirtual(reportID, productID, quality, quantityInChecking, quantity, note);
                        createReporDetail = dao.createReportDetail(reportDetail);
                        if (createReporDetail == true) {
                        if (session != null) {
                            if (ca != null) {
                                session.removeAttribute("LIST_INVEN_VIRTUAL");
                                url = SUCCESS;

                            }
                        }

                    }
                    }
                }

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
