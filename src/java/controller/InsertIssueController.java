/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import issueAccountant.DAOIssue;
import issueAccountant.UserIssue;
import issueAccountant.UserOrderDetail;
import issueAccountant.UserProductUS;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "InsertIssueController", urlPatterns = {"/InsertIssueController"})
public class InsertIssueController extends HttpServlet {

    private static final String ERROR = "MainController?action=SeacrhOrder&OrderI=&CustomerNam=&Addres=&PhoneNumbe=";
    private static final String SUCCESS = "MainController?action=ShowIssueFull";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean flag = false;
            Timer t = new Timer();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String note = "Implementing";
            String accountantID = loginUser.getAccountID();
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            String DateP = t.timeNow();
            DAOIssue dao = new DAOIssue();
            String sellerID = dao.getSellerID(orderID);//
            UserIssue issue = new UserIssue(note, accountantID, sellerID, orderID, DateP);//
            List<UserOrderDetail> listOrderDetail = dao.getListOrderDetail(orderID);//
            if (listOrderDetail.size() > 0) {//
                boolean create = dao.createIssue(issue);//
                if(create==true){//
                    String status = "Confirmed";//
                    UserProductUS proU = new UserProductUS(orderID, status);//
                    boolean updateS = dao.updateQuantityProductSs(proU);//
                    if(updateS==true){//
                        flag = true;//
                    }//
                }//
            }
            if (flag == true) {
                url = SUCCESS;
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
