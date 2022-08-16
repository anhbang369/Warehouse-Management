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
import ListStockeeper.ChooseBinDTO;
import ListStockeeper.InventoryTempDTO;
import inventoryStockeepr.BinDAO;
import inventoryStockeepr.BinDTO;
import inventoryStockeepr.InventoryDAO;
import inventoryStockeepr.ProductDAO;
import time.Timer;

/**
 *
 * @author GMT
 */
@WebServlet(name = "CreateConsignmentStockkeeperController", urlPatterns = {"/CreateConsignmentStockkeeperController"})
public class CreateConsignmentStockkeeperController extends HttpServlet {

    private static final String ERROR = "createConsignment.jsp";
    private static final String SUCCESS = "createConsignment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Timer t = new Timer();
            /*List<InventoryTempDTO> inven = (List<InventoryTempDTO>) session.getAttribute("INVEN");
            List<ChooseBinDTO> listChosen = (List<ChooseBinDTO>) session.getAttribute("LIST_CHOOSE");*/
            List<ChooseBinDTO> listChosen = (List<ChooseBinDTO>) session.getAttribute("LIST_CHOOSE");
            InventoryTempDTO inven = (InventoryTempDTO) session.getAttribute("INVEN");

            InventoryDAO daoI = new InventoryDAO();
            ProductDAO daoP = new ProductDAO();
            BinDAO daoB = new BinDAO();
            int createInventory = 0;
            boolean updateProduct = false;
            int editBin = 0;
            boolean updateQuantityBin = false;
            if (inven != null) {
                if (listChosen != null) {

                    String inputDate = t.timeNow();
                    String warranty = inven.getWarranty();
                    String stockKeeperID = inven.getStockKeeperID();
                    String productID = inven.getProductID();
                    int quantityOnHand = inven.getQuantityOnHand();
                    InventoryTempDTO user = new InventoryTempDTO(quantityOnHand, inputDate, warranty, "Available", stockKeeperID, productID, quantityOnHand);
                    createInventory = daoI.createInventory(user);

                    int a = inven.getQuantity(), count = 0;
                    if (createInventory > 0) {
                        updateProduct = daoP.updateProductQuantity(productID, inven.getQuantity());
                        for (ChooseBinDTO i : listChosen) {
                            String binID = i.getBinID();
                            BinDTO bin = daoB.getBinChoose(binID);
                            if (bin.getCapacity() - bin.getQuantity() > a) {
                                editBin = daoI.sumBin(a, binID);
                                //new
                                a = 0;
                            } else {
                                editBin = daoI.sumBin(bin.getCapacity() - bin.getQuantity(), binID);
                                //new
                                a = a - (bin.getCapacity() - bin.getQuantity());
                            }
                            updateQuantityBin = daoB.updateQuantityBin(bin.getCapacity() - bin.getQuantity(), editBin, binID, createInventory);
                            if (updateQuantityBin == true) {
                                //new
                                count = 1;
                            }

                        }
                    }
                    //new
                    if (count == 1) {
                        request.setAttribute("ERROR", "Create successfully.");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Create fail.");
                        url = ERROR;
                    }
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
