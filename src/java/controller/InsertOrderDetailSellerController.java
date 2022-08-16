/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orderSeller.DAOOrder;
import orderSeller.UserOrderSeller;
import time.Timer;
import user.UserDTO;
import virtualSeller.ListOrder;

/**
 *
 * @author 84348
 */
@WebServlet(name = "InsertOrderDetailSellerController", urlPatterns = {"/InsertOrderDetailSellerController"})
public class InsertOrderDetailSellerController extends HttpServlet {

    private static final String ERROR = "createOrderSeller.jsp";
    private static final String SUCCESS = "MainController?action=InsertOrderDetailSellerDetail";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ListOrder ca = (ListOrder) session.getAttribute("VIRTUAL_ORDER");
            DAOOrder dao = new DAOOrder();
            boolean createReceipt = false;
            if (ca != null) {
                if (ca.getListOrder().size() > 0) {
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    String customerName = request.getParameter("custom");
                    String address = request.getParameter("address");
                    String phoneNumber = request.getParameter("phone");
                    String note = request.getParameter("note");
                    String deliveryDate = request.getParameter("date");                   
                    String sellerID = loginUser.getAccountID();
                    String status = "Not Confirm";
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DAY_OF_YEAR, 30);
                    Calendar g = Calendar.getInstance();
                    g.add(Calendar.DAY_OF_YEAR, 3);
                    Date d = c.getTime();
                    Date e = g.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(d);
                    String strDate1 = dateFormat.format(e);
                    if (customerName.equalsIgnoreCase("") || address.equalsIgnoreCase("") || phoneNumber.equalsIgnoreCase("") || deliveryDate.equalsIgnoreCase("")) {
                        request.setAttribute("ERROR_FINISH", "Please fill out all information.");

                    } else {
                        if (phoneNumber.length() == 10) {
                            if (deliveryDate.compareTo(strDate) <= 0) {
                                if(deliveryDate.compareTo(strDate1)>=0){
                                    UserOrderSeller tm = new UserOrderSeller(customerName, address, phoneNumber, status, note, deliveryDate, sellerID);
                                createReceipt = dao.createOrder(tm);
                                if (createReceipt == true) {
                                    url = SUCCESS;
                                }
                                }else{
                                    request.setAttribute("ERROR_FINISH", "The delivery date must be 3 days before the delivery date.");
                                }
                            }else{
                                request.setAttribute("ERROR_FINISH", "Delivery date must be in about 30 days.");
                            }
                        } else {
                            request.setAttribute("ERROR_FINISH", "Phone number must be 10 digits.");
                        }
                    }

                } else {
                    request.setAttribute("ERROR_FINISH", "You must add the product to the order.");
                }
            } else {
                //request.setAttribute("ERROR_FINISH", "You must add the product to the invoice.");
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
