/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryStockeepr.InventoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sort.SortInventoryListDateA;
import sort.SortInventoryListDateD;

/**
 *
 * @author 84348
 */
@WebServlet(name = "SortConsignmentStockkeeperController", urlPatterns = {"/SortConsignmentStockkeeperController"})
public class SortConsignmentStockkeeperController extends HttpServlet {

    private static final String ERROR="viewConsignment.jsp";
    private static final String SUCCESS="viewConsignment.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            HttpSession session = request.getSession();
            List<InventoryDTO> inven = (List<InventoryDTO>) session.getAttribute("LIST_INVEN");
            if(search.equalsIgnoreCase("1")){
                Collections.sort(inven, new SortInventoryListDateA());
                url=SUCCESS;
            }else{
                Collections.sort(inven, new SortInventoryListDateD()); 
                url=SUCCESS;
            }

        } catch (Exception e) {
            System.out.println("Error at SortController: " + e.toString());
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
