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
import user.UserDAO;
import user.UserNotify;
import user.UserProduct;
import virtual.ListNotify;

/**
 *
 * @author 84348
 */
@WebServlet(name = "NotifyImportController", urlPatterns = {"/NotifyImportController"})
public class NotifyImportController extends HttpServlet {

    private static final String ERROR = "listProductManager.jsp";
    private static final String SUCCESS = "MainController?action=CountZero";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.getAttribute("LIST_PRODUCT_NOTIFY");
                session.getAttribute("LIST_NOTIFY");
            }

            UserDAO dao = new UserDAO();
            List<UserProduct> listProduct = dao.getListProduct();
            if (listProduct.size() > 0) {
                session.setAttribute("LIST_PRODUCT_NOTIFY", listProduct);
            }
            List<UserProduct> product = (List<UserProduct>) session.getAttribute("LIST_PRODUCT_NOTIFY");
            if (product != null) {
                if (product.size() > 0) {
                    for (UserProduct prodct : product) {
                        String p = prodct.getProductID();
                        String name = dao.getListNotify(p, p, p);
                        if (name!=null) {
                            UserNotify noti = new UserNotify(name,p);
                            ListNotify cart = (ListNotify) session.getAttribute("LIST_NOTIFY");
                            if (cart == null) {
                                cart = new ListNotify();
                            }
                            cart.add(noti);
                            session.setAttribute("LIST_NOTIFY", cart);
                        }

                    }
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
