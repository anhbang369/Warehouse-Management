/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import inventoryStockeepr.InventoryDAO;
import inventoryStockeepr.InventoryDTO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "SearchInvenStockkeeperController", urlPatterns = {"/SearchInvenStockkeeperController"})
public class SearchInvenStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewConsignment.jsp";
    private static final String SUCCESS = "viewConsignment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String prodID = request.getParameter("productID");
            String statusA = request.getParameter("statusA");
            String statusN = request.getParameter("statusN");
            InventoryDAO daoI = new InventoryDAO();
            List<InventoryDTO> listInven = null;
            if (statusA != null && statusN != null) {
                request.setAttribute("ERROR", "Cannot get any product.");
                session.setAttribute("LIST_INVEN", null);
                url = SUCCESS;
            } else {
                if (statusA != null && statusN == null && !prodID.equals("")) {
                    listInven = daoI.getListInven(statusA, prodID);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
                if (statusA != null && statusN == null && prodID.equals("")) {
                    listInven = daoI.getListInven(statusA);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
                if (statusA == null && statusN == null && !prodID.equals("")) {
                    listInven = daoI.getListInven(prodID);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
                if (statusA == null && statusN != null && prodID.equals("")) {
                    listInven = daoI.getListInven(statusN);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
                if (statusA == null && statusN != null && !prodID.equals("")) {
                    listInven = daoI.getListInven(statusN, prodID);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
                if (statusA == null && statusN == null && prodID.equals("")) {
                    listInven = daoI.getListInven(prodID);
                    session.setAttribute("LIST_INVEN", listInven);
                    request.setAttribute("ERROR", null);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
