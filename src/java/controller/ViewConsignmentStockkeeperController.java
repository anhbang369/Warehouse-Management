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
import inventoryStockeepr.InventoryDAO;
import inventoryStockeepr.InventoryDTO;
import inventoryStockeepr.ProductDAO;
import inventoryStockeepr.ProductDTO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "ViewConsignmentStockkeeperController", urlPatterns = {"/ViewConsignmentStockkeeperController"})
public class ViewConsignmentStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewProduct.jsp";
    private static final String SUCCESS = "viewConsignment.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            InventoryDAO dao = new InventoryDAO();
            List<InventoryDTO> listInven = dao.getListInven(search);
            ProductDAO daoP = new ProductDAO();
            ProductDTO product = daoP.getProduct(search);
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("LIST_INVEN");
                if (search.equals("")) {
                    session.removeAttribute("PRODUCT");
                }
            }
            if (listInven.size() > 0) {
                session.setAttribute("LIST_INVEN", listInven);
                if (!search.equals("")) {
                    session.setAttribute("PRODUCT", product);
                }
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "No result is found.");
                url = SUCCESS;
            }
        }catch(Exception e){
            e.printStackTrace();
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
