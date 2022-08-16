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
import receiptAccountant.UserReceiptVirtual;
import time.Timer;
import user.UserDTO;
import virtual.ListReceiptM;

/**
 *
 * @author 84348
 */
@WebServlet(name = "AddReceiptController", urlPatterns = {"/AddReceiptController"})
public class AddReceiptController extends HttpServlet {

    private static final String ERROR = "createReceiptDetail.jsp";
    private static final String SUCCESS = "MainController?action=AddReceiptDetail";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ListReceiptM ca = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
            DAOReceipt dao = new DAOReceipt();
            boolean createReceipt = false;
            if (ca != null) {
                if (ca.getListReceipt().size() > 0) {
                    int a = 0;
                    String b = "";
                    for (UserReceiptDetail tm : ca.getListReceipt().values()) {
                        a += tm.getQuantityInBill();
                    }
                    for (UserReceiptDetail tl : ca.getListReceipt().values()) {
                        if (tl.getSolution() == null) {
                            b = "True";
                        }else{
                            b = "False";
                            break;
                        }
                    }

                    Timer t = new Timer();
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    String inputDate = t.timeNow();
                    String status = b;
                    int totalQuantity = a;
                    String note = "";
                    String accountantID = loginUser.getAccountID();
                    String stockKeeperID = request.getParameter("stockKeeperID");
                    //boolean checkS = dao.checkExistAccount(stockKeeperID);

                        UserReceiptVirtual tm = new UserReceiptVirtual(inputDate, status, totalQuantity, note, accountantID, stockKeeperID);
                        createReceipt = dao.createReceipt(tm);
                        if (createReceipt == true) {
                            url = SUCCESS;
                        }
                    

                } else {
                    request.setAttribute("ERROR_FINISH", "You must add the product to the invoice.");
                }
            } else {
                request.setAttribute("ERROR_FINISH", "You must add the product to the invoice.");
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
