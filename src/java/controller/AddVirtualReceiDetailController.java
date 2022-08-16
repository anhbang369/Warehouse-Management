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
import receiptAccountant.DAOReceipt;
import receiptAccountant.UserReceiptDetail;
import virtual.ListReceiptM;

/**
 *
 * @author 84348
 */
@WebServlet(name = "AddVirtualReceiDetailController", urlPatterns = {"/AddVirtualReceiDetailController"})
public class AddVirtualReceiDetailController extends HttpServlet {

    private static final String ERROR = "createReceiptDetail.jsp";
    private static final String SUCCESS = "createReceiptDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean k = true;
            HttpSession session = request.getSession();
            String productID = request.getParameter("productID");
            int quantityInBill = Integer.parseInt(request.getParameter("quantityInBill"));
            int quantityInShipping = Integer.parseInt(request.getParameter("quantityInShipping"));
            DAOReceipt dao = new DAOReceipt();
            int receiptID = dao.getReceiptID();
            String solution = request.getParameter("solution");
            ListReceiptM ca = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
            if (ca != null) {
                if (ca.getListReceipt().size() > 0) {
                    for (UserReceiptDetail tm : ca.getListReceipt().values()) {
                        if (tm.getProductID().equalsIgnoreCase(productID)) {
                            k = false;
                        }
                    }
                }
            }
            if (quantityInBill == quantityInShipping || ((quantityInBill != quantityInShipping) && !solution.equals(""))) {
                if (k == true) {
                    UserReceiptDetail tm = new UserReceiptDetail(quantityInBill, quantityInShipping, productID, receiptID, solution);

                    ListReceiptM cart = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
                    if (cart == null) {
                        cart = new ListReceiptM();
                    }
                    cart.add(tm);
                    session.setAttribute("VIRTUAL_RECEI", cart);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR_CHECK", "Product ID already exists. Please re-enter.");
                }

            } else {
                request.setAttribute("ERROR_CHECK", "Quantity in shipping is not equal quantity in bill. Please enter your solution.");
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
