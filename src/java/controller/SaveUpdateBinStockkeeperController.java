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
import inventoryStockeepr.BinDAO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "SaveUpdateBinStockkeeperController", urlPatterns = {"/SaveUpdateBinStockkeeperController"})
public class SaveUpdateBinStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewListLocation.jsp";
    private static final String SUCCESS = "MainController?action=ViewBinSK";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String binID = request.getParameter("binID");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String available = request.getParameter("available");
            BinDAO daoB = new BinDAO();
            boolean check = daoB.updateBin(binID, capacity, available);
            HttpSession session = request.getSession();
            if (check) {
                session.setAttribute("SUCCESS", "Updated successful.");
                session.removeAttribute("BIN");
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "No result is found.");
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
