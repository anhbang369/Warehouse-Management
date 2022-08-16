/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orderSeller.DAOOrder;
import orderSeller.UserOrderUpdate;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateOrderSellerController", urlPatterns = {"/UpdateOrderSellerController"})
public class UpdateOrderSellerController extends HttpServlet {

    private static final String ERROR = "orderSeller.jsp";
    private static final String SUCCESS = "MainController?action=SearchOrderSeller&OrderI=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int orderID = Integer.parseInt(request.getParameter("orderId"));
            String customerName = request.getParameter("custom");
            String deliveryDate = request.getParameter("deliveryDate");
            String note = request.getParameter("note");
            String status = request.getParameter("status");
            DAOOrder dao = new DAOOrder();
            if (status == null) {//
                status = dao.getStatusOrder(orderID);
            }//
            Timer t = new Timer();
            String f = t.timeNow();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 30);
            Date d = c.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(d);
            boolean b = false;
            if (status.equalsIgnoreCase("Cancel")) {
//                if(note!=null){
//                    b = true;
//                }else{
//                    request.setAttribute("ERROR_FINISH", "Please enter note.");
//                }        
                b = true;
            }
            if (b) {
                if (!note.equals("")) {
                    UserOrderUpdate user = new UserOrderUpdate(customerName, status, note, deliveryDate, orderID);
                    boolean check = dao.updateOrder(user);
                    if (check) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR_FINISH", "Please enter note.");
                }
            } else if (deliveryDate.compareTo(strDate) <= 0 && deliveryDate.compareTo(f) >= 0) {
                UserOrderUpdate user = new UserOrderUpdate(customerName, status, note, deliveryDate, orderID);
                boolean check = dao.updateOrder(user);
                if (check) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_FINISH", "Delivery date must be in about 30 days.");
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
