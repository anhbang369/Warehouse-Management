/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryStockeepr.BinDAO;
import inventoryStockeepr.BinDTO;
import inventoryStockeepr.ProductDAO;
import inventoryStockeepr.UserUpdatePosition;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateBinStockeppController", urlPatterns = {"/UpdateBinStockeppController"})
public class UpdateBinStockeppController extends HttpServlet {

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
            BinDAO dao = new BinDAO();
            BinDTO bin = dao.getBinChoose(binID);
            //thêm mới chỗ này
            ProductDAO daoP = new ProductDAO();
            List<String> brand = daoP.getListBrandCode();
            int count = 0;
            for (String b : brand) {
                if (b.equals(available.toUpperCase())) {
                    count = 1;
                }
            }
            //thêm cặp if (count) else
            if (count == 1) {
                if (bin.getQuantity() <= capacity) {
                    UserUpdatePosition user = new UserUpdatePosition(binID, capacity, available);
                    boolean check = dao.updatePositionBin(user);
                    if (check) {
                        request.setAttribute("ERROR", "Update successful.");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", "Capacity cannot lower than quantity.");
                }
            } else {
                request.setAttribute("ERROR", "Cannot found this brand");
            }

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
