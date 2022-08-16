/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ListStockeeper.InventoryTempDTO;
import inventoryStockeepr.ProductDTO;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "SaveInvenStockkeeperController", urlPatterns = {"/SaveInvenStockkeeperController"})
public class SaveInvenStockkeeperController extends HttpServlet {

    private static final String ERROR = "createConsignment.jsp";
    private static final String SUCCESS = "createConsignment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("INVEN");
                session.removeAttribute("LIST_CHOOSE");
            }
            ProductDTO product = (ProductDTO) session.getAttribute("PRODUCT");
            Timer t = new Timer();
            String inputDate = t.timeNow();
            String warranty = request.getParameter("warranty");
            String note = request.getParameter("note");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String skID = loginUser.getAccountID();
            String pID = product.getProductID();
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int quantityOnHand = Integer.parseInt(request.getParameter("quantityOnHand"));

            InventoryTempDTO inven = new InventoryTempDTO(quantity, inputDate, warranty, note, skID, pID, quantityOnHand);
            session.setAttribute("INVEN", inven);
            url = SUCCESS;

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
