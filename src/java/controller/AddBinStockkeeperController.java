/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 *
 * @author GMT
 */
@WebServlet(name = "AddBinStockkeeperController", urlPatterns = {"/AddBinStockkeeperController"})
public class AddBinStockkeeperController extends HttpServlet {

    private static final String ERROR = "viewProduct.jsp";
    private static final String SUCCESS = "createConsignment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String binID = request.getParameter("binID");
            BinDAO bDao = new BinDAO();
            BinDTO bin = bDao.getBinChoose(binID);
            List<ChooseBinDTO> listChoose = (List<ChooseBinDTO>) session.getAttribute("LIST_CHOOSE");
            int quantity;
            if (listChoose == null) {
                InventoryTempDTO inven = (InventoryTempDTO) session.getAttribute("INVEN");
                quantity = inven.getQuantity();
                session.setAttribute("QUAN", quantity);
            } else {
                quantity = (int) session.getAttribute("QUAN");
            }
            if (session.getAttribute("LIST_CHOOSE") == null) {
                if (bin != null && quantity > 0) {
                    listChoose = new ArrayList<>();
                    int availableQuan = bin.getCapacity() - bin.getQuantity();
                    if (availableQuan > quantity) {
                        listChoose.add(new ChooseBinDTO(binID, quantity));
                        quantity = 0;
                        session.setAttribute("QUAN", quantity);
                    } else {
                        listChoose.add(new ChooseBinDTO(binID, availableQuan));
                        
                        quantity = quantity - availableQuan;
                        session.setAttribute("QUAN", quantity);
                    }
                }
            } else {
                if (bin != null && quantity > 0) {
                    boolean check = bDao.checkDuplicate(listChoose, bin);
                    if (check) {
                        int availableQuan = bin.getCapacity() - bin.getQuantity();
                        if (availableQuan > quantity) {
                            listChoose.add(new ChooseBinDTO(binID, quantity));
                            quantity = 0;
                            session.setAttribute("QUAN", quantity);
                        } else {
                            listChoose.add(new ChooseBinDTO(binID, quantity));
                            quantity = quantity - availableQuan;
                            session.setAttribute("QUAN", quantity);
                        }
                    } else {
                        request.setAttribute("ERROR", "This bin is chosen.");
                        url = SUCCESS;
                    }
                }
            }
            if (listChoose != null) {
                session.setAttribute("LIST_CHOOSE", listChoose);
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
