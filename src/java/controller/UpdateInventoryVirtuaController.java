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
import inventoryAccountant.UserInventoryVirtual;
import virtual.ListInventory;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateInventoryVirtuaController", urlPatterns = {"/UpdateInventoryVirtuaController"})
public class UpdateInventoryVirtuaController extends HttpServlet {

    private static final String ERROR = "createInventory.jsp";
    private static final String SUCCESS = "createInventory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            int quality = Integer.parseInt(request.getParameter("quality"));
            int quantityInChecking = Integer.parseInt(request.getParameter("quantityInChecking"));
            String note = request.getParameter("note");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            HttpSession session = request.getSession();
            if (session != null) {
                ListInventory cart = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                if (cart != null) {
                    if (quality > 0) {
                        if (quantityInChecking > 0) {
                            if (cart.getListInventory().containsKey(productID)) {
                                if (quantity != quantityInChecking || quality < 100) {
                                    if (!note.equals("")) {
                                        UserInventoryVirtual tea = cart.getListInventory().get(productID);
                                        tea.setQuality(quality);
                                        tea.setQuantityInChecking(quantityInChecking);
                                        tea.setNote(note);
                                        cart.edit(productID, tea);
                                        session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                        url = SUCCESS;
                                    } else {
                                        request.setAttribute("ERROR", "Please enter note.");
                                    }
                                } else {
                                    UserInventoryVirtual tea = cart.getListInventory().get(productID);
                                    tea.setQuality(quality);
                                    tea.setQuantityInChecking(quantityInChecking);
                                    tea.setNote(note);
                                    cart.edit(productID, tea);
                                    session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                    url = SUCCESS;
                                }
                            }
                        } else {
                            request.setAttribute("ERROR", "Quantity when checking after updating must be greater than 0. Please re-enter.");
                        }
                    } else {
                        request.setAttribute("ERROR", "Quality after update must be greater than 0. Please re-enter.");
                    }
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
