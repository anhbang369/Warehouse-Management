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
import receiptAccountant.DAOReceipt;
import receiptAccountant.UserReceiptDetailS;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ShowDetailReceiptController", urlPatterns = {"/ShowDetailReceiptController"})
public class ShowDetailReceiptController extends HttpServlet {

    private static final String ERROR="MainController?action=SeacrhReceipt&search=&searchSeceond=";
    private static final String SUCCESS="MainController?action=SortReceipt";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            int receiptID = Integer.parseInt(request.getParameter("receiptID"));
            DAOReceipt dao = new DAOReceipt();
            List<UserReceiptDetailS> listDetail = dao.getListShowOrderDetail(receiptID);
            if(listDetail.size() >0){
                request.setAttribute("LIST_DETAIL_RECEIPT", listDetail);
                url = SUCCESS;
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
