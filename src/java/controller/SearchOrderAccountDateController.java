/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import issueAccountant.UserOrder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orderSeller.DAOOrder;

/**
 *
 * @author 84348
 */
@WebServlet(name = "SearchOrderAccountDateController", urlPatterns = {"/SearchOrderAccountDateController"})
public class SearchOrderAccountDateController extends HttpServlet {

    private static final String ERROR="orderAccount.jsp";
    private static final String SUCCESS="orderAccount.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            HttpSession session = request.getSession();
            if(session!=null){
                session.removeAttribute("LIST_ORDER_ACCOUNT");
            }
            String search = request.getParameter("search");
            String seacrh1 = request.getParameter("searchR");
            if(search.compareTo(seacrh1)<=0){
                DAOOrder dao = new DAOOrder();
            List<UserOrder> listIssue = dao.getListSearchOrderDate(search, seacrh1);
            if(listIssue.size() >0){
                session.setAttribute("LIST_ORDER_ACCOUNT", listIssue);
                url = SUCCESS;
            }else{
                request.setAttribute("ERROR", "No result is found.");
            }
            }else{
                request.setAttribute("ERROR", "Date not valid.");
            }
        }catch(Exception e){
            System.out.println("Error at ShowController: " + e.toString());
        }finally{
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
