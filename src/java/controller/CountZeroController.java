/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import productManager.UserProducNotiManager;
import user.UserDAO;
import virtualSeller.ListProductNotiManager;

/**
 *
 * @author 84348
 */
@WebServlet(name = "CountZeroController", urlPatterns = {"/CountZeroController"})
public class CountZeroController extends HttpServlet {

    private static final String ERROR = "listProductManager.jsp";
    private static final String SUCCESS = "listProductManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("LIST_ZERO");
                session.removeAttribute("LIST_ZERO_ONE");
            }
            UserDAO dao = new UserDAO();
            List<UserProducNotiManager> listUser = dao.getZore();
            if (listUser.size() > 0) {
                session.setAttribute("LIST_ZERO", listUser);
                List<UserProducNotiManager> product = (List<UserProducNotiManager>) session.getAttribute("LIST_ZERO");
                if (product != null) {
                    if (product.size() > 0) {
                        for (UserProducNotiManager prodct : product) {
                            UserProducNotiManager noti = new UserProducNotiManager(prodct.getProductID(),prodct.getName());
                            ListProductNotiManager cart = (ListProductNotiManager) session.getAttribute("LIST_ZERO_ONE");
                            if (cart == null) {
                                cart = new ListProductNotiManager();
                            }
                            cart.add(noti);
                            session.setAttribute("LIST_ZERO_ONE", cart);

                        }
                    }
                }
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "No result is found.");
            }
        } catch (Exception e) {
            System.out.println("Error at SearchController: " + e.toString());
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
