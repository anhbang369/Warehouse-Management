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
import orderSeller.DAOOrder;
import orderSeller.UserOrderDetailFullSeller;
import orderSeller.UserOrderDetailSeller;
import orderSeller.UserProductError;
import virtualSeller.ListErrorProduct;
import virtualSeller.ListOrder;

/**
 *
 * @author 84348
 */
@WebServlet(name = "InsertOrderDetailSellerDetailController", urlPatterns = {"/InsertOrderDetailSellerDetailController"})
public class InsertOrderDetailSellerDetailController extends HttpServlet {

    private static final String ERROR = "createOrderSeller.jsp";
    private static final String SUCCESS = "MainController?action=ShowFullOrderSeller";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if(session!=null){
                session.removeAttribute("VIRTUAL_ERROR_PRODUCT");
            }
            ListOrder cart = (ListOrder) session.getAttribute("VIRTUAL_ORDER");
            if (cart != null) {
                if (cart.getListOrder().size() > 0) {
                    boolean createIssue = false;
                    DAOOrder dao = new DAOOrder();
                    int a = dao.getOrderId();
                    for (UserOrderDetailSeller tm : cart.getListOrder().values()) {

                        int quantity = tm.getQuantity();
                        String productID = tm.getProductId();

                        int quantityOrderNotImplement = dao.getQuantityOrderNotImplement(productID);
                        int quantityProduct = dao.getQuantityProduct(productID);
                        if ((quantityProduct - (quantity + quantityOrderNotImplement)) < 0) {
                            UserProductError productError = new UserProductError(productID);
                            ListErrorProduct car = (ListErrorProduct) session.getAttribute("VIRTUAL_ERROR_PRODUCT");
                            if (car == null) {
                                car = new ListErrorProduct();
                            }
                            car.add(productError);
                            session.setAttribute("VIRTUAL_ERROR_PRODUCT", car);
                        }
                    }
                    
                    int checkQuantity = 1;
                    ListErrorProduct car = (ListErrorProduct) session.getAttribute("VIRTUAL_ERROR_PRODUCT");
                    if(car!=null){
                        for (UserProductError tm : car.getListErrorProduct().values()) {
                        String bv = tm.getProductID();
                        checkQuantity++;
                    }
                    }
                    
                    
                    if (checkQuantity==1) {
                        for (UserOrderDetailSeller tm : cart.getListOrder().values()) {

                            int quantity = tm.getQuantity();
                            int orderId = a;
                            String productID = tm.getProductId();

                            UserOrderDetailFullSeller shoppingCart = new UserOrderDetailFullSeller(productID, orderId, quantity);
                            createIssue = dao.createOrderDetail(shoppingCart);
                        }
                    } else {
                        request.setAttribute("ERROR_FINISH", "Insufficient items in stock.");
                    }
                    if (createIssue == true) {
                        if (session != null) {
                            if (cart != null) {
                                session.removeAttribute("VIRTUAL_ORDER");
                                url = SUCCESS;

                            }
                        }

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
