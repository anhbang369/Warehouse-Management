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
import inventoryStockeepr.BinDTO;
import inventoryStockeepr.ProductDAO;
import inventoryStockeepr.ProductDTO;

/**
 *
 * @author GMT
 */
@WebServlet(name = "ViewCreateConsignmentStockkeeperController", urlPatterns = {"/ViewCreateConsignmentStockkeeperController"})
public class ViewCreateConsignmentStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewProduct.jsp";
    private static final String SUCCESS = "createConsignment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String prodID = request.getParameter("productID");
            ProductDAO dao = new ProductDAO();
            ProductDTO product = dao.getProduct(prodID);
            String brand = product.getBrand().split("-")[0];
            BinDAO bDao = new BinDAO();
            List<BinDTO> listBin = bDao.getListBin(brand);
            List<BinDTO> listBinSum = bDao.getListBinSum(brand);
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("PRODUCT");
                session.removeAttribute("LIST_BIN");
                session.removeAttribute("LIST_BIN_SUM");
                session.removeAttribute("LIST_CHOOSE");
                session.removeAttribute("INVEN");
            }
            if (product != null) {
                session.setAttribute("PRODUCT", product);
                if (listBin.size() > 0) {
                    session.setAttribute("LIST_BIN", listBin);
                    if (listBinSum.size() > 0) {
                        session.setAttribute("LIST_BIN_SUM", listBinSum);
                    } else {
                        request.setAttribute("ERROR", "There is no suitable location.");
                    }
                    url = SUCCESS;
                }
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
