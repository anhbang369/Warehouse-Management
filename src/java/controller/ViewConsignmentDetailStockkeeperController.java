/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryStockeepr.BinDAO;
import inventoryStockeepr.BinDTO;
import inventoryStockeepr.InventoryDAO;
import inventoryStockeepr.InventoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GMT
 */
@WebServlet(name = "ViewConsignmentDetailStockkeeperController", urlPatterns = {"/ViewConsignmentDetailStockkeeperController"})
public class ViewConsignmentDetailStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewProduct.jsp";
    private static final String SUCCESS = "viewConsignmentDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int invenID = Integer.parseInt(request.getParameter("inventoryID"));
            InventoryDAO dao = new InventoryDAO();
            InventoryDTO inven = dao.getInven(invenID);
            BinDAO bDao = new BinDAO();
            List<BinDTO> listBin = bDao.getListBin(invenID);
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("LIST_BIN");
                session.removeAttribute("INVENTORY");
            }
            if (inven != null) {
                session.setAttribute("INVENTORY", inven);
                if (listBin.size() > 0 && inven.getNote().equals("Available")) {
                    session.setAttribute("LIST_BIN", listBin);
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR", "Cannot find this inventory.");
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
