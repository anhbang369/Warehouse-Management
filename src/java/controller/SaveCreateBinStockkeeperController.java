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
import inventoryStockeepr.BinDAO;
import inventoryStockeepr.ProductDAO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "SaveCreateBinStockkeeperController", urlPatterns = {"/SaveCreateBinStockkeeperController"})
public class SaveCreateBinStockkeeperController extends HttpServlet {

    private static final String ERROR = "homeStockKeeper.jsp";
    private static final String SUCCESS = "createBin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String bID = request.getParameter("binID");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String available = request.getParameter("available");
            boolean checkValid = true;
            ProductDAO daoP = new ProductDAO();
            List<String> brand = daoP.getListBrandCode();
            String binID = bID + "-1";
            int i = 0;
            //validation
            if (binID.length() < 7) {
                request.setAttribute("ERROR", "ID must be at least 5 char.");
                checkValid = false;
            }
            i = 0;
            for (String b : brand) {
                if (available.equals(b)) {
                    i = 1;
                }
            }
            if (i == 0) {
                request.setAttribute("ERROR", "Brand code is not found.");
                checkValid = false;
            }
            BinDAO daoB = new BinDAO();
            if (checkValid) {
                boolean check = daoB.createBin(binID, capacity, available);
                if (check) {
                    request.setAttribute("ERROR", "Create success.");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Duplicate ID.");
                    url = SUCCESS;
                }
            } else {
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
